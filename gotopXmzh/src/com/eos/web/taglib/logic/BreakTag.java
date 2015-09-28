// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BreakTag.java

package com.eos.web.taglib.logic;

import com.eos.web.exception.BreakException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class BreakTag extends TagSupport
{

    public BreakTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        throw new BreakException();
    }
}
