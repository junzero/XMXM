// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Label.java

package com.primeton.web.core.control;

import com.eos.web.taglib.util.StringUtil;
import com.primeton.web.core.control.util.FormatException;
import com.primeton.web.core.control.util.FormatUtil;

// Referenced classes of package com.primeton.web.core.control:
//            WebComponent

public class Label extends WebComponent
{

    private static final long serialVersionUID = 0x7ca772194543a32eL;

    public Label()
    {
        setType("label");
    }

    public String toHtml()
        throws Exception
    {
        Object vo = getValue();
        if(vo == null && getDefaultValue() == null)
            return "&nbsp;";
        if(vo == null && getDefaultValue() != null)
            return getDefaultValue();
        String retstr = null;
        String format = getAttribute("format");
        String srcFormat = getAttribute("srcFormat");
        if(format != null && format.length() > 0)
            try
            {
                if(srcFormat == null)
                    retstr = FormatUtil.format(vo, format);
                else
                    retstr = FormatUtil.format(vo, srcFormat, format);
            }
            catch(FormatException e)
            {
                e.printStackTrace();
                throw e;
            }
        if(retstr == null)
            retstr = vo.toString();
        String s_filter = getAttribute("filter");
        if(s_filter != null && Boolean.parseBoolean(s_filter))
            retstr = StringUtil.htmlFilter(retstr);
        String s_maxOutLen = getAttribute("maxOutLen");
        int maxlen = -1;
        if(s_maxOutLen != null)
            maxlen = Integer.parseInt(s_maxOutLen);
        if(retstr == null || retstr.length() == 0)
            return "";
        if(maxlen != -1 && retstr.length() > maxlen)
            retstr = (new StringBuilder()).append(retstr.substring(0, maxlen)).append("...").toString();
        return retstr;
    }
}
