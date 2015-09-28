package com.gotop.jbpm.model;

import java.io.Serializable;

public class TProcessBusinessConfig implements Serializable {
    /**
     * 主键 .
     * @abatorgenerated
     */
    private Long id;

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    private String businessType;

    /**
     * 业务字段 .
     * @abatorgenerated
     */
    private String businessColumn;

    /**
     * 业务表 .
     * @abatorgenerated
     */
    private String businessTable;

    /**
     * 主键 .
     * @abatorgenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键 .
     * @abatorgenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * 业务类型 .
     * @abatorgenerated
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * 业务字段 .
     * @abatorgenerated
     */
    public String getBusinessColumn() {
        return businessColumn;
    }

    /**
     * 业务字段 .
     * @abatorgenerated
     */
    public void setBusinessColumn(String businessColumn) {
        this.businessColumn = businessColumn;
    }

    /**
     * 业务表 .
     * @abatorgenerated
     */
    public String getBusinessTable() {
        return businessTable;
    }

    /**
     * 业务表 .
     * @abatorgenerated
     */
    public void setBusinessTable(String businessTable) {
        this.businessTable = businessTable;
    }
}