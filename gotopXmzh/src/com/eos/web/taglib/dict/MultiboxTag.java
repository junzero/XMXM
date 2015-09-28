// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MultiboxTag.java

package com.eos.web.taglib.dict;

import com.primeton.web.core.control.ComponentFactory;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseInputDictTag

public class MultiboxTag extends BaseInputDictTag
{

    private static final long serialVersionUID = 0x497af97e6928fcf4L;

    public MultiboxTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
            component = ComponentFactory.createWebComponent("dictmultibox", modelField);
            initAttributes();
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 6;
    }
}
