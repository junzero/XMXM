package com.gotop.tyjg.common.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.tyjg.common.model.AttachmentBean;
import com.gotop.tyjg.common.service.IAttachmentService;
import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.util.Global;
import com.gotop.util.XmlConvert;
import com.gotop.util.security.ForUtil;
import com.gotop.vo.system.MUOUserSession;

public class AttachmentAction extends BaseAction {
	/**
	 * 通过spring注入的Service对象.
	 * @abatorgenerated
	 */
	protected IAttachmentService attachmentService;
	//下载流
	private InputStream inputStream = null;

	private String contentDisposition;
	private String contentType;
	private static final long serialVersionUID = -793643462070460211L;
	private AttachmentBean attachment;
	private AtFileupload atFileupload;

	// 文件接收
	private List<File> uploadfile;
	private List<String> uploadfileFileName;
	private List<String> uploadfileContentType;
	/**
	 * 通过spring注入Service的set类.
	 * 
	 * @abatorgenerated
	 */
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	/**
	 * 通过spring注入Service的get类.
	 * 
	 * @abatorgenerated
	 */
	public IAttachmentService getAttachmentService() {
		return this.attachmentService;
	}

	/**
	 * @return the attachment
	 */
	public AttachmentBean getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(AttachmentBean attachment) {
		this.attachment = attachment;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 文件下载，传入文件ID /attachment/attachment_download.action?attachment.fileid= 传入
	 * attachment.fileid或者fileid做请求参数
	 */

	public String download() {

		String fileid = ServletActionContext.getRequest()
				.getParameter("fileid");
		if (fileid == null || "".equals(fileid)) {
			fileid = attachment.getfileid();
		}
		try {
			HashMap<String, String> resultMap = this.getAttachmentService()
					.downloadAttachmentInweb(fileid);
			if (resultMap == null) {
				log.error("文件不存在:fileid=");
			}
			String fileName = null;
			String filePath = null;
			if (resultMap != null && !resultMap.isEmpty()) {
				fileName = (String) resultMap.get("fileName");
				filePath = (String) resultMap.get("filePath");
			}
			// filePath=ServletActionContext.getServletContext().getRealPath(
			// "/")+filePath;
			fileName = URLEncoder.encode(fileName, "UTF-8");
			this.inputStream = new BufferedInputStream(ForUtil.createFileInputStream(
					filePath));
			// resultMap.put("inputStream", inputStream);
			// this.inputStream = (InputStream) resultMap.get("inputStream");
			// =inputStream1;
			contentDisposition = "filename=\"" + fileName + "\"";

		} catch (FileNotFoundException e) {
			log.error("下载文件出错:" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			log.error("下载文件出错:" + e.getMessage());
		} finally {
			try {
				if (this.inputStream != null) {
					this.inputStream.close();
				}
			} catch (IOException e) {
				log.error("inputStream.close()" + e.getMessage());
			}
		}
		return "download";
	}

	/**
	 * 删除单个附件 /attachment/attachment_delete.action 传入 attachment.fileId做请求参数
	 * 
	 * @return
	 * @throws IOException @
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */
	public String delete() throws IOException {
		StringBuffer buffer = new StringBuffer(100);
		// 将AJAX请求的XML信息转成MAP
		HashMap<String, String> hmp = XmlConvert.getParamsAjax();
		try {
			buffer.append("<root><data><rtun>");

			String fileid = hmp.get("fileid");
			if (fileid == null || "".equals(fileid)) {
				fileid = attachment.getfileid();
			}
			if (fileid == null) {
				buffer.append("notExist");
			} else {
				this.getAttachmentService().delAttachmentInfo(fileid);
				buffer.append("success");
			}
		} catch (RuntimeException e) {
			log.error(e);
			buffer.append("fail");
			log.error("[附件删除失败！]", e);

		} finally {
			buffer.append("</rtun></data></root>");
			ServletActionContext.getResponse().getWriter().write(
					buffer.toString());
		}
		return null;
	}

	public String deleteSingleAttachment() throws IOException {

		StringBuffer buffer = new StringBuffer(100);
		try {
			buffer.append("<root><data>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String path = hmp.get("path");
			String src_cd = hmp.get("srccd");
			boolean falg = this.getAttachmentService().deleteSingleAttachement(
					path, src_cd);
			buffer.append("<isSucc>");
			if (falg) {
				buffer.append("success");
			} else {
				buffer.append("fail");
			}
			buffer.append("</isSucc>");

		} catch (SQLException e) {
			buffer.append("<isSucc>");
			buffer.append("fail");
			buffer.append("</isSucc>");
			log.error("[删除附件失败]", e);
		} finally {
			buffer.append("</data></root>");
			this.getResponse().getWriter().print(buffer.toString());
		}
		return null;
	}

	/**
	 * 
	 * 上传多个附件信息. 访问地址/common/attachmentAction_upload.action 传入 attachment.srcId和
	 * attachment.srcCd做请求参数 参数attachment.srcCd ,需要在
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20    叶承坤    新建
	 * </pre>
	 * 
	 * @throws IOException
	 */
	public String upload() throws IOException {
		StringBuffer buffer = new StringBuffer(100);
		try {
			buffer.append("<root><data><rtun>");
			if (attachment.getSrcId() == null) {
				buffer.append("notSrcId");
			} else if (uploadfile == null || uploadfile.size() == 0) {
				buffer.append("notFile");
			} else {
				this.getAttachmentService().uploadAttachment(
						attachment.getSrcId(), attachment.getSrcCd(),
						uploadfileFileName, uploadfile);
				buffer.append("success");
			}
		} catch (IOException e) {
			buffer.append("fail");
			log.error("[附件上传失败！]", e);

		} finally {
			buffer.append("</rtun></data></root>");
			ServletActionContext.getResponse().getWriter().write(
					buffer.toString());
		}
		return null;
	}

	/**
	 * 
	 * 上传多个附件信息. 但不入库，访问地址/common/attachmentAction_uploadWithOutDb.action 传入
	 * attachment.srcCd做请求参数 参数attachment.srcCd ,需要在
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20    叶承坤    新建
	 * </pre>
	 */
	public String uploadWithOutDb() {
		HashMap resultMap = new HashMap();
		List<HashMap<String, String>> fileList = null;
		try {
			resultMap = this.getAttachmentService().uploadAttachmentWithOutDB(
					attachment.getSrcCd(), uploadfileFileName, uploadfile);
			fileList = (List) resultMap.get("fileList");
		} catch (IOException e) {
			resultMap.put("isSucc", "fail");
			log.error("[附件上传失败！]", e);

		} finally {

			ServletActionContext.getRequest().setAttribute("resultMap",
					resultMap);
			ServletActionContext.getRequest()
					.setAttribute("fileList", fileList);
		}
		return "uploads";
	}
	/**
	 * @return the uploadfile
	 */
	public List<File> getUploadfile() {
		return uploadfile;
	}

	/**
	 * @param uploadfile
	 *            the uploadfile to set
	 */
	public void setUploadfile(List<File> uploadfile) {
		this.uploadfile = uploadfile;
	}

	/**
	 * @return the uploadfileFileName
	 */
	public List<String> getUploadfileFileName() {
		return uploadfileFileName;
	}

	/**
	 * @param uploadfileFileName
	 *            the uploadfileFileName to set
	 */
	public void setUploadfileFileName(List<String> uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}

	/**
	 * @return the uploadfileContentType
	 */
	public List<String> getUploadfileContentType() {
		return uploadfileContentType;
	}

	/**
	 * @param uploadfileContentType
	 *            the uploadfileContentType to set
	 */
	public void setUploadfileContentType(List<String> uploadfileContentType) {
		this.uploadfileContentType = uploadfileContentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public AtFileupload getAtFileupload() {
		return atFileupload;
	}

	public void setAtFileupload(AtFileupload atFileupload) {
		this.atFileupload = atFileupload;
	}
	/**
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 */
	public void uploadWithDB() {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		List<String> fileList = null;
		try {
			MUOUserSession muous = this.getCurrentOnlineUser();
			if(atFileupload==null){
				atFileupload = new AtFileupload();
			}
			atFileupload.setOperatorid(muous.getEmpid());
			atFileupload.setOperatorname(muous.getEmpname());
			atFileupload.setFileNewName(this.getSession().getId());
			resultMap = this.getAttachmentService().addUploadWithDB(atFileupload, uploadfileFileName, uploadfile,uploadfileContentType);
			fileList = (List<String>) resultMap.get("fileList");
			StringBuffer fileId = new StringBuffer("");
			for (int i = 0; i < fileList.size(); i++) {
				fileId.append(fileList.get(i));
				if(i!=fileList.size()-1){
					fileId.append(",");
				}
			}
			this.write("successed,"+atFileupload.getFileName()+","+fileId);
		} catch (Exception e) {
			log.error("[附件上传失败！]", e);
		}
		return;
	}
	/**
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public void deleteWithDB() throws Exception {
		HttpServletRequest request = this.getRequest();
		String fileIds = request.getParameter("fileIds");
		if(StringUtils.isBlank(fileIds)){
			String ajax = request.getParameter("ajax");
			if(StringUtils.isNotBlank(ajax)){
				HashMap param = XmlConvert.getParamsAjax();
				fileIds = (String)param.get("fileIds");
			}
		}
		boolean result = attachmentService.deleteUploadWithDB(atFileupload, fileIds);
		this.write("<result>1</result>");
	}
	/**
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public void confirmWithDB() throws Exception {
		HttpServletRequest request = this.getRequest();
		String fileIds = request.getParameter("fileIds");
		if(StringUtils.isBlank(fileIds)){
			String ajax = request.getParameter("ajax");
			if(StringUtils.isBlank(ajax)){
				HashMap param = XmlConvert.getParamsAjax();
				fileIds = (String)param.get("fileIds");
			}
		}
		boolean result = attachmentService.confirmUploadWithDB(atFileupload, fileIds);
		this.write("操作结果："+result);
	}
	/**
	 * Constant.Attachment中定义，还要在数据字典IMP_FILEDIR_PATH中定义路径
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public String downloadWithDB() throws Exception {
		HttpServletRequest request = this.getRequest();
		String fileIds = request.getParameter("fileIds");
		if(StringUtils.isBlank(fileIds)){
			String ajax = request.getParameter("ajax");
			if(StringUtils.isBlank(ajax)){
				HashMap param = XmlConvert.getParamsAjax();
				fileIds = (String)param.get("fileIds");
			}
		}
		atFileupload = attachmentService.downloadWithDB(atFileupload, fileIds);
		if(atFileupload==null){
			this.write("----下载失败--");
		}else{
			String fileName = null;
			String filePath = null;
			if("1".equals(atFileupload.getFileSave())){
				String filePathStr = atFileupload.getFilePath();
				if(filePathStr.endsWith("/") || filePathStr.endsWith("\\")){
					filePath = filePathStr+atFileupload.getFileNewName();
				}else{
					filePath = filePathStr+File.separator+atFileupload.getFileNewName();
				}
				this.inputStream = new BufferedInputStream(ForUtil.createFileInputStream(filePath));
			}else{
				this.inputStream = new ByteArrayInputStream(atFileupload.getContent());
			}
			contentType = atFileupload.getFileType();
			fileName = atFileupload.getFileName();
			fileName = URLEncoder.encode(fileName, "UTF-8");
			contentDisposition = "filename=\"" + fileName + "\"";
		}
		return "download";
	}
	/**
	 * 查询全部上传记录
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public String queryWithDB() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HashMap hm = new HashMap();
		if(attachment!=null){
			hm.put("fileTimeStart", attachment.getFileTimeStart());
			hm.put("fileTimeEnd", attachment.getFileTimeEnd());
			hm.put("fileSizeStart", attachment.getFileSizeStart());
			hm.put("fileSizeEnd", attachment.getFileSizeEnd());
		}
		if(atFileupload!=null){
			hm.put("operatorname", atFileupload.getOperatorname());
			hm.put("fileCatalog", atFileupload.getFileCatalog());
			hm.put("fileSave", atFileupload.getFileSave());
			hm.put("relationId", atFileupload.getRelationId());
		}
//		hm.put("fileCatalog", atFileupload.getFileCatalog());
		List data = attachmentService.queryWithDBBeanByMapAndPage(hm, this.getPage());
		request.setAttribute("uploadFiles", data);
		return "fileupload_manager";
	}
	/**
	 * 通过关联ID查询一条记录
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public void queryWithDBByRelationId() throws Exception {
		HashMap param = XmlConvert.getParamsAjax();
		AtFileupload atFileupload = new AtFileupload();
		atFileupload.setRelationId((String)param.get("relationId"));
		List data = attachmentService.queryWithDB(atFileupload);
        HashMap result = new HashMap();
        result.put("rdata", data);
        String xmlStr = XmlConvert.getXmlListBean(result);
        MUO.write(xmlStr);
		return;
	}
	/**
	 * 获取用户信息，以供判断是否有那些权限 该附件。
	 * @throws Exception
	 */
	public void queryOpUserMess() throws Exception {
		MUOUserSession muous = this.getCurrentOnlineUser();
		Long empid = muous.getEmpid();
		String[] roleid = muous.getRoleid();
		StringBuffer result = new StringBuffer();
		result.append("<?xml version='1.0' encoding='UTF-8'?><root><data>");
		result.append("<empid>"+empid+"</empid>");
		result.append("<roleids>");
		for (int i = 0; i < roleid.length; i++) {
			result.append(roleid[i]);
			if(i!=roleid.length-1){
				result.append(",");
			}
		}
		result.append("</roleids></data></root>");
//		log.info(result);
		this.write(result.toString());
	}
	/**
	 * 上传文件
	 * @return
	 */
	public String uploadFile(){
		if(atFileupload==null){
			atFileupload = new AtFileupload();
			atFileupload.setOperatorname(this.getCurrentOnlineUser().getEmpname());
			atFileupload.setFileTime(new Date());
		}
		return "fileupload";
	}
	/**
	 * 上传文件
	 * @return
	 */
	public String fileuploadUi(){
		if(atFileupload==null){
			atFileupload = new AtFileupload();
			atFileupload.setOperatorname(this.getCurrentOnlineUser().getEmpname());
			atFileupload.setFileTime(new Date());
		}
		return "fileupload_ui";
	}
	/**
	 * 查寻单条记录以供修改
	 * @return
	 * @throws Exception 
	 */
	public String fileuploadUpdate() throws Exception{
		String fileId = atFileupload.getFileId();
		atFileupload = attachmentService.queryWithDBBeanByID(fileId);
		return "fileupload_update";
	}
	/**
	 * 修改单条记录
	 * @return
	 * @throws Exception 
	 */
	public void updateFileupload() throws Exception{
		HashMap param = XmlConvert.getParamsAjax();
		String atFileupload_fileId = (String)param.get("atFileupload.fileId");
		String atFileupload_attachmentSrcId = (String)param.get("atFileupload.attachmentSrcId");
		String atFileupload_fileName = (String)param.get("atFileupload.fileName");
		String atFileupload_attachmentSrcCd = (String)param.get("atFileupload.attachmentSrcCd");
		String atFileupload_groupId = (String)param.get("atFileupload.groupId");
		String atFileupload_relationId = (String)param.get("atFileupload.relationId");
		String atFileupload_fileCatalog = (String)param.get("atFileupload.fileCatalog");
		atFileupload = new AtFileupload();
		atFileupload.setFileId(atFileupload_fileId);
		atFileupload.setAttachmentSrcId(atFileupload_attachmentSrcId);
		atFileupload.setFileName(atFileupload_fileName);
		atFileupload.setAttachmentSrcCd(atFileupload_attachmentSrcCd);
		atFileupload.setGroupId(atFileupload_groupId);
		atFileupload.setRelationId(atFileupload_relationId);
		atFileupload.setFileCatalog(atFileupload_fileCatalog);
		
		this.attachmentService.updateFileupload(atFileupload);
		this.write("<result>1</result>");
	}
	/**
	 * 提交批量下载功能，将压缩为rar包
	 * 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-6-20       lz       修改
	 * </pre>
	 * @throws Exception 
	 */
	public String downloadWithDBByGroup() throws Exception {
		HttpServletRequest request = this.getRequest();
		String sessionId = this.getSession().getId();
		String fileIds = request.getParameter("fileIds");
		String relationId = request.getParameter("relationId");
		String groupId = request.getParameter("groupId");
		String dowName = request.getParameter("dowName");

		if(StringUtils.isBlank(fileIds) && StringUtils.isBlank(relationId)&& StringUtils.isBlank(groupId)){
			this.write("----下载失败---参数不能为空-");
			return null;
		}
		
		File fileDow = attachmentService.downloadWithDBByGroup(fileIds,relationId,groupId,sessionId);
		log.info("-----"+fileDow.getAbsoluteFile());
		if(fileDow==null){
			this.write("----下载失败--");
			return null;
		}else{
			String fileName = null;
			this.inputStream = new BufferedInputStream(ForUtil.createFileInputStream(fileDow));
			this.contentType = "";
			if(StringUtils.isBlank(dowName)){
				dowName = "批量下载.zip";
			}
			fileName = URLEncoder.encode(dowName, "UTF-8");
			this.contentDisposition = "filename=\"" + fileName + "\"";
		}
		return "download";
	}
	/**
	 * 获取令牌
	 */
	public void getTokenUuid(){
		String uuid = UUID.randomUUID().toString();
		HttpSession session = this.getSession();
		Set<String> tokenUuid = (Set<String>)session.getAttribute(Global.LOGON_USER_Token);
		if(tokenUuid==null){
			tokenUuid = new HashSet<String>();
			session.setAttribute(Global.LOGON_USER_Token, tokenUuid);
		}
		tokenUuid.add(uuid);
		this.write(uuid);
	}
	/**
	 * 删除令牌
	 */
	public void rmTokenUuid(){
		HttpSession session = this.getSession();
		Set<String> tokenUuid = (Set<String>)session.getAttribute(Global.LOGON_USER_Token);
		if(tokenUuid!=null){
			String tokenId = this.getRequest().getParameter("tokenId");
			tokenUuid.remove(tokenId);
		}
	}
}