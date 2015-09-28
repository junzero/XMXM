 package com.eos.web.taglib.richweb.rtree;
 
 import com.eos.web.taglib.basic.BaseTagSupport;
 import com.eos.web.taglib.util.ResponseUtil;
 import com.eos.web.taglib.util.XpathUtil;
 import java.util.ArrayList;
 import java.util.Hashtable;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import javax.servlet.jsp.JspException;
 
 public class Rtree extends BaseTagSupport
 {
   private String id;
   private String width;
   private String height;
   private String hasRoot;
   private String expandLevel;
   private int _expandLevel;
   private Hashtable menus;
   private Root root;
   private List moveActions;
   private List nodes;
   private String relatedSel;
   private String checkType;
   private String checkButton;
 
   public String getCheckType() {
	return checkType;
	}
	
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

public String getCheckButton() {
	return checkButton;
}

public void setCheckButton(String checkButton) {
	this.checkButton = checkButton;
}

public Rtree()
   {
     this.id = "rtree";
 
     this.width = "";
 
     this.height = "";
 
     this.hasRoot = "true";
 
     this.expandLevel = "1";
 
     this._expandLevel = 1;
     
     this.relatedSel = "";
     
     this.checkType = "simple";
     
     this.checkButton = "";
   }
 
   public int doEndTag()
     throws JspException
   {
     writeScript();
     return super.doEndTag();
   }
 
   public int doStartTag() throws JspException
   {
     init();
     return 1;
   }
 
   private void init() throws JspException {
     initAttributes();
     this.id = XpathUtil.getStringByXpathSupport(getRootObj(), this.id);
     this.width = XpathUtil.getStringByXpathSupport(getRootObj(), this.width);
 
     this.height = XpathUtil.getStringByXpathSupport(getRootObj(), this.height);
 
     this.hasRoot = XpathUtil.getStringByXpathSupport(getRootObj(), this.hasRoot);
 
     this._expandLevel = XpathUtil.getIntByXpathSupport(getRootObj(), this.expandLevel, 1, "expandLevel");
     
     this.relatedSel = XpathUtil.getStringByXpathSupport(getRootObj(), this.relatedSel);
 
     this.checkType = XpathUtil.getStringByXpathSupport(getRootObj(), this.checkType);
     
     this.checkButton = XpathUtil.getStringByXpathSupport(getRootObj(), this.checkButton);
     
     if (this._expandLevel < 1)
       this._expandLevel = 1;
     this.moveActions = new ArrayList();
     this.nodes = new ArrayList();
     this.menus = new Hashtable();
   }
 
   public void release()
   {
     super.release();
     this.id = "rtree";
     this.width = "";
     this.height = "";
     this.hasRoot = "true";
     this.relatedSel = "";
     this.checkType = "";
     this.checkButton = "";
   }
 
   public void addRtreeNode(String entity, String expression, String icon, String onclick, String ondblclick, String onrefresh, String bizAction, String[] childEntities, String initParamFunction, String iconType, String submitXpath, boolean preload)
   {
     TreeNode node = new TreeNode(entity, expression, icon, onclick, ondblclick, onrefresh, bizAction, childEntities, initParamFunction, iconType, submitXpath, preload);
 
     this.nodes.add(node);
   }
   public void addRtreeNode(String entity, String expression, String icon, String onclick, String ondblclick, String onrefresh, String bizAction, String[] childEntities, String initParamFunction, String iconType, String submitXpath, boolean preload,
		   String hasCheck,String fieldCheck,String entityProperty,String displayProperty,String displayOtherProperty,String checkCount)
   {
	   TreeNode node = new TreeNode(entity, expression, icon, onclick, ondblclick, onrefresh, bizAction, childEntities, initParamFunction, iconType, submitXpath, preload,
			   hasCheck,fieldCheck,entityProperty,displayProperty,displayOtherProperty,checkCount);
	   
	   this.nodes.add(node);
   }
 
   public void addMoveAction(String entity, String toEntity, String bizAction, String initParamFunction)
   {
     MoveAction action = new MoveAction(toEntity, bizAction, entity, initParamFunction);
 
     this.moveActions.add(action);
   }
 
   public void addMenu(String entity, String name, String onclick)
   {
     Menu menu;
     if (this.menus.containsKey(entity)) {
       menu = (Menu)this.menus.get(entity);
     } else {
       menu = new Menu();
       this.menus.put(entity, menu);
     }
     menu.addItem(name, onclick);
   }
 
   public void addRoot(String name, String icon, String expandAction, String[] firstLevelEntities, String onclick, String onrefresh, String ondblclick, String initParamFunction)
   {
     this.root = new Root(name, icon, expandAction, firstLevelEntities, onclick, ondblclick, onrefresh, initParamFunction);
   }
 
   private void writeScript()
     throws JspException
   {
     StringBuilder sb = new StringBuilder();
     sb.append("<div align=\"left\" style=\"width:" + this.width + ";overflow:auto\"id=\"" + this.id + "_container\"><nobr>");
 
     sb.append("<span id=\"" + this.id + "\"></span></nobr></div>\n");
     sb.append("<SCRIPT language=\"javascript\">\n");
 
     sb.append("var rtree=new RTree( $id('" + this.id + "'), \"" + this.width + "\", \"" + this.height + "\", " + this.hasRoot + ",\""+this.relatedSel+"\",\""+this.checkType+"\",\""+this.checkButton+"\");\n");
 
     sb.append("function " + this.id + "_rtree(){\n");
     sb.append(initTree());
     sb.append(getDisplay());
 
     sb.append(getMoveAction());
     sb.append(getMenu());
     sb.append("rtree.init();\n");
     if (this._expandLevel != 1) {
       sb.append(this.id + ".expandLevel(" + this._expandLevel + ");\n");
     }
 
     sb.append("}\n");
     sb.append("eventManager.add(window,'load'," + this.id + "_rtree);\n");
     sb.append("</SCRIPT>");
     ResponseUtil.write(this.pageContext, sb.toString());
   }
 
   private String getMenu() throws JspException {
     StringBuilder sb = new StringBuilder();
     Hashtable menus = this.menus;
     if (menus == null)
       return "";
     Iterator keys = menus.keySet().iterator();
 
     while (keys.hasNext()) {
       String entity = (String)keys.next();
       Menu menu = (Menu)menus.get(entity);
 
       Iterator menuItems = menu.menuItems.iterator();
       while (menuItems.hasNext()) {
         Rtree.Menu.MenuItem item = (Rtree.Menu.MenuItem)menuItems.next();
         sb.append("rtree_model.addMenuItem(\"" + entity + "\", \"" + item.name + "\", \"" + item.onclick + "\");\n");
       }
 
     }
 
     return sb.toString();
   }
 
   private String getMoveAction() throws JspException {
     StringBuilder sb = new StringBuilder();
 
     List moveActions = this.moveActions;
     if (moveActions == null) {
       return "";
     }
 
     for (int i = 0; i < moveActions.size(); i++)
     {
       MoveAction action = (MoveAction)moveActions.get(i);
 
       sb.append("rtree_model.setMoveAction( ");
       sb.append("\"" + action.entity + "\",");
       sb.append("\"" + action.toEntity + "\",");
       sb.append("\"" + action.bizAction + "\",");
       sb.append("\"" + action.initParamFunction + "\");\n");
     }
 
     return sb.toString();
   }
 
   private String getDisplay() throws JspException {
     StringBuilder sb = new StringBuilder();
     int length = this.nodes.size();
     if (length == 0)
       return "";
     for (int i = 0; i < length; i++) {
       TreeNode node = (TreeNode)this.nodes.get(i);
       StringBuilder firstLevelEntities = new StringBuilder("[");
       for (int j = 0; j < node.childEntities.length; j++) {
         if (j == 0) {
           firstLevelEntities.append("\"" + node.childEntities[j] + "\"");
         }
         else
           firstLevelEntities.append(", \"" + node.childEntities[j] + "\"");
       }
       firstLevelEntities.append("]");
       sb.append("rtree_model.setEntityInfo(\"");
       sb.append(node.entity).append("\",\"");
       sb.append(node.expression).append("\",\"");
       sb.append(node.icon).append("\",\"");
       sb.append(node.onclick).append("\",\"");
       sb.append(node.onrefresh).append("\",\"");
       sb.append(node.ondblclick).append("\",\"");
       sb.append(node.iconType).append("\",\"");
       sb.append(node.initParamFunction).append("\",");
       sb.append(firstLevelEntities).append(",\"");
       sb.append(node.bizAction).append("\",\"");
       sb.append(node.submitXpath).append("\",");
       sb.append(node.preLoad).append(",\"");
       sb.append(node.fieldCheck).append("\",\"");
       sb.append(node.entityProperty).append("\",\"");
       sb.append(node.displayProperty).append("\",\"");
       sb.append(node.displayOtherProperty).append("\",\"");
       sb.append(node.checkCount).append("\",");
       sb.append(node.hasCheck).append(");\n");
     }
 
     return sb.toString();
   }
 
   private String initTree() throws JspException {
     if (this.root == null) {
       throw new JspException("Root Node does not initialized!");
     }
     StringBuilder sb = new StringBuilder();
 
     sb.append("var rtree=$id('" + this.id + "')\n");
     sb.append("var rtree_model = rtree.model;\n");
 
     StringBuilder firstLevelEntities = new StringBuilder("[");
     for (int i = 0; i < this.root.firstLevelEntities.length; i++) {
       if (i == 0) {
         firstLevelEntities.append("\"" + this.root.firstLevelEntities[i] + "\"");
       }
       else
       {
         firstLevelEntities.append(", \"" + this.root.firstLevelEntities[i] + "\"");
       }
     }
     firstLevelEntities.append("]");
     sb.append("rtree_model.setEntityInfo(\"");
     sb.append("root").append("\",\"");
     sb.append(this.root.name).append("\",\"");
     sb.append(this.root.icon).append("\",\"");
     sb.append(this.root.onclick).append("\",\"");
     sb.append(this.root.onrefresh).append("\",\"");
     sb.append(this.root.ondblclick).append("\",\"");
     sb.append("").append("\",\"");
     sb.append(this.root.initParamFunction).append("\",");
     sb.append(firstLevelEntities).append(",\"");
     sb.append(this.root.expandAction).append("\");\n");
 
     return sb.toString();
   }
 
   public String isHasRoot()
   {
     return this.hasRoot;
   }
 
   public void setHasRoot(String hasRoot)
   {
     this.hasRoot = hasRoot;
   }
 
   public String getHeight()
   {
     return this.height;
   }
 
   public void setHeight(String height)
   {
     this.height = height;
   }
 
   public String getId()
   {
     return this.id;
   }
 
   public void setId(String id)
   {
     this.id = id;
   }
 
   public String getWidth()
   {
     return this.width;
   }
 
   public void setWidth(String width)
   {
     this.width = width;
   }
 
   public String getExpandLevel() {
     return this.expandLevel;
   }
 
   public void setExpandLevel(String expandLevel) {
     this.expandLevel = expandLevel;
   }
 
   private class Menu
   {
     public ArrayList menuItems;
 
     private Menu()
     {
       this.menuItems = new ArrayList();
     }
     public void addItem(String name, String onclick) {
       MenuItem item = new MenuItem(name, onclick);
       this.menuItems.add(item);
     }
     public class MenuItem {
       String name;
       String onclick;
 
       public MenuItem(String name, String onclick) {
         this.name = name;
         this.onclick = onclick;
       }
     }
   }
 
   private class MoveAction
   {
     public String toEntity;
     public String bizAction;
     public String entity;
     public String initParamFunction = null;
 
     public MoveAction(String toEntity, String bizAction, String entity) {
       this.toEntity = toEntity;
       this.bizAction = bizAction;
       this.entity = entity;
     }
 
     public MoveAction(String toEntity, String bizAction, String entity, String initParamFunction)
     {
       this.toEntity = toEntity;
       this.bizAction = bizAction;
       this.entity = entity;
       this.initParamFunction = initParamFunction;
     }
   }
 
   private class TreeNode
   {
     public String entity = "";
 
     public String expression = "";
 
     public String icon = "";
 
     public String onclick = "";
 
     public String ondblclick = "";
 
     public String onrefresh = "";
     public String bizAction;
     public String[] childEntities;
     public String initParamFunction = null;
     public String iconType;
     public String submitXpath;
     public boolean preLoad;
     public String hasCheck;
     public String fieldCheck;
     public String entityProperty;
     public String displayProperty;
     public String displayOtherProperty;
     public String checkCount;
 
     public TreeNode(String entity, String expression, String icon, String onclick, String ondblclick, String onrefresh, String bizAction, String[] childEntities, String initParamFunction, String iconType, String submitXpath, boolean preLoad)
     {
       this.entity = entity;
       this.expression = expression;
       this.icon = icon;
       this.onclick = onclick;
       this.ondblclick = ondblclick;
       this.onrefresh = onrefresh;
       this.bizAction = bizAction;
       this.childEntities = childEntities;
       this.initParamFunction = initParamFunction;
       this.iconType = iconType;
       this.submitXpath = submitXpath;
       this.preLoad = preLoad;
       this.hasCheck = hasCheck;
     }
     public TreeNode(String entity, String expression, String icon, String onclick, String ondblclick, String onrefresh, String bizAction, String[] childEntities, String initParamFunction, String iconType, String submitXpath, boolean preLoad,
    		 String hasCheck,String fieldCheck,String entityProperty,String displayProperty,String displayOtherProperty,String checkCount)
     {
    	 this.entity = entity;
    	 this.expression = expression;
    	 this.icon = icon;
    	 this.onclick = onclick;
    	 this.ondblclick = ondblclick;
    	 this.onrefresh = onrefresh;
    	 this.bizAction = bizAction;
    	 this.childEntities = childEntities;
    	 this.initParamFunction = initParamFunction;
    	 this.iconType = iconType;
    	 this.submitXpath = submitXpath;
    	 this.preLoad = preLoad;
    	 this.hasCheck = hasCheck;
    	 this.fieldCheck = fieldCheck;
    	 this.entityProperty = entityProperty;
    	 this.displayProperty = displayProperty;
    	 this.displayOtherProperty = displayOtherProperty;
    	 this.checkCount = checkCount;
     }
   }
 
   private class Root
   {
     public String name;
     public String icon;
     public String expandAction;
     public String onclick;
     public String ondblclick;
     public String[] firstLevelEntities;
     public String initParamFunction;
     public String onrefresh;
 
     public Root(String name, String icon, String expandAction, String[] firstLevelEntities, String onclick, String ondblclick, String onrefresh, String initParamFunction)
     {
       this.name = name;
       this.icon = icon;
       this.expandAction = expandAction;
       this.firstLevelEntities = firstLevelEntities;
       this.onclick = onclick;
       this.ondblclick = ondblclick;
       this.initParamFunction = initParamFunction;
       this.onrefresh = onrefresh;
     }
   }

public String getRelatedSel() {
	return relatedSel;
}

public void setRelatedSel(String relatedSel) {
	this.relatedSel = relatedSel;
}
 }