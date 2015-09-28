// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   CheckBoxGroupTag.java

package com.eos.web.taglib.webcomp.rowselect;

import com.eos.web.taglib.util.ResponseUtil;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp.rowselect:
//            GroupTag

public class CheckBoxGroupTag extends GroupTag
{

    public CheckBoxGroupTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>\nvar checkgroup = new CheckGroup(\"").append(getId()).append("\");\n</script>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<script>\neventManager.add(window,'load',function(){var group = $o(\"").append(getId()).append("\");\ngroup.init();\n});</script>");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }
}
