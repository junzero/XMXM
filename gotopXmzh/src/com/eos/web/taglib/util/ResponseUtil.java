// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ResponseUtil.java

package com.eos.web.taglib.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import com.gotop.util.security.ForUtil;

// Referenced classes of package com.eos.web.taglib.util:
//            StringUtil

public class ResponseUtil
{

    public ResponseUtil()
    {
    }

    public static String filter(String value)
    {
        if(value == null)
            return null;
        if(value.indexOf('<') == value.indexOf('>') && value.indexOf('&') == value.indexOf('"') && value.indexOf('\'') == value.indexOf("\\n"))
            return value;
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuilder result = new StringBuilder(content.length + 50);
        for(int i = 0; i < content.length; i++)
            switch(content[i])
            {
            case 60: // '<'
                result.append("&lt;");
                break;

            case 62: // '>'
                result.append("&gt;");
                break;

            case 38: // '&'
                result.append("&amp;");
                break;

            case 34: // '"'
                result.append("&quot;");
                break;

            case 39: // '\''
                result.append("&#39;");
                break;

            default:
                result.append(content[i]);
                break;
            }

        String res = result.toString();
        res = StringUtil.replace(res, "\\n", "\n");
        return res;
    }

    public static void write(PageContext pageContext, String text)
        throws JspException
    {
        JspWriter writer = pageContext.getOut();
        try
        {
        	ForUtil.outObj(writer, text);
        }
        catch(Exception e)
        {
            throw new JspException(e.toString());
        }
    }

    public static void writePrevious(PageContext pageContext, String text)
        throws JspException
    {
        JspWriter writer = pageContext.getOut();
        if(writer instanceof BodyContent)
            writer = ((BodyContent)writer).getEnclosingWriter();
        try
        {
        	ForUtil.outObj(writer, text);
//            writer.print(text);
        }
        catch(Exception e)
        {
            throw new JspException(e.toString());
        }
    }
}
