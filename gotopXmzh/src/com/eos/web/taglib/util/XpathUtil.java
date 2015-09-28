// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   XpathUtil.java

package com.eos.web.taglib.util;

import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import ognl.Ognl;

import org.apache.struts2.views.jsp.TagUtils;

import com.eos.data.datacontext.DataContextFactory;
import com.eos.data.datacontext.DataContextManager;
import com.eos.data.datacontext.DataContextOperationException;
import com.eos.data.datacontext.IDataContext;
import com.eos.data.xpath.XPathLocator;
import com.eos.system.utility.XmlUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.primeton.engine.core.impl.context.PageflowRuntimeContext;
import com.primeton.ext.data.serialize.ExtendedXMLSerializer;
import com.primeton.ext.data.serialize.SerializeOption;

public class XpathUtil
{

    public XpathUtil()
    {
    }

    public static Object getDataContextRoot(String scope, PageContext pageContext)
    {
        if(Character.toLowerCase(scope.charAt(0)) == 'r')
            return pageContext.getRequest();
        if(Character.toLowerCase(scope.charAt(0)) == 'f')
        {
            IDataContext context = DataContextManager.current().getDefaultContext();
            if(context instanceof PageflowRuntimeContext)
                return ((PageflowRuntimeContext)context).getFlowContext().getRootObject();
            else
                return context.getRootObject();
        }
        if(Character.toLowerCase(scope.charAt(0)) == 'p')
            return pageContext;
        if(Character.toLowerCase(scope.charAt(0)) == 's')
            return pageContext.getSession();
        else
            throw new DataContextOperationException("Can not find Datacontext!");
    }

    public static Object getObjectByXpathSupport(Object rootObj, String attrValue)
    {
        if(attrValue == null)
            return null;
        if(attrValue.length() == 0 || attrValue.charAt(0) != '@')
            return attrValue;
        attrValue = attrValue.substring(1);
        if(attrValue.length() == 0 || attrValue.charAt(0) == '@')
            return attrValue;
        else
            return getObjectByXpath(rootObj, attrValue);
    }

    public static String getStringByXpathSupport(Object rootObj, String attrValue)
        throws JspException
    {
        return getStringByXpathSupport(rootObj, attrValue, null);
    }

    public static String getStringByXpathSupport(Object rootObj, String attrValue, String defaultString)
        throws JspException
    {
        if(attrValue == null)
            return defaultString;
        Object result = getObjectByXpathSupport(rootObj, attrValue);
        if(result != null)
            return String.valueOf(result);
        else
            return null;
    }

    public static int getIntByXpathSupport(Object rootObj, String attrValue, int defaultValue)
        throws JspException
    {
        if(attrValue == null)
            return defaultValue;
        try
        {
            return Integer.parseInt(getStringByXpathSupport(rootObj, attrValue));
        }
        catch(Exception e)
        {
            throw new JspException((new StringBuilder()).append("\"").append(getStringByXpathSupport(rootObj, attrValue)).append("\"不能转换成int.").toString());
        }
    }

    public static boolean getBooleanByXpathSupport(Object rootObj, String attrValue, boolean defaultValue)
        throws JspException
    {
        if(attrValue == null)
            return defaultValue;
        try
        {
            return Boolean.parseBoolean(getStringByXpathSupport(rootObj, attrValue));
        }
        catch(Exception e)
        {
            throw new JspException((new StringBuilder()).append("\"").append(getStringByXpathSupport(rootObj, attrValue)).append("\"不能转换成boolean.").toString());
        }
    }

    public static Object getObjectByXpath(Object rootObj, String xpath)
    {
        try
        {
        	Object result = getObjectByXpath_Struts(xpath);
        	if(result==null){
        		result = XPathLocator.newInstance().getValue(rootObj, xpath);
        	}
        	return result;
        }
        catch(Throwable e)
        {
//        	e.printStackTrace();
            return null;
        }
    }
    /**
     * 通过struts ongl取值
     * @param pageContext
     * @param expr
     * @return
     */
    public static Object getObjectByXpath_Struts(String expr)
    {
    	ValueStack stack = ActionContext.getContext().getValueStack();
        if (expr.startsWith("%{") && expr.endsWith("}")) {
            expr = expr.substring(2, expr.length() - 1);
        }
    	return stack.findValue(expr);
    }
    
    public static String getStringByList(Object rootObj, String xpath)
    {
        Object xpathObj = getObjectByXpath(rootObj, xpath);
        java.util.Map map = new HashMap();
        IDataContext context = DataContextFactory.create(map);
        if(xpathObj != null)
            context.set(xpath, xpathObj);
        StringBuilder buffer = new StringBuilder("<root>");
        SerializeOption option = new SerializeOption();
        ExtendedXMLSerializer xmls = new ExtendedXMLSerializer();
        xmls.setOption(option);
        try
        {
            org.w3c.dom.Element elem = null;
            option.setDepth(3);
            elem = xmls.marshall(map, "data");
            String str = XmlUtil.node2String(elem, false, false);
            buffer.append(str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        buffer.append("</root>");
        return buffer.toString();
    }

    public static void setValue(String scope, PageContext pageContext, String xpath, Object value)
    {
        Object rootObj = getDataContextRoot(scope, pageContext);
        XPathLocator.getInstance().setValue(rootObj, xpath, value);
    }

    public static Object getObjectValue(String scope, PageContext pageContext, String xpath)
    {
        Object rootObj = getDataContextRoot(scope, pageContext);
        return XPathLocator.getInstance().getValue(rootObj, xpath);
    }

    public static boolean getBooleanValue(String scope, PageContext pageContext, String xpath, boolean defaultValue)
        throws JspException
    {
        String value = getStringValue(scope, pageContext, xpath);
        if(value != null)
            return Boolean.parseBoolean(value);
        else
            return defaultValue;
    }

    public static int getIntValue(String scope, PageContext pageContext, String xpath, int defaultValue)
        throws JspException
    {
        String value = getStringValue(scope, pageContext, xpath);
        if(value != null)
            return Integer.parseInt(value);
        else
            return defaultValue;
    }

    public static String getStringValue(String scope, PageContext pageContext, String xpath)
    {
        Object rootObj = getDataContextRoot(scope, pageContext);
        return String.valueOf(XPathLocator.getInstance().getValue(rootObj, xpath));
    }

    public static int getIntByXpathSupport(Object rootObj, String attrValue, int defaultValue, String attrName)
        throws JspException
    {
        if(attrValue == null)
            return defaultValue;
        String value = getStringByXpathSupport(rootObj, attrValue);
        try
        {
            return Integer.parseInt(value);
        }
        catch(Exception e)
        {
            throw new JspException((new StringBuilder()).append("The attribute ").append(attrName).append(":").append(value).append(" is invalid!").toString());
        }
    }

    public static boolean getBooleanByXpathSupport(Object rootObj, String attrValue, boolean defaultValue, String attrName)
        throws JspException
    {
        if(attrValue == null)
            return defaultValue;
        String value = getStringByXpathSupport(rootObj, attrValue);
        if(value.equalsIgnoreCase("true"))
            return true;
        if(value.equalsIgnoreCase("false"))
            return false;
        else
            throw new JspException((new StringBuilder()).append("The attribute ").append(attrName).append(":").append(value).append(" is invalid!").toString());
    }
}
