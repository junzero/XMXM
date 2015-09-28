package com.gotop.tyjg.common.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

import com.eos.foundation.common.utils.FileUtil;
import com.gotop.tyjg.common.dao.IAttachmentDAO;
import com.gotop.tyjg.common.service.IAttachmentService;
import com.gotop.tyjg.stable.dao.IAtFileuploadDAO;
import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.tyjg.stable.model.AtFileuploadExample;
import com.gotop.util.security.ForUtil;
import com.primeton.utils.Page;

public class AttachmentService implements IAttachmentService {
    /**
	 * @abatorgenerated
	 */
	protected Logger log = Logger.getLogger(AttachmentService.class);
	/**
	 * 通过spring注入的DAO对象.
	 * @abatorgenerated
	 */
	protected IAttachmentDAO attachmentDAO;

	protected IAtFileuploadDAO atFileuploadDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setAttachmentDAO(IAttachmentDAO attachmentDAO) throws Exception {
        this.attachmentDAO = attachmentDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IAttachmentDAO getAttachmentDAO(){
        return this.attachmentDAO;
    }

	public IAtFileuploadDAO getAtFileuploadDAO() {
		return atFileuploadDAO;
	}

	public void setAtFileuploadDAO(IAtFileuploadDAO atFileuploadDAO) {
		this.atFileuploadDAO = atFileuploadDAO;
	}
	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#copyAttachmentInfo(java.util.List, java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap copyAttachmentInfo(List<String> arrachmentId, String srcId) {
		return this.getAttachmentDAO().copyAttachmentInfo(arrachmentId, srcId);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#delAttachmentInfo(java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap delAttachmentInfo(String id) {
		return this.getAttachmentDAO().delAttachmentInfo(id);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#delAttachmentList(java.lang.String, java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap delAttachmentList(String srcId, String srcCd) {
		return this.getAttachmentDAO().delAttachmentList(srcId, srcCd);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#delAttachmentList(java.util.List, java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap delAttachmentList(List<String> srcIdList, String srcCd) {
		return this.getAttachmentDAO().delAttachmentList(srcIdList, srcCd);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#downloadAttachment(java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap downloadAttachment(String id) throws FileNotFoundException {
		return this.getAttachmentDAO().downloadAttachment(id);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#downloadAttachmentInweb(java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap downloadAttachmentInweb(String id)
			throws FileNotFoundException {
		return this.getAttachmentDAO().downloadAttachmentInweb(id);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#getAttachmentLimit(java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap getAttachmentLimit(String srcCd) {
		return this.getAttachmentDAO().getAttachmentLimit(srcCd);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#getAttachmentList(java.lang.String, java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public List getAttachmentList(String srcId, String srcCd) {
		return this.getAttachmentDAO().getAttachmentList(srcId,srcCd);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#moveAttachmentInfo(java.lang.String, java.lang.String, java.lang.String)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap moveAttachmentInfo(String id, String srcId, String srcCd) {
		return this.getAttachmentDAO().moveAttachmentInfo(id, srcId, srcCd);
	}

	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#uploadAttachment(java.lang.String, java.lang.String, java.lang.String, java.io.InputStream)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap uploadAttachment(String srcId, String srcCd,
			String fileName, InputStream inputStream) throws IOException {
		return this.getAttachmentDAO().uploadAttachment(srcId, srcCd, fileName,inputStream);
	}
/* (non-Javadoc)
 * @see com.gotop.testmanager.common.service.IAttachmentService#uploadAttachmentWithOutDB(java.lang.String, java.util.List, java.util.List)
 *
 * <pre>
 * 修改日期        修改人    修改原因
 * 2012-7-13    叶承坤    新建
 * </pre>
 */

@Override
public HashMap uploadAttachmentWithOutDB(String srcCd,
		List<String> uploadfileFileName, List<File> uploadfile)
		throws IOException {
	return this.getAttachmentDAO().uploadAttachmentWithOutDB(srcCd, uploadfileFileName, uploadfile);
}
	/* (non-Javadoc)
	 * @see com.gotop.testmanager.common.service.IAttachmentService#uploadAttachment(java.lang.String, java.lang.String, java.util.List, java.util.List)
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-12    叶承坤    新建
	 * </pre>
	 */
	
	@Override
	public HashMap uploadAttachment(String srcId, String srcCd,
			List<String> uploadfileFileName, List<File> uploadfile)
			throws IOException {
		return this.getAttachmentDAO().uploadAttachment(srcId, srcCd, uploadfileFileName,uploadfile);
	}
	
