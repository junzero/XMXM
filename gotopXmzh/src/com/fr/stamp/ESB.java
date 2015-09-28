package com.fr.stamp;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import com.gotop.util.security.ForUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ESB {
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
			graphics2D.drawString("ESB", 30, 50);

			font = new Font("", Font.PLAIN, 10);
			graphics2D.setFont(font);
			graphics2D.drawString("by 单量", 60, 85);
		    outPutStream = ForUtil.createFileOutputStream("c:\\ESB.jpg");
			JPEGImageEncoder encoder = JPEGCodec
					.createJPEGEncoder(outPutStream);
			encoder.encode(bufferedImage);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			if (graphics2D != null) {
				graphics2D.dispose();
			}
			if (outPutStream!=null) {
				try {
					outPutStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
