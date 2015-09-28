package com.gotop.file.service.impl;

import com.eos.server.dict.DictManager;
import com.gotop.file.dao.ITFileResourceTableDAO;
import com.gotop.file.model.FileBean;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.model.TFileResourceTableExample;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.tyjg.datadictionary.dao.IDictEntryDao;
import com.gotop.tyjg.datadictionary.model.DictEntry;
import com.gotop.util.FileUploadUtil;
import com.gotop.util.file.FileUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import com.primeton.workflow.process.util.readme;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class TFileResourceTableService implements ITFileResourceTableService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TFileResourceTableService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected ITFileResourceTableDAO tFileResourceTableDAO;
    
    private IDictEntryDao dictEntryDao;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void settFileResourceTableDAO(ITFileResourceTableDAO tFileResourceTableDAO) throws Exception {
        this.tFileResourceTableDAO = tFileResourceTableDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public ITFileResourceTableDAO gettFileResourceTableDAO() throws Exception {
        return this.tFileResourceTableDAO;
    }
    
    

    public IDictEntryDao getDictEntryDao() throws Exception{
		return dictEntryDao;
	}

	public void setDictEntryDao(IDictEntryDao dictEntryDao) throws Exception{
		this.dictEntryDao = dictEntryDao;
	}

	/**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    public List queryDataGrid(HashMap map, Page page) throws Exception {
        TFileResourceTableExample example = new TFileResourceTableExample();
        TFileResourceTableExample.Criteria criteria = example.createCriteria();
        example.setOracleStart(page.getBegin());
        example.setOracleEnd(page.getBegin()+page.getLength());
        TFileResourceTable record = new TFileResourceTable();
        List list = tFileResourceTableDAO.selectByExampleAndPage(record,example,page);
        return list;
    }

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    public void update(TFileResourceTable obj) throws Exception {
        this.tFileResourceTableDAO.updateByPrimaryKeySelective(obj);
    }

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    public String insert(TFileResourceTable obj) throws Exception {
        return this.tFileResourceTableDAO.insert(obj);
    }

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    public void delete(TFileResourceTable obj) throws Exception {
        java.lang.Long key = obj.getFileId();
        this.tFileResourceTableDAO.deleteByPrimaryKey(key);
    }

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    public void updateBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TFileResourceTable tObject = (TFileResourceTable)abs.get(i);
            	this.update(tObject);
        }
    }

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    public void insertBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TFileResourceTable tObject = (TFileResourceTable)abs.get(i);
            	this.insert(tObject);
        }
    }

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    public void deleteBatch(List abs) throws Exception {
        if(abs==null){
            	return;
        }
        for(int i=0;i<abs.size();i++){
            	TFileResourceTable tObject = (TFileResourceTable)abs.get(i);
            	this.delete(tObject);
        }
    }

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    public void updateDataGrid(HashMap hmp) throws Exception {
        this.tFileResourceTableDAO.startBatch();
        List insertEntities = (List) hmp.get("insertEntities");
        List deleteEntities = (List) hmp.get("deleteEntities");
        List updateEntities = (List) hmp.get("updateEntities");
        this.updateBatch(updateEntities);
        this.insertBatch(insertEntities);
        this.deleteBatch(deleteEntities);
        this.tFileResourceTableDAO.executeBatch();
    }

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    public List queryAllDataList(HashMap map) throws Exception {
        TFileResourceTableExample example = new TFileResourceTableExample();
        TFileResourceTableExample.Criteria criteria = example.createCriteria();
        List list = tFileResourceTableDAO.selectByExample(example);
        return list;
    }

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    public List queryPageDataList(HashMap map, Page page) throws Exception {
        List list = tFileResourceTableDAO.selectByDynamic(map,page);
        return list;
    }

	@Override
	public List<TFileResourceTable> queryProcessFileLists() {
		List<TFileResourceTable> tFileResourceTables = tFileResourceTableDAO.queryProcessFileLists();
		return tFileResourceTables;
	}

	public TFileResourceTable getFileResource(String fileId) {
		return this.tFileResourceTableDAO.getFileResource(fileId);
	}

	@Override
	public List queryFileByIdandType(Long id,String resourceType,String fileType) {
		return this.tFileResourceTableDAO.queryFileByIdandType(id,resourceType,fileType);
	}

	@Override
	public void deleteFileToFileName(TFileResourceTable file) throws Exception {
		tFileResourceTableDAO.deleteFileToFileName(file);
	}

	@Override
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
   public String getFileUpload(File upload,String uploadFileName) throws FileNotFoundException{
   	if(upload!=null){
   		String path=ServletActionContext.getServletContext().getRealPath("/upload");
   		path+="\\processFile";
   		String fileName=uploadFileName;
   		File file= new File(path);
   		if(file==null||!file.exists()){
   			file.mkdirs();
   		}
   		String url = path + "\\"+fileName;
   		FileUtil fileUtil= new FileUtil();
   		fileUtil.fileUpload(new FileInputStream((File)upload), url);
   		//url="/upload/processFile/"+fileName;
   		return url;
   	}
   	return "";
   }

	/**
	 * 上传单个文件
	 */
	@Override
	public String fileUpload(FileBean tfile, File file, String filesFileName,
			MUOUserSession muo) throws Exception {
     if(filesFileName!=null&&!"".equals(filesFileName)){
    	 String suffixStr = null;
    	 String address="";
    	 address=DictManager.getDictName("ZHPT_FILE_PATH","01");
	    	Properties props=System.getProperties();
	    	System.out.println(props.getProperty("os.name"));
	    	// if(list1==null||list1.size()<1)
	    	if(address==null||"".equals(address))
    		  address=ServletActionContext.getServletContext().getRealPath("/uploadfile");
    	 else {
    		// address=list1.get(0).getDictname();//字典中的文件路径
    	    	if(props.getProperty("os.name").indexOf("Windows")>=0)
    		    	address="D:"+address;
    	 }  
    		 String uuid = UUID.randomUUID().toString();//UUID
    		 SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
    		 String fileDate=sdf.format(new Date());//时间
    		 suffixStr = filesFileName.substring(filesFileName.indexOf("."), filesFileName.length());//获取后缀名
    		 TFileResourceTable filetabel=new TFileResourceTable();
    		 filetabel.setFileName(filesFileName);
    		 filetabel.setFileReName(uuid+suffixStr);
    		 filetabel.setFilePath(address+File.separator+fileDate+File.separator+uuid+suffixStr);
    		 filetabel.setResourceId(tfile.getResourceId());
    		 filetabel.setResourceType(tfile.getResourceType());
    		 if(tfile.getFileType()!=null&&!"".equals(tfile.getFileType()))
    			 filetabel.setFileType(tfile.getFileType());
    		 else 
    			 filetabel.setFileType("0");
    		 filetabel.setCreator(muo.getEmpid());
    		 if(tfile.getNodeId()!=null&&!"".equals(tfile.getNodeId()))
    			 filetabel.setNodeId(tfile.getNodeId());
	       	 if(tfile.getNodeName()!=null&&!"".equals(tfile.getNodeName()))
	       		 filetabel.setNodeName(tfile.getNodeName());
	     	FileUploadUtil.uploadFile(uuid, fileDate, address, filesFileName, file, suffixStr);
	       	tFileResourceTableDAO.insertTFileResource(filetabel);
    	 }
		return null;
	}

	/**
	 * 上传多个文件
	 */
	@Override
	public String fileUploads(FileBean tfile, File[] file,
			String[] filesFileName, MUOUserSession muo) throws Exception {
		 if(filesFileName!=null&&!"".equals(filesFileName)){
	    	 String suffixStr = null;
	    	 String address="";
	    	// DictEntry dict=new DictEntry();
	    	// dict.setDicttypeid("ZHPT_FILE_PATH");
	    	// List<DictEntry> list1=dictEntryDao.queryDictEntryList(dict);
	    	 address=DictManager.getDictName("ZHPT_FILE_PATH","01");
	    	Properties props=System.getProperties();
	    	System.out.println(props.getProperty("os.name"));
	    	// if(list1==null||list1.size()<1)
	    	if(address==null||"".equals(address))
			     address=ServletActionContext.getServletContext().getRealPath("/uploadfile");
	    	else {
	    		 //address=list1.get(0).getDictname();//字典中的文件路径
	    	    	if(props.getProperty("os.name").indexOf("Windows")>=0)
	    		    	address="D:"+address;
	    	 }  
	    		 SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
	    		 String fileDate=sdf.format(new Date());//时间
	    		 TFileResourceTable filetabel=new TFileResourceTable();
	    		 filetabel.setResourceId(tfile.getResourceId());
	    		 filetabel.setResourceType(tfile.getResourceType());
	    		 if(tfile.getFileType()!=null&&!"".equals(tfile.getFileType()))
	    			 filetabel.setFileType(tfile.getFileType());
	    		 else 
	    			 filetabel.setFileType("0");
	    		 filetabel.setCreator(muo.getEmpid());
	    		 if(tfile.getNodeId()!=null&&!"".equals(tfile.getNodeId()))
	    			 filetabel.setNodeId(tfile.getNodeId());
		       	 if(tfile.getNodeName()!=null&&!"".equals(tfile.getNodeName()))
		       		 filetabel.setNodeName(tfile.getNodeName());
		       	 for(int i=0;i<filesFileName.length;i++){
		    		 String uuid = UUID.randomUUID().toString();//UUID
		       		 suffixStr = filesFileName[i].substring(filesFileName[i].indexOf("."), filesFileName[i].length());//获取后缀名
		       		 filetabel.setFileName(filesFileName[i]);
		       		 filetabel.setFileReName(uuid+suffixStr);
		    		 filetabel.setFilePath(address+File.separator+fileDate+File.separator+uuid+suffixStr);
		    		 FileUploadUtil.uploadFile(uuid, fileDate, address, filesFileName[i], file[i], suffixStr);
				     tFileResourceTableDAO.insertTFileResource(filetabel);
		       	 }
	    	 }
			return null;
	}
	
}
