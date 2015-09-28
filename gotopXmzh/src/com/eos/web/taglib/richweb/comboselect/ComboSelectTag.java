// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ComboSelectTag.java

package com.eos.web.taglib.richweb.comboselect;

import com.eos.web.IParameterSupport;
import com.eos.web.taglib.html.BaseInputTag;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.*;
import java.util.HashMap;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TryCatchFinally;

public class ComboSelectTag extends BaseInputTag
    implements TryCatchFinally, IParameterSupport
{

    private static final long serialVersionUID = 1L;
    private String queryAction;
    private String xpath;
    private String id;
    private String style;
    private String styleClass;
    private String size;
    private String onChangeFunc;
    private String width;
    private String height;
    private String linkId;
    private String linkField;
    private String filterField;
    private String filterType;
    private String valueField;
    private String textField;
    private String readonly;
    private String allowInput;
    private String allowFilter;
    private String disabled;
    private boolean _readonly;
    private boolean _allowInput;
    private boolean _allowFilter;
    private boolean _disabled;
    private String readOnly;
    private Boolean _readOnly;
    private String optionsWidth;
    private String optionsHeight;
    private String validateFunc;
    private String initParamFunc;
    private String submitXpath;
    private String nullText;
    private HashMap parameterMap;
    private String comid;
    private String filterModel;
    
    public ComboSelectTag()
    {
        queryAction = null;
        xpath = null;
        _readonly = false;
        _allowInput = false;
        _allowFilter = true;
        _disabled = false;
        readOnly = "false";
        _readOnly = Boolean.valueOf(false);
        initParamFunc = null;
        submitXpath = null;
        parameterMap = new HashMap();
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        queryAction = XpathUtil.getStringByXpathSupport(getRootObj(), queryAction);
        xpath = XpathUtil.getStringByXpathSupport(getRootObj(), xpath);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        optionsWidth = XpathUtil.getStringByXpathSupport(getRootObj(), optionsWidth);
        optionsHeight = XpathUtil.getStringByXpathSupport(getRootObj(), optionsHeight);
        submitXpath = XpathUtil.getStringByXpathSupport(getRootObj(), submitXpath);
        size = XpathUtil.getStringByXpathSupport(getRootObj(), size);
        onChangeFunc = XpathUtil.getStringByXpathSupport(getRootObj(), onChangeFunc);
        linkId = XpathUtil.getStringByXpathSupport(getRootObj(), linkId);
        linkField = XpathUtil.getStringByXpathSupport(getRootObj(), linkField);
        filterField = XpathUtil.getStringByXpathSupport(getRootObj(), filterField);
        filterType = XpathUtil.getStringByXpathSupport(getRootObj(), filterType);
        valueField = XpathUtil.getStringByXpathSupport(getRootObj(), valueField);
        textField = XpathUtil.getStringByXpathSupport(getRootObj(), textField);
        validateFunc = XpathUtil.getStringByXpathSupport(getRootObj(), validateFunc);
        nullText = XpathUtil.getStringByXpathSupport(getRootObj(), nullText, nullText);
        initParamFunc = XpathUtil.getStringByXpathSupport(getRootObj(), initParamFunc);
        filterModel = XpathUtil.getStringByXpathSupport(getRootObj(), filterModel);
        _readonly = XpathUtil.getBooleanByXpathSupport(getRootObj(), readonly, _readonly);
        _allowInput = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowInput, _allowInput);
        _allowFilter = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowFilter, _allowFilter);
        _disabled = XpathUtil.getBooleanByXpathSupport(getRootObj(), disabled, _disabled);
        if(!_readonly)
            _readonly = XpathUtil.getBooleanByXpathSupport(getRootObj(), readOnly, _readOnly.booleanValue());
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        component.setAttribute("queryAction", queryAction);
        component.setAttribute("xpath", xpath);
        component.setAttribute("height", height);
        component.setAttribute("width", width);
        component.setAttribute("optionsWidth", optionsWidth);
        component.setAttribute("optionsHeight", optionsHeight);
        component.setAttribute("size", size);
        component.setAttribute("submitXpath", submitXpath);
        component.setAttribute("onChangeFunc", onChangeFunc);
        component.setAttribute("linkId", linkId);
        component.setAttribute("linkField", linkField);
        component.setAttribute("filterField", filterField);
        component.setAttribute("filterType", filterType);
        component.setAttribute("nullText", nullText);
        component.setAttribute("name", getName());
        if(getPropertyValue() != null)
            component.setAttribute("value", (new StringBuilder()).append("").append(getPropertyValue()).toString());
        else
            component.setAttribute("value", getValue());
        component.setAttribute("valueField", valueField);
        component.setAttribute("textField", textField);
        component.setAttribute("validateFunc", validateFunc);
        component.setAttribute("initParamFunc", initParamFunc);
        component.setAttribute("readonly", (new StringBuilder()).append("").append(_readonly).toString().toLowerCase());
        component.setAttribute("allowInput", (new StringBuilder()).append("").append(_allowInput).toString().toLowerCase());
        component.setAttribute("allowFilter", (new StringBuilder()).append("").append(_allowFilter).toString().toLowerCase());
        component.setAttribute("disabled", (new StringBuilder()).append("").append(_disabled).toString().toLowerCase());
        component.setAttribute("filterModel", filterModel);
        ((ComboSelect)component).setParameterMap(parameterMap);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("comboSelect", getModelField());
        }
        catch(Exception e)
        {
            throw new JspException("error for create coomboSelect component! ", e);
        }
        prepareAttributes();
        StringBuilder buffer = new StringBuilder();
        buffer.append("<!-- start of ComboSelect -->\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        try
        {
            ((ComboSelect)component).setRootObj(getRootObj());
            buffer.append(component.toHtml());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        buffer.append("<!-- end of ComboSelect -->\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 6;
    }

    public void release()
    {
        super.release();
        id = null;
        queryAction = null;
        nullText = null;
        xpath = null;
        initParamFunc = null;
        readonly = "false";
        _readonly = false;
        _allowInput = false;
        _allowFilter = true;
        _disabled = false;
        style = null;
        styleClass = null;
        size = null;
        onChangeFunc = null;
        width = null;
        height = null;
        linkId = null;
        linkField = null;
        filterField = null;
        filterType = null;
        valueField = null;
        textField = null;
        readonly = null;
        allowInput = null;
        allowFilter = null;
        disabled = null;
        optionsWidth = null;
        optionsHeight = null;
        validateFunc = null;
        parameterMap = new HashMap();
        submitXpath = null;
        comid = null;
        filterModel = null;
    }

    public String getOptionsWidth()
    {
        return optionsWidth;
    }

    public void setOptionsWidth(String optionsWidth)
    {
        this.optionsWidth = optionsWidth;
    }

    public String getOptionsHeight()
    {
        return optionsHeight;
    }

    public void setOptionsHeight(String optionsHeight)
    {
        this.optionsHeight = optionsHeight;
    }

    public void doCatch(Throwable throwable)
        throws Throwable
    {
        throw throwable;
    }

    public void doFinally()
    {
        release();
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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

    public String getReadonly()
    {
        return readonly;
    }

    public void setReadonly(String readonly)
    {
        this.readonly = readonly;
    }

    public String getDisabled()
    {
        return disabled;
    }

    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public String setOnChangeFunc()
    {
        return onChangeFunc;
    }

    public void setOnChangeFunc(String onChangeFunc)
    {
        this.onChangeFunc = onChangeFunc;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getLinkId()
    {
        return linkId;
    }

    public void setLinkId(String linkId)
    {
        this.linkId = linkId;
    }

    public String getLinkField()
    {
        return linkField;
    }

    public void setLinkField(String linkField)
    {
        this.linkField = linkField;
    }

    public String getAllowFilter()
    {
        return allowFilter;
    }

    public void setAllowFilter(String allowFilter)
    {
        this.allowFilter = allowFilter;
    }

    public String getFilterField()
    {
        return filterField;
    }

    public void setFilterField(String filterField)
    {
        this.filterField = filterField;
    }

    public String getFilterType()
    {
        return filterType;
    }

    public void setFilterType(String filterType)
    {
        this.filterType = filterType;
    }

    public String getAllowInput()
    {
        return allowInput;
    }

    public void setAllowInput(String allowInput)
    {
        this.allowInput = allowInput;
    }

    public String getValueField()
    {
        return valueField;
    }

    public void setValueField(String valueField)
    {
        this.valueField = valueField;
    }

    public String getTextField()
    {
        return textField;
    }

    public void setTextField(String textField)
    {
        this.textField = textField;
    }

    public static long getSerialVersionUID()
    {
        return 1L;
    }

    public String getQueryAction()
    {
        return queryAction;
    }

    public void setQueryAction(String queryAction)
    {
        this.queryAction = queryAction;
    }

    public String getXpath()
    {
        return xpath;
    }

    public void setXpath(String xpath)
    {
        this.xpath = xpath;
    }

    public String getNullText()
    {
        return nullText;
    }

    public void setNullText(String nullText)
    {
        this.nullText = nullText;
    }

    public String getInitParamFunc()
    {
        return initParamFunc;
    }

    public void setInitParamFunc(String initParamFunc)
    {
        this.initParamFunc = initParamFunc;
    }

    public String getValidateFunc()
    {
        return validateFunc;
    }

    public void setValidateFunc(String validateFunc)
    {
        this.validateFunc = validateFunc;
    }

    public String getOnChangeFunc()
    {
        return onChangeFunc;
    }

    public void addParameter(String name, Object value)
    {
        parameterMap.put(name, value);
    }

    public String getSubmitXpath()
    {
        return submitXpath;
    }

    public void setSubmitXpath(String submitXpath)
    {
        this.submitXpath = submitXpath;
    }

    public String getReadOnly()
    {
        return readOnly;
    }

    public void setReadOnly(String readOnly)
    {
        this.readOnly = readOnly;
    }

	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public String getFilterModel() {
		return filterModel;
	}

	public void setFilterModel(String filterModel) {
		this.filterModel = filterModel;
	}
	
}
