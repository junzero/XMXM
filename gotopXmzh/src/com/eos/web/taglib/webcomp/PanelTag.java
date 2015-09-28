// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   PanelTag.java

package com.eos.web.taglib.webcomp;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.*;
import com.gotop.util.security.ForUtil;

import javax.servlet.jsp.JspException;

public class PanelTag extends BaseTagSupport
{

    private static final long serialVersionUID = 1L;
    private String title;
    private String width;
    private String height;
    private String onExpandFunc;
    private String onCloseFunc;
    private String align;
    private String expand;
    private boolean _expand;
    private String titleStyle;
    private String titleStyleClass;
    private String bodyStyle;
    private String bodyStyleClass;

    public PanelTag()
    {
        title = null;
        expand = "true";
        _expand = true;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        title = XpathUtil.getStringByXpathSupport(getRootObj(), title);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        onExpandFunc = XpathUtil.getStringByXpathSupport(getRootObj(), onExpandFunc);
        onCloseFunc = XpathUtil.getStringByXpathSupport(getRootObj(), onCloseFunc);
        align = XpathUtil.getStringByXpathSupport(getRootObj(), align);
        expand = XpathUtil.getStringByXpathSupport(getRootObj(), expand);
        _expand = XpathUtil.getBooleanByXpathSupport(getRootObj(), expand, true);
        titleStyle = XpathUtil.getStringByXpathSupport(getRootObj(), titleStyle);
        titleStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), titleStyleClass);
        bodyStyle = XpathUtil.getStringByXpathSupport(getRootObj(), bodyStyle);
        bodyStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), bodyStyleClass);
    }

    public void release()
    {
        super.release();
        title = null;
        width = null;
        height = null;
        onExpandFunc = null;
        onCloseFunc = null;
        align = null;
        expand = "true";
        _expand = true;
        titleStyle = null;
        titleStyleClass = null;
        bodyStyle = null;
        bodyStyleClass = null;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("</td>");
        sb.append("\t</tr>");
        sb.append("</table>");
        sb.append("</div>");
        sb.append("<script>\n");
        sb.append("var panel = $o(\"").append(id).append("\");\n");
        if(onCloseFunc != null)
            sb.append("panel.onCloseFunc = \"").append(onCloseFunc).append("\";\n");
        if(onExpandFunc != null)
            sb.append("panel.onExpandFunc = \"").append(onExpandFunc).append("\";\n");
        if(_expand)
            sb.append("panel.status = true;\n");
        else
            sb.append("panel.status = false;\n");
        sb.append("panel.init();");
        sb.append("</script>\n");
        ResponseUtil.write(pageContext, sb.toString());
        release();
        return 1;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(id == null)
            id = (new StringBuilder()).append("eos-panel-").append(ForUtil.rRandom()).toString();
        StringBuilder sb = new StringBuilder();
        sb.append("<script>var panel = new Panel(\"").append(id).append("\");</script>");
        sb.append("<div id=\"").append(id).append("_container\" style=\"");
        if(width != null)
            sb.append("width:").append(width).append(";");
        if(height != null)
            sb.append("height:").append(height).append("");
        sb.append("\">");
        sb.append((new StringBuilder()).append("<table id=\"_").append(id).append("_panel_table\" border=\"0\"  width=\"100%\"  cellspacing=\"0\" cellpadding=\"0\" class=\"eos-panel-table\"").toString());
        if(getHeight() != null)
            sb.append("height=\"100%\" ");
        sb.append(">");
        sb.append("\t<tr>");
        sb.append("\t\t<td height=\"20\">");
        sb.append("\t\t<table  border=\"0\" width=\"100%\" height=\"20\" cellspacing=\"0\" cellpadding=\"0\">\n");
        sb.append("\t\t\t<tr>\n");
        sb.append("\t\t\t\t<td width=\"4px\"><div class=\"eos-panel-title-left\"></div></td>\n");
        sb.append("\t\t\t\t<td width=\"20px\"class=\"eos-panel-title\"><div id=\"").append(id).append("_button\"></div></td>\n");
        sb.append("\t\t\t\t<td ></td>");
        sb.append("\t\t\t\t<td class=\"eos-panel-title\"  nowarp ");
        if(titleStyleClass != null)
            sb.append("class=\"").append(titleStyleClass).append("\" ");
        if(titleStyle != null)
            sb.append("style=\"").append(titleStyle).append("\" ");
        if(align != null)
            sb.append("align=\"").append(align).append("\" ");
        sb.append("><nobr id=\"").append(id).append("_title\">").append(title != null ? StringUtil.htmlFilter(title) : "&nbsp;").append("</nobr></td>\n");
        sb.append("\t\t\t\t<td width=\"4px\"><div class=\"eos-panel-title-right\"></div></td>\n");
        sb.append("\t\t\t</tr>\n");
        sb.append("\t\t</table>\n");
        sb.append("\t\t</td>\n");
        sb.append("\t</tr>\n");
        if(_expand)
            sb.append("\t<tr layout=\"1\">\n");
        else
            sb.append("\t<tr style='display:none'>\n");
        sb.append("\t\t<td  class=\"eos-panel-body\"  valign=\"top\" ");
        if(getHeight() != null)
            sb.append("height=\"100%\" ");
        if(bodyStyle != null)
            sb.append("style=\"").append(bodyStyle).append("\" ");
        if(bodyStyleClass != null)
            sb.append("class=\"").append(bodyStyleClass).append("\" ");
        sb.append(">");
        ResponseUtil.write(pageContext, sb.toString());
        return 1;
    }

    public String getAlign()
    {
        return align;
    }

    public void setAlign(String align)
    {
        this.align = align;
    }

    public String getBodyStyle()
    {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle)
    {
        this.bodyStyle = bodyStyle;
    }

    public String getBodyStyleClass()
    {
        return bodyStyleClass;
    }

    public void setBodyStyleClass(String bodyStyleClass)
    {
        this.bodyStyleClass = bodyStyleClass;
    }

    public String getExpand()
    {
        return expand;
    }

    public void setExpand(String expand)
    {
        this.expand = expand;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getOnCloseFunc()
    {
        return onCloseFunc;
    }

    public void setOnCloseFunc(String onCloseFunc)
    {
        this.onCloseFunc = onCloseFunc;
    }

    public String getOnExpandFunc()
    {
        return onExpandFunc;
    }

    public void setOnExpandFunc(String onExpandFunc)
    {
        this.onExpandFunc = onExpandFunc;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitleStyle()
    {
        return titleStyle;
    }

    public void setTitleStyle(String titleStyle)
    {
        this.titleStyle = titleStyle;
    }

    public String getTitleStyleClass()
    {
        return titleStyleClass;
    }

    public void setTitleStyleClass(String titleStyleClass)
    {
        this.titleStyleClass = titleStyleClass;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }
}
