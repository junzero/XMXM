// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DownloadTag.java

package com.eos.web.taglib.html;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;

import com.eos.system.exception.EOSException;
import com.eos.web.taglib.util.RequestUtil;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.gotop.util.security.ForUtil;
import com.primeton.ext.access.http.EOSDownloadResource;
import com.primeton.ext.access.http.IDownloadResource;
import com.primeton.web.core.control.ComponentFactory;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseHandlerTag

public class DownloadTag extends BaseHandlerTag
{

    private String outputFileName;
    private String contentType;

    public DownloadTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        if(outputFileName != null && !Pattern.matches("[^\\\\\\?/:\\*\"\\|<>]+", outputFileName))
            throw new JspException("Filename is invalid!");
        try
        {
            component = ComponentFactory.createWebComponent("link");
            prepareAttributes();
            Object obj = getPropertyValue();
            String downloadFilekey = null;
            Object o = pageContext.getSession().getAttribute("EosDownFileList");
            Map fileList;
            if(o != null)
            {
                fileList = (Map)o;
            } else
            {
                fileList = new HashMap(100);
                pageContext.getSession().setAttribute("EosDownFileList", fileList);
            }
            if(obj instanceof String)
            {
                EOSDownloadResource dr = new EOSDownloadResource(obj.toString());
                if(!dr.getFile().exists())
                    throw new JspException("File not exist!");
                if(outputFileName != null)
                    dr.setOutputFileName(outputFileName);
                if(contentType != null)
                    dr.setContentType(contentType);
                do
                    downloadFilekey = (new StringBuilder()).append("download").append(ForUtil.rRandom()).toString();
                while(fileList.get(downloadFilekey) != null);
                fileList.put(downloadFilekey, dr);
            }
            if(obj instanceof IDownloadResource)
            {
                if(!((IDownloadResource)obj).getFile().exists())
                    throw new JspException("File not exist!");
                if(outputFileName != null)
                    ((IDownloadResource)obj).setOutputFileName(outputFileName);
                if(contentType != null)
                    ((IDownloadResource)obj).setContentType(contentType);
                do
                    downloadFilekey = (new StringBuilder()).append("download").append(ForUtil.rRandom()).toString();
                while(fileList.get(downloadFilekey) != null);
                fileList.put(downloadFilekey, (IDownloadResource)obj);
            }
            String href = (new StringBuilder()).append(RequestUtil.getContextPath(pageContext)).append("/common/jsp/download.jsp?id=").append(downloadFilekey).toString();
            component.setAttribute("href", href);
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        if(component == null)
            return 6;
        try
        {
            String output = component.toHtml();
            output = output.substring(0, output.lastIndexOf("null</a>"));
            ResponseUtil.write(pageContext, output);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        outputFileName = XpathUtil.getStringByXpathSupport(getRootObj(), outputFileName);
        contentType = XpathUtil.getStringByXpathSupport(getRootObj(), contentType);
    }

    protected void prepareAttributes()
    {
        super.prepareAttributes();
    }

    public void release()
    {
        super.release();
        outputFileName = null;
        contentType = null;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getOutputFileName()
    {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName)
    {
        this.outputFileName = outputFileName;
    }

    public int doEndTag()
        throws JspException
    {
        try
        {
            ResponseUtil.write(pageContext, "</a>");
        }
        catch(Exception e)
        {
            EOSException ex = new EOSException("17001211", (new StringBuilder()).append("error toHtml method in component :").append(component.getClass().getName()).toString(), e);
            throw new JspException(ex);
        }
        return 6;
    }
}
