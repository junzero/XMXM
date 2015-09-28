// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Image.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            WebComponent

public class Image extends WebComponent
{

    public Image()
    {
        setType("image");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<img");
        prepareId(buf);
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
        {
            String src = actValue.toString();
            prepareAttribute(buf, "src", src);
        }
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
