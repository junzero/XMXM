// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictMultiSelect.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            DictSelect

public class DictMultiSelect extends DictSelect
{

    public DictMultiSelect()
    {
        setType("dictmultiselect");
    }

    public String toHtml()
        throws Exception
    {
        if(getAttribute("datacellEditor") != null)
        {
            outputJson = true;
            json = new StringBuilder("{");
            useAttribute("datacellEditor");
        }
        StringBuilder buf = new StringBuilder("<span");
        String id = getId();
        if(id == null)
            id = (new StringBuilder()).append("select").append(hashCode()).toString();
        String containerId = (new StringBuilder()).append(id).append("_container").toString();
        String jsObj = (new StringBuilder()).append("eos_").append(id).toString();
        prepareAttribute(buf, "id", containerId);
        String classStyle = getAttribute("class");
        if(classStyle != null)
        {
            useAttribute("class");
            prepareAttribute(buf, "class", classStyle);
        }
        String style = getAttribute("style");
        if(style != null)
        {
            useAttribute("style");
            prepareAttribute(buf, "style", style);
        }
        buf.append("><input type=\"hidden\"");
        prepareAttribute(buf, "id", (new StringBuilder()).append(id).append("_hidden").toString());
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
            prepareAttribute(buf, "value", actValue.toString());
        buf.append(">");
        String seperator = getAttribute("seperator");
        if(seperator == null)
            seperator = ",";
        else
            useAttribute("seperator");
        buf.append("<select id=\"");
        buf.append(id);
        buf.append("_select\" multiple");
        prepareValidations(buf);
        boolean filter = false;
        if(getAttribute("filter") != null)
        {
            filter = Boolean.parseBoolean(getAttribute("filter"));
            useAttribute("filter");
        }
        useAttribute("nullOption");
        useAttribute("nullLabel");
        useAttribute("filterOp");
        useAttribute("filterStr");
        prepareAttributes(buf);
        buf.append(">");
        prepareOptions(buf, actValue != null ? actValue.toString() : null, filter, true, seperator);
        buf.append("</select></span><script language=\"Javascript\"> var ");
        buf.append(jsObj);
        buf.append("=new MultiSelect(\"");
        buf.append(id);
        buf.append("\");");
        if(!seperator.equals(","))
        {
            buf.append(jsObj);
            buf.append(".splitChar=\"");
            buf.append(seperator);
            buf.append("\";");
        }
        if(outputJson)
        {
            buf.append(jsObj);
            buf.append(".jsonObj=");
            buf.append(json.toString());
        }
        buf.append(jsObj);
        buf.append(".init();</script>");
        return buf.toString();
    }
}
