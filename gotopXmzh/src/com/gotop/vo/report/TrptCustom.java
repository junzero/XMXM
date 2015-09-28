package com.gotop.vo.report;

/**
 * TrptCustom entity. @author MyEclipse Persistence Tools
 */

public class TrptCustom  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rptid;		//报表id
     private String rptname;	//报表名称
     private String rptremark;	//报表备注
     private Integer rptdirid;	//报表文件夹id
     private String rpttype;	//报表类型
     private Integer graphtype;	//图表类型
     private String xaxislegend;//x轴说明
     private String yaxislegend;//Y轴说明
     private String legend;		//轴说明
     private String rptmainmodel;//主模块
     private String rptrelmod;	//关联模块


    // Constructors

    /** default constructor */
    public TrptCustom() {
    }

	/** minimal constructor */
    public TrptCustom(Integer rptid) {
        this.rptid = rptid;
    }
    
    /** full constructor */
    public TrptCustom(Integer rptid, String rptname, String rptremark, Integer rptdirid, String rpttype, Integer graphtype, String xaxislegend, String yaxislegend, String legend,String rptmainmodel,String rptrelmod) {
        this.rptid = rptid;
        this.rptname = rptname;
        this.rptremark = rptremark;
        this.rptdirid = rptdirid;
        this.rpttype = rpttype;
        this.rptrelmod = rptrelmod;
        this.graphtype = graphtype;
        this.xaxislegend = xaxislegend;
        this.yaxislegend = yaxislegend;
        this.legend = legend;
    }

   
    // Property accessors

    public Integer getRptid() {
        return this.rptid;
    }
    
    public void setRptid(Integer rptid) {
        this.rptid = rptid;
    }

    public String getRptname() {
        return this.rptname;
    }
    
    public void setRptname(String rptname) {
        this.rptname = rptname;
    }

    public String getRptremark() {
        return this.rptremark;
    }
    
    public void setRptremark(String rptremark) {
        this.rptremark = rptremark;
    }

    public Integer getRptdirid() {
        return this.rptdirid;
    }
    
    public void setRptdirid(Integer rptdirid) {
        this.rptdirid = rptdirid;
    }

    public String getRpttype() {
        return this.rpttype;
    }
    
    public void setRpttype(String rpttype) {
        this.rpttype = rpttype;
    }

    public String getRptrelmod() {
        return this.rptrelmod;
    }
    
    public void setRptrelmod(String rptrelmod) {
        this.rptrelmod = rptrelmod;
    }

    public Integer getGraphtype() {
        return this.graphtype;
    }
    
    public void setGraphtype(Integer graphtype) {
        this.graphtype = graphtype;
    }

    public String getXaxislegend() {
        return this.xaxislegend;
    }
    
    public void setXaxislegend(String xaxislegend) {
        this.xaxislegend = xaxislegend;
    }

    public String getYaxislegend() {
        return this.yaxislegend;
    }
    
    public void setYaxislegend(String yaxislegend) {
        this.yaxislegend = yaxislegend;
    }

    public String getLegend() {
        return this.legend;
    }
    
    public void setLegend(String legend) {
        this.legend = legend;
    }

	public String getRptmainmodel()
	{
		return rptmainmodel;
	}

	public void setRptmainmodel(String rptmainmodel)
	{
		this.rptmainmodel = rptmainmodel;
	}
}