	/**
	 * 附件根据文件所在路径与文件名删除
	 * @param path 文件路径（包含文件名） 
	 * @param src_cd 模块编号
	 * @return true:成功  false:失败
	 * @ throws SQLException
	 */
	public boolean deleteSingleAttachement(String path,String src_cd)  throws SQLException{
		boolean falg = false;
		String filePath = this.getAttachmentDAO().returnAttachementAllPath(path, src_cd);
		File file = ForUtil.createFile(filePath);
		if(file.exists()){
			falg = file.delete();
		}else{
			//文件不存在直接返回true
			falg = true;
		}
		return falg;
	}
	
	/* 
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-13    叶承坤    新建
	 * </pre>
	 */

	@Override
	public HashMap<String,Object> addUploadWithDB(AtFileupload atFileupload,
			List<String> uploadfileFileName, List<File> uploadfile,List<String> uploadfileContentType)
			throws Exception {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		List<String> fileList = new ArrayList<String>();
		String fileDatePath = attachmentDAO.getFileDatePath();
		resultMap.put("fileList", fileList);
		atFileuploadDAO.startBatch();
		for (int i = 0; i < uploadfile.size(); i++) {
			File tempFile = uploadfile.get(i);
			AtFileupload fileupload =new AtFileupload();
			fileupload.setFileCatalog(atFileupload.getFileCatalog());
			fileupload.setFileName(uploadfileFileName.get(i));
			fileupload.setAbsolutepath(tempFile.getAbsolutePath());
			fileupload.setFileSize(tempFile.length());
			fileupload.setFileType(uploadfileContentType.get(i));
			fileupload.setFileTime(new Date());
			fileupload.setFileSave(atFileupload.getFileSave());
			if("2".equals(atFileupload.getFileSave())){
				byte[] ctba = FileCopyUtils.copyToByteArray(tempFile);
				fileupload.setContent(ctba);
				ctba=null;
			}else{
				String fNewName = System.currentTimeMillis()+"_"+atFileupload.getFileNewName()+"_"+tempFile.getName();
				fileupload.setFileNewName(fNewName);//确认建立关系后设置
				if(fileDatePath.endsWith("/")|| fileDatePath.endsWith("\\")){
					fNewName = fileDatePath+fNewName;
				}else{
					fNewName = fileDatePath+File.separator+fNewName;
				}
				FileUtil.copyFile(tempFile.getAbsolutePath(), fNewName);
			}
			if("true".equals(atFileupload.getFilePath())){
				fileupload.setFilePath(fileDatePath);//确认建立关系后设置
			}
			fileupload.setOperatorid(atFileupload.getOperatorid());
			fileupload.setOperatorname(atFileupload.getOperatorname());
			fileupload.setRelationId(atFileupload.getRelationId());
			fileupload.setGroupId(atFileupload.getGroupId());
			fileupload.setAttachmentSrcId(atFileupload.getAttachmentSrcId());
			fileupload.setAttachmentSrcCd(atFileupload.getAttachmentSrcCd());
			atFileuploadDAO.insert(fileupload);
			fileList.add(fileupload.getFileId());
		}
//		return atFileuploadDAO(srcCd, uploadfileFileName, uploadfile);
		atFileuploadDAO.executeBatch();
		return resultMap;
	}
	
	/* 
	 * 删除
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-13    叶承坤    新建
	 * </pre>
	 */

