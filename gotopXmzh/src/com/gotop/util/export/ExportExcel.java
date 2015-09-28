package com.gotop.util.export;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.eos.foundation.common.io.FileUtil;
import com.eos.foundation.eoscommon.ConfigurationUtil;
import com.eos.runtime.core.ApplicationContext;
import com.gotop.tyjg.datadictionary.model.DictType;
import com.gotop.util.file.UtilConfiguration;
import com.gotop.util.security.ForUtil;

import commonj.sdo.DataObject;



/**
 * <p>Title: 报表生成通用类</p>
 *
 * <p>Description: Description: 每个sheet显示5000条数据，如果数据集合大于5000，则分开显示</p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-3-4
 *
 * @version 1.0
 **/
public class ExportExcel {
	private int sheetDataSize =5000;// 每个sheet显示的数据量

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制， 可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL的形式输出到指定IO设备上
	 * 
	 * @param headers
	 *            表格的标题信息
	 * 
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。
	 *            此方法支持的javabean属性的数据类型String。 下个版本改进支持多类型的
	 * 
	 * @param sheetName
	 *            EXCEL表格sheet的名字
	 * 
	 * 
	 * @param search
	 *            显示要查询的条件，如果没有设置为NULL
	 * 
	 * 
	 * @param out
	 *            输出流对象
	 * @throws Exception
	 */
	public void exportExcel(String[] headers, List dataset, String sheetName,
			String search, String unit, OutputStream out) throws Exception {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();	
		//需要多少张表格
		int pageNum = 0;
		if (dataset.size() % sheetDataSize == 0) {
			pageNum = dataset.size() / sheetDataSize;
		} else {
			pageNum = dataset.size() / sheetDataSize + 1;
		}
		int sheetNum = sheetDataSize;
		int showSheet = 1;
		String tmpSheet = sheetName;
		for (int i = 0; i < pageNum; i++) {
			if (dataset.size() > sheetDataSize) {
				int j = i + 1;
				sheetName = tmpSheet + j;
			}
			if (headers == null && search == null && unit == null) {
				exportExcel(workbook, sheetName, dataset, out, sheetNum,
						showSheet);
			} else {
				exportExcel(workbook, sheetName,search, unit, headers,
						dataset, out, sheetNum, showSheet);
			}
			sheetNum += sheetDataSize;
			showSheet++;
		}
		workbook.write(out);

	}

	/**
	 * 生成工作薄
	 * @param headers
	 * @param dataset
	 * @param sheetName
	 * @param search
	 * @param unit
	 * @param totals
	 *            合计信息 没有设为NULL
	 * @param out
	 * @throws Exception
	 */
	public void exportExcel(String[] headers, List dataset, String sheetName,
			String search, String unit, String[] totals, OutputStream out)
			throws Exception {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();	
		int pageNum = 0;
		if (dataset.size() % sheetDataSize == 0) {
			pageNum = dataset.size() / sheetDataSize;
		} else {
			pageNum = dataset.size() / sheetDataSize + 1;
		}
		int sheetNum = sheetDataSize;
		int showSheet = 1;
		String tmpSheet = sheetName;
		for (int i = 0; i < pageNum; i++) {
			if (dataset.size() > sheetDataSize) {
				int j = i + 1;
				sheetName = tmpSheet + j;
			}
			if (totals != null) {
				exportExcel(workbook, sheetName, search, unit, headers,
						dataset, out, sheetNum, showSheet, totals);
			}
			sheetNum += sheetDataSize;
			showSheet++;
		}
		workbook.write(out);

	}

