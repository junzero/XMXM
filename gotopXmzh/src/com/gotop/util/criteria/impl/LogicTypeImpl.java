package com.gotop.util.criteria.impl;

import java.util.List;

public class LogicTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = 6570629598831296099L;
	private List<LogicTypeImpl> _and;
	private List<LogicTypeImpl> _or;
	private List<LogicTypeImpl> _not;
	private List<ExprTypeImpl> _expr;

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

	public List<ExprTypeImpl> get_expr() {
		return _expr;
	}

	public void set_expr(List<ExprTypeImpl> _expr) {
		this._expr = _expr;
	}

}