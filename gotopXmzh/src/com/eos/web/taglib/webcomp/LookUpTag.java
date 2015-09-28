// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   LookUpTag.java

package com.eos.web.taglib.webcomp;

import com.eos.system.exception.EOSException;
import com.eos.web.IParameterSupport;
import com.eos.web.taglib.html.BaseInputTag;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

public class LookUpTag extends BaseInputTag
    implements IParameterSupport
{

    private static final long serialVersionUID = 0xc976a2f57b666fdeL;
    private String lookupUrl;
    private String width;
    private String height;
    private String left;
    private String lookupWidth;
    private String top;
    private String displayValue;
    private String onReturnFunc;
    private String useIeModel;
    private boolean _useIeModel;
    private Map params;
    private String dialogTitle;
    private String displayProperty;
    private String displayName;
    private String readOnly;
    private String allowInput;
    private Boolean _allowInput;
    private Boolean _readOnly;

    public LookUpTag()
    {
        lookupUrl = null;
        width = null;
        height = null;
        left = null;
        lookupWidth = null;
        top = null;
        displayValue = null;
        onReturnFunc = null;
        useIeModel = "false";
        _useIeModel = false;
        params = new HashMap();
        dialogTitle = null;
        displayProperty = null;
        displayName = null;
        readOnly = "false";
        allowInput = "true";
        _allowInput = Boolean.valueOf(true);
        _readOnly = Boolean.valueOf(false);
    }

    public String getAllowInput()
    {
        return allowInput;
    }

    public void setAllowInput(String allowInput)
    {
        this.allowInput = allowInput;
    }

    public String getReadOnly()
    {
        return readOnly;
    }

    public void setReadOnly(String readOnly)
    {
        this.readOnly = readOnly;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        lookupUrl = XpathUtil.getStringByXpathSupport(getRootObj(), lookupUrl);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        left = XpathUtil.getStringByXpathSupport(getRootObj(), left);
        top = XpathUtil.getStringByXpathSupport(getRootObj(), top);
        displayValue = XpathUtil.getStringByXpathSupport(getRootObj(), displayValue);
        _useIeModel = XpathUtil.getBooleanByXpathSupport(getRootObj(), useIeModel, false, "useIeModel");
        dialogTitle = XpathUtil.getStringByXpathSupport(getRootObj(), dialogTitle);
        displayProperty = XpathUtil.getStringByXpathSupport(getRootObj(), displayProperty);
        displayName = XpathUtil.getStringByXpathSupport(getRootObj(), displayName);
        _allowInput = Boolean.valueOf(XpathUtil.getBooleanByXpathSupport(getRootObj(), allowInput, true));
        _readOnly = Boolean.valueOf(XpathUtil.getBooleanByXpathSupport(getRootObj(), readOnly, false));
        lookupWidth = XpathUtil.getStringByXpathSupport(getRootObj(), lookupWidth);
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("lookupUrl", lookupUrl);
        component.setAttribute("lookupWidth", lookupWidth);
        component.setAttribute("width", width);
        component.setAttribute("height", height);
        component.setAttribute("left", left);
        component.setAttribute("top", top);
        component.setAttribute("displayValue", displayValue);
        component.setAttribute("onReturnFunc", onReturnFunc);
        component.setAttribute("dialogTitle", dialogTitle);
        component.setAttribute("displayName", displayName);
        component.setAttribute("useIeModel", String.valueOf(_useIeModel));
        component.setAttribute("readOnly", (new StringBuilder()).append(_readOnly).append("").toString());
        if(!_allowInput.booleanValue())
            component.setAttribute("readonly", "true");
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Object dispValue = null;
        if(getIterateId() == null)
        {
            if(displayProperty != null)
                dispValue = XpathUtil.getObjectByXpath(getRootObj(), displayProperty);
        } else
        {
            Object rootObj = pageContext.getAttribute(getIterateId());
            if(displayProperty != null && rootObj != null)
                dispValue = XpathUtil.getObjectByXpath(rootObj, displayProperty);
        }
        if(dispValue != null)
            displayValue = String.valueOf(dispValue);
        if(displayName == null)
            displayName = displayProperty;
        try
        {
            component = ComponentFactory.createWebComponent("lookup", getModelField());
        }
        catch(EOSException e)
        {
            throw new JspException("error for create lookup component! ", e);
        }
        prepareAttributes();
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        ((Lookup)component).setParams(params);
        try
        {
            String output = component.toHtml();
            ResponseUtil.write(pageContext, output);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        release();
        return 1;
    }

    public void release()
    {
        super.release();
        lookupUrl = null;
        width = null;
        height = null;
        left = null;
        top = null;
        displayValue = null;
        onReturnFunc = null;
        useIeModel = "false";
        _useIeModel = false;
        dialogTitle = null;
        displayName = null;
        displayProperty = null;
        readOnly = "false";
        allowInput = "true";
        lookupWidth = null;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }

    public void setDisplayValue(String displayValue)
    {
        this.displayValue = displayValue;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getLeft()
    {
        return left;
    }

    public void setLeft(String left)
    {
        this.left = left;
    }

    public String getLookupUrl()
    {
        return lookupUrl;
    }

    public void setLookupUrl(String lookupUrl)
    {
        this.lookupUrl = lookupUrl;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public void addParameter(String name, Object value)
    {
        params.put(name, value);
    }

    public String getTop()
    {
        return top;
    }

    public void setTop(String top)
    {
        this.top = top;
    }

    public String getOnReturnFunc()
    {
        return onReturnFunc;
    }

    public void setOnReturnFunc(String onColse)
    {
        onReturnFunc = onColse;
    }

    public String getUseIeModel()
    {
        return useIeModel;
    }

    public void setUseIeModel(String useIeModel)
    {
        this.useIeModel = useIeModel;
    }

    public String getDialogTitle()
    {
        return dialogTitle;
    }

    public void setDialogTitle(String dialogTitle)
    {
        this.dialogTitle = dialogTitle;
    }

    public String getDisplayProperty()
    {
        return displayProperty;
    }

    public void setDisplayProperty(String displayProperty)
    {
        this.displayProperty = displayProperty;
    }

    public String getLookupWidth()
    {
        return lookupWidth;
    }

    public void setLookupWidth(String lookupWidth)
    {
        this.lookupWidth = lookupWidth;
    }
}
