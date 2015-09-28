// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SwitchCheckboxTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseInputDictTag

public class SwitchCheckboxTag extends BaseInputDictTag
{

    private static final long serialVersionUID = 0xed6b8528dc8719aaL;
    private String showLabel;
    private boolean _showLabel;

    public SwitchCheckboxTag()
    {
        showLabel = "true";
        _showLabel = true;
    }

    public String getShowLabel()
    {
        return showLabel;
    }

    public void setShowLabel(String showLabel)
    {
        this.showLabel = showLabel;
    }

    public boolean is_showLabel()
    {
        return _showLabel;
    }

    protected void prepareAttributes()
        throws JspException
    {
        super.prepareAttributes();
        if(is_showLabel())
            component.setAttribute("showLabel", "true");
        else
            component.setAttribute("showLabel", "false");
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
            component = ComponentFactory.createWebComponent("dictswitchcheckbox", true);
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
        _showLabel = XpathUtil.getBooleanByXpathSupport(getRootObj(), showLabel, false);
    }

    public void release()
    {
        super.release();
        showLabel = "true";
        _showLabel = false;
    }
}
