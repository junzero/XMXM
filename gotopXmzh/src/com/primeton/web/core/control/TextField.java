// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TextField.java

package com.primeton.web.core.control;

import com.primeton.web.core.control.util.FormatException;
import com.primeton.web.core.control.util.FormatUtil;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class TextField extends BaseModifiable
{

    public TextField()
    {
        setType("text");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<input type=\"text\"");
        prepareId(buf);
        prepareName(buf);
        Object actValue = getActualValue();
        if(actValue != null)
        {
            String inputFormat = getAttribute("format");
            if(inputFormat != null)
                try
                {
                    prepareAttribute(buf, "value", FormatUtil.format(actValue, inputFormat));
                }
                catch(FormatException e)
                {
                    e.printStackTrace();
                    throw e;
                }
            else
            if(getActualValue() != null)
                prepareAttribute(buf, "value", actValue.toString());
        }
        if("char".equals(getExtAttribute("lengthUnit")))
        {
            String maxlen = getExtAttribute("maxlen");
            if(maxlen != null && maxlen.length() > 0 && !isAvailable("maxlength"))
                setAttribute("maxlength", maxlen);
        }
        prepareValidations(buf);
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
