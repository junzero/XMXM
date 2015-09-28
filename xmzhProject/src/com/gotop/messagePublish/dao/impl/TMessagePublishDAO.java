package com.gotop.messagePublish.dao.impl;

import com.fr.report.core.A.f;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.opinion.model.TApproveOpninion;
import com.gotop.messagePublish.dao.ITMessagePublishDAO;
import com.gotop.messagePublish.model.TMessagePublish;
import com.gotop.messagePublish.model.TMessagePublishExample;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class TMessagePublishDAO extends SqlMapClientDao implements ITMessagePublishDAO {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(TMessagePublishDAO.class);

    /**
     * This method was generated by Abator for iBATIS.
     * This method corresponds to the database table XMZHPT.T_MESSAGE_PUBLISH
     *
     * @abatorgenerated 
     */
    public TMessagePublishDAO() {
        super();
    }

	@Override
	public void insertMessagePublish(TMessagePublish message) throws Exception {
		insert("T_MESSAGE_PUBLISH_SqlMap.abatorgenerated_insert", message);	
	}

	@Override
	public List<TMessagePublish> queryMessagePublishList(
			TMessagePublish message, Page page) throws Exception {
		List<TMessagePublish> messages=queryForList("T_MESSAGE_PUBLISH_SqlMap.query_message_publish_lists", message, page);
		return messages;
	}

	@Override
	public void insertTFileResource(TFileResourceTable tfile) throws Exception {
		insert("T_MESSAGE_PUBLISH_SqlMap.tfileresource_insert", tfile);	
	}
	
	@Override
	public Long queryBatchSeq() throws Exception {
         Object object=this.getSqlMapClient().queryForObject("T_MESSAGE_PUBLISH_SqlMap.T_MESSAGE_PUBLISH_seq");
		return Long.valueOf(String.valueOf(object));
	}

	@Override
	public List<TApproveOpninion> queryApproveOpninions(HashMap  op)
			throws Exception {
	 List<TApproveOpninion> list= queryForList("T_MESSAGE_PUBLISH_SqlMap.query_approve_opninions", op);
		return list;
	}

	@Override
	public TMessagePublish queryMessageInfo(String messageId,String flowId) throws Exception {
			TMessagePublish message = new TMessagePublish();
			if(messageId!=null&&!"".equals(messageId))
			message.setMessageId(Long.valueOf(messageId));
			if(flowId!=null&&!"".equals(flowId))
		    message.setFlowId(flowId);
			return (TMessagePublish) this.getSqlMapClient().queryForObject("T_MESSAGE_PUBLISH_SqlMap.query_message_info",message);
	}

	@Override
	public void insertMessageRedPer(HashMap op) throws Exception {
		insert("T_MESSAGE_PUBLISH_SqlMap.insert_message_red_per",op);
	}

	@Override
	public void updateMessageInfo(TMessagePublish message) throws Exception {
		update("T_MESSAGE_PUBLISH_SqlMap.abatorgenerated_updateByPrimaryKey", message);
	}

	@Override
	public List querysupervise() {
		return queryForList("T_MESSAGE_PUBLISH_SqlMap.querysupervise",null);
	}

	@Override
	public List queryOrgName(String empIds) {
		return queryForList("T_MESSAGE_PUBLISH_SqlMap.queryOrgName",empIds);
	}
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
   public Long queryMessageRed(HashMap op) throws Exception {
       Object object=this.getSqlMapClient().queryForObject("T_MESSAGE_PUBLISH_SqlMap.queryTmessagetRed", op);
       return Long.valueOf(String.valueOf(object));
   }
  
}