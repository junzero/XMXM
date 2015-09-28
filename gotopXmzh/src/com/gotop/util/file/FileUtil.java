package com.gotop.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import jxl.read.biff.BiffException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gotop.util.security.ForUtil;
import com.gotop.util.time.TimeUtil;


/**
 * <p>Title: 文件处理类</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-3-8
 *
 * @version 1.0
 **/
public class FileUtil {

	
	public static Logger log = Logger.getLogger(FileUtil.class);
	/**
	 * 将数据保存在本地文件中
	 * @param list 数据列表(List<String>)
	 * @param filePath 文件路径
	 * @return 保存的数据条数
	 * @throws IOException
	 */
	public static int writeFileData(List list, String filePath) throws IOException {
		File file = ForUtil.createFile(filePath);
		FileWriter fw = null;
		BufferedWriter bw = null;
		Iterator it = list.iterator();
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			while(it.hasNext()){
				bw.write((String)it.next());
				bw.write("\r\n");
			}
			bw.flush();
		} catch (IOException e) {
			throw new IOException("找不到系统路径[" + filePath + "] " + e.getMessage());
		} finally{
			try {
				if(bw != null) bw.close();
				if(fw != null) fw.close();
			} catch (IOException e) {
				throw e;
			}
		}
		
		return list.size();
	}
	
	/**
	 * 添加数据到本地文件中
	 * @param list 数据列表(List<String>)
	 * @param filePath 文件路径
	 * @return 保存的数据条数
	 * @throws IOException
	 */
	public static int addFileData(List list, String filePath) throws IOException {
		File file = ForUtil.createFile(filePath);
		FileWriter fw = null;
		BufferedWriter bw = null;
		Iterator it = list.iterator();
		
		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			
			while(it.hasNext()){
				bw.write((String)it.next());
				bw.write("\r\n");
			}
			bw.flush();
		} catch (IOException e) {
			throw new IOException("找不到系统路径[" + filePath + "] " + e.getMessage());
		} finally{
			try {
				if(bw != null) bw.close();
				if(fw != null) fw.close();
			} catch (IOException e) {
				throw e;
			}
		}
		
		return list.size();
	}
	
	/**
	 * 读取指定文件内的全部结果数据
	 * @param filePath 文件路径
	 * @return List<String> 包含全部数据的列表
	 * @throws IOException
	 */
	public static List readFileData(String filePath) throws IOException {
		List list = new ArrayList();
		
		File file = ForUtil.createFile(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String element = null;
			
			while ((element = br.readLine()) != null) {
				list.add(element);
			}
		} catch (IOException e) {
			throw new IOException("找不到系统路径[" + filePath + "] " + e.getMessage());
		} finally {
			try {
				if(br != null) br.close();
				if(fr != null) fr.close();
			} catch (IOException e) {
				throw e;
			}
		}
		
		return list;
	}
	
	/**
	 * 读取Excel文件中的数据，将每条数据存入一个字符串数组中
	 * @param is 上传文件的数据输入流
	 * @return List<String[]> 上传的Execl文件中的数据集合
	 * @throws IOException
	 * @throws BiffException 
	 */
	public static List readExcelFileData(InputStream is) throws Exception {
		HSSFWorkbook wk = null;
		List list = new ArrayList();
		try {
			wk = new HSSFWorkbook(is);//创建个workbook，根据InputStream对象
			int sheetNum = wk.getNumberOfSheets();//取得sheet数
			for(int i = 0;i<sheetNum;i++){
				HSSFSheet sh = wk.getSheetAt(i);//取得当前用sheet
				int rowNum = sh.getLastRowNum();//取得当前sheet的总行数
				for(int j = 0;j<=rowNum&&rowNum!=0;j++){
					HSSFRow sr = sh.getRow(j);
					int cellNum = sr.getPhysicalNumberOfCells();//获取当前ROW有多少CELL
					String strs[] = new String[cellNum];
					for(int k = 0;k<cellNum;k++){
						HSSFCell sc = sr.getCell((short)k);
						String str = sc.getRichStringCellValue().toString();
						strs[k] = str;
					}
					list.add(strs);
				}
			}
		} catch (IOException e) {
			throw new Exception(e);
		}finally{
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				throw new Exception (e);
			}
		}
		return list;
	}

	/**
	 * 读取TXT文件中的数据，将每条数据存入一个字符串数组中(按","分割)
	 * @param is 上传文件的数据输入流
	 * @return List<String[]> 上传的TXT文件中的数据集合
	 * @throws IOException 
	 */
	public static List readTxtFileData(InputStream is) throws IOException{
		List list = new LinkedList();
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isReader);
		String element = null;
		
		try {
			while ((element = br.readLine()) != null) {
					list.add(element.split(","));
			}
		} catch (IOException e) {
			throw e;
		} finally{
			try {
				if(is != null) is.close();
				if(br != null) br.close();
				if(isReader != null) isReader.close();
			} catch (IOException e) {
				throw e;
			}
		}

		return list;
	}
	
