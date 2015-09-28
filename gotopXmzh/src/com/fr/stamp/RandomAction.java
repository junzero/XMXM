package com.fr.stamp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.gotop.util.security.ForUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RandomAction extends ActionSupport{
	
	private ByteArrayInputStream inputStream;
	
	public String randimage() throws Exception{
//		 在内存中创建图象
		int width=85, height=20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		 获取图形上下文
		Graphics g = image.getGraphics();
//		生成随机类
		Random random = new Random();
//		 设定背景色
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
//		设定字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
//		 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160,200));
		for (int i=0;i<155;i++)
		{
		 int x = ForUtil.rNextInt(width);
		 int y = ForUtil.rNextInt(height);
		        int xl = ForUtil.rNextInt(12);
		        int yl = ForUtil.rNextInt(12);
		 g.drawLine(x,y,x+xl,y+yl);
		}
//		 取随机产生的认证码(6位数字)
		String sRand="";
		for (int i=0;i<6;i++){
		    String rand=String.valueOf(ForUtil.rNextInt(10));
		    sRand+=rand;
		    // 将认证码显示到图象中
		    g.setColor(new Color(20+ForUtil.rNextInt(110),20+ForUtil.rNextInt(110),20+ForUtil.rNextInt(110)));
//		调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		    g.drawString(rand,13*i+6,16);
		}
//		 将认证码存入SESSION
		ActionContext.getContext().getSession().put("yzcRand",sRand);
//		 图象生效
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
        ImageIO.write(image, "JPEG", imageOut);
        imageOut.close();
        ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
        this.setInputStream(input);
		return SUCCESS;
	}
	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc,int bc){
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+ForUtil.rNextInt(bc-fc);
        int g=fc+ForUtil.rNextInt(bc-fc);
        int b=fc+ForUtil.rNextInt(bc-fc);
        return new Color(r,g,b);
   }
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

}