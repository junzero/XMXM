/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2009-4-24
 *******************************************************************************/

package com.gotop.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.eos.data.serialize.XMLSerializer;
import com.eos.foundation.common.io.FileUtil;
import com.eos.system.annotation.Bizlet;
import com.eos.system.utility.XmlUtil;
import com.gotop.util.security.ForUtil;

import commonj.sdo.DataObject;

/**
 * TODO 此处填写 class 信息
 *
 * @author lz
 * @date 2009-04-24 11:05:23
 */
/*
 * 修改历史
 * $Log$ 
 */
@Bizlet("")
public class JavaCallLogic {
	/**
	 * 生成模块
	 * @param params[0]=text,[1]=value,[2]=width,[3]=colspan,[4]=rowspan
	 * @return
	 * @throws Exception 
	 */
	public static String createTemplate(String fields) throws Exception {
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
			if(fie.length>5 && StringUtils.isNotBlank(fie[5])){
				fie[1] += "《"+fie[5];
			}
			createCell(row1, (short) i, (short) -1, opStyle, "#" + fie[1]);
			if (fie.length > 2 && StringUtils.isNotBlank(fie[2])
					&& !fie[2].equals("undefined")) {
				sheet.setColumnWidth(i, (short) (Short.valueOf(fie[2]) * 36));
			}
		}
		String filename = new Date().getTime() + ".xls";
		String templateFile = getFilePath(filename);
		try {
			fileOut = ForUtil.createFileOutputStream(templateFile);
			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fileOut!=null){
				fileOut.close();
			}
		}
		return filename;
	}
    /**
     * 生成模块
     * @param params[0]=text,[1]=value,[2]=width,[3]=colspan,[4]=rowspan
     * @return
     * @throws Exception 
     */
    public static String createTemplate(String fields,int numSheet,int dataNum) throws Exception {

        HSSFWorkbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = null;
        for(int i=0;i<numSheet;i++){
            
            HSSFSheet sheet = wb.createSheet("sheet"+i);
            
            HSSFCellStyle opStyle = getStyle(wb);
            
            HSSFRow row = sheet.createRow((short) 0);
            row.setHeight((short) ((20) / ((double) 1 / 24)));
            HSSFRow row1 = sheet.createRow((short) 1);
            row1.setHeight((short) ((20) / ((double) 1 / 24)));
            
            String[] field = fields.split(";");
            for (short j = 0; j < field.length; j++) {
                String[] fie = field[j].split(":");
                createCell(row, (short) j, (short) -1, opStyle, fie[0]);
                if(fie.length>5 && StringUtils.isNotBlank(fie[5])){
                    fie[1] += "《"+fie[5];
                }
                createCell(row1, (short) j, (short) -1, opStyle, "#" + fie[1]);
                if (fie.length > 2 && StringUtils.isNotBlank(fie[2])
                        && !fie[2].equals("undefined")) {
                    sheet.setColumnWidth(j, (short) (Short.valueOf(fie[2]) * 36));
                }
            }
        }
        String filename = new Date().getTime() + ".xls";
        String templateFile = getFilePath(filename);
        try {
	    	 fileOut = ForUtil.createFileOutputStream(templateFile);
	         wb.write(fileOut);
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			if(fileOut!=null){
				 fileOut.close();
			}
		}
        return filename;
    }
	/**
	 * XLS 样式
	 * @param workbook
	 * @return
	 */
	public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
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
	@Bizlet("")
	public static String getFilePath(String filename) throws Exception {

		//临时路径是服务器当前war下面的excel-config目录
		String templateDir = getSystemPath();
		return templateDir + filename;
	}

	/**
	 * 解码
	 * @param text
	 * @return
	 */
	public String getXmlEncode(Object text) {
		if (text == null || text.equals("")) {
			return "";
		}
		String textNew = text.toString();
		textNew.replaceAll("&#x0;", " ");
		textNew.replaceAll("&amp;;", "&");
		textNew.replaceAll("&lt;", "<");
		textNew.replaceAll("&gt;", ">");
		textNew.replaceAll("&#x0D;", "\r");
		textNew.replaceAll("&quot;", "\"");
		textNew.replaceAll("&apos;", "\'");

		return textNew;
	}

	/**
	 * @param param
	 * @return
	 */
	@Bizlet("")
	public Document getXmlByString(String param) {
		Document document = null;
		System.out.println("==document0==" + document);
		param = getXmlEncode(param);
		try {
			System.out.println("==document1==" + document);
			document = XmlUtil.parseString(param);
			System.out.println("==document2==" + document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("==document3==" + document);
		return document;
	}
	/**
	 * 将字符串转换为DataObject对象
	 * @param fieldID 需要转换的字符
	 * @param entityType 转化为的类型
	 * @param xpath 访问路径
	 * @return
	 */
	@Bizlet("")
	public Object[] transitionDO(String paramBuffer, String entityType,
			String xpath) {

		if (StringUtils.isBlank(paramBuffer)) {
			return null;
		}

		paramBuffer = getXmlEncode(paramBuffer);

		DataObject obj = null;
		Object[] doj = null;

		try {
			//				fieldID = "<root><data><QLExportBean><ListData><CodeHeight>25</CodeHeight><ListTH><Colspan>1</Colspan><Rowspan>1</Rowspan><Value>选择 </Value><CodeValue>选择 </CodeValue><CellType>1</CellType><CodeWidth>165</CodeWidth></ListTH><ListTH><Colspan>1</Colspan><Rowspan>1</Rowspan><Value>orgcode </Value><CodeValue>orgcode </CodeValue><CellType>1</CellType><CodeWidth>242</CodeWidth></ListTH><ListTH><Colspan>1</Colspan><Rowspan>1</Rowspan><Value>orgname </Value><CodeValue>orgname </CodeValue><CellType>1</CellType><CodeWidth>259</CodeWidth></ListTH><ListTH><Colspan>1</Colspan><Rowspan>1</Rowspan><Value>orgleader </Value><CodeValue>orgleader </CodeValue><CellType>1</CellType><CodeWidth>276</CodeWidth></ListTH></ListData></QLExportBean></data><params><param><key>_eosFlowAction</key><value>pageQuery</value></param><param><key>criteria/_orderby[1]/_sort</key><value>asc</value></param><param><key>criteria/_orderby[1]/_property</key><value>id</value></param><param><key>criteria/_expr[3]/_op</key><value>=</value></param><param><key>criteria/_expr[4]/_op</key><value>=</value></param><param><key>criteria/_entity</key><value>com.primeton.exp1.test.Test</value></param><param><key>criteria/_expr[1]/_op</key><value>=</value></param><param><key>criteria/_expr[2]/_op</key><value>=</value></param><param><key>page/begin</key><value>0</value></param><param><key>page/length</key><value>10</value></param><param><key>page/count</key><value>4996</value></param><param><key>page/isCount</key><value>true</value></param><param><key>select_objs[1]/id</key><value>null</value></param><param><key>select_objs[2]/id</key><value>null</value></param><param><key>select_objs[3]/id</key><value>7</value></param><param><key>select_objs[4]/id</key><value>8</value></param><param><key>select_objs[5]/id</key><value>null</value></param><param><key>select_objs[6]/id</key><value>null</value></param><param><key>select_objs[7]/id</key><value>null</value></param><param><key>select_objs[8]/id</key><value>null</value></param><param><key>select_objs[9]/id</key><value>null</value></param><param><key>select_objs[10]/id</key><value>null</value></param><param><key>_eosFlowKey</key><value></value></param></params><selectTable><selectObj><param><key>id</key><value>7</value></param></selectObj><selectObj><param><key>id</key><value>8</value></param></selectObj></selectTable></root>";
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					paramBuffer)));

			if (StringUtils.isBlank(xpath)) {
				xpath = "root/selectTable/selectObj";
			}
			NodeList selectObj = XmlUtil.findNodes(document, xpath);
			doj = new Object[selectObj.getLength()];

			for (int j = 0; selectObj != null && j < selectObj.getLength(); j++) {
				String param = "<select_objs __type='sdo:" + entityType + "'>";
				Node select = selectObj.item(j);
				NodeList params = XmlUtil.findNodes(select, "param");
				for (int k = 0; k < params.getLength(); k++) {
					String name = XmlUtil.getNodeValue(params.item(k), "key");
					String value = XmlUtil
							.getNodeValue(params.item(k), "value");
					param += "<" + name + ">" + value + "</" + name + ">";
				}
				param += "</select_objs>";

				XMLSerializer xmls = new XMLSerializer();

				obj = (DataObject) xmls.unmarshall(param);

				doj[j] = obj;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doj;
	}
	@Bizlet("")
	public static String getSystemPath(){
		String classPath = JavaCallLogic.class.getResource("").getFile();
		File file = ForUtil.createFile(classPath);
		String path = file.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParent();     //    /WEB-INF/classes/org.gocom.abframe.tools
		if (!path.endsWith("/")) {
			path += "/";
		}
		String tempDir = path + "temp/";
		File files = ForUtil.createFile(tempDir); 
		if (!files.exists()) {
			try {
				FileUtil.mkDir(tempDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return tempDir;
	}
	public static void main(String[] arg) throws Exception {
		String entityName = "sdo:com.primeton.purview.privilege.AcRole";
		if (1 == 1) {

		}

		String paramBuffer = "<QLExportItem>sfdsfdfsdf</QLExportItem>";

		int start = paramBuffer.indexOf("<QLExportItem>");
		int end = paramBuffer.indexOf("</QLExportItem>");
		String str1 = paramBuffer.substring(start, end
				+ "</QLExportItem>".length());
		System.out.println("--str--" + str1);

		if (1 == 1) {
			return;
		}
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();

			String str = "tempFormParamLZ/dictType/_expr[2]/dicttypeid=DU&tempFormParamLZ/dictType/_expr[3]/dicttypename=&tempFormParamLZ/dictType/_expr[1]/parentid=&tempFormParamLZ/dictType/_expr[1]/_processNullValue=true&tempFormParamLZ/dictType/_expr[2]/_op=like&tempFormParamLZ/dictType/_expr[2]/_likeRule=all&tempFormParamLZ/dictType/_expr[3]/_op=like&tempFormParamLZ/dictType/_expr[3]/_likeRule=all&tempFormParamLZ/ako=重新导出&tempFormParamLZ/_eosFlowKey=822454b9-75c1-439d-afc6-000b64dac443.view0";

			Document doc = builder
					.parse(new InputSource(new StringReader(str)));

			System.out.println(doc.getChildNodes().getLength());

			System.out.println();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
