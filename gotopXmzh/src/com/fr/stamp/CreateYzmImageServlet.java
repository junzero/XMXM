package com.fr.stamp;

//定义生成验证码图片的Servlet：


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
import com.gotop.util.security.ForUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 
public class CreateYzmImageServlet extends HttpServlet {
 
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       //取消缓存，否则没有单击后更新不了图片
       response.setHeader("cache-control", "no-cache");
       
       response.setContentType("image/jpeg");
       
       int width=50;
       int height=30;
       
       BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
       
       Graphics2D g=bi.createGraphics();
       
       g.setBackground(Color.YELLOW);
       
       g.clearRect(0,0,width, height);
       
       g.setColor(Color.BLACK);
       
       g.drawString(getRandomString(request), 5, 20);
       
       g.dispose();
       bi.flush();
       
       //设定jpg格式解码
       OutputStream outPut=response.getOutputStream();
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outPut);
       JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
       param.setQuality(1.0f, false);
       encoder.setJPEGEncodeParam(param);
 
       try {
           encoder.encode(bi);
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       System.out.println("image runed ...");
       
    }
 
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       this.doGet(request, response);
       
    }
    //随机生成的四位数的验证码
    private String getRandomString(HttpServletRequest request){
 
       int ranNum=(int)(ForUtil.rRandom()*9000)+1000;
       //放置进session
       request.getSession().setAttribute("ranNum", ranNum+"");
       
       return ranNum+"";
    }
 
}
