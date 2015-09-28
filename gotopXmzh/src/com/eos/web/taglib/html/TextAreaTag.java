// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   TextAreaTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.util.XpathUtil;
import com.primeton.web.core.control.ComponentFactory;
import com.primeton.web.core.control.WebComponent;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class TextAreaTag extends BaseInputTag
{

    private String cols;
    private String rows;

    public TextAreaTag()
    {
        cols = null;
        rows = null;
    }

    public String getCols()
    {
        return cols;
    }

    public void setCols(String cols)
    {
        this.cols = cols;
    }

    public void setCols(int cols)
    {
        this.cols = (new StringBuilder()).append(cols).append("").toString();
    }

    public String getRows()
    {
        return rows;
    }

    public void setRows(String rows)
    {
        this.rows = rows;
    }

    public void setRows(int rows)
    {
        this.rows = (new StringBuilder()).append(rows).append("").toString();
    }

    public void prepareAttributes()
    {
        super.prepareAttributes();
        if(getCols() != null)
            component.setAttribute("cols", getCols());
        if(getRows() != null)
            component.setAttribute("rows", getRows());
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            component = ComponentFactory.createWebComponent("textarea", getModelField());
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        cols = XpathUtil.getStringByXpathSupport(getRootObj(), cols);
        rows = XpathUtil.getStringByXpathSupport(getRootObj(), rows);
    }

    public void release()
    {
        super.release();
        cols = null;
        rows = null;
    }
}
