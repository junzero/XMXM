// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   File.java

package com.primeton.web.core.control;


// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class File extends BaseModifiable
{

    public File()
    {
        setType("file");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<input type=\"file\"");
        prepareId(buf);
        prepareName(buf);
        prepareAttributes(buf);
        buf.append(">");
        return buf.toString();
    }
}
