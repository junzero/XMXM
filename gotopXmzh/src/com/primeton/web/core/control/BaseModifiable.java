// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseModifiable.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            WebComponent

public abstract class BaseModifiable extends WebComponent
{

    protected boolean outputJson;
    protected StringBuilder json;

    public BaseModifiable()
    {
        outputJson = false;
        json = null;
    }

    public void prepareValidations(StringBuilder buf)
    {
        String validateAttr = getAttribute("validateAttr");
        useAttribute("validateAttr");
        if(validateAttr != null)
            buf.append(" validateAttr=\"").append(validateAttr).append("\"");
    }
}
