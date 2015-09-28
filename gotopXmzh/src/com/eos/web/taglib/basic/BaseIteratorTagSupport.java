 package com.eos.web.taglib.basic;
 
 import com.eos.web.taglib.logic.IterateTag;
 import com.eos.web.taglib.util.XpathUtil;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.PageContext;
 
 public class BaseIteratorTagSupport extends BaseTagSupport
 {
   private static final long serialVersionUID = -7098541093050626175L;
   private String iterateId = null;
   private String property = null;
   private String value = null;
   private Object propertyValue = null;
 
   public Object getPropertyValue()
   {
     return this.propertyValue;
   }
   public void initAttributes() throws JspException {
     super.initAttributes();
 
     this.property = XpathUtil.getStringByXpathSupport(getRootObj(), this.property);
     this.value = XpathUtil.getStringByXpathSupport(getRootObj(), this.value);
     initProperty();
   }
 
   private void initProperty()
     throws JspException
   {
     if (this.iterateId == null) {
       if (this.property == null) {
         return;
       }
	   this.propertyValue = XpathUtil.getObjectByXpath(getRootObj(), this.property);
     } else {
       Object rootObj = this.pageContext.getAttribute(this.iterateId);
       if (this.property == null) {
         this.propertyValue = rootObj;
       } else {
         if (rootObj == null)
         {
           this.propertyValue = null;
           return;
         }
         this.propertyValue = XpathUtil.getObjectByXpath(rootObj, this.property);
       }
     }
   }
 
   public int getIteratorIndex()
   {
     IterateTag it = (IterateTag)findAncestorWithClass(this, IterateTag.class);
     if (it == null) {
       return -1;
     }
     return it.getIndex();
   }
 
   public void release() {
     super.release();
     this.iterateId = null;
     this.property = null;
     this.value = null;
     this.propertyValue = null;
   }
 
   public String getIterateId()
   {
     return this.iterateId;
   }
 
   public void setIterateId(String IterateId)
   {
     this.iterateId = IterateId;
   }
 
   public String getProperty()
   {
     return this.property;
   }
 
   public void setProperty(String property)
   {
     this.property = property;
   }
 
   public String getValue()
   {
     return this.value;
   }
 
   public void setValue(String value)
   {
     this.value = value;
   }
 }