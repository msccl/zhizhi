package com.qkzz.game.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

public class TestUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String uploadImgPath = "d:/data/img/";
	private static final String uploadTmpPath = "d:/data/tmp/";

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");// 设置字符编码为UTF-8, 统一编码，处理出现乱码问题

		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("file1"));

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);// 检查是否是一个文件上传请求
		if (isMultipart) {

			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置上传文件时用于临时存放文件的内存大小,这里是2K.多于的部分将临时存在硬盘
			factory.setSizeThreshold(1024 * 2);
			// 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录
			factory.setRepository(new File(uploadTmpPath));// 临时文件

			// 以上两项可通过DiskFileItemFactory构参来创建
			// DiskFileItemFactory factory = new
			// DiskFileItemFactory(yourMaxMemorySize, yourTempDirectory);

			// 用上面的工厂实例化上传组件，
			ServletFileUpload upload = new ServletFileUpload(factory);
			// upload.setFileItemFactory(factory);
			// 如果ServletFileUpload实例中不设置上面的Factory则报java.lang.NullPointerException:
			// No FileItemFactory has been set.

			// 设置最大上传大小 10M
			upload.setSizeMax(1024 * 1024 * 50);

			try {
				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {// 依次处理每个文件
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();// 获得文件名，包括路径
					System.out.println(item.getName());
					String fieldName = item.getFieldName();
					if(fieldName.equals("d5power")){
						String fieldValue = new String(item.getString().getBytes("ISO-8859-1"),"utf-8");
						System.out.println("fieldName:"+fieldName);
						System.out.println("fieldValue:"+fieldValue);
					}
					if (fileName != null) {
						File fullFile = new File(item.getName());
						File savedFile = new File(uploadImgPath,
								fullFile.getName());
						item.write(savedFile);
					}
				}

				System.out.print("upload succeed");
				
				out.print("{\"status\":1}");//上传成功
				out.flush();
				out.close();
				return;
				
			} catch (FileUploadException e) {
				// 处理文件尺寸过大异常
				e.printStackTrace();
			} catch (Exception e) {
				// 处理文件写入时的异常
				e.printStackTrace();
			}
			
			out.print("{\"status\":0}");//上传失败
			out.flush();
			out.close();
			return;
		}

		response.sendRedirect("");

	}

}
