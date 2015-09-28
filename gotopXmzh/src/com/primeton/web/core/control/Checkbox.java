// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Checkbox.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Checkbox extends BaseModifiable
{

    public Checkbox()
    {
        setType("checkbox");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder();
        prepareCheckbox(buf, getValue() == null ? "" : getValue().toString(), getActualValue() == null ? "" : getActualValue().toString());
        buf.append("> ");
        return buf.toString();
    }

    protected void prepareCheckbox(StringBuilder buf, String value, String propertyValue)
    {
        buf.append("<input type=\"checkbox\"");
        prepareId(buf);
        prepareName(buf);
        if(value != null)
            prepareAttribute(buf, "value", value);
        prepareAttributes(buf);
    }
}
