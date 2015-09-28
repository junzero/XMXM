// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DASEntityRuntimeException.java

package com.primeton.utils;

import com.eos.system.exception.EOSRuntimeException;

public class DASEntityRuntimeException extends EOSRuntimeException
{

    /**
     * @deprecated Method DASEntityRuntimeException is deprecated
     */

    public DASEntityRuntimeException()
    {
    }

    public DASEntityRuntimeException(String code, Object params[], Throwable cause)
    {
        super(code, params, cause);
    }

    public DASEntityRuntimeException(String code, Object params[])
    {
        super(code, params);
    }

    public DASEntityRuntimeException(String code, String message, Object params[], Throwable cause)
    {
        super(code, message, params, cause);
    }

    public DASEntityRuntimeException(String code, String message, Object params[])
    {
        super(code, message, params);
    }

    public DASEntityRuntimeException(String code, String message, Throwable cause)
    {
        super(code, message, cause);
    }

    public DASEntityRuntimeException(String code, String message)
    {
        super(code, message);
    }

    public DASEntityRuntimeException(String code, Throwable cause)
    {
        super(code, cause);
    }

    public DASEntityRuntimeException(String code)
    {
        super(code);
    }

    public DASEntityRuntimeException(Throwable cause)
    {
        super(cause);
    }
}
