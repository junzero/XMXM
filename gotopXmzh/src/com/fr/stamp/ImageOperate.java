package com.fr.stamp;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang.StringUtils;

import com.gotop.util.security.ForUtil;

//修改CATALINA.SH文件，我把原JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=GBK"改为JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=GBK;-Djava.awt.headless=true"即可解决这个问题，我甚至还在程序中加入了System.setProperty("java.awt.headless", "true");，但是不知道为什么压缩速度没windows下快。
public class ImageOperate {
	/**
	 * 旋转图片为指定角度
	 * 
	 * @param bufferedimage
	 *            目标图像
	 * @param degree
	 *            旋转角度
	 * @return
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage,
			final int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
	}

	/**
	 * 变更图像为指定大小
	 * 
	 * @param bufferedimage
	 *            目标图像
	 * @param w
	 *            宽
	 * @param h
	 *            高
	 * @return
	 */
	public static BufferedImage resizeImage(final BufferedImage bufferedimage,
			final int w, final int h) {
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.drawImage(bufferedimage, 0, 0, w, h, 0, 0, bufferedimage
				.getWidth(), bufferedimage.getHeight(), null);
		graphics2d.dispose();
		return img;
	}

	/**
	 * 水平翻转图像
	 * 
	 * @param bufferedimage
	 *            目标图像
	 * @return
	 */
	public static BufferedImage flipImage(final BufferedImage bufferedimage) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		BufferedImage img;
		Graphics2D graphics2d;
		(graphics2d = (img = new BufferedImage(w, h, bufferedimage
				.getColorModel().getTransparency())).createGraphics())
				.drawImage(bufferedimage, 0, 0, w, h, w, 0, 0, h, null);
		graphics2d.dispose();
		return img;
	}

	public static void main(String[] args) {
		if (compressPic("D:/Program Files/myftp/1.jpg", "D:/Program Files/myftp/ssss.jpg")) {
			System.out.println("压缩成功！");
		} else {
			System.out.println("压缩失败！");
		}
	}

	public static boolean compressPic(String srcFilePath, String descFilePath) {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.1);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
				colorModel, colorModel.createCompatibleSampleModel(16, 16)));
		try {
			if (StringUtils.isBlank(srcFilePath)) {
				return false;
			} else {
				file = ForUtil.createFile(srcFilePath);
				src = ImageIO.read(file);
				out = ForUtil.createFileOutputStream(descFilePath);
				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	public static boolean compressPic2(String srcFilePath, String descFilePath,byte[] data) {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        
        BufferedImage src = null;
        ByteArrayOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;

        // 指定写图片的方式为 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality((float)0.1/data.length);
                         
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();
        // 指定压缩时使用的色彩模式
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel
                .createCompatibleSampleModel(16, 16)));

        try
        {
            src = ImageIO.read(is);
            out = new ByteArrayOutputStream(data.length);

            imgWrier.reset();
            // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 调用write方法，就可以向输入流写图片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            
            out.flush();
            out.close();
            is.close();
            data = out.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
	}
}