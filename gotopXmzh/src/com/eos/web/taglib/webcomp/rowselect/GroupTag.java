// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   GroupTag.java

package com.eos.web.taglib.webcomp.rowselect;

import com.eos.web.taglib.basic.BaseTagSupport;
import javax.servlet.jsp.JspException;

public class GroupTag extends BaseTagSupport
{

    private int rowCount;

    public GroupTag()
    {
        rowCount = 0;
    }

    public int getRowCount()
    {
        return rowCount;
    }

    public void addRow()
    {
        rowCount++;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        rowCount = 0;
    }

    public void release()
    {
        super.release();
        rowCount = 0;
    }
}
