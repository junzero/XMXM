package com.gotop.util.criteria.impl;

import java.util.List;

public class SelectTypeImpl implements java.io.Serializable{
	private static final long serialVersionUID = 2380550405811732684L;
	private List<String> _field;
	private List<String> _count;
	private List<String> _avg;
	private List<String> _sum;
	private List<String> _min;
	private List<String> _max;

	public List<String> get_field() {
		return _field;
	}

	public void set_field(List<String> _field) {
		this._field = _field;
	}

	public List<String> get_count() {
		return _count;
	}

	public void set_count(List<String> _count) {
		this._count = _count;
	}

	public List<String> get_avg() {
		return _avg;
	}

	public void set_avg(List<String> _avg) {
		this._avg = _avg;
	}

	public List<String> get_sum() {
		return _sum;
	}

	public void set_sum(List<String> _sum) {
		this._sum = _sum;
	}

	public List<String> get_min() {
		return _min;
	}

	public void set_min(List<String> _min) {
		this._min = _min;
	}

	public List<String> get_max() {
		return _max;
	}

	public void set_max(List<String> _max) {
		this._max = _max;
	}

}