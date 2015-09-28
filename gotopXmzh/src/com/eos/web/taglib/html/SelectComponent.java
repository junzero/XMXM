// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SelectComponent.java

package com.eos.web.taglib.html;

import com.primeton.web.core.control.BaseModifiable;

public class SelectComponent extends BaseModifiable
{

    public SelectComponent()
    {
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder();
        buf.append("<select");
        prepareId(buf);
        prepareName(buf);
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
