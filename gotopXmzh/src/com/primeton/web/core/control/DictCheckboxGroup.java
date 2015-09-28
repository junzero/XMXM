// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictCheckboxGroup.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictManager;
import com.eos.web.taglib.util.StringUtil;
import java.util.List;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class DictCheckboxGroup extends BaseModifiable
{

    public DictCheckboxGroup()
    {
        setType("dictcheckboxgroup");
    }

    public String toHtml()
        throws Exception
    {
        if(getAttribute("datacellEditor") != null)
        {
            outputJson = true;
            json = new StringBuilder("{");
            useAttribute("datacellEditor");
        }
        StringBuilder buf = new StringBuilder("<span");
        String id = getId();
        if(id == null)
            id = (new StringBuilder()).append("ckb").append(hashCode()).toString();
        String containerId = (new StringBuilder()).append(id).append("_container").toString();
        String jsObj = "eos_checkbox";
        prepareAttribute(buf, "id", containerId);
        String classStyle = getAttribute("class");
        if(classStyle != null)
        {
            useAttribute("class");
            prepareAttribute(buf, "class", classStyle);
        }
        String style = getAttribute("style");
        if(style != null)
        {
            useAttribute("style");
            prepareAttribute(buf, "style", style);
        }
        buf.append(">");
        Object actValue = getActualValue();
        prepareCheckboxGroup(buf, actValue != null ? actValue.toString() : null, id);
        buf.append("</span><script language=\"Javascript\"> var ");
        buf.append(jsObj);
        String seperator = getAttribute("seperator");
        if(seperator != null)
            useAttribute("seperator");
        else
            seperator = ",";
        buf.append("=new MultiBox(\"");
        buf.append(id);
        buf.append("\");");
        if(!seperator.equals(","))
        {
            buf.append(jsObj);
            buf.append(".splitChar=\"");
            buf.append(seperator);
            buf.append("\";");
        }
        if(outputJson)
        {
            buf.append(jsObj);
            buf.append(".jsonObj=");
            buf.append(json.toString());
        }
        buf.append("</script>");
        return buf.toString();
    }

    protected String prepareCheckboxGroup(StringBuilder buf, String actValue, String id)
    {
        List<DictEntry> dicts = null;
        String filterOp = getAttribute("filterOp");
        String filterField = getAttribute("filterField");
        String filterStr = getAttribute("filterStr");
        String roles = getAttribute("roles");
        String locale = getAttribute("locale");
        useAttribute("locale");
        useAttribute("filterOp");
        useAttribute("filterField");
        useAttribute("filterStr");
        if(filterOp != null && filterStr != null)
            dicts = DictManager.getDictEntries(getDictTypeId(), filterOp, filterField, filterStr,roles);
        else
            dicts = DictManager.getDictEntries(getDictTypeId(),roles);
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
        String perrow = getAttribute("perrow");
        int _perrow = 0;
        if(perrow != null)
        {
            useAttribute("perrow");
            _perrow = Integer.parseInt(perrow);
        }
        int j = 0;
        int i = 0;
        buf.append("<table class=\"eos_dict_class\">");
        if(_perrow == 0)
            buf.append("<tr>");
        do
        {
            if(_perrow != 0)
            {
                buf.append("<tr>");
                for(j = 0; j < _perrow; j++)
                {
                    buf.append("<td nowrap>");
                    if(i < dicts.size())
                    {
                        DictEntry dict = (DictEntry)dicts.get(i);
                        if(outputJson)
                        {
                            if(i != 0)
                                json.append(",");
                            json.append((new StringBuilder()).append(dict.getDictId()).append(":").append("\"").append(DictManager.getDictName(dicts, dict.getDictId())).append("\"").toString());
                        }
                        prepareCheckbox(buf, dict.getDictId(), DictManager.getDictName(dicts, dict.getDictId()), filter, actValue, seperator, (new StringBuilder()).append(id).append("checkbox").append(i).toString());
                    }
                    buf.append("</td>");
                    i++;
                }

                buf.append("</tr>");
            } else
            {
                buf.append("<td nowrap>");
                DictEntry dict = (DictEntry)dicts.get(i);
                if(outputJson)
                {
                    if(i != 0)
                        json.append(",");
                    json.append((new StringBuilder()).append(dict.getDictId()).append(":").append("\"").append(DictManager.getDictName(dicts, dict.getDictId())).append("\"").toString());
                }
                prepareCheckbox(buf, dict.getDictId(), DictManager.getDictName(dicts, dict.getDictId()), filter, actValue, seperator, (new StringBuilder()).append(id).append("checkbox").append(i).toString());
                i++;
                buf.append("</td>");
            }
            if(i >= dicts.size())
                break;
            j = 0;
        } while(true);
        if(_perrow == 0)
            buf.append("</tr>");
        buf.append("</table>");
        if(outputJson)
            json.append("};");
        return buf.toString();
    }

    protected void prepareCheckbox(StringBuilder buf, String key, String label, boolean filter, String actValue, String seperator, String id)
    {
        buf.append("<input type=\"checkbox\" id='").append(id).append("'");
        prepareName(buf);
        prepareAttribute(buf, "value", key);
        prepareAttributes(buf);
        if(actValue != null)
            if(actValue.indexOf(seperator) == -1)
            {
                if(key.equals(actValue))
                    buf.append(" checked");
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
                    buf.append(" checked");
            }
        buf.append(">");
        String labelStr = null;
        if(filter)
            labelStr = StringUtil.htmlFilter(label);
        else
            labelStr = label;
        labelStr = label;
        buf.append("<label for='").append(id).append("'>").append(labelStr).append("</label>");
    }
}
