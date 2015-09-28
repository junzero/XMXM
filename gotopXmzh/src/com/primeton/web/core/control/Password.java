// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Password.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Password extends BaseModifiable
{

    public Password()
    {
        setType("password");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<input type=\"password\"");
        prepareId(buf);
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
            prepareAttribute(buf, "value", actValue.toString());
        if("char".equals(getAttribute("lengthUnit")))
        {
            String maxlen = getAttribute("maxlen");
            if(maxlen != null && maxlen.length() > 0)
                prepareAttribute(buf, "maxlength", maxlen);
        }
        prepareValidations(buf);
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
