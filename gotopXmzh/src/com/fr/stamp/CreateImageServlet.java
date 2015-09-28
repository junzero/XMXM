package com.fr.stamp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成单元格斜线图片
 * 
 */
public class CreateImageServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		String type = (String) request.getParameter("type");
		try {
			if (type.equals("1"))
				createImage1(request, response.getOutputStream());
			else if (type.equals("2"))
				createImage2(request, response.getOutputStream());
			else if (type.equals("3"))
				createImage3(request, response.getOutputStream());
			else
				System.out.println("Error slash type");
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	// topdown
	/**
	 * @param request
	 * @param out
	 * @throws Exception
	 */
	private void createImage1(HttpServletRequest request, OutputStream out)
			throws Exception {
		int width = new Integer(request.getParameter("width"));
		int height = new Integer(request.getParameter("height"));
		String borderweight = request.getParameter("borderweight") == null ? "0"
				: request.getParameter("borderweight");
		String bordercolor = request.getParameter("bordercolor");
		String backcolor = request.getParameter("backcolor");
		String forecolor = request.getParameter("forecolor");
		String str1 = request.getParameter("str1");
		String str2 = request.getParameter("str2");
		String fontname = request.getParameter("fontname");
		int fontsize = new Integer(request.getParameter("fontsize")) * 96 / 72;
		boolean isBold = new Boolean(request.getParameter("isBold"));
		boolean isItalic = new Boolean(request.getParameter("isItalic"));
		boolean isUnderline = new Boolean(request.getParameter("isUnderline"));
		boolean isStrikeThrough = new Boolean(request
				.getParameter("isStrikeThrough"));
		int style = 0;
		if (!(isBold && isItalic))
			style = Font.PLAIN;
		else {
			if (isItalic)
				style = Font.ITALIC;
			if (isBold)
				style += Font.BOLD;
		}

		BufferedImage bi = new BufferedImage(width - 1, height - 1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		// set background:
		g.setBackground(new Color(Integer.parseInt(backcolor, 16)));
		// g.clearRect(borderweight, borderweight, width-borderweight*2,
		// height-borderweight*2);
		g.clearRect(0, 0, width - 1, height - 1);
		// set border color:
		g.setColor(new Color(Integer.parseInt(bordercolor, 16)));

		// 2009.03.25 设置线条粗细
		float linewidth = Float.parseFloat(borderweight);
		BasicStroke bs = new BasicStroke(linewidth);
		g.setStroke(bs);

		// start draw:
		g.drawLine(0, 0, width - 1, height - 1);
		Font font = new Font(fontname, style, fontsize);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int ascent2 = fm.getMaxAscent();
		int width2 = fm.stringWidth(str2);
		int height1 = fm.getHeight();

		// g.drawString(str2, width-2-width2,ascent2);
		g.drawString(str2, width - width2, ascent2);
		// g.drawString(str1, 0,height-2);
		g.drawString(str1, 0, height - fm.getMaxDescent());
		// end draw:
		g.dispose();
		bi.flush();
		// encode:
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// aggregation
	/**
	 * @param request
	 * @param out
	 */
	private void createImage2(HttpServletRequest request, OutputStream out) {
		int width = new Integer(request.getParameter("width"));
		int height = new Integer(request.getParameter("height"));
		String borderweight = request.getParameter("borderweight") == null ? "0"
				: request.getParameter("borderweight");
		String bordercolor = request.getParameter("bordercolor");
		String backcolor = request.getParameter("backcolor");
		String forecolor = request.getParameter("forecolor");
		String str1 = request.getParameter("str1");
		String str2 = request.getParameter("str2");
		String str3 = request.getParameter("str3");
		String fontname = request.getParameter("fontname");
		int fontsize = new Integer(request.getParameter("fontsize")) * 96 / 72;
		boolean isBold = new Boolean(request.getParameter("isBold"));
		boolean isItalic = new Boolean(request.getParameter("isItalic"));
		boolean isUnderline = new Boolean(request.getParameter("isUnderline"));
		boolean isStrikeThrough = new Boolean(request
				.getParameter("isStrikeThrough"));
		int style = 0;
		if (!(isBold && isItalic))
			style = Font.PLAIN;
		else {
			if (isItalic)
				style = Font.ITALIC;
			if (isBold)
				style += Font.BOLD;
		}
		BufferedImage bi = new BufferedImage(width - 2, height - 2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();

		// set background:
		g.setBackground(new Color(Integer.parseInt(backcolor, 16)));
		g.clearRect(0, 0, width, height);

		// set border color:
		g.setColor(new Color(Integer.parseInt(bordercolor, 16)));

		// 2009.03.25 设置线条粗细
		float linewidth = Float.parseFloat(borderweight);
		BasicStroke bs = new BasicStroke(linewidth);
		g.setStroke(bs);

		// start draw:
		g.drawLine(width / 2, 1, width - 1, height - 1);
		g.drawLine(1, height / 2, width - 1, height - 1);

		Font font = new Font(fontname, style, fontsize);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int ascent = fm.getMaxAscent();
		int width1 = fm.stringWidth(str1);
		int width2 = fm.stringWidth(str2);
		int width3 = fm.stringWidth(str3);
		g.drawString(str1, 0, height);
		g.drawString(str2, 0, ascent);
		g.drawString(str3, width - width3, ascent);
		// end draw:
		g.dispose();
		bi.flush();
		// encode:
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// radiation
	/**
	 * @param request
	 * @param out
	 */
	private void createImage3(HttpServletRequest request, OutputStream out) {
		int width = new Integer(request.getParameter("width"));
		int height = new Integer(request.getParameter("height"));
		String borderweight = request.getParameter("borderweight") == null ? "0"
				: request.getParameter("borderweight");
		String bordercolor = request.getParameter("bordercolor");
		String backcolor = request.getParameter("backcolor");
		String forecolor = request.getParameter("forecolor");
		String str1 = request.getParameter("str1");
		String str2 = request.getParameter("str2");
		String str3 = request.getParameter("str3");
		String fontname = request.getParameter("fontname");
		int fontsize = new Integer(request.getParameter("fontsize")) * 96 / 72;
		boolean isBold = new Boolean(request.getParameter("isBold"));
		boolean isItalic = new Boolean(request.getParameter("isItalic"));
		boolean isUnderline = new Boolean(request.getParameter("isUnderline"));
		boolean isStrikeThrough = new Boolean(request
				.getParameter("isStrikeThrough"));
		int style = 0;
		if (!(isBold && isItalic))
			style = Font.PLAIN;
		else {
			if (isItalic)
				style = Font.ITALIC;
			if (isBold)
				style += Font.BOLD;
		}

		BufferedImage bi = new BufferedImage(width - 1, height - 1,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		// set background:
		g.setBackground(new Color(Integer.parseInt(backcolor, 16)));
		g.clearRect(0, 0, width - 1, height - 1);
		// set border color:
		g.setColor(new Color(Integer.parseInt(bordercolor, 16)));

		// 2009.03.25 设置线条粗细
		float linewidth = Float.parseFloat(borderweight);
		BasicStroke bs = new BasicStroke(linewidth);
		g.setStroke(bs);

		// start draw:
		g.drawLine(0, 0, width - 1, height / 2 - 1);
		g.drawLine(0, 0, width / 2 - 1, height - 1);
		Font font = new Font(fontname, style, fontsize);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics(font);
		int ascent = fm.getMaxAscent();
		int width1 = fm.stringWidth(str1);
		int width2 = fm.stringWidth(str2);
		int width3 = fm.stringWidth(str3);
		g.drawString(str1, 0, height - 2);
		g.drawString(str2, width - 2 - width2, height - 2);
		g.drawString(str3, width - 2 - width3, ascent);
		// end draw:
		g.dispose();
		bi.flush();
		// encode:
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
		param.setQuality(1.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(bi);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
