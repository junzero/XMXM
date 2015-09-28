package com.gotop.tyjg.common.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.common.dao.IAttachmentDAO;
import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.vo.system.MUOUserSession;
import com.gotop.tyjg.common.model.QueryWithDBBean;
import com.primeton.utils.Page;

public interface IAttachmentService {
	/**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void setAttachmentDAO(IAttachmentDAO attachmentDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    IAttachmentDAO getAttachmentDAO() throws Exception;
    
	/**
	 * 
	 *
	 *   获取附件限制
	 * 
	 * @param src_cd
	 *            附件来源
	 * @return HashMap HashMap/MAXNUM<String> 最大数量 HashMap/MAXSIZE<String>
	 *         最大空间
	 *
	 * <pre>
	 * 修改日期        修改人    修改原因
	 * 2012-7-9    叶承坤    新建
	 * </pre>
	 */
		
		public HashMap getAttachmentLimit(String src_cd) ;
		
		/**
		 * 文件上传,不保存数据库记录
		 * 
		 * @param src_id
		 * @param src_cd
		 * @param uploadfileFileName  文件名LIST
		 * @param uploadfile文件LIST
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息; HashMap/fileList （ HashMap<String,String> realName，path，fileSize）
		 *         返回文件对象
		 * @throws IOException
		 */

		public HashMap uploadAttachmentWithOutDB(String src_cd,List<String> uploadfileFileName,
				 List<File> uploadfile) throws IOException;
		
		
		/**
		 * 文件上传
		 * 
		 * @param src_id
		 * @param src_cd
		 * @param uploadfileFileName  文件名LIST
		 * @param uploadfile文件LIST
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息; HashMap/file<File[]>
		 *         返回文件对象
		 * @throws IOException
		 */

		public HashMap uploadAttachment(String src_id, String src_cd,List<String> uploadfileFileName,
				 List<File> uploadfile) throws IOException;
				 
		/**
		 * 文件上传
		 * 
		 * @param src_id
		 *            来源ID
		 * @param src_cd
		 *            来源代码
		 * @param fileName
		 *            文件名
		 * @param inputStream
		 *            文件流
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息; HashMap/file 返回文件对象
		 * @throws IOException
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap uploadAttachment(String src_id, String src_cd,
				String fileName, InputStream inputStream) throws IOException;

		/**
		 * 获取附件数据流
		 * 
		 * @param id
		 *            文件ID
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息; HashMap/fileName 文件名;
		 *         HashMap/outputStream文件流;
		 * @throws FileNotFoundException
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap downloadAttachment(String id) throws FileNotFoundException ;

		
		
		/**
		 * 获取附件路径
		 * 
		 * @param id
		 *            文件ID
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息; HashMap/fileName 文件名;
		 *         HashMap/filePath文件地址;
		 * @throws FileNotFoundException
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap downloadAttachmentInweb(String id) throws FileNotFoundException ;
		
		/**
		 * 获取附件列表
		 * 
		 * @param src_id
		 * @param src_cd
		 * @return list(hashMap) -> ATTACHMENT_INFO_ID, ATTACHMENT_NAME,
		 *         SPACE_SIZE,STORAGE_PATH, CREATE_TIME, UPDATE_TIME
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public List getAttachmentList(String src_id, String src_cd) ;
		
		/**
		 * 删除一条附件记录 (伪删除)
		 * 
		 * @param id
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息;
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap delAttachmentInfo(String id) ;
		
		/**
		 * 删除业务记录关联的所有附件
		 * 
		 * @param src_id  记录ID
		 * @param src_cd 来源模块
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息;
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap delAttachmentList(String src_id, String src_cd) ; 
		
		/**
		 * 批量删除多天业务逻辑记录关联的所有附件
		 * 
		 * @param src_idList
		 *            List<String> 记录ID列表
		 * @param src_cd
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息;
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap delAttachmentList(List<String> src_idList, String src_cd) ; 
		
		
		/**
		 * 将某个文件移给另外的业务逻辑记录。
		 * 
		 * @param id
		 *            文件ID
		 * @param src_id
		 *           新的来源ID
		 * @param src_cd
		 *           新的来源代码
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息;
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap moveAttachmentInfo(String id, String src_id, String src_cd) ;
		
		/**
		 * 附件拷贝
		 * 
		 * @param arrachment_id  要拷贝的文件ID列表
		 * @param src_id		来源ID
		 * @return HashMap/isSucc 是否成功; HashMap/msg 结果信息;
		 * <pre>
		 * 修改日期        修改人    修改原因
		 * 2012-7-9    叶承坤    新建
		 * </pre>
		 */
		public HashMap copyAttachmentInfo(List<String> arrachment_id, String src_id) ;
		
		/**
		 * 附件根据文件所在路径与文件名删除
		 * @param path 文件路径（包含文件名） 
		 * @param src_cd 模块编号
		 * @return true:成功  false:失败
		 * @ throws SQLException
		 */
		public boolean deleteSingleAttachement(String path,String src_cd)  throws SQLException;
		/**
		 * 增加附件
		 * @param atFileupload
		 * @param uploadfileFileName
		 * @param uploadfile
		 * @param uploadfileContentType
		 * @return
		 * @throws Exception
		 */
		public HashMap<String,Object> addUploadWithDB(AtFileupload atFileupload,
				List<String> uploadfileFileName, List<File> uploadfile,List<String> uploadfileContentType)
				throws Exception;
		/**
		 * 删除附件
		 * @param atFileupload
		 * @param fileIds
		 * @return
		 * @throws Exception
		 */
		public boolean deleteUploadWithDB(AtFileupload atFileupload,String fileIds) throws Exception;
		/**
		 * 确认提交
		 * @param atFileupload
		 * @param fileIds
		 * @return
		 * @throws Exception
		 */
		public boolean confirmUploadWithDB(AtFileupload atFileupload,String fileIds) throws Exception;
		/**
		 * 下载，仅支持单个
		 */
		public AtFileupload downloadWithDB(AtFileupload atFileupload,String fileIds) throws Exception;
		/**
		 * 查询所有上传列表，查询分页数据
		 * @abatorgenerated
		 */
		public List queryWithDBBeanByMapAndPage(HashMap map, Page page) throws Exception;
		/**
		 * 查看需要修改的数据
		 * @abatorgenerated
		 */
		public AtFileupload queryWithDBBeanByID(String fileId) throws Exception;
		/**
		 * 修改单条记录
		 * @return
		 * @throws Exception 
		 */
		public boolean updateFileupload(AtFileupload atFileupload) throws Exception;
		
		/**
		 * 通过实例查询相关记录
		 * @param atFileupload
		 * @return
		 * @throws Exception
		 */
		public List queryWithDB(AtFileupload atFileupload) throws Exception;
		
		/**
		 * 打包需要下载的内容
		 * @param fileIds
		 * @param relationId
		 * @param groupId
		 * @return
		 * @throws Exception
		 */
		public File downloadWithDBByGroup(String fileIds,String relationId,String groupId,String sessionId) throws Exception;
		
		/**
		 * 删除上月未确认的资源列表。 FILE_PATH is null基本判定垃圾数据
		 */
		public void deleteNotEffective();
}