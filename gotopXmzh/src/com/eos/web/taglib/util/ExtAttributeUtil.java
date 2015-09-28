// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ExtAttributeUtil.java

package com.eos.web.taglib.util;

import java.util.*;

// Referenced classes of package com.eos.web.taglib.util:
//            StringUtil

public class ExtAttributeUtil
{

    public ExtAttributeUtil()
    {
    }

    public static Map getAttributes(String extAttr)
    {
        List list = StringUtil.splitString(extAttr, ";");
        Properties retmap = new Properties();
        for(int i = 0; i < list.size(); i++)
        {
            String str = (String)list.get(i);
            int pos = str.indexOf("=");
            if(pos != -1)
            {
                String name = str.substring(0, pos);
                String value = str.substring(pos + 1, str.length());
                retmap.put(name.trim(), value.trim());
            }
        }

        return retmap;
    }
}
