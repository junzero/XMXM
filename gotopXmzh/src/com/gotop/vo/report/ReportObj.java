package com.gotop.vo.report;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class ReportObj implements Serializable
{
	private String rptName;
	private String rptType;
	private int graphType;
	private String rptSql;
	private String rptGraphSql;
	private String rptXaxis;
	private String rptYaxis;
	private String rptLegend;
	private List orderFieldsEn;	//字段英文名称
	private List orderFieldsCh;	//字段中文名称
	private List collectFieldEn;//统计字段英文名
	private List collectFieldCh;//统计字段中文名
	private Map fieldMap;
	public ReportObj()
	{
		super();
	}


	public ReportObj(String rptName, String rptType, int graphType,
			String rptSql, String rptGraphSql, String rptXaxis,
			String rptYaxis, String rptLegend, List orderFieldsEn,
			List orderFieldsCh, Map fieldMap,List collectFieldEn,List collectFieldCh)
	{
		super();
		this.rptName = rptName;
		this.rptType = rptType;
		this.graphType = graphType;
		this.rptSql = rptSql;
		this.rptGraphSql = rptGraphSql;
		this.rptXaxis = rptXaxis;
		this.rptYaxis = rptYaxis;
		this.rptLegend = rptLegend;
		this.orderFieldsEn = orderFieldsEn;
		this.orderFieldsCh = orderFieldsCh;
		this.fieldMap = fieldMap;
		this.collectFieldEn=collectFieldEn;
		this.collectFieldCh=collectFieldCh;
	}
	public String getRptName()
	{
		return rptName;
	}
	public void setRptName(String rptName)
	{
		this.rptName = rptName;
	}
	public String getRptType()
	{
		return rptType;
	}
	public void setRptType(String rptType)
	{
		this.rptType = rptType;
	}
	public String getRptSql()
	{
		return rptSql;
	}
	public void setRptSql(String rptSql)
	{
		this.rptSql = rptSql;
	}

	public String getRptGraphSql()
	{
		return rptGraphSql;
	}

	public void setRptGraphSql(String rptGraphSql)
	{
		this.rptGraphSql = rptGraphSql;
	}

	public Map getFieldMap()
	{
		return fieldMap;
	}

	public void setFieldMap(Map fieldMap)
	{
		this.fieldMap = fieldMap;
	}



	public String getRptXaxis()
	{
		return rptXaxis;
	}



	public void setRptXaxis(String rptXaxis)
	{
		this.rptXaxis = rptXaxis;
	}

	public int getGraphType()
	{
		return graphType;
	}

	public void setGraphType(int graphType)
	{
		this.graphType = graphType;
	}



	public String getRptYaxis()
	{
		return rptYaxis;
	}



	public void setRptYaxis(String rptYaxis)
	{
		this.rptYaxis = rptYaxis;
	}



	public String getRptLegend()
	{
		return rptLegend;
	}



	public void setRptLegend(String rptLegend)
	{
		this.rptLegend = rptLegend;
	}

	public List getOrderFieldsEn()
	{
		return orderFieldsEn;
	}


	public void setOrderFieldsEn(List orderFieldsEn)
	{
		this.orderFieldsEn = orderFieldsEn;
	}


	public List getOrderFieldsCh()
	{
		return orderFieldsCh;
	}


	public void setOrderFieldsCh(List orderFieldsCh)
	{
		this.orderFieldsCh = orderFieldsCh;
	}


	public List getCollectFieldEn()
	{
		return collectFieldEn;
	}


	public void setCollectFieldEn(List collectFieldEn)
	{
		this.collectFieldEn = collectFieldEn;
	}


	public List getCollectFieldCh()
	{
		return collectFieldCh;
	}


	public void setCollectFieldCh(List collectFieldCh)
	{
		this.collectFieldCh = collectFieldCh;
	}
	
	
}
