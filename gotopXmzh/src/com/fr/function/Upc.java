package com.fr.function;

import java.awt.image.BufferedImage;

import org.krysalis.barcode4j.impl.upcean.UPCABean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.fr.script.AbstractFunction;
  
public class Upc extends AbstractFunction {
    public Object run(Object[] args) {
        if (args == null || args.length < 1) {      
            return "参数不对，必须有一个参数";      
        }      
        try {      
            // 创建一个UPC编码生成器   
            UPCABean bean = new UPCABean();    
            // 设置条形码高度   
            final int dpi = Integer.parseInt(args[1].toString());      
            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));      
            bean.doQuietZone(false);      
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi,BufferedImage.TYPE_BYTE_BINARY, false, 0);      
            // 创建条形码   
            bean.generateBarcode(canvas, "" + args[0]);      
            canvas.finish();      
            // 返回图片显示      
            return canvas.getBufferedImage();      
        } catch (Exception e) {      
            e.printStackTrace();      
        }      
        return args[0];      
    }      
    
    public static void main(String[] arg){
    	Upc upc = new Upc();
    	Object[] args = {"12345678912",100};
    	upc.run(args);
    	
    }
}   
