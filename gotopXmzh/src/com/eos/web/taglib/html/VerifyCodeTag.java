// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   VerifyCodeTag.java

package com.eos.web.taglib.html;

import com.eos.system.exception.EOSException;
import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

public class VerifyCodeTag extends BaseTagSupport
{

    private String name;
    private String validateAttr;
    private String hasInput;
    private int iLength;
    private String length;
    private String imageHeight;
    private int iImageHeight;
    private String type;
    private String style;
    private String styleClass;
    private WebComponent component;

    public VerifyCodeTag()
    {
        name = null;
        validateAttr = null;
        hasInput = "true";
        iLength = 4;
        length = null;
        imageHeight = null;
        iImageHeight = 21;
        type = null;
        style = null;
        styleClass = null;
        component = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        validateAttr = XpathUtil.getStringByXpathSupport(getRootObj(), validateAttr);
        hasInput = XpathUtil.getStringByXpathSupport(getRootObj(), hasInput, "true");
        if(!hasInput.equalsIgnoreCase("true") && !hasInput.equalsIgnoreCase("false"))
            throw new JspException((new StringBuilder()).append("The attribue hasInput:").append(hasInput).append(" is invalid.").toString());
        iLength = XpathUtil.getIntByXpathSupport(getRootObj(), length, 4, length);
        if(iLength < 4 || iLength > 12)
            throw new JspException((new StringBuilder()).append("The attribue length:").append(length).append(" is invalid.").toString());
        iImageHeight = XpathUtil.getIntByXpathSupport(getRootObj(), imageHeight, 21, length);
        type = XpathUtil.getStringByXpathSupport(getRootObj(), type, "number");
        if(!type.equalsIgnoreCase("char") && !type.equalsIgnoreCase("number"))
        {
            throw new JspException((new StringBuilder()).append("The attribue type:").append(type).append(" is invalid.").toString());
        } else
        {
            style = XpathUtil.getStringByXpathSupport(getRootObj(), style);
            styleClass = XpathUtil.getStringByXpathSupport(getRootObj(), styleClass);
            return;
        }
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("verifyCode");
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        if(component == null)
            return 6;
        try
        {
            ResponseUtil.write(pageContext, component.toHtml());
        }
        catch(Exception e)
        {
            EOSException ex = new EOSException("17001211", (new StringBuilder()).append("error toHtml method in component :").append(component.getClass().getName()).toString(), e);
            throw new JspException(ex);
        }
        release();
        return 6;
    }

    private void prepareAttributes()
    {
        component.setName(name);
        component.setAttribute("type", type);
        component.setAttribute("validateAttr", validateAttr);
        component.setAttribute("length", (new StringBuilder()).append(iLength).append("").toString());
        component.setAttribute("imageHeight", (new StringBuilder()).append(iImageHeight).append("").toString());
        component.setAttribute("validateAttr", validateAttr);
        component.setAttribute("style", style);
        component.setAttribute("styleClass", styleClass);
        component.setAttribute("hasInput", hasInput);
    }

    public String getImageHeight()
    {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight)
    {
        this.imageHeight = imageHeight;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength(String length)
    {
        this.length = length;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getValidateAttr()
    {
        return validateAttr;
    }

    public void setValidateAttr(String validateAttr)
    {
        this.validateAttr = validateAttr;
    }

    public String getHasInput()
    {
        return hasInput;
    }

    public void setHasInput(String hasInput)
    {
        this.hasInput = hasInput;
    }

    public void release()
    {
        super.release();
        name = null;
        style = null;
        styleClass = null;
        component = null;
        validateAttr = null;
        hasInput = "true";
        length = null;
        imageHeight = null;
        type = null;
    }

    public String getStyle()
    {
        return style;
    }

    public void setStyle(String style)
    {
        this.style = style;
    }

    public String getStyleClass()
    {
        return styleClass;
    }

    public void setStyleClass(String styleClass)
    {
        this.styleClass = styleClass;
    }
}
