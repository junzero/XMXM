/*******************************************************************************
 * $Header: /opt/cvsroot/wiki/opensource/gocom/abframe2/src/org.gocom.abframe.tools/src/org/gocom/abframe/tools/ExcelUtil.java,v 1.4 2009/02/24 06:47:40 xusl Exp $
 * $Revision: 1.4 $
 * $Date: 2009/02/24 06:47:40 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2008-9-3
 *******************************************************************************/

package com.gotop.util.export;

import java.io.File;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.gotop.util.file.ExcelTemplate;
import com.gotop.util.file.JavaCallLogic;
import com.gotop.util.security.ForUtil;
import com.primeton.utils.Condb;
import com.primeton.utils.ResultSet;
import commonj.sdo.DataObject;
/**
 *
 * Excel文件操作工具类<BR>
 *
 * @author wengzr (mailto:wengzr@primeton.com)
 */
public class ExcelUtil {

	protected static Logger log = Logger.getLogger(ExcelUtil.class);
	public List<String> sqlL= new ArrayList<String>();
	
	private ExcelUtil() {
		//工具类不允许实例化
	}

	/**
	 * 将Excel数据导入到数据库指定的表，默认每500条数据执行一次批处理导入
	 *
	 * @param excelFile Excel文件名
	 * @param entityFullName 导入的实体全名
	 * @return
	 * @throws Exception
	 */
	public static int importExcel(String excelFile, String entityFullName)
			throws Exception {
		ExcelTemplate template = new ExcelTemplate();
		return template.importData(excelFile, entityFullName, 500);
	}

	/**
	 * 将指定的对象数组exportObjects导出到指定模板的Excel文件
	 *
	 * @param exportObjects 待导出的对象数组
	 * @param exportInfo  模板文件的其他附加信息(非结果集内容)
	 * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
	 * @return 返回生成的Excel文件下载路径
	 * @throws Exception
	 */
	public static String exportExcel(List exportObjects,
			DataObject exportInfo, String templateFilename) throws Exception {
		return exportExcel(exportObjects, exportInfo, templateFilename, false,
				false);
	}

	/**
	 * 分页将对象数组导出到指定的模板Excel文件,注意：此时模板文件必需包含Excel表格的分页符
	 * @param exportObjects 待导出的对象数组
	 * @param exportInfo  模板文件的其他附加信息(非结果集内容)
	 * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
	 * @return 返回生成的Excel文件下载路径
	 * @throws Exception
	 */
	public static String exportExcelWithPagnation(List exportObjects,
			DataObject exportInfo, String templateFilename) throws Exception {
		return exportExcel(exportObjects, exportInfo, templateFilename, true,
				false);
	}

	/**
	 * 分工作表将对象数组导出到指定的模板Excel文件，默认情况下输出工作表最大行:20000
	 * @param exportObjects 待导出的对象数组
	 * @param exportInfo  模板文件的其他附加信息(非结果集内容)
	 * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
	 * @return 返回生成的Excel文件下载路径
	 * @throws Exception
	 */
	public static String exportExcelWithSheet(List exportObjects,
			DataObject exportInfo, String templateFilename) throws Exception {
		return exportExcel(exportObjects, exportInfo, templateFilename, false,
				true);
	}

	/**
	 * 导出Excel文件,根据指定路径下的模板生成输出的Excel文件
	 *
	 * @param exportObjects 待导出的对象数组
	 * @param exportInfo 模板文件的其他附加信息(非结果集内容)
	 * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
	 * @param autoPagination 是否分页
	 * @param autoSheet 是否分工作表
	 * @return 返回生成的Excel文件下载路径
	 * @throws Exception
	 */
	private static String exportExcel(List exportObjects,
			DataObject exportInfo, String templateFilename,
			boolean autoPagination, boolean autoSheet) throws Exception {
		return exportExcel(exportObjects, exportInfo, templateFilename,
				autoPagination, autoSheet, false, null);
	}

