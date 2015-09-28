// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   EmptyTag.java

package com.eos.web.taglib.logic;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import javax.servlet.jsp.JspException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Referenced classes of package com.eos.web.taglib.logic:
//            EqualTagBase

public class EmptyTag extends EqualTagBase
{

    public EmptyTag()
    {
    }

    public boolean condition()
        throws JspException
    {
        Object testValue = getPropertyValue();
        if(testValue == null)
            return true;
        if((testValue instanceof String) && ((String)testValue).length() == 0)
            return true;
        int size = 0;
        if(testValue.getClass().isArray())
            size = Array.getLength(testValue);
        else
        if(testValue instanceof Map)
            size = ((Map)testValue).size();
        else
        if(testValue instanceof Collection)
            size = ((Collection)testValue).size();
        else
        if(testValue instanceof Node)
            size = ((Node)testValue).getChildNodes().getLength();
        else
        if(testValue instanceof NodeList)
            size = ((NodeList)testValue).getLength();
        else
            return false;
        return size == 0;
    }
}
