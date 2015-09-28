// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   FileTag.java

package com.eos.web.taglib.html;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.StringUtils;

import com.eos.web.exception.EOSTagExceptionUtil;
import com.eos.web.taglib.basic.BaseTagSupport;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;

// Referenced classes of package com.eos.web.taglib.html:
//            BaseInputTag

public class SFileTag extends BaseTagSupport
{
	private String typesDescription;
	private String attachmentSrcId;
	private String attachmentSrcCd;
	private String autoQueryData;
	private String fileCatalog;
	private String uploadLimit;
	private String autoConfirm;
	private String queueLimit;
	private String relationId;
	private String autoUpload;
	private String sizeLimit;
	private String fileSave;
	private String groupId;
	private String delAuth;
	private String dowAuth;
	private String height;
	private String width;
	private String types;
	private String name;
	private String id;
    public SFileTag()
    {
    }

    public void initAttributes() throws JspException{
        super.initAttributes();
        typesDescription = XpathUtil.getStringByXpathSupport(getRootObj(), typesDescription);
        attachmentSrcId = XpathUtil.getStringByXpathSupport(getRootObj(), attachmentSrcId);
        attachmentSrcCd = XpathUtil.getStringByXpathSupport(getRootObj(), attachmentSrcCd);
        autoQueryData = XpathUtil.getStringByXpathSupport(getRootObj(), autoQueryData);
        fileCatalog = XpathUtil.getStringByXpathSupport(getRootObj(), fileCatalog);
        uploadLimit = XpathUtil.getStringByXpathSupport(getRootObj(), uploadLimit);
        autoConfirm = XpathUtil.getStringByXpathSupport(getRootObj(), autoConfirm);
        queueLimit = XpathUtil.getStringByXpathSupport(getRootObj(), queueLimit);
        relationId = XpathUtil.getStringByXpathSupport(getRootObj(), relationId);
        autoUpload = XpathUtil.getStringByXpathSupport(getRootObj(), autoUpload);
        sizeLimit = XpathUtil.getStringByXpathSupport(getRootObj(), sizeLimit);
        fileSave = XpathUtil.getStringByXpathSupport(getRootObj(), fileSave);
        groupId = XpathUtil.getStringByXpathSupport(getRootObj(), groupId);
        delAuth = XpathUtil.getStringByXpathSupport(getRootObj(), delAuth);
        dowAuth = XpathUtil.getStringByXpathSupport(getRootObj(), dowAuth);
        height = XpathUtil.getStringByXpathSupport(getRootObj(), height);
        width = XpathUtil.getStringByXpathSupport(getRootObj(), width);
        types = XpathUtil.getStringByXpathSupport(getRootObj(), types);
        name = XpathUtil.getStringByXpathSupport(getRootObj(), name);
        id = XpathUtil.getStringByXpathSupport(getRootObj(), id);
    }

    public int doStartTag()
        throws JspException
    {
        initAttributes();
        try
        {
            prepareAttributes();
        }
        catch(Exception e)
        {
            throw new JspException(e);
        }
        return 1;
    }

