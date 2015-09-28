/**
 * 福建国通科技有限公司 1997-2012 版权所有.
 */
package com.gotop.home.model;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2014-7-26    黄开忠    新建
 * </pre>
 */
public class CurrUserInfo {
    /*
     * 当前登录人员ID
     */
    private long currempid;
    /*
     * 当前人员登录机构
     */
    private String currorgcode;
    /*
     * 当前登录人员的机构号
     */
    private long currorgid;
    
    /**
     * 当前登录人的岗位
     */
    private String positionId;
    /**
	 * @return positionId
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId 要设置的 positionId
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
     * @return the currorgid
     */
    public long getCurrorgid() {
        return currorgid;
    }

    /**
     * @param currorgid the currorgid to set
     */
    public void setCurrorgid(long currorgid) {
        this.currorgid = currorgid;
    }

    /*
     * 当前人员角色串
     */
    private String currRoles;

    /**
     * @return the currempid
     */
    public long getCurrempid() {
        return currempid;
    }

    /**
     * @param currempid the currempid to set
     */
    public void setCurrempid(long currempid) {
        this.currempid = currempid;
    }

    /**
     * @return the currorgcode
     */
    public String getCurrorgcode() {
        return currorgcode;
    }

    /**
     * @param currorgcode the currorgcode to set
     */
    public void setCurrorgcode(String currorgcode) {
        this.currorgcode = currorgcode;
    }

    /**
     * @return the currRoles
     */
    public String getCurrRoles() {
        return currRoles;
    }

    /**
     * @param currRoles the currRoles to set
     */
    public void setCurrRoles(String currRoles) {
        this.currRoles = currRoles;
    }
    
}
