// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   MenuItemBaseTag.java

package com.eos.web.taglib.webcomp.popmenu;

import com.eos.web.taglib.basic.BaseTagSupport;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp.popmenu:
//            IPopMenuItem

public class MenuItemBaseTag extends BaseTagSupport
    implements IPopMenuItem
{

    private int menuCount;

    public MenuItemBaseTag()
    {
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
    }

    public void release()
    {
        super.release();
        clearChild();
    }

    public int addChild()
    {
        return menuCount++;
    }

    public void clearChild()
    {
        menuCount = 0;
    }
}
