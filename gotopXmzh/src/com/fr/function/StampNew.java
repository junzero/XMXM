package com.fr.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gotop.util.security.ForUtil;

public class StampNew {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new StampNew().newStyle();
	}

	public void newStyle() {
		// TODO Auto-generated method stub
		BufferedImage image = new BufferedImage(200, 200,
				BufferedImage.SCALE_DEFAULT);
		Graphics2D g2 = image.createGraphics();

		image = g2.getDeviceConfiguration().createCompatibleImage(200, 200,
				Transparency.TRANSLUCENT);
		g2.dispose();
		g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int totop = 5;
		int toleft = 32;

		int d_outter = 136;
		int circleLine = 2;
		int d_inner = d_outter - 2 * circleLine;

		double pi = Math.PI;
		System.out.println(2 * pi * 300 / 360);

		g2.setColor(Color.red);
		g2.fillArc(toleft, totop, d_outter, d_outter, 0, 360);

		g2.setColor(Color.white);
		g2.fillArc(toleft + circleLine, totop + circleLine, d_inner, d_inner,
				0, 360);
		String Name = "容嬷嬷";
		String title = "皇后奶妈";
		String deptName = "后宫秘书部";

		Font font = new Font(null, Font.CENTER_BASELINE, 20);
		Font f_name = font;
		Font f_title = font;
		FontRenderContext context = g2.getFontRenderContext();
		// 初始化
		Rectangle2D bounds_name = font.getStringBounds(Name, context);
		Rectangle2D bounds_title = font.getStringBounds(title, context);

		Paint arcpaint3 = Color.red;
		g2.setPaint(arcpaint3);

		// 字符过长时，按比例缩小字号
		if (bounds_name.getWidth() > 117) {
			f_name = new Font("Serif", Font.BOLD,
					(int) ((float) 20 * 117 / bounds_name.getWidth()));
			bounds_name = f_name.getStringBounds(Name, context);
		}
		if (bounds_title.getWidth() > 118) {
			f_title = new Font("Serif", Font.BOLD,
					(int) ((float) 20 * 118 / bounds_title.getWidth()));
			bounds_title = f_title.getStringBounds(title, context);
		}
		g2.setFont(f_name);
		g2.drawString(Name, 100 - (float) (bounds_name.getWidth() / 2),
				(float) 60);
		g2.setFont(f_title);

		// 中心旋转
		g2.drawString(title, 100 - (float) (bounds_title.getWidth() / 2),
				(float) 90);

		Font font_desc = new Font(null, Font.CENTER_BASELINE, 20);

		// 部门信息支持缩放
		Font f_dept = font_desc;
		// 写入部门
		g2.setFont(f_dept);

		Paint arcpaint4 = Color.red;
		g2.setPaint(arcpaint4);
		g2.setFont(font_desc);

		g2.drawString("2011-09-19", 50, 170);

		// 测试弧形文字,这里只针对奇数位长度的字符串做举例，且没有关于字符串过长的处理方案
		Font font_dept1 = new Font(null, Font.CENTER_BASELINE, 15);
		g2.setFont(font_dept1);
		// 转移中心点
		g2.translate(100, 73);

		String strmiddle = deptName.substring((deptName.length()) / 2,
				(deptName.length()) / 2 + 1);
		String strbefore = deptName.substring(0, (deptName.length()) / 2);
		String strafter = deptName.substring((deptName.length()) / 2 + 1,
				deptName.length());
		System.out.println(strmiddle);
		System.out.println(strbefore);
		System.out.println(strafter);
		// 每次偏移的弧度
		float rad_per = (float) pi * 30 / 180;

		g2.drawString(strmiddle, (float) 0, -50);

		String[] arrafter = strafter.split("");
		// 回退弧度
		float rad_back = rad_per * arrafter.length;
		// 先画右半边
		for (int i = 0; i < arrafter.length; i++) {
			String depti = arrafter[i];
			g2.drawString(depti, (float) 0, -50);
			g2.rotate(rad_per);
		}
		// 回退
		g2.rotate(-rad_back);
		// 画左半边
		g2.rotate(-rad_per);
		String[] arrbefore = strbefore.split("");
		for (int i = arrbefore.length - 1; i >= 0; i--) {
			String depti = arrbefore[i];
			g2.drawString(depti, (float) 0, -50);
			g2.rotate(-rad_per);
		}
		try {
			ImageIO.write(image, "jpg", ForUtil.createFile("c:/stamp.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}