// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictTreeSelect.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictManager;
import java.util.List;

// Referenced classes of package com.primeton.web.core.control:
//            DictSelect

public class DictTreeSelect extends DictSelect
{

    public DictTreeSelect()
    {
        setType("dicttreeselect");
    }

    public String toHtml()
        throws Exception
    {
        StringBuilder buf = new StringBuilder("<select");
        prepareId(buf);
        prepareName(buf);
        prepareTitle(buf);
        Object actValue = getActualValue();
        if(actValue != null)
            prepareAttribute(buf, "value", actValue.toString());
        prepareValidations(buf);
        boolean filter = false;
        if(getAttribute("filter") != null)
        {
            filter = Boolean.parseBoolean(getAttribute("filter"));
            useAttribute("filter");
        }
        String seperator = getAttribute("seperator");
        if(seperator == null)
            seperator = ",";
        else
            useAttribute("seperator");
        useAttribute("nullOption");
        useAttribute("nullLabel");
        useAttribute("filterOp");
        useAttribute("filterField");
        useAttribute("filterStr");
        useAttribute("level");
        prepareAttributes(buf);
        buf.append(">");
        boolean multiple = false;
        if(getAttribute("multiple") != null)
            multiple = Boolean.parseBoolean(getAttribute("multiple"));
        prepareOptions(buf, actValue != null ? actValue.toString() : null, filter, multiple, seperator);
        buf.append("</select>");
        return buf.toString();
    }

    public void prepareOptions(StringBuilder buf, String actValue, boolean filter, boolean multiple, String seperator)
    {
        boolean nullOption = false;
        String nullOptionStr = getAttribute("nullOption");
        nullOption = Boolean.parseBoolean(nullOptionStr);
        if(nullOption)
        {
            String nullLabel = getAttribute("nullLabel");
            if(nullLabel == null)
                nullLabel = "";
            prepareOption(buf, "", nullLabel, filter, null, seperator);
        }
        List dicts = null;
        String filterOp = getAttribute("filterOp");
        String filterField = getAttribute("filterField");
        String filterStr = getAttribute("filterStr");
        String levelStr = getAttribute("level");
        String roles = getAttribute("roles");
        int level = -1;
        if(levelStr != null)
            level = Integer.parseInt(levelStr);
        if(filterOp != null && filterStr != null)
            dicts = DictManager.getDictEntries(getDictTypeId(), filterOp, filterField, filterStr, level,roles);
        else
            dicts = DictManager.getDictEntries(getDictTypeId(), level,roles);
        if(dicts != null)
        {
            for(int i = 0; i < dicts.size(); i++)
            {
                DictEntry dict = (DictEntry)dicts.get(i);
                int rank = dict.getLevel();
                String outName = null;
                if(rank > 1)
                {
                    StringBuilder sbuf = new StringBuilder();
                    for (int j = 3; j <= rank; j++) {
                        sbuf.append("│&nbsp;&nbsp;");
                    }
                    sbuf.append("├─ ");
                    sbuf.append(dict.getDictName());
                    outName = sbuf.toString();
                } else
                {
                    outName = dict.getDictName();
                }
                prepareOption(buf, dict.getDictId(), outName, filter, actValue, seperator);
            }

        }
    }
    public void prepareTitle(StringBuilder buf)
    {
    	String title = this.getAttribute("title");
    	String dtn = DictManager.getDictTypeName(getDictTypeId());
        if(title == null){
        	prepareAttribute(buf, "title", dtn);
        }
    }
}
