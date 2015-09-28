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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eos.system.utility.XmlUtil;
import com.primeton.ext.engine.core.IVariable;
import com.primeton.ext.engine.core.VariableFactory;

public class AjaxSet {
	
    public static final String customType = "__type";
    protected Map values;
    protected HttpServletRequest request;
    private List customVars;
    private Document xmlDom;

    public AjaxSet(HttpServletRequest request){
        values = new HashMap();
        customVars = new ArrayList();
    	this.request = request;
    }
    
    public void setValue(String key, Object value)
    {
        if(key.endsWith("__type"))
        {
            IVariable var = createIVariable(key, String.valueOf(value));
            customVars.add(var);
        } else
        {
            values.put(key, value);
        }
    }

    private static IVariable createIVariable(String key, String varType)
    {
        IVariable var = VariableFactory.createVariable();
        String name = key.replace("/__type", "");
        var.setName(name);
        String varName = varType;
        if(varName.endsWith("[]"))
        {
            var.setArray(true);
            varName = varName.replace("[]", "");
        }
        if(varName.startsWith("sdo:"))
        {
            String sdoName = varName.replace("sdo:", "");
            var.setDataObjectType(sdoName);
            var.setCategory(3);
        } else
        {
            String javaName = varName.replace("java:", "");
            var.setCategory(2);
            var.setTypeName(varName);
            Class clz = forName(javaName);
            var.setTypeClass(clz);
        }
        return var;
    }
    
    private static Class forName(String className)
    {
        try
        {
            return Class.forName(className);
        }
        catch(Exception e) { }
        try
        {
            return Thread.currentThread().getContextClassLoader().loadClass(className);
        }
        catch(Exception e1)
        {
            return null;
        }
    }
    
    public void init()
    {
        try
        {
            request.setCharacterEncoding("UTF-8");
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        try
        {
            BufferedReader in = request.getReader();
            String line;
            while((line = in.readLine()) != null) 
                buffer.append(line);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if(buffer.toString().trim().equals(""))
                buffer.append("<root><params></params><data></data></root>");
            
            System.out.println("******************************************************"+buffer.toString());
            
            Document document = XmlUtil.parseString(buffer.toString());
            Node paramNode = XmlUtil.findNode(document, "root/params");
            if(paramNode != null)
            {
                NodeList params = XmlUtil.findNodes(document, "root/params/param");
                for(int i = 0; i < params.getLength(); i++)
                {
                    String name = XmlUtil.getNodeValue(params.item(i), "key");
                    String value = XmlUtil.getNodeValue(params.item(i), "value");
                    setValue(name, value);
                }

            }
            Node begin = XmlUtil.findNode(document, "root/data/page");
            String value = XmlUtil.getNodeValue(begin, "begin");
            setValue("page/begin", value);
            setXmlDom(document);
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setXmlDom(Document xmlDom)
    {
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
    
}
