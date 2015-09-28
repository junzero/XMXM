// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   LabelTag.java

package com.eos.web.taglib.html;

import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.primeton.ext.data.vmodel.Field;
import com.primeton.ext.data.vmodel.ViewModelHelper;
import javax.servlet.jsp.JspException;

public class LabelTag extends BaseTagSupport
{

    private String modelField;

    public LabelTag()
    {
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        modelField = XpathUtil.getStringByXpathSupport(getRootObj(), modelField, "modelField");
    }

    public void release()
    {
        modelField = null;
        super.release();
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Field field;
        try
        {
            field = ViewModelHelper.getField(modelField);
        }
        catch(Exception e)
        {
            throw new JspException((new StringBuilder()).append("modelField ").append(modelField).append(" is not found!").toString());
        }
        String label = field.getLabel();
        if(label != null)
            ResponseUtil.write(pageContext, label);
        return 0;
    }

    public String getModelField()
    {
        return modelField;
    }

    public void setModelField(String modelField)
    {
        this.modelField = modelField;
    }
}
