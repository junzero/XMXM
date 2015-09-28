package com.gotop.commonProcess.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class TCommonProcessExample {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TCommonProcessExample.class);

    /**
     * 排序字段.
     * @abatorgenerated
     */
    protected String orderByClause;

    /**
     * sql条件.
     * @abatorgenerated
     */
    protected List oredCriteria;

    /**
     * 分页查询的起始行数.
     * @abatorgenerated
     */
    private Integer oracleStart;

    /**
     * 分页查询结束行数.
     * @abatorgenerated
     */
    private Integer oracleEnd;

    /** * @abatorgenerated  */
    public TCommonProcessExample() {
        oredCriteria = new ArrayList();
    }

    /** * @abatorgenerated  */
    protected TCommonProcessExample(TCommonProcessExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.oracleStart = example.oracleStart;
        this.oracleEnd = example.oracleEnd;
    }

    /** * @abatorgenerated  */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /** * @abatorgenerated  */
    /** * @abatorgenerated  */
    public String getOrderByClause() {
        return orderByClause;
    }

    /** * @abatorgenerated  */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /** * @abatorgenerated  */
    public Integer getOracleStart() {
        return oracleStart;
    }

    /** * @abatorgenerated  */
    public void setOracleStart(Integer oracleStart) {
        this.oracleStart = oracleStart;
    }

    /** * @abatorgenerated  */
    public Integer getOracleEnd() {
        return oracleEnd;
    }

    /** * @abatorgenerated  */
    public void setOracleEnd(Integer oracleEnd) {
        this.oracleEnd = oracleEnd;
    }

    /**
     * 增加整个新查询条件(Criteria)，增加后以or方式查询.
     * @abatorgenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * 创新新Criteria，如果为第一个则加入到Criteria列表。并且返回
     * @abatorgenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * 仅创新新Criteria,并且返回
     * @abatorgenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * 清除查询条件
     * @abatorgenerated
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Abator for iBATIS.
     * This class corresponds to the database table T_COMMON_PROCESS
     *
     * @abatorgenerated 
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andRecIdIsNull() {
            addCriterion("REC_ID is null");
            return this;
        }

        public Criteria andRecIdIsNotNull() {
            addCriterion("REC_ID is not null");
            return this;
        }

        public Criteria andRecIdEqualTo(Long value) {
            addCriterion("REC_ID =", value, "recId");
            return this;
        }

        public Criteria andRecIdNotEqualTo(Long value) {
            addCriterion("REC_ID <>", value, "recId");
            return this;
        }

        public Criteria andRecIdGreaterThan(Long value) {
            addCriterion("REC_ID >", value, "recId");
            return this;
        }

        public Criteria andRecIdGreaterThanOrEqualTo(Long value) {
            addCriterion("REC_ID >=", value, "recId");
            return this;
        }

        public Criteria andRecIdLessThan(Long value) {
            addCriterion("REC_ID <", value, "recId");
            return this;
        }

        public Criteria andRecIdLessThanOrEqualTo(Long value) {
            addCriterion("REC_ID <=", value, "recId");
            return this;
        }

        public Criteria andRecIdIn(List values) {
            addCriterion("REC_ID in", values, "recId");
            return this;
        }

        public Criteria andRecIdNotIn(List values) {
            addCriterion("REC_ID not in", values, "recId");
            return this;
        }

        public Criteria andRecIdBetween(Long value1, Long value2) {
            addCriterion("REC_ID between", value1, value2, "recId");
            return this;
        }

        public Criteria andRecIdNotBetween(Long value1, Long value2) {
            addCriterion("REC_ID not between", value1, value2, "recId");
            return this;
        }

        public Criteria andEmpIdIsNull() {
            addCriterion("EMP_ID is null");
            return this;
        }

        public Criteria andEmpIdIsNotNull() {
            addCriterion("EMP_ID is not null");
            return this;
        }

        public Criteria andEmpIdEqualTo(Long value) {
            addCriterion("EMP_ID =", value, "empId");
            return this;
        }

        public Criteria andEmpIdNotEqualTo(Long value) {
            addCriterion("EMP_ID <>", value, "empId");
            return this;
        }

        public Criteria andEmpIdGreaterThan(Long value) {
            addCriterion("EMP_ID >", value, "empId");
            return this;
        }

        public Criteria andEmpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EMP_ID >=", value, "empId");
            return this;
        }

        public Criteria andEmpIdLessThan(Long value) {
            addCriterion("EMP_ID <", value, "empId");
            return this;
        }

        public Criteria andEmpIdLessThanOrEqualTo(Long value) {
            addCriterion("EMP_ID <=", value, "empId");
            return this;
        }

        public Criteria andEmpIdIn(List values) {
            addCriterion("EMP_ID in", values, "empId");
            return this;
        }

        public Criteria andEmpIdNotIn(List values) {
            addCriterion("EMP_ID not in", values, "empId");
            return this;
        }

        public Criteria andEmpIdBetween(Long value1, Long value2) {
            addCriterion("EMP_ID between", value1, value2, "empId");
            return this;
        }

        public Criteria andEmpIdNotBetween(Long value1, Long value2) {
            addCriterion("EMP_ID not between", value1, value2, "empId");
            return this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return this;
        }

        public Criteria andOrgIdEqualTo(Long value) {
            addCriterion("ORG_ID =", value, "orgId");
            return this;
        }

        public Criteria andOrgIdNotEqualTo(Long value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return this;
        }

        public Criteria andOrgIdGreaterThan(Long value) {
            addCriterion("ORG_ID >", value, "orgId");
            return this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return this;
        }

        public Criteria andOrgIdLessThan(Long value) {
            addCriterion("ORG_ID <", value, "orgId");
            return this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return this;
        }

        public Criteria andOrgIdIn(List values) {
            addCriterion("ORG_ID in", values, "orgId");
            return this;
        }

        public Criteria andOrgIdNotIn(List values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return this;
        }

        public Criteria andOrgIdBetween(Long value1, Long value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return this;
        }

        public Criteria andOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return this;
        }

        public Criteria andFlowIdIsNull() {
            addCriterion("FLOW_ID is null");
            return this;
        }

        public Criteria andFlowIdIsNotNull() {
            addCriterion("FLOW_ID is not null");
            return this;
        }

        public Criteria andFlowIdEqualTo(String value) {
            addCriterion("FLOW_ID =", value, "flowId");
            return this;
        }

        public Criteria andFlowIdNotEqualTo(String value) {
            addCriterion("FLOW_ID <>", value, "flowId");
            return this;
        }

        public Criteria andFlowIdGreaterThan(String value) {
            addCriterion("FLOW_ID >", value, "flowId");
            return this;
        }

        public Criteria andFlowIdGreaterThanOrEqualTo(String value) {
            addCriterion("FLOW_ID >=", value, "flowId");
            return this;
        }

        public Criteria andFlowIdLessThan(String value) {
            addCriterion("FLOW_ID <", value, "flowId");
            return this;
        }

        public Criteria andFlowIdLessThanOrEqualTo(String value) {
            addCriterion("FLOW_ID <=", value, "flowId");
            return this;
        }

        public Criteria andFlowIdLike(String value) {
            addCriterion("FLOW_ID like", value, "flowId");
            return this;
        }

        public Criteria andFlowIdNotLike(String value) {
            addCriterion("FLOW_ID not like", value, "flowId");
            return this;
        }

        public Criteria andFlowIdIn(List values) {
            addCriterion("FLOW_ID in", values, "flowId");
            return this;
        }

        public Criteria andFlowIdNotIn(List values) {
            addCriterion("FLOW_ID not in", values, "flowId");
            return this;
        }

        public Criteria andFlowIdBetween(String value1, String value2) {
            addCriterion("FLOW_ID between", value1, value2, "flowId");
            return this;
        }

        public Criteria andFlowIdNotBetween(String value1, String value2) {
            addCriterion("FLOW_ID not between", value1, value2, "flowId");
            return this;
        }

        public Criteria andPhoneNoIsNull() {
            addCriterion("PHONE_NO is null");
            return this;
        }

        public Criteria andPhoneNoIsNotNull() {
            addCriterion("PHONE_NO is not null");
            return this;
        }

        public Criteria andPhoneNoEqualTo(String value) {
            addCriterion("PHONE_NO =", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoNotEqualTo(String value) {
            addCriterion("PHONE_NO <>", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoGreaterThan(String value) {
            addCriterion("PHONE_NO >", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NO >=", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoLessThan(String value) {
            addCriterion("PHONE_NO <", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NO <=", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoLike(String value) {
            addCriterion("PHONE_NO like", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoNotLike(String value) {
            addCriterion("PHONE_NO not like", value, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoIn(List values) {
            addCriterion("PHONE_NO in", values, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoNotIn(List values) {
            addCriterion("PHONE_NO not in", values, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoBetween(String value1, String value2) {
            addCriterion("PHONE_NO between", value1, value2, "phoneNo");
            return this;
        }

        public Criteria andPhoneNoNotBetween(String value1, String value2) {
            addCriterion("PHONE_NO not between", value1, value2, "phoneNo");
            return this;
        }

        public Criteria andComTitleIsNull() {
            addCriterion("COM_TITLE is null");
            return this;
        }

        public Criteria andComTitleIsNotNull() {
            addCriterion("COM_TITLE is not null");
            return this;
        }

        public Criteria andComTitleEqualTo(String value) {
            addCriterion("COM_TITLE =", value, "comTitle");
            return this;
        }

        public Criteria andComTitleNotEqualTo(String value) {
            addCriterion("COM_TITLE <>", value, "comTitle");
            return this;
        }

        public Criteria andComTitleGreaterThan(String value) {
            addCriterion("COM_TITLE >", value, "comTitle");
            return this;
        }

        public Criteria andComTitleGreaterThanOrEqualTo(String value) {
            addCriterion("COM_TITLE >=", value, "comTitle");
            return this;
        }

        public Criteria andComTitleLessThan(String value) {
            addCriterion("COM_TITLE <", value, "comTitle");
            return this;
        }

        public Criteria andComTitleLessThanOrEqualTo(String value) {
            addCriterion("COM_TITLE <=", value, "comTitle");
            return this;
        }

        public Criteria andComTitleLike(String value) {
            addCriterion("COM_TITLE like", value, "comTitle");
            return this;
        }

        public Criteria andComTitleNotLike(String value) {
            addCriterion("COM_TITLE not like", value, "comTitle");
            return this;
        }

        public Criteria andComTitleIn(List values) {
            addCriterion("COM_TITLE in", values, "comTitle");
            return this;
        }

        public Criteria andComTitleNotIn(List values) {
            addCriterion("COM_TITLE not in", values, "comTitle");
            return this;
        }

        public Criteria andComTitleBetween(String value1, String value2) {
            addCriterion("COM_TITLE between", value1, value2, "comTitle");
            return this;
        }

        public Criteria andComTitleNotBetween(String value1, String value2) {
            addCriterion("COM_TITLE not between", value1, value2, "comTitle");
            return this;
        }

        public Criteria andComContentIsNull() {
            addCriterion("COM_CONTENT is null");
            return this;
        }

        public Criteria andComContentIsNotNull() {
            addCriterion("COM_CONTENT is not null");
            return this;
        }

        public Criteria andComContentEqualTo(String value) {
            addCriterion("COM_CONTENT =", value, "comContent");
            return this;
        }

        public Criteria andComContentNotEqualTo(String value) {
            addCriterion("COM_CONTENT <>", value, "comContent");
            return this;
        }

        public Criteria andComContentGreaterThan(String value) {
            addCriterion("COM_CONTENT >", value, "comContent");
            return this;
        }

        public Criteria andComContentGreaterThanOrEqualTo(String value) {
            addCriterion("COM_CONTENT >=", value, "comContent");
            return this;
        }

        public Criteria andComContentLessThan(String value) {
            addCriterion("COM_CONTENT <", value, "comContent");
            return this;
        }

        public Criteria andComContentLessThanOrEqualTo(String value) {
            addCriterion("COM_CONTENT <=", value, "comContent");
            return this;
        }

        public Criteria andComContentLike(String value) {
            addCriterion("COM_CONTENT like", value, "comContent");
            return this;
        }

        public Criteria andComContentNotLike(String value) {
            addCriterion("COM_CONTENT not like", value, "comContent");
            return this;
        }

        public Criteria andComContentIn(List values) {
            addCriterion("COM_CONTENT in", values, "comContent");
            return this;
        }

        public Criteria andComContentNotIn(List values) {
            addCriterion("COM_CONTENT not in", values, "comContent");
            return this;
        }

        public Criteria andComContentBetween(String value1, String value2) {
            addCriterion("COM_CONTENT between", value1, value2, "comContent");
            return this;
        }

        public Criteria andComContentNotBetween(String value1, String value2) {
            addCriterion("COM_CONTENT not between", value1, value2, "comContent");
            return this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return this;
        }

        public Criteria andCreateDateIn(List values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return this;
        }

        public Criteria andCreateDateNotIn(List values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return this;
        }

        public Criteria andCreateTimeIn(List values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotIn(List values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return this;
        }

        public Criteria andApplyTimeEqualTo(String value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeNotEqualTo(String value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeGreaterThan(String value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeLessThan(String value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeLike(String value) {
            addCriterion("APPLY_TIME like", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeNotLike(String value) {
            addCriterion("APPLY_TIME not like", value, "applyTime");
            return this;
        }

        public Criteria andApplyTimeIn(List values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return this;
        }

        public Criteria andApplyTimeNotIn(List values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return this;
        }

        public Criteria andApplyTimeBetween(String value1, String value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return this;
        }

        public Criteria andApplyTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
            return this;
        }

        public Criteria andBussinessTypeIsNull() {
            addCriterion("BUSSINESS_TYPE is null");
            return this;
        }

        public Criteria andBussinessTypeIsNotNull() {
            addCriterion("BUSSINESS_TYPE is not null");
            return this;
        }

        public Criteria andBussinessTypeEqualTo(String value) {
            addCriterion("BUSSINESS_TYPE =", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeNotEqualTo(String value) {
            addCriterion("BUSSINESS_TYPE <>", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeGreaterThan(String value) {
            addCriterion("BUSSINESS_TYPE >", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_TYPE >=", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeLessThan(String value) {
            addCriterion("BUSSINESS_TYPE <", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeLessThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_TYPE <=", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeLike(String value) {
            addCriterion("BUSSINESS_TYPE like", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeNotLike(String value) {
            addCriterion("BUSSINESS_TYPE not like", value, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeIn(List values) {
            addCriterion("BUSSINESS_TYPE in", values, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeNotIn(List values) {
            addCriterion("BUSSINESS_TYPE not in", values, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeBetween(String value1, String value2) {
            addCriterion("BUSSINESS_TYPE between", value1, value2, "bussinessType");
            return this;
        }

        public Criteria andBussinessTypeNotBetween(String value1, String value2) {
            addCriterion("BUSSINESS_TYPE not between", value1, value2, "bussinessType");
            return this;
        }

        public Criteria andSpareColIsNull() {
            addCriterion("SPARE_COL is null");
            return this;
        }

        public Criteria andSpareColIsNotNull() {
            addCriterion("SPARE_COL is not null");
            return this;
        }

        public Criteria andSpareColEqualTo(String value) {
            addCriterion("SPARE_COL =", value, "spareCol");
            return this;
        }

        public Criteria andSpareColNotEqualTo(String value) {
            addCriterion("SPARE_COL <>", value, "spareCol");
            return this;
        }

        public Criteria andSpareColGreaterThan(String value) {
            addCriterion("SPARE_COL >", value, "spareCol");
            return this;
        }

        public Criteria andSpareColGreaterThanOrEqualTo(String value) {
            addCriterion("SPARE_COL >=", value, "spareCol");
            return this;
        }

        public Criteria andSpareColLessThan(String value) {
            addCriterion("SPARE_COL <", value, "spareCol");
            return this;
        }

        public Criteria andSpareColLessThanOrEqualTo(String value) {
            addCriterion("SPARE_COL <=", value, "spareCol");
            return this;
        }

        public Criteria andSpareColLike(String value) {
            addCriterion("SPARE_COL like", value, "spareCol");
            return this;
        }

        public Criteria andSpareColNotLike(String value) {
            addCriterion("SPARE_COL not like", value, "spareCol");
            return this;
        }

        public Criteria andSpareColIn(List values) {
            addCriterion("SPARE_COL in", values, "spareCol");
            return this;
        }

        public Criteria andSpareColNotIn(List values) {
            addCriterion("SPARE_COL not in", values, "spareCol");
            return this;
        }

        public Criteria andSpareColBetween(String value1, String value2) {
            addCriterion("SPARE_COL between", value1, value2, "spareCol");
            return this;
        }

        public Criteria andSpareColNotBetween(String value1, String value2) {
            addCriterion("SPARE_COL not between", value1, value2, "spareCol");
            return this;
        }
    }
}