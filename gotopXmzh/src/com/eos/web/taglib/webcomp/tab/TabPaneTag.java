// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TabPaneTag.java

package com.eos.web.taglib.webcomp.tab;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;

public class TabPaneTag extends BaseTagSupport
{

    private static final long serialVersionUID = 0x52aa298b5d1f6324L;
    private String width;
    private String height;
    private String defaultTab;
    private String titleStyle;
    private String titleStyleClass;
    private String bodyStyle;
    private String bodyStyleClass;
    private String titleAlign;

    public TabPaneTag()
    {
        width = "100%";
        height = "100%";
        defaultTab = null;
        titleAlign = "top";
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        defaultTab = XpathUtil.getStringByXpathSupport(getRootObj(), defaultTab);
        titleAlign = XpathUtil.getStringByXpathSupport(getRootObj(), titleAlign);
        titleStyle = XpathUtil.getStringByXpathSupport(getRootObj(), titleStyle);
        titleStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), titleStyleClass);
        bodyStyle = XpathUtil.getStringByXpathSupport(getRootObj(), bodyStyle);
        bodyStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), bodyStyleClass);
        if(width == null)
            width = "100%";
        if(height == null)
            height = "100%";
    }

    private StringBuilder initTitleHtml()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<tr><td valign='top' style='height:22px'>\n");
        buffer.append("<table class=\"nav ");
        if(titleStyleClass != null)
            buffer.append(titleStyleClass);
        buffer.append("\" ");
        if(titleStyle != null)
            buffer.append(" style=\"").append(titleStyle).append("\"");
        buffer.append(" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n");
        buffer.append("<tr id=\"").append(getId()).append("_nav_tr\">");
        String navClass = "right-top";
        if("buttom".equals(titleAlign))
            navClass = "right-buttom";
        buffer.append("<td id=\"").append(getId()).append("_nav_pilot_container\" style=\"width:100%\" class=\"").append(navClass).append("\" align=\"right\">");
        buffer.append("<div style=\"display:none;white-space:nowrap;\" id=\"").append(getId()).append("_nav_pilot\">");
        buffer.append("\t</div>");
        buffer.append("</td>");
        buffer.append("</tr>");
        buffer.append("</table></td></tr>");
        return buffer;
    }

    private StringBuilder initStartHtml()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<div class=\"tab\" id=\"").append(getId()).append("_container\" style=\"width:").append(width).append(";height:").append(height).append(";\">");
        buffer.append("<table border='0' cellspacing='0' cellpadding='0' style='width:100%;height:100%'>");
        if("top".equals(titleAlign))
            buffer.append(initTitleHtml());
        buffer.append("<tr><td style=\"width:").append(width).append(";height:").append(height).append(";\">\n");
        buffer.append("<table border='0' cellspacing='0' cellpadding='0' style='width:100%;height:100%'><tr><td class=\"tab-body\" id=\"");
        buffer.append(getId()).append("_body\" valign=\"top\"  style=\"width:100%;height:100%\">\n");
        buffer.append("<div style='width:100%;height:100%;");
        if(bodyStyle != null)
            buffer.append(bodyStyle);
        buffer.append("'");
        if(bodyStyleClass != null)
            buffer.append(" class='").append(bodyStyleClass).append("'");
        buffer.append(">");
        return buffer;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>\n");
        buffer.append("var tabgroup = new TabPane(\"").append(getId()).append("\");\n");
        if(width != null)
            buffer.append("tabgroup.width = \"").append(width).append("\";\n");
        if(height != null)
            buffer.append("tabgroup.height = \"").append(height).append("\";\n");
        if(defaultTab != null)
            buffer.append("tabgroup.defaultTab = \"").append(defaultTab).append("\";\n");
        buffer.append("tabgroup.titleAlign = \"").append(titleAlign).append("\";\n");
        buffer.append("</script>");
        buffer.append(initStartHtml());
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("</div></td></tr></table>");
        buffer.append("</td></tr>");
        if("buttom".equals(titleAlign))
            buffer.append(initTitleHtml());
        buffer.append("</table></div>\n");
        buffer.append("<script>\n");
        buffer.append("var tabgroup = $o(\"").append(getId()).append("\");\n");
        buffer.append("tabgroup.init();\n");
        buffer.append("</script>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }

    public void release()
    {
        super.release();
        titleStyle = null;
        titleStyleClass = null;
        bodyStyle = null;
        bodyStyleClass = null;
        width = "100%";
        height = "100%";
        defaultTab = null;
        titleAlign = "top";
    }

    public String getDefaultTab()
    {
        return defaultTab;
    }

    public void setDefaultTab(String defaultTab)
    {
        this.defaultTab = defaultTab;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public String getTitleAlign()
    {
        return titleAlign;
    }

    public void setTitleAlign(String titleAlign)
    {
        this.titleAlign = titleAlign;
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
}
