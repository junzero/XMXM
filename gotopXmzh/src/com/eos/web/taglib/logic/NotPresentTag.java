// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   NotPresentTag.java

package com.eos.web.taglib.logic;

import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.logic:
//            PresentTag

public class NotPresentTag extends PresentTag
{

    public NotPresentTag()
    {
    }

    public boolean condition()
        throws JspException
    {
        return !super.condition();
    }
}
