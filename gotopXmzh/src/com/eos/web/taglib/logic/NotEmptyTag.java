// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   NotEmptyTag.java

package com.eos.web.taglib.logic;

import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            EmptyTag

public class NotEmptyTag extends EmptyTag
{

    public NotEmptyTag()
    {
    }

    public boolean condition()
        throws JspException
    {
        return !super.condition();
    }
}
