package com.primeton.utils;

import java.util.HashMap;

import org.w3c.dom.Node;

import org.w3c.dom.Document;

public class AjaxParam {
	//分面信息
	private Page page = new Page();
	//ajax通过params方式提交来的数据
	private HashMap params = new HashMap();
	private Node criteria;
	private Document document;
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public HashMap getParams() {
		return params;
	}

	public void setParams(HashMap params) {
		this.params = params;
	}

	public Node getCriteria() {
		return criteria;
	}

	public void setCriteria(Node criteria) {
		this.criteria = criteria;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
