package com.gotop.util.hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class Prop extends Property{
	private static final long serialVersionUID = 284430480744737681L;
	protected Prop(String propertyName) {
		super(propertyName);
	}
	public Criterion between(Object min, Object max) {
		if(min==null && max==null){
			return null;
		}
		return Restrictions.between(getPropertyName(), min, max);
	}
	public static Criterion between(String propertyName,Object min, Object max) {
		if(min==null && max==null){
			return null;
		}
		return Restrictions.between(propertyName, min, max);
	}
	public Criterion in(Collection values) {
		if(values ==null){
			return null;
		}
		return Restrictions.in(getPropertyName(), values);
	}
	public static Criterion in(String propertyName,Collection values) {
		if(values ==null){
			return null;
		}
		return Restrictions.in(propertyName, values);
	}

	public Criterion in(Object[] values) {
		if(values==null){
			return null;
		}
		return Restrictions.in(getPropertyName(), values);
	}
	
	public static Criterion in(String propertyName,Object[] values) {
		if(values==null){
			return null;
		}
		return Restrictions.in(propertyName, values);
	}

	public SimpleExpression like(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.like(getPropertyName(), value);
	}
	
	public static SimpleExpression like(String propertyName,Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.like(propertyName, value);
	}
	

	public SimpleExpression like(String value, MatchMode matchMode) {
		if(value==null){
			return null;
		}
		return Restrictions.like(getPropertyName(), value, matchMode);
	}
	
	public static SimpleExpression like(String propertyName, String value, MatchMode matchMode) {
		if(value==null){
			return null;
		}
		return Restrictions.like(propertyName, value, matchMode);
	}

	public SimpleExpression eq(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.eq(getPropertyName(), value);
	}
	
	public static SimpleExpression eq(String propertyName,Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.eq(propertyName, value);
	}

	public SimpleExpression ne(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.ne(getPropertyName(), value);
	}
	
	public static SimpleExpression ne(String propertyName, Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.ne(propertyName, value);
	}

	public SimpleExpression gt(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.gt(getPropertyName(), value);
	}
	
	public static SimpleExpression gt(String propertyName, Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.gt(propertyName, value);
	}

	public SimpleExpression lt(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.lt(getPropertyName(), value);
	}
	
	public static SimpleExpression lt(String propertyName,Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.lt(propertyName, value);
	}

	public SimpleExpression le(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.le(getPropertyName(), value);
	}
	
	public static SimpleExpression le(String propertyName,Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.le(propertyName, value);
	}

	public SimpleExpression ge(Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.ge(getPropertyName(), value);
	}
	
	public static SimpleExpression ge(String propertyName,Object value) {
		if(value==null){
			return null;
		}
		return Restrictions.ge(propertyName, value);
	}
	public static Criterion not(Criterion expression) {
		return Expression.not(expression);
	}
	/**
	 * 扩展or
	 * @param lhs
	 * @return
	 */
	public static Criterion or(Criterion... lhs){
		int lhsLen = 0;
		if(lhs==null || (lhsLen = lhs.length)<1){
			return null;
		}
		List<Criterion> result = new ArrayList<Criterion>();
		for (int i = 0; i < lhs.length; i++) {
			if(lhs[i]!=null){//移除空项
				result.add(lhs[i]);
			}
		}
		lhsLen = result.size();
		if(lhsLen<1){
			return null;
		}
		if(lhsLen<2){
			return result.get(0);
		}
		Criterion ends = null;
		ends = Expression.or(result.get(lhsLen-1),result.get(lhsLen-2));
		for (int i = lhsLen-3; i >= 0; i--) {
			ends = Expression.or(result.get(i),ends);
		}
		return ends;
	}
	/**
	 * 扩展and
	 * @param lhs
	 * @return
	 */
	public static Criterion and(Criterion... lhs){
		int lhsLen = 0;
		if(lhs==null || (lhsLen = lhs.length)<1){
			return null;
		}
		List<Criterion> result = new ArrayList<Criterion>();
		for (int i = 0; i < lhs.length; i++) {
			if(lhs[i]!=null){//移除空项
				result.add(lhs[i]);
			}
		}
		lhsLen = result.size();
		if(lhsLen<1){
			return null;
		}
		if(lhsLen<2){
			return result.get(0);
		}
		Criterion ends = null;
		ends = Expression.and(result.get(lhsLen-1),result.get(lhsLen-2));
		for (int i = lhsLen-3; i >= 0; i--) {
			ends = Expression.and(result.get(i),ends);
		}
		return ends;
	}
	/**
	 * 扩展not
	 * @param lhs
	 * @return
	 */
	public static Criterion not(Criterion... lhs){
		int lhsLen = 0;
		if(lhs==null || (lhsLen = lhs.length)<1){
			return null;
		}
		List<Criterion> result = new ArrayList<Criterion>();
		for (int i = 0; i < lhs.length; i++) {
			if(lhs[i]!=null){//移除空项
				result.add(lhs[i]);
			}
		}
		lhsLen = result.size();
		if(lhsLen<1){
			return null;
		}
		if(lhsLen<2){
			return result.get(0);
		}
		//如果存在多项，则先and起来，再not
		Criterion ends = null;
		ends = Expression.and(result.get(lhsLen-1),result.get(lhsLen-2));
		for (int i = lhsLen-3; i >= 0; i--) {
			ends = Expression.and(result.get(i),ends);
		}
		ends = Expression.not(ends);
		return ends;
	}
	public Date DateFormat(Object date,String pattern) throws Exception{
		if(date==null){
			return null;
		}else if(date instanceof Date){
			return (Date)date;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date datef = dateFormat.parse(String.valueOf(date));
		return datef;
	}
	
	public static Projection groupProp(String propertyName){
		Projection proj = Projections.groupProperty(propertyName);
		return proj;
	}
	
	public static Projection rowCount(){
		return Projections.rowCount();
	}
}
