/**
 * RichTextEditor的构造函数.
 * @param {Object} id
 */
function RichTextEditor(id){
	this.id = id;
	this.name = "";
	this.value = "";
	this.width = "100%";
	this.height = 200;
	this.value = "";
	this.BasePath		= contextPath + '/common/fckeditor/' ;
	this.hiddenInput = null;
	this.toolbarSet = "Default";
	this.container = null;
	this.richEditor = null;
	PageControl.add(id,this);
}
/**
 * 初始化富文本编辑器的方法，创建一个Fckeditor
 */
RichTextEditor.prototype.init = function(){
	var oFCKeditor = new FCKeditor(this.id + "_textarea") ;
	oFCKeditor.BasePath	= this.BasePath ;
	this.container = $id(this.id + "_container");
	if(isNaN(parseInt(this.width))){
		this.width = "100%";
	}
	if(isNaN(parseInt(this.height))){
		this.height = 200;
	}
	oFCKeditor.Height	= this.height;
	oFCKeditor.Width    = this.width;
	oFCKeditor.Value	= this.value;
	oFCKeditor.ToolbarSet = this.toolbarSet;
	oFCKeditor.ReplaceTextarea() ;
	this.richEditor = oFCKeditor;
}
/**
 * 调用FCKeditorAPI的GetInstance方法得到editor实例,
 * 获取富文本编辑器的值.
 * 该方法只能在编辑器完全载入后调用，否则找不到FCKeditorAPI
 */
RichTextEditor.prototype.getValue = function(){
	var oEditor = this.getFCKEditor();
	var value = oEditor.GetHTML();
	if(value && this.value){
		if(value=="<p>" + this.value + "</p>"){
			return this.value;
		}
	}
	if(value==""||value=="<p></p>"){
		return this.value;
	}
	return value;
}
/**
 * 调用FCKeditorAPI的GetInstance方法得到editor实例,
 */
RichTextEditor.prototype.getFCKEditor = function(){
	var oEditor = FCKeditorAPI.GetInstance(this.id + "_textarea") ;
	return oEditor;
}
/**
 * 调用FCKeditorAPI的GetInstance方法得到editor实例,
 * 设置富文本编辑器的值.
 * 该方法只能在编辑器完全载入后调用，否则找不到FCKeditorAPI
 * @param {Object} value
 */
RichTextEditor.prototype.setValue = function(value){
	if(value!=null && value!=undefined){
		var oEditor = this.getFCKEditor();//FCKeditorAPI.GetInstance(this.id + "_textarea") ;
		oEditor.SetHTML(value);
	}else{
		oEditor.SetHTML("");
	}
	this.value = value;
}
/**
 * 符合控件开发规范的方法.
 */
RichTextEditor.prototype.setFocus = function(){

}
/**
 * 符合控件开发规范的方法.
 */
RichTextEditor.prototype.lostFocus = function(){

}
RichTextEditor.prototype.showEditor = function(){
	this.container.style.display = "";
}
RichTextEditor.prototype.hideEditor = function(){
	this.container.style.display = "none";
}
RichTextEditor.prototype.hide = function(){
	this.hideEditor();
}
RichTextEditor.prototype.validate = function(){
	return true;
}
RichTextEditor.prototype.isFocus = function(){
	return false;
}
RichTextEditor.prototype.getDisplayValue = function(value){
	return value;
}
RichTextEditor.prototype.setPosition = function(left,topx,width,height){
	this.container.style.position = "absolute";
	this.container.style.top = "0px";
	this.container.style.left = "0px";
	var maxZindex = getMaxZindex(document);
	if(this.container.style.zIndex!=maxZindex){
		this.container.style.zIndex = maxZindex;
	}
	setElementXY(this.container,[left,topx]);
}