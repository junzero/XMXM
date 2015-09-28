// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BetweenTag.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.util.DateUtil;
import com.eos.web.taglib.util.XpathUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTagBase

public class BetweenTag extends EqualTagBase
{

    private String maxValue;
    private String minValue;
    private Object oMaxValue;
    private Object oMinValue;

    public BetweenTag()
    {
        maxValue = null;
        minValue = null;
        oMaxValue = null;
        oMinValue = null;
    }

    public boolean condition()
        throws JspException
    {
        int result = compare();
        return result == 1;
    }

    public void init()
        throws JspException
    {
        super.init();
        oMaxValue = XpathUtil.getObjectByXpathSupport(getRootObj(), maxValue);
        oMinValue = XpathUtil.getObjectByXpathSupport(getRootObj(), minValue);
    }

    public int compare()
        throws JspException
    {
        if(getCompareType().equals("string"))
            return compareByString();
        if(getCompareType().equals("number"))
            return compareByNumber();
        if(getCompareType().equals("date"))
            return compareByDate();
        else
            throw new JspException((new StringBuilder()).append("The compareType:").append(getCompareType()).append(" is invaild!").toString());
    }

    public int compareByString()
        throws JspException
    {
        Object value = prepareValue(getValueObject(), getPropertyValue());
        if(value == null)
            return 0;
        if(oMaxValue == null)
            throw new JspException("Can not found MaxValue!");
        if(oMinValue == null)
            throw new JspException("Can not found MinValue!");
        String sValue = value.toString();
        String sMax = oMaxValue.toString();
        String sMin = oMinValue.toString();
        if(isBIgnoreCase())
        {
            if(sMax.compareToIgnoreCase(sMin) < 0)
                throw new JspException("The minValue must less then maxValue!");
            return sValue.compareToIgnoreCase(sMax) > 0 || sValue.compareToIgnoreCase(sMin) < 0 ? 0 : 1;
        }
        if(sMax.compareTo(sMin) < 0)
            throw new JspException("The minValue must less then maxValue!");
        return sValue.compareTo(sMax) > 0 || sValue.compareTo(sMin) < 0 ? 0 : 1;
    }

    public int compareByNumber()
        throws JspException
    {
        Object value = prepareValue(getValueObject(), getPropertyValue());
        if(value == null)
            return 0;
        if(oMaxValue == null)
            throw new JspException("Can not found MaxValue!");
        if(oMinValue == null)
            throw new JspException("Can not found MinValue!");
        Double dValue = null;
        Double dMax = null;
        Double dMin = null;
        if(value instanceof Number)
            dValue = Double.valueOf(((Number)value).doubleValue());
        else
            dValue = new Double((String)value);
        if(oMaxValue instanceof Number)
            dMax = Double.valueOf(((Number)oMaxValue).doubleValue());
        else
            dMax = new Double(oMaxValue.toString());
        if(oMinValue instanceof Number)
            dMin = Double.valueOf(((Number)oMinValue).doubleValue());
        else
            dMin = new Double(oMinValue.toString());
        if(dMax.compareTo(dMin) < 0)
            throw new JspException("The minValue must less then maxValue!");
        return dValue.compareTo(dMax) > 0 || dValue.compareTo(dMin) < 0 ? 0 : 1;
    }

    public int compareByDate()
        throws JspException
    {
        String pattern = getPattern();
        Object value = prepareValue(getValueObject(), getPropertyValue());
        if(value == null)
            return 0;
        if(oMaxValue == null)
            throw new JspException("Can not found MaxValue!");
        if(oMinValue == null)
            throw new JspException("Can not found MinValue!");
        Date dValue = null;
        Date dMax = null;
        Date dMin = null;
        if(pattern == null)
            pattern = DateUtil.getDefaultFormatPattern();
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        if(value instanceof Date)
            dValue = (Date)value;
        else
            try
            {
                dValue = df.parse((String)value);
            }
            catch(ParseException e)
            {
                throw new JspException((new StringBuilder()).append("Date format exception.For Input string:").append(value).toString());
            }
        if(oMaxValue instanceof Date)
            dMax = (Date)oMaxValue;
        else
            try
            {
                dMax = df.parse(oMaxValue.toString());
            }
            catch(ParseException e)
            {
                throw new JspException((new StringBuilder()).append("Date format exception.For Input string:").append(oMaxValue).toString());
            }
        if(oMinValue instanceof Date)
            dMin = (Date)oMinValue;
        else
            try
            {
                dMin = df.parse(oMinValue.toString());
            }
            catch(ParseException e)
            {
                throw new JspException((new StringBuilder()).append("Date format exception.For Input string:").append(oMinValue).toString());
            }
        if(dMax.compareTo(dMin) < 0)
            throw new JspException("The minValue must less then maxValue!");
        return dValue.compareTo(dMax) > 0 || dValue.compareTo(dMin) < 0 ? 0 : 1;
    }

    public String getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(String maxValue)
    {
        this.maxValue = maxValue;
    }

    public String getMinValue()
    {
        return minValue;
    }

    public void setMinValue(String minValue)
    {
        this.minValue = minValue;
    }

    public Object getOMaxValue()
    {
        return oMaxValue;
    }

    public void setOMaxValue(Object maxValue)
    {
        oMaxValue = maxValue;
    }

    public Object getOMinValue()
    {
        return oMinValue;
    }

    public void setOMinValue(Object minValue)
    {
        oMinValue = minValue;
    }

    public void release()
    {
        super.release();
        maxValue = null;
        minValue = null;
        oMaxValue = null;
        oMinValue = null;
    }
}
