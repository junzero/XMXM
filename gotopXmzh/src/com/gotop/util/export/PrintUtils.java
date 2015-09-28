package com.gotop.util.export;

import java.awt.Color;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;

import com.gotop.util.dataSource.ConUtils;

public class PrintUtils {

	public static void main(String[] arg){
		try {
			(new PrintUtils()).getJasperDesign();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JasperDesign getJasperDesign() throws Exception {
		JasperDesign jasperDesign = new JasperDesign();

		JRDesignQuery query = new JRDesignQuery();
		query.setText("select userid,OPERATORNAME name,11 age,1 sex,password,status department from ac_operator where rownum<50");
		jasperDesign.setQuery(query);
		
		createTemplate1(jasperDesign);
		exportReportFile(jasperDesign);
		
		return jasperDesign;
	}
	/**
	 * 创建模块文件
	 * @param jasperDesign
	 * @param normalStyle
	 */
	private void createTemplate(JasperDesign jasperDesign) throws Exception{
		JRDesignStyle normalStyle = setReportSytle(jasperDesign);
		// Title
		JRDesignBand band = new JRDesignBand();
		band.setHeight(40);
		JRDesignStaticText staticText = new JRDesignStaticText();

		staticText.setX(200);
		staticText.setY(0);
		staticText.setWidth(200);
		staticText.setHeight(40);

		staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT); // 设置文本的对齐方式
		staticText.setStyle(normalStyle);
		staticText.setText("福建国通信息科技有限公司审计报表");
		JRLineBox lbx1 = staticText.getLineBox();
		lbx1.getLeftPen().setLineWidth(1);
		lbx1.getBottomPen().setLineWidth(1);
		lbx1.getRightPen().setLineWidth(1);
		lbx1.getTopPen().setLineWidth(1);
		staticText.setWidth(400);
		band.addElement(staticText);
		jasperDesign.setTitle(band);

		// Page header
		band = new JRDesignBand();
		// band.setHeight(20);
		band.setHeight(0);
		jasperDesign.setPageHeader(band);

		// Column header
		band = new JRDesignBand();
		band.setHeight(0);
		jasperDesign.setColumnHeader(band);
		// detail

		// 开始添加列字段
		String[] headers = { "USERID", "NAME", "AGE", "SEX", "PASSWORD", "DEPARTMENT" };
		String[] alias = { "USERID", "NAME", "AGE", "SEX", "PASSWORD", "DEPARTMENT" };
		int Y = 20;
		int columnHeaderfontSize = 10;
		int fontSize = 8;
		int textHeight = 20;
		int textWidth = 180;
		int detailHeight = 20;
		JRDesignBand detail = new JRDesignBand();
		detail.setHeight(detailHeight);
		
		for (int i = 0; i < headers.length; i++) {
			JRDesignStaticText jrstaticText = new JRDesignStaticText();
			jrstaticText.setText(headers[i]);

			jrstaticText.setFontSize(columnHeaderfontSize);
			jrstaticText.setHeight(textHeight);
			jrstaticText.setWidth(textWidth);
			
			jrstaticText.setBold(true);

			jrstaticText.setX(textWidth * i);
			jrstaticText.setPdfFontName("STSong-Light");
			jrstaticText.setPdfEmbedded(true);
			jrstaticText.setPdfEncoding("UniGB-UCS2-H");
			jrstaticText.setForecolor(Color.black);
			jrstaticText.setBackcolor(Color.yellow);
			
			JRLineBox lbx = jrstaticText.getLineBox();
//			lbx.setTopPadding(1);
			lbx.getLeftPen().setLineWidth(1);
			lbx.getBottomPen().setLineWidth(1);
			lbx.getRightPen().setLineWidth(1);
			lbx.getTopPen().setLineWidth(1);
//			lbx.getLeftPen().setLineColor(Color.black);
			detail.addElement(jrstaticText);

			// 定义字段，注册字段
			JRDesignField field = new JRDesignField();
			field.setName(alias[i]);
			field.setValueClass(String.class);
			jasperDesign.addField(field);

			// add text fields for displaying fields
			JRDesignTextField textField = new JRDesignTextField();
			JRDesignExpression expression = new JRDesignExpression();
			expression.setText("$F{" + alias[i] + "}");
			textField.setExpression(expression);
			textField.setFontSize(fontSize);
			textField.setHeight(textHeight);
			textField.setWidth(textWidth);
//			textField.setY(20); 
			textField.setX(textWidth * i);
			textField.setPdfFontName("STSong-Light");
			textField.setPdfEmbedded(true);
			textField.setPdfEncoding("UniGB-UCS2-H");
			textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
			textField.setForecolor(Color.black);
			textField.setStretchWithOverflow(true);
			
			lbx = textField.getLineBox();
//			lbx.setTopPadding(1);
			lbx.getLeftPen().setLineWidth(1);
			lbx.getBottomPen().setLineWidth(1);
			lbx.getRightPen().setLineWidth(1);
			lbx.getTopPen().setLineWidth(1);
			detail.addElement(textField);
		}

		((JRDesignSection) jasperDesign.getDetailSection()).addBand(detail);

		// Column footer
		band = new JRDesignBand();
		band.setHeight(0);
		jasperDesign.setColumnFooter(band);
		// Page footer
		band = new JRDesignBand();
		band.setHeight(20);

		staticText = new JRDesignStaticText();
		staticText.setX(0);
		staticText.setY(0);
		staticText.setWidth(100);
		staticText.setHeight(20);
		staticText.setPdfFontName("STSong-Light");
		staticText.setPdfEmbedded(true);
		staticText.setPdfEncoding("UniGB-UCS2-H");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		staticText.setText(sdf.format(new Date()));
		band.addElement(staticText);

		JRDesignTextField textField = new JRDesignTextField();
		textField.setX(450);
		textField.setY(0);
		textField.setWidth(100);
		textField.setHeight(20);
		textField.setPdfFontName("STSong-Light");
		textField.setPdfEmbedded(true);
		textField.setPdfEncoding("UniGB-UCS2-H");
		JRDesignExpression expression = new JRDesignExpression();
		expression = new JRDesignExpression();
		expression.setText("$V{PAGE_NUMBER}");

		textField.setExpression(expression);
		band.addElement(textField);
		jasperDesign.setPageFooter(band);

		System.out.println(expression.getText());
		// Summary
		band = new JRDesignBand();
		band.setHeight(0);

		jasperDesign.setSummary(band);

	}

	/**
	 * 创建模块文件
	 * @param jasperDesign
	 * @param normalStyle
	 */
	private void createTemplate1(JasperDesign jasperDesign) throws Exception{
		JRDesignStyle normalStyle = setReportSytle(jasperDesign);
		// Title
		JRDesignBand band = new JRDesignBand();
		band.setHeight(40);
		JRDesignStaticText staticText = new JRDesignStaticText();
		staticText.setX(200);
		staticText.setY(0);
		staticText.setWidth(200);
		staticText.setHeight(40);
		
		

		staticText.setHorizontalAlignment(HorizontalAlignEnum.LEFT); // 设置文本的对齐方式
		JRLineBox lbx1 = staticText.getLineBox();
		lbx1.getLeftPen().setLineWidth(1);
		lbx1.getBottomPen().setLineWidth(1);
		lbx1.getRightPen().setLineWidth(1);
		lbx1.getTopPen().setLineWidth(1);
		staticText.setText("福建国通信息科技有限公司审计报表");
		staticText.setWidth(200);
		band.addElement(staticText);
		jasperDesign.setTitle(band);

		if(1==1){
			return;
		}
		// Page header
		JRDesignBand pageBand = new JRDesignBand();
		pageBand.setHeight(20);
//		band.setHeight(0);
		

		// Column header
		band = new JRDesignBand();
		band.setHeight(0);
		jasperDesign.setColumnHeader(band);
		// detail

		// 开始添加列字段
		String[] headers = { "USERID", "NAME", "AGE", "SEX", "PASSWORD", "DEPARTMENT" };
		String[] alias = { "USERID", "NAME", "AGE", "SEX", "PASSWORD", "DEPARTMENT" };
		int Y = 20;
		int columnHeaderfontSize = 10;
		int fontSize = 8;
		int textHeight = 20;
		int textWidth = 180;
		int detailHeight = 20;
		JRDesignBand detail = new JRDesignBand();
		detail.setHeight(detailHeight);
		
		for (int i = 0; i < headers.length; i++) {
			JRDesignStaticText jrstaticText = new JRDesignStaticText();
			jrstaticText.setText(headers[i]);

			jrstaticText.setFontSize(columnHeaderfontSize);
			jrstaticText.setHeight(textHeight);
			jrstaticText.setWidth(textWidth);
			
			jrstaticText.setBold(true);

			jrstaticText.setX(textWidth * i);
			jrstaticText.setPdfFontName("STSong-Light");
			jrstaticText.setPdfEmbedded(true);
			jrstaticText.setPdfEncoding("UniGB-UCS2-H");
			jrstaticText.setForecolor(Color.black);
			jrstaticText.setBackcolor(Color.yellow);
			
			JRLineBox lbx = jrstaticText.getLineBox();
//			lbx.setTopPadding(1);
			lbx.getLeftPen().setLineWidth(1);
			lbx.getBottomPen().setLineWidth(1);
			lbx.getRightPen().setLineWidth(1);
			lbx.getTopPen().setLineWidth(1);
//			lbx.getLeftPen().setLineColor(Color.black);
			pageBand.addElement(jrstaticText);

			// 定义字段，注册字段
			JRDesignField field = new JRDesignField();
			field.setName(alias[i]);
			field.setValueClass(String.class);
			jasperDesign.addField(field);

			// add text fields for displaying fields
			JRDesignTextField textField = new JRDesignTextField();
			JRDesignExpression expression = new JRDesignExpression();
			expression.setText("$F{" + alias[i] + "}");
			textField.setExpression(expression);
			textField.setFontSize(fontSize);
			textField.setHeight(textHeight);
			textField.setWidth(textWidth);
//			textField.setY(20); 
			textField.setX(textWidth * i);
			textField.setPdfFontName("STSong-Light");
			textField.setPdfEmbedded(true);
			textField.setPdfEncoding("UniGB-UCS2-H");
			textField.setHorizontalAlignment(HorizontalAlignEnum.LEFT);
			textField.setForecolor(Color.black);
			textField.setStretchWithOverflow(true);
			
			lbx = textField.getLineBox();
//			lbx.setTopPadding(1);
			lbx.getLeftPen().setLineWidth(1);
			lbx.getBottomPen().setLineWidth(1);
			lbx.getRightPen().setLineWidth(1);
			lbx.getTopPen().setLineWidth(1);
			detail.addElement(textField);
		}

		((JRDesignSection) jasperDesign.getDetailSection()).addBand(detail);
		jasperDesign.setPageHeader(pageBand);
	}
	
	private JRDesignStyle setReportSytle(JasperDesign jasperDesign)throws JRException {
		jasperDesign.setName("SampleReport");
		jasperDesign.setPageWidth(595);
		jasperDesign.setPageHeight(842);
		jasperDesign.setColumnWidth(335);
		jasperDesign.setColumnSpacing(0);

		jasperDesign.setLeftMargin(30);
		jasperDesign.setRightMargin(30);
		jasperDesign.setTopMargin(20);
		jasperDesign.setBottomMargin(20);
		// whenNoDataType="NoPages"
		jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.BLANK_PAGE);
		// isTitleNewPage="false"
		jasperDesign.setTitleNewPage(false);
		// isSummaryNewPage="false"
		jasperDesign.setSummaryNewPage(false);
		// jasperDesign.setOrientation(JRReport.ORIENTATION_PORTRAIT);
		// jasperDesign.setPrintOrder(JRReport.PRINT_ORDER_VERTICAL);

		JRDesignStyle normalStyle = new JRDesignStyle();
		normalStyle.setName("Arial_Normal");
		normalStyle.setDefault(true);
		normalStyle.setFontName("Arial");
		normalStyle.setFontSize(16);
		normalStyle.setPdfFontName("STSong-Light");
		normalStyle.setPdfEncoding("UniGB-UCS2-H");
		normalStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(normalStyle);

		JRDesignStyle boldStyle = new JRDesignStyle();
		boldStyle.setName("Arial_Bold");
		boldStyle.setFontName("Arial");
		boldStyle.setFontSize(12);
		boldStyle.setBold(true);
		boldStyle.setPdfFontName("STSong-Light");
		boldStyle.setPdfEncoding("UniGB-UCS2-H");
		boldStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(boldStyle);

		JRDesignStyle italicStyle = new JRDesignStyle();
		italicStyle.setName("Arial_Italic");
		italicStyle.setFontName("Arial");
		italicStyle.setFontSize(12);
		italicStyle.setItalic(true);
		italicStyle.setPdfFontName("STSong-Light");
		italicStyle.setPdfEncoding("UniGB-UCS2-H");
		italicStyle.setPdfEmbedded(true);
		jasperDesign.addStyle(italicStyle);
		return normalStyle;
	}
	/**
	 * 导出信息
	 * @param jasperDesign
	 * @throws Exception
	 */
	private void exportReportFile(JasperDesign jasperDesign) throws Exception{

		JasperReport jasperReport = JasperCompileManager .compileReport(jasperDesign);
		Map parameters = new HashMap();
		Connection connection = null;
		JasperPrint jasperPrint = null;
		try{
			connection = ConUtils.getConn();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
		}catch(Exception e){
			throw e;
		}finally{
			if(connection!=null){
				connection.close();
			}
		}

//		JasperExportManager.exportReportToHtmlFile(jasperPrint, "F:/temp/temp7/ll//test3.html");
//		JasperExportManager.exportReportToPdfFile(jasperPrint, "F:/temp/temp7/ll//test3.pdf");
//		JasperExportManager.exportReportToXmlFile(jasperPrint, "F:/temp/temp7/ll//test3.xml", false);
		
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "F:/temp/temp7/ll//static.xls");
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
		exporter.exportReport();
	}
}
