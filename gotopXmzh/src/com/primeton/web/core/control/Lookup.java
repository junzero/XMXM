// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Lookup.java

package com.primeton.web.core.control;

import java.util.*;

import com.gotop.util.security.ForUtil;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class Lookup extends BaseModifiable
{

    private static final long serialVersionUID = 0xab1a44a5edfdeafeL;
    private Map params;

    public Lookup()
    {
        params = new HashMap();
        setType("lookup");
    }

    public Map getParams()
    {
        return params;
    }

    public void setParams(Map params)
    {
        this.params = params;
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buffer = new StringBuilder();
        if(getId() == null)
            setId((new StringBuilder()).append("lookup_").append(ForUtil.rRandom()).toString());
        buffer.append("<div  class=\"eos-ic-container\" id=\"").append(getId()).append("_container\" >\n");
        buffer.append("<input class=\"eos-lookup-editor-text\" id=\"").append(getId()).append("_input\" type=\"text\"");
        if(getAttribute("displayValue") != null)
            buffer.append(" value=\"").append(getAttribute("displayValue")).append("\"");
        if(getAttribute("displayName") != null)
            buffer.append(" name=\"").append(getAttribute("displayName")).append("\"");
        if(getAttribute("style") != null)
            buffer.append(" style=\"").append(getAttribute("style")).append("\"");
        if(getAttribute("class") != null)
            buffer.append(" class=\"").append(getAttribute("class")).append("\"");
        if(getAttribute("accesskey") != null)
            buffer.append(" accesskey=\"").append(getAttribute("accesskey")).append("\"");
        if(getAttribute("size") != null)
            buffer.append(" size=\"").append(getAttribute("size")).append("\"");
        if(getAttribute("tabindex") != null)
            buffer.append(" tabindex=\"").append(getAttribute("tabindex")).append("\"");
        if(getAttribute("onclick") != null)
            buffer.append(" onclick=\"").append(getAttribute("onclick")).append("\"");
        if(getAttribute("onblur") != null)
            buffer.append(" onblur=\"").append(getAttribute("onblur")).append("\"");
        if(getAttribute("onchange") != null)
            buffer.append(" onchange=\"").append(getAttribute("onchange")).append("\"");
        if(getAttribute("ondblclick") != null)
            buffer.append(" ondblclick=\"").append(getAttribute("ondblclick")).append("\"");
        if(getAttribute("onfocus") != null)
            buffer.append(" onfocus=\"").append(getAttribute("onfocus")).append("\"");
        if(getAttribute("onkeydown") != null)
            buffer.append(" onkeydown=\"").append(getAttribute("onkeydown")).append("\"");
        if(getAttribute("onkeypress") != null)
            buffer.append(" onkeypress=\"").append(getAttribute("onkeypress")).append("\"");
        if(getAttribute("onkeyup") != null)
            buffer.append(" onkeyup=\"").append(getAttribute("onkeyup")).append("\"");
        if(getAttribute("onmousedown") != null)
            buffer.append(" onmousedown=\"").append(getAttribute("onmousedown")).append("\"");
        if(getAttribute("onmousemove") != null)
            buffer.append(" onmousemove=\"").append(getAttribute("onmousemove")).append("\"");
        if(getAttribute("onmouseout") != null)
            buffer.append(" onmouseout=\"").append(getAttribute("onmouseout")).append("\"");
        if(getAttribute("onmouseover") != null)
            buffer.append(" onmouseover=\"").append(getAttribute("onmouseover")).append("\"");
        if(getAttribute("onmouseup") != null)
            buffer.append(" onmouseup=\"").append(getAttribute("onmouseup")).append("\"");
        if(getAttribute("onselect") != null)
            buffer.append(" onselect=\"").append(getAttribute("onselect")).append("\"");
        if(getAttribute("validateAttr") != null)
            buffer.append(" validateAttr=\"").append(getAttribute("validateAttr")).append("\"");
        if(getAttribute("alt") != null)
            buffer.append(" alt=\"").append(getAttribute("alt")).append("\"");
        if(getAttribute("title") != null)
            buffer.append(" title=\"").append(getAttribute("title")).append("\"");
        if(getAttribute("maxlength") != null)
            buffer.append(" maxlength=\"").append(getAttribute("maxlength")).append("\"");
        buffer.append(">");
        buffer.append("<img id='").append(getId()).append("_button' class=\"eos-ic-button\" />");
        Object value = getValue();
        buffer.append("<input id=\"").append(getId()).append("_hidden\" type=\"hidden\"");
        if(getName() != null)
            buffer.append(" name=\"").append(getName()).append("\"");
        if(value != null)
            buffer.append(" value=\"").append(value).append("\"");
        buffer.append("></div>\n");
        buffer.append("\t<script>\n");
        buffer.append("\t\tvar lookup = new LookUp(\"").append(getId()).append("\");\n");
        buffer.append("\t\tlookup.lookupUrl = \"").append(getAttribute("lookupUrl")).append("\";\n");
        String key;
        for(Iterator i$ = params.keySet().iterator(); i$.hasNext(); buffer.append("\t\tlookup.addParam(\"").append(key).append("\",'").append(params.get(key)).append("');\n"))
            key = (String)i$.next();

        if(getAttribute("left") != null)
            buffer.append("\t\tlookup.left = \"").append(getAttribute("left")).append("\";\n");
        if(getAttribute("dialogTitle") != null)
            buffer.append("\t\tlookup.dialogTitle = \"").append(getAttribute("dialogTitle")).append("\";\n");
        if(getAttribute("top") != null)
            buffer.append("\t\tlookup.top = \"").append(getAttribute("top")).append("\";\n");
        if(getAttribute("useIeModel") != null)
            buffer.append("\t\tlookup.useIeModel = ").append(getAttribute("useIeModel")).append(";\n");
        if(getAttribute("width") != null)
            buffer.append("\t\tlookup.width = \"").append(getAttribute("width")).append("\";\n");
        if(getAttribute("height") != null)
            buffer.append("\t\tlookup.height = \"").append(getAttribute("height")).append("\";\n");
        if(getAttribute("onReturnFunc") != null)
            buffer.append("\t\tlookup.onReturnFunc = \"").append(getAttribute("onReturnFunc")).append("\";\n");
        if(getAttribute("disabled") != null && getAttribute("disabled").equals("true"))
            buffer.append("\t\tlookup.disabled = true;\n");
        if(getAttribute("lookupWidth") != null)
            buffer.append("\t\tlookup.lookupWidth = \"").append(getAttribute("lookupWidth")).append("\";\n");
        String readOnly = getAttribute("readOnly");
        if(readOnly != null)
            buffer.append("lookup.readOnly = ").append(readOnly).append(";\n");
        String allowInput = getAttribute("readonly");
        if(allowInput != null)
            buffer.append("lookup.allowInput = !").append(allowInput).append(";\n");
        buffer.append("\t\tlookup.init();\n");
        buffer.append("\t</script>\n");
        return buffer.toString();
    }
}
