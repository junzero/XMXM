// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   LessThanTag.java

package com.eos.web.taglib.logic;

import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            GreaterEqualTag

public class LessThanTag extends GreaterEqualTag
{

    public LessThanTag()
    {
    }

    public boolean condition()
        throws JspException
    {
        int result = compare();
        if(result == -999)
            return false;
        return result < 0;
    }
}