	/**
	 * 导出Excel文件,根据指定路径下的模板生成输出的Excel文件,hm为下拉列表值
	 *
	 * @param exportObjects 待导出的对象数组
	 * @param exportInfo 模板文件的其他附加信息(非结果集内容)
	 * @param templateFilename 模板文件名(不带扩展名),对应到在user-config.xml配置路径下的模板文件
	 * @param autoPagination 是否分页
	 * @param autoSheet 是否分工作表
	 * @param includeFormula 是否显示公式
	 * @param hm 下拉列表 如：hashMap.put("fr1fc1lr2lc2","T(\"福州,厦门,泉州\")")。fr1fc1lr2lc2：first_row、first_col、last_row、last_col。。T(\"福州,厦门,泉州\")为公式
	 * @return 返回生成的Excel文件下载路径
	 * @throws Exception
	 */
	public static String exportExcel(List exportObjects,
			DataObject exportInfo, String templateFilename,
			boolean autoPagination, boolean autoSheet, boolean includeFormula,
			HashMap hm) throws Exception {
		String filename = templateFilename;

		if (filename.indexOf(".xls") == -1) {
			filename += ".xls";
		}

		String templateFile = JavaCallLogic.getSystemPath();
		String outputFile = templateFile + generateOutputExcelFile(filename);

		ExcelTemplate template = new ExcelTemplate(templateFile+filename, outputFile);
		template.setIncludeFormula(includeFormula);
		template.setAutoPagination(autoPagination);
		template.setAutoSheet(autoSheet);
		int excelExportMaxnumInt = 0;
		try {
			excelExportMaxnumInt = 100000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		template.setMaxRow(excelExportMaxnumInt);
		template.generate(exportObjects, exportInfo, hm);

		return outputFile;
	}
	public static String exportExcel(List exportObjects,HashMap hm) throws Exception {
		String templateFilename = JavaCallLogic.createTemplate(hm.get("fields").toString());
		String downloadFile = ExcelUtil.exportExcel(exportObjects, null, templateFilename, false, false, false, hm);
		return downloadFile;
	}
	public static String exportExcel(String[] exportObjects,DataObject exportInfo, String templateFilename) throws Exception {
		return exportExcel(Arrays.asList(exportObjects),null,templateFilename,false,false,false,null);
	}

	/**
	 * 生成EXCEL输出文件，默认带时间戳
	 * @param templateFilename 文件名
	 * @return
	 */
	private static String generateOutputExcelFile(String templateFilename) {
		String filename = templateFilename;
		if (templateFilename.endsWith(".xls")) {
			filename = templateFilename.substring(0,
					templateFilename.length() - 4);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetimeString = format.format(new Date());

		filename = filename + "_" + datetimeString + ".xls";
		return filename;
	}

	/**
	 * 功能:解读Excel
	 * 
	 * @param fileToBeRead .xls地址
	 * @param sheetIndex 帧号
	 * @param maxCol 最大行数
	 * @return 取数据: (Object[][][]) hmo.get("datas");
	 * 数据的第一维是行，第二维是列，第三维说明如下
	 * String[][][0]==值    String[][][1]==宽   String[][][2]==高    String[][][3]==rowspan   String[][][4]==colspan  String[][][5]==HSSFCell
	 * 			  String[][][6]==CellType   String[][][7]==HSSFFont   String[][][8]==公式   String[][][9]==批注   datas[rowNumOfSheet][cellNumOfRow][10]==附加类型
	 * @throws Exception
	 */
	public static HashMap<String, Object> readExcelBySheetIndex(
			String fileToBeRead, int sheetIndex, int maxCol) throws Exception {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Object[][][] datas = null;
		File fileTemp = ForUtil.createFile(fileToBeRead);
		if (!fileTemp.exists()) {
			return null;
		}
		InputStream inStream = ForUtil.createFileInputStream(fileTemp);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inStream);
			if (null != workbook.getSheetAt(sheetIndex)) {
				HSSFSheet sheet = workbook.getSheetAt(sheetIndex); // 获得一个sheet
				String sheetName = workbook.getSheetName(sheetIndex);
				hm.put("sheetName", sheetName);
				hm.put("sheetIndex", sheetIndex);
				hm.put("breaks", sheet.getRowBreaks());
				if (!sheet.isDisplayFormulas()) {
					sheet.setDisplayFormulas(true);
				}

				datas = new Object[sheet.getLastRowNum() + 1][][];

				for (int rowNumOfSheet = 0; rowNumOfSheet <= sheet
						.getLastRowNum(); rowNumOfSheet++) {
					if (null != sheet.getRow(rowNumOfSheet)) {
						HSSFRow aRow = sheet.getRow(rowNumOfSheet);
						int maxLastCellNum = aRow.getLastCellNum();
						if (maxLastCellNum > maxCol) {
							maxLastCellNum = maxCol;
						}
						datas[rowNumOfSheet] = new Object[maxLastCellNum][11];
						for (short cellNumOfRow = 0; cellNumOfRow < maxLastCellNum; cellNumOfRow++) {
							Object strCell = "";
							String formula = "";
							if (null != aRow.getCell(cellNumOfRow)) {
								HSSFCell aCell = aRow.getCell(cellNumOfRow);
								int cellType = aCell.getCellType();
								switch (cellType) {
								case HSSFCell.CELL_TYPE_NUMERIC: // Numeric
									strCell = String.valueOf(aCell
											.getNumericCellValue());
									if (HSSFDateUtil.isCellDateFormatted(aCell)) {
										double d = aCell.getNumericCellValue();
										SimpleDateFormat df = new SimpleDateFormat(
												"yyyy-MM-dd HH:mm:ss");
										strCell = df.format(HSSFDateUtil
												.getJavaDate(d));
									} else {
										NumberFormat nf = NumberFormat
												.getInstance();
										strCell = nf.parse((String) (strCell));
									}
									break;

								case HSSFCell.CELL_TYPE_STRING: // String
									strCell = aCell.getRichStringCellValue().getString();
									// log.info("String["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
									break;

								case HSSFCell.CELL_TYPE_FORMULA: // formula
									// strCell =aCell.getCellFormula();
									try {
										formula = aCell.getCellFormula();
										strCell = String.valueOf(aCell
												.getNumericCellValue());
									} catch (Exception e) {
										e.printStackTrace();
										strCell = aCell.getStringCellValue();
									}

									//									System.out.println("##################"+aCell.getCellFormula()+"#############"+aCell.getCellComment().getString());

									// log.info("formula["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
									break;

								case HSSFCell.CELL_TYPE_BLANK: // blank
									strCell = aCell.getRichStringCellValue()
											.getString();
									// log.info("blank["+rowNumOfSheet+","+cellNumOfRow+"]=="+strCell);
									break;

								default:
									// log.info("----------------格式读入不正确！");
									// //其它格式的数据
									boolean cellTypeD = HSSFDateUtil
											.isCellDateFormatted(aCell);
									if (cellTypeD) {
										cellType = -1;
										strCell = aCell.getDateCellValue();
									} else {
										strCell = "格式读入不正确!";
									}
								}
//								if (strCell instanceof String) {
//									strCell = ((String) strCell).replaceAll(
//											"'", "''");
//								}
								datas[rowNumOfSheet][cellNumOfRow][0] = strCell;
								String width = String.valueOf(sheet
										.getColumnWidth((short) cellNumOfRow));
								datas[rowNumOfSheet][cellNumOfRow][1] = width;
								datas[rowNumOfSheet][cellNumOfRow][2] = String
										.valueOf(aRow.getHeight());
								datas[rowNumOfSheet][cellNumOfRow][5] = aCell;
								datas[rowNumOfSheet][cellNumOfRow][6] = cellType;
								datas[rowNumOfSheet][cellNumOfRow][7] = aCell
										.getCellStyle().getFont(workbook);
								datas[rowNumOfSheet][cellNumOfRow][8] = formula;
								HSSFComment hc = aCell.getCellComment();
								if (hc != null) {
									datas[rowNumOfSheet][cellNumOfRow][9] = hc
											.getString();
								}
								datas[rowNumOfSheet][cellNumOfRow][10] = null;

								// log.info("datas["+rowNumOfSheet+"]["+
								// cellNumOfRow+"]==" +
								// datas[rowNumOfSheet][cellNumOfRow]);
							}
						}
					}
				}
				for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
					Region r = sheet.getMergedRegionAt(i);
					datas[r.getRowFrom()][r.getColumnFrom()][3] = String
							.valueOf(r.getRowTo() - r.getRowFrom() + 1);
					datas[r.getRowFrom()][r.getColumnFrom()][4] = String
							.valueOf(r.getColumnTo() - r.getColumnFrom() + 1);
					for (int j = r.getRowFrom() + 1; j <= r.getRowTo(); j++) {
						for (int k = r.getColumnFrom(); k <= r.getColumnTo(); k++) {
							//							datas[j][k][0]="rowspan colspan null";
							datas[j][k][0] = datas[r.getRowFrom()][k][0];
							datas[j][k][10] = "rowspan colspan null";
						}
					}
					for (int j = r.getColumnFrom() + 1; j <= r.getColumnTo(); j++) {
						for (int k = r.getRowFrom(); k <= r.getRowTo(); k++) {
							//							datas[k][j][0]="rowspan colspan null";
							datas[k][j][0] = datas[k][r.getColumnFrom()][0];
							datas[k][j][10] = "colspan rowspan null";
						}
					}
				}
			}

			log.info("====================sheet  end========================");
//			workbook.cloneSheet(0);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("ReadExcelError==" , e);
		} finally {
			inStream.close();
		}
		hm.put("datas", datas);

