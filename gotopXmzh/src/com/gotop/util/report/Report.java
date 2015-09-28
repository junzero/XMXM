package com.gotop.util.report;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import com.gotop.util.dataSource.ConUtils;
import com.gotop.util.security.ForUtil;

public class Report extends PublicServlet {

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext context = this.getServletConfig().getServletContext();
		JasperPrint jasperPrint = (JasperPrint)session.getAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE);
		ReportSource rpts = (ReportSource) request.getAttribute("ReportSource");
		
		if(request.getParameter("reload")!= null || jasperPrint==null || rpts!=null){
			try {
				File reportFile = ForUtil.createFile(context.getRealPath("/report/"+rpts.getFileName()));
				if (!reportFile.exists()) throw new JRRuntimeException("报表文件 "+rpts.getFileName()+" 没有找到!!");
				JasperReport jasperReport = (JasperReport)JRLoader.loadObject(reportFile.getPath());
				Map parameters = new HashMap();
				parameters.put("ReportTitle", rpts.getReportTitle());
				String resourcePath = context.getRealPath("/report/")+"\\resource\\";
				//String resourcePath = "http://localhost:7001/yzletters/report/resource/";
				//System.out.println("resourcePath="+resourcePath);
				parameters.put("p_rptsrc", resourcePath);
				DataSource ds = new DataSource();
				ds.setDataList(rpts.getDataSource());
				
				//载入父报表的其他参数
				Map<String,Object> paramMap = rpts.getParamMap();
				Set set = paramMap.keySet();
				Object[] setobj = set.toArray();
				for(int i=0;i<setobj.length;i++){
					//将其他参数传入父报表
					parameters.put(setobj[i], paramMap.get(setobj[i]));
				}
				
				//处理子报表
				JasperReport subJasperReport;
				String subReportPath = "";
				File subReportFile;
				List subrpts = rpts.getSubReport()==null?new ArrayList():rpts.getSubReport();
				for(int i=0;i<subrpts.size();i++){
					SubReport subrpt = (SubReport) subrpts.get(i);
					subReportPath = context.getRealPath("report/"+subrpt.getSubPath());
					subReportFile = ForUtil.createFile(subReportPath);
					if(!subReportFile.exists())
						throw new JRRuntimeException("子报表文件 "+ subReportPath +" 没有找到!!");
					try {
						subJasperReport = (JasperReport) JRLoader.loadObject(subReportFile.getPath());
					} catch (JRException e) {
						throw new JRRuntimeException("载入子报表 "+subReportPath+" 失败!!");
					}
					parameters.put(subrpt.getParamName(),subJasperReport);	
				}
				//处理子报表结束
				
				Connection conn = null;
				try{
					conn = ConUtils.getConn();
					parameters.put(JRParameter.REPORT_CONNECTION,conn);	
					jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
					request.getSession().setAttribute("printObj", jasperPrint);
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			session.setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
			session.setAttribute("RPTTITLE", rpts.getReportTitle());
		}
		
		JRHtmlExporter exporter = new JRHtmlExporter();
		
		int pageIndex = 0;
		int lastPageIndex = 0;
		int nextPage = pageIndex;
		int privPage = 0;
		int pageSize = 0;
		if(jasperPrint.getPages() != null){
			lastPageIndex = jasperPrint.getPages().size() - 1;
			pageSize = jasperPrint.getPages().size();
		}
		
		String pageStr = request.getParameter("page");
		
		try{
			pageIndex = Integer.parseInt(pageStr);
			nextPage = pageIndex;
			privPage = pageIndex;
		}catch(Exception e){
			
		}
		
		if(pageIndex < 0){
			pageIndex = 0;
		}

		if(pageIndex > lastPageIndex){
			pageIndex = lastPageIndex;
		}
		
		if(nextPage <= lastPageIndex){
			nextPage++;
		}
		
		if(privPage>0){
			privPage--;
		}
		
		StringBuffer sbuffer = new StringBuffer();
		String contextPath = "http://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath();
		
		//StringBuffer HTML_HEADER = new StringBuffer("<script type=\"text/javascript\" src=\""+contextPath+"/scripts/activeMenu.js\"></script>");
		
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, contextPath + "/servlets/image?image=");
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
		exporter.setParameter(JRExporterParameter.PAGE_INDEX, Integer.valueOf(pageIndex));
		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
		exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);
		exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, "pt");
		try {
			exporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer HTML = new StringBuffer();
		HTML.append("<html>\n");
		HTML.append("	<head>\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_common.css' />\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_datelist.css' />\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_edit.css' />\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_leftnav.css' />\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_list.css' />\n");
		HTML.append("		<link rel='stylesheet' type='text/css' href='"+contextPath+"/css/default_tipbox.css' />\n");
		HTML.append("		<object ID='WebBrowser' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object>\n");
		HTML.append("		<script type='text/javascript' src='"+contextPath+"/common/gotop/report.js'></script>\n");
		String rpttitle = session.getAttribute("RPTTITLE")==null?"":session.getAttribute("RPTTITLE").toString();
		HTML.append("		<title> "+ rpttitle +" </title>\n");
		HTML.append("	</head>");
		HTML.append("	<body onbeforeprint=\"bar.style.display='none';\" onafterprint=\"bar.style.display='block';\">");
		HTML.append("		<table width=\"100%\" height=\"100%\" border=\"0\">");	
		HTML.append("			<tr id=\"bar\" name=\"bar\">");
		HTML.append("				<td><br>");
		String fristButton = "<input type=\"button\" class=\"btn_bg\" value=\"首页\" onclick=\"document.location='"+contextPath+"/report.rpt?page=0';\" />";
		String nextButton = "<input type=\"button\" class=\"btn_bg\" value=\"下一页\" onclick=\"document.location='"+contextPath+"/report.rpt?page="+nextPage+"';\" />";
		String privButton = "<input type=\"button\" class=\"btn_bg\" value=\"上一页\" onclick=\"document.location='"+contextPath+"/report.rpt?page="+privPage+"';\" />";
		String lastButton = "<input type=\"button\" class=\"btn_bg\" value=\"末页\" onclick=\"document.location='"+contextPath+"/report.rpt?page="+lastPageIndex+"';\" />";
		String printConfig = "<input type=\"button\" class=\"btn_bg\" value=\"打印设置\" onclick=\"document.all.WebBrowser.ExecWB(8,1);\" />";
		String printView = "<input type=\"button\" class=\"btn_bg\" value=\"打印预览\" onclick=\"document.all.WebBrowser.ExecWB(7,1);\" />";
		String printOne = "<input type=\"button\" class=\"btn_bg\" value=\"打印本页\" onclick=\"printOne(document.all.WebBrowser);\" /> <input type=\"button\" class=\"btn_bg\" value=\"打印全部\" onclick=\"document.location='"+contextPath+"/jsp/util/printAll.jsp"+"'\" />";
		HTML.append(fristButton);
		HTML.append(privButton);
		HTML.append("					当前第 "+((int)pageIndex+1)+" / "+pageSize+" 页&nbsp;转到第&nbsp;<input type=\"text\" class=\"input_text\" style=\"width:30px\" name=\"page\" id=\"page\" />&nbsp;页<input type=\"button\" name=\"btn\" class=\"btn_bg\" id=\"btn\" value=\"go\" onclick=\"document.location='"+contextPath+"/report.rpt?page='+(parseInt(document.getElementById('page').value)-1)\" />");
		HTML.append(nextButton);
		HTML.append(lastButton);
		HTML.append(printConfig);
		HTML.append(printView);
		HTML.append(printOne);
		HTML.append("				</td>");
		HTML.append("			</tr>");
		HTML.append("			<tr>");
		HTML.append("				<td>");
		HTML.append(sbuffer);
		HTML.append("				</td>");
		HTML.append("			</tr>");
		HTML.append("		</table>");
		HTML.append("	</body>");
		HTML.append("</html>");
		
		HTML.append("");
		response.getWriter().write(HTML.toString());
	}

	public void init() throws ServletException {

	}
}
