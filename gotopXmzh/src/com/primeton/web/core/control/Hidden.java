// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Hidden.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Hidden extends BaseModifiable
{

    public Hidden()
    {
        setType("hidden");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<input type=\"hidden\"");
        prepareId(buf);
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
            prepareAttribute(buf, "value", actValue.toString());
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