    protected void prepareAttributes() throws JspException
    {
    	StringBuilder buff = new StringBuilder();
    	try{
    		if(StringUtils.isNotBlank(name)){
    			baseHtml(buff);
    			buff.append("		<script type=\"text/javascript\">\n");
    			if(StringUtils.isNotBlank(relationId)){
    				buff.append("			settings.post_params[\"atFileupload.relationId\"]=\""+relationId+"\";\n");
    			}
    			if(StringUtils.isNotBlank(groupId)){
    				buff.append("			settings.post_params[\"atFileupload.groupId\"]=\""+groupId+"\";\n");
    			}
    			if(StringUtils.isNotBlank(attachmentSrcId)){
    				buff.append("			settings.post_params[\"atFileupload.attachmentSrcId\"]=\""+attachmentSrcId+"\";\n");
    			}
    			if(StringUtils.isNotBlank(attachmentSrcCd)){
    				buff.append("			settings.post_params[\"atFileupload.attachmentSrcCd\"]=\""+attachmentSrcCd+"\";\n");
    			}
    			if(StringUtils.isNotBlank(fileSave)){
    				buff.append("			settings.post_params[\"atFileupload.fileSave\"]=\""+fileSave+"\";\n");
    			}
    			if(StringUtils.isNotBlank(fileCatalog)){
    				buff.append("			settings.post_params[\"atFileupload.fileCatalog\"]=\""+fileCatalog+"\";\n");
    			}
    			
    			if("false".equals(autoConfirm)){
    				buff.append("			settings.post_params[\"atFileupload.filePath\"]=\""+autoConfirm+"\";\n");
    			}
    			if(StringUtils.isNotBlank(uploadLimit)){
    				buff.append("			settings.file_upload_limit=\""+uploadLimit+"\";\n");
    			}
    			if(StringUtils.isNotBlank(queueLimit)){
    				buff.append("			settings.file_queue_limit=\""+queueLimit+"\";\n");
    			}
    			if(StringUtils.isNotBlank(autoUpload)){
    				buff.append("			settings.auto_upload="+autoUpload+";\n");
    			}
    			if(StringUtils.isNotBlank(typesDescription)){
    				buff.append("			settings.file_types_description=\""+typesDescription+"\";\n");
    			}
    			if(StringUtils.isNotBlank(types)){
    				buff.append("			settings.file_types=\""+types+"\";\n");
    			}
    			if(StringUtils.isNotBlank(sizeLimit)){
    				buff.append("			settings.file_size_limit=\""+sizeLimit+"\";\n");
    			}
    			if(StringUtils.isNotBlank(delAuth)){
    				buff.append("			settings.custom_settings.delAuth =\""+delAuth+"\";\n");
    			}else{
    				buff.append("			settings.custom_settings.delAuth =\"1\";\n");
    			}
    			if(StringUtils.isNotBlank(dowAuth)){
    				buff.append("			settings.custom_settings.dowAuth =\""+dowAuth+"\";\n");
    			}else{
    				buff.append("			settings.custom_settings.dowAuth =\"0\";\n");
    			}
    			if(StringUtils.isNotBlank(name)){
    				buff.append("			settings.custom_settings.submitName =\""+name+"\";\n");
    			}
    			if(StringUtils.isNotBlank(id)){
    				buff.append("			var "+id+" = ");
    			}else{
    				buff.append("			var swfu = ");
    			}
    			buff.append("new SWFUpload(settings);\n");
    			if(!"false".equals(autoQueryData)){
    				buff.append("__createSwtUploadData();\n");
    			}
    			buff.append("		</script>\n");
    		}else{
    			EOSTagExceptionUtil.throwEOSTagException("990000002", "h:sfile name 不能为空 !");
    		}
    	}catch(Exception e){
            EOSTagExceptionUtil.throwEOSTagException("990000001", "h:sfile object does not object !");
        }
    	ResponseUtil.write(pageContext, buff.toString());
    }
    private void baseHtml(StringBuilder buff){
		buff.append("		<div id='content'>\n");
		buff.append("			<table width='100%' cellspacing='0' cellpadding='0' border='0'>\n");
		buff.append("				<tr>\n");
		buff.append("					<td>\n");
		buff.append("						<span id='spanButtonPlaceholder'></span>\n");
		String display = "					<span";
		if(!"false".equals(autoUpload)){
			 display += " style='display: none;'";
		}
		display+=">\n";
		buff.append(display);
		buff.append("						<input id='btnUpload' type='button' value='提交上传' class='button'/>");
		buff.append("						<input id='btnCancel' type='button' value='取消全部' disabled='disabled' class='button'/>");
		buff.append("					</span>\n");
		buff.append("						等待上传<span id='idFileListCount'>0</span> 个 ，");
		buff.append("						成功上传<span id='idFileListSuccessUploadCount'>0</span> 个，");
		buff.append("						成功删除<span id='idFileListDelCount'>0</span> 个");
		buff.append("					</td>\n");
		buff.append("				</tr>\n");
		buff.append("			</table>\n");
		String dtyle = "";
		if(StringUtils.isNotBlank(height)){
			dtyle += "height: "+height+"; ";
		}else{
			dtyle += "height: 100%; ";
		}
		if(StringUtils.isNotBlank(width)){
			dtyle += "width: "+width+"; ";
		}else{
			dtyle += "width: 100%; ";
		}
		buff.append("			<div style='"+dtyle+"overflow: auto;' align='center'>\n");
		buff.append("				<table id='idFileList' width='100%' class='EOS_table' border='0'>\n");
		buff.append("					<tr>\n");
		buff.append("						<th width='4%'>\n");
		buff.append("							<nobr>&nbsp;</nobr>\n");
		buff.append("						</th>\n");
		buff.append("						<th width='55%' align='center'>\n");
		buff.append("							<nobr>文件名</nobr>\n");
		buff.append("						</th>\n");
		buff.append("						<th width='8%' align='center'>\n");
		buff.append("							<nobr>文件大小</nobr>\n");
		buff.append("						</th>\n");
		buff.append("						<th width='25%' align='center'>\n");
		buff.append("							<nobr>状态</nobr>\n");
		buff.append("						</th>\n");
		buff.append("						<th width='8%' align='center'>\n");
		buff.append("							<nobr>操作</nobr>\n");
		buff.append("						</th>\n");
		buff.append("					</tr>\n");
		buff.append("				</table>\n");
		buff.append("			</div>\n");
		
		buff.append("			<div id='divSWFUploadUI' style='visibility: hidden;'></div>");
		buff.append("			<noscript style='display: none; margin: 10px 25px; padding: 10px 15px;'>");
		buff.append("				很抱歉，附件上传界面无法载入，请将浏览器设置成支持JavaScript。");
		buff.append("			</noscript>");
		buff.append("			<div id='divLoadingContent' class='content'>");
		buff.append("				附件上传界面正在载入，请稍后...");
		buff.append("			</div>");
		buff.append("			<div id='divLongLoading' class='content'>");
		buff.append("				附件上传界面载入失败，请确保浏览器已经开启对JavaScript的支持，并且已经安装可以工作的Flash插件版本。");
		buff.append("			</div>");
		buff.append("			<div id='divAlternateContent' class='content'>");
		buff.append("				很抱歉，附件上传界面无法载入，请安装或者升级您的Flash插件。 请访问：");
		buff.append("				<a href='http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash'");
		buff.append("					target='_blank'>Adobe网站</a> 获取最新的Flash插件。");
		buff.append("			</div>");
		buff.append("		</div>");
    }
    public void release()
    {
        super.release();
        typesDescription = null;
        attachmentSrcId = null;
        attachmentSrcCd = null;
        autoQueryData = null;
        fileCatalog = null;
        uploadLimit = null;
        autoConfirm = null;
        queueLimit = null;
        relationId = null;
        autoUpload = null;
        sizeLimit = null;
        fileSave = null;
        groupId = null;
        delAuth = null;
        dowAuth = null;
        height = null;
        width = null;
        types = null;
        name = null;
        id = null;
    }

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAttachmentSrcId() {
		return attachmentSrcId;
	}

