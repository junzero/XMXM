// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   DatacellTag.java

package com.eos.web.taglib.richweb.datacell;

import com.eos.web.IParameterSupport;
import com.eos.web.taglib.richweb.AjaxBaseTag;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.gotop.util.security.ForUtil;

import java.util.*;
import javax.servlet.jsp.JspException;

public class DatacellTag extends AjaxBaseTag
    implements IParameterSupport
{

    private static final long serialVersionUID = 0x928af0b37971f283L;
    private String submitAction;
    private String isCount;
    private boolean _isCount;
    private String allowDel;
    private boolean _allowDel;
    private String allowAdd;
    private boolean _allowAdd;
    private String readonly;
    private boolean _readonly;
    private String freezeNumber;
    private int _freezeNumber;
    private String showIndex;
    private boolean _showIndex;
    private String width;
    private String height;
    private String linkId;
    private String linkField;
    private String rowSelectStyleClass;
    private String pageSize;
    private int _pageSize;
    private String initParamFunc;
    private String submitXpath;
    private String pageSizeList;
    private String rowEvenStyleClass;
    private String rowStyleClass;
    private String toolBar;
    private String toolbarLocation;
    private String customToolBar;
    private String toolsList;
    private String paramFormId;
    private String entityType;
    private HashMap parameterMap;
    private String varName;

    public DatacellTag()
    {
        submitAction = null;
        isCount = "true";
        _isCount = true;
        allowDel = "true";
        _allowDel = true;
        allowAdd = "true";
        _allowAdd = true;
        readonly = "false";
        _readonly = false;
        freezeNumber = "0";
        _freezeNumber = 0;
        showIndex = "true";
        _showIndex = true;
        width = null;
        height = null;
        linkId = null;
        linkField = null;
        rowSelectStyleClass = null;
        pageSize = "10";
        _pageSize = 10;
        initParamFunc = null;
        submitXpath = null;
        toolBar = null;
        toolbarLocation = null;
        customToolBar = null;
        toolsList = null;
        paramFormId = null;
        entityType = null;
        parameterMap = new HashMap();
    }

    public String getCustomToolBar()
    {
        return customToolBar;
    }

    public void setCustomToolBar(String customToolBar)
    {
        this.customToolBar = customToolBar;
    }

    public String getToolBar()
    {
        return toolBar;
    }

    public void setToolBar(String toolBar)
    {
        this.toolBar = toolBar;
    }

    public String getRowStyleClass()
    {
        return rowStyleClass;
    }

    public void setRowStyleClass(String rowStyleClass)
    {
        this.rowStyleClass = rowStyleClass;
    }

    public String getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(String pageCount)
    {
        pageSize = pageCount;
    }

    public void setPageSize(int pageCount)
    {
        pageSize = String.valueOf(pageCount);
    }

    public void initAttributes()
        throws JspException
    {
        super.initAttributes();
        _allowAdd = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowAdd, _allowAdd);
        _allowDel = XpathUtil.getBooleanByXpathSupport(getRootObj(), allowDel, _allowDel);
        _readonly = XpathUtil.getBooleanByXpathSupport(getRootObj(), readonly, _readonly);
        _freezeNumber = XpathUtil.getIntByXpathSupport(getRootObj(), freezeNumber, 0);
        _isCount = XpathUtil.getBooleanByXpathSupport(getRootObj(), isCount, false);
        submitAction = XpathUtil.getStringByXpathSupport(getRootObj(), submitAction);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        linkId = XpathUtil.getStringByXpathSupport(getRootObj(), linkId);
        linkField = XpathUtil.getStringByXpathSupport(getRootObj(), linkField);
        rowStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), rowStyleClass);
        rowSelectStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), rowSelectStyleClass);
        rowEvenStyleClass = XpathUtil.getStringByXpathSupport(getRootObj(), rowEvenStyleClass);
        submitXpath = XpathUtil.getStringByXpathSupport(getRootObj(), submitXpath);
        pageSizeList = XpathUtil.getStringByXpathSupport(getRootObj(), pageSizeList);
        toolsList = XpathUtil.getStringByXpathSupport(getRootObj(), toolsList);
        paramFormId = XpathUtil.getStringByXpathSupport(getRootObj(), paramFormId);
        pageSize = XpathUtil.getStringByXpathSupport(getRootObj(), pageSize);
        _pageSize = XpathUtil.getIntByXpathSupport(getRootObj(), pageSize, 10);
        initParamFunc = XpathUtil.getStringByXpathSupport(getRootObj(), initParamFunc);
        entityType = XpathUtil.getStringByXpathSupport(getRootObj(), entityType);
    }

    public void release()
    {
        super.release();
        submitAction = null;
        isCount = "true";
        _isCount = true;
        allowDel = "true";
        _allowDel = true;
        allowAdd = "true";
        _allowAdd = true;
        freezeNumber = "0";
        _freezeNumber = 0;
        width = null;
        height = null;
        linkId = null;
        linkField = null;
        rowSelectStyleClass = null;
        pageSize = "10";
        readonly = "false";
        _readonly = false;
        _pageSize = 10;
        initParamFunc = null;
        submitXpath = null;
        pageSizeList = null;
        rowEvenStyleClass = null;
        rowStyleClass = null;
        toolsList = null;
        paramFormId = null;
        toolBar = null;
        toolbarLocation = null;
        customToolBar = null;
        showIndex = "true";
        _showIndex = true;
        entityType = null;
        parameterMap = new HashMap();
    }

    public String getFreezeNumber()
    {
        return freezeNumber;
    }

    public void setFreezeNumber(String fixLeft)
    {
        freezeNumber = fixLeft;
    }

    public void setFreezeNumber(int fixLeft)
    {
        freezeNumber = String.valueOf(fixLeft);
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getLinkField()
    {
        return linkField;
    }

    public void setLinkField(String linkField)
    {
        this.linkField = linkField;
    }

    public String getLinkId()
    {
        return linkId;
    }

    public void setLinkId(String linkID)
    {
        linkId = linkID;
    }

    public String getIsCount()
    {
        return isCount;
    }

    public String isCount()
    {
        return isCount;
    }

    public void setIsCount(String isCount)
    {
        this.isCount = isCount;
    }

    public void setIsCount(boolean isCount)
    {
        this.isCount = String.valueOf(isCount);
    }

    public String getRowSelectStyleClass()
    {
        return rowSelectStyleClass;
    }

    public void setRowSelectStyleClass(String rowSelectStyle)
    {
        rowSelectStyleClass = rowSelectStyle;
    }

    public String getSubmitAction()
    {
        return submitAction;
    }

    public void setSubmitAction(String submitAction)
    {
        this.submitAction = submitAction;
    }

    public String getWidth()
    {
        return width;
    }

    public void setWidth(String width)
    {
        this.width = width;
    }

    public int get_freezeNumber()
    {
        return _freezeNumber;
    }

    public void set_freezeNumber(int left)
    {
        _freezeNumber = left;
    }

    public boolean is_isCount()
    {
        return _isCount;
    }

    public void set_isCount(boolean count)
    {
        _isCount = count;
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        StringBuilder buffer = new StringBuilder();
        if(getId() == null)
            setId((new StringBuilder()).append("datacell_").append(ForUtil.rRandom()).toString());
        varName = (new StringBuilder()).append("eos_").append(getId()).toString();
        buffer.append("<div id=\"");
        buffer.append(getId()).append("_container\" >\n");
        buffer.append("<script>\n");
        buffer.append("  var ").append(varName).append(" = new Datacell(\"").append(getId()).append("\");");
        buffer.append("</script>\n");
        buffer.append("<table class=\"eos-grid-container\" id=\"");
        buffer.append(getId()).append("_container_table\" ");
        buffer.append(" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n");
        buffer.append(" <tr><td></td></tr>\n");
        buffer.append(" <tr><td></td></tr>\n");
        buffer.append("</table>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        StringBuilder buffer = new StringBuilder();
        if(getToolBar() != null)
            buffer.append(getToolBar());
        if(customToolBar != null)
            buffer.append(customToolBar);
        if(getXpath() != null && getQueryAction() == null)
        {
            String xml = XpathUtil.getStringByList(getRootObj(), getXpath());
            if(xml != null && xml.length() > 0 && !"null".equalsIgnoreCase(xml))
            {
                buffer.append("<script type=\"text/xml\" id=\"").append(getId()).append("_xml\" >\n");
                buffer.append(xml).append("\n");
                buffer.append("</script>\n");
            }
        }
        buffer.append("<script  type=\"text/javascript\">\n");
        buffer.append("(function(){\n");
        buffer.append(varName).append(" = $o(\"").append(getId()).append("\");\n");
        if(getXpath() != null)
            buffer.append(varName).append(".xpath = \"").append(getXpath()).append("\";\n");
        if(getQueryAction() != null)
            buffer.append(varName).append(".queryAction = \"").append(getQueryAction()).append("\";\n");
        if(getRowSelectStyleClass() != null)
            buffer.append(varName).append(".selectStyleClass = \"").append(getRowSelectStyleClass()).append("\";\n");
        buffer.append(varName).append(".pageSize = ").append(pageSize).append(";\n");
        buffer.append(varName).append(".freezeNumber = ").append(get_freezeNumber()).append(";\n");
        buffer.append(varName).append(".isCount = ").append(is_isCount()).append(";\n");
        buffer.append(varName).append(".allowAdd = ").append(is_allowAdd()).append(";\n");
        buffer.append(varName).append(".allowDel = ").append(is_allowDel()).append(";\n");
        buffer.append(varName).append(".readonly = ").append(_readonly).append(";\n");
        buffer.append(varName).append(".readOnly = ").append(_readonly).append(";\n");
        if(linkField != null)
            buffer.append(varName).append(".linkField = \"").append(linkField).append("\";\n");
        if(showIndex != null)
            buffer.append(varName).append(".showIndex = ").append(showIndex).append(";\n");
        if(submitAction != null)
            buffer.append(varName).append(".submitAction = \"").append(submitAction).append("\";\n");
        if(width != null)
            buffer.append(varName).append(".width = \"").append(width).append("\";\n");
        if(height != null)
            buffer.append(varName).append(".height = \"").append(height).append("\";\n");
        if(initParamFunc != null)
            buffer.append(varName).append(".initParamFunc = \"").append(initParamFunc).append("\";\n");
        if(submitXpath != null)
            buffer.append(varName).append(".submitXpath = \"").append(submitXpath).append("\";\n");
        if(pageSizeList != null)
            buffer.append(varName).append(".pageSizeList = \"").append(pageSizeList).append("\";\n");
        if(rowEvenStyleClass != null)
            buffer.append(varName).append(".rowEvenStyleClass = \"").append(rowEvenStyleClass).append("\";\n");
        if(rowStyleClass != null)
            buffer.append(varName).append(".rowStyleClass = \"").append(rowStyleClass).append("\";\n");
        if(rowSelectStyleClass != null)
            buffer.append(varName).append(".rowSelectStyleClass = \"").append(rowSelectStyleClass).append("\";\n");
        if(toolbarLocation != null)
            buffer.append(varName).append(".toolbarLocation = \"").append(toolbarLocation).append("\";\n");
        if(toolsList != null)
            buffer.append(varName).append(".toolsList = \"").append(toolsList).append("\";\n");
        if(paramFormId != null)
            buffer.append(varName).append(".paramFormId = \"").append(paramFormId).append("\";\n");
        if(linkId != null)
            buffer.append(varName).append(".linkId = \"").append(linkId).append("\";\n");
        if(entityType != null)
            buffer.append(varName).append(".entityType = \"").append(entityType).append("\";\n");
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
        buffer.append("</script>\n");
        buffer.append("</div>\n");
        ResponseUtil.write(pageContext, buffer.toString());
        release();
        return 1;
    }

    public boolean is_allowAdd()
    {
        return _allowAdd;
    }

    public void set_allowAdd(boolean append)
    {
        _allowAdd = append;
    }

    public boolean is_allowDel()
    {
        return _allowDel;
    }

    public void set_allowDel(boolean delete)
    {
        _allowDel = delete;
    }

    public String getAllowAdd()
    {
        return allowAdd;
    }

    public void setAllowAdd(String noAppend)
    {
        allowAdd = noAppend;
    }

    public void setAllowAdd(boolean noAppend)
    {
        allowAdd = String.valueOf(noAppend);
    }

    public String getAllowDel()
    {
        return allowDel;
    }

    public void setAllowDel(String noDelete)
    {
        allowDel = noDelete;
    }

    public void setAllowDel(boolean noDelete)
    {
        allowDel = String.valueOf(noDelete);
    }

    public boolean is_readonly()
    {
        return _readonly;
    }

    public void set_readonly(boolean _readonly)
    {
        this._readonly = _readonly;
    }

    public String getReadonly()
    {
        return readonly;
    }

    public void setReadonly(String readonly)
    {
        this.readonly = readonly;
    }

    public void setReadonly(boolean readonly)
    {
        this.readonly = String.valueOf(readonly);
    }

    public String getInitParamFunc()
    {
        return initParamFunc;
    }

    public void setInitParamFunc(String initParamFunc)
    {
        this.initParamFunc = initParamFunc;
    }

    public String getSubmitXpath()
    {
        return submitXpath;
    }

    public void setSubmitXpath(String submitXpath)
    {
        this.submitXpath = submitXpath;
    }

    public String getPageSizeList()
    {
        return pageSizeList;
    }

    public void setPageSizeList(String pageSizeList)
    {
        this.pageSizeList = pageSizeList;
    }

    public String getRowEvenStyleClass()
    {
        return rowEvenStyleClass;
    }

    public void setRowEvenStyleClass(String rowEvenStyleClass)
    {
        this.rowEvenStyleClass = rowEvenStyleClass;
    }

    public int get_pageSize()
    {
        return _pageSize;
    }

    public void set_pageSize(int size)
    {
        _pageSize = size;
    }

    public String getToolbarLocation()
    {
        return toolbarLocation;
    }

    public void setToolbarLocation(String toolbarLocation)
    {
        this.toolbarLocation = toolbarLocation;
    }

    public String getToolsList()
    {
        return toolsList;
    }

    public void setToolsList(String toolsList)
    {
        this.toolsList = toolsList;
    }

    public String getParamFormId()
    {
        return paramFormId;
    }

    public void setParamFormId(String paramFormId)
    {
        this.paramFormId = paramFormId;
    }

    public String getShowIndex()
    {
        return showIndex;
    }

    public void setShowIndex(String showIndex)
    {
        this.showIndex = showIndex;
    }

    public void setShowIndex(boolean showIndex)
    {
        this.showIndex = String.valueOf(showIndex);
    }

    public boolean is_showIndex()
    {
        return _showIndex;
    }

    public void set_showIndex(boolean index)
    {
        _showIndex = index;
    }

    public String getEntityType()
    {
        return entityType;
    }

    public void setEntityType(String entityType)
    {
        this.entityType = entityType;
    }

    public void addParameter(String name, Object value)
    {
        parameterMap.put(name, value);
    }
}
