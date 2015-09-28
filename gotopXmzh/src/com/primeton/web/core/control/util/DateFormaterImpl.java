// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DateFormaterImpl.java

package com.primeton.web.core.control.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

// Referenced classes of package com.primeton.web.core.control.util:
//            DateFormatException, Formater

public class DateFormaterImpl
    implements Formater
{

    private static final String SHORT_TIMESTAMP = "yyyyMMddHHmmss";
    private static final String SHORT_TIME = "HHmmss";
    private static final String SHORT_DATE = "yyyyMMdd";
    private static final String LONG_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    private static final String LONG_TIME = "HH:mm:ss";
    private static final String LONG_DATE = "yyyy-MM-dd";

    public DateFormaterImpl()
    {
    }

    public String format(Object value, String pattern)
        throws DateFormatException
    {
        return format(value, null, pattern);
    }

    public String format(Object value, String srcPattern, String pattern)
        throws DateFormatException
    {
        if(value==null || StringUtils.isBlank(value.toString()))
            return "";
        if((pattern == null) & pattern.trim().equals(""))
            return value.toString();
        DateFormat targetFormater;
        try
        {
            targetFormater = new SimpleDateFormat(pattern);
        }
        catch(Exception e)
        {
            throw new DateFormatException((new StringBuilder()).append("错误的日期时间格式：").append(pattern).append("\n").append(e.getMessage()).toString());
        }
        if(value instanceof Date)
            return targetFormater.format(value);
        if(value instanceof String)
        {
            if(srcPattern == null)
            {
                if(isDate((String)value, "yyyyMMdd"))
                    srcPattern = "yyyyMMdd";
                else
                if(isDate((String)value, "yyyyMMddHHmmss"))
                    srcPattern = "yyyyMMddHHmmss";
                else
                if(isDate((String)value, "HHmmss"))
                    srcPattern = "HHmmss";
                else
                if(isDate((String)value, "yyyy-MM-dd"))
                    srcPattern = "yyyy-MM-dd";
                else
                if(isDate((String)value, "HH:mm:ss"))
                    srcPattern = "HH:mm:ss";
                else
                if(isDate((String)value, "yyyy-MM-dd HH:mm:ss"))
                    srcPattern = "yyyy-MM-dd HH:mm:ss";
                else
                    throw new DateFormatException("请输入日期时间的原始格式!");
            } else
            {
                value = formatSupport((String)value, srcPattern);
                if(!isDate((String)value, srcPattern))
                    throw new DateFormatException((new StringBuilder()).append("输入了错误的原日期时间格式：").append(srcPattern).append(" for ").append(value).toString());
            }
            Date tmpValue;
            try
            {
                SimpleDateFormat srcFormater = new SimpleDateFormat(srcPattern);
                tmpValue = srcFormater.parse(value.toString());
            }
            catch(Exception e)
            {
                throw new DateFormatException((new StringBuilder()).append("输入了错误的原日期时间格式：").append(srcPattern).append(" for ").append(value).append("\n").append(e.getMessage()).toString());
            }
            try
            {
                return targetFormater.format(tmpValue);
            }
            catch(Exception e)
            {
                throw new DateFormatException((new StringBuilder()).append("日期时间格式化错误：").append(tmpValue).append("(").append(pattern).append(")\n").append(e.getMessage()).toString());
            }
        } else
        {
            return value.toString();
        }
    }

    private boolean isDate(String dateString, String format)
    {
        if(dateString.length() == format.length())
        {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            try
            {
                if(formater.format(formater.parse(dateString)).equals(dateString))
                    return true;
            }
            catch(ParseException e)
            {
                return false;
            }
        } else
        {
            return false;
        }
        return false;
    }

    private String formatSupport(String dateString, String format)
    {
        if("yyyyMMddHHmmss".equals(format))
        {
            if(dateString.length() == 8)
                dateString = (new StringBuilder()).append(dateString).append("000000").toString();
        } else
        if("yyyyMMdd".equals(format))
        {
            if(dateString.length() == 14)
                dateString = dateString.substring(0, 8);
        } else
        if("yyyy-MM-dd HH:mm:ss".equals(format))
        {
            if(dateString.length() == 10)
                dateString = (new StringBuilder()).append(dateString).append(" 00:00:00").toString();
        } else
        if("yyyy-MM-dd".equals(format) && dateString.length() == 20)
            dateString = dateString.substring(0, 10);
        return dateString;
    }
}
