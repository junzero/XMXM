<%@ taglib uri="http://eos.primeton.com/tags/html" prefix="h"%>
<%@ taglib uri="http://eos.primeton.com/tags/logic" prefix="l"%>
<%@ taglib uri="http://eos.primeton.com/tags/bean" prefix="b"%>
<%@ taglib uri="http://eos.primeton.com/tags/eos" prefix="e"%>
<%@ taglib uri="http://eos.primeton.com/tags/webcomp" prefix="w"%>
<%@ taglib uri="http://eos.primeton.com/tags/richweb" prefix="r"%>
<%@ taglib uri="http://eos.primeton.com/tags/dict" prefix="d"%>
<%@ taglib uri="http://eos.primeton.com/tags/abframe" prefix="abf"%>
<%@page import="com.eos.data.datacontext.IUserObject"%>
<% String _headskin = "default";
   IUserObject user = (IUserObject)session.getAttribute(IUserObject.KEY_IN_CONTEXT);
   if(user!=null){
		java.util.Map map = (java.util.Map)user.getAttributes();
		if (map!=null){
		     String skin = (String) user.getAttributes().get("skin");
		     if(skin!=null&&!"".equals(skin.trim())){
		        _headskin = skin;
		     }
		}
   }
   String contextPathStr = request.getContextPath();
   String resourceJsStr = "/common/skins/"+_headskin +"/scripts/resource.js";
   String commonJsStr = "/common/skins/"+_headskin +"/scripts/common.js";
   String style_sysCssStr = "/common/skins/"+_headskin +"/theme/style-sys.css";
   String style_componentCssStr = "/common/skins/"+_headskin +"/theme/style-component.css";
   String style_customCssStr = "/common/skins/"+_headskin +"/theme/style-custom.css";
%>
<script type="text/javascript">
	var contextPath = "<%=contextPathStr %>";	 // you should define the contextPath of web application
	var EOSDEBUG = false;
	var FINAL_PANEL_HEIGHT = 22;
	<%-- manager(group_manager.jsp)主页面中iframe需要减少的高度 --%>
	var IFRAME_MODIFY_HEIGHE = 35;
	<%-- tree(group_tree.jsp)页面中tree需要减少的高度 --%>
	var TREE_MODIFY_HEIGHT = 22;
</script>
<h:script src="/common/javascripts/zh_CN/message.js"/>
<h:script src='<%=resourceJsStr %>'/>
<h:script src="/common/fckeditor/fckeditor.js" />
<h:script src='/common/scripts/eos-web.js' />
<h:script src="/common/lib/mootools.js"/>
<h:script src="/common/gotop/role_path.js"/>
<h:script src="/common/gotop/webkit_path.js"/>
<h:script src='<%=commonJsStr %>' />
<h:css href='<%=style_sysCssStr %>'/>
<h:css href='<%=style_componentCssStr %>'/>
<h:css href='<%=style_customCssStr %>'/>
<script type="text/javascript">
EOSLog.formatXML=function(s){return s;} //临时解决动态树慢的问题

//解决Datacell提示信息问题
Datacell.prototype.validateAll = function(){
  var _activeEntity=this.activeEntity;
  var ac=this.getActiveCell();
  var ar=this.activeRow;
  for (var i=0;i<this.table.tBodies[0].rows.length;i++ ){
 
   var row = this.table.tBodies[0].rows[i];
   var entity = this.getEntity(row);
   if (entity.status==Entity.STATUS_NEW || entity.status==Entity.STATUS_MODIFIED){
    this.activeEntity=entity;
    for (var cn=0;cn<this.fields.length;cn++ ){
     var field=this.fields[cn];
     
     if (field.editor){
      this.setActiveCell(row.cells[cn]);
      var value=entity.getProperty(field.fieldName);
      field.editor.setValue(value);
      var f;
      try {
       f=field.editor.validate();
         
      }catch (e){
       f=false;
      }
      if (!f){
      
       this.setActiveCell(null,row.cells[cn]);
       
       this.startEdit();
     
       try {
        f=field.editor.validate();
       }catch (e){
        f=false;
       }
       this.unlocked();
       this.activeEntity=_activeEntity;
       return false;
      }
     }
    }
   }
  }
  this.activeEntity=_activeEntity;
  this.activeCell=ac;
        this.activeRow=ar;
  return true;
}
</script>