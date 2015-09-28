// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DictRadioGroup.java

package com.primeton.web.core.control;

import com.eos.server.dict.DictEntry;
import com.eos.server.dict.DictManager;
import com.eos.web.taglib.util.StringUtil;
import com.gotop.util.security.ForUtil;

import java.util.List;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class DictRadioGroup extends BaseModifiable
{

    public DictRadioGroup()
    {
        setType("dictradiogroup");
    }

    public String toHtml()
        throws Exception
    {
        if(getAttribute("datacellEditor") != null)
        {
            outputJson = true;
            json = new StringBuilder("{");
            useAttribute("datacellEditor");
            if(getName() == null)
                setName((new StringBuilder()).append("dcEditor_").append(ForUtil.rRandom()).toString());
        }
        StringBuilder buf = new StringBuilder("<span");
        String id = getId();
        if(id == null)
            id = (new StringBuilder()).append("ckb").append(hashCode()).toString();
        setId(null);
        String containerId = (new StringBuilder()).append(id).append("_container").toString();
        String jsObj = "eos_radio";
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
        useAttribute("seperator");
        if(filterOp != null && filterStr != null)
            dicts = DictManager.getDictEntries(getDictTypeId(), filterOp, filterField, filterStr,roles);
        else
            dicts = DictManager.getDictEntries(getDictTypeId(),roles);
        String actValue = null;
        if(getActualValue() != null)
            actValue = getActualValue().toString();
        boolean filter = false;
        if(getAttribute("filter") != null)
        {
            filter = Boolean.parseBoolean(getAttribute("filter"));
            useAttribute("filter");
        }
        String perrow = getAttribute("perrow");
        int _perrow = 0;
        if(perrow != null)
        {
            useAttribute("perrow");
            _perrow = Integer.parseInt(perrow);
        }
        int i = 0;
        int j = 0;
        buf.append("<div><table  class=\"eos_dict_class\">");
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
                        prepareRadio(buf, dict.getDictId(), DictManager.getDictName(dicts, dict.getDictId()), filter, actValue, (new StringBuilder()).append(id).append("radio").append(i).toString());
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
                prepareRadio(buf, dict.getDictId(), DictManager.getDictName(dicts, dict.getDictId()), filter, actValue, (new StringBuilder()).append(id).append("radio").append(i).toString());
                i++;
                buf.append("</td>");
            }
            if(i >= dicts.size())
                break;
            j = 0;
        } while(true);
        if(_perrow == 0)
            buf.append("</tr>");
        buf.append("</table></div>");
        if(outputJson)
            json.append("};");
        buf.append("</span><script language=\"Javascript\"> ");
        buf.append((new StringBuilder()).append("var ").append(jsObj).append(" =new DictRadioGroup(\"").toString());
        buf.append(id);
        buf.append("\");");
        if(outputJson)
        {
            buf.append(jsObj).append(".isDcEdit=true;\n");
            buf.append(jsObj);
            buf.append(".jsonObj=");
            buf.append(json.toString());
        }
        buf.append("</script>");
        return buf.toString();
    }

    protected void prepareRadio(StringBuilder buf, String key, String label, boolean filter, String actValue, String id)
    {
        buf.append("<input type=\"radio\" id='").append(id).append("'");
        prepareName(buf);
        prepareAttribute(buf, "value", key);
        prepareAttributes(buf);
        if(actValue != null && key.equals(actValue))
            buf.append(" checked");
        buf.append(">");
        String labelStr = null;
        if(filter)
            labelStr = StringUtil.htmlFilter(label);
        else
            labelStr = label;
        buf.append("<label for='").append(id).append("'>").append(labelStr).append("</label>");
    }
}
