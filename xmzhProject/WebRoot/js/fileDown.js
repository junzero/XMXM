/**
 * 文件展示及下载封装
 *  user1:用来标识文件上传人
 *  user2:用来标识当前操作人
 *  remove:1:标识可以删除，0：标识不能删除
 *  author：yz
 */
(function(a){
	a.fn.fileDown=function(opts){
		opts=a.extend({
		filename:"下载一",
		filevalue:"",
		fileid:"",
		maxlength:10,
		remove:0,
		user1:"11",
		user2:"11",
		unUse:0
		},opts);
	//创建对象
	var _span;
	if(opts.filename!=''&&opts.filevalue!=''){
		//if($("div #tag")){//判断是否存在div #tag
		if(opts.unUse==0){
			_span="<span name='tab' id='radius'><input type='hidden' class='uploadf' value='"+opts.filevalue+"' /><b>" + opts.filename + "</b>"
		}else{
			_span="<span id='unradius'><b>" + opts.filename + "</b>";
		}
		if(opts.remove==0){
			_span+="</span>"
		}else{
			if(opts.user1==opts.user2){
				_span+="<a id='deltab'>×</a></span>";
			}else{
				_span+="</span>"
			}
		}
	    //$("#tag").append(_span);
	    return $(this).append(_span);;
		//}else{
		//	alert("不存在<div id='tag'></div>");
		//return;
		//}		
	}else{
		return false;
	}
};

$("#radius").live('click', function() {
		window.open('/file/tFileResourceTableAction_downLoadFile.action?fileId='+$(this).find(".uploadf").val());
		return false;
});
$("#deltab").live('click', function() {
	if(confirm('是否删除附件？')){
	var obj= $(this);
     $.ajax({
            url: '/file/tFileResourceTableAction_deleteFile.action',
            async: false,
            type: 'post',
	        data: "fileId="+$(this).parent().find(".uploadf").val(),
	        dataType: 'text',
            timeout: 60000,
	        success: function (txt) {
		      if(txt.indexOf("success")>=0){
		    	  obj.parent().remove();
		        	alert("删除文件成功!");
			        }
	        }  
	        });
     }
	return false;

});
})(jQuery);