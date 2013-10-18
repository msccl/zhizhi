package com.qkzz.web.developer.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qkzz.util.CookieUtil;
import com.qkzz.web.developer.service.ValidateEntryService;


@Controller
public class ValidateAction {

	private char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//
//	// 给定范围获得随机颜色 
//	Color getRandColor(int fc, int bc) {
//		Random random = new Random();
//		if (fc > 255)
//			fc = 255;
//		if (bc > 255)
//			bc = 255;
//		int r = fc + random.nextInt(bc - fc);
//		int g = fc + random.nextInt(bc - fc);
//		int b = fc + random.nextInt(bc - fc);
//		return new Color(r, g, b);
//	}

	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public void validate(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 设定宽度和高度
		int width = 100;
		int height = 30;
		// 在内存中创建图象
		BufferedImage bi = new BufferedImage(width, height,	BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g =  bi.getGraphics();
		// 画边框
		java.util.Random random = new java.util.Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		// 设置字体颜色
		g.setColor(Color.BLACK);
		// 画认证码,每个认证码在不同的水平位置
		String[] ziti ={"Arial", "simsun"};
		int fontSize[] = {17,20,23};
		int fontStyle[] = {Font.ITALIC, Font.PLAIN};

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(codeSequence[random.nextInt(10)]);;
			int w = 0;
			int x = (i + 1) % 3;
			sRand += rand;
			// 随即生成验证码字符的水平偏移量
			if (x == random.nextInt(3)) {
				w = 20 - random.nextInt(4);
			} else {
				w = 20 + random.nextInt(4);
			}
			
			// 设置字体(字体,是否加粗,字号)
			Font mFont = new Font(ziti[random.nextInt(2)], fontStyle[random.nextInt(2)], fontSize[random.nextInt(3)]);
			g.setFont(mFont);

			// 随即生成颜色
			Color color1 = new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180));
			g.setColor(color1);
			
			g.drawString(rand, 20 * i + 10, w);
		}

		// 将认证码存入SESSION
		HttpSession session = request.getSession();
		session.setAttribute("validateCode", sRand);

		// 画干扰线
		for (int i = 0; i < 7; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			// 随即画各种颜色的线
			Color color1 = new Color(random.nextInt(180), random.nextInt(180),random.nextInt(180));
			g.setColor(color1); 
			g.drawLine(x, y, x1, y1);
		}
		// 图像生效
		g.dispose();
		// 输出页面
		ImageIO.write(bi, "GIF", response.getOutputStream());
	}


	@RequestMapping(value = "/validate-{key}", method = RequestMethod.GET)
	public void showValidate(@PathVariable("key") String key,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 设定宽度和高度
		int width = 100;
		int height = 30;
		// 在内存中创建图象
		BufferedImage bi = new BufferedImage(width, height,	BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g =  bi.getGraphics();
		// 画边框
		java.util.Random random = new java.util.Random();
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		// 设置字体颜色
		g.setColor(Color.BLACK);
		// 画认证码,每个认证码在不同的水平位置
		String[] ziti ={"Arial", "simsun"};
		int fontSize[] = {17,20,23};
		int fontStyle[] = {Font.ITALIC, Font.PLAIN};

		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(codeSequence[random.nextInt(10)]);;
			int w = 0;
			int x = (i + 1) % 3;
			sRand += rand;
			// 随即生成验证码字符的水平偏移量
			if (x == random.nextInt(3)) {
				w = 20 - random.nextInt(4);
			} else {
				w = 20 + random.nextInt(4);
			}
			
			// 设置字体(字体,是否加粗,字号)
			Font mFont = new Font(ziti[random.nextInt(2)], fontStyle[random.nextInt(2)], fontSize[random.nextInt(3)]);
			g.setFont(mFont);

			// 随即生成颜色
			Color color1 = new Color(random.nextInt(180), random.nextInt(180), random.nextInt(180));
			g.setColor(color1);
			
			g.drawString(rand, 20 * i + 10, w);
		}

		// 将认证码存入SESSION
		//HttpSession session = request.getSession();
		//session.setAttribute("validateCode", sRand);
		// 删除数据库中过时的验证码
		ValidateEntryService.deleteTimeOut();
		// 将认证码存入数据库
		ValidateEntryService.save(key, sRand);

		// 画干扰线
		for (int i = 0; i < 7; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			// 随即画各种颜色的线
			Color color1 = new Color(random.nextInt(180), random.nextInt(180),random.nextInt(180));
			g.setColor(color1); 
			g.drawLine(x, y, x1, y1);
		}
		// 图像生效
		g.dispose();
		// 输出页面
		ImageIO.write(bi, "GIF", response.getOutputStream());
	}

	@RequestMapping(value = "/user/valientry", method = RequestMethod.GET)
	public String login(@RequestParam(value = "url", required = false) String url,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		CookieUtil.getCookieUser(request,response);
		
		request.setAttribute("pageTitle", "测试图片验证码");
		
		return "user/valientry";
	}
	
	@RequestMapping(value = "/user/valientry", method = RequestMethod.POST)
	public String processSubmit(@RequestParam(value = "valicode", required = false) String valicode,
			HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.getCookieUser(request,response);
		
		String key = request.getParameter("userid");
		
		System.out.println("----------test begin :"+ valicode);
		
		request.setAttribute("pageTitle", "用户登录");
		
		if(!StringUtils.hasText(valicode)){
			request.setAttribute("message", "验证码为空..");
			return "user/valientry";
		}
		if(!StringUtils.hasText(key)){
			request.setAttribute("message", "验证码入口为空..");
			return "user/valientry";
		}
		String code = ValidateEntryService.getValue(key);
		if(!StringUtils.hasText(code)){
			request.setAttribute("message", "服务器无验证码.");
			return "user/valientry";
		}
		if(!valicode.equals(code)){
			request.setAttribute("message", "验证码输入错误.");
			return "user/valientry";
		}
		request.setAttribute("message", "恭喜你中头彩了.");
		return "user/valientry";
	}
	
}
