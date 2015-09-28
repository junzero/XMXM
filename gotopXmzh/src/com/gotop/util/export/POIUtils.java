package com.gotop.util.export;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.eos.system.annotation.Bizlet;
import com.gotop.util.security.ForUtil;


public class POIUtils {
	private final static Log logger = LogFactory.getLog(POIUtils.class);
	  public static String fileToBeRead = "c:/test1.xls";
	  public static String timePattern1 = "yyyy-MM-dd HH:mm:ss";
	  private static int MAXCOL = 35;
	
		/**
		 * 功能:解读Excel
		 * 
		 * @param fileToBeRead .xls地址
		 * @param sheetIndex 帧号
		 * @param maxCol 最大列数
		 * @return String[][][0]==值    String[][][1]==宽   String[][][2]==高    String[][][3]==rowspan   String[][][4]==colspan String[][][5]==CellType
		 * @throws Exception
		 */
		public static Object[][][] readExcelBySheetIndex(String fileToBeRead,int sheetIndex,int maxRow, int maxCol) throws Exception {

			Object[][][] datas = null;
			File fileTemp = ForUtil.createFile(fileToBeRead);
			if(!fileTemp.exists()){
				return null;
			}
			InputStream inStream = ForUtil.createFileInputStream(fileTemp);
			try {
				HSSFWorkbook workbook = new HSSFWorkbook(inStream);
				if (null != workbook.getSheetAt(sheetIndex)) {
					HSSFSheet sheet = workbook.getSheetAt(sheetIndex); // 获得一个sheet
					logger
							.info("--sheetName=="
									+ workbook.getSheetName(sheetIndex));
					if (!sheet.isDisplayFormulas()) {
						sheet.setDisplayFormulas(true);
					}
					
					if(maxRow==0 || maxRow>sheet.getPhysicalNumberOfRows()){
						maxRow = sheet.getPhysicalNumberOfRows();
					}
                    //System.out.println(maxRow);
					datas = new Object[maxRow + 1][][];

					for (int rowNumOfSheet = 0; rowNumOfSheet < maxRow; rowNumOfSheet++) {
						if (null != sheet.getRow(rowNumOfSheet)) {
							HSSFRow aRow = sheet.getRow(rowNumOfSheet);
							int maxLastCellNum = aRow.getPhysicalNumberOfCells();
							// log.info("--maxLastCellNum====" + maxLastCellNum);
							if (maxLastCellNum > maxCol) {
								maxLastCellNum = maxCol;
							}
							datas[rowNumOfSheet] = new Object[maxLastCellNum+1][6];
							for (short cellNumOfRow = 0; cellNumOfRow < maxLastCellNum; cellNumOfRow++) {
								Object strCell = "";
								if (null != aRow.getCell(cellNumOfRow)) {
									HSSFCell aCell = aRow.getCell(cellNumOfRow);
									int cellType = aCell.getCellType();
									// log.info("--cellType=="+cellType);
									switch (cellType) {
									case HSSFCell.CELL_TYPE_NUMERIC: // Numeric
										strCell = String.valueOf(aCell
												.getNumericCellValue());
										if (HSSFDateUtil.isCellDateFormatted(aCell)) {
											double d = aCell.getNumericCellValue();
											strCell = getDateTime(timePattern1,HSSFDateUtil.getJavaDate(d));
										} else {
											NumberFormat nf = NumberFormat.getInstance();
											strCell = nf.parse((String)(strCell));
										}
										break;

									case HSSFCell.CELL_TYPE_STRING: // String
										strCell = aCell.getStringCellValue();
										// log.info("String["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
										break;

									case HSSFCell.CELL_TYPE_FORMULA: // formula
										// strCell =aCell.getCellFormula();
										strCell = String.valueOf(aCell
												.getNumericCellValue());
										// log.info("formula["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
										break;

									case HSSFCell.CELL_TYPE_BLANK: // blank
										strCell = aCell.getStringCellValue();
										// log.info("blank["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
										break;

									default:
										// log.info("----------------格式读入不正确！");
										// //其它格式的数据
										boolean cellTypeD = HSSFDateUtil.isCellDateFormatted(aCell);
										if(cellTypeD){
											cellType = -1;
											strCell = aCell.getDateCellValue();
										}else{
											strCell = "格式读入不正确!";
										}
									}
									if(strCell instanceof String){
										strCell = ((String)strCell).replaceAll("'", "''");
									}
									//System.out.println("---------rowNumOfSheet--"+rowNumOfSheet);
									//System.out.println("---------rowNumOfSheet--"+rowNumOfSheet);
									datas[rowNumOfSheet][cellNumOfRow][0] = strCell;
									String width = String.valueOf(sheet.getColumnWidth((short)cellNumOfRow));
									datas[rowNumOfSheet][cellNumOfRow][1] = width;
									datas[rowNumOfSheet][cellNumOfRow][2] = String.valueOf(aRow.getHeight());
									datas[rowNumOfSheet][cellNumOfRow][5] = cellType;
								}
							}
						}
					}
					for(int i=0;i<sheet.getNumMergedRegions();i++){
						Region r = sheet.getMergedRegionAt(i);
						datas[r.getRowFrom()][r.getColumnFrom()][3] = String.valueOf(r.getRowTo()-r.getRowFrom()+1);
						datas[r.getRowFrom()][r.getColumnFrom()][4] = String.valueOf(r.getColumnTo()-r.getColumnFrom()+1);
						for (int j = r.getRowFrom()+1; j <= r.getRowTo(); j++) {
							for (int k = r.getColumnFrom(); k <= r.getColumnTo(); k++) {
								datas[j][k][0]="rowspan colspan null";
							}
						}
						for (int j = r.getColumnFrom() +1; j <= r.getColumnTo(); j++) {
							for (int k = r.getRowFrom(); k <= r.getRowTo(); k++) {
								datas[k][j][0]="rowspan colspan null";
							}
						}
					}
				}

				logger.info("====================sheet  end========================");
				workbook.cloneSheet(0);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("ReadExcelError==" + e.getMessage());
			} finally {
				inStream.close();
			}
			return datas;
		}
		
