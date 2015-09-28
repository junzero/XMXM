package com.gotop.jbpm.dto;

import org.json.JSONObject;

public class RecDto {

	//节点类型
	private String type;
	
	//节点显示名称
	private JSONObject text;
	
	//节点坐标属性
	private JSONObject attr;
	
	//节点属性
	private JSONObject props;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JSONObject getText() {
		return text;
	}

	public void setText(JSONObject text) {
		this.text = text;
	}

	public JSONObject getAttr() {
		return attr;
	}

	public void setAttr(JSONObject attr) {
		this.attr = attr;
	}

	public JSONObject getProps() {
		return props;
	}

	public void setProps(JSONObject props) {
		this.props = props;
	}
	
}
