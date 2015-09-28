// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RadioTag.java

package com.eos.web.taglib.dict;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.dict:
//            BaseInputDictTag

public class RadioTag extends BaseInputDictTag
{

    private static final long serialVersionUID = 0xbadc186aa285a296L;
    private int _prerow;
    private String perrow;
    private String roles = null;

    public RadioTag()
    {
        perrow = null;
        roles = null;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("dictradio", modelField);
            if(perrow != null)
                component.setAttribute("perrow", perrow);
            if(roles != null)
            	component.setAttribute("roles", roles);
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 6;
    }

    public void release()
    {
        super.release();
        perrow = null;
        roles = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        if(perrow != null)
        {
            if(perrow.charAt(0) == '-')
                throw new JspException((new StringBuilder()).append("The attribute perrow:").append(perrow).append(" is invalid!").toString());
            _prerow = XpathUtil.getIntByXpathSupport(getRootObj(), perrow, 0, "perrow");
            if(_prerow == 0)
                throw new JspException((new StringBuilder()).append("The attribute perrow:").append(perrow).append(" is invalid!").toString());
        }
//        if(roles !=null){
//        	roles = XpathUtil.getStringByXpathSupport(getRootObj(), roles);
//        }
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
