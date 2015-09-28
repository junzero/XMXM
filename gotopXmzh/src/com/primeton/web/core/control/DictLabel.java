// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictLabel.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictManager;
import com.eos.web.taglib.util.StringUtil;

// Referenced classes of package com.primeton.web.core.control:
//            Label

public class DictLabel extends Label
{

    public DictLabel()
    {
        setType("dictlabel");
    }

    public String toHtml()
        throws Exception
    {
        String roles = getAttribute("roles");
        String locale = getAttribute("locale");
        useAttribute("locale");
        String retstr = null;
        if(getValue() != null)
            retstr = getValue().toString();
        if(retstr == null && getDefaultValue() == null)
            return "";
        if(retstr == null && getDefaultValue() != null)
            retstr = getDefaultValue();
        String seperator = getAttribute("seperator");
        if(seperator == null)
            seperator = ",";
        if(retstr!=null && retstr.indexOf(seperator) == -1)
        {
            String levelStr = getAttribute("level");
            if(levelStr == null)
            {
                retstr = DictManager.getDictNameI18n(getDictTypeId(), retstr, locale, roles);
            } else
            {
                int level = Integer.parseInt(levelStr);
                retstr = DictManager.getDictNameI18n(getDictTypeId(), retstr, level, locale, roles);
                if(retstr == null)
                    throw new Exception("Level exceeds limit!");
            }
        } else
        {
        	retstr = DictManager.getDictNames(getDictTypeId(), retstr, seperator, roles);
        }
        String s_filter = getAttribute("filter");
        if(s_filter != null && Boolean.parseBoolean(s_filter))
            retstr = StringUtil.htmlFilter(retstr);
        return retstr;
    }
}
