// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   RichText.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class RichText extends BaseModifiable
{

    public RichText()
    {
        super.setType("richtext");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buffer = new StringBuilder();
        String id = getAttribute("id");
        if(id == null)
            id = (new StringBuilder()).append("_richtext").append(hashCode()).toString();
        Object value = getValue();
        if(value == null)
            value = "";
        String name = getName();
        String width = getAttribute("width");
        String height = getAttribute("height");
        buffer.append("<div id=\"").append(id).append("_container\" style=\"width:").append(width).append(";height:").append(height).append("\"><textarea name=\"").append(name).append("\" id=\"").append(id).append("_textarea\">").append(value).append("</textarea></div>");
        buffer.append("<script>\n");
        buffer.append("var richtext = new RichTextEditor(\"").append(id).append("\");\n");
        if(width != null)
            buffer.append("richtext.width = \"").append(width).append("\";\n");
        if(height != null)
            buffer.append("richtext.height = \"").append(height).append("\";\n");
        String toolBar = getAttribute("toolBar");
        if(toolBar != null)
            buffer.append("richtext.toolbarSet = \"").append(toolBar).append("\";\n");
        buffer.append("richtext.init();\n");
        buffer.append("</script>\n");
        return buffer.toString();
    }
}
