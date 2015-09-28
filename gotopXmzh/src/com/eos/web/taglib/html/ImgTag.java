// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ImgTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.*;
import com.gotop.util.security.ForUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseHandlerTag

public class ImgTag extends BaseHandlerTag
{

    private String src;
    private String border;
    private int _border;
    private String width;
    private String height;
    private String inContext;
    private boolean _inContext;
    private String locale;
    private String i18n;
    public boolean _i18n;

    public ImgTag()
    {
        src = null;
        border = null;
        _border = 0;
        width = null;
        height = null;
        inContext = "true";
        _inContext = true;
        locale = null;
        i18n = null;
        _i18n = false;
    }

    public String getBorder()
    {
        return border;
    }

    public void setBorder(String border)
    {
        this.border = border;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(String src)
    {
        this.src = src;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }

    public String getI18n()
    {
        return i18n;
    }

    public void setI18n(String i18n)
    {
        this.i18n = i18n;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _i18n = XpathUtil.getBooleanByXpathSupport(getRootObj(), i18n, false);
        locale = XpathUtil.getStringByXpathSupport(getRootObj(), locale, null);
        src = XpathUtil.getStringByXpathSupport(getRootObj(), src);
        _border = XpathUtil.getIntByXpathSupport(getRootObj(), border, 0);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        _inContext = XpathUtil.getBooleanByXpathSupport(getRootObj(), inContext, true);
    }

    public void prepareAttributes()
    {
        super.prepareAttributes();
        Object obj = getPropertyValue();
        if(obj != null)
            try
            {
                byte buffer[] = (byte[])(byte[])obj;
                String imageName = null;
                HttpSession session = pageContext.getSession();
                do
                    imageName = (new StringBuilder()).append("image").append(ForUtil.rRandom()).toString();
                while(session.getAttribute(imageName) != null);
                session.setAttribute(imageName, buffer);
                component.setAttribute("src", (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append("/common/jsp/image.jsp?id=").append(imageName).toString());
            }
            catch(Exception e)
            {
                src = obj.toString();
            }
        if(src != null)
        {
            if(_inContext)
                if(_i18n)
                    src = (new I18nUtil()).getFile(src, pageContext, getLocale());
                else
                if(src.charAt(0) == '/')
                    src = (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append(src).toString();
            component.setAttribute("src", src);
        }
        if(border != null)
            component.setAttribute("border", String.valueOf(_border));
        if(width != null)
            component.setAttribute("width", width);
        if(height != null)
            component.setAttribute("height", height);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("image");
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
        src = null;
        border = null;
        width = null;
        height = null;
        _border = 0;
        _inContext = true;
        inContext = "true";
        locale = null;
        i18n = null;
        _i18n = false;
    }

    public void setInContext(String context)
    {
        inContext = context;
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
