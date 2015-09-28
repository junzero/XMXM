package com.gotop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploadUtil {

	public static String uploadFile(String uuid,String fileDate,String address,String fileName,File upload,String suffixStr){
		address=address+File.separator+fileDate+File.separator+uuid+suffixStr;
		File uploadFile=new File(address);
		if(!uploadFile.getParentFile().exists()){
			uploadFile.getParentFile().mkdirs();
		}
		FileOutputStream fos;
        InputStream is;
        try {
			fos = new FileOutputStream(address);
			is = new FileInputStream(upload);
			byte[] buffer = new byte[(int) upload.length()];
			int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
