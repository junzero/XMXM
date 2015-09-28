/* Demo Note:  This demo uses a FileProgress class that handles the UI for displaying the file name and percent complete.
The FileProgress class is not part of SWFUpload.
*/


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */

function swfUploadPreLoad() {
	var self = this;
	var loading = function () {
		//document.getElementById("divSWFUploadUI").style.display = "none";
		document.getElementById("divLoadingContent").style.display = "";

		var longLoad = function () {
			document.getElementById("divLoadingContent").style.display = "none";
			document.getElementById("divLongLoading").style.display = "";
		};
		this.customSettings.loadingTimeout = setTimeout(function () {
				longLoad.call(self)
			},
			15 * 1000
		);
	};
	
	this.customSettings.loadingTimeout = setTimeout(function () {
			loading.call(self);
		},
		1*1000
	);
}
function swfUploadLoaded() {
	var self = this;
	clearTimeout(this.customSettings.loadingTimeout);
	//document.getElementById("divSWFUploadUI").style.visibility = "visible";
	//document.getElementById("divSWFUploadUI").style.display = "block";
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "none";
	
	//document.getElementById("btnBrowse").onclick = function () { self.selectFiles(); };
	//document.getElementById("btnCancel").onclick = function () { self.cancelQueue(); };
	document.getElementById("btnCancel").onclick = function () { self.cancelQueue(); document.getElementById(self.customSettings.cancelButtonId).disabled = true;};
	//上传按钮执行的动作 add by stephen
	document.getElementById("btnUpload").onclick = function () { self.startUpload(); };
}
   
function swfUploadLoadFailed() {
	clearTimeout(this.customSettings.loadingTimeout);
	//document.getElementById("divSWFUploadUI").style.display = "none";
	document.getElementById("divLoadingContent").style.display = "none";
	document.getElementById("divLongLoading").style.display = "none";
	document.getElementById("divAlternateContent").style.display = "";
}
   
   
function fileQueued(file) {
	try {
		FileProgress(file, this.customSettings.myFileListTarget,this);
	} catch (ex) {
		this.debug(ex);
	}
	/* comment by stephen
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Pending...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}
	*/

}