		return hm;
	}

	/*
	 * 模拟main
	 */
	public HashMap<String,Object> createSqlByExcel(String dirfilename) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		List<String> logError = new ArrayList<String>();
		List<String> logInfo = new ArrayList<String>();
//		List<String> sqlL= new ArrayList<String>();
		sqlL= new ArrayList<String>();
		result.put("sqlList", sqlL);
		result.put("logErrorList", logError);
		result.put("logInfoList", logInfo);
		
		logInfo.add("------------------------------------------开始读到Excel----------------------------");
		HashMap<String,String[]> fieldHM = new HashMap<String,String[]>();
		List<String> afterSL= new ArrayList<String>();
		Integer useRow=null;
		HashMap<Integer,HashMap<String,String>> hm = new HashMap<Integer,HashMap<String,String>>();
		String tableName="";
		String strField="";
		String templateInsert="";
		String field[][]=null;
		int dLen=0;
		logInfo.add("------------------------------------------读取Excel完成----------------------------path--"+dirfilename);
		Condb condb = new Condb("default");
		try {
			HashMap<String, Object> hmo = readExcelBySheetIndex(dirfilename, 0, 1000);
			Object[][][] datas = (Object[][][]) hmo.get("datas");
			if(datas.length>3){
				if (datas[1] == null || datas[1].length<1) {
					logError.add("行" + 0 + "内容为空。");
					return result;
				}
				dLen=datas[1].length;
				if(useRow!=null){
					dLen = useRow;
				}
				for (int j = 0; j < dLen; j++) {
					if (datas[1][j] == null) {
						logError.add("行=1列=" + j + "内容为空。");
						break;
					}
					if(datas[1][j][0]==null|| StringUtils.isBlank(datas[1][j][0].toString())){
						logError.add("--列头首行定义不完整---j--"+j);
						break;
					}
					String value = datas[1][j][0].toString().trim();
					String[] mess = value.substring(1, value.length()-1).split("]\\[");
					if("dict".equals(mess[0])){
						System.out.println("---------mess[1]--"+mess[1]);
						ResultSet rs = condb.executeQuery(mess[1]);
						HashMap<String,String> hms= new HashMap<String,String>();
						if(rs==null){
							logError.add("---行=1列="+j+"----执行失败-"+mess[1]);
							break;
						}
						while(rs.next()){
							hms.put(rs.getString(1), rs.getString(2));
						}
						if(hms.keySet().size()<1){
							logError.add("----列头首行--"+j+"--长度为--"+hms.keySet().size()+"---value-"+value+"。请进行检查");
							break;
						}
						hm.put(j, hms);
					}
				}
				logInfo.add("------------------------------------------取表头完成----------------------------");
				for(int t=0;t<datas[0].length;t++){
					if(datas[0][t]==null || datas[0][t][0]==null || StringUtils.isBlank(datas[0][t][0].toString())){
						continue;
					}
					String[] tempArra = datas[0][t][0].toString().substring(1, datas[0][t][0].toString().length()-1).split("]\\[");
					if(datas[0][t][0].toString().startsWith("[table][")){
						tableName = tempArra[1];
					}
					if(datas[0][t][0].toString().startsWith("[beforeSql][")){
						sqlL.add(tempArra[1]);
					}
					if(datas[0][t][0].toString().startsWith("[afterSql][")){
						afterSL.add(tempArra[1]);
					}
				}
				
				
				
				try {
					String tempSql = "SELECT USER_TAB_COLUMNS.COLUMN_NAME, "
						+ "		       USER_TAB_COLUMNS.DATA_TYPE, "
						+ "		       USER_TAB_COLUMNS.DATA_LENGTH, "
						+ "		       USER_COL_COMMENTS.COMMENTS, "
						+ "		       USER_TAB_COLUMNS.NULLABLE "
						+ "		  FROM USER_TAB_COLUMNS, USER_COL_COMMENTS "
						+ "		 WHERE USER_TAB_COLUMNS.TABLE_NAME = '"+tableName.toUpperCase()+"' "
						+ "		   AND USER_TAB_COLUMNS.TABLE_NAME = USER_COL_COMMENTS.TABLE_NAME "
						+ "		   AND USER_TAB_COLUMNS.COLUMN_NAME = USER_COL_COMMENTS.COLUMN_NAME ";
					ResultSet rs = condb.executeQuery(tempSql);
					while (rs.next()) {
						String[] userCol = new String[3];
						userCol[0]=rs.getString(2);//DATA_TYPE
						userCol[1]=rs.getString(3);//DATA_LENGTH
						userCol[2]=rs.getString(5);//NULLABLE
						fieldHM.put(rs.getString(1), userCol);
					}
				} catch (Exception e) {
					logError.add("---取表名出错"+tableName+"--Exception--"+e.getMessage());
					e.printStackTrace();
					return result;
				}
				field=new String[dLen][4];
				logInfo.add("-------------------------------------------读取表名完成----------------------------");
				templateInsert += "INSERT INTO "+tableName+"(";
				for (int j = 0; j < dLen; j++) {
					if (datas[2][j] == null || datas[2][j][0]==null || StringUtils.isBlank(datas[2][j][0].toString()) || datas[3][j] == null || datas[3][j][0]==null || StringUtils.isBlank(datas[3][j][0].toString())) {
						logError.add("行=" + 0 + "列=" + j + "内容为空。");
						break;
					}else{
						field[j][0]=datas[2][j][0].toString();
						field[j][1]=datas[3][j][0].toString();
						HSSFFont hf = (HSSFFont)datas[2][j][7];
						field[j][2]="";
						if(hf.getBoldweight()==HSSFFont.BOLDWEIGHT_BOLD){
							field[j][2]="BOLD";
						}
						if(hf.getItalic()){
							field[j][2]+="ITALIC";
							if(datas[1][j][0]!=null && datas[1][j][0].toString().indexOf("[PEntity][")>-1){
								field[j][3]=datas[1][j][0].toString().substring(1,datas[1][j][0].toString().length()-1).split("]\\[")[1];
							}else{
								logError.add("---主键列设定错误-"+datas[1][j][0]+"  列="+j);
								break;
							}
						}
						templateInsert += " "+field[j][0]+",";
						String[] fieldObj = fieldHM.get(field[j][0].toString().toUpperCase());
						if(fieldObj==null){
							logError.add("-----列-"+j+"---字段-"+field[j][0].toString().toUpperCase()+"--在表中不存在。");
						}
					}
				}
				templateInsert = templateInsert.substring(0, templateInsert.length()-1);
				templateInsert += " )values( ";
				strField += "select ";
				for(int l=0;l<dLen;l++){
					strField +=" "+field[l][0]+",";
				}
				strField += " rownum from "+tableName + " where rownum<2";
				try {
					ResultSet rs = condb.executeQuery(strField);
					rs.next();
				} catch (Exception e) {
					logError.add("---取表字段出错-"+strField+"--Exception--"+e.getMessage());
					e.printStackTrace();
					return result;
				}
			}else{
				logError.add("Excel文件内容为空或不完整!Length="+datas.length);
				return result;
			}
			if(logError.size()>0){
				return result;
			}
			logInfo.add("-------------------------------------------读取表字段完成----------------------------");
			for (int i = 4; i < datas.length; i++) {
				if (datas[i] == null || datas[i].length<1) {
					logError.add("行" + i + "内容为空。");
					continue;
				}
				StringBuffer sbUpdate = new StringBuffer("update "+tableName+" set ");
				StringBuffer sbInsert = new StringBuffer(templateInsert);
				String sbSelect = "select count(*) from "+tableName+" where 1=1 ";
				String sbDelete = "delete "+tableName+" where 1=1 ";
				String temp = "";
				int opType = 1;//1为插入，2为更新，0为删掉
				for (int j = 0; j < dLen; j++) {
					if (datas[i].length<dLen || datas[i][j] == null) {
						Object[][] tempArra =  new Object[dLen][11];
						System.arraycopy(datas[i], 0, tempArra, 0, datas[i].length);
						datas[i]=tempArra;
					}
					HSSFFont hf = (HSSFFont)datas[i][j][7];
					if(datas[1][j][0]!=null && datas[1][j][0].toString().indexOf("[split][")>-1){
						String tempSql = datas[1][j][0].toString().substring("[split][".length(), datas[1][j][0].toString().length()-1);
						try {
							if (datas[i][j][0] != null
									&& StringUtils.isNotBlank(datas[i][j][0]
											.toString())) {
								String[] tempArra = datas[i][j][0].toString()
										.split(",");
								for (int k = 0; k < tempArra.length; k++) {
									tempSql = tempSql.replaceAll("&" + k,tempArra[k]);//进行替换操作
								}
								ResultSet rs = condb.executeQuery(tempSql);
								if (rs.next()) {
									String tempStr = rs.getString(1);
									System.out.println("------------tempStr-"+tempStr);
									datas[i][j][0] = tempStr;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							logError.add("-----行1列:"+j+"--sql语句-"+tempSql);
						}						
					}
					if(datas[i][j][0]!=null && StringUtils.isNotBlank(datas[i][j][0].toString()) && hm.get(j)!=null){
						datas[i][j][0] = hm.get(j).get(datas[i][j][0].toString());//取自数据字典
					}
					if(field[j][2].indexOf("ITALIC")>-1 && (datas[i][j][0]==null || StringUtils.isBlank(datas[i][j][0].toString()))){
						Long pkey = com.eos.foundation.database.DatabaseExt.getNextSequence(field[j][3]);//自动生成KEY
						datas[i][j][0]=pkey;
					}
					String[] fieldObj = fieldHM.get(field[j][0].toString().toUpperCase());
					if("N".equals(fieldObj[2]) && (datas[i][j][0]==null || StringUtils.isBlank(datas[i][j][0].toString()))){
						logError.add("-------行:"+i+"--列:"+j+"---列-"+field[j][0]+"-数据不能为空");
						continue;
					}
					if("NUMBER".equals(fieldObj[0]) && datas[i][j][0]!=null && StringUtils.isNotBlank(datas[i][j][0].toString()) && !NumberUtils.isNumber(datas[i][j][0].toString())){
						logError.add("-------行:"+i+"--列:"+j+"-----值为["+datas[i][j][0]+"]-不为数据类型");
						continue;
					}else if("VARCHAR2".equals(fieldObj[0]) && datas[i][j][0]!=null && (field[j][0].toString().getBytes("UTF-8").length)>Integer.valueOf(fieldObj[1])){
						logError.add("-------行:"+i+"--列:"+j+"-----"+datas[i][j][0]+"--lengh-"+(field[j][0].toString().getBytes("UTF-8").length)+"-长度不能超过:"+fieldObj[1]);
						continue;
					}
					if(field[j][2].indexOf("BOLD")>-1){
						if((datas[i][j][0]==null || StringUtils.isBlank(datas[i][j][0].toString()))){
							logError.add("----主键值不能为空----行:"+i+"--列:"+j+"----"+field[j][1]);
							break;
						}else{
							temp += " and "+field[j][0]+"='"+datas[i][j][0]+"'";
						}
					}else{
						if(field[j][2].indexOf("ITALIC")<0){
							if(datas[i][j][0]==null){
								sbUpdate.append(field[j][0]+" = null, ");
							}else{
								sbUpdate.append(field[j][0]+"='"+datas[i][j][0]+"', ");
							}
						}
					}
					if(datas[i][j][0]==null){
						sbInsert.append(datas[i][j][0]+",");
					}else{
						sbInsert.append("'"+datas[i][j][0]+"',");
					}
					if(hf!=null && (hf.getStrikeout() || opType==0)){//斜体字为需删除的字段
						opType= 0;
/*						if(datas[i][j][0]!=null && "BOLD".equals(field[j][2])){
							sbDelete += " and "+field[j][0]+"='"+datas[i][j][0]+"'";
						}*/
					}
				}
				if(opType==0){
					sqlL.add(sbDelete+temp);
				}else{
					sbSelect += temp;
					ResultSet rs = condb.executeQuery(sbSelect);
					if(rs.next()){
						int num = rs.getInt(1);
						if(num<1){
							opType=1;
							sqlL.add(sbInsert.toString().substring(0,sbInsert.length()-1)+")");
						}else{
							opType=2;
							if(StringUtils.isNotBlank(temp)){
								String sbTemp = sbUpdate.toString().trim();
								sqlL.add(sbTemp.substring(0,sbTemp.length()-1)+" where 1=1 "+temp);
							}else{
								logInfo.add("----更新失败-行:"+i+"--条件为空-");
							}
						}
					}
				}
			}
			sqlL.addAll(afterSL);
			
/*			if(logError.size()<1){
				condb.getConn().setAutoCommit(false);
				for (int i = 0; i < sqlL.size(); i++) {
					String sqlStr = sqlL.get(i);
					if (StringUtils.isNotBlank(sqlStr)) {
						try{
							int eup = condb.getStmt().executeUpdate(sqlStr);
						}
						catch(Exception e){
							System.out.println(sqlStr);
							System.out.println("-------conn.getStmt().executeUpdate---"+e.getMessage());
							e.printStackTrace();
							condb.getConn().rollback();
						}
					}
				}
				condb.getConn().commit();
			}*/
			logInfo.add("-------------------------------------------生成SQL语句完成---------------------------共需操作记录数为:"+sqlL.size());
		} catch (Exception e) {
			e.printStackTrace();
			logError.add(e.getMessage());
			return result;
		}finally{
			condb.close();
		}
//		for (Iterator iter = sqlL.iterator(); iter.hasNext();) {
//			String element = (String) iter.next();
//			System.out.println(element+";");
//		}
		for (Iterator iter = logError.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			System.out.println("-----"+element);
		}
		return result;
	}
	
	/*
	 * 模拟main
	 */
	public HashMap<String,Object> runSql(List<String> sqlL) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		List<String> logError = new ArrayList<String>();
		List<String> logInfo = new ArrayList<String>();
		sqlL = this.sqlL;
		result.put("sqlList", sqlL);
		result.put("logErrorList", logError);
		result.put("logInfoList", logInfo);
		if(sqlL==null && sqlL.size()<1){
			logInfo.add("---没有任何可执行SQL-");
			return result;
		}
		Condb condb = new Condb("default");
		try {
			condb.getConn().setAutoCommit(false);
			for (int i = 0; i < sqlL.size(); i++) {
				String sqlStr = sqlL.get(i);
				if (StringUtils.isNotBlank(sqlStr)) {
					try {
						int eup = condb.getStmt().executeUpdate(sqlStr);
						logInfo.add("---行-" + i + "---状态-" + eup + "---SQL-" + sqlStr);
					} catch (Exception e) {
						logError.add("---行-" + i + "---SQL-" + sqlStr+ "---失败原因-" + e.getMessage());
						e.printStackTrace();
						condb.getConn().rollback();
					}
				} else {
					logInfo.add("---行-" + i + "---为空SQL-");
				}
			}
			condb.getConn().commit();
		} catch (Exception e) {
			e.printStackTrace();
			logInfo.add("---执行失败-"+e.getMessage());
			try {
				condb.getConn().rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		} finally {
			condb.close();
		}
		return result;
	}
	
	public static void main(String[] arg) throws Exception{
	    String dirfilename = "C:/TEMP/机构信息表_代理机构.xls";
        HashMap<String, Object> hmo = readExcelBySheetIndex(dirfilename, 0, 100000);
        Object[][][] datas = (Object[][][]) hmo.get("datas");
        StringBuffer sbr = new StringBuffer();
        for (int i = 0; i < datas.length; i++) {
            sbr.append("\n");
            if(datas[i]==null){
                continue;
            }
            for(int j=0;j<datas[i].length;j++){
                Object value = datas[i][j][0];
                sbr.append(value+"          ");
            }
        }
        log.info(sbr);
	}
}
