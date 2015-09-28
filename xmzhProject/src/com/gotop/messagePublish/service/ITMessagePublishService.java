package com.gotop.messagePublish.service;

import com.gotop.file.model.TFileResourceTable;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.messageReceive.dao.ITMessageReceiveDAO;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITMessagePublishService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settMessagePublishDAO(ITMessagePublishDAO tMessagePublishDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITMessagePublishDAO gettMessagePublishDAO() throws Exception;
    
    public JbpmService getJbpmService()throws Exception;

	public void setJbpmService(JbpmService jbpmService)throws Exception;
	
	public ITMessageReceiveDAO gettMessageReceiveDAO()throws Exception;

	public void settMessageReceiveDAO(ITMessageReceiveDAO tMessageReceiveDAO)throws Exception;


   public List<TMessagePublish> queryTMessagePublishList(MUOUserSession muo,TMessagePublish message, Page page)throws Exception;
   
   public TaskAssgineeDto insertTMessagePublish(TMessagePublish message,File[] files,String[] filesname,MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto, String isFirst)throws Exception;
   
   public void insertTFileResource(TFileResourceTable tfile)throws Exception;
   
   public Long queryBatchSeq()throws Exception;
   
   public List<TApproveOpninion> queryApproveOpninions(HashMap  op)throws Exception;
   
   public TMessagePublish queryMessageInfo(String messageId,String flowId)throws Exception;
   
   /**
    * 阅毕
    * @param messageId
    * @param muo
    * @throws Exception
    */
   public void insertMessageRedPer(String messageId,String opninion,MUOUserSession muo)throws Exception;
   /**
    * 转发
    * @param message
    * @param muo
 * @param status 
    * @throws Exception
    */
   public void insertMessageTransmit(TMessagePublish message,MUOUserSession muo,String messageId, String status)throws Exception;

List querysupervise();

	/**
	 * 装督办
	 * @param message
	 * @param muo
	 * @throws Exception
	 */
   public String insertMegTstSup(TMessagePublish message,MUOUserSession muo,TaskAssgineeDto taskAssgineeDto)throws Exception;

   public void insertMessageTransmit(TMessagePublish message,
			MUOUserSession muo,String messageId)throws Exception;
   public void insertMessageRedPer_zdb(String messageId,String opninion,MUOUserSession muo) throws Exception;
}