	/**
	 * 重载方法，生成不带表头的报表
	 * 
	 * @param sheetName
	 * @param dataset
	 * @param out
	 */
	private void exportExcel(HSSFWorkbook workbook, String sheetName,
			List dataset, OutputStream out, int sheetNum, int showSheet) {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置样式
		HSSFCellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// dataStyle.setWrapText(true);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 遍历集合数据，产生数据行
		Iterator it = dataset.iterator();
		HSSFRow row;
		int index = 0;
		int j = (showSheet - 1) * sheetDataSize;
		if (dataset.size() > sheetDataSize) {
			// 遍历集合数据，产生数据行
			for (; j < sheetNum && j < dataset.size(); j++) {
				row = sheet.createRow(index);
				index++;
				Object t = dataset.get(j);
				getBeanValue(row, t, dataStyle, sheet);
			}
		} else {
			while (it.hasNext()) {
				row = sheet.createRow(index);
				index++;
				Object t = (Object) it.next();
				getBeanValue(row, t, dataStyle, sheet);
			}

		}

	}

	/**
	 * @param workbook 要产生的工作薄
	 * @param sheetName 表格名称
	 * @param search 查询条件
	 * @param unit 附加信息
	 * @param headers 标题栏
	 * @param dataset 数据集合
	 * @param out 输出流
	 * @param sheetNum 表面号
	 * @param showSheet 
	 */
	private void exportExcel(HSSFWorkbook workbook, String sheetName,
			String search, String unit, String[] headers, List dataset,
			OutputStream out, int sheetNum, int showSheet) {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);//222222
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 设置样式
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// normalStyle.setWrapText(true);
		// 设置样式
		HSSFCellStyle searchStyle = workbook.createCellStyle();
		searchStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// searchStyle.setWrapText(true);
		// 设置样式
		HSSFCellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// dataStyle.setWrapText(true);
		// 合并表格
		short regionNum = (short) (headers.length - 1);// 合并的列数
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) regionNum));

		// 显示标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell2 = row1.createCell((short) 0);
		cell2.setCellStyle(normalStyle);
		HSSFRichTextString text2 = new HSSFRichTextString(sheetName);
		cell2.setCellValue(text2);
		int nextNum = 2;
		if (search != null && !"".equals(search)) {
			regionNum -= 1;
			sheet.addMergedRegion(new Region(1, (short) 0, 1,(short) regionNum));
			// 显示查询条件
			HSSFRow row2 = sheet.createRow(1);
			HSSFCell cell3 = row2.createCell((short) 0);
			HSSFCell cell4 = row2.createCell((short) (regionNum + 1));
			cell3.setCellStyle(searchStyle);
			cell4.setCellStyle(searchStyle);
			HSSFRichTextString text3 = new HSSFRichTextString(search);// 放入查询信息
			cell3.setCellValue(text3);
			HSSFRichTextString text4 = new HSSFRichTextString(unit);// 放入查询信息
			cell4.setCellValue(text4);
		} else {
			nextNum = 1;
		}
