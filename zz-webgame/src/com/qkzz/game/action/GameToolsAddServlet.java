package com.qkzz.game.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.qkzz.game.bean.GameTools;
import com.qkzz.game.service.GameInfoService;
import com.qkzz.game.service.GameToolsService;

/**
 * 用于将游戏内道具添加到数据库中
 * @author Administrator
 *
 */
public class GameToolsAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String uploadImgPath = "/data/7kzz/static/img/ut";
	private static final String uploadTmpPath = "/data/7kzz/static/img/tmp";

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");// 设置字符编码为UTF-8, 统一编码，处理出现乱码问题

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查是否是一个文件上传请求
		if (isMultipart) {

			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置上传文件时用于临时存放文件的内存大小,这里是2K.多于的部分将临时存在硬盘
			factory.setSizeThreshold(1024 * 2);
			// 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录
			factory.setRepository(new File(uploadTmpPath));// 临时文件

			// 用上面的工厂实例化上传组件，
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setFileItemFactory(factory);
			// 如果ServletFileUpload实例中不设置上面的Factory则报java.lang.NullPointerException:
			// No FileItemFactory has been set.

			// 设置最大上传大小 10M
			upload.setSizeMax(1024 * 1024 * 50);
			GameTools gt = new GameTools();
			int gameid = 0;//游戏ID
			String toolidInGame = "";//道具在游戏中的唯一ID
			
			try {
				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {// 依次处理每个文件
					FileItem item = (FileItem) itr.next();
//					String fileName = item.getName();// 获得文件名，包括路径
					System.out.println(item.getName());
					String fieldName = item.getFieldName();
					if(fieldName.equals("act")){
						String fieldValue = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
						if(!fieldValue.equals("addunregisteredtools")) {
							//如果act值不正确，直接返回，不做任何操作
							out.print("{\"status\":-1}");//上传失败
							out.flush();
							out.close();
							return;
						}
						
						System.out.println("fieldName:"+fieldName);
						System.out.println("fieldValue:"+fieldValue);
					} else if(fieldName.equals("image")) {
						//传递过来的道具缩略图二进制流
						
						// create file name
						String filename = System.currentTimeMillis() + ".png";

						// create file path
						String filepath = uploadImgPath + getFilePath() + gameid;

						
						// 文件夹不存在就自动创建
					    if(!new File(filepath).isDirectory())
					        new File(filepath).mkdirs();

//						File fullFile = new File(item.getName());
						File savedFile = new File(filepath,filename);
						item.write(savedFile);
						
						gt.setImg("http://s.7kzz.com/img/ut/"+getFilePath()+gameid+"/"+filename);
						
					} else if(fieldName.equals("id")) {
						//道具在游戏中的唯一ID
						toolidInGame = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
						if(toolidInGame.equals("")) {
							out.print("{\"status\":-2}");//道具ID传递错误
							out.flush();
							out.close();
							return;
						}
						gt.setIdingame(toolidInGame);
					} else if(fieldName.equals("gameid")) {
						//游戏ID
						String gamecode = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
						gameid = GameInfoService.getGameIDByGameCode(gamecode);
						
						if(gameid <= 0) {
							out.print("{\"status\":-3}");//游戏ID传递错误
							out.flush();
							out.close();
							return;
						}

						gt.setGameid(gameid);
					} else if(fieldName.equals("name")) {
						//道具名称
						String name = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
						gt.setName(name);
					}
				}
				
				if(GameToolsService.isToolsInGameExist(gameid, toolidInGame)) {
					out.print("{\"status\":-4}");//该道具已经存在于数据库中，不能重复添加
					out.flush();
					out.close();
					return;
				}

				System.out.print("upload succeed");

				//添加道具
				int res = GameToolsService.addTools(gt);
				if(res != -1) {
					//获取刚刚添加的道具ID
					GameTools gtool = GameToolsService.getByUnregisteredToolId(gameid, toolidInGame);
					if(gtool != null) {
						out.print("{\"status\":1,\"toolid\":"+gtool.getId()+"}");//上传成功，返回刚刚添加的ID
						out.flush();
						out.close();
						return;
					}
				}
				
			} catch (FileUploadException e) {
				// 处理文件尺寸过大异常
				e.printStackTrace();
			} catch (Exception e) {
				// 处理文件写入时的异常
				e.printStackTrace();
			}
			
			out.print("{\"status\":-5}");//上传失败
			out.flush();
			out.close();
			return;
		}

		response.sendRedirect("");

	}
	
	
	public static String getFilePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("/yyyyMM/dd/");
		return sdf.format(new java.util.Date());
	}


}
