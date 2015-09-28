package com.fr.stamp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * 印章类.保存印章必须的参数和绘制方法.
 * 
 * @author i2534
 * 
 */
public class Seal {
	/**
	 * 印章名称距中心点偏移量,按照y轴方向
	 */
	private int nameOffset = 5;
	/**
	 * 印章宽度
	 */
	private int width = 102;
	/**
	 * 印章高度
	 */
	private int height = 102;
	/**
	 * 印章所属单位的起始角度,以6点钟方向为中心,向两个方向平均扩展
	 */
	private float firmAngle = 340;//6 -240    2--320
	/**
	 * 印章名称
	 */
	private String name = "时间";
	/**
	 * 印章名称颜色
	 */
	private Color nameColor = Color.BLACK;
	/**
	 * 印章所属单位
	 */
	private String firm = "福州";
	/**
	 * 印章所属单位颜色
	 */
	private Color firmColor = Color.BLACK;
	/**
	 * 
	 */
	private Font nameFont = new Font("宋体", Font.BOLD, 12);
	/**
	 * 印章所属单位字体信息
	 */
	private Font firmFont = new Font("宋体", Font.BOLD, 15);
	/**
	 * 单位字体的宽度缩放比率(百分比).此参数可以使字体看起来瘦长
	 */
	private float firmScale = 1.0F;
	/**
	 * 边框线宽
	 */
	private float borderWidth = 2F;
	/**
	 * 边框颜色
	 */
	private Color borderColor = Color.BLACK;

	public void draw(Graphics2D g2d,String aStr,String fStr) {
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.hh");
		name = formatter.format(date); 
		
		// 把绘制起点挪到圆中心点
		g2d.translate(width / 2, height / 2);

		Stroke stroke = g2d.getStroke();// 旧的线性
		
		// 绘制印章边框
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.drawOval(-width / 2, -height / 2, width, height);
		g2d.setStroke(stroke);

		// 绘制印章名称
		g2d.setFont(nameFont);
		g2d.setColor(nameColor);
		FontMetrics fm = g2d.getFontMetrics();
		int w = fm.stringWidth(name);// 名称宽度
		int h = fm.getHeight();// 名称高度
		int y = fm.getAscent() - h / 2;// 求得中心线经过字体的高度的一半时的字体的起绘点
		g2d.drawString(name, -w / 2, y + nameOffset);
		
		createAboveText(g2d,aStr,fm);
		createFollowingText(g2d,fStr,fm);
	}
	/**
	 *  省地市
	 * @param g2d
	 * @return
	 */
	private void createAboveText(Graphics2D g2d,String text,FontMetrics fm){
		if(text==null || "".equals(text)){
			text = "  ";
		}
		char[] chars = text.toCharArray();
		int cwi = fm.charWidth(chars[0]);// 此字符宽度
		float firmAngleTemp = firmAngle - text.length()*cwi;
		// 绘制印章单位
		g2d.setFont(firmFont);
		g2d.setColor(firmColor);
		int h = fm.getHeight();// 字高度

		int count = text.length();// 字数
		int r = width / 2;// 半径,就假设此印章是个矩形,方便计算

		float angle = (360 - firmAngleTemp) / (count - 1);// 字间角度
		float start = 90 + firmAngleTemp / 2;// 以x轴正向为0,顺时针旋转
		double vr = Math.toRadians(90);// 垂直旋转弧度
		
		for (int i = 0; i < count; i++) {
			char c = chars[i];// 需要绘制的字符
			int cw = fm.charWidth(c);// 此字符宽度
			float a = start + angle * i;// 现在角度
			double radians = Math.toRadians(a);
			g2d.rotate(radians);// 旋转坐标系,让要绘制的字符处于x正轴
			float x = r - h;// 绘制字符的x坐标为半径减去字高度
			g2d.translate(x, 0);// 移动到此位置,此时字和x轴垂直
			g2d.rotate(vr);// 旋转90度,让字平行于x轴
			g2d.scale(firmScale, 1);// 缩放字体宽度
			g2d.drawString(String.valueOf(c), -cw / 2, 0);// 此点为字的中心点
			// 将所有设置还原,等待绘制下一个
			g2d.scale(1 / firmScale, 1);
			g2d.rotate(-vr);
			g2d.translate(-x, 0);
			g2d.rotate(-radians);
		}
	}
	/**
	 * 县街道
	 * @param g2d
	 * @return
	 */
	private void createFollowingText(Graphics2D g2d,String text,FontMetrics fm){
		if(text==null || "".equals(text)){
			text = "  ";
		}
		char[] chars = text.toCharArray();
		int cwi = fm.charWidth(chars[0]);// 此字符宽度
		float firmAngleTemp = firmAngle - text.length()*cwi;
		// 绘制印章单位
		g2d.setFont(firmFont);
		g2d.setColor(firmColor);
		fm = g2d.getFontMetrics();
		int h = fm.getHeight();// 字高度
		int count = text.length();// 字数
		int r = width / 2;// 半径,就假设此印章是个矩形,方便计算

		float angle = (360 - firmAngleTemp) / (count - 1);// 字间角度
		float start = 90 + firmAngleTemp / 2;// 以x轴正向为0,顺时针旋转
		double vr = Math.toRadians(360-90);// 垂直旋转弧度
		for (int i = 0; i < count; i++) {
			char c = chars[i];// 需要绘制的字符
			int cw = fm.charWidth(c);// 此字符宽度
			float a = start + angle * i;// 现在角度

			double radians = Math.toRadians(-a);
			g2d.rotate(radians);// 旋转坐标系,让要绘制的字符处于x正轴
			float x = r - h/2+6;// 绘制字符的x坐标为半径减去字高度
			g2d.translate(x, 0);// 移动到此位置,此时字和x轴垂直
			g2d.rotate(vr);// 旋转90度,让字平行于x轴
			g2d.scale(firmScale, 1);// 缩放字体宽度
			g2d.drawString(String.valueOf(c), -cw / 2, 0);// 此点为字的中心点
			// 将所有设置还原,等待绘制下一个
			g2d.scale(1 / firmScale, 1);
			g2d.rotate(-vr);
			g2d.translate(-x, 0);
			g2d.rotate(-radians);
		}
	}
	/**
	 * 导出此印章为透明背景的图片字节数组.
	 * 
	 * @param format
	 *            图片类型,如果为null,则默认为png
	 * @return 数组
	 * @throws IOException
	 *             写出图像数据出现问题
	 */
	public byte[] export2pic(String format,String aStr,String fStr) throws IOException {
		int fix = 5;// 宽高修正,如果宽高就为图片宽高,可能边框线被切割
		BufferedImage bi = new BufferedImage(getWidth() + fix * 2, getHeight() + fix * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.translate(fix, fix);
		this.draw(g2d,aStr,fStr);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, format == null ? "png" : format, baos);
		return baos.toByteArray();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
