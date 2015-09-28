package com.gotop.messagePublish.dao;

import com.gotop.file.model.TFileResourceTable;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.messagePublish.model.TMessagePublishExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITMessagePublishDAO {
   
	
	public List<TMessagePublish> queryMessagePublishList(TMessagePublish message,Page page)throws Exception;
	
	public void insertMessagePublish(TMessagePublish message)throws Exception;
	
	public void insertTFileResource(TFileResourceTable tfile)throws Exception;
	
	public Long queryBatchSeq() throws Exception;
	
	public List<TApproveOpninion> queryApproveOpninions(HashMap  op)throws Exception;
	
	public TMessagePublish queryMessageInfo(String messageId,String flowId)throws Exception;
	
	/**
	 * 插入已阅信息
	 * @param op
	 * @throws Exception
	 */
	public void insertMessageRedPer(HashMap op)throws Exception;
	
	/**
	 * 修改
	 * @param message
	 * @throws Exception
	 */
	public void updateMessageInfo(TMessagePublish message)throws Exception;

	/**
	 * 获得督办管理员的信息
	 * @return
	 */
	public List querysupervise();

	/**
	 * 获取机构名称
	 * @param empIds
	 * @return
	 */
	public List queryOrgName(String empIds);
    /**
     * 
     *
     * 根据信息ID，登录人ID，查询该条信息是否已读
     *
     * @param op
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-8    黄开忠    新建
     * </pre>
     */
   public Long queryMessageRed(HashMap op) throws Exception ;
	
}