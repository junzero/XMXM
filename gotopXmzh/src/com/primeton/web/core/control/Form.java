// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Form.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Form extends BaseModifiable
{

    public Form()
    {
        setType("form");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder();
        buf.append("<form ");
        prepareId(buf);
        prepareName(buf);
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
