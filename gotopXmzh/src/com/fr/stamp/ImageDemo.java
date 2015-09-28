package com.fr.stamp;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.image.*;
import java.awt.*;
import java.awt.geom.*;

public class ImageDemo extends JFrame {

	public ImageDemo() {
		setTitle("Image");
		setSize(600, 300);
		setLocation(200, 100);

		MyPanel mp = new MyPanel();
		add(mp);
	}

	public static void main(String[] args) {
		// TODO 自动生成方法存根
		ImageDemo id = new ImageDemo();
		id.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		id.setVisible(true);
	}

}

class MyPanel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D;

		BufferedImage buffImage = new BufferedImage(200, 200, BufferedImage.TYPE_3BYTE_BGR);//你用TYPE_INT_ARGB就可以了,其中的A是指alpha的意思,在图形学中alpha表示像素不透明度的程度,alpha=0.0时表示完全透明,alpha=1.0表示完全不透明.		但为了节省空间,某些图象数据类型不支持alpha属性(图形学中叫alpha通道).



		g2D = buffImage.createGraphics();
		int width = buffImage.getWidth();
		int height = buffImage.getHeight();
//		 for(int i = 0; i < width; i++)
//			 for(int j = 0; j < height; j++)
//			 buffImage.setRGB(i, j, 0x000000);
//			 g2D.setBackground(Color.blue);
//			 g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
//			 Rectangle2D.Double rect1 = new Rectangle2D.Double(0,0,width,height);
//			 g2D.fill(rect1);
//			 g2D.clearRect(0, 0, width, height);
//			 g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 1.0f));
//			 g2D.fill(rect1);
		Rectangle2D.Float rect = new Rectangle2D.Float(50, 50, 50, 50);
		g2D.setPaint(Color.red);
		g2D.draw(rect);
		g2D.fill(rect);
		g.drawImage(buffImage, 50, 50, null);
	}
}
