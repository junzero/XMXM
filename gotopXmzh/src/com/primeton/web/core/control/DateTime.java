// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DateTime.java

package com.primeton.web.core.control;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.primeton.web.core.control.util.FormatException;
import com.primeton.web.core.control.util.FormatUtil;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class DateTime extends BaseModifiable
{

    private static final long serialVersionUID = 0x96304b4aa2a0315eL;
    public static final String SUBMIT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SUBMIT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public DateTime()
    {
        setType("date");
    }

    public String toHtml()
        throws Exception
    {
    	
    	String addMm = getAttribute("addMm");
    	String addDd = getAttribute("addDd");
    	String addSs = getAttribute("addSs");
    	
        String format = getAttribute("format");
        useAttribute("format");
        if(format == null)
            format = getAttribute("tagformat");
        String maxValue = getAttribute("maxValue");
        String minValue = getAttribute("minValue");
        if(maxValue != null && minValue != null)
        {
            SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
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
        String actFormat = "yyyy-MM-dd";
        if(format == null)
            format = "yyyy-MM-dd";
        else
        if(format.indexOf("yyyy") != -1 && format.indexOf("ss") != -1)
            actFormat = "yyyy-MM-dd HH:mm:ss";
        String srcFormat = getAttribute("srcFormat");
        useAttribute("srcFormat");
        Object value = getActualValue();
        String actValue = null;
        if(value != null)
        {
            actValue = FormatUtil.format(getValue(), srcFormat, actFormat);
        } else
        {
            String defaultNull = getAttribute("defaultNull");
            if(defaultNull != null && defaultNull.equals("false"))
            {
                value = new Date();
                actValue = FormatUtil.format(value, actFormat);
            }
        }
        
        if(actValue!=null && actFormat!=null && (
        	StringUtils.isNotBlank(addMm) || StringUtils.isNotBlank(addDd) || StringUtils.isNotBlank(addSs))){
        	if(isNum(addMm)){
        		actValue = addDate(actValue,actFormat,Calendar.MONDAY,addMm);
        	}
        	if(isNum(addDd)){
        		actValue = addDate(actValue,actFormat,Calendar.DATE,addDd);
        	}
        	if(isNum(addSs)){
        		actValue = addDate(actValue,actFormat,Calendar.SECOND,addSs);
        	}
        }
        
        String id = getId();
        if(id == null)
            id = (new StringBuilder()).append("date_").append(hashCode()).toString();
        String hiddenId = (new StringBuilder()).append(id).append("_hidden").toString();
        String inputId = (new StringBuilder()).append(id).append("_input").toString();
        StringBuilder buf = new StringBuilder();
        buf.append("<div id=\"").append(id).append("_container\" class=\"eos-ic-container\">");
        buf.append("<input class=\"eos-calendar-editor-text\" type=\"text\"");
        buf.append(" id=\"").append(inputId).append("\"");
        if(getAttribute("style") != null)
            buf.append(" style=\"").append(getAttribute("style")).append("\"");
        if(getAttribute("styleClass") != null)
            buf.append(" class=\"").append(getAttribute("styleClass")).append("\"");
        if(getAttribute("title") != null)
            buf.append(" title=\"").append(getAttribute("title")).append("\"");
        if(getAttribute("accesskey") != null)
            buf.append(" accesskey=\"").append(getAttribute("accesskey")).append("\"");
        if(getAttribute("size") != null)
            buf.append(" size=\"").append(getAttribute("size")).append("\"");
        if(getAttribute("tabindex") != null)
            buf.append(" tabindex=\"").append(getAttribute("tabindex")).append("\"");
        if(getAttribute("allowNull") != null)
            buf.append(" validateAttr=\"type=calendar;allowNull=").append(getAttribute("allowNull")).append("\"");
        if(getAttribute("extAttr") != null)
            buf.append((new StringBuilder()).append(" ").append(getAttribute("allowNull").replace(';', ' ')).toString());
        if(getAttribute("onclick") != null)
            buf.append(" onclick=\"").append(getAttribute("onclick")).append("\"");
        if(getAttribute("onblur") != null)
            buf.append(" onblur=\"").append(getAttribute("onblur")).append("\"");
        if(getAttribute("onchange") != null)
            buf.append(" onchange=\"").append(getAttribute("onchange")).append("\"");
        if(getAttribute("ondblclick") != null)
            buf.append(" ondblclick=\"").append(getAttribute("ondblclick")).append("\"");
        if(getAttribute("onfocus") != null)
            buf.append(" onfocus=\"").append(getAttribute("onfocus")).append("\"");
        if(getAttribute("onkeydown") != null)
            buf.append(" onkeydown=\"").append(getAttribute("onkeydown")).append("\"");
        if(getAttribute("onkeypress") != null)
            buf.append(" onkeypress=\"").append(getAttribute("onkeypress")).append("\"");
        if(getAttribute("onkeyup") != null)
            buf.append(" onkeyup=\"").append(getAttribute("onkeyup")).append("\"");
        if(getAttribute("onmousedown") != null)
            buf.append(" onmousedown=\"").append(getAttribute("onmousedown")).append("\"");
        if(getAttribute("onmousemove") != null)
            buf.append(" onmousemove=\"").append(getAttribute("onmousemove")).append("\"");
        if(getAttribute("onmouseout") != null)
            buf.append(" onmouseout=\"").append(getAttribute("onmouseout")).append("\"");
        if(getAttribute("onmouseover") != null)
            buf.append(" onmouseover=\"").append(getAttribute("onmouseover")).append("\"");
        if(getAttribute("onmouseup") != null)
            buf.append(" onmouseup=\"").append(getAttribute("onmouseup")).append("\"");
        if(getAttribute("onselect") != null)
            buf.append(" onselect=\"").append(getAttribute("onselect")).append("\"");
        if(getAttribute("alt") != null)
            buf.append(" alt=\"").append(getAttribute("alt")).append("\"");
        if(getAttribute("maxlength") != null)
            buf.append(" maxlength=\"").append(getAttribute("maxlength")).append("\"");
        prepareAttributes(buf);
        buf.append("/><img id='").append(id).append("_button' class=\"eos-ic-button\"/>");
        buf.append("<input type=\"hidden\"");
        if(getName() != null)
            prepareAttribute(buf, "name", getName());
        prepareAttribute(buf, "id", (new StringBuilder()).append("").append(hiddenId).toString());
        buf.append("/></div>");
        buf.append("<script language=\"javascript\">\n");
        buf.append("var _date =new Calendar(\"").append(id).append("\",\"").append(format).append("\");\n");
        if(maxValue != null)
            buf.append("_date.maxValue = \"").append(maxValue).append("\";\n");
        if(actValue != null)
            buf.append("_date.value = \"").append(actValue).append("\";\n");
        if(minValue != null)
            buf.append("_date.minValue = \"").append(minValue).append("\";\n");
        String submitFormat = getAttribute("submitFormat");
        if(submitFormat != null)
            buf.append("_date.submitFormat = \"").append(submitFormat).append("\";\n");
        String width = getAttribute("width");
        if(width != null)
            buf.append("_date.width = \"").append(width).append("\";\n");
        String readOnly = getAttribute("readOnly");
        if(readOnly != null)
            buf.append("_date.readOnly = ").append(readOnly).append(";\n");
        String allowInput = getAttribute("readonly");
        if(allowInput != null)
            buf.append("_date.allowInput = !").append(allowInput).append(";\n");
        String disabled = getAttribute("disabled");
        if(disabled != null)
            buf.append("_date.isDisabled = ").append(disabled).append(";\n");
        String onSelectFunc = getAttribute("onSelectFunc");
        if(onSelectFunc != null)
            buf.append("_date.onSelectFunc = \"").append(onSelectFunc).append("\";\n");
        String inDatacell = getAttribute("inDatacell");
        if(inDatacell != null)
            buf.append("_date.inDatacell = \"").append(inDatacell).append("\";\n");
        buf.append("_date.init();\n</script>");
        buf.append("");
        return buf.toString();
    }
	private static boolean isNum(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	private String addDate(String actValue,String actFormat,int opType,String num){
		try {
			DateFormat targetFormater = new SimpleDateFormat(actFormat);
			Date tfp = targetFormater.parse(actValue);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(tfp);
			rightNow.add(opType, Integer.parseInt(num,10));
			actValue = FormatUtil.format(rightNow.getTime(), actFormat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actValue;
	}
}
