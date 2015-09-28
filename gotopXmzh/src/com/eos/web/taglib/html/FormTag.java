// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FormTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.*;
import com.gotop.util.security.ForUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseHandlerTag

public class FormTag extends BaseHandlerTag
{

    private String action;
    private String enctype;
    private String method;
    private String target;
    private String focus;
    private String inContext;
    private boolean _inContext;
    private String extAttr;
    private String onreset;
    private String onsubmit;
    private String checkType;

    public FormTag()
    {
        action = null;
        enctype = null;
        method = "post";
        target = null;
        focus = null;
        inContext = "true";
        _inContext = true;
        extAttr = null;
        onreset = null;
        onsubmit = null;
        checkType = null;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getEnctype()
    {
        return enctype;
    }

    public void setEnctype(String enctype)
    {
        this.enctype = enctype;
    }

    public String getFocus()
    {
        return focus;
    }

    public void setFocus(String focus)
    {
        this.focus = focus;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public String getExtAttr()
    {
        return extAttr;
    }

    public void setExtAttr(String extAttr)
    {
        this.extAttr = extAttr;
    }

    public String getOnreset()
    {
        return onreset;
    }

    public void setOnreset(String onreset)
    {
        this.onreset = onreset;
    }

    public String getOnsubmit()
    {
        return onsubmit;
    }

    public void setOnsubmit(String onsubmit)
    {
        this.onsubmit = onsubmit;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        action = XpathUtil.getStringByXpathSupport(getRootObj(), action);
        target = XpathUtil.getStringByXpathSupport(getRootObj(), target);
        enctype = XpathUtil.getStringByXpathSupport(getRootObj(), enctype);
        method = XpathUtil.getStringByXpathSupport(getRootObj(), method);
        focus = XpathUtil.getStringByXpathSupport(getRootObj(), focus);
        _inContext = XpathUtil.getBooleanByXpathSupport(getRootObj(), inContext, false);
        onreset = XpathUtil.getStringByXpathSupport(getRootObj(), onreset);
        onsubmit = XpathUtil.getStringByXpathSupport(getRootObj(), onsubmit);
        extAttr = XpathUtil.getStringByXpathSupport(getRootObj(), extAttr);
        checkType = XpathUtil.getStringByXpathSupport(getRootObj(), checkType);
        if(getName() == null && getId() == null)
            setName((new StringBuilder()).append("_name").append(ForUtil.rRandom()).toString());
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
        if(action != null)
        {
            action = RequestUtil.builderURL(pageContext, action, _inContext);
            component.setAttribute("action", action);
        } else
        {
            Object flowUrl = pageContext.getRequest().getAttribute("_eosLastRequestURL");
            if(flowUrl != null)
                component.setAttribute("action", String.valueOf(flowUrl));
        }
        if(target != null)
            component.setAttribute("target", target);
        if(enctype != null)
            component.setAttribute("enctype", enctype);
        if(method != null)
            component.setAttribute("method", method);
        if(focus != null)
            component.setAttribute("focus", focus);
        if(extAttr != null)
            component.setAttribute("extAttr", extAttr);
        if(onreset != null)
            component.setAttribute("onreset", onreset);
        if(onsubmit != null)
            component.setAttribute("onsubmit", onsubmit);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(getId() == null)
            setId((new StringBuilder()).append("form").append(ForUtil.rRandom()).toString());
        try
        {
            component = ComponentFactory.createWebComponent("form");
            prepareAttributes();
            ResponseUtil.write(pageContext, component.toHtml());
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
        StringBuilder buffer = new StringBuilder();
        Object _eosFlowKey = pageContext.getRequest().getAttribute("_eosFlowKey");
        if(_eosFlowKey != null)
            buffer.append("<input type='hidden' name='").append("_eosFlowKey").append("' value='").append(_eosFlowKey).append("'/>");
        Object _eosFlowDataContext = pageContext.getRequest().getAttribute("_eosFlowDataContext");
        if(_eosFlowDataContext != null)
            buffer.append("<input type='hidden' name='").append("_eosFlowDataContext").append("' value='").append(_eosFlowDataContext).append("'/>");
        buffer.append("</form>");
        buffer.append("<script type=\"text/javascript\">");
        String formObj = null;
        if(id != null)
            formObj = (new StringBuilder()).append("$id('").append(id).append("')").toString();
        else
        if(getName() != null)
            formObj = (new StringBuilder()).append("$name('").append(getName()).append("')").toString();
        if(formObj != null)
        {
            if("blur".equals(checkType))
                buffer.append((new StringBuilder()).append("checkOnblur(").append(formObj).append(");").toString());
            else
            if("keypress".equals(checkType))
                buffer.append((new StringBuilder()).append("checkOnkeypress(").append(formObj).append(");").toString());
            buffer.append((new StringBuilder()).append("checkOnsubmit(").append(formObj).append(");").toString());
        }
        buffer.append("");
        buffer.append("</script>");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 6;
    }

    public void release()
    {
        super.release();
        action = null;
        enctype = null;
        method = "post";
        target = null;
        focus = null;
        _inContext = true;
        inContext = "true";
        extAttr = null;
        onreset = null;
        onsubmit = null;
        checkType = null;
    }

    public String getInContext()
    {
        return inContext;
    }

    public void setInContext(String inContext)
    {
        this.inContext = inContext;
    }

    public boolean is_inContext()
    {
        return _inContext;
    }

    public String getCheckType()
    {
        return checkType;
    }

    public void setCheckType(String checkType)
    {
        this.checkType = checkType;
    }
}