function fileQueueError(file, errorCode, message) {
	try {
		
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {//  SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED
			alert("操作失败，上传的文件个数限制为："+this.settings.file_upload_limit);
			return;
		}
		
		FileProgress(file, this.customSettings.myFileListTarget,this);
		var tr = document.getElementById(file.id);
		tr.style.color="red";
		var bar = document.getElementById(file.id+"_bar");
		var errInfo = "选择失败";
		
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			//progress.setStatus("File is too big.");
			errInfo = errInfo + ":文件太大("+this.settings.file_size_limit+" 内)";
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			//progress.setStatus("Cannot upload Zero Byte files.");
			errInfo = errInfo + ":0字节文件";
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			//progress.setStatus("Invalid File Type.");
			errInfo = errInfo + ":文件类型错误";
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		default:
			if (file !== null) {
				//progress.setStatus("Unhandled Error");
				errInfo = errInfo + ":系统未知错误";
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
		
		bar.parentNode.innerHTML="<font style='color: red;'>"+errInfo+"</font>";
		var delObject = document.getElementById(file.id+"_del");
		delObject.parentNode.innerHTML="<span id=\""+file.id+"_delFile\" swtId=\""+file.id+"\" onclick=\"__deleteTrFun(this,0)\" class=uploadCancel>删除</span>";
		swfUploadInstance.cancelUpload(file.id);
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (numFilesSelected > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		
		/* I want auto start the upload and I can do that here */
		//add by stephen
		if(this.settings.auto_upload){//是否要上传
			this.startUpload();
		}
		//this.startUpload(); comment by stephen
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and
		return true to indicate that the upload should start.
		It's important to update the UI here because in Linux no uploadProgress events are called. The best
		we can do is say we are uploading.
		 */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {}
	
	return true;
}

function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var bar = document.getElementById(file.id+"_bar");
		bar.style.width=percent+"px";
		if(percent!=100){
			var bar_title = file.id+"_bar_title";
			bar_title = document.getElementById(bar_title);
			if(bar_title!=null && bar_title.title){
				bar_title.innerHTML=bar_title.title+" "+percent+"%";
			}
		}
		var tr = document.getElementById(file.id);
		tr.style.color="blue";
	} catch (ex) {
		this.debug(ex);
	}
	/*
	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus("Uploading...");
	} catch (ex) {
		this.debug(ex);
	}
	*/
}

//处理每一个文件的上传状态
function uploadSuccess(file, serverData) {
	var rtnArr = serverData.split(",");
	var isSuccess = (rtnArr[0].indexOf("successed")==0?true:false);
	var logicFileName = rtnArr[1];
	var sid = rtnArr[2];
	try {
		if(isSuccess){
			var tr = document.getElementById(file.id);
			tr.style.color="green";
			var bar = document.getElementById(file.id+"_bar");
			var submitName = "<input type='hidden' name='"+this.customSettings.submitName+"' value='"+sid+"'>";
			bar.parentNode.innerHTML="上传成功"+submitName;
			var delObject = document.getElementById(file.id+"_del");
			delObject.parentNode.innerHTML="<span id=\""+file.id+"_delFile\" fileId=\""+sid+"\" swtId=\""+file.id+"\" class=uploadCancel>删除</span>";
			var delFile = document.getElementById(file.id+"_delFile");
			var dtf = this;
			delFile.onclick=function __deleteTrFunTest(){
				__deleteTrFun(delFile,1,dtf);
			}
			document.getElementById(this.customSettings.myFileListTarget+"Count").innerHTML=this.getStats().files_queued;
		}else{
			var tr = document.getElementById(file.id);
			tr.style.color="red";
			var bar = document.getElementById(file.id+"_bar");
			if(logicFileName ==' or devMode'){
				logicFileName = "文件大小超过限制";
			}
			bar.parentNode.innerHTML="<font style='color: red;'>"+"上传失败:"+logicFileName+"</font>";
			var delObject = document.getElementById(file.id+"_del");
			delObject.parentNode.innerHTML="<span id=\""+file.id+"_delFile\" swtId=\""+file.id+"\" onclick=\"__deleteTrFun(this,0)\" class=uploadCancel>删除</span>";
		}
	} catch (ex) {
		this.debug(ex);
	}
	/*
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("Complete.");
		progress.toggleCancel(false);

	} catch (ex) {
		this.debug(ex);
	}
	*/
}
function __deleteTrFun(Obj,state,swfUploadInstance){
	var fileId = Obj.fileId;
	var tr = document.getElementById(Obj.swtId);
	tr.style.color="red";
	var table = tr.parentElement;
	if(fileId){}else{
		table.deleteRow(tr.rowIndex);
		return;
	}
	if(state==0){
		table.deleteRow(tr.rowIndex);
	}else if(Obj.isCancel || window.confirm('是否确定删除该附件？')){
  		var myAjax = new Ajax("/common/attachmentAction_deleteWithDB.action");
  		myAjax.addParam("fileIds", fileId);
  		myAjax.submit();
  		var returnValue = myAjax.getValue("result");
  		if(returnValue=="1"){//删除成功的内容
  			if(state!=2){//2上次提交的内容，不需要到成功删除中
  				swfUploadInstance.cancelUpload(Obj.swtId);
  				var fileListID = swfUploadInstance.customSettings.myFileListTarget;
  				var fStats = swfUploadInstance.getStats();
  				fStats.successful_uploads=fStats.successful_uploads-1;
  				swfUploadInstance.setStats(fStats);
	  			document.getElementById(fileListID+"SuccessUploadCount").innerHTML=swfUploadInstance.getStats().successful_uploads;
	  			var idFileListDelCount = document.getElementById(fileListID+"DelCount");
	  			var ifldc = idFileListDelCount.innerText;
	  			if(ifldc){
		  			idFileListDelCount.innerText = parseInt(ifldc,10)+1;
	  			}else{
		  			idFileListDelCount.innerText = 1;
	  			}
  			}
  			table.deleteRow(tr.rowIndex);
  		}else{
  			alert("删除文件失败");
  			return;
  		}
	}
}
function uploadError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("Upload Error: " + message);
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			// If there aren't any files left (they were all cancelled) disable the cancel button
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("Stopped");
			break;
		default:
			progress.setStatus("Unhandled Error: " + errorCode);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

//列表文件上传完毕后
function uploadComplete(file) {
	if (this.getStats().files_queued === 0) {
		document.getElementById(this.customSettings.cancelButtonId).disabled = true;
	}
}

// This event comes from the Queue Plugin
function queueComplete(numFilesUploaded) {
	document.getElementById(this.customSettings.myFileListTarget+"SuccessUploadCount").innerHTML=this.getStats().successful_uploads;//add by stephen
	/* comment by stephen
	var status = document.getElementById("divStatus");
	status.innerHTML = numFilesUploaded + " file" + (numFilesUploaded === 1 ? "" : "s") + " uploaded.";
	*/
}

//add by stephen 
//version 2.2 why remove follow function, i must add again.
var _K = 1024;
var _M = _K*1024;
function getNiceFileSize(bitnum){
	if(bitnum<_M){
		if(bitnum<_K){
			return bitnum+'B';
		}else{
			return Math.ceil(bitnum/_K)+'K';
		}
		
	}else{
		return Math.ceil(100*bitnum/_M)/100+'M';
	}
}
			var settings = {
				flash_url : "/common/scripts/swfupload/swfupload.swf",
				upload_url: "/common/attachmentAction_uploadWithDB.action",
				file_post_name:"uploadfile",//加了这个，在webwork的action中要用
				post_params: {
					"atFileupload.fileSave" : "1",
					"atFileupload.filePath" : "true",
					"atFileupload.fileCatalog" : "signimage"
				},
				file_size_limit : "500 MB",
				file_types : "*.jpg;*.jpeg;*.gif;*.*",
				file_types_description : "上传文件",
				file_upload_limit : 100,
				file_queue_limit : 0,
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "btnCancel",
					uploadButtonId : "btnUpload",
					myFileListTarget : "idFileList"
				},
				debug: false,
				auto_upload:true,
		
				// Button Settings
				button_image_url : "/common/scripts/swfupload/SmallSpyGlassWithTransperancy_17x18.png",	// Relative to the SWF file
				button_width: 18,
				button_height: 18,
				button_placeholder_id : "spanButtonPlaceholder",
		
				swfupload_loaded_handler : swfUploadLoaded,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,
				queue_complete_handler : queueComplete,	// Queue plugin event
				
				// SWFObject settings
				minimum_flash_version : "9.0.28",
				swfupload_pre_load_handler : swfUploadPreLoad,
				swfupload_load_failed_handler : swfUploadLoadFailed
			};
			function __createSwtUploadData(){
				var relationId = settings.post_params["atFileupload.relationId"];
				if(relationId){}else{
					return;
				}
			    var myFileListTarget = settings.custom_settings.myFileListTarget;
			    var submitName = settings.custom_settings.submitName;
			    var ifDel = settings.custom_settings.delAuth;
			    var ifDow = settings.custom_settings.dowAuth;
				var myAjax = new Ajax("/common/attachmentAction_queryOpUserMess.action");
				myAjax.submit();
				var empid = myAjax.getValue("root/data/empid");
				var roleids = myAjax.getValue("root/data/roleids");
				
				var myAjax = new Ajax("/common/attachmentAction_queryWithDBByRelationId.action");
				myAjax.addParam("relationId", relationId);
				myAjax.submit();
			    var sfwdTCjfab = myAjax.getEntitys("/root/data/rdata");
			    for(var i=0;i<sfwdTCjfab.length;i++){
			    	var file = {};
			    	file.id = myAjax.getNodeValue(sfwdTCjfab[i],"fileId");
			    	file.name = myAjax.getNodeValue(sfwdTCjfab[i],"fileName");
			    	file.size = myAjax.getNodeValue(sfwdTCjfab[i],"fileSize");
			    	var operatorid = myAjax.getNodeValue(sfwdTCjfab[i],"operatorid");
			    	var attachmentSrcId = myAjax.getNodeValue(sfwdTCjfab[i],"attachmentSrcId");
					var result = __checkRoleFile(empid,roleids,operatorid,attachmentSrcId,ifDel,ifDow);
			    	FileProgressOfAjax(file,myFileListTarget,submitName,result[0],result[1]);
			    }
			}
			/**
			*  ifDel 0：不限制 1:仅人员  2、仅限权限字段 3、人员及权限字段  。其它不允许
			*  ifDow 0：不限制 1:仅人员  2、仅限权限字段 3、人员及权限字段  。其它不允许
			*/
			function __checkRoleFile(empid,roleids,operatorid,attachmentSrcId,ifDel,ifDow){
				var result = [];
				result[0] = false;
				result[1] = false;
				var isEmp = empid==operatorid;
				var roleidsArra = roleids.split(",");
				var attachmentSrcIdArra = attachmentSrcId.split(",");
				var isRole = false;
				for(var i=0;i<roleidsArra.length;i++){
					for(var j=0;j<attachmentSrcIdArra.length;j++){
						if(roleidsArra[i]==attachmentSrcIdArra[j]){
							isRole = true;
							if(isRole){
								break;
							}
						}
					}
					if(isRole){
						break;
					}
				}
				//是否允许删除
				if(ifDel==0){
					result[0] = true;
				}else if(ifDel==1){
					if(isEmp){
						result[0] = true;
					}
				}else if(ifDel==2){
					if(isRole){
						result[0] = true;
					}
				}else if(ifDel==3){
					if(isEmp || isRole){
						result[0] = true;
					}
				}else{
					result[0] = false;
				}
				//是否允许下载
				if(ifDow==0){
					result[1] = true;
				}else if(ifDow==1){
					if(isEmp){
						result[1] = true;
					}
				}else if(ifDow==2){
					if(isRole){
						result[1] = true;
					}
				}else if(ifDow==3){
					if(isEmp || isRole){
						result[1] = true;
					}
				}else{
					result[1] = false;
				}
				return result;
			}
			/**
			* 是否上传完成
			*/
			function __isWaitUploadQueued(){
				var ifl = settings.custom_settings.myFileListTarget;
				var ifcf = document.getElementById(ifl+"Count");
				if(ifcf.innerText<1){
					return false;
				}else{
					return true;
				}
			}
			/**
			* 删除勾选项目
			*/
			function __deleteUploadCheckBox(){
				var uploadCB = document.getElementsByName("uploadCheckBox");
				var checkArra = [];
				if(checkArra.length<1){
					alert('请至少勾选一条记录！');
					return false;
				}
				if(window.confirm("批量删除后将无法回复，是否继续！")){
					for(var i=0;i<uploadCB.length;i++){
						var vcb = uploadCB[i];
						if(vcb.checked){
							var deleteB = document.getElementById(vcb.value+"_delFile");
							deleteB.isCancel=true;
							checkArra.push(deleteB);
						}
					}
					for(var j=0;j<checkArra.length;j++){
						var deleteB = checkArra[j];
						deleteB.onclick();
					}
					return true;
				}
				return false;
			}
			/**
			*	批量下载，1、提供勾选复选 调用方式__downloadByGroup()
			*			2、提供关联实例ID 调用方式__downloadByGroup('33')
			*			3、提供组ID 调用方式__downloadByGroup(null,'33')
			*/
			function __downloadByGroup(relationId,groupId){
				var uploadCB = document.getElementsByName("uploadCheckBox");
				var checkArra = [];
				var fileIds = [];
				for(var i=0;i<uploadCB.length;i++){
					var vcb = uploadCB[i];
					if(vcb.checked){
						fileIds.push(vcb.value);
					}
				}
				var ifid = document.getElementById("ifid");
				if(fileIds.length<1){
					if(relationId || groupId){}else{
						alert('请勾选需要下载的附件');
						return;
					}
				}
				var url = "/common/attachmentAction_downloadWithDBByGroup.action?fileIds="+fileIds.join(",");
				if(relationId){
					url += "&relationId="+relationId;
				}
				if(groupId){
					url += "&groupId="+groupId;
				}
				ifid.src = url;
			}