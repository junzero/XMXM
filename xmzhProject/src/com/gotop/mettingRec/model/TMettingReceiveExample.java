package com.gotop.mettingRec.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class TMettingReceiveExample {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMettingReceiveExample.class);

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

    /** */
    public TMettingReceiveExample() {
        oredCriteria = new ArrayList();
    }

    /** */
    protected TMettingReceiveExample(TMettingReceiveExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.oracleStart = example.oracleStart;
        this.oracleEnd = example.oracleEnd;
    }

    /** */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /** */
    /** */
    public String getOrderByClause() {
        return orderByClause;
    }

    /** */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /** */
    public Integer getOracleStart() {
        return oracleStart;
    }

    /** */
    public void setOracleStart(Integer oracleStart) {
        this.oracleStart = oracleStart;
    }

    /** */
    public Integer getOracleEnd() {
        return oracleEnd;
    }

    /** */
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
     * This class corresponds to the database table T_METTING_RECEIVE
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

        public Criteria andMettingIdIsNull() {
            addCriterion("METTING_ID is null");
            return this;
        }

        public Criteria andMettingIdIsNotNull() {
            addCriterion("METTING_ID is not null");
            return this;
        }

        public Criteria andMettingIdEqualTo(Long value) {
            addCriterion("METTING_ID =", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdNotEqualTo(Long value) {
            addCriterion("METTING_ID <>", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdGreaterThan(Long value) {
            addCriterion("METTING_ID >", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdGreaterThanOrEqualTo(Long value) {
            addCriterion("METTING_ID >=", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdLessThan(Long value) {
            addCriterion("METTING_ID <", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdLessThanOrEqualTo(Long value) {
            addCriterion("METTING_ID <=", value, "mettingId");
            return this;
        }

        public Criteria andMettingIdIn(List values) {
            addCriterion("METTING_ID in", values, "mettingId");
            return this;
        }

        public Criteria andMettingIdNotIn(List values) {
            addCriterion("METTING_ID not in", values, "mettingId");
            return this;
        }

        public Criteria andMettingIdBetween(Long value1, Long value2) {
            addCriterion("METTING_ID between", value1, value2, "mettingId");
            return this;
        }

        public Criteria andMettingIdNotBetween(Long value1, Long value2) {
            addCriterion("METTING_ID not between", value1, value2, "mettingId");
            return this;
        }

        public Criteria andRecEmpIsNull() {
            addCriterion("REC_EMP is null");
            return this;
        }

        public Criteria andRecEmpIsNotNull() {
            addCriterion("REC_EMP is not null");
            return this;
        }

        public Criteria andRecEmpEqualTo(Long value) {
            addCriterion("REC_EMP =", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpNotEqualTo(Long value) {
            addCriterion("REC_EMP <>", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpGreaterThan(Long value) {
            addCriterion("REC_EMP >", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpGreaterThanOrEqualTo(Long value) {
            addCriterion("REC_EMP >=", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpLessThan(Long value) {
            addCriterion("REC_EMP <", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpLessThanOrEqualTo(Long value) {
            addCriterion("REC_EMP <=", value, "recEmp");
            return this;
        }

        public Criteria andRecEmpIn(List values) {
            addCriterion("REC_EMP in", values, "recEmp");
            return this;
        }

        public Criteria andRecEmpNotIn(List values) {
            addCriterion("REC_EMP not in", values, "recEmp");
            return this;
        }

        public Criteria andRecEmpBetween(Long value1, Long value2) {
            addCriterion("REC_EMP between", value1, value2, "recEmp");
            return this;
        }

        public Criteria andRecEmpNotBetween(Long value1, Long value2) {
            addCriterion("REC_EMP not between", value1, value2, "recEmp");
            return this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return this;
        }

        public Criteria andStatusIn(List values) {
            addCriterion("STATUS in", values, "status");
            return this;
        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("STATUS not in", values, "status");
            return this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return this;
        }

        public Criteria andRecDateIsNull() {
            addCriterion("REC_DATE is null");
            return this;
        }

        public Criteria andRecDateIsNotNull() {
            addCriterion("REC_DATE is not null");
            return this;
        }

        public Criteria andRecDateEqualTo(String value) {
            addCriterion("REC_DATE =", value, "recDate");
            return this;
        }

        public Criteria andRecDateNotEqualTo(String value) {
            addCriterion("REC_DATE <>", value, "recDate");
            return this;
        }

        public Criteria andRecDateGreaterThan(String value) {
            addCriterion("REC_DATE >", value, "recDate");
            return this;
        }

        public Criteria andRecDateGreaterThanOrEqualTo(String value) {
            addCriterion("REC_DATE >=", value, "recDate");
            return this;
        }

        public Criteria andRecDateLessThan(String value) {
            addCriterion("REC_DATE <", value, "recDate");
            return this;
        }

        public Criteria andRecDateLessThanOrEqualTo(String value) {
            addCriterion("REC_DATE <=", value, "recDate");
            return this;
        }

        public Criteria andRecDateLike(String value) {
            addCriterion("REC_DATE like", value, "recDate");
            return this;
        }

        public Criteria andRecDateNotLike(String value) {
            addCriterion("REC_DATE not like", value, "recDate");
            return this;
        }

        public Criteria andRecDateIn(List values) {
            addCriterion("REC_DATE in", values, "recDate");
            return this;
        }

        public Criteria andRecDateNotIn(List values) {
            addCriterion("REC_DATE not in", values, "recDate");
            return this;
        }

        public Criteria andRecDateBetween(String value1, String value2) {
            addCriterion("REC_DATE between", value1, value2, "recDate");
            return this;
        }

        public Criteria andRecDateNotBetween(String value1, String value2) {
            addCriterion("REC_DATE not between", value1, value2, "recDate");
            return this;
        }

        public Criteria andRecTimeIsNull() {
            addCriterion("REC_TIME is null");
            return this;
        }

        public Criteria andRecTimeIsNotNull() {
            addCriterion("REC_TIME is not null");
            return this;
        }

        public Criteria andRecTimeEqualTo(String value) {
            addCriterion("REC_TIME =", value, "recTime");
            return this;
        }

        public Criteria andRecTimeNotEqualTo(String value) {
            addCriterion("REC_TIME <>", value, "recTime");
            return this;
        }

        public Criteria andRecTimeGreaterThan(String value) {
            addCriterion("REC_TIME >", value, "recTime");
            return this;
        }

        public Criteria andRecTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REC_TIME >=", value, "recTime");
            return this;
        }

        public Criteria andRecTimeLessThan(String value) {
            addCriterion("REC_TIME <", value, "recTime");
            return this;
        }

        public Criteria andRecTimeLessThanOrEqualTo(String value) {
            addCriterion("REC_TIME <=", value, "recTime");
            return this;
        }

        public Criteria andRecTimeLike(String value) {
            addCriterion("REC_TIME like", value, "recTime");
            return this;
        }

        public Criteria andRecTimeNotLike(String value) {
            addCriterion("REC_TIME not like", value, "recTime");
            return this;
        }

        public Criteria andRecTimeIn(List values) {
            addCriterion("REC_TIME in", values, "recTime");
            return this;
        }

        public Criteria andRecTimeNotIn(List values) {
            addCriterion("REC_TIME not in", values, "recTime");
            return this;
        }

        public Criteria andRecTimeBetween(String value1, String value2) {
            addCriterion("REC_TIME between", value1, value2, "recTime");
            return this;
        }

        public Criteria andRecTimeNotBetween(String value1, String value2) {
            addCriterion("REC_TIME not between", value1, value2, "recTime");
            return this;
        }
    }
}