	@Override
	public boolean deleteUploadWithDB(AtFileupload atFileupload,String fileIds) throws Exception {
//		确认是否里面要删除 已上传的附件
		
		atFileuploadDAO.startBatch();
		AtFileuploadExample example = new AtFileuploadExample();
		com.gotop.tyjg.stable.model.AtFileuploadExample.Criteria criteria = example.createCriteria();
		boolean result = false;
		if(StringUtils.isNotBlank(fileIds)){
			String[] fileIdArra = fileIds.split(",");
			List<String> wordList = Arrays.asList(fileIdArra);
			criteria.andFileIdIn(wordList);
			result = true;
		}else{
			String fileId = atFileupload.getFileId();
			if(StringUtils.isNotBlank(fileId)){
				criteria.andFileIdEqualTo(fileId);
				result = true;
			}
			Long operatorid = atFileupload.getOperatorid();
			if(operatorid>0){
				criteria.andOperatoridEqualTo(operatorid);
				result = true;
			}
			String relationId = atFileupload.getRelationId();
			if(StringUtils.isNotBlank(relationId)){
				criteria.andRelationIdEqualTo(relationId);
				result = true;
			}
			String groupId = atFileupload.getGroupId();
			if(StringUtils.isNotBlank(groupId)){
				criteria.andGroupIdEqualTo(groupId);
				result = true;
			}
			String attachmentSrcId = atFileupload.getAttachmentSrcId();
			if(StringUtils.isNotBlank(attachmentSrcId)){
				criteria.andAttachmentSrcIdEqualTo(attachmentSrcId);
				result = true;
			}
			String attachmentSrcCd = atFileupload.getAttachmentSrcCd();
			if(StringUtils.isNotBlank(attachmentSrcCd)){
				criteria.andAttachmentSrcCdEqualTo(attachmentSrcCd);
				result = true;
			}
		}
		if(result){
			List files = atFileuploadDAO.selectByExample(example);
			for (int i = 0; i < files.size(); i++) {
				AtFileupload afd = (AtFileupload)files.get(i);
				if("1".equals(afd.getFileSave())){
					String filePathStr = afd.getFilePath();
					String filePath;
					if(filePathStr.endsWith("/")|| filePathStr.endsWith("\\")){
						filePath = filePathStr+afd.getFileNewName();
					}else{
						filePath = filePathStr+File.separator+afd.getFileNewName();
					}
					File file = ForUtil.createFile(filePath);
					file.delete();
				}
				File file = ForUtil.createFile(afd.getAbsolutepath());
				file.delete();
			}
			atFileuploadDAO.deleteByExample(example);
		}
		atFileuploadDAO.executeBatch();
		return result;
	}
	/* 
	 * 删除
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-13    叶承坤    新建
	 * </pre>
	 */

