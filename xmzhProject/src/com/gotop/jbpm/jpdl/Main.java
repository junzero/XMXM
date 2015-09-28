package com.gotop.jbpm.jpdl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

import com.gotop.jbpm.jpdl.model.JpdlModel;

public class Main {
	
public static void main(String[] args) {
	FileInputStream fis;
	try {
		fis = new FileInputStream("D:\\develop\\tomcat-6.0\\webapps\\ROOT\\WEB-INF\\classes\\superviseTable.jpdl.xml");
		//JpdlModel jpdlModel = new JpdlModel (fis);    
	//	  ImageIO.write(new JpdlModelDrawer().draw(jpdlModel), "png", new File("c:/loan1.png"));    
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
}
}
