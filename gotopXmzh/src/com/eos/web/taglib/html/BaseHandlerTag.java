// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   BaseHandlerTag.java

package com.eos.web.taglib.html;

import com.eos.system.exception.EOSException;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.web.core.control.WebComponent;
import java.util.*;
import javax.servlet.jsp.JspException;

public abstract class BaseHandlerTag extends BaseIteratorTagSupport
{

    protected WebComponent component;
    private String name;
    private String accesskey;
    private String tabindex;
    private Map attributes;
    private boolean _indexed;
    private String indexed;
    private String onclick;
    private String ondblclick;
    private String onmouseover;
    private String onmouseout;
    private String onmousemove;
    private String onmousedown;
    private String onmouseup;
    private String onkeydown;
    private String onkeyup;
    private String onkeypress;
    private String onselect;
    private String onchange;
    private String onblur;
    private String onfocus;
    private boolean _disabled;
    private String disabled;
    private boolean _readonly;
    private String readonly;
    private String style;
    private String styleClass;
    private String alt;
    private String title;

    public BaseHandlerTag()
    {
        component = null;
        name = null;
        accesskey = null;
        tabindex = null;
        attributes = null;
        _indexed = false;
        indexed = "false";
        onclick = null;
        ondblclick = null;
        onmouseover = null;
        onmouseout = null;
        onmousemove = null;
        onmousedown = null;
        onmouseup = null;
        onkeydown = null;
        onkeyup = null;
        onkeypress = null;
        onselect = null;
        onchange = null;
        onblur = null;
        onfocus = null;
        _disabled = false;
        disabled = "false";
        _readonly = false;
        readonly = "false";
        style = null;
        styleClass = null;
        alt = null;
        title = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAccesskey()
    {
        return accesskey;
    }

    public void setAccesskey(String accesskey)
    {
        this.accesskey = accesskey;
    }

    public String getAlt()
    {
        return alt;
    }

    public void setAlt(String alt)
    {
        this.alt = alt;
    }

    public String getOnblur()
    {
        return onblur;
    }

    public void setOnblur(String onblur)
    {
        this.onblur = onblur;
    }

    public String getOnchange()
    {
        return onchange;
    }

    public void setOnchange(String onchange)
    {
        this.onchange = onchange;
    }

    public String getOnclick()
    {
        return onclick;
    }

    public void setOnclick(String onclick)
    {
        this.onclick = onclick;
    }

    public String getOndblclick()
    {
        return ondblclick;
    }

    public void setOndblclick(String ondblclick)
    {
        this.ondblclick = ondblclick;
    }

    public String getOnfocus()
    {
        return onfocus;
    }

    public void setOnfocus(String onfocus)
    {
        this.onfocus = onfocus;
    }

    public String getOnkeydown()
    {
        return onkeydown;
    }

    public void setOnkeydown(String onkeydown)
    {
        this.onkeydown = onkeydown;
    }

    public String getOnkeypress()
    {
        return onkeypress;
    }

    public void setOnkeypress(String onkeypress)
    {
        this.onkeypress = onkeypress;
    }

    public String getOnkeyup()
    {
        return onkeyup;
    }

    public void setOnkeyup(String onkeyup)
    {
        this.onkeyup = onkeyup;
    }

    public String getOnmousedown()
    {
        return onmousedown;
    }

    public void setOnmousedown(String onmousedown)
    {
        this.onmousedown = onmousedown;
    }

    public String getOnmousemove()
    {
        return onmousemove;
    }

    public void setOnmousemove(String onmousemove)
    {
        this.onmousemove = onmousemove;
    }

    public String getOnmouseout()
    {
        return onmouseout;
    }

    public void setOnmouseout(String onmouseout)
    {
        this.onmouseout = onmouseout;
    }

    public String getOnmouseover()
    {
        return onmouseover;
    }

    public void setOnmouseover(String onmouseover)
    {
        this.onmouseover = onmouseover;
    }

    public String getOnmouseup()
    {
        return onmouseup;
    }

    public void setOnmouseup(String onmouseup)
    {
        this.onmouseup = onmouseup;
    }

    public String getOnselect()
    {
        return onselect;
    }

    public void setOnselect(String onselect)
    {
        this.onselect = onselect;
    }

    public String getReadonly()
    {
        return readonly;
    }

    public void setReadonly(String readonly)
    {
        this.readonly = readonly;
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

    public String getTabindex()
    {
        return tabindex;
    }

    public void setTabindex(String tabindex)
    {
        this.tabindex = tabindex;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public WebComponent getComponent()
    {
        return component;
    }

    protected void prepareAttributes()
    {
        if(getName() != null)
        {
            if(is_indexed())
            {
                int index = getIteratorIndex();
                if(index >= 0)
                    setName(StringUtil.getXpathNameWithIndex(getName(), index));
            }
            component.setName(getName());
        } else
        if(is_indexed())
        {
            int index = getIteratorIndex();
            if(index >= 0)
                component.setName(StringUtil.getXpathNameWithIndex(getProperty(), index));
            else
                component.setName(null);
        } else
        {
            component.setName(null);
        }
        if(attributes != null)
        {
            Iterator i$ = attributes.entrySet().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Object obj = i$.next();
                if(obj instanceof java.util.Map.Entry)
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)obj;
                    String key = String.valueOf(entry.getKey());
                    String value = String.valueOf(entry.getValue());
                    component.setAttribute(key, value);
                }
            } while(true);
        }
        prepareStyles();
        if(getTabindex() != null)
            component.setAttribute("tabindex", getTabindex());
        if(getAccesskey() != null)
            component.setAttribute("accesskey", getAccesskey());
        prepareEvents();
    }

