package com.gotop.util.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.eos.foundation.common.io.FileUtil;
import com.gotop.util.security.ForUtil;

public class JavaCallLogic {
	/**
	 * 读取excel文件，并解析为可显示的html
	 * @param excelFile
	 * @return
	 */
	public String readExcel(String excelFile) {
		System.out.println("-----excelFile-----" + excelFile);
		StringBuffer sb = new StringBuffer("");
		try {
			//String[][][0]==值    String[][][1]==宽   String[][][2]==高    String[][][3]==rowspan   String[][][4]==colspan String[][][5]==CellType
			Object[][][] excel = POIUtils.readExcelBySheetIndex(excelFile, 0,
					3, 100);
			for (int i = 0; excel != null && i < excel.length; i++) {

				if (excel[i] == null) {
					sb.append("<tr><td></td></tr>");
					continue;
				}
				sb.append("<tr>");
				for (int j = 0; j < excel[i].length; j++) {
					if (excel[i][j] == null
							|| excel[i][j][0] == null
							|| "rowspan colspan null".equals(excel[i][j][0]
									.toString())) {
						sb.append("<td></td>");
					} else {
						sb.append("<td");
						if (excel[i][j][1] != null) {
							sb.append(" width='" + excel[i][j][1] + "'");
						}
						/*if (excel[i][j][2] != null) {
							sb.append(" height='" + excel[i][j][2] + "'");
						}
						if (excel[i][j][3] != null) {
							sb.append(" rowspan='" + excel[i][j][3] + "'");
						}
						if (excel[i][j][4] != null) {
							sb.append(" colspan='" + excel[i][j][4] + "'");
						}
						if (excel[i][j][4] != null) {
							sb.append(" cellType='" + excel[i][j][5] + "'");
						}*/
						sb.append(">" + excel[i][j][0]);
						sb.append("</td>");
					}
				}
				sb.append("</tr>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/**
	 * 生成导入模版
	 * @param fieldsBr
	 * @param excelFile
	 * @return
	 */
	public String importTemplateExcel(String fieldsBr, String excelFile) {

		InputStream inStream = null;
		FileOutputStream fileOut = null;
		String templateFile = "";
		try {
			File fileTemp = ForUtil.createFile(excelFile);
			inStream = ForUtil.createFileInputStream(fileTemp);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("new sheet");

			HSSFCellStyle opStyle = getStyle(wb);

			int rowIndex = 0;

			HSSFRow row = sheet.createRow((short) rowIndex);
			row.setHeight((short) ((20) / ((double) 1 / 24)));

			rowIndex = rowIndex + 1;
			HSSFRow row1 = sheet.createRow((short) rowIndex);
			row1.setHeight((short) ((20) / ((double) 1 / 24)));

			//		String[] field = fieldsBr.split(";");
			//		for (short i = 0; i < field.length; i++) {
			//			String[] fie = field[i].split(":");
			//			createCell(row, (short) i, (short) -1, opStyle, fie[0]);
			//			createCell(row1, (short) i, (short) -1, opStyle, "#" + fie[1]);
			//			if (fie.length > 2 && StringUtils.isNotBlank(fie[2]) && !fie[2].equals("undefined")) {
			//				sheet.setColumnWidth(i, (short) (Short.valueOf(fie[2]) * 30));
			//			}
			//		}

			//ID:roleid:100:;名称:rolename:100:;类型:roletype:100:;说明:roledesc:100:;
			if (StringUtils.isNotBlank(fieldsBr)) {
				String[] fieldArra = fieldsBr.split(";;");
				for (short i = 0; i < fieldArra.length; i++) {
					String fields = fieldArra[i];
					String[] field = fields.split(";");
					for (int j = 0; j < field.length; j++) {
						if (field[j] != null) {
							String[] fie = field[j].split(":");
							//第一行为#字段名
							//第二行为字段标题，因此内容读取从startRow+2
							createCell(row, (short) j, (short) -1, opStyle, "#"
									+ fie[1]);
							createCell(row1, (short) j, (short) -1, opStyle,
									fie[0]);
						}
					}

				}
			}

			rowIndex = rowIndex + 1;
			//String[][][0]==值    String[][][1]==宽   String[][][2]==高    String[][][3]==rowspan   String[][][4]==colspan String[][][5]==CellType
			Object[][][] excel = POIUtils.readExcelBySheetIndex(excelFile, 0,
					0, 100);
			for (int i = 0; excel != null && i < excel.length; i++) {
				if (excel[i] == null) {
					continue;
				}
				HSSFRow rowBody = sheet.createRow((short) rowIndex + i);
				rowBody.setHeight((short) ((20) / ((double) 1 / 24)));
				for (int j = 0; excel[i] != null && j < excel[i].length; j++) {
					if (excel[i][j] == null
							|| excel[i][j][0] == null
							|| "rowspan colspan null".equals(excel[i][j][0]
									.toString())) {
						createCell(rowBody, (short) j, (short) -1, null, "");
					} else {
						createCell(rowBody, (short) j, (short) -1, null,
								excel[i][j][0].toString());
						System.out.println("-----excel[i][j][0]------"
								+ excel[i][j][0]);
					}
				}
			}

			//生成文件
			String filename = new Date().getTime() + ".xls";
			templateFile = getFilePath(filename);
			//			templateFile += "C:\\"+filename;
			fileOut = ForUtil.createFileOutputStream(templateFile);
			wb.write(fileOut);
			System.out.println("-----templateFile------" + templateFile);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (fileOut != null) {
					fileOut.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return templateFile;
	}
	/**
	 * XLS 样式
	 * @param workbook
	 * @return
	 */
	public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		//
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		//		style.setFillForegroundColor(HSSFColor.RED.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		return style;
	}
	/**
	 * 设置方式内容
	 * @param row 指定第几行的对像
	 * @param column 指定第几列
	 * @param align 对齐方式
	 * @param cellStyle 样式,需workbook.createCellStyle();的
	 * @param value 值
	 */
	public static void createCell(HSSFRow row, short column, short align,
			HSSFCellStyle cellStyle, String value) {
		HSSFCell cell = row.createCell(column);
//		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		if (value == null) {
			cell.setCellValue(new HSSFRichTextString(""));
		} else {
			cell.setCellValue(new HSSFRichTextString(value));
		}
		if (align != -1 && cellStyle != null) {
			cellStyle.setAlignment(align);
		}
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
	}
	/**
	 * 模块路径
	 * @param filename 
	 * @return
	 * @throws Exception
	 */
	public String getFilePath(String filename) throws Exception {
        
		//临时路径是服务器当前war下面的excel-config目录
		String templateDir = this.getClass().getClassLoader().getResource("").getPath();
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

		String templateFile = templateDir + filename;

		return templateFile;
	}
	
	/**
	 * 生成模块
	 * @param params[0]=text,[1]=value,[2]=width,[3]=colspan,[4]=rowspan
	 * @return
	 * @throws Exception 
	 */
	public String createTemplate(String fields) throws Exception {
		FileOutputStream fileOut = null;
		
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("new sheet");
			
			HSSFCellStyle opStyle = getStyle(wb);
			HSSFRow row = sheet.createRow((short) 0);
			row.setHeight((short) ((20) / ((double) 1 / 24)));
			HSSFRow row1 = sheet.createRow((short) 1);
			row1.setHeight((short) ((20) / ((double) 1 / 24)));
			String[] field = fields.split(";");
			for (short i = 0; i < field.length; i++) {
				String[] fie = field[i].split(":");
				createCell(row, (short) i, (short) -1, opStyle, fie[0]);
				createCell(row1, (short) i, (short) -1, opStyle, "#" + fie[1]);
				if (fie.length > 2 && StringUtils.isNotBlank(fie[2])
						&& !fie[2].equals("undefined")) {
					sheet.setColumnWidth(i, (short) (Short.valueOf(fie[2]) * 30));
				}
			}
			String filename = new Date().getTime() + ".xls";
			String templateFile = getFilePath(filename);
		try {
			fileOut = ForUtil.createFileOutputStream(templateFile);
			wb.write(fileOut);
			System.out.println("---templateFile---" + templateFile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fileOut!=null){
				fileOut.close();
			}
		}
		return templateFile;
	}
}
