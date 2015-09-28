// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Multibox.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Multibox extends BaseModifiable
{

    public Multibox()
    {
        setType("multibox");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder();
        String id = getId();
        buf.append("<input type=\"checkbox\"");
        if(id != null)
            prepareId(buf);
        prepareName(buf);
        if(getValue() != null)
            prepareAttribute(buf, "value", (String)getValue());
        if(checked())
            buf.append(" checked");
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }

    private boolean checked()
    {
        boolean checked = false;
        String value = (String)getValue();
        if(value == null || value.equals(""))
            return checked;
        value = (new StringBuilder()).append(value).append(",").toString();
        String muti = getAttribute("mutiValue");
        muti = (new StringBuilder()).append(muti).append(",").toString();
        int index = muti.indexOf(value);
        if(index != -1)
            checked = true;
        return checked;
    }
}
