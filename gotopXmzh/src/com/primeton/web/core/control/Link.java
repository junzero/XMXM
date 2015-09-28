// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   Link.java

package com.primeton.web.core.control;

import com.eos.web.IParameterSupport;
import java.util.*;

// Referenced classes of package com.primeton.web.core.control:
//            WebComponent

public class Link extends WebComponent
    implements IParameterSupport
{

    Map parameters;

    public Link()
    {
        parameters = null;
        setType("link");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<a");
        prepareId(buf);
        String hrefStr = getAttribute("href");
        StringBuilder tmpHrefBuf = new StringBuilder();
        StringBuilder paramBuf = new StringBuilder();
        tmpHrefBuf.append(hrefStr);
        if(hrefStr != null)
        {
            useAttribute("href");
            buf.append(" href=\"");
            if(parameters != null)
            {
                prepareParameters(paramBuf);
                int pos = hrefStr.indexOf("?");
                if(pos == -1)
                {
                    if(!paramBuf.toString().equals(""))
                        tmpHrefBuf.append("?");
                } else
                if(pos < hrefStr.length() - 1)
                    tmpHrefBuf.append("&");
                tmpHrefBuf.append(paramBuf);
            }
            if(getAttribute("confirmMsg") != null)
            {
                buf.append("javascript:linkConfirm('");
                buf.append(getAttribute("confirmMsg"));
                buf.append((new StringBuilder()).append("','").append(tmpHrefBuf).append("','").toString());
                buf.append(getAttribute("target") != null ? getAttribute("target") : "_self");
                buf.append("');");
            } else
            {
                buf.append(tmpHrefBuf);
            }
            buf.append("\"");
        }
        prepareAttributes(buf);
        buf.append(">");
        buf.append(getValue());
        buf.append("</a>");
        tmpHrefBuf = null;
        paramBuf = null;
        return buf.toString();
    }

    public void addParameter(String name, Object value)
    {
        if(parameters == null)
            parameters = new Hashtable(5);
        if(value != null)
            parameters.put(name, value);
        else
            parameters.put(name, "");
    }

    public Map getParameters()
    {
        return parameters;
    }

    public void setParameters(Map params)
    {
        parameters = params;
    }

    protected void prepareParameters(StringBuilder buf)
    {
        boolean isFirst = true;
        Object value;
        for(Iterator it = getParameters().keySet().iterator(); it.hasNext(); buf.append(value))
        {
            String key = (String)it.next();
            value = parameters.get(key);
            if(!isFirst)
                buf.append("&");
            else
                isFirst = false;
            buf.append(key);
            buf.append("=");
        }

    }
}
