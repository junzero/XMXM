package com.gotop.messageReceive.model;

import java.io.Serializable;

public class TMessageReceive implements Serializable {
    /**
     * 接收编号 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 信息编号 .
     * @abatorgenerated
     */
    private Long messageId;

    /**
     * 1：转发 .
     * @abatorgenerated
     */
    private String type;

    /**
     * 接收对象（人员、机构、角色） .
     * @abatorgenerated
     */
    private String receiveObject;

    /**
     * 用于选择角色发布时，指定发送给机构下某些角色。 .
     * @abatorgenerated
     */
    private String objOrgcode;

    /**
     * 0：未接受
1：已接收 .
     * @abatorgenerated
     */
    private String receiveStatus;

    /**
     * YYYY-MM-DD .
     * @abatorgenerated
     */
    private String receiveDate;

    /**
     * null .
     * @abatorgenerated
     */
    private String receiveTime;

    /**
     * 提交人 .
     * @abatorgenerated
     */
    private Long submitEmp;

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    private String submitTime;

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
     * 信息编号 .
     * @abatorgenerated
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 信息编号 .
     * @abatorgenerated
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 1：转发 .
     * @abatorgenerated
     */
    public String getType() {
        return type;
    }

    /**
     * 1：转发 .
     * @abatorgenerated
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 接收对象（人员、机构、角色） .
     * @abatorgenerated
     */
    public String getReceiveObject() {
        return receiveObject;
    }

    /**
     * 接收对象（人员、机构、角色） .
     * @abatorgenerated
     */
    public void setReceiveObject(String receiveObject) {
        this.receiveObject = receiveObject;
    }

    /**
     * 用于选择角色发布时，指定发送给机构下某些角色。 .
     * @abatorgenerated
     */
    public String getObjOrgcode() {
        return objOrgcode;
    }

    /**
     * 用于选择角色发布时，指定发送给机构下某些角色。 .
     * @abatorgenerated
     */
    public void setObjOrgcode(String objOrgcode) {
        this.objOrgcode = objOrgcode;
    }

    /**
     * 0：未接受
1：已接收 .
     * @abatorgenerated
     */
    public String getReceiveStatus() {
        return receiveStatus;
    }

    /**
     * 0：未接受
1：已接收 .
     * @abatorgenerated
     */
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    /**
     * YYYY-MM-DD .
     * @abatorgenerated
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * YYYY-MM-DD .
     * @abatorgenerated
     */
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public String getReceiveTime() {
        return receiveTime;
    }

    /**
     * null .
     * @abatorgenerated
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 提交人 .
     * @abatorgenerated
     */
    public Long getSubmitEmp() {
        return submitEmp;
    }

    /**
     * 提交人 .
     * @abatorgenerated
     */
    public void setSubmitEmp(Long submitEmp) {
        this.submitEmp = submitEmp;
    }

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    public String getSubmitTime() {
        return submitTime;
    }

    /**
     * 提交时间 .
     * @abatorgenerated
     */
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
}