// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MatchTag.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.util.XpathUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTagBase

public class MatchTag extends EqualTagBase
{

    private String matchType;

    public MatchTag()
    {
        matchType = "anywhere";
    }

    public void init()
        throws JspException
    {
        super.init();
        matchType = XpathUtil.getStringByXpathSupport(getRootObj(), matchType);
    }

    public boolean condition()
        throws JspException
    {
        int result = compare();
        return result >= 0;
    }

    public int compare()
        throws JspException
    {
        Object value = prepareValue(getValueObject(), getPropertyValue());
        Object targetValue = prepareValue(getTargetValueObject(), getTargetPropertyValue());
        if(value == null || targetValue == null)
            return -1;
        String sValue = value.toString();
        if(matchType.equals("anywhere"))
            return sValue.indexOf(targetValue.toString());
        if(matchType.equals("start"))
            return !sValue.startsWith(targetValue.toString()) ? -1 : 1;
        if(matchType.equals("end"))
            return !sValue.endsWith(targetValue.toString()) ? -1 : 1;
        if(matchType.equals("regexp"))
        {
            if(targetValue instanceof Pattern)
                return !((Pattern)targetValue).matcher(sValue).find() ? -1 : 1;
            return !Pattern.matches(targetValue.toString(), sValue) ? -1 : 1;
        } else
        {
            throw new JspException((new StringBuilder()).append("The matchType:").append(matchType).append(" is invalid").toString());
        }
    }

    public String getMatchType()
    {
        return matchType;
    }

    public void setMatchType(String matchType)
    {
        this.matchType = matchType;
    }

    public void release()
    {
        super.release();
        matchType = "anywhere";
    }
}
