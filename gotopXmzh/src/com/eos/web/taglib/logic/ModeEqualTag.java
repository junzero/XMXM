// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ModeEqualTag.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTagBase

public class ModeEqualTag extends EqualTagBase
{

    private String remainder;
    private String divisor;
    private Object oRemainder;
    private Object oDivisor;

    public ModeEqualTag()
    {
        remainder = null;
        divisor = null;
    }

    public boolean condition()
        throws JspException
    {
        int result = compare();
        return result == 1;
    }

    public int compare()
        throws JspException
    {
        Object value = prepareValue(getValueObject(), getPropertyValue());
        if(value == null)
            return 0;
        if(oRemainder == null)
            throw new JspException("Can not found remainder!");
        if(oDivisor == null)
            throw new JspException("Can not found divisor!");
        long remainder = getLong(oRemainder);
        long divisor = getLong(oDivisor);
        long lValue = getLong(value);
        if(divisor == 0L)
            throw new JspException("Divisor cat not be 0");
        return lValue % divisor != remainder ? 0 : 1;
    }

    private long getLong(Object obj)
        throws JspException
    {
        try
        {
            if((obj instanceof Long) || (obj instanceof Integer))
                return ((Number)obj).longValue();
        }
        catch(NumberFormatException e)
        {
            throw new JspException("mode标签输入参数必须是整数");
        }
        return Long.parseLong(obj.toString());
    }

    public void init()
        throws JspException
    {
        super.init();
        oDivisor = XpathUtil.getStringByXpathSupport(getRootObj(), divisor);
        oRemainder = XpathUtil.getStringByXpathSupport(getRootObj(), remainder);
    }

    public void release()
    {
        super.release();
        remainder = null;
        divisor = null;
    }

    public String getDivisor()
    {
        return divisor;
    }

    public void setDivisor(String divisor)
    {
        this.divisor = divisor;
    }

    public String getRemainder()
    {
        return remainder;
    }

    public void setRemainder(String remainder)
    {
        this.remainder = remainder;
    }
}
