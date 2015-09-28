package com.gotop.group.model;

import java.io.Serializable;

public class TGroupmember extends TGroupmemberKey implements Serializable {
    /**
     * null .
     * @abatorgenerated
     */
    private String flag;
    
    private String empName;
    
    public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
     * null .
     * @abatorgenerated
     */
    public String getFlag() {
        return flag;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}