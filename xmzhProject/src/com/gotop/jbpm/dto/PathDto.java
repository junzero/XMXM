package com.gotop.jbpm.dto;

import org.json.JSONArray;
import org.json.JSONObject;

public class PathDto {

	//发起节点
	private String from;
	
	//目标节点
	private String to;
	
	//显示名称
	private JSONObject text;
	
	//前进坐标属性
	private JSONObject textPos;
	
	//回退坐标属性
	private JSONArray dots;
	
	//属性
	private JSONObject props;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public JSONObject getText() {
		return text;
	}

	public void setText(JSONObject text) {
		this.text = text;
	}

	public JSONObject getTextPos() {
		return textPos;
	}

	public void setTextPos(JSONObject textPos) {
		this.textPos = textPos;
	}

	public JSONObject getProps() {
		return props;
	}

	public void setProps(JSONObject props) {
		this.props = props;
	}

	public JSONArray getDots() {
		return dots;
	}

	public void setDots(JSONArray dots) {
		this.dots = dots;
	}
	
}
