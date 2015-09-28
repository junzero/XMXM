package com.fr.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.gotop.util.security.ForUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TestMark {
	private static int wid = 0;
	private static int het = 0;

	/**
	 * 添加水印, filePath 源图片路径 含图片名， watermark 水印图片路径 savePath 为你添加水印后的图片保存路径文件夹
	 * words 要添加的文字
	 */
	public static boolean createMark(String filePath, String watermark, String words, String savePath) {
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		ImageIcon waterIcon = new ImageIcon(watermark);
		Image waterImg = waterIcon.getImage();
		FileOutputStream out = null;
		File f = null;
		// /////////////////////////////////////////////////////////////////////
		f = ForUtil.createFile(filePath);
		String picname = f.getName();// 取得图片名
		if (watermark != null && !watermark.equals("")) {// 当水印图标为空时
			ImageIcon markIcon = new ImageIcon(watermark); // 要添加的水印图标
			Image markImg = markIcon.getImage();
			wid = markImg.getWidth(null); // 水印图标宽度
			het = markImg.getHeight(null); // 水印图标高度
		}
		// ////////////////////////////////////////////////////////////////////

		int width = theImg.getWidth(null); // 源图片宽度
		int height = theImg.getHeight(null); // 源图片高度
		if (savePath.equals(""))
			savePath = filePath;// 如果未指定保存路径则保存回原路径
		else
			savePath = savePath + "\\" + picname;// 指定保存文件夹时,拼接出保存路径

		BufferedImage bimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = bimage.createGraphics();
		g.setColor(Color.black); // 设置颜色
		g.setBackground(Color.white);
		Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 15);
	    g.setFont(myFont);
		g.drawImage(theImg, 0, 0, null);
		g.drawImage(waterImg, width - wid + 5, height - het + 5, null); // 添加图标中间两个数字参数
																		// 是设定位置
		g.drawString(words, width - 78, height - 45); // 添加文字
		
		try {
			out = ForUtil.createFileOutputStream(savePath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(50f, true); // 图片质量
			encoder.encode(bimage, param);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("===========水印失败");
			return false;
		} finally {
//			System.gc();// 清理 垃圾对象
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("===========水印成功");
		return true;
	}

	// /测试主程序
	public static void main(String[] args) {
		createMark("j:/dddddd.jpg", "j:/8300090105.gif", "2011-10-30", "d:/");

	}
}
