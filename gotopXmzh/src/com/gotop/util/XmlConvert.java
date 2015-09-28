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


package com.gotop.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eos.system.utility.XmlUtil;
import com.gotop.util.convertor.BigDecimalConvertor;
import com.gotop.util.convertor.BigDecimalQueryConvertor;
import com.gotop.util.convertor.BigIntegerConvertor;
import com.gotop.util.convertor.BigIntegerQueryConvertor;
import com.gotop.util.convertor.DoubleConvertor;
import com.gotop.util.convertor.DoubleQueryConvertor;
import com.gotop.util.convertor.FloatConvertor;
import com.gotop.util.convertor.FloatQueryConvertor;
import com.gotop.util.convertor.IntConvertor;
import com.gotop.util.convertor.IntQueryConvertor;
import com.gotop.util.convertor.LongConvertor;
import com.gotop.util.convertor.LongQueryConvertor;
import com.gotop.util.convertor.ShortConvertor;
import com.gotop.util.convertor.ShortQueryConvertor;
import com.gotop.util.convertor.TimestampsConvertor;
import com.gotop.util.export.ExcelUtil;
import com.gotop.util.file.JavaCallLogic;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
  
public class XmlConvert {
	private static final Log log = LogFactory.getLog(XmlConvert.class);
	/**
	 * 返回的对象为List<Bean>方式，仅针对datacell
	 * @param pageCond 分面对象
	 * @param beanList 需要返回的数据集，格式为List<Bean>
	 * @return 组织化的xml字符串
	 */
    public static String getXmlListBean(Page pageCond,List beanList,Object... params){   
        List results = new ArrayList(); //创建临时的 List 对象 results   
        if(pageCond!=null){
        	results.add(pageCond);
        }
        results.addAll(beanList); // 追加对象   
        XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
        StringBuffer smxml = new StringBuffer();
        sm.registerConverter(new BigDecimalQueryConvertor());
        sm.registerConverter(new BigIntegerQueryConvertor());
        sm.registerConverter(new DoubleQueryConvertor());
        sm.registerConverter(new FloatQueryConvertor());
        sm.registerConverter(new IntQueryConvertor());
        sm.registerConverter(new LongQueryConvertor());
        sm.registerConverter(new ShortQueryConvertor());
        sm.registerConverter(new TimestampsConvertor());
        sm.autodetectAnnotations(true);
        for(int i=0;i<results.size();i++){
        	if(results.get(i) instanceof Map){
        		Map map = (Map) results.get(i);
        		smxml.append("<map>");
        		for(Object key : map.keySet()){
        			Object valueObject = map.get(key);
        			if(valueObject==null){
        				valueObject="";
        			}
        			if(valueObject instanceof List){
        				smxml.append("<"+key+">"+getXmlListBeanBody((List)valueObject)+"</"+key+">");
        			}else{
        				String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
        				smxml.append("<"+key+">"+vot+"</"+key+">");
        			}
        		}
        		smxml.append("</map>");
        	}else{
        		smxml.append(sm.toXML(results.get(i)));
        	}
        }   
        smxml.append("<param>");
        for(int i=0;i<params.length;i++){
        	if(params[i] instanceof Map){
        		Map map = (Map) params[i];
        		smxml.append("<map>");
        		for(Object key : map.keySet()){
        			Object valueObject = map.get(key);
        			if(valueObject==null){
        				valueObject="";
        			}
        			if(valueObject instanceof List){
        				smxml.append("<"+key+">"+getXmlListBeanBody((List)valueObject)+"</"+key+">");
        			}else{
        				String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
        				smxml.append("<"+key+">"+vot+"</"+key+">");
        			}
        		}
        		smxml.append("</map>");
        	}else{
        		smxml.append(sm.toXML(params[i]));
        	}
        }   
        smxml.append("</param>");
        
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
        sbf.append(smxml);
        sbf.append("</data></root>");
        return sbf.toString();   
    }
    /**
     * 返回的对象为List<Bean>方式,无分页。有过滤完整路径
     * @param beanList
     * @return
     */
    public static String getXmlListBean(List... beanList){
    	XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
        sm.registerConverter(new BigDecimalQueryConvertor());
        sm.registerConverter(new BigIntegerQueryConvertor());
        sm.registerConverter(new DoubleQueryConvertor());
        sm.registerConverter(new FloatQueryConvertor());
        sm.registerConverter(new IntQueryConvertor());
        sm.registerConverter(new LongQueryConvertor());
        sm.registerConverter(new ShortQueryConvertor());
        sm.registerConverter(new TimestampsConvertor());
    	sm.autodetectAnnotations(true);
    	StringBuffer smxml = new StringBuffer();
    	for (int i = 0; i < beanList.length; i++) {
    		List resultList = beanList[i];
    		for(int j=0;j<resultList.size();j++){   
    			if(resultList.get(j) instanceof Map) {
    				Map map = (Map)resultList.get(j);
    				smxml.append("<map>");
    				for(Object key : map.keySet()){
    					Object valueObject = map.get(key);
    					if(valueObject==null){
    						valueObject="";
    					}
    					String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
    					smxml.append("<"+key+">"+vot+"</"+key+">");
    				}
    				smxml.append("</map>");
    			}else{
    				Class c = resultList.get(j).getClass();   
    				String b = c.getName();   
    				String[] temp = b.split("\\.");
    				sm.alias(temp[temp.length-1],c);
    				smxml.append(sm.toXML(resultList.get(j)));
    			}
    			
    		} 
    	}
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
    	sbf.append(smxml);
    	sbf.append("</data></root>");
    	return sbf.toString();
    }
    /**
     * 返回的对象为List<Bean>方式,无分页。有过滤完整路径,可直接进行导出
     * @param beanList
     * @return
     * @throws Exception 
     */
    public static String getXmlListBean(AjaxParam apm, HashMap<String,Object> beanList) throws Exception{
    	HashMap hm = apm.getParams();
    	if(hm!=null && hm.get("queryDatacellToExcel")!=null){
    		String templateFilename = JavaCallLogic.createTemplate(hm.get("fields").toString());
    		String downloadFile = ExcelUtil.exportExcel((List)beanList.get("rdata"), null,templateFilename, false, false, false, hm);
    		return downloadFile;
    	}else if(hm!=null && hm.get("queryDatacellToPrint")!=null){
    		String templateFilename = JavaCallLogic.createTemplate(hm.get("fields").toString());
    		String downloadFile = ExcelUtil.exportExcel((List)beanList.get("rdata"), null,templateFilename, false, false, false, hm);
    		return downloadFile;
    	}else{
    		return getXmlListBean(beanList);
    	}
    }
	/**
	 * 返回的对象为List<Bean>方式，仅针对datacell,可直接支持导出
	 * @param pageCond 分面对象
	 * @param beanList 需要返回的数据集，格式为List<Bean>
	 * @return 组织化的xml字符串
	 * @throws Exception 
	 */
    public static String getXmlListBean(AjaxParam apm, List beanList, Object... params) throws Exception{   
    	HashMap hm = apm.getParams();
    	if(hm!=null && hm.get("queryDatacellToExcel")!=null){
    		String templateFilename = JavaCallLogic.createTemplate(hm.get("fields").toString());
    		String downloadFile = ExcelUtil.exportExcel(beanList, null, templateFilename, false, false, false, hm);
    		return downloadFile;
    	}if(hm!=null && hm.get("queryDatacellToPrint")!=null){
    		String templateFilename = JavaCallLogic.createTemplate(hm.get("fields").toString());
    		String downloadFile = ExcelUtil.exportExcel(beanList, null, templateFilename, false, false, false, hm);
    		return downloadFile;
    	}else{
    		return getXmlListBean(apm.getPage(),beanList,params);
    	}
    }
    /**
     * 返回的对象为List<Bean>方式,无分页。有过滤完整路径
     * @param beanList
     * @return
     */
    public static String getXmlListBean(HashMap<String,Object> beanList){
    	return getXmlListBean(beanList,true);
    }
    /**
     * 返回的对象为List<Bean>方式,无分页。有过滤完整路径
     * @param beanList
     * @return
     */
    public static String getXmlListBean(HashMap<String,Object> beanList,boolean isRoot){
    	XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
        sm.registerConverter(new BigDecimalQueryConvertor());
        sm.registerConverter(new BigIntegerQueryConvertor());
        sm.registerConverter(new DoubleQueryConvertor());
        sm.registerConverter(new FloatQueryConvertor());
        sm.registerConverter(new IntQueryConvertor());
        sm.registerConverter(new LongQueryConvertor());
        sm.registerConverter(new ShortQueryConvertor());
        sm.registerConverter(new TimestampsConvertor());
    	sm.autodetectAnnotations(true);
    	StringBuffer smxml = new StringBuffer();
    	Set set = beanList.keySet();
    	for (Iterator iterator = set.iterator(); iterator.hasNext();) {
    		String keyName = (String) iterator.next();
    		Object mapObj = beanList.get(keyName);
    		if(mapObj==null){
    			continue;
    		}
    		if(mapObj instanceof List){
	    		List resultList = (List)mapObj;
	    		for(int j=0;j<resultList.size();j++){   
	    			if(resultList.get(j) instanceof Map) {
	    				Map map = (Map)resultList.get(j);
	    				smxml.append("<"+keyName+">");
	    				for(Object key : map.keySet()){
	    					Object valueObject = map.get(key);
	    					if(valueObject==null){
	    						valueObject="";
	    					}
	    					String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
	    					smxml.append("<"+key+">"+vot+"</"+key+">");
	    				}
	    				smxml.append("</"+keyName+">");
	    			}else{
	    				if(resultList.get(j)!=null){
	    					Class c = resultList.get(j).getClass();   
	    					sm.alias(keyName,c);
	    					smxml.append(sm.toXML(resultList.get(j)));
	    				}
	    			}
	    			
	    		}
    		}else if(mapObj instanceof Map) {
				Map map = (Map)mapObj;
				smxml.append("<"+keyName+">");
				for(Object key : map.keySet()){
					Object valueObject = map.get(key);
					if(valueObject==null){
						valueObject="";
					}
					String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
					smxml.append("<"+key+">"+vot+"</"+key+">");
				}
				smxml.append("</"+keyName+">");
			}else{
				Class c = mapObj.getClass();   
				sm.alias(keyName,c);
				smxml.append(sm.toXML(mapObj));
    		}
    	}
    	if(isRoot){
	        StringBuffer sbf = new StringBuffer();
	        sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
	    	sbf.append(smxml);
	    	sbf.append("</data></root>");
	    	return sbf.toString();
    	}else{
    		return smxml.toString();
    	}
    }    
    /**
     * 返回的对象为List<Bean>方式,无分页。有过滤完整路径
     * @param beanList
     * @return
     */
    private static String getXmlListBeanBody(List... beanList){
    	XStream sm = new XStream(new DomDriver()); //创建Xstream对象   
    	List resultList = new ArrayList();
    	for (int i = 0; i < beanList.length; i++) {
    		resultList.addAll(beanList[i]);
		}
        sm.registerConverter(new BigDecimalQueryConvertor());
        sm.registerConverter(new BigIntegerQueryConvertor());
        sm.registerConverter(new DoubleQueryConvertor());
        sm.registerConverter(new FloatQueryConvertor());
        sm.registerConverter(new IntQueryConvertor());
        sm.registerConverter(new LongQueryConvertor());
        sm.registerConverter(new ShortQueryConvertor());
        sm.registerConverter(new TimestampsConvertor());
    	sm.autodetectAnnotations(true);
    	StringBuffer smxml = new StringBuffer();
        for(int i=0;i<resultList.size();i++){   
            if(resultList.get(i) instanceof Map) {
            	Map map = (Map)resultList.get(i);
            	smxml.append("<map>");
        		for(Object key : map.keySet()){
        			Object valueObject = map.get(key);
        			if(valueObject==null){
        				valueObject="";
        			}
        			String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
        			smxml.append("<"+key+">"+vot+"</"+key+">");
        		}
        		smxml.append("</map>");
			}else{
				Class c = resultList.get(i).getClass();   
				String b = c.getName();   
				String[] temp = b.split("\\.");
				sm.alias(temp[temp.length-1],c);
				smxml.append(sm.toXML(resultList.get(i)));
			}
            
        } 
    	return smxml.toString();
    }
    /**
     * 返回的对象为List<map>方式
     * @param beanList 数组列表，格式为List<Map>
     * @return xml字符串
     */
    private static String getXmlListMap(List<Map> beanList){
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
    	String xpath = "map";
    	for (Iterator iterator = beanList.iterator(); iterator.hasNext();) {
    		Map map = (Map) iterator.next();
    		sbf.append("<"+xpath+">");
    		for(Object key : map.keySet()){
    			Object valueObject = map.get(key);
    			if(valueObject==null){
    				valueObject="";
    			}
    			if(valueObject instanceof List){
    				sbf.append("<"+key+">"+getXmlListBeanBody((List)valueObject)+"</"+key+">");
    			}else{
    				String vot = com.eos.web.taglib.util.StringUtil.htmlFilter(valueObject.toString());
    				sbf.append("<"+key+">"+vot+"</"+key+">");
    			}
    		}
    		sbf.append("</"+xpath+">");
		}
    	sbf.append("</data></root>");
    	return sbf.toString();
    }
    
