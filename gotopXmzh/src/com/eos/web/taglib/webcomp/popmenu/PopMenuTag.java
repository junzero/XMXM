// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   PopMenuTag.java

package com.eos.web.taglib.webcomp.popmenu;

import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp.popmenu:
//            MenuItemBaseTag

public class PopMenuTag extends MenuItemBaseTag
{

    private String onClickFunc;

    public PopMenuTag()
    {
        onClickFunc = null;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        onClickFunc = XpathUtil.getStringByXpathSupport(getRootObj(), onClickFunc);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buffer = new StringBuilder();
        buffer.append("<div id=\"").append(getId()).append("_container\" style=\"position:absolute;display:none\"></div>\n<script>\n");
        buffer.append("var popMenu = new PopMenu(\"").append(getId()).append("\");\n</script>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>\nvar rootMenu = $o(\"").append(getId()).append("\");\n");
        if(onClickFunc != null)
            buffer.append("rootMenu.onClickFunc=\"").append(onClickFunc).append("\";\n");
        buffer.append("rootMenu.init();\n</script>");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }

    public String getOnClickFunc()
    {
        return onClickFunc;
    }

    public void setOnClickFunc(String onClickFunc)
    {
        this.onClickFunc = onClickFunc;
    }

    public void release()
    {
        super.release();
        onClickFunc = null;
    }
}
