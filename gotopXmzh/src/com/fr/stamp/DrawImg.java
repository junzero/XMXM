package com.fr.stamp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

/**
 * 创建日期:2006-5-16 Title:文件所属模块（台帐、查询统计、权限、运行管理、报表）
 * Description：对本文件的详细描述，原则上不能少于50字
 * 
 * @author shaokun305
 * @mender：（文件的修改者，文件创建者之外的人）
 * @version 1.0 Remark：认为有必要的其他信息
 */

public class DrawImg {

	/**
	 * 字母标注集合。
	 */
	public static final String[] LETTER = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L" };

	/**
	 * 
	 * 功能:根据索引获得随机规定的颜色Color。 作者: shaokun305 创建日期:2006-5-16
	 * 
	 * @param index
	 * @return
	 */
	public static Color getColor(int index) {
		switch (index) {
		case 0:
			return Color.YELLOW;
		case 1:
			return Color.GREEN;
		case 2:
			return Color.RED;
		case 3:
			return Color.BLACK;
		default:
			return Color.BLACK;

		}
	}

	/**
	 * 功能: 作者: shaokun305 创建日期:2006-5-16
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根

	}

	/**
	 * 画一组图形元素和字母标注 功能: 作者: shaokun305 创建日期:2006-5-16
	 * 
	 * @param g
	 * @param rooundlist
	 */
	public void drawRounds(Graphics g, List rooundlist) {
		Color color = g.getColor();
		Font font = g.getFont();

		if (rooundlist != null) {
			for (int i = 0; i < rooundlist.size(); i++) {
				RoundData roundData = (RoundData) rooundlist.get(i);
				drawRound(g, roundData, i);
			}
		}
		// 还原初始化的数据。
		g.setColor(color);
		g.setFont(font);
	}

	/**
	 * 
	 * 功能: 画一个图形和对应的字母标注 作者: shaokun305 创建日期:2006-5-16
	 * 
	 * @param g
	 * @param roundData
	 * @param color
	 */
	private void drawRound(Graphics g, RoundData rd, int index) {
		g.setColor(getColor(index));
		g.drawArc(rd.getPointX(), rd.getPointX(), rd.getRadii(), rd.getRadii(),
				0, 360);
		g.fillArc(rd.getPointX(), rd.getPointX(), rd.getRadii(), rd.getRadii(),
				0, 360);

		g.setColor(Color.BLACK);
		g.drawString(rd.getLetter(), rd.getPointX() - 10, rd.getPointX() + 10);

	}
	
	private void initFont(Graphics g) {
		String fontColor = ""; // 字体颜色
		int fontSize = 14; // 字体大小
		String fontStyle = "bold"; // 字体风格(斜体,粗体等)
		String fontName = "宋体"; // 字体名称
		g.setColor(Color.BLUE);
		Font mFont = new Font(fontName, Font.PLAIN, fontSize);// 默认字体
		if (fontStyle.equalsIgnoreCase("italic"))
			mFont = new Font(fontName, Font.ITALIC, fontSize);
		if (fontStyle.equalsIgnoreCase("bold"))
			mFont = new Font(fontName, Font.BOLD, fontSize);
		if (fontStyle.equalsIgnoreCase("plain"))
			mFont = new Font(fontName, Font.PLAIN, fontSize);
		// System.out.println("字体大小：＝" + mFont.getSize());
		g.setFont(mFont);
	}
}
class RoundData {

	public int getPointX() {
		return 0;
	}
	public int getPointY() {
		return 0;
	}

	public int getRadii() {
		return 0;
	}

	public String getLetter() {
		return null;
	}

}
