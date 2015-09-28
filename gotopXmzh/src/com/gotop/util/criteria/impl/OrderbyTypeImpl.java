package com.gotop.util.criteria.impl;


public class OrderbyTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = 4770610025584641622L;
	private String _property;
	private String _sort;

	public String get_property() {
		return _property;
	}

	public void set_property(String _property) {
		this._property = _property;
	}

	public String get_sort() {
		return _sort;
	}

	public void set_sort(String _sort) {
		this._sort = _sort;
	}

}