//	/**
//	 * 读取TXT文件中的数据，将每条数据存入一个字符串数组中(按"|"分割)
//	 * @param is 上传文件的数据输入流
//	 * @return List<String[]> 上传的TXT文件中的数据集合
//	 * @throws IOException 
//	 */
//	public static List readTxtFileData1(InputStream is) throws IOException{
//		List list = new LinkedList();
//		InputStreamReader isReader = new InputStreamReader(is);
//		BufferedReader br = new BufferedReader(isReader);
//		String element = null;
//		
//		try {
//			while ((element = br.readLine()) != null) {
//					list.add(element.split("[|]"));
//			}
//		} catch (IOException e) {
//			throw e;
//		} finally{
//			try {
//				if(is != null) is.close();
//				if(br != null) br.close();
//				if(isReader != null) isReader.close();
//			} catch (IOException e) {
//				throw e;
//			}
//		}
//
//		return list;
//	}
	
	/**
	 * 按行读取文件装入LIST
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static List readTxtFileDataByRow(InputStream is) throws IOException{
		List list = new LinkedList();
		InputStreamReader isReader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isReader);
		String element = null;
		while((element = br.readLine()) != null){
			list.add(element);
		}
		try {
			if(is != null) is.close();
			if(br != null) br.close();
			if(isReader != null) isReader.close();
		} catch (IOException e) {
			throw e;
		}
		return list;
	}
	
	/**
	 * 文件上传函数 面传上传文件，获取文件数据流 做为参数传入将流写入服务器某路径下
	 * 
	 */
	public static int fileUpload(InputStream in, String path){
		OutputStream out = null;
		int i = 0;// 状态标志，上传文件内容不为空时值为1，为空时为0
		try {
			//定义接收文件的路径（web服务器上）
		    out = ForUtil.createFileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
				out.write(buffer, 0, bytesRead); // 将文件写入服务器
				i++;
			}
			// *************当上传文件内容为空时，将已经上传到服务器的文件删除掉*********
			if (i == 0) {
				File deFile = ForUtil.createFile(path);
				deFile.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	
	/** 文件删除函数*** */
    public static void deleteFile(String path) throws Exception{
    	try{
    		File file = ForUtil.createFile(path);
    		file.delete();
    	}catch(Exception e){
    		throw e;
    	}
    }
    /**
	 * 通过给定的包括绝对路径的文件名来获得文件的长度。如果文件不存在，则返回-1
	 * 
	 * @param path
	 *            带绝对路径的文件名
	 * @return 文件长度
	 */
	public static long getSize(String path) {
		File file = ForUtil.createFile(path);
		if (file.isFile()) {
			return file.length();
		} else {
			return -1;
		}
	}
	/**
	 * 通过绝对路径的获得不带路径的文件的名称。
	 * 
	 * @param path
	 *            带绝对路径的文件名
	 * @return 文件名
	 */
	public static String getFileName(String path) {
		if (path == null || path.trim().equals(""))
			return null;
		File file = ForUtil.createFile(path);
		return file.getName();
	}
	/**
	 * 获得文件的行数。如果文件不存在或者读取文件发生错误等异常则返回-1
	 * 
	 * @param path
	 *            带绝对路径的文件名
	 * @return 文件的行数
	 */
	public static long getFileLines(String path) {
		File file = ForUtil.createFile(path);
		if (file.isFile()) {
			int count = 1;
			BufferedReader bf = null;
			try {
				bf = new BufferedReader(new InputStreamReader(ForUtil.createFileInputStream(file)));
				while (bf.readLine() != null)
					count++;
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
				return -1;
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return -1;
			} finally {
				if (bf != null)
					try {
						bf.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
						return -1;
					}
			}
			return count;
		} else {
			return -1;
		}
	}
	/**
	 * 通过给定的绝对两个路径的文件名，比较两个文件是否完全相同
	 * 
	 * @param path1
	 * @param path2
	 * @return
	 */
	public static boolean isSameFile(String path1, String path2) {
		File file1 = ForUtil.createFile(path1);
		File file2 = ForUtil.createFile(path2);
		if (!file1.isFile() || !file2.isFile()) {
			return false;
		}
		boolean ret = true;
		BufferedReader bf1 = null;
		BufferedReader bf2 = null;
		try {
			bf1 = new BufferedReader(new InputStreamReader(ForUtil.createFileInputStream(file1)));
			bf2 = new BufferedReader(new InputStreamReader(ForUtil.createFileInputStream(file2)));
			String line1 = "";
			String line2 = "";
			while ((line1 = bf1.readLine()) != null) {
				line2 = bf2.readLine();
				if (line2 == null || !line1.equals(line2)) {
					ret = false;
					break;
				}
			}
			if (bf2.readLine() != null) {
				ret = false;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (bf1 != null)
					bf1.close();
				if (bf2 != null)
					bf2.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		return ret;
	}
	/**
	 * 通过给定的带有绝对路径的文件名去获得和读取文件，将文件内容读取到字节数组中， 然后读取长度为指定的字节数组的长度， 返回实际读取的文件长度。
	 * 
	 * @param path
	 *            带有绝对路径的文件名
	 * @param b
	 *            字节数组
	 * @return 读取文件的实际长度
	 */
	public static long read(String path, byte[] b) {
		File file = ForUtil.createFile(path);
		long length = -1;
		if (file.isFile()) {
			InputStream in = null;
			try {
				in = ForUtil.createFileInputStream(file);
				length = in.read(b);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} catch (IOException e) {
				System.err.println(e.getMessage());
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						System.err.println(e.getMessage());
					}
				}
			}
		} else {
			System.err.println("不存在文件：" + path);
		}
		return length;
	}
	/**
	 * 替换一个文件中的指定字符串为其他的字符串，替换成功后将产生一个新的文件，文件名为：原文件名_tmp
	 * 
	 * @param path
	 *            带有绝对路径的文件名
	 * @param orign
	 *            需要从原文件中替换的字符串
	 * @param target
	 *            替换后的字符串
	 * 
	 * @return 操作成功与否
	 */
	public static boolean fsReplace(String path, String orign, String target) {
		File file = ForUtil.createFile(path);
		boolean ret = true;
		if (file.isFile()) {

			if (orign == null || orign.equals("")) {
				System.err.println("需要从原文件中替换的字符串不能为空或空字符串：" + path);
				return false;
			}

			if (orign == null) {
				orign = "";
			}

			if (file.length() > Math.pow(2, 31) - 1) {
				System.err.println("文件太大：" + path);
				ret = false;
			} else {
				byte[] b = new byte[new Long(file.length()).intValue()];
				read(path, b);
				String str = new String(b);
				FileOutputStream out = null;
				try {
					out = ForUtil.createFileOutputStream(path + "_tmp");
					out.write(str.replace(orign, target).getBytes());
					out.flush();
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
					ret = false;
				} catch (IOException e) {
					System.err.println(e.getMessage());
					ret = false;
				} finally {
					if (out != null)
						try {
							out.close();
						} catch (IOException e) {
							System.err.println(e.getMessage());
							ret = false;
						}
				}
			}
		} else {
			System.err.println("不存在文件：" + path);
			ret = false;
		}
		return ret;
	}

	/**
	 * 判断指定的路径是否为目录
	 * 
	 * @param path
	 *            输入带绝对路径的目录名
	 * @return true-是目录<br/>
	 *         false-不是目录
	 */
	public static boolean isDir(String path) {
		File file = ForUtil.createFile(path);
		if (file.isDirectory()) {
			return true;
		} else
			return false;
	}

	/**
	 * 创建目录
	 * 
	 * @param path
	 *            输入带绝对路径的目录名
	 * @return true-创建目录成功<br/>
	 *         false-创建目录失败
	 */
	public static boolean mkDir(String path) {
		File file = ForUtil.createFile(path);
		if (file.isDirectory()) {
			System.err.println("目录已经存在");
			return false;
		} else {
			return file.mkdir();
		}
	}

	/**
	 * 在指定的目录下移除修改时间在指定日期之前的文件
	 * 
	 * @param path
	 *            待操作的目录（绝对路径）
	 * @param date
	 *            指定日期，格式为yyyyMMdd
	 * @return -1-操作失败<br/>
	 *         其它数字-成功删除的文件数
	 */
	public static int removeBefore(String path, String date) {
		File file = ForUtil.createFile(path);
		int ret = 0;
		if (!TimeUtil.isDate(date)) {
			System.err.println("日期参数目录格式非法，正确格式应该为：yyyyMMdd");
			return -1;
		}
		long before = TimeUtil.toTime(date + "000000") * 1000;
		if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File item = files[i];
				if (item.isFile() && item.lastModified() < before) {
					item.delete();
					ret++;
				}
			}
		} else {
			System.err.println("不存在指定目录：" + path);
			return -1;
		}
		return ret;
	}
//    public static void main(String[] args) throws Exception{
//		FileInputStream fis = new FileInputStream("g://bbb.xls");
//		List list = readExcelFileData(fis);
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//			String strs[] = (String[])it.next();
//			for(int i = 0;i<strs.length;i++){
//				System.out.println(strs[i]);
//			}
//		}
//    }
}
