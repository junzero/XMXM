
package com.gotop.file.action;

import com.fr.report.core.A.f;
import com.fr.third.org.apache.poi.hssf.record.formula.functions.Var;
import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.file.FileUtil;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class TFileResourceTableAction extends BaseAction {
	
	private List<TFileResourceTable> fileResourceTables;
	//下载流
	private InputStream inputStream = null;
	
	private String contentDisposition;
	private TFileResourceTable tFileResourceTable;
	private String fileName;
	private File upload;
    private String uploadFileName;
	private String uploadContentType;
	private String fileId;
	
	private String empid;
	private String empname;
	private String orgid;
	private String orgname;
	private String resourceId;
	private String resourceType;
	private String fileType;
	
    public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableService tFileResourceTableService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settFileResourceTableService(ITFileResourceTableService tFileResourceTableService) {
        this.tFileResourceTableService = tFileResourceTableService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITFileResourceTableService gettFileResourceTableService() {
        return this.tFileResourceTableService;
    }

    public List<TFileResourceTable> getFileResourceTables() {
		return fileResourceTables;
	}

	public void setFileResourceTables(List<TFileResourceTable> fileResourceTables) {
		this.fileResourceTables = fileResourceTables;
	}

	public TFileResourceTable gettFileResourceTable() {
		return tFileResourceTable;
	}

	public void settFileResourceTable(TFileResourceTable tFileResourceTable) {
		this.tFileResourceTable = tFileResourceTable;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	/**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tFileResourceTableService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tFileResourceTableService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tFileResourceTableService.queryAllDataList(combopara);
            	String dataresult = XmlConvert.getXmlListBean(combo);
            	MUO.write(dataresult);
        }
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Page page = this.getPage();
        HashMap hm = new HashMap();
        List orgs = tFileResourceTableService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    /**
     * 跳转到流程配置文件列表的界面
     * @return
     */
    public String toProcessFileUpload(){
    	this.setEmpid(String.valueOf(this.getCurrentOnlineUser().getEmpid()));
    	this.setEmpname(this.getCurrentOnlineUser().getEmpname());
    	this.setOrgid(String.valueOf(this.getCurrentOnlineUser().getOrgid()));
    	this.setOrgname(this.getCurrentOnlineUser().getOrgname());
    	return "process_file_upload_list";
    }
    
    public String queryProcessFileLists(){
    	List<TFileResourceTable> tFileResourceTables = this.tFileResourceTableService.queryProcessFileLists();
    	this.setFileResourceTables(tFileResourceTables);
    	this.page = new Page();
    	this.page.setCount(tFileResourceTables.size());
    	this.setPage(page);
    	this.setEmpid(String.valueOf(this.getCurrentOnlineUser().getEmpid()));
    	this.setEmpname(this.getCurrentOnlineUser().getEmpname());
    	this.setOrgid(String.valueOf(this.getCurrentOnlineUser().getOrgid()));
    	this.setOrgname(this.getCurrentOnlineUser().getOrgname());
    	return "process_file_upload_list";
    }
    
    /**
     * 跳转到流程配置文件上传的界面
     * @return
     */
    public String toAddProcessFile(){
    	return "process_file_upload";
    }
    
    /**
     * 流程配置文件上传
     * @return
     */
    public String addProcessFile(){
  	try {
  		String urlstr=getFileUpload();
          tFileResourceTable = new TFileResourceTable();
          tFileResourceTable.setFilePath(urlstr);
          tFileResourceTable.setFileName(fileName);
          tFileResourceTable.setResourceType("10");
          tFileResourceTableService.insert(tFileResourceTable);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "process_file_upload";
    }
    
    /**
    *
   *上传文件及返回路径 
   *修改者名字:gotop
   *修改日期:2014-3-26
   *修改内容 
   *@param:@return
   *@return:String
    * @throws FileNotFoundException 
   *@throws 
    */
   public String getFileUpload() throws FileNotFoundException{
   	if(upload!=null){
   		String path=ServletActionContext.getServletContext().getRealPath("/upload");
   		path+="\\processFile";
   		String fileName=this.uploadFileName;
   		File file= new File(path);
   		if(file==null||!file.exists()){
   			file.mkdirs();
   		}
   		String url = path + "\\"+fileName;
   		FileUtil fileUtil= new FileUtil();
   		fileUtil.fileUpload(new FileInputStream((File)this.upload), url);
   		//url="/upload/processFile/"+fileName;
   		return url;
   	}
   	return "";
   }
   
   /**
	 * 文件下载，传入文件ID /attachment/attachment_download.action?attachment.fileid= 传入
	 * attachment.fileid或者fileid做请求参数
	 */

	public String download() {
		String filePath = ServletActionContext.getRequest()
				.getParameter("filePath");
		try {
			
			fileName = URLEncoder.encode(fileName, "UTF-8");
			this.inputStream = new BufferedInputStream(new FileInputStream(
					filePath));
			contentDisposition = "filename=\"" + fileName + "\"";

		} catch (FileNotFoundException e) {
			log.error("下载文件出错:" + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			log.error("下载文件出错:" + e.getMessage());
		} finally {
		}
		return "download";
	}
	/**
	 * 下载文件
	 */
	public void downLoadFile()throws Exception{
		HttpServletResponse response=getResponse();
		if(fileId==null||"".equals(fileId.trim())){
			throw new Exception("要下载是文件编号为空！");
		}
		BufferedInputStream instream = null;
		ServletOutputStream out = response.getOutputStream();
		try{
			TFileResourceTable tfile=tFileResourceTableService.getFileResource(fileId);
			if(tfile!=null){
			String filePath=tfile.getFilePath();
			if(filePath==null || "".equals(filePath)){
				return;
			}
			log.debug("下载渲染结果路径:"+filePath);
			File file = new File(filePath);
			//System.out.println(new String(tfile.getFileName().getBytes("iso8859-1"),"UTF-8"));
			// 清空response
			response.reset();
			//设置下载文件的类型为任意类型
			response.setContentType("application/x-download");
			response.setContentType("octets/stream");  
			response.addHeader("Content-Type", "text/html; charset=utf-8");  
			  String downLoadName = new String(tfile.getFileName().getBytes("gbk"), "iso8859-1"); 
//			response.setContentType("application/zip");
			//添加下载文件的头信息。此信息在下载时会在下载面板上显示，比如：迅雷下载显示的文件名称，就是此处filiname
			response.addHeader("Content-Disposition","attachment;filename="+downLoadName); 
			//添加文件的大小信息
			response.addHeader("Content-Length", "" + file.length()); // 设置返回的文件类型 
			instream = new BufferedInputStream(new FileInputStream(file));
			byte[] b = new byte[1];
			int count;
			while((count = instream.read(b))!=-1){
				out.write(b, 0, count);
			}
			out.flush();
			}
			
		}catch (Exception e) {
			log.error("下载文件异常", e);
			e.printStackTrace();
		}finally{
			if(instream!=null){
				instream.close();
			}
			if(out!=null){
				out.close();
			}
		}
	}
	
	public void deleteFileById() throws Exception{
		Map<String, Object> map = XmlConvert.getParamsAjax();
		TFileResourceTable obj = tFileResourceTableService.getFileResource((String) map.get("fileId"));
		tFileResourceTableService.delete(obj);
		this.write("<flag>success</flag>");
	}
	
	/**
	 * 通过fileId来删除文件
	 * @return
	 */
      public String deleteFile(){
    	  String info="";
		try {
			TFileResourceTable file=new TFileResourceTable();
			file.setFileId(Long.valueOf(fileId));
			tFileResourceTableService.deleteFileToFileName(file);
			info="success";
		} catch (Exception e) {
			log.error("删除文件异常", e);
		}
		Struts2Utils.renderText(info);
		return null;
	}
	
	 /**
     * 异步加载文件
     * @return
     */
    public String queryFileList(){
    	try {//queryFileByIdandType
    		Long lid;
    		if(resourceId!=null&&!"".equals(resourceId))
    		fileResourceTables=	tFileResourceTableService.queryFileByIdandType(Long.valueOf(resourceId), resourceType,fileType);
		} catch (Exception e) {
			log.error("查询文件列表失败！！", e);
		}
		return "list1";
    }

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

    /**
     * @return the fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType the fileType to set
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    

}