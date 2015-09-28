// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   NotEqualTag.java

package com.eos.web.taglib.logic;

import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTag

public class NotEqualTag extends EqualTag
{

    public NotEqualTag()
    {
    }

    public boolean condition()
        throws JspException
    {
        return !super.condition();
    }
}
