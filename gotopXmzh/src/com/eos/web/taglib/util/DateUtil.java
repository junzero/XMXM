// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DateUtil.java

package com.eos.web.taglib.util;

import com.primeton.ext.common.config.EnvironmentConfigHelper;
import javax.servlet.jsp.PageContext;

public class DateUtil
{

    public DateUtil()
    {
    }

    public static String getDefaultFormatPattern()
    {
        return "yyyy-MM-dd HH:mm:ss";
    }

    public static String getDefaultDisplayDateTimeFormatPattern(PageContext pageContext)
    {
        return EnvironmentConfigHelper.getDateTimeFormat();
    }

    public static String getDefaultDisplayDateFormatPattern(PageContext pageContext)
    {
        return EnvironmentConfigHelper.getDateFormat();
    }
}