		/**
		 * This method generates a string representation of a date's date/time
		 * in the format you specify on input
		 *
		 * @param aMask the date pattern the string is in
		 * @param aDate a date object
		 * @return a formatted string representation of the date
		 * 
		 * @see java.text.SimpleDateFormat
		 */
		public static final String getDateTime(String aMask, Date aDate) {
			SimpleDateFormat df = null;
			String returnValue = "";
			
			if (aDate == null) {
				logger.error("aDate is null!");
			} else {
				df = new SimpleDateFormat(aMask);
				returnValue = df.format(aDate);
			}
			
			return (returnValue);
		}
	
	public static String RMSheetByName(String path,String sheetName) throws Exception {
		File file = ForUtil.createFile(path);
		if(!file.exists()){
			throw new Exception("模板文件没有找到");
		}
		String newPathName = String.valueOf(new Date().getTime());
		String newPath = file.getParentFile().createTempFile(newPathName, ".xls").getAbsolutePath();
		System.out.println(newPath);
		HSSFWorkbook hwb = readTemplate(path);
		
		int si = hwb.getSheetIndex(sheetName);
		System.out.println(si);
		if(si<0){
			return newPath;
		}
		hwb.removeSheetAt(si);
		saveExcel(hwb,newPath);
		return newPath;
	}

	 public static HSSFWorkbook readTemplate(String templatePath) {    
		   HSSFWorkbook result = null;   
		   FileInputStream fileIn = null;
		    try{
		     fileIn = ForUtil.createFileInputStream(templatePath);
		     POIFSFileSystem fs =new POIFSFileSystem(fileIn);   
		     result= new HSSFWorkbook(fs);   
		    }catch(Exception ex){   
		     ex.printStackTrace();   
		    }finally{
		    	if(fileIn!=null){
		    		try {
						fileIn.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		    }   
		    return result;
	}	
	
	public static boolean saveExcel(HSSFWorkbook wb,String path){   
	     boolean isSuccess=false;   
	     FileOutputStream fileOut = null;
	   try {   
	     fileOut = ForUtil.createFileOutputStream(path);  
	     wb.write(fileOut);   
	     isSuccess=true;   
	     System.out.println("数据替换完成");   
	   } catch (Exception e) {   
	     isSuccess=false;   
	     System.out.println("数据替换失败");   
	    e.printStackTrace();   
	   }finally{
		   if(fileOut!=null){
			   try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		   }
	   }  
	   return isSuccess;
  }
	
	public static boolean deleteExcel(String tempPath) throws Exception{
		File file = ForUtil.createFile(tempPath);
		return file.delete();
	}
		
	public static void main(String[] arg){
		try{
			String newPath = RMSheetByName("C:/ddd.xls", "Sheet5");
			System.out.println(newPath);
			if(1==1){
				return;
			}
			
			Object[][][] arra= POIUtils.readExcelBySheetIndex("C:/1252312803281.xls", 0,100, 100);
			
			for (int i = 0; i < arra.length; i++) {
				if(arra[i]==null){
					continue;
				}
				for (int j = 0; j < arra[i].length; j++) {
					System.out.println("-------arra--i=="+i+"--j--"+j+"----"+arra[i][j][0]);
					
				}
			}
			
//			POIUtils.test1(arra);
//			if(1==1){
//				return;
//			}
			
			
			List<QLExportItem[]> ql = new ArrayList<QLExportItem[]>();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
