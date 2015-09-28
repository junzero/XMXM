// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FormatUtil.java

package com.primeton.web.core.control.util;


// Referenced classes of package com.primeton.web.core.control.util:
//            FormatException, FormaterFactory, Formater

public class FormatUtil
{

    public FormatUtil()
    {
    }

    public static String format(Object value, String pattern)
        throws FormatException
    {
        Formater fmt = FormaterFactory.getFormater(value, pattern);
        return fmt.format(value, pattern);
    }

    public static String format(Object value, String srcPattern, String pattern)
        throws FormatException
    {
        Formater fmt = FormaterFactory.getFormater(value, pattern);
        return fmt.format(value, srcPattern, pattern);
    }
}
