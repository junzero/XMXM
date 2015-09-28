package com.gotop.util.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FcfReport extends PublicServlet {

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = this.getServletConfig().getServletContext();
		StringBuffer sbuffer = new StringBuffer();
		String contextPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		
		
		List<String>colorList = new ArrayList<String>();
		colorList.add("FF8E46");colorList.add("56B9F9");colorList.add("8BBA00");colorList.add("9D080D");colorList.add("008E8E");colorList.add("D64646");colorList.add("8E468E");colorList.add("588526");
		colorList.add("CAE1FF");colorList.add("CD1076");colorList.add("CD8162");colorList.add("C0FF3E");colorList.add("BDB76B");colorList.add("CD853F");colorList.add("EEB422");colorList.add("EE7942");
		colorList.add("8470FF");colorList.add("5E5E5E");colorList.add("00FA9A");colorList.add("CD661D");colorList.add("D15FEE");colorList.add("EE0000");colorList.add("EE3A8C");colorList.add("588526");
		colorList.add("F0E68C");colorList.add("FF00FF");colorList.add("EECBAD");colorList.add("FF83FA");colorList.add("FFEC8B");colorList.add("FFA54F");colorList.add("FF6EB4");colorList.add("F4A460");
		colorList.add("E0EEE0");colorList.add("DA70D6");colorList.add("CDC673");colorList.add("CD3278");colorList.add("008E8E");colorList.add("D64646");colorList.add("CAE1FF");colorList.add("BBFFFF");
		StringBuilder sql = (StringBuilder)request.getSession().getAttribute("sqlStr");
		Object [] params =(Object [])request.getSession().getAttribute("params");
		List<?> dataSource = null;//??????
		String column = request.getAttribute("column")==null?"":request.getAttribute("column").toString();
		String title = request.getAttribute("title")==null?"":request.getAttribute("title").toString();
		String content = request.getAttribute("content")==null?"":request.getAttribute("content").toString();
		String column_name = request.getAttribute("column_name")==null?"":"-"+request.getAttribute("column_name").toString();
		//xml数据头
		StringBuilder XMLDATA = new StringBuilder("<graph  animation='1'  baseFontSize='12'  hovercapbg='DEDEBE' hovercapborder='889E6D' rotateNames='0' yAxisMaxValue='50' numdivlines='9' divLineColor='' divLineAlpha='' decimalPrecision='0' showAlternateHGridColor='1' AlternateHGridAlpha='30' AlternateHGridColor='' caption='"+title+""+column_name+"' subcaption='"+content+"'formatNumberScale='0'   showNames='1'>");
		Map paramMap =(Map)request.getAttribute("paramMap");
		
		String type = request.getAttribute("type").toString();
		Map<?, ?>xmlMap = new HashMap<String,Object>();
		if(type.equals("1")){
			//xml列头
			XMLDATA.append(" <categories font='Arial' fontSize='13' fontColor='000000'> ");
			for(int i=0;dataSource!=null && i<dataSource.size();i++){
				xmlMap =(Map<?,?>)dataSource.get(i);
				if(xmlMap.get(column)!=null){
					if(xmlMap.get(column).toString().length()>1){
						if(xmlMap.get(column).toString().equals("合计")){
							continue;
						}
						XMLDATA.append(" <category name='"+xmlMap.get(column).toString().substring(0,2)+"'/> ");
					}else{
						XMLDATA.append(" <category name='"+xmlMap.get(column).toString()+"'/> ");
					}
					
				}
			}
			XMLDATA.append(" </categories> ");
			//xml值
				Object [] paramArray = paramMap.keySet().toArray(); 
				Object [] paramValueArray = paramMap.values().toArray();
				for(int k=0;k<paramMap.size();k++){
					XMLDATA.append(" <dataset baseFontSize='15' seriesname='"+paramArray[k]+"' color='"+colorList.get(k)+"' > ");
					for(int i=0;dataSource!=null && i<dataSource.size();i++){
						xmlMap=(Map<?,?>)dataSource.get(i);
						XMLDATA.append("  <set  value='"+xmlMap.get(paramValueArray[k])+"' fontSize='13' /> ");
							
					}
					XMLDATA.append("</dataset>");
				}
		}else if(type.equals("2")){
			String column_pie = request.getAttribute("column_pie")==null?"":request.getAttribute("column_pie").toString();
			for(int i=0;dataSource!=null && i<dataSource.size();i++){
				xmlMap=(Map<?,?>)dataSource.get(i);
				if(xmlMap.get("NAME").toString().equals("合计")){
					continue;
				}
				if(xmlMap.get(column_pie)!=null&&xmlMap.get(column_pie).toString().trim().length()>0){
					long value = Long.parseLong(xmlMap.get(column_pie).toString());
					if(value>0){
						XMLDATA.append("  <set name='"+xmlMap.get("NAME").toString().subSequence(0,2)+"'  value='"+xmlMap.get(column_pie)+"' fontSize='13' color='"+colorList.get(i)+"'/> ");
					}
				}
			}
		}
		XMLDATA.append("</graph>");
		if(dataSource==null){
			dataSource = new ArrayList();
		}
		StringBuffer HTML = new StringBuffer();
		HTML.append("<html>\n");
		HTML.append("	<head>\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/jquery/themes/default/easyui.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/jquery/themes/icon.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_common.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_datelist.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_edit.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_leftnav.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_list.css'> \n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='/managerstyle/css/default_tipbox.css'> \n");
		HTML.append("		<script type='text/javascript' src='" + contextPath + "/FusionCharts/FusionCharts.js'></script> \n");
		//HTML.append("		<object ID='WebBrowser' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>\n");
		HTML.append("		<title>图形报表</title>\n");
		HTML.append("	</head>");
		HTML.append("	<body> ");
		HTML.append("   	<table width='100%' border='0' cellspacing='0' cellpadding='0' align='center'> ");
		HTML.append("			<tr>  ");
		HTML.append("				<td valign='top' class='text' align='center'>  ");
		HTML.append("        			<div id='chartdiv' align='center' style='WIDTH: 1000px;overflow-x:auto;overflow-y:auto;'>FusionCharts. </div> ");
		HTML.append("					<script type='text/javascript'> ");
		HTML.append("						var maxlength = 0; ");
		HTML.append("						var maxfontsize = "+4+"; ");
		HTML.append("						var datesize = "+dataSource.size()+";  ");
		HTML.append("						maxlength=maxfontsize*datesize*7.5;  ");
		HTML.append("						maxlength=maxlength<800?800:maxlength;  ");
		if(type.equals("1")){
			HTML.append("					var chart = new FusionCharts('" + contextPath + "/FusionCharts/FCF_StackedColumn3D.swf', 'ChartId', maxlength, '600'); ");
		}else if(type.equals("2")){
			HTML.append("					var chart = new FusionCharts('" + contextPath + "/FusionCharts/FCF_Pie3D.swf', 'ChartId', maxlength, '600'); ");
		}
		HTML.append("						chart.setDataXML(\""+XMLDATA+"\");		    ");	
		HTML.append("						chart.render('chartdiv'); ");
		HTML.append("					</script> " +
				"					</td> ");
		HTML.append("			</tr> ");
		HTML.append("		</table> ");
		HTML.append("	</body> ");
		HTML.append("</html>");
		HTML.append("");

		response.getWriter().write(HTML.toString());
	}

	public void init() throws ServletException {

	}
}
