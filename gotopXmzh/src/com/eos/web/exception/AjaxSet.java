/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2011-10-5
 *******************************************************************************/

package com.eos.web.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;

public class AjaxSet {

	public static final String customType = "__type";
	protected Map values;
	protected HttpServletRequest request;
	private List customVars;
	private Document xmlDom;

	public AjaxSet(HttpServletRequest request) {
		values = new HashMap();
		customVars = new ArrayList();
		this.request = request;
	}

	private static Class forName(String className) {
		try {
			return Class.forName(className);
		} catch (Exception e) {
		}
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(
					className);
		} catch (Exception e1) {
			return null;
		}
	}

	public void setXmlDom(Document xmlDom) {
		this.xmlDom = xmlDom;
	}

	public Map getValues() {
		return values;
	}

	public Document getXmlDom() {
		return xmlDom;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void init() {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("---"+json);
	}
	
	public void ssss(){
		
		
	}
}
