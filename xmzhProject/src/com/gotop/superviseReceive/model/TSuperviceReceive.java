package com.gotop.superviseReceive.model;

import java.io.Serializable;

public class TSuperviceReceive implements Serializable {
    /**
     * 接收编号 .
     * @abatorgenerated
     */
    private Long recId;

    /**
     * 督办单编号 .
     * @abatorgenerated
     */
    private Long superviseId;

    /**
     * 接收人 .
     * @abatorgenerated
     */
    private String receiveEmp;

    /**
     * 反馈信息 .
     * @abatorgenerated
     */
    private String content;

    /**
     * 接收状态 .
     * @abatorgenerated
     */
    private String status;

    /**
     * 接受日期 .
     * @abatorgenerated
     */
    private String receiveDate;

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    private String receiveTime;

    /**
     * 节点 .
     * @abatorgenerated
     */
    private String nodeId;

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    private String nodeName;

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
     * 督办单编号 .
     * @abatorgenerated
     */
    public Long getSuperviseId() {
        return superviseId;
    }

    /**
     * 督办单编号 .
     * @abatorgenerated
     */
    public void setSuperviseId(Long superviseId) {
        this.superviseId = superviseId;
    }

    /**
     * 接收人 .
     * @abatorgenerated
     */
    public String getReceiveEmp() {
        return receiveEmp;
    }

    /**
     * 接收人 .
     * @abatorgenerated
     */
    public void setReceiveEmp(String receiveEmp) {
        this.receiveEmp = receiveEmp;
    }

    /**
     * 反馈信息 .
     * @abatorgenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * 反馈信息 .
     * @abatorgenerated
     */
    public void setContent(String content) {
        this.content = content;
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
     * 接受日期 .
     * @abatorgenerated
     */
    public String getReceiveDate() {
        return receiveDate;
    }

    /**
     * 接受日期 .
     * @abatorgenerated
     */
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    public String getReceiveTime() {
        return receiveTime;
    }

    /**
     * 接收时间 .
     * @abatorgenerated
     */
    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 节点 .
     * @abatorgenerated
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 节点 .
     * @abatorgenerated
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 节点名称 .
     * @abatorgenerated
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}