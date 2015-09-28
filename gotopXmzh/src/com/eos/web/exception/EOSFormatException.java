// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EOSFormatException.java

package com.eos.web.exception;

import com.eos.system.exception.EOSException;

public class EOSFormatException extends EOSException
{

    public EOSFormatException(String code)
    {
        super(code);
    }

    public EOSFormatException(String code, String message)
    {
        super(code, message);
    }

    public EOSFormatException(String code, String message, Throwable exception)
    {
        super(code, message, exception);
    }
}
