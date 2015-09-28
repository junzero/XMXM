// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ExceptionTag.java

package com.eos.web.taglib.html;

import com.eos.system.exception.EOSException;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.utility.ClassUtil;
import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.*;
import com.primeton.ext.system.utility.international.ResourceMessageUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.jsp.JspException;

public class ExceptionTag extends BaseTagSupport
{

    private String showErrorNo;
    private String showStacktrace;
    private boolean _showErrorNo;
    private boolean _showStacktrace;
    private String property;

    public ExceptionTag()
    {
        showErrorNo = "true";
        showStacktrace = "false";
        _showErrorNo = true;
        _showStacktrace = false;
        property = null;
    }

    public String getShowErrorNo()
    {
        return showErrorNo;
    }

    public void setShowErrorNo(String showErrorNo)
    {
        this.showErrorNo = showErrorNo;
    }

    public String getShowStacktrace()
    {
        return showStacktrace;
    }

    public void setShowStacktrace(String showStacktrace)
    {
        this.showStacktrace = showStacktrace;
    }

    public String getProperty()
    {
        return property;
    }

    public void setProperty(String property)
    {
        this.property = property;
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _showErrorNo = XpathUtil.getBooleanByXpathSupport(getRootObj(), showErrorNo, true);
        _showStacktrace = XpathUtil.getBooleanByXpathSupport(getRootObj(), showStacktrace, false);
        property = XpathUtil.getStringByXpathSupport(getRootObj(), property, "_exception");
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        Object eobj = null;
        StringBuilder buf = new StringBuilder("<table id='table' class='eosException'>");
        if(property != null)
            eobj = XpathUtil.getObjectByXpath(getRootObj(), property);
        if(eobj instanceof EOSException)
        {
            EOSException eose = (EOSException)eobj;
            buf.append(getExceptionMessage(eose.getCode(), eose.getMessageOnly((new I18nUtil()).getLocale(null, pageContext))));
            if(_showErrorNo && eose.getCode() != null)
                buf.append(getExceptionCode(eose.getCode()));
            if(_showStacktrace)
                buf.append(getExceptionStack(eose.getStackTraceWithoutMessage()));
            eose = null;
        } else
        if(eobj instanceof EOSRuntimeException)
        {
            EOSRuntimeException eosre = (EOSRuntimeException)eobj;
            buf.append(getExceptionMessage(eosre.getCode(), eosre.getMessageOnly((new I18nUtil()).getLocale(null, pageContext))));
            if(_showErrorNo)
                buf.append(getExceptionCode(eosre.getCode()));
            if(_showStacktrace)
                buf.append(getExceptionStack(eosre.getStackTraceWithoutMessage()));
            eosre = null;
        } else
        {
            Throwable ex = (Throwable)eobj;
            buf.append(getExceptionStack(ex));
        }
        buf.append("</table>");
        ResponseUtil.write(pageContext, buf.toString());
        buf = null;
        eobj = null;
        return 1;
    }

    public void release()
    {
        super.release();
        showErrorNo = "true";
        showStacktrace = "false";
        _showErrorNo = true;
        _showStacktrace = false;
        property = null;
    }

    private String getExceptionCode(String code)
    {
        return (new StringBuilder()).append("<tr><td width='150px'style='text-align : center;font-size:9pt' ><b>").append(ResourceMessageUtil.getI18nResourceMessage("eos_ricweb_exceptionCode", (new I18nUtil()).getLocale(null, pageContext))).append("</b></td><td>").append(code).append("</td></tr>").toString();
    }

    private String getExceptionMessage(String code, String iMsg)
    {
        return (new StringBuilder()).append("<tr><td width='150px'style='text-align : center;font-size:9pt' ><b>").append(ResourceMessageUtil.getI18nResourceMessage("eos_ricweb_exceptionMsg", (new I18nUtil()).getLocale(null, pageContext))).append("</b></td><td style='color:#f60'>").append(StringUtil.htmlFilter(iMsg)).append("</td></tr>").toString();
    }

    private String getExceptionStack(Throwable ex)
    {
        StringBuilder sb = new StringBuilder((new StringBuilder()).append("<tr><td width='150px' style='text-align : center;font-size:9pt' ><b>").append(ResourceMessageUtil.getI18nResourceMessage("eos_ricweb_exceptionstk", (new I18nUtil()).getLocale(null, pageContext))).append("</b></td><td id='stack'>").toString());
        if(ClassUtil.getMethod(ex.getClass(), "getRootCause", null) != null)
            try
            {
                Method method = ClassUtil.getMethod(ex.getClass(), "getRootCause", null);
                if(method != null && method.invoke(ex, new Object[0]) != null)
                {
                    Exception rootEx = (Exception)method.invoke(ex, new Object[0]);
                    do
                    {
                        if(rootEx instanceof JspException)
                            break;
                        method = ClassUtil.getMethod(rootEx.getClass(), "getRootCause", null);
                        if(method == null || method.invoke(rootEx, new Object[0]) == null || !(method.invoke(rootEx, new Object[0]) instanceof Exception))
                            break;
                        rootEx = (Exception)method.invoke(rootEx, new Object[0]);
                    } while(true);
                    sb.append("<div><B>Root caused by: ");
                    sb.append(StringUtil.htmlFilter(rootEx.toString())).append("</B></div>");
                    StackTraceElement t[] = rootEx.getStackTrace();
                    StackTraceElement arr$[] = t;
                    int len$ = arr$.length;
                    for(int i$ = 0; i$ < len$; i$++)
                    {
                        StackTraceElement v = arr$[i$];
                        sb.append((new StringBuilder()).append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at ").append(StringUtil.htmlFilter(v.toString())).toString()).append("</div>");
                    }

                    return sb.toString();
                }
            }
            catch(SecurityException e)
            {
                e.printStackTrace();
            }
            catch(IllegalArgumentException e)
            {
                e.printStackTrace();
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch(InvocationTargetException e)
            {
                e.printStackTrace();
            }
        do
        {
            StackTraceElement t[] = ex.getStackTrace();
            if(ex.getCause() == null)
                sb.append("<div><b>Caused by: ");
            sb.append(StringUtil.htmlFilter(ex.toString())).append("</b></div>");
            StackTraceElement arr$[] = t;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                StackTraceElement v = arr$[i$];
                sb.append((new StringBuilder()).append("<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at ").append(StringUtil.htmlFilter(v.toString())).toString()).append("</div>");
            }

            if(ex.getCause() != null)
            {
                ex = ex.getCause();
            } else
            {
                sb.append("</td></tr>");
                return sb.toString();
            }
        } while(true);
    }

    private String getExceptionStack(String t)
    {
        StringBuilder sb = new StringBuilder((new StringBuilder()).append("<tr><td width='150px' style='text-align : center;font-size:9pt' ><b>").append(ResourceMessageUtil.getI18nResourceMessage("eos_ricweb_exceptionstk", (new I18nUtil()).getLocale(null, pageContext))).append("</b></td><td id='stack'>").toString());
        sb.append(StringUtil.htmlFilter(t).replace("\t", "<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").replace("\r", "</div>"));
        sb.append("</td></tr>");
        return sb.toString();
    }
}
