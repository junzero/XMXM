// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Formater.java

package com.primeton.web.core.control.util;


// Referenced classes of package com.primeton.web.core.control.util:
//            FormatException

public interface Formater
{

    public abstract String format(Object obj, String s)
        throws FormatException;

    public abstract String format(Object obj, String s, String s1)
        throws FormatException;
}
