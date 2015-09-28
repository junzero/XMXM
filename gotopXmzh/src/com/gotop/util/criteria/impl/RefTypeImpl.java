package com.gotop.util.criteria.impl;

import java.util.List;

public class RefTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = -8831753410746455709L;
	private String _id;
	private String _entity;
	private SelectTypeImpl _select;
	private List<ExprTypeImpl> _expr;
	private List<LogicTypeImpl> _and;
	private List<LogicTypeImpl> _or;
	private List<LogicTypeImpl> _not;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

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

}