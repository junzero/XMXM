package com.fr.stamp;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import org.apache.log4j.Logger;

import com.gotop.util.security.ForUtil;

//这个类重写了setCompressionQuality方法，因为在压缩JPEG图片的时候可能会出现问题   
public class MyImageWriteParam extends JPEGImageWriteParam {

	protected static Logger log = Logger.getLogger(MyImageWriteParam.class);

	public MyImageWriteParam() {
		super(Locale.getDefault());
	}

	public void setCompressionQuality(float quality) {
		if (quality < 0.0F || quality > 1.0F) {
			throw new IllegalArgumentException("Quality out-of-bounds!");
		}
		this.compressionQuality = 256 - (quality * 256);
	}
	public void compressJpegFile(File infile, File outfile, float compressionQuality) {
		try {
			// 检索要压缩的图片
			RenderedImage rendImage = ImageIO.read(infile);

			// 找到一个jpeg writer
			ImageWriter writer = null;
			Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
			if (iter.hasNext()) {
				writer = (ImageWriter) iter.next();
			}

			// 准备输出文件
			ImageOutputStream ios = ImageIO.createImageOutputStream(outfile);
			if(writer==null){
				log.info("--------空对象writer");
				return;
			}
			writer.setOutput(ios);

			// 设置压缩比
			ImageWriteParam iwparam = new MyImageWriteParam();
			iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			iwparam.setCompressionQuality(compressionQuality);

			// 写图片
			writer.write(null, new IIOImage(rendImage, null, null), iwparam);

			// 最后清理
			ios.flush();
			writer.dispose();
			ios.close();
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) {
		String initSrc = "c:\\1.jpg";
		String dest = "c:\\2.jpg";
		new MyImageWriteParam().compressJpegFile(ForUtil.createFile(initSrc), new File(dest), 1);
	}
}
