// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FormaterFactory.java

package com.primeton.web.core.control.util;


// Referenced classes of package com.primeton.web.core.control.util:
//            DateFormaterImpl, NumberFormaterImpl, Formater

public class FormaterFactory
{

    public FormaterFactory()
    {
    }

    public static Formater getFormater(Object value, String pattern)
    {
        if(pattern.indexOf("yyyy") != -1 || pattern.indexOf("ss") != -1)
            return new DateFormaterImpl();
        else
            return new NumberFormaterImpl();
    }
}
