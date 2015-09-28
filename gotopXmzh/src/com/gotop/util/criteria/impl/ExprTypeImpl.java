package com.gotop.util.criteria.impl;

import java.util.HashMap;

public class ExprTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = 7813550210939830352L;
	private String _op;
	private String _min;
	private String _max;
	private String _likeRule;
	private String _dateRule;
	private String _pattern;
	private int _year;
	private int _quarter;
	private int _month;
	private String _opProperty;
	private String _ref;
	private boolean _processNullValue;
	private String _property;
	private String _value;
	
	private HashMap<String,String> property;

	public String get_op() {
		return _op;
	}

	public void set_op(String _op) {
		this._op = _op;
	}

	public String get_min() {
		return _min;
	}

	public void set_min(String _min) {
		this._min = _min;
	}

	public String get_max() {
		return _max;
	}

	public void set_max(String _max) {
		this._max = _max;
	}

	public String get_likeRule() {
		return _likeRule;
	}

	public void set_likeRule(String rule) {
		_likeRule = rule;
	}

	public String get_dateRule() {
		return _dateRule;
	}

	public void set_dateRule(String rule) {
		_dateRule = rule;
	}

	public String get_pattern() {
		return _pattern;
	}

	public void set_pattern(String _pattern) {
		this._pattern = _pattern;
	}

	public int get_year() {
		return _year;
	}

	public void set_year(int _year) {
		this._year = _year;
	}

	public int get_quarter() {
		return _quarter;
	}

	public void set_quarter(int _quarter) {
		this._quarter = _quarter;
	}

	public int get_month() {
		return _month;
	}

	public void set_month(int _month) {
		this._month = _month;
	}

	public String get_opProperty() {
		return _opProperty;
	}

	public void set_opProperty(String property) {
		_opProperty = property;
	}

	public String get_ref() {
		return _ref;
	}

	public void set_ref(String _ref) {
		this._ref = _ref;
	}

	public boolean is_processNullValue() {
		return _processNullValue;
	}

	public void set_processNullValue(boolean nullValue) {
		_processNullValue = nullValue;
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

	public HashMap<String, String> getProperty() {
		return property;
	}

	public void setProperty(HashMap<String, String> property) {
		this.property = property;
	}
	
}