package com.gotop.util.criteria.impl;

import java.util.List;

public class CriteriaTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = 3412582924550161768L;
	private String _entity;
	private SelectTypeImpl _select;
	private List<ExprTypeImpl> _expr;
	private List<LogicTypeImpl> _and;
	private List<LogicTypeImpl> _or;
	private List<LogicTypeImpl> _not;
	private List<OrderTypeImpl> _order;
	private List<OrderbyTypeImpl> _orderby;
	private String _group;
	private Boolean _distinct;
	private List<String> _association;
	private List<RefTypeImpl> _ref;
	private String _lock;
	private String _property;
	private String _value;
	
	public String get_entity() {
		return _entity;
	}
	public void set_entity(String _entity) {
		this._entity = _entity;
	}
	public SelectTypeImpl get_select() {
		return _select;
	}
	public void set_select(SelectTypeImpl _select) {
		this._select = _select;
	}
	public List<ExprTypeImpl> get_expr() {
		return _expr;
	}
	public void set_expr(List<ExprTypeImpl> _expr) {
		this._expr = _expr;
	}
	public List<LogicTypeImpl> get_and() {
		return _and;
	}
	public void set_and(List<LogicTypeImpl> _and) {
		this._and = _and;
	}
	public List<LogicTypeImpl> get_or() {
		return _or;
	}
	public void set_or(List<LogicTypeImpl> _or) {
		this._or = _or;
	}
	public List<LogicTypeImpl> get_not() {
		return _not;
	}
	public void set_not(List<LogicTypeImpl> _not) {
		this._not = _not;
	}
	public List<OrderTypeImpl> get_order() {
		return _order;
	}
	public void set_order(List<OrderTypeImpl> _order) {
		this._order = _order;
	}
	public List<OrderbyTypeImpl> get_orderby() {
		return _orderby;
	}
	public void set_orderby(List<OrderbyTypeImpl> _orderby) {
		this._orderby = _orderby;
	}
	public String get_group() {
		return _group;
	}
	public void set_group(String _group) {
		this._group = _group;
	}
	public Boolean get_distinct() {
		return _distinct;
	}
	public void set_distinct(Boolean _distinct) {
		this._distinct = _distinct;
	}
	public List<String> get_association() {
		return _association;
	}
	public void set_association(List<String> _association) {
		this._association = _association;
	}
	public List<RefTypeImpl> get_ref() {
		return _ref;
	}
	public void set_ref(List<RefTypeImpl> _ref) {
		this._ref = _ref;
	}
	public String get_lock() {
		return _lock;
	}
	public void set_lock(String _lock) {
		this._lock = _lock;
	}
	public String get_property() {
		return _property;
	}
	public void set_property(String _property) {
		this._property = _property;
	}
	public String get_value() {
		return _value;
	}
	public void set_value(String _value) {
		this._value = _value;
	}
}