// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RowCheckBoxTag.java

package com.eos.web.taglib.webcomp.rowselect;

import com.eos.web.taglib.util.*;
import java.util.*;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.webcomp.rowselect:
//            RowSelectTag

public class RowCheckBoxTag extends RowSelectTag
{

    private String showCheckBox;
    private boolean _showCheckBox;

    public RowCheckBoxTag()
    {
        showCheckBox = "true";
        _showCheckBox = true;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _showCheckBox = XpathUtil.getBooleanByXpathSupport(getRootObj(), showCheckBox, true);
    }

    public void release()
    {
        super.release();
        showCheckBox = null;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append("<div id=\"").append(getId()).append("_container\">");
        buffer.append("</div>\n<script>\nvar eos_rowbox = new rowCheckBox(\"").append(getId()).append("\");");
        buffer.append("with(eos_rowbox){\n");
        buffer.append("params = [");
        boolean first = true;
        for(Iterator i$ = getParams().keySet().iterator(); i$.hasNext();)
        {
            String key = (String)i$.next();
            Object value = getParams().get(key);
            String name = key;
            if(is_indexed())
                name = StringUtil.getXpathNameWithIndex(key, getCurrCount());
            if(first)
            {
                buffer.append((new StringBuilder()).append("{name:'").append(name).append("',value:'").append(value).append("'}").toString());
                first = false;
            } else
            {
                buffer.append((new StringBuilder()).append(",{name:'").append(name).append("',value:'").append(value).append("'}").toString());
            }
        }

        buffer.append("];\n");
        if(getSelectStyleClass() != null)
            buffer.append("selectStyle = \"").append(getSelectStyleClass()).append("\";\n");
        buffer.append("isChecked = ").append(isChecked()).append(";\n");
        buffer.append("groupid = \"").append(getGroupId()).append("\";\n");
        if(getRowEvent() != null)
            buffer.append("rowEvent = \"").append(getRowEvent()).append("\";\n");
        if(getOnSelectFunc() != null)
            buffer.append("onSelectFunc = \"").append(getOnSelectFunc()).append("\";\n");
        if(getOnUnSelectFunc() != null)
            buffer.append("onUnSelectFunc = \"").append(getOnUnSelectFunc()).append("\";\n");
        if(getBeforeSelectFunc() != null)
            buffer.append("beforeSelectFunc = \"").append(getBeforeSelectFunc()).append("\";\n");
        if(getBeforeUnSelectFunc() != null)
            buffer.append("beforeUnSelectFunc = \"").append(getBeforeUnSelectFunc()).append("\";\n");
        if(getAfterSelectFunc() != null)
            buffer.append("afterSelectFunc = \"").append(getAfterSelectFunc()).append("\";\n");
        if(getAfterUnSelectFunc() != null)
            buffer.append("afterUnSelectFunc = \"").append(getAfterUnSelectFunc()).append("\";\n");
        if(_showCheckBox)
            buffer.append("showCheckBox = true;\n");
        else
            buffer.append("showCheckBox = false;\n");
        if(getTagName() != null)
            buffer.append("tagName = \"").append(getTagName()).append("\";\n");
        buffer.append("init();}\n</script>");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }

    public String getShowCheckBox()
    {
        return showCheckBox;
    }

    public void setShowCheckBox(String showCheckBox)
    {
        this.showCheckBox = showCheckBox;
    }
}
