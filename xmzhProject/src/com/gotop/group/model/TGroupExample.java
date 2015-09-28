package com.gotop.group.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class TGroupExample {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TGroupExample.class);

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
    public TGroupExample() {
        oredCriteria = new ArrayList();
    }

    /** * @abatorgenerated  */
    protected TGroupExample(TGroupExample example) {
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
     * This class corresponds to the database table T_GROUP
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

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return this;
        }

        public Criteria andGroupNameIn(List values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return this;
        }

        public Criteria andGroupNameNotIn(List values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
            return this;
        }

        public Criteria andGroupDetailIsNull() {
            addCriterion("GROUP_DETAIL is null");
            return this;
        }

        public Criteria andGroupDetailIsNotNull() {
            addCriterion("GROUP_DETAIL is not null");
            return this;
        }

        public Criteria andGroupDetailEqualTo(String value) {
            addCriterion("GROUP_DETAIL =", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailNotEqualTo(String value) {
            addCriterion("GROUP_DETAIL <>", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailGreaterThan(String value) {
            addCriterion("GROUP_DETAIL >", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_DETAIL >=", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailLessThan(String value) {
            addCriterion("GROUP_DETAIL <", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailLessThanOrEqualTo(String value) {
            addCriterion("GROUP_DETAIL <=", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailLike(String value) {
            addCriterion("GROUP_DETAIL like", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailNotLike(String value) {
            addCriterion("GROUP_DETAIL not like", value, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailIn(List values) {
            addCriterion("GROUP_DETAIL in", values, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailNotIn(List values) {
            addCriterion("GROUP_DETAIL not in", values, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailBetween(String value1, String value2) {
            addCriterion("GROUP_DETAIL between", value1, value2, "groupDetail");
            return this;
        }

        public Criteria andGroupDetailNotBetween(String value1, String value2) {
            addCriterion("GROUP_DETAIL not between", value1, value2, "groupDetail");
            return this;
        }

        public Criteria andGroupCreatorIsNull() {
            addCriterion("GROUP_CREATOR is null");
            return this;
        }

        public Criteria andGroupCreatorIsNotNull() {
            addCriterion("GROUP_CREATOR is not null");
            return this;
        }

        public Criteria andGroupCreatorEqualTo(Long value) {
            addCriterion("GROUP_CREATOR =", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorNotEqualTo(Long value) {
            addCriterion("GROUP_CREATOR <>", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorGreaterThan(Long value) {
            addCriterion("GROUP_CREATOR >", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorGreaterThanOrEqualTo(Long value) {
            addCriterion("GROUP_CREATOR >=", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorLessThan(Long value) {
            addCriterion("GROUP_CREATOR <", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorLessThanOrEqualTo(Long value) {
            addCriterion("GROUP_CREATOR <=", value, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorIn(List values) {
            addCriterion("GROUP_CREATOR in", values, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorNotIn(List values) {
            addCriterion("GROUP_CREATOR not in", values, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorBetween(Long value1, Long value2) {
            addCriterion("GROUP_CREATOR between", value1, value2, "groupCreator");
            return this;
        }

        public Criteria andGroupCreatorNotBetween(Long value1, Long value2) {
            addCriterion("GROUP_CREATOR not between", value1, value2, "groupCreator");
            return this;
        }

        public Criteria andCreateRoleIsNull() {
            addCriterion("CREATE_ROLE is null");
            return this;
        }

        public Criteria andCreateRoleIsNotNull() {
            addCriterion("CREATE_ROLE is not null");
            return this;
        }

        public Criteria andCreateRoleEqualTo(String value) {
            addCriterion("CREATE_ROLE =", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleNotEqualTo(String value) {
            addCriterion("CREATE_ROLE <>", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleGreaterThan(String value) {
            addCriterion("CREATE_ROLE >", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_ROLE >=", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleLessThan(String value) {
            addCriterion("CREATE_ROLE <", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleLessThanOrEqualTo(String value) {
            addCriterion("CREATE_ROLE <=", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleLike(String value) {
            addCriterion("CREATE_ROLE like", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleNotLike(String value) {
            addCriterion("CREATE_ROLE not like", value, "createRole");
            return this;
        }

        public Criteria andCreateRoleIn(List values) {
            addCriterion("CREATE_ROLE in", values, "createRole");
            return this;
        }

        public Criteria andCreateRoleNotIn(List values) {
            addCriterion("CREATE_ROLE not in", values, "createRole");
            return this;
        }

        public Criteria andCreateRoleBetween(String value1, String value2) {
            addCriterion("CREATE_ROLE between", value1, value2, "createRole");
            return this;
        }

        public Criteria andCreateRoleNotBetween(String value1, String value2) {
            addCriterion("CREATE_ROLE not between", value1, value2, "createRole");
            return this;
        }

        public Criteria andIsWholebankIsNull() {
            addCriterion("IS_WHOLEBANK is null");
            return this;
        }

        public Criteria andIsWholebankIsNotNull() {
            addCriterion("IS_WHOLEBANK is not null");
            return this;
        }

        public Criteria andIsWholebankEqualTo(String value) {
            addCriterion("IS_WHOLEBANK =", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankNotEqualTo(String value) {
            addCriterion("IS_WHOLEBANK <>", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankGreaterThan(String value) {
            addCriterion("IS_WHOLEBANK >", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankGreaterThanOrEqualTo(String value) {
            addCriterion("IS_WHOLEBANK >=", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankLessThan(String value) {
            addCriterion("IS_WHOLEBANK <", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankLessThanOrEqualTo(String value) {
            addCriterion("IS_WHOLEBANK <=", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankLike(String value) {
            addCriterion("IS_WHOLEBANK like", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankNotLike(String value) {
            addCriterion("IS_WHOLEBANK not like", value, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankIn(List values) {
            addCriterion("IS_WHOLEBANK in", values, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankNotIn(List values) {
            addCriterion("IS_WHOLEBANK not in", values, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankBetween(String value1, String value2) {
            addCriterion("IS_WHOLEBANK between", value1, value2, "isWholebank");
            return this;
        }

        public Criteria andIsWholebankNotBetween(String value1, String value2) {
            addCriterion("IS_WHOLEBANK not between", value1, value2, "isWholebank");
            return this;
        }
    }
}