	public void setAttachmentSrcId(String attachmentSrcId) {
		this.attachmentSrcId = attachmentSrcId;
	}

	public String getAttachmentSrcCd() {
		return attachmentSrcCd;
	}

	public void setAttachmentSrcCd(String attachmentSrcCd) {
		this.attachmentSrcCd = attachmentSrcCd;
	}

	public String getFileSave() {
		return fileSave;
	}

	public void setFileSave(String fileSave) {
		this.fileSave = fileSave;
	}

	public String getFileCatalog() {
		return fileCatalog;
	}

	public void setFileCatalog(String fileCatalog) {
		this.fileCatalog = fileCatalog;
	}

	public String getUploadLimit() {
		return uploadLimit;
	}

	public void setUploadLimit(String uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	public String getQueueLimit() {
		return queueLimit;
	}

	public void setQueueLimit(String queueLimit) {
		this.queueLimit = queueLimit;
	}

	public String getAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(String autoUpload) {
		this.autoUpload = autoUpload;
	}

	public String getTypesDescription() {
		return typesDescription;
	}

	public void setTypesDescription(String typesDescription) {
		this.typesDescription = typesDescription;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getSizeLimit() {
		return sizeLimit;
	}

	public void setSizeLimit(String sizeLimit) {
		this.sizeLimit = sizeLimit;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAutoQueryData() {
		return autoQueryData;
	}

	public void setAutoQueryData(String autoQueryData) {
		this.autoQueryData = autoQueryData;
	}

	public String getDelAuth() {
		return delAuth;
	}

	public void setDelAuth(String delAuth) {
		this.delAuth = delAuth;
	}

	public String getDowAuth() {
		return dowAuth;
	}

	public void setDowAuth(String dowAuth) {
		this.dowAuth = dowAuth;
	}

	public String getAutoConfirm() {
		return autoConfirm;
	}

	public void setAutoConfirm(String autoConfirm) {
		this.autoConfirm = autoConfirm;
	}
}
