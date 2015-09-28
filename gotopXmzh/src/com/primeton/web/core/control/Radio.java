// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Radio.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Radio extends BaseModifiable
{

    public Radio()
    {
        setType("radio");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder();
        prepareRadio(buf, (String)getValue(), (String)getActualValue());
        buf.append("> ");
        return buf.toString();
    }

    protected void prepareRadio(StringBuilder buf, String value, String propertyValue)
    {
        buf.append("<input type=\"radio\"");
        prepareId(buf);
        prepareName(buf);
        if(value != null)
            prepareAttribute(buf, "value", value);
        prepareAttributes(buf);
    }
}
