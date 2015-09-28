// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Option.java

package com.primeton.web.core.control;

import com.eos.web.taglib.util.StringUtil;
import java.util.List;

public class Option
{

    private String value;
    private String label;
    private boolean disabled;

    public Option(String value, String label)
    {
        this.value = null;
        this.label = null;
        disabled = false;
        this.value = value;
        this.label = label;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public void prepareOption(StringBuilder buf, boolean filter, String actValue, boolean multiple, String seperator)
    {
        buf.append("<option value=\"");
        buf.append(value);
        buf.append("\"");
        if(multiple)
        {
            List sValues = StringUtil.splitString(actValue, seperator);
            boolean found = false;
            int i = 0;
            do
            {
                if(i >= sValues.size())
                    break;
                if(value.equals(sValues.get(i)))
                {
                    found = true;
                    break;
                }
                i++;
            } while(true);
            if(found)
                buf.append(" selected");
        } else
        if(value.equals(actValue))
            buf.append(" selected");
        if(disabled)
            buf.append(" selected");
        buf.append(">");
        String labelStr = null;
        if(filter)
            labelStr = StringUtil.htmlFilter(label);
        else
            labelStr = label;
        buf.append(labelStr);
        buf.append("</option>");
    }
}
