// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   WebComponent.java

package com.primeton.web.core.control;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package com.primeton.web.core.control:
//            IComponent

public abstract class WebComponent
    implements IComponent, Serializable
{

    private Properties attributes;
    protected Properties extAttributes;
    private List available;
    private String type;
    private String dataType;
    private boolean dict;
    private String dictTypeId;
    private String id;
    private String name;
    private Object value;
    private String defaultValue;
    private String attributesText;

    public String getAttributesText()
    {
        return attributesText;
    }

    public void setAttributesText(String attributesText)
    {
        this.attributesText = attributesText;
    }

    public WebComponent()
    {
        attributes = null;
        extAttributes = null;
        available = null;
        type = null;
        dataType = "String";
        dict = false;
        dictTypeId = null;
        id = null;
        name = null;
        value = null;
        defaultValue = null;
        attributesText = null;
        attributes = new Properties();
        available = new ArrayList(5);
    }

    public boolean isDict()
    {
        return dict;
    }

    public void setDict(boolean dict)
    {
        this.dict = dict;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getDictTypeId()
    {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId)
    {
        this.dictTypeId = dictTypeId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Object getActualValue()
    {
        if(value != null)
            return value;
        if(defaultValue != null)
            return defaultValue;
        else
            return null;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public void setAttribute(String name, String value)
    {
        if(name != null && value != null)
        {
            attributes.setProperty(name, value);
            if(!available.contains(name))
                available.add(name);
        }
    }

    public String getAttribute(String name)
    {
        return attributes.getProperty(name);
    }

    public void setExtAttribute(String name, String value)
    {
        if(extAttributes == null)
            extAttributes = new Properties();
        extAttributes.setProperty(name, value);
    }

    public String getExtAttribute(String name)
    {
        if(extAttributes == null)
            return null;
        else
            return extAttributes.getProperty(name);
    }

    public void useAttribute(String name)
    {
        available.remove(name);
    }

    public boolean isAvailable(String name)
    {
        return available.contains(name);
    }

    public WebComponent getComponent()
    {
        return this;
    }

    public void prepareAttribute(StringBuilder buf, String name)
    {
        if(name.equals("readonly") && !Boolean.parseBoolean(getAttribute(name)))
            return;
        if(name.equals("disabled") && !Boolean.parseBoolean(getAttribute(name)))
            return;
        if(name.equals("multiple") && !Boolean.parseBoolean(getAttribute(name)))
        {
            return;
        } else
        {
            prepareAttribute(buf, name, getAttribute(name));
            return;
        }
    }

    public void prepareAttributes(StringBuilder buf)
    {
        for(int i = 0; i < available.size(); i++)
        {
            String name = (String)available.get(i);
            prepareAttribute(buf, name, attributes.getProperty(name));
        }

        if(getAttributesText() != null)
            buf.append((new StringBuilder()).append(" ").append(getAttributesText()).append(" ").toString());
    }

    public void prepareAttribute(StringBuilder buf, String name, String value)
    {
        if(name.equals("readonly") && !Boolean.parseBoolean(getAttribute(name)))
            return;
        if(name.equals("disabled") && !Boolean.parseBoolean(getAttribute(name)))
            return;
        if(name.equals("multiple") && !Boolean.parseBoolean(getAttribute(name)))
            return;
        buf.append(" ");
        buf.append(name);
        buf.append("=\"");
        if(value != null)
            buf.append(value);
        buf.append("\"");
    }

    public void prepareId(StringBuilder buf)
    {
        if(id != null)
            prepareAttribute(buf, "id", id);
    }

    public void prepareName(StringBuilder buf)
    {
        if(name != null)
            prepareAttribute(buf, "name", name);
    }
}
