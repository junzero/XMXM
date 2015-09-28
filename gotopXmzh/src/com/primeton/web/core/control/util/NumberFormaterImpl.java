// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   NumberFormaterImpl.java

package com.primeton.web.core.control.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

// Referenced classes of package com.primeton.web.core.control.util:
//            NumberFormatException, Formater

public class NumberFormaterImpl
    implements Formater
{

    public NumberFormaterImpl()
    {
    }

    public String format(Object value, String pattern)
        throws NumberFormatException
    {
        return format(value, null, pattern);
    }

    public String format(Object value, String srcPattern, String pattern)
        throws NumberFormatException
    {
        if(value == null)
            return "";
        String str = value.toString();
        if((pattern == null) & pattern.trim().equals(""))
            return str;
        DecimalFormat targetFormater;
        try
        {
            targetFormater = new DecimalFormat(pattern);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new NumberFormatException((new StringBuilder()).append("输入格式错误: ").append(pattern).append("\n").append(e.getMessage()).toString());
        }
        if(srcPattern == null || srcPattern.length() == 0)
        {
            try
            {
                return targetFormater.format(roundUp(str, pattern));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            throw new NumberFormatException((new StringBuilder()).append("格式化错误: ").append(pattern).toString());
        }
        String tmpValue = null;
        try
        {
            DecimalFormat srcFormater = new DecimalFormat(srcPattern);
            tmpValue = (new StringBuilder()).append("").append(srcFormater.parse(value.toString())).toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new NumberFormatException((new StringBuilder()).append("输入的原格式错误: ").append(srcPattern).append("\n").append(e.getMessage()).toString());
        }
        try
        {
            return targetFormater.format(roundUp(tmpValue, pattern));
        }
        catch(Exception e)
        {
            throw new NumberFormatException((new StringBuilder()).append("数值格式化错误：").append(tmpValue).append("(").append(pattern).append(")\n").append(e.getMessage()).toString());
        }
    }

    private static double roundUp(String str, String pattern)
    {
        BigDecimal decimal = new BigDecimal(str);
        int op = pattern.indexOf(".");
        int scale = 0;
        if(op == -1)
        {
            scale = 0;
        } else
        {
            char chars[] = pattern.toCharArray();
            for(int i = op + 1; i < chars.length && (chars[i] == '0' || chars[i] == '#'); i++)
                scale++;

        }
        if(pattern.endsWith("‰"))
            scale += 3;
        else
        if(pattern.endsWith("%"))
            scale += 2;
        return decimal.setScale(scale, 4).doubleValue();
    }
}
