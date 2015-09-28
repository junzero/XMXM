// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EOSNumberParseException.java

package com.eos.web.exception;

import com.eos.system.exception.EOSException;

public class EOSNumberParseException extends EOSException
{

    public EOSNumberParseException(String code)
    {
        super(code);
    }

    public EOSNumberParseException(String code, String message)
    {
        super(code, message);
    }

    public EOSNumberParseException(String code, String message, Throwable exception)
    {
        super(code, message, exception);
    }
}
