// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Time.java

package com.primeton.web.core.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.primeton.web.core.control.util.FormatUtil;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Time extends BaseModifiable
{

    private static final String TIME_FORMAT = "HH:mm:ss";

    public Time()
    {
        setType("time");
    }

    public String toHtml()
        throws Exception
    {
    	String id = getAttribute("id");
        String srcFormat = getAttribute("srcFormat");
        useAttribute("srcFormat");
        Object value = getActualValue();
        String dispValue = null;
        if(value != null && srcFormat != null){
        	try{
        		dispValue = FormatUtil.format(getValue(), srcFormat, "HH:mm:ss");
        	}catch(Exception e){
        		System.out.println("id:"+id+"。时间控件格式异常，收到的数据为："+getValue()+"\n"+"srcFormat:"+srcFormat+"\n默认格式为HH:mm:ss");
        		throw e;
        	}
        }else
            dispValue = (String)getValue();
        if(id == null)
            id = (new StringBuilder()).append("time_").append(hashCode()).toString();
        StringBuilder buf = new StringBuilder();
        buf.append("<div id=\"").append(id).append("_container\"");
        String title = getAttribute("title");
        if(title != null)
            buf.append(" title=\"").append(title).append("\"");
        buf.append(" style=\"display:inline;float:left;\"></div>\n");
        buf.append("<script>\n");
        buf.append("var _time = new TimeSelect(\"").append(id).append("\");\n");
        if(dispValue != null)
            buf.append("_time.defaultValue = \"").append(dispValue).append("\";\n");
        String maxValue = getAttribute("maxValue");
        if(maxValue != null)
            buf.append("_time.maxValue = \"").append(maxValue).append("\";\n");
        String defaultNull = getAttribute("defaultNull");
        if(defaultNull != null)
            buf.append("_time.defaultNull = ").append(defaultNull).append(";\n");
        String allowNull = getAttribute("allowNull");
        if(allowNull != null)
            buf.append("_time.allowNull = ").append(allowNull).append(";\n");
        String minValue = getAttribute("minValue");
        if(minValue != null)
            buf.append("_time.minValue = \"").append(minValue).append("\";\n");
        if(StringUtils.isNotBlank(srcFormat)){
        	buf.append("_time.srcFormat = \"").append(srcFormat).append("\";\n");
        }
        
        if(maxValue != null && minValue != null)
        {
            SimpleDateFormat simpleFormat = new SimpleDateFormat("HH:mm:ss");
            Date max;
            try
            {
                max = simpleFormat.parse(maxValue);
            }
            catch(ParseException e)
            {
                throw new JspException("error format of maxValue", e);
            }
            Date min;
            try
            {
                min = simpleFormat.parse(minValue);
            }
            catch(ParseException e)
            {
                throw new JspException("error format of minValue", e);
            }
            if(min.after(max))
                throw new JspException((new StringBuilder()).append("maxValue ").append(maxValue).append(" should later than minValue ").append(minValue).toString());
        }
        String name = getName();
        if(name != null)
            buf.append("_time.name = \"").append(name).append("\";\n");
        String readonly = getAttribute("readonly");
        if(readonly != null)
            buf.append("_time.readonly = ").append(readonly).append(";\n");
        String tabIndex = getAttribute("tabindex");
        if(tabIndex != null)
            buf.append("_time.tabIndex = \"").append(tabIndex).append("\";\n");
        String disabled = getAttribute("disabled");
        if(disabled != null && disabled.equals("true"))
            buf.append("_time.disabled = ").append(disabled).append(";\n");
        String accesskey = getAttribute("accesskey");
        if(accesskey != null)
            buf.append("_time.accesskey = \"").append(accesskey).append("\";\n");
        buf.append("_time.init();\n</script>");
        return buf.toString();
    }
}
