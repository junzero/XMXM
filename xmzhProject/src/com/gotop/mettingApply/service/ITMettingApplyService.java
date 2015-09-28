package com.gotop.mettingApply.service;

import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.mettingApply.dao.ITMettingApplyDAO;
import com.gotop.mettingApply.model.TMettingApply;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITMettingApplyService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settMettingApplyDAO(ITMettingApplyDAO tMettingApplyDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITMettingApplyDAO gettMettingApplyDAO() throws Exception;

   /**
    * 查询信息列表
    * @param meet
    * @param muo
    * @param page
    * @return
    * @throws Exception
    */
    public List<TMettingApply> queryMettingApplyList(TMettingApply meet,MUOUserSession muo,Page page)throws Exception;
    
    /**
     * 插入信息
     * @param isFirst 
     * @throws Exception
     */
    public void insertMettingInfo(TMettingApply meet,TaskAssgineeDto dto,String btnType,MUOUserSession muo,File[] files,String[] filesFileName, String isFirst)throws Exception;
    
    /**
     * 查询信息
     * @param mettingId
     * @param flowId
     * @return
     * @throws Exception
     */
    public TMettingApply queryMettingInfo(String mettingId,String flowId)throws Exception;
    
    /**
     * 查看详情
     * @param meet
     * @return
     * @throws Exception
     */
    public TMettingApply querySuperviseRecive(TMettingApply meet)throws Exception;

    /**
     * 会议转发
     * @param meet
     * @param muo
     */
	void insertMessageTransmit(TMettingApply meet, MUOUserSession muo);

	List<TMettingApply> queryHomeMettingList(TMettingApply meet,
			MUOUserSession currentOnlineUser, Page page);
}