// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FlowAction.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class FlowAction extends BaseModifiable
{

    public FlowAction()
    {
    }

    public String toHtml()
        throws Exception
    {
        String type = getAttribute("type");
        useAttribute("type");
        if(type.equalsIgnoreCase("hidden"))
            return createHidden();
        if(type.equalsIgnoreCase("button"))
            return createButton();
        else
            return createLink();
    }

    private String createHidden()
    {
        return (new StringBuilder()).append("<input type=\"hidden\" name=\"_eosFlowAction\" value=\"").append(getValue()).append("\">").toString();
    }

    private String createLink()
    {
        StringBuilder buf = new StringBuilder("<a href=\"#\" ");
        prepareId(buf);
        String text = getAttribute("text");
        useAttribute("text");
        String onClick = getAttribute("onclick");
        if(onClick != null)
        {
            useAttribute("onclick");
            onClick = (new StringBuilder()).append("(").append(onClick).append(")!==false").toString();
        } else
        {
            onClick = "true";
        }
        String sp = "'";
        if(onClick.indexOf(sp) != -1)
            sp = "\"";
        onClick = (new StringBuilder()).append("if(").append(onClick).append(")flowSubmit(this)").toString();
        buf.append(" value=\"").append(text).append("\" onclick=").append(sp).append(onClick).append(sp).append(" flowAction=\"").append(getActualValue() == null ? getValue() : getActualValue()).append("\"");
        prepareAttributes(buf);
        buf.append(">").append(text).append("</a>");
        return buf.toString();
    }

    private String createButton()
    {
        StringBuilder buf = new StringBuilder("<input type=\"button\"");
        prepareId(buf);
        String text = getAttribute("text");
        useAttribute("text");
        String onClick = getAttribute("onclick");
        if(onClick != null)
        {
            useAttribute("onclick");
            onClick = (new StringBuilder()).append("(").append(onClick).append(")!==false").toString();
        } else
        {
            onClick = "true";
        }
        String sp = "'";
        if(onClick.indexOf(sp) != -1)
            sp = "\"";
        onClick = (new StringBuilder()).append("if(").append(onClick).append(")flowSubmit(this)").toString();
        buf.append(" value=\"").append(text).append("\" onclick=").append(sp).append(onClick).append(sp).append(" flowAction=\"").append(getActualValue() == null ? getValue() : getActualValue()).append("\"");
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