	@Override
	public boolean confirmUploadWithDB(AtFileupload atFileupload,String fileIds) throws Exception {
		atFileuploadDAO.startBatch();
		
		String fileDatePath = attachmentDAO.getFileDatePath();
		AtFileuploadExample example = new AtFileuploadExample();
		com.gotop.tyjg.stable.model.AtFileuploadExample.Criteria criteria = example.createCriteria();
		boolean result = false;
		if(StringUtils.isNotBlank(fileIds)){
			String[] fileIdArra = fileIds.split(",");
			List<String> wordList = Arrays.asList(fileIdArra);
			criteria.andFileIdIn(wordList);
			result = true;
		}else if(atFileupload!=null){
			String fileId = atFileupload.getFileId();
			if(StringUtils.isNotBlank(fileId)){
				criteria.andFileIdEqualTo(fileId);
				result = true;
			}
			Long operatorid = atFileupload.getOperatorid();
			if(operatorid>0){
				criteria.andOperatoridEqualTo(operatorid);
				result = true;
			}
			String relationId = atFileupload.getRelationId();
			if(StringUtils.isNotBlank(relationId)){
				criteria.andRelationIdEqualTo(relationId);
				result = true;
			}
			String groupId = atFileupload.getGroupId();
			if(StringUtils.isNotBlank(groupId)){
				criteria.andGroupIdEqualTo(groupId);
				result = true;
			}
			String attachmentSrcId = atFileupload.getAttachmentSrcId();
			if(StringUtils.isNotBlank(attachmentSrcId)){
				criteria.andAttachmentSrcIdEqualTo(attachmentSrcId);
				result = true;
			}
			String attachmentSrcCd = atFileupload.getAttachmentSrcCd();
			if(StringUtils.isNotBlank(attachmentSrcCd)){
				criteria.andAttachmentSrcCdEqualTo(attachmentSrcCd);
				result = true;
			}
		}
		if(result){
			if(atFileupload==null){
				atFileupload = new AtFileupload();
			}
			atFileupload.setFilePath(fileDatePath);
			atFileuploadDAO.updateByExample(atFileupload,example);
		}
		atFileuploadDAO.executeBatch();
		return result;
	}
	/**
	 * 下载，仅支持单个
	 */
	@Override
	public AtFileupload downloadWithDB(AtFileupload atFileupload,String fileIds) throws Exception{
		if(StringUtils.isBlank(fileIds)){
			fileIds = atFileupload.getFileId();
		}
		if(StringUtils.isBlank(fileIds)){
			return null;
		}
		atFileupload = atFileuploadDAO.selectByPrimaryKey(fileIds);
		return atFileupload;
	}
	/**
	 * 查询所有上传列表，查询分页数据
	 * @abatorgenerated
	 */
	public List queryWithDBBeanByMapAndPage(HashMap map, Page page)
			throws Exception {
		List list = attachmentDAO.queryWithDBBeanByMapAndPage(map, page);
		return list;
	}
	/**
	 * 查看需要修改的数据
	 * @abatorgenerated
	 */
	public AtFileupload queryWithDBBeanByID(String fileId) throws Exception {
		AtFileupload afl = atFileuploadDAO.selectByPrimaryKey(fileId);
		return afl;
	}
	/**
	 * 修改单条记录
	 * @abatorgenerated
	 */
	public boolean updateFileupload(AtFileupload atFileupload) throws Exception {
		int afl = atFileuploadDAO.updateByPrimaryKeySelective(atFileupload);
		return true;
	}
	/**
	 * 通过实例查询相关记录
	 * @param atFileupload
	 * @return
	 * @throws Exception
	 */
	public List queryWithDB(AtFileupload atFileupload) throws Exception {
		List afl = attachmentDAO.queryWithDB(atFileupload);
		return afl;
	}
	/**
	 * 打包需要下载的内容
	 * @param fileIds
	 * @param relationId
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public File downloadWithDBByGroup(String fileIds,String relationId,String groupId,String sessionId) throws Exception {
		HashMap<String,String> atFileupload = new HashMap<String,String>();
		atFileupload.put("fileIds", fileIds);
		atFileupload.put("relationId", relationId);
		atFileupload.put("groupId", groupId);
		List<AtFileupload> afl = attachmentDAO.queryWithDBByGroup(atFileupload);
		File destJar = jarDirZip(afl,sessionId);
		return destJar;
	}
	/**
	 * 将文件打包
	 * @param afl
	 * @throws Exception
	 */
	private File jarDirZip(List<AtFileupload> afl,String sessionId) throws Exception {
		String fileDatePath = attachmentDAO.getFileDatePath();
		BufferedOutputStream out = null;
		ZipOutputStream zos = null;
		CheckedOutputStream csum = null;
		FileOutputStream f = null;
		if(!fileDatePath.endsWith("/") && !fileDatePath.endsWith("\\")){
			fileDatePath += File.separator;
		}
		File destJar = ForUtil.createFile(fileDatePath+System.currentTimeMillis()+"_"+sessionId+".zip");
		log.info(destJar.getAbsoluteFile());
		if(destJar.exists()){
			destJar.delete();
		}
		if(destJar.getParentFile().exists()){
			destJar.getParentFile().mkdirs();
		}
		destJar.createNewFile();
		try {
			f = ForUtil.createFileOutputStream(destJar);
			// 用来计算和校验文件的校验和
			csum = new CheckedOutputStream(f, new Adler32());
			// 定义输入输出流，调用ZipOutputStream接口，进行压缩输出。
			zos = new ZipOutputStream(csum);
			out = ForUtil.createBufferedOutputStream(zos);
			Set<String> fnSet = new HashSet<String>();
			for (int i = 0; i < afl.size(); i++) {
				AtFileupload afd = afl.get(i);
				String fileSave = afd.getFileSave();
				String fileName = afd.getFileName();
				String fileNewName = afd.getFileNewName();
				String filePath = afd.getFilePath();
				String newFile = "";
				if(filePath.endsWith("/")|| filePath.endsWith("\\")){
					newFile = filePath+File.separator+fileNewName;
				}
				byte[] bb = null;
				if("1".equals(fileSave)){
					File file = ForUtil.createFile(newFile);
					bb = FileCopyUtils.copyToByteArray(file);
				}else if("2".equals(fileSave)){
					bb = afd.getContent();
				}
				if(fnSet.contains(fileName)){
					fileName = i+"_"+fileName;
				}
				fnSet.add(fileName);
//				fileName = URLEncoder.encode(fileName, "UTF-8");
				ZipEntry zey = new ZipEntry(fileName);
				zos.putNextEntry(zey);
				out.write(bb);
				out.flush();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try{
				if(zos!=null){
					zos.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(out!=null){
					out.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
				if(f!=null){
					f.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return destJar;
	}
	/**
	 * 删除上月未确认的资源列表。 FILE_PATH is null基本判定垃圾数据
	 */
	public void deleteNotEffective(){
		int dwdbbne = attachmentDAO.deleteWithDBByNotEffective();
	}
}