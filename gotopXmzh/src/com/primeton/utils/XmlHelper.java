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


package com.primeton.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.eos.system.utility.XmlUtil;
import com.eos.system.utility.exception.XmlUtilException;
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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
  
public class XmlHelper {
	private static final Log log = LogFactory.getLog(XmlHelper.class);
	/**
	 * ���صĶ���ΪList<Bean>��ʽ�������datacell
	 * @param pageCond �������
	 * @param beanList ��Ҫ���ص����ݼ�����ʽΪList<Bean>
	 * @return ��֯����xml�ַ���
	 */
    public static String getXmlListBean(Page pageCond,List beanList){   
        List results = new ArrayList(); //������ʱ�� List ���� results   
        results.add(pageCond);
        results.addAll(beanList); // ׷�Ӷ���   
        XStream sm = new XStream(new DomDriver()); //����Xstream����   
        for(int i=0;i<results.size();i++){   
            Class c = results.get(i).getClass();   
            String b = c.getName();   
            String[] temp = b.split("\\.");
            sm.registerConverter(new BigDecimalQueryConvertor());
            sm.registerConverter(new BigIntegerQueryConvertor());
            sm.registerConverter(new DoubleQueryConvertor());
            sm.registerConverter(new FloatQueryConvertor());
            sm.registerConverter(new IntQueryConvertor());
            sm.registerConverter(new LongQueryConvertor());
            sm.registerConverter(new ShortQueryConvertor());
            sm.registerConverter(new TimestampsConvertor());
//            sm.alias(temp[temp.length-1],c);   
	        sm.autodetectAnnotations(true);
        }   
        String smxml = sm.toXML(results);
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
        sbf.append(smxml.substring(6, smxml.length()-7));
        sbf.append("</data></root>");
        return sbf.toString();   
    }   
    /**
     * ���صĶ���ΪList<Bean>��ʽ,�޷�ҳ���й�������·��
     * @param beanList
     * @return
     */
    public static String getXmlListBean(List... beanList){   
    	XStream sm = new XStream(new DomDriver()); //����Xstream����   
    	List resultList = new ArrayList();
    	for (int i = 0; i < beanList.length; i++) {
    		resultList.addAll(beanList[i]);
		}
        for(int i=0;i<resultList.size();i++){   
            Class c = resultList.get(i).getClass();   
            String b = c.getName();   
            String[] temp = b.split("\\.");
            sm.registerConverter(new BigDecimalQueryConvertor());
            sm.registerConverter(new BigIntegerQueryConvertor());
            sm.registerConverter(new DoubleQueryConvertor());
            sm.registerConverter(new FloatQueryConvertor());
            sm.registerConverter(new IntQueryConvertor());
            sm.registerConverter(new LongQueryConvertor());
            sm.registerConverter(new ShortQueryConvertor());
            sm.registerConverter(new TimestampsConvertor());
            sm.autodetectAnnotations(true);
            sm.alias(temp[temp.length-1],c);   
        } 
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
        if(resultList.size()>0){
        	String smxml = sm.toXML(resultList);
        	sbf.append(smxml.substring(6, smxml.length()-7));
        }
    	sbf.append("</data></root>");
    	return sbf.toString();
    }
    /**
     * ���صĶ���ΪList<map>��ʽ
     * @param beanList �����б���ʽΪList<Map>
     * @return xml�ַ���
     */
    public static String getXmlListMap(List<Map> beanList){
    	StringBuffer sbf = new StringBuffer();
    	sbf.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
    	String xpath = "map";
    	for (Iterator iterator = beanList.iterator(); iterator.hasNext();) {
    		Map map = (Map) iterator.next();
    		sbf.append("<"+xpath+">");
    		for(Object key : map.keySet()){
    			sbf.append("<"+key+">"+map.get(key)+"</"+key+">");
    		}
    		sbf.append("</"+xpath+">");
		}
    	sbf.append("</data></root>");
    	return sbf.toString();
    }
    
