// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SwitchCheckBox.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictManager;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class SwitchCheckBox extends BaseModifiable
{

    String trueValue;
    String falseValue;

    SwitchCheckBox()
    {
        trueValue = null;
        falseValue = null;
        setType("switchcheckbox");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<span");
        boolean showLabel = Boolean.parseBoolean(getAttribute("showLabel"));
        useAttribute("showLabel");
        trueValue = getAttribute("checkedValue");
        falseValue = getAttribute("uncheckedValue");
        useAttribute("checkedValue");
        useAttribute("uncheckedValue");
        String id = getId();
        if(id == null)
            id = (new StringBuilder()).append("ckb").append(hashCode()).toString();
        String containerId = (new StringBuilder()).append(id).append("_container").toString();
        String jsObj = (new StringBuilder()).append("eos_").append(id).toString();
        prepareAttribute(buf, "id", containerId);
        buf.append("><input type=\"hidden\"");
        prepareAttribute(buf, "id", id);
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
            prepareAttribute(buf, "value", actValue.toString());
        buf.append("><input type=\"checkbox\" id=\"");
        buf.append(id);
        buf.append("_checkbox\"");
        if(actValue != null && trueValue.equals(actValue.toString()))
            buf.append(" checked");
        prepareValidations(buf);
        prepareAttributes(buf);
        buf.append("> ");
        if(showLabel)
        {
            String label = DictManager.getDictTypeName(getDictTypeId());
            buf.append(label);
        }
        buf.append("</span><script language=\"Javascript\"> var ");
        buf.append(jsObj);
        buf.append("=new SwitchCheckbox(\"");
        buf.append(id);
        buf.append("\");");
        buf.append(jsObj);
        buf.append(".trueValue=\"");
        buf.append(trueValue);
        buf.append("\";");
        buf.append(jsObj);
        buf.append(".falseValue=\"");
        buf.append(falseValue);
        buf.append("\";");
        buf.append(jsObj);
        buf.append(".init();</script>");
        return buf.toString();
    }
}
