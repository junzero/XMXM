// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   WriteTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseDictTag

public class WriteTag extends BaseDictTag
{

    private static final long serialVersionUID = 0xcbc89e3433c61bc5L;
    private String level;
    private int _level;
    private boolean _ignore;
    private String ignore;
    
    private String roles = null;

    public WriteTag()
    {
        level = null;
        _level = 0;
        _ignore = true;
        ignore = "true";
        roles = null;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public int get_level()
    {
        return _level;
    }

    public void setIgnore(String ignore)
    {
        this.ignore = ignore;
    }

    public String getIgnore()
    {
        return ignore;
    }

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	protected void prepareAttributes()
        throws JspException
    {
        super.prepareAttributes();
        if(_level != 0)
            component.setAttribute("level", (new StringBuilder()).append("").append(_level).toString());
        if(roles!=null){
        	component.setAttribute("roles", roles);
        }
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
            component = ComponentFactory.createWebComponent("dictwrite", modelField);
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        initAttributes();
        if(getProperty() != null && getPropertyValue() == null && getValue() == null)
        {
            if(!_ignore)
                throw new JspException("d:write tag, can not found oject !");
            else
                return 6;
        } else
        {
            prepareAttributes();
            return 6;
        }
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _level = XpathUtil.getIntByXpathSupport(getRootObj(), level, 0);
        _ignore = XpathUtil.getBooleanByXpathSupport(getRootObj(), ignore, true, "ignore");
    }

    public void release()
    {
        super.release();
        level = null;
        _level = 0;
        _ignore = true;
        ignore = "true";
        roles = null;
    }
}