	/**
	 * ��ȡEOS ajax�ύ��������
	 * @param request
	 * @return
	 */
	public static Document getAjax(HttpServletRequest request){
		String ajax = request.getParameter("ajax");
		Document document=null;
		try {
			document = XmlUtil.parseString(ajax.toString());
		} catch (Exception e) {
			e.printStackTrace();
			try {
				document = XmlUtil.parseString("<root></root>");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return document;
	}
	/**
	 * ��ȡEOS ajax�ύ��������
	 * @param request
	 * @return
	 */
	public static String getAjax(HttpServletRequest request,String paramName){
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
	 * ��ȡEOS ajax�ύ��������
	 * @param request
	 * @param paramName ȡ�øýڵ��µĺ��ӽڵ�
	 * @return map��ʽ���غ��ӽڵ�������ֵ
	 */
	public static HashMap getMapAjax(HttpServletRequest request,String paramName){
		String ajax = request.getParameter("ajax");
		if(StringUtils.isBlank(ajax)){
			return null;
		}
		Document document=null;
		HashMap params = new HashMap();
		try {
			document = XmlUtil.parseString(ajax.toString());
			NodeList paramNodes = XmlUtil.findNodes(document, "root/data/"+paramName);
			for(int i = 0; i < paramNodes.getLength(); i++){
				Node pnode = paramNodes.item(i);
				NodeList nl = pnode.getChildNodes();
				for (int j = 0; j < nl.getLength(); j++) {
					Node node = nl.item(j);
					String nodeValue = XmlUtil.getNodeValue(node);
					log.info(node.getNodeName()+","+ nodeValue);
					params.put(node.getNodeName(), nodeValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return params;
	}
	/**
	 * ��ȡEOS ajaxͨ��params��ʽ�ύ��������
	 * @param request
	 * @param paramName ȡ�øýڵ��µĺ��ӽڵ�
	 * @return map��ʽ���غ��ӽڵ�������ֵ
	 */
	public static HashMap getParamsAjax(Document document){
		HashMap params = new HashMap();
		Node paramNode = XmlUtil.findNode(document, "root/params");
		if(paramNode != null){
			NodeList paramNodes = XmlUtil.findNodes(document, "root/params/param");
			for(int i = 0; i < paramNodes.getLength(); i++)
			{
				String name = XmlUtil.getNodeValue(paramNodes.item(i), "key");
				String value = XmlUtil.getNodeValue(paramNodes.item(i), "value");
				params.put(name, value);
			}
		}
		return params;
	}
	/**
	 * ��ȡEOS ajaxͨ��params��ʽ�ύ��������
	 * @param document ȡ�øýڵ��µĺ��ӽڵ�
	 * @return map��ʽ���غ��ӽڵ�������ֵ
	 */
	public static HashMap getParamsAjax(HttpServletRequest request){
		Document document = getAjax(request);
		HashMap params = getParamsAjax(document);
		Node dataNodes = XmlUtil.findNode(document, "root/data");
		findNode(dataNodes.getChildNodes(),params);
		return params;
	}
	/**
	 * �ݹ��Һ��ӽڵ�
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
	 * ��ȡEOS datacell�ύ��������,javaBean��ʽ
	 * @param request
	 * @return params: paramFormId= ���� initParamFunc����������
	 * 		   page: ����datacell���÷�ҳ����
	 * @throws Exception
	 */
	public static AjaxParam queryDatacell(HttpServletRequest request) throws Exception{
		AjaxParam apm = new AjaxParam();
		Document document = getAjax(request);
		apm.setDocument(document);
		Page page = apm.getPage();

		HashMap params = getParamsAjax(document);
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
            if(StringUtils.isNotBlank(begin)){
            	page.setBegin(Integer.parseInt(begin));
            }
            if(StringUtils.isNotBlank(length)){
            	page.setLength(Integer.parseInt(length));
            }
            if(StringUtils.isNotBlank(begin)){
            	page.setBegin(Integer.parseInt(begin));
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
	 * datacell�����޸Ļ�ȡ,HashMap��ʽ
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws XmlUtilException 
	 */
	public static HashMap getRowByHashMap(HttpServletRequest request) throws Exception{
		String ajax = request.getParameter("ajax");
//		String ajaxstr = ajax.substring(ajax.indexOf("<data>"), ajax.lastIndexOf("</data>")+7);
		System.out.println(ajax);
		Document document = XmlUtil.parseString(ajax.toString());
		NodeList updateEntities = XmlUtil.findNodes(document, "root/data/updateEntities");
		NodeList deleteEntities = XmlUtil.findNodes(document, "root/data/deleteEntities");
		NodeList insertEntities = XmlUtil.findNodes(document, "root/data/insertEntities");
		HashMap hmps = new HashMap();
		System.out.println("=----------"+updateEntities.getLength());
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
	 * datacell�����޸Ļ�ȡ,javaBean��ʽ
	 * @param request
	 * @return insertEntities updateEntities deleteEntities
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws XmlUtilException 
	 */
	public static HashMap getRowByBean(HttpServletRequest request) throws Exception{
		String ajax = request.getParameter("ajax");
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
		System.out.println(ajaxstr);
		HashMap hmp = (HashMap)xs.fromXML(ajaxstr);
		return hmp;
	}
	/**
	 * ��NodeתΪ��ΪList<Map>��ʽ
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
    
    public static void main(String[] args){   
    	
    }
    
}