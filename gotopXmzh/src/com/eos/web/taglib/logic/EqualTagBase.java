// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EqualTagBase.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.util.DateUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.ext.engine.core.SimpleConditionUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            CompareTagBase

public abstract class EqualTagBase extends CompareTagBase
{

    private String compareType;
    private String pattern;
    private String ignoreCase;
    private boolean bIgnoreCase;

    public EqualTagBase()
    {
        compareType = "string";
        pattern = null;
        ignoreCase = "false";
        bIgnoreCase = false;
    }

    public int doStartTag()
        throws JspException
    {
        init();
        return !condition() ? 0 : 1;
    }

    public void init()
        throws JspException
    {
        super.init();
        compareType = XpathUtil.getStringByXpathSupport(getRootObj(), compareType);
        bIgnoreCase = XpathUtil.getBooleanByXpathSupport(getRootObj(), ignoreCase, false, "ignoreCase");
        pattern = XpathUtil.getStringByXpathSupport(getRootObj(), pattern);
    }

    public abstract boolean condition()
        throws JspException;

    public int compare()
        throws JspException
    {
        Object value = prepareValue(getValueObject(), getPropertyValue());
        Object targetValue = prepareValue(getTargetValueObject(), getTargetPropertyValue());
        if(value == null || targetValue == null)
            return -999;
        if(compareType.equals("string"))
            return compareByString(value, targetValue);
        if(compareType.equals("number"))
            return compareByNumber(value, targetValue);
        if(compareType.equals("date"))
            return compareByDate(value, targetValue);
        if(compareType.equals("unknown"))
            return compareByUnknown(value, targetValue);
        else
            throw new JspException((new StringBuilder()).append("The compareType:").append(compareType).append(" is invaild!").toString());
    }

    private int compareByUnknown(Object value, Object targetValue)
        throws JspException
    {
        int result;
        try
        {
            result = SimpleConditionUtil.compare(value, targetValue);
        }
        catch(ClassCastException e)
        {
            throw new JspException("Incomparable,the type disaccord. ");
        }
        return result;
    }

    public int compareByString(Object value, Object targetValue)
        throws JspException
    {
        String sValue = value.toString();
        String sTagetValue = targetValue.toString();
        if(bIgnoreCase)
            return sValue.compareToIgnoreCase(sTagetValue);
        else
            return sValue.compareTo(sTagetValue);
    }

    public Object prepareValue(Object value, Object targetValue)
    {
        if(targetValue != null)
            return targetValue;
        else
            return value;
    }

    public int compareByNumber(Object value, Object targetValue)
        throws JspException
    {
        Double dValue = null;
        Double dTargetvalue = null;
        if(value instanceof Number)
            dValue = Double.valueOf(((Number)value).doubleValue());
        else
            dValue = new Double(value.toString());
        if(targetValue instanceof Number)
            dTargetvalue = Double.valueOf(((Number)targetValue).doubleValue());
        else
            dTargetvalue = new Double(targetValue.toString());
        return dValue.compareTo(dTargetvalue);
    }

    public int compareByDate(Object value, Object targetValue)
        throws JspException
    {
        Date dValue = null;
        Date dTargetvalue = null;
        if(pattern == null)
            pattern = DateUtil.getDefaultFormatPattern();
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        if(value instanceof Date)
            dValue = (Date)value;
        else
            try
            {
                dValue = df.parse(value.toString());
            }
            catch(ParseException e)
            {
                throw new JspException("被比较值为非法的日期或时间类型");
            }
        if(!(targetValue instanceof Date))
            try
            {
                dTargetvalue = df.parse(targetValue.toString());
            }
            catch(ParseException e)
            {
                throw new JspException("比较值为非法的日期或时间类型");
            }
        else
            dTargetvalue = (Date)targetValue;
        return dValue.compareTo(dTargetvalue);
    }

    public String getCompareType()
    {
        return compareType;
    }

    public void setCompareType(String compareType)
    {
        this.compareType = compareType;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public void release()
    {
        super.release();
        compareType = "string";
        pattern = null;
        ignoreCase = "false";
    }

    public boolean isBIgnoreCase()
    {
        return bIgnoreCase;
    }

    public String getIgnoreCase()
    {
        return ignoreCase;
    }

    public void setIgnoreCase(String ignoreCase)
    {
        this.ignoreCase = ignoreCase;
    }
}
