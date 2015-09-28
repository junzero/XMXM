// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SelectTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseInputDictTag

public class SelectTag extends BaseInputDictTag
{

    private static final long serialVersionUID = 0xfb077cba3e188df2L;
    private String multiple;
    private boolean _multiple;
    private String size;
    private int _size;
    private String nullOption;
    private String nullLabel;
    private String submitMethod;
    private String roles = null;

    public SelectTag()
    {
        multiple = null;
        _multiple = false;
        size = null;
        _size = 10;
        nullOption = null;
        nullLabel = null;
        submitMethod = "standard";
        roles = null;
    }

    public String getMultiple()
    {
        return multiple;
    }

    public void setMultiple(String multiple)
    {
        this.multiple = multiple;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String getNullLabel()
    {
        return nullLabel;
    }

    public void setNullLabel(String nullLabel)
    {
        this.nullLabel = nullLabel;
    }

    public String getNullOption()
    {
        return nullOption;
    }

    public void setNullOption(String nullOption)
    {
        this.nullOption = nullOption;
    }

    public boolean is_multiple()
    {
        return _multiple;
    }

    public int get_size()
    {
        return _size;
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
        if(_multiple)
            component.setAttribute("multiple", "true");
        if(nullLabel != null)
        {
            component.setAttribute("nullLabel", nullLabel);
            component.setAttribute("nullOption", "true");
        }
        if(size != null && _size != 1)
            component.setAttribute("size", Integer.toString(_size));
        if(roles != null)
        	component.setAttribute("roles", roles);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        String selectType = "dictselect";
        if(submitMethod.equalsIgnoreCase("special") && _multiple)
            selectType = "dictmultiselect";
        try
        {
            component = ComponentFactory.createWebComponent(selectType, modelField);
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
        _multiple = XpathUtil.getBooleanByXpathSupport(getRootObj(), multiple, false, "multiple");
        _size = XpathUtil.getIntByXpathSupport(getRootObj(), size, 10);
        if(_size <= 0)
            throw new JspException((new StringBuilder()).append("The attribute size:").append(_size).append(" is invalid!").toString());
        nullLabel = XpathUtil.getStringByXpathSupport(getRootObj(), nullLabel);
        submitMethod = XpathUtil.getStringByXpathSupport(getRootObj(), submitMethod);
        if(!submitMethod.equalsIgnoreCase("standard") && !submitMethod.equalsIgnoreCase("special"))
            throw new JspException((new StringBuilder()).append("The attribute sumbitMethod:").append(submitMethod).append(" is invalid!").toString());
        else
            return;
    }

    public void release()
    {
        super.release();
        multiple = null;
        _multiple = false;
        size = null;
        _size = 1;
        nullOption = null;
        nullLabel = null;
        submitMethod = "standard";
        roles = null;
    }

    public String getSubmitMethod()
    {
        return submitMethod;
    }

    public void setSubmitMethod(String submitMethod)
    {
        this.submitMethod = submitMethod;
    }
}
