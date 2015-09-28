// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TextArea.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class TextArea extends BaseModifiable
{

    public TextArea()
    {
        setType("textarea");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<textarea");
        prepareId(buf);
        prepareName(buf);
        prepareValidations(buf);
        prepareAttributes(buf);
        buf.append(">");
        Object actValue = getActualValue();
        if(actValue != null)
            buf.append(actValue);
        buf.append("</textarea>");
        return buf.toString();
    }
}
