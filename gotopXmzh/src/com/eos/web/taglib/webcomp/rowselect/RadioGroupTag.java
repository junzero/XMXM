// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RadioGroupTag.java

package com.eos.web.taglib.webcomp.rowselect;

import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp.rowselect:
//            GroupTag

public class RadioGroupTag extends GroupTag
{

    private String defaultRow;

    public RadioGroupTag()
    {
    }

    public String getDefaultRow()
    {
        return defaultRow;
    }

    public void setDefaultRow(String defaultRow)
    {
        this.defaultRow = defaultRow;
    }

    public void release()
    {
        defaultRow = null;
        super.release();
    }

    public void initAttributes()
        throws JspException
    {
        defaultRow = XpathUtil.getStringByXpathSupport(getRootObj(), defaultRow);
        super.initAttributes();
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>\nvar radiogroup = new RadioGroup(\"").append(getId()).append("\");\n");
        if(defaultRow != null)
            buffer.append("radiogroup.defaultRow = \"").append(defaultRow).append("\"");
        buffer.append("</script>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>eventManager.add(window,'load',function(){\nvar group = $o(\"").append(getId()).append("\");\ngroup.init();\n});</script>");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }
}
