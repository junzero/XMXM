package com.gotop.util.report;

/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

import java.sql.Connection;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapterService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptletException;

import com.gotop.util.report.script.TransCMoney;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: Scriptlet.java 5180 2012-03-29 13:23:12Z teodord $
 */
public class GTScriptlet extends JRDefaultScriptlet
{
	
	protected static Log log = LogFactory.getLog(GTScriptlet.class);
	public static TransCMoney TransCM = new TransCMoney();

	/**
	 *
	 */
	public void beforeReportInit() throws JRScriptletException
	{
		System.out.println("call beforeReportInit");
	}


	/**
	 *
	 */
	public void afterReportInit() throws JRScriptletException
	{
		System.out.println("call afterReportInit");
	}


	/**
	 *
	 */
	public void beforePageInit() throws JRScriptletException
	{
		System.out.println("call   beforePageInit : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
	}


	/**
	 *
	 */
	public void afterPageInit() throws JRScriptletException
	{
		System.out.println("call   afterPageInit  : PAGE_NUMBER = " + this.getVariableValue("PAGE_NUMBER"));
	}


	/**
	 *
	 */
	public void beforeColumnInit() throws JRScriptletException
	{
		System.out.println("call     beforeColumnInit");
	}


	/**
	 *
	 */
	public void afterColumnInit() throws JRScriptletException
	{
		System.out.println("call     afterColumnInit");
	}


	/**
	 *
	 */
	public void beforeGroupInit(String groupName) throws JRScriptletException
	{
		if (groupName.equals("CityGroup"))
		{
			System.out.println("call       beforeGroupInit : City = " + this.getFieldValue("City"));
		}
	}


	/**
	 *
	 */
	public void afterGroupInit(String groupName) throws JRScriptletException
	{
		if (groupName.equals("CityGroup"))
		{
			System.out.println("call       afterGroupInit  : City = " + this.getFieldValue("City"));
		
			String allCities = (String)this.getVariableValue("AllCities");
			String city = (String)this.getFieldValue("City");
			StringBuffer sbuffer = new StringBuffer();
		
			if (allCities != null)
			{
				sbuffer.append(allCities);
				sbuffer.append(", ");
			}
		
			sbuffer.append(city);
			this.setVariableValue("AllCities", sbuffer.toString());
		}
	}


	/**
	 *
	 */
	public void beforeDetailEval() throws JRScriptletException
	{
		System.out.println("        detail");
	}


	/**
	 *
	 */
	public void afterDetailEval() throws JRScriptletException
	{
	}


	/**
	 *测试数据源
	 */
	public String hello() throws JRScriptletException
	{
		JRDataSource dataSource = (JRDataSource)this.getParameterValue(JRParameter.REPORT_DATA_SOURCE);
		Connection conn = (Connection)this.getParameterValue(JRParameter.REPORT_CONNECTION);
//		log.info("-数据源1------"+dataSource);
//		log.info("-数据源2------"+conn);
		System.out.println("-conn3------"+conn);
		return "Hello! I'm the report's scriptlet object.";
	}


}
