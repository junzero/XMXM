package com.fr.stamp;

import java.io.*;
import java.awt.*;
import java.awt.image.*;

import com.gotop.util.security.ForUtil;
import com.sun.image.codec.jpeg.*;

public class Google {
	public static void main(String[] args) {
		BufferedImage bufferedImage = null;
		Graphics2D graphics2D = null;
		OutputStream outPutStream = null;
		int widths = 100;
		int heights = 100;
		try {
			bufferedImage = new BufferedImage(widths, heights,
					BufferedImage.TYPE_INT_RGB);
			graphics2D = (Graphics2D) bufferedImage.getGraphics();
			graphics2D.setBackground(java.awt.Color.black);
			graphics2D.setColor(java.awt.Color.green);
			Font font = new Font("", Font.PLAIN, 20);
			graphics2D.setFont(font);
			graphics2D.drawString("谷歌", 30, 50);
			font = new Font("", Font.PLAIN, 10);
			graphics2D.setFont(font);
			graphics2D.drawString("by 神灯", 60, 85);
			outPutStream = ForUtil.createFileOutputStream("c:\\google.jpg");
			JPEGImageEncoder encoder = JPEGCodec
					.createJPEGEncoder(outPutStream);
			encoder.encode(bufferedImage);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (graphics2D != null) {
				graphics2D.dispose();
			}
			if(outPutStream!=null){
				try {
					outPutStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}