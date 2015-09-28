// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseComponentTag.java

package com.eos.web.taglib.webcomp;

import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

public class BaseComponentTag extends BaseIteratorTagSupport
{

    private static final long serialVersionUID = 0x5dcc12fcb3067644L;
    private String tabindex;
    private String accesskey;
    private String name;
    private String readonly;
    private boolean _readonly;
    private String indexed;
    private boolean _indexed;
    private String style;
    private String styleClass;
    private String size;
    private String title;
    private String modelField;
    protected WebComponent component;

    public BaseComponentTag()
    {
        tabindex = null;
        accesskey = null;
        name = null;
        readonly = "false";
        _readonly = false;
        indexed = "false";
        _indexed = false;
        style = null;
        styleClass = null;
        size = null;
        title = null;
        component = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        tabindex = XpathUtil.getStringByXpathSupport(getRootObj(), tabindex);
        accesskey = XpathUtil.getStringByXpathSupport(getRootObj(), accesskey);
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        _readonly = XpathUtil.getBooleanByXpathSupport(getRootObj(), readonly, false);
        _indexed = XpathUtil.getBooleanByXpathSupport(getRootObj(), indexed, false);
        style = XpathUtil.getStringByXpathSupport(getRootObj(), style);
        styleClass = XpathUtil.getStringByXpathSupport(getRootObj(), styleClass);
        size = XpathUtil.getStringByXpathSupport(getRootObj(), size);
        title = XpathUtil.getStringByXpathSupport(getRootObj(), title);
        modelField = XpathUtil.getStringByXpathSupport(getRootObj(), modelField);
        if(name == null)
            name = getProperty();
    }

    protected void prepareIteratorAttributes()
    {
        if(is_indexed())
        {
            int index = getIteratorIndex();
            if(index >= 0)
            {
                if(getName() != null)
                    setName(StringUtil.getXpathNameWithIndex(getName(), index));
                if(getId() != null)
                    setId(StringUtil.getXpathNameWithIndex(getId(), index));
                else
                    setId((new StringBuilder()).append("comp").append(hashCode()).append(index).toString());
            }
        }
    }

    protected void prepareAttributes()
    {
        prepareIteratorAttributes();
        component.setName(getName());
        component.setAttribute("id", getId());
        component.setAttribute("tabindex", tabindex);
        component.setAttribute("accesskey", accesskey);
        if(getPropertyValue() != null)
            component.setValue(getPropertyValue());
        else
        if(getValue() != null)
            component.setValue(getValue());
        if(is_readonly())
            component.setAttribute("readonly", "true");
        component.setAttribute("style", style);
        component.setAttribute("styleClass", styleClass);
        component.setAttribute("size", size);
        component.setAttribute("title", title);
    }

    public int doEndTag()
        throws JspException
    {
        if(component == null)
        {
            ResponseUtil.write(pageContext, "&nbsp;");
            return 6;
        }
        String result = null;
        try
        {
            result = component.toHtml();
        }
        catch(Exception e)
        {
            throw new JspException((new StringBuilder()).append("error toHtml method in component :").append(component.getClass().getName()).toString());
        }
        ResponseUtil.write(pageContext, result);
        release();
        return 6;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
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

    public WebComponent getComponent()
    {
        return component;
    }

    public void setComponent(WebComponent component)
    {
        this.component = component;
    }

    public boolean is_indexed()
    {
        return _indexed;
    }

    public String getIndexed()
    {
        return indexed;
    }

    public void setIndexed(String indexed)
    {
        this.indexed = indexed;
    }

    public String getAccesskey()
    {
        return accesskey;
    }

    public void setAccesskey(String accesskey)
    {
        this.accesskey = accesskey;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTabindex()
    {
        return tabindex;
    }

    public void setTabindex(String tabIndex)
    {
        tabindex = tabIndex;
    }

    public boolean is_readonly()
    {
        return _readonly;
    }

    public String getReadonly()
    {
        return readonly;
    }

    public void setReadonly(String readonly)
    {
        this.readonly = readonly;
    }

    public void release()
    {
        super.release();
        tabindex = null;
        accesskey = null;
        name = null;
        readonly = "false";
        _readonly = false;
        indexed = "false";
        _indexed = false;
        style = null;
        styleClass = null;
        size = null;
        title = null;
        modelField = null;
    }

    public String getModelField()
    {
        return modelField;
    }

    public void setModelField(String modelField)
    {
        this.modelField = modelField;
    }
}
