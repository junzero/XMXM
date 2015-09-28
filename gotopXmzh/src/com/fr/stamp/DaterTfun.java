package com.fr.stamp;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.gotop.util.security.ForUtil;

public class DaterTfun{
	private static final long serialVersionUID = -4608178665974985715L;
	public BufferedImage createImage(String aStr,String fStr){
		int width = 103;
		int height = 103;
		// 创建BufferedImage对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.SCALE_DEFAULT);
		// 获取Graphics2D
		Graphics2D g2d = image.createGraphics();
		// ----------  增加下面的代码使得背景透明  -----------------
		image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d = image.createGraphics();
		// ----------  背景透明代码结束  -----------------
		// 画图
		g2d.setStroke(new BasicStroke(1));
		Seal seal = new Seal();
		seal.draw(g2d,aStr,fStr);
		//释放对象
		g2d.dispose();
		return image;
	}
	public Object run(Object[] arg0) {
		BufferedImage image = null;
		try{
			image = createImage(arg0[0].toString(),arg0[1].toString());
			ImageIO.write(image, "jpg", ForUtil.createFile("test.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(image==null){
			return "图片生成失败";
		}else{
			return image;
		}
	}
	public static void main(String[] arg) throws Exception{
		Object[] arg0 = {"福建莆田","水口01"};
		BufferedImage image = (BufferedImage)new DaterTfun().run(arg0);
		File file = ForUtil.createFile("test.jpg");
		ImageIO.write(image, "jpg", file);
	}
}
