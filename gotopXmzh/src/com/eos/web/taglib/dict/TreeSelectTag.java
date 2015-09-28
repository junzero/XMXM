// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TreeSelectTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            SelectTag

public class TreeSelectTag extends SelectTag
{

    private static final long serialVersionUID = 0x206f0cab26bd0445L;
    private String level;
    private int _level;

    public TreeSelectTag()
    {
        level = null;
        _level = -1;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public void setLevel(int level)
    {
        this.level = String.valueOf(level);
    }

    public int get_level()
    {
        return _level;
    }

    protected void prepareAttributes()
        throws JspException
    {
        super.prepareAttributes();
        if(_level != -1)
            component.setAttribute("level", (new StringBuilder()).append("").append(_level).toString());
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
            component = ComponentFactory.createWebComponent("dicttreeselect", true);
            initAttributes();
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 6;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _level = XpathUtil.getIntByXpathSupport(getRootObj(), level, -1);
    }

    public void release()
    {
        super.release();
        level = null;
        _level = -1;
    }
}
