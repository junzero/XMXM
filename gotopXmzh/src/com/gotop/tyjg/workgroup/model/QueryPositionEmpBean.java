package com.gotop.tyjg.workgroup.model;

import java.util.Date;
import java.math.BigDecimal;

public class QueryPositionEmpBean {
    private String eotypename;

	private String eotype;

	private String name;

	private String code;

	private BigDecimal eoid;

	/**
     * null .
     * @abatorgenerated
     */
    private Long groupid;

    /**
     * null .
     * @abatorgenerated
     */
    private Long orgid;

    /**
     * null .
     * @abatorgenerated
     */
    private String orgname;

    /**
     * null .
     * @abatorgenerated
     */
    private String groupname;

    /**
     * null .
     * @abatorgenerated
     */
    private String grouptype;

    /**
     * null .
     * @abatorgenerated
     */
    private String groupstatus;

    /**
     * null .
     * @abatorgenerated
     */
    private Date startdate;

    /**
     * null .
     * @abatorgenerated
     */
    private Date enddate;

    /**
     * @abatorgenerated*/
    public Long getGroupid() {
        return groupid;
    }

    /**
     * @abatorgenerated*/
    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    /**
     * @abatorgenerated*/
    public Long getOrgid() {
        return orgid;
    }

    /**
     * @abatorgenerated*/
    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    /**
     * @abatorgenerated*/
    public String getOrgname() {
        return orgname;
    }

    /**
     * @abatorgenerated*/
    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * @abatorgenerated*/
    public String getGroupname() {
        return groupname;
    }

    /**
     * @abatorgenerated*/
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * @abatorgenerated*/
    public String getGrouptype() {
        return grouptype;
    }

    /**
     * @abatorgenerated*/
    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype;
    }

    /**
     * @abatorgenerated*/
    public String getGroupstatus() {
        return groupstatus;
    }

    /**
     * @abatorgenerated*/
    public void setGroupstatus(String groupstatus) {
        this.groupstatus = groupstatus;
    }

    /**
     * @abatorgenerated*/
    public Date getStartdate() {
        return startdate;
    }

    /**
     * @abatorgenerated*/
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * @abatorgenerated*/
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @abatorgenerated*/
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEotypename() {
		return eotypename;
	}

	public void setEotypename(String eotypename) {
		this.eotypename = eotypename;
	}

	public String getEotype() {
		return eotype;
	}

	public void setEotype(String eotype) {
		this.eotype = eotype;
	}

	public BigDecimal getEoid() {
		return eoid;
	}

	public void setEoid(BigDecimal eoid) {
		this.eoid = eoid;
	}

}