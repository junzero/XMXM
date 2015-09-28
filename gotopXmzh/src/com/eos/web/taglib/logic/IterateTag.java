// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   IterateTag.java

package com.eos.web.taglib.logic;

import com.eos.data.xpath.XPathLocator;
import com.eos.web.exception.BreakException;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.XpathUtil;
import java.lang.reflect.Array;
import java.util.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TryCatchFinally;
import org.w3c.dom.*;

public class IterateTag extends BaseIteratorTagSupport
    implements TryCatchFinally
{

    private String indexId;
    private Iterator iterator;
    private Object currentObj;
    private Collection collection;
    private String offset;
    private String length;
    private String step;
    private int iOffset;
    private int iLength;
    private int iStep;
    private Object rootObj;
    private static final int BREAKSTATUS = 1;
    private static final int NOMALSTATUS = 0;
    private int iterateStatus;
    private int index;

    public IterateTag()
    {
        indexId = null;
        iterator = null;
        currentObj = null;
        collection = null;
        offset = null;
        length = null;
        step = null;
        rootObj = null;
        iterateStatus = 0;
        index = 1;
    }

    public int getIndex()
    {
        return index;
    }

    public String getIndexId()
    {
        return indexId;
    }

    public void setIndexId(String indexId)
    {
        this.indexId = indexId;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Object collection = getPropertyValue();
        if(collection == null)
            return 0;
        if(collection.getClass().isArray())
            try
            {
                iterator = Arrays.asList((Object[])(Object[])collection).iterator();
            }
            catch(ClassCastException e)
            {
                int length = Array.getLength(collection);
                ArrayList c = new ArrayList(length);
                for(int i = 0; i < length; i++)
                    c.add(Array.get(collection, i));

                iterator = c.iterator();
            }
        else
        if(collection instanceof Collection)
            iterator = ((Collection)collection).iterator();
        else
        if(collection instanceof Iterator)
            iterator = (Iterator)collection;
        else
        if(collection instanceof Map)
            iterator = ((Map)collection).entrySet().iterator();
        else
        if(collection instanceof Document)
        {
            NodeList nl = ((Document)collection).getDocumentElement().getChildNodes();
            iterator = createIteratorByNodeList(nl);
        } else
        if(collection instanceof Node)
        {
            NodeList nl = ((Node)collection).getChildNodes();
            iterator = createIteratorByNodeList(nl);
        } else
        if(collection instanceof NodeList)
        {
            NodeList nl = (NodeList)collection;
            iterator = createIteratorByNodeList(nl);
        } else
        {
            throw new JspException("找不到集合类变量！");
        }
        rootObj = XpathUtil.getDataContextRoot("page", pageContext);
        for(; iOffset >= 0; iOffset--)
            if(iterator.hasNext())
                currentObj = iterator.next();
            else
                return 0;

        if(iLength < 0)
            return 0;
        if(iLength == 0)
            return 1;
        iLength--;
        XPathLocator.getInstance().setValue(rootObj, id, currentObj);
        if(indexId != null)
            XPathLocator.getInstance().setValue(rootObj, indexId, Integer.valueOf(index));
        return 1;
    }

    public int doAfterBody()
    {
        for(int i = iStep; i > 0; i--)
            if(iterator.hasNext())
                currentObj = iterator.next();
            else
                return 0;

        if(iLength >= 0)
        {
            if(iLength == 0)
                return 0;
            iLength--;
        }
        index++;
        if(indexId != null)
            XPathLocator.getInstance().setValue(rootObj, indexId, (new StringBuilder()).append(index).append("").toString());
        pageContext.setAttribute(id, currentObj);
        return iterateStatus != 1 ? 2 : 0;
    }

    public int doEndTag()
    {
        release();
        return 6;
    }

    public void release()
    {
        indexId = null;
        iterator = null;
        currentObj = null;
        collection = null;
        index = 1;
        offset = null;
        length = null;
        step = null;
        rootObj = null;
        iterateStatus = 0;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        if(length != null && length.charAt(0) == '-')
            throw new JspException((new StringBuilder()).append("The attribute length:").append(length).append(" is invalid!").toString());
        iLength = XpathUtil.getIntByXpathSupport(getRootObj(), length, 0x7fffffff, "length");
        if(offset != null && offset.charAt(0) == '-')
            throw new JspException((new StringBuilder()).append("The attribute offset:").append(offset).append(" is invalid!").toString());
        iOffset = XpathUtil.getIntByXpathSupport(getRootObj(), offset, 0, "offset");
        if(step != null && step.charAt(0) == '-')
        {
            throw new JspException((new StringBuilder()).append("The attribute step:").append(step).append(" is invalid!").toString());
        } else
        {
            iStep = XpathUtil.getIntByXpathSupport(getRootObj(), step, 1, "step");
            return;
        }
    }

    private Iterator createIteratorByNodeList(NodeList nl)
    {
        List m = new ArrayList();
        int length = nl.getLength();
        for(int i = 0; i < length; i++)
            m.add(i, nl.item(i));

        return m.iterator();
    }

    public Collection getCollection()
    {
        return collection;
    }

    public void setCollection(Collection collection)
    {
        this.collection = collection;
    }

    public Object getCurrentObj()
    {
        return currentObj;
    }

    public void setCurrentObj(Object currentObj)
    {
        this.currentObj = currentObj;
    }

    public int getILength()
    {
        return iLength;
    }

    public void setILength(int length)
    {
        iLength = length;
    }

    public int getIOffset()
    {
        return iOffset;
    }

    public void setIOffset(int offset)
    {
        iOffset = offset;
    }

    public int getIStep()
    {
        return iStep;
    }

    public void setIStep(int step)
    {
        iStep = step;
    }

    public Iterator getIterator()
    {
        return iterator;
    }

    public void setIterator(Iterator iterator)
    {
        this.iterator = iterator;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength(String length)
    {
        this.length = length;
    }

    public void setLength(int length)
    {
        this.length = (new StringBuilder()).append(length).append("").toString();
    }

    public String getOffset()
    {
        return offset;
    }

    public void setOffset(String offset)
    {
        this.offset = offset;
    }

    public void setOffset(int offset)
    {
        this.offset = (new StringBuilder()).append(offset).append("").toString();
    }

    public String getStep()
    {
        return step;
    }

    public void setStep(String step)
    {
        this.step = step;
    }

    public void setStep(int step)
    {
        this.step = (new StringBuilder()).append(step).append("").toString();
    }

    public void doCatch(Throwable arg0)
        throws Throwable
    {
        if(arg0 instanceof BreakException)
        {
            iterateStatus = 1;
            return;
        } else
        {
            throw arg0;
        }
    }

    public void doFinally()
    {
    }
}
