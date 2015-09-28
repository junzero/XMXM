 package com.eos.web.taglib.html;
 
 import javax.servlet.jsp.JspException;

import com.eos.web.Constants;
import com.eos.web.IParameterSupport;
import com.eos.web.exception.EOSTagExceptionUtil;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.StringUtil;
import com.eos.web.taglib.util.XpathUtil;
 
 public class ParameterTag extends BaseIteratorTagSupport
 {
   private String name = null;
 
   private String indexed = "false";
 
   private boolean _indexed = false;
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public void initAttributes()
     throws JspException
   {
     String tmpProperty = XpathUtil.getStringByXpathSupport(getRootObj(), getProperty());
     super.initAttributes();
     this._indexed = XpathUtil.getBooleanByXpathSupport(getRootObj(), this.indexed, false);
 
     if (this.name == null) {
       if (tmpProperty != null)
         this.name = tmpProperty;
     }
     else {
       this.name = XpathUtil.getStringByXpathSupport(getRootObj(), this.name);
     }
     tmpProperty = null;
   }
 
   public int doStartTag() throws JspException {
     initAttributes();
     String key = this.name;
     if (is_indexed()) {
       key = StringUtil.getXpathNameWithIndex(this.name, getIteratorIndex());
     }
     IParameterSupport parent = (IParameterSupport)findAncestorWithClass(this, IParameterSupport.class);
 
     if (parent == null) {
       EOSTagExceptionUtil.throwEOSTagException("17030110", "parent tag  not found", Constants.EOS_TAG_EXCEPTION_TYPE.EOSParentTagNotFoundException);
     }
     Object pv = getPropertyValue() != null ? getPropertyValue() : getValue();
     if (pv == null) pv = "";
     parent.addParameter(key, pv);
 
     return 6;
   }
   public void release() {
     super.release();
     this.name = null;
     this.indexed = "false";
     this._indexed = false;
   }
 
   public boolean is_indexed()
   {
     return this._indexed;
   }
 
   public void set_indexed(boolean _indexed)
   {
     this._indexed = _indexed;
   }
 
   public String getIndexed()
   {
     return this.indexed;
   }
 
   public void setIndexed(String indexed)
   {
     this.indexed = indexed;
   }
 }