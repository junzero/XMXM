// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   CheckboxTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseInputDictTag

public class CheckboxTag extends BaseInputDictTag
{

    private static final long serialVersionUID = 0x6b745a6fad108472L;
    private String submitMethod;
    private int _prerow;
    private String perrow;
    private String roles = null;

    public CheckboxTag()
    {
        submitMethod = "standard";
        perrow = null;
        roles = null;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        String checkboxType = "dictCheckbox";
        if(submitMethod.equalsIgnoreCase("special"))
            checkboxType = "dictmultibox";
        try
        {
            component = ComponentFactory.createWebComponent(checkboxType, modelField);
            prepareAttributes();
            if(perrow != null)
                component.setAttribute("perrow", (new StringBuilder()).append("").append(_prerow).toString());
            if(roles != null)
            	component.setAttribute("roles", roles);
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
        submitMethod = XpathUtil.getStringByXpathSupport(getRootObj(), submitMethod);
        if(!submitMethod.equalsIgnoreCase("standard") && !submitMethod.equalsIgnoreCase("special"))
            throw new JspException((new StringBuilder()).append("The attribute sumbitMethod:").append(submitMethod).append(" is invalid!").toString());
        if(perrow != null)
        {
            if(perrow.charAt(0) == '-')
                throw new JspException((new StringBuilder()).append("The attribute perrow:").append(perrow).append(" is invalid!").toString());
            _prerow = XpathUtil.getIntByXpathSupport(getRootObj(), perrow, 0, "perrow");
            if(_prerow == 0)
                throw new JspException((new StringBuilder()).append("The attribute perrow:").append(perrow).append(" is invalid!").toString());
        }
    }

    public void release()
    {
        super.release();
        submitMethod = "standard";
        perrow = null;
    }

    public String getSubmitMethod()
    {
        return submitMethod;
    }

    public void setSubmitMethod(String submitMethod)
    {
        this.submitMethod = submitMethod;
    }

    public int get_prerow()
    {
        return _prerow;
    }

    public void set_prerow(int _prerow)
    {
        this._prerow = _prerow;
    }

    public String getPerrow()
    {
        return perrow;
    }

    public void setPerrow(String perrow)
    {
        this.perrow = perrow;
    }

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