    protected void prepareStyles()
    {
        if(getId() != null)
            component.setId(getId());
        if(getStyleClass() != null)
            component.setAttribute("class", getStyleClass());
        if(getStyle() != null)
            component.setAttribute("style", getStyle());
        if(getTitle() != null)
            component.setAttribute("title", getTitle());
        if(getAlt() != null)
            component.setAttribute("alt", getAlt());
    }

    protected void prepareEvents()
    {
        prepareFocusEvents();
        prepareTextEvents();
        prepareMouseEvents();
        prepareKeyEvents();
    }

    protected void prepareFocusEvents()
    {
        if(getOnblur() != null)
            component.setAttribute("onblur", getOnblur());
        if(getOnfocus() != null)
            component.setAttribute("onfocus", getOnfocus());
        if(is_readonly())
            component.setAttribute("readonly", "true");
        if(is_disabled())
            component.setAttribute("disabled", "true");
    }

    protected void prepareTextEvents()
    {
        if(getOnchange() != null)
            component.setAttribute("onchange", getOnchange());
        if(getOnselect() != null)
            component.setAttribute("onselect", getOnselect());
    }

    protected void prepareMouseEvents()
    {
        if(getOnclick() != null)
            component.setAttribute("onclick", getOnclick());
        if(getOndblclick() != null)
            component.setAttribute("ondblclick", getOndblclick());
        if(getOnmousedown() != null)
            component.setAttribute("onmousedown", getOnmousedown());
        if(getOnmouseover() != null)
            component.setAttribute("onmouseover", getOnmouseover());
        if(getOnmousemove() != null)
            component.setAttribute("onmousemove", getOnmousemove());
        if(getOnmouseout() != null)
            component.setAttribute("onmouseout", getOnmouseout());
        if(getOnmouseover() != null)
            component.setAttribute("onmouseover", getOnmouseover());
        if(getOnmouseup() != null)
            component.setAttribute("onmouseup", getOnmouseup());
    }

    protected void prepareKeyEvents()
    {
        if(getOnkeydown() != null)
            component.setAttribute("onkeydown", getOnkeydown());
        if(getOnkeypress() != null)
            component.setAttribute("onkeypress", getOnkeypress());
        if(getOnkeyup() != null)
            component.setAttribute("onkeyup", getOnkeyup());
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

    public void release()
    {
        super.release();
        name = null;
        accesskey = null;
        alt = null;
        indexed = "false";
        _disabled = false;
        _indexed = false;
        _readonly = false;
        onclick = null;
        ondblclick = null;
        onmouseover = null;
        onmouseout = null;
        onmousemove = null;
        onmousedown = null;
        onmouseup = null;
        onkeydown = null;
        onkeyup = null;
        onkeypress = null;
        onselect = null;
        onchange = null;
        onblur = null;
        onfocus = null;
        disabled = "false";
        readonly = "false";
        style = null;
        styleClass = null;
        tabindex = null;
        title = null;
        component = null;
        attributes = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _disabled = XpathUtil.getBooleanByXpathSupport(getRootObj(), disabled, false);
        accesskey = XpathUtil.getStringByXpathSupport(getRootObj(), accesskey);
        alt = XpathUtil.getStringByXpathSupport(getRootObj(), alt);
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        _indexed = XpathUtil.getBooleanByXpathSupport(getRootObj(), indexed, false);
        _readonly = XpathUtil.getBooleanByXpathSupport(getRootObj(), readonly, false);
        style = XpathUtil.getStringByXpathSupport(getRootObj(), style);
        styleClass = XpathUtil.getStringByXpathSupport(getRootObj(), styleClass);
        tabindex = XpathUtil.getStringByXpathSupport(getRootObj(), tabindex);
        title = XpathUtil.getStringByXpathSupport(getRootObj(), title);
    }

    public boolean is_disabled()
    {
        return _disabled;
    }

    public String getDisabled()
    {
        return disabled;
    }

    public void setDisabled(String disabled)
    {
        this.disabled = disabled;
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

    public void setComponent(WebComponent component)
    {
        this.component = component;
    }

    public boolean is_readonly()
    {
        return _readonly;
    }

    public Map getAttributes()
    {
        return attributes;
    }

    public void setAttributes(Map attributes)
    {
        this.attributes = attributes;
    }
}
