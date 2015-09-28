package com.gotop.mettingRec.model;

import java.io.Serializable;

public class TMettingReceive implements Serializable {
    /**
     * 接收编号 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 会议编号 .
     * @abatorgenerated
     */
    private Long mettingId;

    /**
     * 接收人 .
     * @abatorgenerated
     */
    private Long recEmp;

    /**
     * 接收状态 .
     * @abatorgenerated
     */
    private String status;

    /**
     * 接收日期 .
     * @abatorgenerated
     */
    private String recDate;

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    private String recTime;

    /**
     * 接收编号 .
     * @abatorgenerated
     */
    public Long getRecId() {
        return recId;
    }

    /**
     * 接收编号 .
     * @abatorgenerated
     */
    public void setRecId(Long recId) {
        this.recId = recId;
    }

    /**
     * 会议编号 .
     * @abatorgenerated
     */
    public Long getMettingId() {
        return mettingId;
    }

    /**
     * 会议编号 .
     * @abatorgenerated
     */
    public void setMettingId(Long mettingId) {
        this.mettingId = mettingId;
    }

    /**
     * 接收人 .
     * @abatorgenerated
     */
    public Long getRecEmp() {
        return recEmp;
    }

    /**
     * 接收人 .
     * @abatorgenerated
     */
    public void setRecEmp(Long recEmp) {
        this.recEmp = recEmp;
    }

    /**
     * 接收状态 .
     * @abatorgenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * 接收状态 .
     * @abatorgenerated
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 接收日期 .
     * @abatorgenerated
     */
    public String getRecDate() {
        return recDate;
    }

    /**
     * 接收日期 .
     * @abatorgenerated
     */
    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    public String getRecTime() {
        return recTime;
    }

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }
}