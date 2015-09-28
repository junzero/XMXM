// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   InTag.java

package com.eos.web.taglib.logic;

import com.eos.web.taglib.util.XpathUtil;
import java.util.StringTokenizer;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTagBase

public class InTag extends EqualTagBase
{

    private String seperator;

    public InTag()
    {
        seperator = ",";
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
label0:
        {
            Object value = prepareValue(getValueObject(), getPropertyValue());
            Object targetValue = prepareValue(getTargetValueObject(), getTargetPropertyValue());
            if(value == null || targetValue == null)
                return 0;
            String sValue = value.toString();
            String sTagetValue = targetValue.toString();
            StringTokenizer s = new StringTokenizer(sTagetValue, seperator);
            String tmp;
            if(isBIgnoreCase())
            {
                do
                {
                    if(!s.hasMoreTokens())
                        break label0;
                    tmp = s.nextToken();
                } while(!sValue.equalsIgnoreCase(tmp));
                return 1;
            }
            do
            {
                if(!s.hasMoreTokens())
                    break label0;
                tmp = s.nextToken();
            } while(!sValue.equals(tmp));
            return 1;
        }
        return 0;
    }

    public String getSeperator()
    {
        return seperator;
    }

    public void setSeperator(String seperator)
    {
        this.seperator = seperator;
    }

    public void init()
        throws JspException
    {
        super.init();
        seperator = XpathUtil.getStringByXpathSupport(getRootObj(), seperator);
    }

    public void release()
    {
        super.release();
        seperator = ",";
    }
}
