// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MultiSelectTag.java

package com.eos.web.taglib.dict;

import com.primeton.web.core.control.ComponentFactory;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            SelectTag

public class MultiSelectTag extends SelectTag
{

    private static final long serialVersionUID = 0x352d0f4b704771f3L;

    public MultiSelectTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
            component = ComponentFactory.createWebComponent("dictmultiselect", modelField);
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
