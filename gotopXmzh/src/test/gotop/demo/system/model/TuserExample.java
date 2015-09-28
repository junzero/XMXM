package test.gotop.demo.system.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuserExample {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public TuserExample() {
		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	protected TuserExample(TuserExample example) {
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public List getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	public void clear() {
		oredCriteria.clear();
	}

	/**
	 * This class was generated by Abator for iBATIS. This class corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List values,
				String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			List list = new ArrayList();
			list.add(value1);
			list.add(value2);
			Map map = new HashMap();
			map.put("condition", condition);
			map.put("values", list);
			criteriaWithBetweenValue.add(map);
		}

		public Criteria andUsidIsNull() {
			addCriterion("USID is null");
			return this;
		}

		public Criteria andUsidIsNotNull() {
			addCriterion("USID is not null");
			return this;
		}

		public Criteria andUsidEqualTo(BigDecimal value) {
			addCriterion("USID =", value, "usid");
			return this;
		}

		public Criteria andUsidNotEqualTo(BigDecimal value) {
			addCriterion("USID <>", value, "usid");
			return this;
		}

		public Criteria andUsidGreaterThan(BigDecimal value) {
			addCriterion("USID >", value, "usid");
			return this;
		}

		public Criteria andUsidGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("USID >=", value, "usid");
			return this;
		}

		public Criteria andUsidLessThan(BigDecimal value) {
			addCriterion("USID <", value, "usid");
			return this;
		}

		public Criteria andUsidLessThanOrEqualTo(BigDecimal value) {
			addCriterion("USID <=", value, "usid");
			return this;
		}

		public Criteria andUsidIn(List values) {
			addCriterion("USID in", values, "usid");
			return this;
		}

		public Criteria andUsidNotIn(List values) {
			addCriterion("USID not in", values, "usid");
			return this;
		}

		public Criteria andUsidBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("USID between", value1, value2, "usid");
			return this;
		}

		public Criteria andUsidNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("USID not between", value1, value2, "usid");
			return this;
		}

		public Criteria andPwdIsNull() {
			addCriterion("PWD is null");
			return this;
		}

		public Criteria andPwdIsNotNull() {
			addCriterion("PWD is not null");
			return this;
		}

		public Criteria andPwdEqualTo(String value) {
			addCriterion("PWD =", value, "pwd");
			return this;
		}

		public Criteria andPwdNotEqualTo(String value) {
			addCriterion("PWD <>", value, "pwd");
			return this;
		}

		public Criteria andPwdGreaterThan(String value) {
			addCriterion("PWD >", value, "pwd");
			return this;
		}

		public Criteria andPwdGreaterThanOrEqualTo(String value) {
			addCriterion("PWD >=", value, "pwd");
			return this;
		}

		public Criteria andPwdLessThan(String value) {
			addCriterion("PWD <", value, "pwd");
			return this;
		}

		public Criteria andPwdLessThanOrEqualTo(String value) {
			addCriterion("PWD <=", value, "pwd");
			return this;
		}

		public Criteria andPwdLike(String value) {
			addCriterion("PWD like", value, "pwd");
			return this;
		}

		public Criteria andPwdNotLike(String value) {
			addCriterion("PWD not like", value, "pwd");
			return this;
		}

		public Criteria andPwdIn(List values) {
			addCriterion("PWD in", values, "pwd");
			return this;
		}

		public Criteria andPwdNotIn(List values) {
			addCriterion("PWD not in", values, "pwd");
			return this;
		}

		public Criteria andPwdBetween(String value1, String value2) {
			addCriterion("PWD between", value1, value2, "pwd");
			return this;
		}

		public Criteria andPwdNotBetween(String value1, String value2) {
			addCriterion("PWD not between", value1, value2, "pwd");
			return this;
		}

		public Criteria andZcsjIsNull() {
			addCriterion("ZCSJ is null");
			return this;
		}

		public Criteria andZcsjIsNotNull() {
			addCriterion("ZCSJ is not null");
			return this;
		}

		public Criteria andZcsjEqualTo(Date value) {
			addCriterion("ZCSJ =", value, "zcsj");
			return this;
		}

		public Criteria andZcsjNotEqualTo(Date value) {
			addCriterion("ZCSJ <>", value, "zcsj");
			return this;
		}

		public Criteria andZcsjGreaterThan(Date value) {
			addCriterion("ZCSJ >", value, "zcsj");
			return this;
		}

		public Criteria andZcsjGreaterThanOrEqualTo(Date value) {
			addCriterion("ZCSJ >=", value, "zcsj");
			return this;
		}

		public Criteria andZcsjLessThan(Date value) {
			addCriterion("ZCSJ <", value, "zcsj");
			return this;
		}

		public Criteria andZcsjLessThanOrEqualTo(Date value) {
			addCriterion("ZCSJ <=", value, "zcsj");
			return this;
		}

		public Criteria andZcsjIn(List values) {
			addCriterion("ZCSJ in", values, "zcsj");
			return this;
		}

		public Criteria andZcsjNotIn(List values) {
			addCriterion("ZCSJ not in", values, "zcsj");
			return this;
		}

		public Criteria andZcsjBetween(Date value1, Date value2) {
			addCriterion("ZCSJ between", value1, value2, "zcsj");
			return this;
		}

		public Criteria andZcsjNotBetween(Date value1, Date value2) {
			addCriterion("ZCSJ not between", value1, value2, "zcsj");
			return this;
		}

		public Criteria andGmzqIsNull() {
			addCriterion("GMZQ is null");
			return this;
		}

		public Criteria andGmzqIsNotNull() {
			addCriterion("GMZQ is not null");
			return this;
		}

		public Criteria andGmzqEqualTo(BigDecimal value) {
			addCriterion("GMZQ =", value, "gmzq");
			return this;
		}

		public Criteria andGmzqNotEqualTo(BigDecimal value) {
			addCriterion("GMZQ <>", value, "gmzq");
			return this;
		}

		public Criteria andGmzqGreaterThan(BigDecimal value) {
			addCriterion("GMZQ >", value, "gmzq");
			return this;
		}

		public Criteria andGmzqGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("GMZQ >=", value, "gmzq");
			return this;
		}

		public Criteria andGmzqLessThan(BigDecimal value) {
			addCriterion("GMZQ <", value, "gmzq");
			return this;
		}

		public Criteria andGmzqLessThanOrEqualTo(BigDecimal value) {
			addCriterion("GMZQ <=", value, "gmzq");
			return this;
		}

		public Criteria andGmzqIn(List values) {
			addCriterion("GMZQ in", values, "gmzq");
			return this;
		}

		public Criteria andGmzqNotIn(List values) {
			addCriterion("GMZQ not in", values, "gmzq");
			return this;
		}

		public Criteria andGmzqBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("GMZQ between", value1, value2, "gmzq");
			return this;
		}

		public Criteria andGmzqNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("GMZQ not between", value1, value2, "gmzq");
			return this;
		}

		public Criteria andXzqxIsNull() {
			addCriterion("XZQX is null");
			return this;
		}

		public Criteria andXzqxIsNotNull() {
			addCriterion("XZQX is not null");
			return this;
		}

		public Criteria andXzqxEqualTo(BigDecimal value) {
			addCriterion("XZQX =", value, "xzqx");
			return this;
		}

		public Criteria andXzqxNotEqualTo(BigDecimal value) {
			addCriterion("XZQX <>", value, "xzqx");
			return this;
		}

		public Criteria andXzqxGreaterThan(BigDecimal value) {
			addCriterion("XZQX >", value, "xzqx");
			return this;
		}

		public Criteria andXzqxGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("XZQX >=", value, "xzqx");
			return this;
		}

		public Criteria andXzqxLessThan(BigDecimal value) {
			addCriterion("XZQX <", value, "xzqx");
			return this;
		}

		public Criteria andXzqxLessThanOrEqualTo(BigDecimal value) {
			addCriterion("XZQX <=", value, "xzqx");
			return this;
		}

		public Criteria andXzqxIn(List values) {
			addCriterion("XZQX in", values, "xzqx");
			return this;
		}

		public Criteria andXzqxNotIn(List values) {
			addCriterion("XZQX not in", values, "xzqx");
			return this;
		}

		public Criteria andXzqxBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("XZQX between", value1, value2, "xzqx");
			return this;
		}

		public Criteria andXzqxNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("XZQX not between", value1, value2, "xzqx");
			return this;
		}

		public Criteria andYhncIsNull() {
			addCriterion("YHNC is null");
			return this;
		}

		public Criteria andYhncIsNotNull() {
			addCriterion("YHNC is not null");
			return this;
		}

		public Criteria andYhncEqualTo(String value) {
			addCriterion("YHNC =", value, "yhnc");
			return this;
		}

		public Criteria andYhncNotEqualTo(String value) {
			addCriterion("YHNC <>", value, "yhnc");
			return this;
		}

		public Criteria andYhncGreaterThan(String value) {
			addCriterion("YHNC >", value, "yhnc");
			return this;
		}

		public Criteria andYhncGreaterThanOrEqualTo(String value) {
			addCriterion("YHNC >=", value, "yhnc");
			return this;
		}

		public Criteria andYhncLessThan(String value) {
			addCriterion("YHNC <", value, "yhnc");
			return this;
		}

		public Criteria andYhncLessThanOrEqualTo(String value) {
			addCriterion("YHNC <=", value, "yhnc");
			return this;
		}

		public Criteria andYhncLike(String value) {
			addCriterion("YHNC like", value, "yhnc");
			return this;
		}

		public Criteria andYhncNotLike(String value) {
			addCriterion("YHNC not like", value, "yhnc");
			return this;
		}

		public Criteria andYhncIn(List values) {
			addCriterion("YHNC in", values, "yhnc");
			return this;
		}

		public Criteria andYhncNotIn(List values) {
			addCriterion("YHNC not in", values, "yhnc");
			return this;
		}

		public Criteria andYhncBetween(String value1, String value2) {
			addCriterion("YHNC between", value1, value2, "yhnc");
			return this;
		}

		public Criteria andYhncNotBetween(String value1, String value2) {
			addCriterion("YHNC not between", value1, value2, "yhnc");
			return this;
		}

		public Criteria andYhmcIsNull() {
			addCriterion("YHMC is null");
			return this;
		}

		public Criteria andYhmcIsNotNull() {
			addCriterion("YHMC is not null");
			return this;
		}

		public Criteria andYhmcEqualTo(String value) {
			addCriterion("YHMC =", value, "yhmc");
			return this;
		}

		public Criteria andYhmcNotEqualTo(String value) {
			addCriterion("YHMC <>", value, "yhmc");
			return this;
		}

		public Criteria andYhmcGreaterThan(String value) {
			addCriterion("YHMC >", value, "yhmc");
			return this;
		}

		public Criteria andYhmcGreaterThanOrEqualTo(String value) {
			addCriterion("YHMC >=", value, "yhmc");
			return this;
		}

		public Criteria andYhmcLessThan(String value) {
			addCriterion("YHMC <", value, "yhmc");
			return this;
		}

		public Criteria andYhmcLessThanOrEqualTo(String value) {
			addCriterion("YHMC <=", value, "yhmc");
			return this;
		}

		public Criteria andYhmcLike(String value) {
			addCriterion("YHMC like", value, "yhmc");
			return this;
		}

		public Criteria andYhmcNotLike(String value) {
			addCriterion("YHMC not like", value, "yhmc");
			return this;
		}

		public Criteria andYhmcIn(List values) {
			addCriterion("YHMC in", values, "yhmc");
			return this;
		}

		public Criteria andYhmcNotIn(List values) {
			addCriterion("YHMC not in", values, "yhmc");
			return this;
		}

		public Criteria andYhmcBetween(String value1, String value2) {
			addCriterion("YHMC between", value1, value2, "yhmc");
			return this;
		}

		public Criteria andYhmcNotBetween(String value1, String value2) {
			addCriterion("YHMC not between", value1, value2, "yhmc");
			return this;
		}

		public Criteria andDlcsIsNull() {
			addCriterion("DLCS is null");
			return this;
		}

		public Criteria andDlcsIsNotNull() {
			addCriterion("DLCS is not null");
			return this;
		}

		public Criteria andDlcsEqualTo(BigDecimal value) {
			addCriterion("DLCS =", value, "dlcs");
			return this;
		}

		public Criteria andDlcsNotEqualTo(BigDecimal value) {
			addCriterion("DLCS <>", value, "dlcs");
			return this;
		}

		public Criteria andDlcsGreaterThan(BigDecimal value) {
			addCriterion("DLCS >", value, "dlcs");
			return this;
		}

		public Criteria andDlcsGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("DLCS >=", value, "dlcs");
			return this;
		}

		public Criteria andDlcsLessThan(BigDecimal value) {
			addCriterion("DLCS <", value, "dlcs");
			return this;
		}

		public Criteria andDlcsLessThanOrEqualTo(BigDecimal value) {
			addCriterion("DLCS <=", value, "dlcs");
			return this;
		}

		public Criteria andDlcsIn(List values) {
			addCriterion("DLCS in", values, "dlcs");
			return this;
		}

		public Criteria andDlcsNotIn(List values) {
			addCriterion("DLCS not in", values, "dlcs");
			return this;
		}

		public Criteria andDlcsBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("DLCS between", value1, value2, "dlcs");
			return this;
		}

		public Criteria andDlcsNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("DLCS not between", value1, value2, "dlcs");
			return this;
		}

		public Criteria andYhztIsNull() {
			addCriterion("YHZT is null");
			return this;
		}

		public Criteria andYhztIsNotNull() {
			addCriterion("YHZT is not null");
			return this;
		}

		public Criteria andYhztEqualTo(BigDecimal value) {
			addCriterion("YHZT =", value, "yhzt");
			return this;
		}

		public Criteria andYhztNotEqualTo(BigDecimal value) {
			addCriterion("YHZT <>", value, "yhzt");
			return this;
		}

		public Criteria andYhztGreaterThan(BigDecimal value) {
			addCriterion("YHZT >", value, "yhzt");
			return this;
		}

		public Criteria andYhztGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("YHZT >=", value, "yhzt");
			return this;
		}

		public Criteria andYhztLessThan(BigDecimal value) {
			addCriterion("YHZT <", value, "yhzt");
			return this;
		}

		public Criteria andYhztLessThanOrEqualTo(BigDecimal value) {
			addCriterion("YHZT <=", value, "yhzt");
			return this;
		}

		public Criteria andYhztIn(List values) {
			addCriterion("YHZT in", values, "yhzt");
			return this;
		}

		public Criteria andYhztNotIn(List values) {
			addCriterion("YHZT not in", values, "yhzt");
			return this;
		}

		public Criteria andYhztBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("YHZT between", value1, value2, "yhzt");
			return this;
		}

		public Criteria andYhztNotBetween(BigDecimal value1, BigDecimal value2) {
			addCriterion("YHZT not between", value1, value2, "yhzt");
			return this;
		}

		public Criteria andBzIsNull() {
			addCriterion("BZ is null");
			return this;
		}

		public Criteria andBzIsNotNull() {
			addCriterion("BZ is not null");
			return this;
		}

		public Criteria andBzEqualTo(String value) {
			addCriterion("BZ =", value, "bz");
			return this;
		}

		public Criteria andBzNotEqualTo(String value) {
			addCriterion("BZ <>", value, "bz");
			return this;
		}

		public Criteria andBzGreaterThan(String value) {
			addCriterion("BZ >", value, "bz");
			return this;
		}

		public Criteria andBzGreaterThanOrEqualTo(String value) {
			addCriterion("BZ >=", value, "bz");
			return this;
		}

		public Criteria andBzLessThan(String value) {
			addCriterion("BZ <", value, "bz");
			return this;
		}

		public Criteria andBzLessThanOrEqualTo(String value) {
			addCriterion("BZ <=", value, "bz");
			return this;
		}

		public Criteria andBzLike(String value) {
			addCriterion("BZ like", value, "bz");
			return this;
		}

		public Criteria andBzNotLike(String value) {
			addCriterion("BZ not like", value, "bz");
			return this;
		}

		public Criteria andBzIn(List values) {
			addCriterion("BZ in", values, "bz");
			return this;
		}

		public Criteria andBzNotIn(List values) {
			addCriterion("BZ not in", values, "bz");
			return this;
		}

		public Criteria andBzBetween(String value1, String value2) {
			addCriterion("BZ between", value1, value2, "bz");
			return this;
		}

		public Criteria andBzNotBetween(String value1, String value2) {
			addCriterion("BZ not between", value1, value2, "bz");
			return this;
		}

		public Criteria andLidIsNull() {
			addCriterion("LID is null");
			return this;
		}

		public Criteria andLidIsNotNull() {
			addCriterion("LID is not null");
			return this;
		}

		public Criteria andLidEqualTo(String value) {
			addCriterion("LID =", value, "lid");
			return this;
		}

		public Criteria andLidNotEqualTo(String value) {
			addCriterion("LID <>", value, "lid");
			return this;
		}

		public Criteria andLidGreaterThan(String value) {
			addCriterion("LID >", value, "lid");
			return this;
		}

		public Criteria andLidGreaterThanOrEqualTo(String value) {
			addCriterion("LID >=", value, "lid");
			return this;
		}

		public Criteria andLidLessThan(String value) {
			addCriterion("LID <", value, "lid");
			return this;
		}

		public Criteria andLidLessThanOrEqualTo(String value) {
			addCriterion("LID <=", value, "lid");
			return this;
		}

		public Criteria andLidLike(String value) {
			addCriterion("LID like", value, "lid");
			return this;
		}

		public Criteria andLidNotLike(String value) {
			addCriterion("LID not like", value, "lid");
			return this;
		}

		public Criteria andLidIn(List values) {
			addCriterion("LID in", values, "lid");
			return this;
		}

		public Criteria andLidNotIn(List values) {
			addCriterion("LID not in", values, "lid");
			return this;
		}

		public Criteria andLidBetween(String value1, String value2) {
			addCriterion("LID between", value1, value2, "lid");
			return this;
		}

		public Criteria andLidNotBetween(String value1, String value2) {
			addCriterion("LID not between", value1, value2, "lid");
			return this;
		}

		public Criteria andOrgidIsNull() {
			addCriterion("ORGID is null");
			return this;
		}

		public Criteria andOrgidIsNotNull() {
			addCriterion("ORGID is not null");
			return this;
		}

		public Criteria andOrgidEqualTo(String value) {
			addCriterion("ORGID =", value, "orgid");
			return this;
		}

		public Criteria andOrgidNotEqualTo(String value) {
			addCriterion("ORGID <>", value, "orgid");
			return this;
		}

		public Criteria andOrgidGreaterThan(String value) {
			addCriterion("ORGID >", value, "orgid");
			return this;
		}

		public Criteria andOrgidGreaterThanOrEqualTo(String value) {
			addCriterion("ORGID >=", value, "orgid");
			return this;
		}

		public Criteria andOrgidLessThan(String value) {
			addCriterion("ORGID <", value, "orgid");
			return this;
		}

		public Criteria andOrgidLessThanOrEqualTo(String value) {
			addCriterion("ORGID <=", value, "orgid");
			return this;
		}

		public Criteria andOrgidLike(String value) {
			addCriterion("ORGID like", value, "orgid");
			return this;
		}

		public Criteria andOrgidNotLike(String value) {
			addCriterion("ORGID not like", value, "orgid");
			return this;
		}

		public Criteria andOrgidIn(List values) {
			addCriterion("ORGID in", values, "orgid");
			return this;
		}

		public Criteria andOrgidNotIn(List values) {
			addCriterion("ORGID not in", values, "orgid");
			return this;
		}

		public Criteria andOrgidBetween(String value1, String value2) {
			addCriterion("ORGID between", value1, value2, "orgid");
			return this;
		}

		public Criteria andOrgidNotBetween(String value1, String value2) {
			addCriterion("ORGID not between", value1, value2, "orgid");
			return this;
		}

		public Criteria andEmailIsNull() {
			addCriterion("EMAIL is null");
			return this;
		}

		public Criteria andEmailIsNotNull() {
			addCriterion("EMAIL is not null");
			return this;
		}

		public Criteria andEmailEqualTo(String value) {
			addCriterion("EMAIL =", value, "email");
			return this;
		}

		public Criteria andEmailNotEqualTo(String value) {
			addCriterion("EMAIL <>", value, "email");
			return this;
		}

		public Criteria andEmailGreaterThan(String value) {
			addCriterion("EMAIL >", value, "email");
			return this;
		}

		public Criteria andEmailGreaterThanOrEqualTo(String value) {
			addCriterion("EMAIL >=", value, "email");
			return this;
		}

		public Criteria andEmailLessThan(String value) {
			addCriterion("EMAIL <", value, "email");
			return this;
		}

		public Criteria andEmailLessThanOrEqualTo(String value) {
			addCriterion("EMAIL <=", value, "email");
			return this;
		}

		public Criteria andEmailLike(String value) {
			addCriterion("EMAIL like", value, "email");
			return this;
		}

		public Criteria andEmailNotLike(String value) {
			addCriterion("EMAIL not like", value, "email");
			return this;
		}

		public Criteria andEmailIn(List values) {
			addCriterion("EMAIL in", values, "email");
			return this;
		}

		public Criteria andEmailNotIn(List values) {
			addCriterion("EMAIL not in", values, "email");
			return this;
		}

		public Criteria andEmailBetween(String value1, String value2) {
			addCriterion("EMAIL between", value1, value2, "email");
			return this;
		}

		public Criteria andEmailNotBetween(String value1, String value2) {
			addCriterion("EMAIL not between", value1, value2, "email");
			return this;
		}

		public Criteria andMobileIsNull() {
			addCriterion("MOBILE is null");
			return this;
		}

		public Criteria andMobileIsNotNull() {
			addCriterion("MOBILE is not null");
			return this;
		}

		public Criteria andMobileEqualTo(String value) {
			addCriterion("MOBILE =", value, "mobile");
			return this;
		}

		public Criteria andMobileNotEqualTo(String value) {
			addCriterion("MOBILE <>", value, "mobile");
			return this;
		}

		public Criteria andMobileGreaterThan(String value) {
			addCriterion("MOBILE >", value, "mobile");
			return this;
		}

		public Criteria andMobileGreaterThanOrEqualTo(String value) {
			addCriterion("MOBILE >=", value, "mobile");
			return this;
		}

		public Criteria andMobileLessThan(String value) {
			addCriterion("MOBILE <", value, "mobile");
			return this;
		}

		public Criteria andMobileLessThanOrEqualTo(String value) {
			addCriterion("MOBILE <=", value, "mobile");
			return this;
		}

		public Criteria andMobileLike(String value) {
			addCriterion("MOBILE like", value, "mobile");
			return this;
		}

		public Criteria andMobileNotLike(String value) {
			addCriterion("MOBILE not like", value, "mobile");
			return this;
		}

		public Criteria andMobileIn(List values) {
			addCriterion("MOBILE in", values, "mobile");
			return this;
		}

		public Criteria andMobileNotIn(List values) {
			addCriterion("MOBILE not in", values, "mobile");
			return this;
		}

		public Criteria andMobileBetween(String value1, String value2) {
			addCriterion("MOBILE between", value1, value2, "mobile");
			return this;
		}

		public Criteria andMobileNotBetween(String value1, String value2) {
			addCriterion("MOBILE not between", value1, value2, "mobile");
			return this;
		}

		public Criteria andFaxIsNull() {
			addCriterion("FAX is null");
			return this;
		}

		public Criteria andFaxIsNotNull() {
			addCriterion("FAX is not null");
			return this;
		}

		public Criteria andFaxEqualTo(String value) {
			addCriterion("FAX =", value, "fax");
			return this;
		}

		public Criteria andFaxNotEqualTo(String value) {
			addCriterion("FAX <>", value, "fax");
			return this;
		}

		public Criteria andFaxGreaterThan(String value) {
			addCriterion("FAX >", value, "fax");
			return this;
		}

		public Criteria andFaxGreaterThanOrEqualTo(String value) {
			addCriterion("FAX >=", value, "fax");
			return this;
		}

		public Criteria andFaxLessThan(String value) {
			addCriterion("FAX <", value, "fax");
			return this;
		}

		public Criteria andFaxLessThanOrEqualTo(String value) {
			addCriterion("FAX <=", value, "fax");
			return this;
		}

		public Criteria andFaxLike(String value) {
			addCriterion("FAX like", value, "fax");
			return this;
		}

		public Criteria andFaxNotLike(String value) {
			addCriterion("FAX not like", value, "fax");
			return this;
		}

		public Criteria andFaxIn(List values) {
			addCriterion("FAX in", values, "fax");
			return this;
		}

		public Criteria andFaxNotIn(List values) {
			addCriterion("FAX not in", values, "fax");
			return this;
		}

		public Criteria andFaxBetween(String value1, String value2) {
			addCriterion("FAX between", value1, value2, "fax");
			return this;
		}

		public Criteria andFaxNotBetween(String value1, String value2) {
			addCriterion("FAX not between", value1, value2, "fax");
			return this;
		}

		public Criteria andRoletypeIsNull() {
			addCriterion("ROLETYPE is null");
			return this;
		}

		public Criteria andRoletypeIsNotNull() {
			addCriterion("ROLETYPE is not null");
			return this;
		}

		public Criteria andRoletypeEqualTo(String value) {
			addCriterion("ROLETYPE =", value, "roletype");
			return this;
		}

		public Criteria andRoletypeNotEqualTo(String value) {
			addCriterion("ROLETYPE <>", value, "roletype");
			return this;
		}

		public Criteria andRoletypeGreaterThan(String value) {
			addCriterion("ROLETYPE >", value, "roletype");
			return this;
		}

		public Criteria andRoletypeGreaterThanOrEqualTo(String value) {
			addCriterion("ROLETYPE >=", value, "roletype");
			return this;
		}

		public Criteria andRoletypeLessThan(String value) {
			addCriterion("ROLETYPE <", value, "roletype");
			return this;
		}

		public Criteria andRoletypeLessThanOrEqualTo(String value) {
			addCriterion("ROLETYPE <=", value, "roletype");
			return this;
		}

		public Criteria andRoletypeLike(String value) {
			addCriterion("ROLETYPE like", value, "roletype");
			return this;
		}

		public Criteria andRoletypeNotLike(String value) {
			addCriterion("ROLETYPE not like", value, "roletype");
			return this;
		}

		public Criteria andRoletypeIn(List values) {
			addCriterion("ROLETYPE in", values, "roletype");
			return this;
		}

		public Criteria andRoletypeNotIn(List values) {
			addCriterion("ROLETYPE not in", values, "roletype");
			return this;
		}

		public Criteria andRoletypeBetween(String value1, String value2) {
			addCriterion("ROLETYPE between", value1, value2, "roletype");
			return this;
		}

		public Criteria andRoletypeNotBetween(String value1, String value2) {
			addCriterion("ROLETYPE not between", value1, value2, "roletype");
			return this;
		}

		public Criteria andUsedateIsNull() {
			addCriterion("USEDATE is null");
			return this;
		}

		public Criteria andUsedateIsNotNull() {
			addCriterion("USEDATE is not null");
			return this;
		}

		public Criteria andUsedateEqualTo(Date value) {
			addCriterion("USEDATE =", value, "usedate");
			return this;
		}

		public Criteria andUsedateNotEqualTo(Date value) {
			addCriterion("USEDATE <>", value, "usedate");
			return this;
		}

		public Criteria andUsedateGreaterThan(Date value) {
			addCriterion("USEDATE >", value, "usedate");
			return this;
		}

		public Criteria andUsedateGreaterThanOrEqualTo(Date value) {
			addCriterion("USEDATE >=", value, "usedate");
			return this;
		}

		public Criteria andUsedateLessThan(Date value) {
			addCriterion("USEDATE <", value, "usedate");
			return this;
		}

		public Criteria andUsedateLessThanOrEqualTo(Date value) {
			addCriterion("USEDATE <=", value, "usedate");
			return this;
		}

		public Criteria andUsedateIn(List values) {
			addCriterion("USEDATE in", values, "usedate");
			return this;
		}

		public Criteria andUsedateNotIn(List values) {
			addCriterion("USEDATE not in", values, "usedate");
			return this;
		}

		public Criteria andUsedateBetween(Date value1, Date value2) {
			addCriterion("USEDATE between", value1, value2, "usedate");
			return this;
		}

		public Criteria andUsedateNotBetween(Date value1, Date value2) {
			addCriterion("USEDATE not between", value1, value2, "usedate");
			return this;
		}
	}
}