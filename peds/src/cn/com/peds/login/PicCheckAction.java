package cn.com.peds.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PicCheckAction {

	public PicCheckAction() {
		// TODO Auto-generated constructor stub
	}

	public void perform(HttpServlet servlet, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// 设置页面不缓存
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		String s = new String();

		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);

		// 取随机产生的认证码(4位数字)
		Random random1 = new Random();
		String sRand = "";
		String rand = "";
		for (int i = 0; i < 4; i++) {
			rand = String.valueOf(random1.nextInt(10));
			if (i == 0 && rand.equals("0")) {
				rand = "1";
			}
			sRand += rand;
		}
		// 将认证码存入SESSION
		req.getSession().setAttribute("session_checkPic", sRand);

		// 将认证码显示到图象中
		g.setColor(Color.black);
		Integer tempNumber = new Integer(sRand);
		String numberStr = tempNumber.toString();

		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		String Str = numberStr.substring(0, 1);
		g.drawString(Str, 8, 17);

		Str = numberStr.substring(1, 2);
		g.drawString(Str, 20, 15);
		Str = numberStr.substring(2, 3);
		g.drawString(Str, 35, 18);

		Str = numberStr.substring(3, 4);
		g.drawString(Str, 45, 15);

		// 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}

		// 图象生效
		g.dispose();

		// 输出图象到页面
		ImageIO.write(image, "JPEG", res.getOutputStream());
	}
}
