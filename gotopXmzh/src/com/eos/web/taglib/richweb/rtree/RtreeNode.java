 package com.eos.web.taglib.richweb.rtree;
 
 import com.eos.web.taglib.basic.BaseTagSupport;
 import com.eos.web.taglib.util.XpathUtil;
 import javax.servlet.jsp.JspException;
 
 public class RtreeNode extends BaseTagSupport
 {
   public String action = "";
 
   public String childEntities = "";
 
   public String initParamFunc = "";
 
   public String nodeType = "";
 
   public String icon = "";
 
   public String onClickFunc = "";
 
   public String onDblclickFunc = "";
 
   public String onRefreshFunc = "";
 
   public String showField = "";
 
   public String iconField = "";
 
   public String submitXpath = "";
 
   public String preload = "false";
 
   private boolean _preload = false;
   
   public String hasCheck = "false";
   
   public String fieldCheck = "";
   
   public String entityProperty = "";
   public String displayProperty = "";
   public String displayOtherProperty = "";
   public String checkCount = "";
 
   public void setIcon(String icon) {
     this.icon = icon;
   }
 
   public String getIcon() {
     return this.icon;
   }
 
   public void setAction(String action) {
     this.action = action;
   }
 
   public String getAction() {
     return this.action;
   }
 
   public void setChildEntities(String childEntities) {
     this.childEntities = childEntities;
   }
 
   public String getChildEntities() {
     return this.childEntities;
   }
 
   public String getInitParamFunc()
   {
     return this.initParamFunc;
   }
 
   public void setInitParamFunc(String initParamFunc)
   {
     this.initParamFunc = initParamFunc;
   }
 
   public int doStartTag() throws JspException {
     init();
     Rtree tree = (Rtree)findAncestorWithClass(this, Rtree.class);
 
     if (tree == null)
       throw new JspException("Can not find Tree Tag!");
     String iconType = "image";
     String nodeIcon = this.icon;
     if (nodeIcon.equals("")) {
       nodeIcon = this.iconField;
       iconType = "xpath";
     }
     this.childEntities = this.childEntities.replaceAll(";", ",");
     tree.addRtreeNode(this.nodeType, this.showField, nodeIcon, this.onClickFunc, this.onDblclickFunc, this.onRefreshFunc, this.action, this.childEntities.split(","), this.initParamFunc, iconType, this.submitXpath, this._preload,
    		 this.hasCheck,this.fieldCheck,this.entityProperty,this.displayProperty,this.displayOtherProperty,this.checkCount);
 
     return 1;
   }
 
   public void init() throws JspException {
     initAttributes();
     this.action = XpathUtil.getStringByXpathSupport(getRootObj(), this.action);
 
     this.childEntities = XpathUtil.getStringByXpathSupport(getRootObj(), this.childEntities);
 
     this.initParamFunc = XpathUtil.getStringByXpathSupport(getRootObj(), this.initParamFunc);
 
     this.nodeType = XpathUtil.getStringByXpathSupport(getRootObj(), this.nodeType);
 
     this.icon = XpathUtil.getStringByXpathSupport(getRootObj(), this.icon);
     this.onClickFunc = XpathUtil.getStringByXpathSupport(getRootObj(), this.onClickFunc);
 
     this.onRefreshFunc = XpathUtil.getStringByXpathSupport(getRootObj(), this.onRefreshFunc);
 
     this.showField = XpathUtil.getStringByXpathSupport(getRootObj(), this.showField);
 
     this.iconField = XpathUtil.getStringByXpathSupport(getRootObj(), this.iconField);
 
     this.submitXpath = XpathUtil.getStringByXpathSupport(getRootObj(), this.submitXpath);
 
     this._preload = XpathUtil.getBooleanByXpathSupport(getRootObj(), this.preload, false, "preload");
     
     this.hasCheck = XpathUtil.getStringByXpathSupport(getRootObj(), this.hasCheck);
     
     this.fieldCheck = XpathUtil.getStringByXpathSupport(getRootObj(), this.fieldCheck);
     
     this.entityProperty = XpathUtil.getStringByXpathSupport(getRootObj(), this.entityProperty);
     this.displayProperty = XpathUtil.getStringByXpathSupport(getRootObj(), this.displayProperty);
     this.displayOtherProperty = XpathUtil.getStringByXpathSupport(getRootObj(), this.displayOtherProperty);
     this.checkCount = XpathUtil.getStringByXpathSupport(getRootObj(), this.checkCount);
   }
 
   public void release()
   {
     this.action = "";
     this.childEntities = "";
     this.initParamFunc = "";
     this.nodeType = "";
     this.icon = "";
     this.onClickFunc = "";
     this.onDblclickFunc = "";
     this.onRefreshFunc = "";
     this.showField = "";
     this.iconField = "";
     this.preload = "false";
     this.hasCheck = "false";
     this.fieldCheck = "";
     this.entityProperty = "";
     this.displayProperty = "";
     this.displayOtherProperty = "";
     this.checkCount = "";
   }
 
   public String getShowField()
   {
     return this.showField;
   }
 
   public void setShowField(String showField)
   {
     this.showField = showField;
   }
 
   public String getIconField()
   {
     return this.iconField;
   }
 
   public void setIconField(String iconField)
   {
     this.iconField = iconField;
   }
 
   public String getNodeType()
   {
     return this.nodeType;
   }
 
   public void setNodeType(String nodeType)
   {
     this.nodeType = nodeType;
   }
 
   public String getSubmitXpath()
   {
     return this.submitXpath;
   }
 
   public void setSubmitXpath(String submitXpath)
   {
     this.submitXpath = submitXpath;
   }
 
   public String getOnClickFunc() {
     return this.onClickFunc;
   }
 
   public void setOnClickFunc(String onClickFunc) {
     this.onClickFunc = onClickFunc;
   }
 
   public String getOnDblclickFunc() {
     return this.onDblclickFunc;
   }
 
   public void setOnDblclickFunc(String onDblclickFunc) {
     this.onDblclickFunc = onDblclickFunc;
   }
 
   public String getOnRefreshFunc() {
     return this.onRefreshFunc;
   }
 
   public void setOnRefreshFunc(String onRefreshFunc) {
     this.onRefreshFunc = onRefreshFunc;
   }
 
   public String getPreload() {
     return this.preload;
   }
 
   public void setPreload(String preload) {
     this.preload = preload;
   }

	public String getHasCheck() {
		return hasCheck;
	}
	
	public void setHasCheck(String hasCheck) {
		this.hasCheck = hasCheck;
	}

	public String getFieldCheck() {
		return fieldCheck;
	}

	public void setFieldCheck(String fieldCheck) {
		this.fieldCheck = fieldCheck;
	}

	public String getEntityProperty() {
		return entityProperty;
	}

	public void setEntityProperty(String entityProperty) {
		this.entityProperty = entityProperty;
	}

	public String getDisplayProperty() {
		return displayProperty;
	}

	public void setDisplayProperty(String displayProperty) {
		this.displayProperty = displayProperty;
	}

	public String getDisplayOtherProperty() {
		return displayOtherProperty;
	}

	public void setDisplayOtherProperty(String displayOtherProperty) {
		this.displayOtherProperty = displayOtherProperty;
	}

	public String getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(String checkCount) {
		this.checkCount = checkCount;
	}
 }