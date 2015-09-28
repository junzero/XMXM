// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ComboSelect.java

package com.primeton.web.core.control;

import com.eos.web.taglib.util.XpathUtil;
import com.gotop.util.security.ForUtil;

import java.util.*;

// Referenced classes of package com.primeton.web.core.control:
//            BaseModifiable

public class ComboSelect extends BaseModifiable
{

    private static final long serialVersionUID = -1L;
    private Object rootObj;
    private HashMap parameterMap;
    private Map params;
    private String varName;

    public ComboSelect()
    {
        rootObj = null;
        parameterMap = null;
        params = new HashMap();
        setType("comboSelect");
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
            setId((new StringBuilder()).append("comboselect_").append(ForUtil.rRandom()).toString());
        varName = (new StringBuilder()).append("eos_").append(getId()).toString();
        String _name = getName();
        if(_name == null || _name.length() < 1)
            _name = getId();
        buffer.append("<div id=\"").append(getId()).append("_container\"  class=\"eos-ic-container\" >");
        buffer.append("<input class=\"eos-combo-select-editor-text\" type=\"text\" id=\"").append(getId()).append("_input\"");
        if(getAttribute("validateAttr") != null)
            buffer.append(" validateAttr=\"").append(getAttribute("validateAttr")).append("\"");
        buffer.append(" />");
        buffer.append("<img id='").append(getId()).append("_button' class='eos-ic-button'  />");
        buffer.append("<input type=\"hidden\" id=\"").append(getId()).append("_hidden\" name=\"").append(_name).append("\" />");
        buffer.append("</div>");
        if(getAttribute("xpath") != null && getAttribute("queryAction") == null)
        {
            String xml = XpathUtil.getStringByList(getRootObj(), getAttribute("xpath"));
            if(xml != null && xml.length() > 0 && !"null".equalsIgnoreCase(xml))
            {
                buffer.append("<script type=\"text/xml\" id=\"").append(getId()).append("_xml\" >\n");
                buffer.append(xml).append("\n");
                buffer.append("</script>");
            }
        }
        buffer.append("<script type=\"text/javascript\" >\n");
        buffer.append("(function(){\n");
        buffer.append("var ").append(varName).append(" =new ComboSelect('").append(getId()).append("'); \n");
        if(getAttribute("queryAction") != null)
            buffer.append(varName).append(".queryAction = \"").append(getAttribute("queryAction")).append("\"; \n");
        if(getAttribute("xpath") != null)
            buffer.append(varName).append(".xpath = \"").append(getAttribute("xpath")).append("\"; \n");
        if(getAttribute("valueField") != null)
            buffer.append(varName).append(".valueField = \"").append(getAttribute("valueField")).append("\"; \n");
        if(getAttribute("textField") != null)
            buffer.append(varName).append(".textField = \"").append(getAttribute("textField")).append("\"; \n");
        if(getAttribute("width") != null)
            buffer.append(varName).append(".width = \"").append(getAttribute("width")).append("\"; \n");
        if(getAttribute("height") != null)
            buffer.append(varName).append(".height = \"").append(getAttribute("height")).append("\"; \n");
        if(getAttribute("optionsWidth") != null)
            buffer.append(varName).append(".optionsWidth = \"").append(getAttribute("optionsWidth")).append("\"; \n");
        if(getAttribute("optionsHeight") != null)
            buffer.append(varName).append(".optionsHeight = \"").append(getAttribute("optionsHeight")).append("\"; \n");
        if(getAttribute("size") != null)
            buffer.append(varName).append(".size = \"").append(getAttribute("size")).append("\"; \n");
        if(getAttribute("onChangeFunc") != null)
            buffer.append(varName).append(".onChangeFunc = \"").append(getAttribute("onChangeFunc")).append("\"; \n");
        if(getAttribute("linkId") != null)
            buffer.append(varName).append(".linkId = \"").append(getAttribute("linkId")).append("\"; \n");
        if(getAttribute("linkField") != null)
            buffer.append(varName).append(".linkField = \"").append(getAttribute("linkField")).append("\"; \n");
        if(getAttribute("filterField") != null)
            buffer.append(varName).append(".filterField = \"").append(getAttribute("filterField")).append("\"; \n");
        if(getAttribute("filterType") != null)
            buffer.append(varName).append(".filterType = \"").append(getAttribute("filterType")).append("\"; \n");
        if(getAttribute("property") != null)
            buffer.append(varName).append(".property = \"").append(getAttribute("property")).append("\"; \n");
        if(getAttribute("value") != null)
            buffer.append(varName).append(".value = \"").append(getAttribute("value")).append("\"; \n");
        if(getAttribute("validateFunc") != null)
            buffer.append(varName).append(".validateFunc = \"").append(getAttribute("validateFunc")).append("\"; \n");
        if(getAttribute("nullText") != null)
            buffer.append(varName).append(".nullText = \"").append(getAttribute("nullText")).append("\"; \n");
        if(getAttribute("submitXpath") != null)
            buffer.append(varName).append(".submitXpath = \"").append(getAttribute("submitXpath")).append("\"; \n");
        String filterModel = getAttribute("filterModel");
        if(getAttribute("initParamFunc") != null){
        	buffer.append(varName).append(".initParamFunc = \"").append(getAttribute("initParamFunc")).append("\"; \n");
        }else{
        	if(filterModel!=null){
        		buffer.append(varName).append(".initParamFunc = \"").append("paramFunc").append("\"; \n");
        	}
        }
        buffer.append(varName).append(".readOnly = ").append(getAttribute("readonly")).append("; \n");
        buffer.append(varName).append(".allowInput = ").append(getAttribute("allowInput")).append("; \n");
        buffer.append(varName).append(".allowFilter = ").append(getAttribute("allowFilter")).append("; \n");
        buffer.append(varName).append(".disabled = ").append(getAttribute("disabled")).append("; \n");
        if(filterModel!=null){
        	buffer.append(varName).append(".filterModel = ").append(getAttribute("filterModel")).append("; \n");
        }
        if(parameterMap != null)
        {
            Iterator itr = parameterMap.keySet().iterator();
            do
            {
                if(!itr.hasNext())
                    break;
                String key = String.valueOf(itr.next());
                String value = String.valueOf(parameterMap.get(key));
                if(value != null)
                    buffer.append(varName).append(".addParam(\"").append(key).append("\",\"").append(value).append("\");\n");
            } while(true);
        }
        buffer.append(varName).append(".onPageLoad();\n");
        buffer.append("})()\n");
        buffer.append("</script>");
        return buffer.toString();
    }

    public Object getRootObj()
    {
        return rootObj;
    }

    public void setRootObj(Object rootObj)
    {
        this.rootObj = rootObj;
    }

    public HashMap getParameterMap()
    {
        return parameterMap;
    }

    public void setParameterMap(HashMap parameterMap)
    {
        this.parameterMap = parameterMap;
    }
}
