// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   VerifyCode.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class VerifyCode extends BaseModifiable
{

    public VerifyCode()
    {
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder sb = new StringBuilder();
        String name = getName();
        sb.append("<div id='").append(name).append("_container'></div>");
        sb.append("<script> var tmpObj=new VerifyCode({");
        sb.append("name:\"").append(name).append("\",");
        sb.append("length:\"").append(getAttribute("length")).append("\",");
        sb.append("type:\"").append(getAttribute("type")).append("\",");
        sb.append("imageHeight:\"").append(getAttribute("imageHeight")).append("\",");
        sb.append("hasInput:").append(getAttribute("hasInput"));
        if(getAttribute("validateAttr") != null)
            sb.append(",").append("validateAttr:\"").append(getAttribute("validateAttr")).append("\"");
        if(getAttribute("style") != null)
            sb.append(",").append("style:\"").append(getAttribute("style")).append("\"");
        if(getAttribute("styleClass") != null)
            sb.append(",").append("className:\"").append(getAttribute("styleClass")).append("\"");
        sb.append("});tmpObj.init();</script>");
        return sb.toString();
    }
}
