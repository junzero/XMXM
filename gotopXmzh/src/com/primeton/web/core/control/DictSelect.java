// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictSelect.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictManager;
import com.eos.web.IParameterSupport;
import com.eos.web.taglib.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable, Option

public class DictSelect extends BaseModifiable
    implements IParameterSupport
{

    private ArrayList options;

    public DictSelect()
    {
        options = null;
        setType("dictselect");
    }

    public void addParameter(String value, Object label)
    {
        if(options == null)
            options = new ArrayList();
        Option opt = new Option(value, label.toString());
        options.add(opt);
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
        useAttribute("locale");
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
        String nullLabel = getAttribute("nullLabel");
        if(nullLabel != null)
        {
            if(nullLabel == null)
                nullLabel = "";
            prepareOption(buf, "", nullLabel, filter, null, seperator);
        }
        if(isDict())
        {
            List<DictEntry> dicts = null;
            String filterOp = getAttribute("filterOp");
            String filterField = getAttribute("filterField");
            String filterStr = getAttribute("filterStr");
            String roles = getAttribute("roles");
            String locale = getAttribute("locale");
            useAttribute("locale");
            if(filterOp != null && filterStr != null)
                dicts = DictManager.getDictEntries(getDictTypeId(), filterOp, filterField, filterStr,roles);
            else
                dicts = DictManager.getDictEntries(getDictTypeId(),roles);
            if(dicts != null)
            {
                for(int i = 0; i < dicts.size(); i++)
                {
                    DictEntry dict = (DictEntry)dicts.get(i);
                    if(outputJson)
                    {
                        if(i != 0)
                            json.append(",");
                        json.append((new StringBuilder()).append(dict.getDictId()).append(":").append("\"").append(DictManager.getDictName(dicts, dict.getDictId())).append("\"").toString());
                    }
                    prepareOption(buf, dict.getDictId(), DictManager.getDictName(dicts, dict.getDictId()), filter, actValue, seperator);
                }

                if(outputJson)
                    json.append("};");
            }
        } else
        {
            if(options == null)
                return;
            for(int i = 0; i < options.size(); i++)
            {
                Option opt = (Option)options.get(i);
                opt.prepareOption(buf, filter, getActualValue().toString(), multiple, seperator);
            }

        }
    }

    public void prepareOption(StringBuilder buf, String key, String label, boolean filter, String actValue, String seperator)
    {
        buf.append("<option value=\"");
        buf.append(key);
        buf.append("\"");
        if(actValue != null)
            if(actValue.indexOf(seperator) == -1)
            {
                if(key.equals(actValue))
                    buf.append(" selected");
            } else
            {
                List actValues = StringUtil.splitString(actValue, seperator);
                boolean found = false;
                int i = 0;
                do
                {
                    if(i >= actValues.size())
                        break;
                    if(key.equals(actValues.get(i)))
                    {
                        found = true;
                        break;
                    }
                    i++;
                } while(true);
                if(found)
                    buf.append(" selected");
            }
        buf.append(">");
        String labelStr = null;
        if(filter)
            labelStr = StringUtil.htmlFilter(label);
        else
            labelStr = label;
        buf.append(labelStr);
        buf.append("</option>");
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