//		if(bankName !=null && !"".equals(bankName)){
//			//制作表格的第一行，进行表格样式的调整
//			//合并第二行1、2格
//			sheet.addMergedRegion(new Region(1,(short) 0,1,(short)1));
//			//合并第二行3、4格
//			sheet.addMergedRegion(new Region(1,(short) 2,1,(short)3));
//			//合并第二行5到倒数第二格
//			regionNum -= 1;
//			sheet.addMergedRegion(new Region(1, (short) 4, 1,(short) (regionNum-1)));
//			HSSFRow row2 = sheet.createRow(1);
//			HSSFCell cell3=row2.createCell((short) 0);
//			HSSFCell cell4=row2.createCell((short) 2);
//			HSSFCell cell5=row2.createCell((short) regionNum);
//			HSSFCell cell6=row2.createCell((short) (regionNum+1));
//			cell3.setCellStyle(searchStyle);
//			cell4.setCellStyle(searchStyle);
//			cell5.setCellStyle(searchStyle);
//			cell6.setCellStyle(searchStyle);
//			HSSFRichTextString text3 = new HSSFRichTextString(bankName);// 放入机构信息
//			cell3.setCellValue(text3);
//			HSSFRichTextString text4 = new HSSFRichTextString(search);//放入报表日期
//			cell4.setCellValue(text4);
//			HSSFRichTextString text5 = new HSSFRichTextString(userName);//放入用户信息
//			cell5.setCellValue(text5);
//			HSSFRichTextString text6 = new HSSFRichTextString(unit);//放入制表时间
//			cell6.setCellValue(text6);
//		}else{
//			nextNum = 1;
//		}
		// 产生表格标题行
		HSSFRow row = sheet.createRow(nextNum);
		if (headers != null && headers.length != 0) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		int index = nextNum;
		int j = (showSheet - 1) * sheetDataSize;
		if (dataset.size() >= sheetDataSize) {
			// 遍历集合数据，产生数据行
			for (; j < sheetNum && j < dataset.size(); j++) {
				index++;
				row = sheet.createRow(index);
				Object t = dataset.get(j);
				getBeanValue(row, t, dataStyle, sheet);
			}

		} else {
			for (int n = 0; n < dataset.size(); n++) {
				index++;
				row = sheet.createRow(index);
				Object t = dataset.get(n);
				getBeanValue(row, t, dataStyle, sheet);
			}

		}

	}

	/**
	 * 重载方法，生成尾行带合计信息的报表 合并前俩列放“合计”
	 * 
	 * @param totals
	 *            合计信息
	 */
	private void exportExcel(HSSFWorkbook workbook, String sheetName,
			String search, String unit, String[] headers, List dataset,
			OutputStream out, int sheetNum, int showSheet, String[] totals) {
		// 生成一个工作表
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 设置样式
		HSSFCellStyle normalStyle = workbook.createCellStyle();
		normalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// normalStyle.setWrapText(true);
		// 设置样式
		HSSFCellStyle searchStyle = workbook.createCellStyle();
		searchStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// searchStyle.setWrapText(true);
		// 设置样式
		HSSFCellStyle dataStyle = workbook.createCellStyle();
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// dataStyle.setWrapText(true);
		// 合并表格
		short regionNum = (short) (headers.length - 1);// 合并的列数
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) regionNum));//合并操作

		// 显示标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell2 = row1.createCell((short) 0);
		cell2.setCellStyle(normalStyle);
		HSSFRichTextString text2 = new HSSFRichTextString(sheetName);
		cell2.setCellValue(text2);
		int nextNum = 2;
		if (search != null && !"".equals(search)) {
			regionNum -= 1;
			sheet.addMergedRegion(new Region(1, (short) 0, 1,
							(short) regionNum));
			// 显示查询条件
			HSSFRow row2 = sheet.createRow(1);
			HSSFCell cell3 = row2.createCell((short) 0);
			HSSFCell cell4 = row2.createCell((short) (regionNum + 1));
			cell3.setCellStyle(searchStyle);
			cell4.setCellStyle(searchStyle);
			HSSFRichTextString text3 = new HSSFRichTextString(search);// 放入查询信息
			cell3.setCellValue(text3);
			HSSFRichTextString text4 = new HSSFRichTextString(unit);// 放入查询信息
			cell4.setCellValue(text4);
		} else {
			nextNum = 1;
		}
		// 产生表格标题行
		HSSFRow row = sheet.createRow(nextNum);
		if (headers != null && headers.length != 0) {
			for (short i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				HSSFRichTextString text = new HSSFRichTextString(headers[i]);
				cell.setCellValue(text);
			}
		}
		int index = nextNum;
		int j = (showSheet - 1) * sheetDataSize;
		if (dataset.size() >= sheetDataSize) {
			// 遍历集合数据，产生数据行
			for (; j < sheetNum && j < dataset.size(); j++) {
				index++;
				row = sheet.createRow(index);
				Object t = dataset.get(j);
				getBeanValue(row, t, dataStyle, sheet);
			}

		} else {
			for (int n = 0; n < dataset.size(); n++) {
				index++;
				row = sheet.createRow(index);
				Object t = dataset.get(n);
				getBeanValue(row, t, dataStyle, sheet);
			}

		}
		sheet.addMergedRegion(new Region(index + 1, (short) 0, index + 1,
				(short) 1));
		row = sheet.createRow(index + 1);
		HSSFCell celly_0 = row.createCell((short) 0);
		celly_0.setCellStyle(normalStyle);
		celly_0.setCellValue(new HSSFRichTextString("合计"));
		for (int i = 0; i < totals.length; i++) {
			HSSFCell celly_1 = row.createCell((short) (i + 2));
			celly_1.setCellStyle(dataStyle);
			celly_1.setCellValue(new HSSFRichTextString(String
					.valueOf(totals[i])));
		}

	}

	/**
	 * 建每行中的单元格
	 * 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	 * 
	 * @param row
	 *            表格的行数
	 * @param obj
	 *            每个bean对象
	 * @param dataStyle
	 * @param sheet
	 */
	private void getBeanValue(HSSFRow row, Object obj, HSSFCellStyle dataStyle,
			HSSFSheet sheet) {
		Field[] fields = obj.getClass().getDeclaredFields();//获取所有该javabean中申明的字段
		for (short i = 0; i < fields.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(dataStyle);
			Field field = fields[i];
			String fieldName = field.getName();
			String getMethodName = "get"
					+ fieldName.substring(0, 1).toUpperCase()//将字段的首字母转为大写，因get方法第一字母为大写，字段名为小写
					+ fieldName.substring(1);
			try {
				Class tCls = obj.getClass();
				Method getMethod = tCls
						.getMethod(getMethodName, new Class[] {});//根据方法名和参数集合来得到方法
				Object value = getMethod.invoke(obj, new Object[] {});
				String textValue = "";
				if (value != null && !value.equals("")) {
					textValue = value.toString();
				}
				if (textValue.length() >= 11) {
					sheet.setColumnWidth(i, (short) 6000);
				}
				HSSFRichTextString richString = new HSSFRichTextString(
						textValue);
				cell.setCellValue(richString);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String	heard = "学号,姓名,年龄,成绩";
		String[] headers =heard.split(",");
		ExportExcel ex = new ExportExcel();
		List list = new ArrayList();
		String sheetName = "表格名";
		String search = "查询条件";
		String unit = "UNIT";
		String[] totals = {"2","3"};
		for (int i = 0; i < 3; i++) {
			Student ent = new Student();
			list.add(ent);
		}
		FileOutputStream out = null;
		try {
			out = ForUtil.createFileOutputStream("g://aaaa/1.xls");
			ex.exportExcel(headers, list, sheetName, search, unit, totals, out);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
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
	public static String exportExcel(List<DictType> exportObjects,
			Object exportInfo, String templateFilename,
			boolean autoPagination, boolean autoSheet, boolean includeFormula,
			HashMap hm) throws Exception {
		String filename = templateFilename;

		if (filename.indexOf(".xls") == -1) {
			filename += ".xls";
		}

		//临时路径是服务器当前war下面的excel-config目录
		String templateDir = ExportExcel.class.getClassLoader().getResource("").getPath();
        if (templateDir.startsWith("/")) {
			templateDir  = templateDir.substring(1, templateDir.length()-1);
		}

		if (!templateDir.endsWith("/")) {
			templateDir += "/";
		}
		String tempDir = templateDir + "excelFile/";
		File file = ForUtil.createFile(tempDir);
		if (!file.exists()) {
			//创建临时目录
			FileUtil.mkDir(tempDir);
			//file.createNewFile();
		}

		String outputFile = tempDir + generateOutputExcelFile(filename.replace(templateDir, ""));
		ExcelTemplate template = new ExcelTemplate(filename, outputFile);
		template.setIncludeFormula(includeFormula);
		template.setAutoPagination(autoPagination);
		template.setAutoSheet(autoSheet);
		int excelExportMaxnumInt = 5000;
		template.setMaxRow(excelExportMaxnumInt);
		template.generate(exportObjects, exportInfo, hm);
		return outputFile;
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
}
