// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   LinkTag.java

package com.eos.web.taglib.html;

import com.eos.web.IParameterSupport;
import com.eos.web.taglib.util.RequestUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseHandlerTag

public class LinkTag extends BaseHandlerTag
    implements IParameterSupport
{

    private String href;
    private String target;
    private String confirmMsg;
    private String inContext;
    private boolean _inContext;

    public LinkTag()
    {
        href = null;
        target = null;
        confirmMsg = null;
        inContext = "true";
        _inContext = true;
    }

    public void setInContext(String context)
    {
        inContext = context;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public String getConfirmMsg()
    {
        return confirmMsg;
    }

    public void setConfirmMsg(String confirmMsg)
    {
        this.confirmMsg = confirmMsg;
    }

    public void addParameter(String key, Object value)
    {
        ((IParameterSupport)component).addParameter(key, value);
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        href = XpathUtil.getStringByXpathSupport(getRootObj(), href);
        target = XpathUtil.getStringByXpathSupport(getRootObj(), target);
        confirmMsg = XpathUtil.getStringByXpathSupport(getRootObj(), confirmMsg);
        _inContext = XpathUtil.getBooleanByXpathSupport(getRootObj(), inContext, true);
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        if(target != null)
            component.setAttribute("target", target);
        String url = RequestUtil.builderURL(pageContext, href, _inContext);
        if(getPropertyValue() != null)
            component.setValue(getPropertyValue());
        else
        if(getValue() != null)
            component.setValue(getValue());
        else
        if(href != null)
            component.setValue(url);
        if(href != null)
            component.setAttribute("href", url);
        if(confirmMsg != null)
            component.setAttribute("confirmMsg", confirmMsg);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("link");
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    public void release()
    {
        super.release();
        href = null;
        target = null;
        confirmMsg = null;
        _inContext = true;
        inContext = "true";
    }

    public boolean is_inContext()
    {
        return _inContext;
    }

    public String getInContext()
    {
        return inContext;
    }
}