	/**
	 * 获取EOS ajax提交来的数据
	 * @param request
	 * @return
	 */
	public static Document getAjax(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		Document document=null;
		if(ajax==null){
			return null;
		}
		try {
			document = XmlUtil.parseString(ajax.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
//				document = XmlUtil.parseString("<root></root>");
				return null;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return document;
	}
	/**
	 * 获取EOS ajax提交来的数据
	 * @param request
	 * @return
	 */
	public static String getAjax(String paramName){
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		if(StringUtils.isBlank(ajax)){
			return null;
		}
		Document document=null;
		try {
			document = XmlUtil.parseString(ajax.toString());
			Node node = XmlUtil.findNode(document, "root/data/"+paramName);
			String nv = XmlUtil.getNodeValue(node);
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取EOS ajax提交来的数据
	 * @param request
	 * @param paramName 取得该节点下的孩子节点
	 * @return map方式返回孩子节点名及其值
	 */
	public static List<HashMap> getMapAjax(String paramName){
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		if(StringUtils.isBlank(ajax)){
			return null;
		}
		Document document=null;
		List<HashMap> result = new ArrayList<HashMap>();
		try {
			document = XmlUtil.parseString(ajax.toString());
			NodeList paramNodes = XmlUtil.findNodes(document, "root/data/"+paramName);
			for(int i = 0; i < paramNodes.getLength(); i++){
				HashMap params = new HashMap();
				Node pnode = paramNodes.item(i);
				NodeList nl = pnode.getChildNodes();
				for (int j = 0; j < nl.getLength(); j++) {
					Node node = nl.item(j);
					String nodeValue = XmlUtil.getNodeValue(node);
					params.put(node.getNodeName(), nodeValue);
				}
				paramSubTo(params);
				result.add(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取EOS ajax通过params方式提交来的数据
	 * @param request
	 * @param paramName 取得该节点下的孩子节点
	 * @return map方式返回孩子节点名及其值
	 */
	public static HashMap getParamsAjax(Document document){
		HashMap params = new HashMap();
		if(document==null){
			return null;
		}
		Node paramNode = XmlUtil.findNode(document, "root/params");
		if(paramNode != null){
			NodeList paramNodes = XmlUtil.findNodes(document, "root/params/param");
			for(int i = 0; i < paramNodes.getLength(); i++)
			{
				String name = XmlUtil.getNodeValue(paramNodes.item(i), "key");
				String value = XmlUtil.getNodeValue(paramNodes.item(i), "value");
				if(params.get(name)==null){
					params.put(name, value);
				}else{
					Object values = params.get(name);
					if(values instanceof String){
						List<String> result = new ArrayList<String>();
						result.add((String)values);
						result.add(value);
						params.put(name, result);
					}else if(values instanceof List){
						List<String> result = (List<String>)values;
						result.add(value);
					}
				}
			}
		}
		paramSubTo(params);
		return params;
	}
	/**
	 * 将emp[1]/org转化为emp:HashMap   org:map的key
	 * @param hm
	 */
	private static void paramSubTo(HashMap hm){
    	HashMap hmback = new HashMap();
    	for (Iterator iterator = hm.keySet().iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			List pl = null;
			if(key.indexOf("[")>-1){
				int index = Integer.valueOf(key.substring(key.indexOf("[")+1,key.indexOf("]")));
				String typemap=key.substring(0,key.indexOf("["));
				String keystr = key.substring(key.indexOf("/")+1);
				pl = (List)hmback.get(typemap);
				if(pl == null){
					pl = new ArrayList();
					hmback.put(typemap, pl);
				}
				if(pl.size()<index){
					for(int k =pl.size();k<index;k++){
						pl.add(new HashMap());
					}
				}
				index = index -1;
				HashMap submap = (HashMap)pl.get(index);
				submap.put(keystr, hm.get(key));
			}
		}
    	hm.putAll(hmback);
	}
	/**
	 * 获取EOS 通过ajax提交来的数据
	 * @return map方式返回孩子节点名及其值
	 */
	public static HashMap getParamsAjax(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String submitType = request.getParameter("submitType");
		if(submitType==null){
			return getParameterNames(request);
		}else{
			Document document = getAjax();
			if(document==null){
				return null;
			}
			HashMap params = getParamsAjax(document);
			Node dataNodes = XmlUtil.findNode(document, "root/data");
			findNode(dataNodes.getChildNodes(),params);
			paramSubTo(params);
			params.putAll(getParameterNames(request));
			return params;
		}
	}
	private static HashMap<String,String> getParameterNames(HttpServletRequest request){
		HashMap<String,String> params = new HashMap<String,String>();
		Enumeration<String> enums=request.getParameterNames();   
        while(enums.hasMoreElements()){
	       String paramName=(String)enums.nextElement();                       
	       String[] values=request.getParameterValues(paramName);   
           for(int i=0;i<values.length;i++){
        	   params.put(paramName, values[i]);
           }
        }
        return params;
	}
	/**
	 * 递归找孩子节点
	 * @param nodes
	 * @param params
	 */
	private static void findNode(NodeList nodes,HashMap params){
		for(int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			NodeList cnodes = node.getChildNodes();
			if(cnodes.getLength()>0){
				String nodeValue = XmlUtil.getNodeValue(node);
				if(StringUtils.isBlank(nodeValue)){
					HashMap para = new HashMap();
					params.put(node.getNodeName(), para);
					findNode(node.getChildNodes(),para);
				}else{
					params.put(node.getNodeName(), nodeValue);
				}
			}
        }
	}
	
	/**
	 * 获取EOS datacell提交来的数据,javaBean方式
	 * @param request
	 * @return params: paramFormId= 或者 initParamFunc而来的数据
	 * 		   page: 接收datacell内置分页对象
	 * @throws Exception
	 */
	public static AjaxParam queryDatacell() throws Exception{
		AjaxParam apm = new AjaxParam();
		Document document = getAjax();
		if(document==null){
			return null;
		}
		apm.setDocument(document);
		Page page = apm.getPage();

		HashMap params = getParamsAjax(document);
		params.put("page", page);
        apm.setParams(params);
        
        Node pageNode = XmlUtil.findNode(document, "root/data/page");
        if(pageNode != null){
            String begin = XmlUtil.getNodeValue(pageNode, "begin");
            String length = XmlUtil.getNodeValue(pageNode, "length");
            String count = XmlUtil.getNodeValue(pageNode, "count");
            String totalPage = XmlUtil.getNodeValue(pageNode, "totalPage");
            String currentPage = XmlUtil.getNodeValue(pageNode, "currentPage");
            String isCount = XmlUtil.getNodeValue(pageNode, "isCount");
            String isFirst = XmlUtil.getNodeValue(pageNode, "isFirst");
            String isLast = XmlUtil.getNodeValue(pageNode, "isLast");
            String size = XmlUtil.getNodeValue(pageNode, "size");
            if(StringUtils.isNotBlank(begin) && !"undefined".equals(begin)){
            	page.setBegin(Integer.parseInt(begin));
            }
            if(StringUtils.isNotBlank(length)){
            	page.setLength(Integer.parseInt(length));
            }
            if(StringUtils.isNotBlank(count)){
            	page.setCount(Integer.parseInt(count));
            }
            if(StringUtils.isNotBlank(totalPage)){
            	page.setTotalPage(Integer.parseInt(totalPage));
            }
            if(StringUtils.isNotBlank(currentPage)){
            	page.setCurrentPage(Integer.parseInt(currentPage));
            }
            if(StringUtils.isNotBlank(isCount)){
            	page.setCount(Boolean.valueOf(isCount));
            }
            if(StringUtils.isNotBlank(isFirst)){
            	page.setFirst(Boolean.valueOf(isFirst));
            }
            if(StringUtils.isNotBlank(isLast)){
            	page.setLast(Boolean.valueOf(isLast));
            }
            if(StringUtils.isNotBlank(size)){
            	page.setSize(Integer.parseInt(size));
            }
        }
        Node criteria = XmlUtil.findNode(document, "root/criteria");
        apm.setCriteria(criteria);
/*
    <_criteria xmlns="com.primeton.das.criteria">
    	<_entity>com.eos.User</_entity>
    	<_expr>
    		<gender>male</gender>
    	</_expr>
    	<_expr>
    		<age>20,21,22,23</age>
    		<_op>in</_op>
    	</_expr>
    </_criteria>
*/
		return apm;
	}
	/**
	 * 获取datacell变更项
	 * @return insertEntities新增数组 updateEntities更新数组 deleteEntities删除数组
	 * @throws Exception
	 */
	public static HashMap updateDatacell() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		if(ajax.lastIndexOf("<xpath>map</xpath></data></root>")>0){
			return getRowByHashMap(ajax);
		}else{
			return getRowByBean(ajax);
		}
	}
	/**
	 * datacell更新修改获取,HashMap方式
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 */
	public static HashMap getRowByHashMap() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		return getRowByHashMap(ajax);
	}
	/**
	 * datacell更新修改获取,HashMap方式
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 */
	public static HashMap getRowByHashMap(String ajax) throws Exception{
		Document document = XmlUtil.parseString(ajax.toString());
		NodeList updateEntities = XmlUtil.findNodes(document, "root/data/updateEntities");
		NodeList deleteEntities = XmlUtil.findNodes(document, "root/data/deleteEntities");
		NodeList insertEntities = XmlUtil.findNodes(document, "root/data/insertEntities");
		HashMap hmps = new HashMap();
		if(updateEntities.getLength()>0){
			List updateList = getMapList(updateEntities);
			hmps.put("updateEntities", updateList);
		}
		if(deleteEntities.getLength()>0){
			List updateList = getMapList(deleteEntities);
			hmps.put("deleteEntities", updateList);
		}
		if(insertEntities.getLength()>0){
			List updateList = getMapList(insertEntities);
			hmps.put("insertEntities", updateList);
		}
		return hmps;
	}
	/**
	 * datacell更新修改获取,javaBean方式
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 */
	public static HashMap getRowByBean() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String ajax = request.getParameter("ajax");
		return getRowByBean(ajax);
	}
	/**
	 * datacell更新修改获取,javaBean方式
	 * @param 需要处理的字符串
	 * @return insertEntities updateEntities deleteEntities
	 */
	public static HashMap getRowByBean(String ajax) throws Exception{
		String ajaxstr = ajax.substring(ajax.indexOf("<data>"), ajax.lastIndexOf("</data>")+7);
		XStream xs = new XStream(new DomDriver());
        xs.registerConverter(new BigDecimalConvertor());
        xs.registerConverter(new BigIntegerConvertor());
        xs.registerConverter(new DoubleConvertor());
        xs.registerConverter(new FloatConvertor());
        xs.registerConverter(new IntConvertor());
        xs.registerConverter(new LongConvertor());
        xs.registerConverter(new ShortConvertor());
        xs.registerConverter(new TimestampsConvertor());
		xs.alias("data", HashMap.class);
		HashMap hmp = (HashMap)xs.fromXML(ajaxstr);
		return hmp;
	}
	/**
	 * 将Node转为化为List<Map>方式
	 * @param entities
	 * @return
	 */
	public static List getMapList(NodeList entities){
		List updateList = new ArrayList();
		for(int i=0;i<entities.getLength();i++){
			Node Entities = entities.item(i);
			NodeList nodes = Entities.getChildNodes();
			HashMap hmp = new HashMap();
			for(int j=0;j<nodes.getLength();j++){
				Node node = nodes.item(j);
				String nodeValue = XmlUtil.getNodeValue(node);
				hmp.put(node.getNodeName(), nodeValue);
			}
			updateList.add(hmp);
		}
		return updateList;
	}
	
	/**
	 * 递归找孩子节点
	 * @param nodes
	 * @param params
	 */
	private static void findNodes(NodeList nodes,HashMap params){
		for(int i = 0; i < nodes.getLength(); i++){
			Node node = nodes.item(i);
			NodeList cnodes = node.getChildNodes();
			if(cnodes.getLength()>0){
				String nodeValue = XmlUtil.getNodeValue(node);
				if(StringUtils.isBlank(nodeValue)){
					HashMap para = new HashMap();
					params.put(node.getNodeName(), para);
					findNodes(node.getChildNodes(),para);
				}else{
					params.put(node.getNodeName(), nodeValue);
				}
			}
        }
	}	
	
	public static void main(String[] arg){
		try {
			String ajax = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "<root><params>"
				+ "<param><key>_eosFlowAction</key><value>2222</value></param></params>"
				+ "<data><criteria><_entity></_entity></criteria><page><begin>0</begin><length>10</length><count>0</count><isCount>true</isCount></page></data></root>";
			Document document = XmlUtil.parseString(ajax.toString());
			Node paramNodes = XmlUtil.findNode(document, "root");		
//			NodeList nodelist = paramNodes.getChildNodes();
//			for(int i=0;i<nodelist.getLength();i++){
//				Node node = nodelist.item(i);
//				System.out.println(node.getNodeName());
//			}
			
			HashMap params = new HashMap();
			XmlConvert.findNodes(paramNodes.getChildNodes(), params);
			System.out.println(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}