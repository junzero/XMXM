package com.gotop.supervise.service;

import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.opinion.dao.ITApproveOpninionDAO;
import com.gotop.messagePublish.dao.impl.TMessagePublishDAO;
import com.gotop.supervise.dao.ITSuperviseTableDAO;
import com.gotop.supervise.model.TSuperviseTable;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.List;

public interface ITSuperviseTableService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settSuperviseTableDAO(ITSuperviseTableDAO tSuperviseTableDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITSuperviseTableDAO gettSuperviseTableDAO() throws Exception;
    
    public TMessagePublishDAO gettMessagePublishDAO()throws Exception;
    public void settMessagePublishDAO(TMessagePublishDAO tMessagePublishDAO)throws Exception;
    public JbpmService getJbpmService()throws Exception;
    public void setJbpmService(JbpmService jbpmService) throws Exception;
	public ITApproveOpninionDAO gettApproveOpninionDAO()throws Exception;
	public void settApproveOpninionDAO(ITApproveOpninionDAO tApproveOpninionDAO) throws Exception;

    /**
     * 督办处理
     * @param siup
     * @param files
     * @param filesname
     * @param muo
     * @param btnType
     * @param taskAssgineeDto
     * @return
     * @throws Exception
     */
    public TaskAssgineeDto insertSuperviseTable(TSuperviseTable siup,File[] files,String[] filesname,MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto)throws Exception;
    public TaskAssgineeDto insertSuperviseTable2(TSuperviseTable siup,File[] files,String[] filesname,MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto, String isFirst)throws Exception;
    /**
     * 督办信息查询
     * @param superviseId
     * @return
     * @throws Exception
     */
    public TSuperviseTable querySuperviseTable(String superviseId,String flowId)throws Exception;
    
    /**
     * 查询信息
     * @param siup
     * @param muo
     * @param page
     * @return
     * @throws Exception
     */
    public List<TSuperviseTable> querySuperviseTabls(TSuperviseTable siup,MUOUserSession muo,Page page)throws Exception;
    
    /**
     * 查看待阅读的信息
     * @param sup
     * @return
     * @throws Exception
     */
    public TSuperviseTable querySuperviseRecive(TSuperviseTable sup )throws Exception;
    
   /**
    * 部署--内部会签 
    * @param dto
    * @return
    * @throws Exception
    */
    public String insertTaskAssignePerson(TaskAssgineeDto dto,MUOUserSession muo,TSuperviseTable sup )throws Exception;
    
    /**
     * 获取部室下副负责人
     * @param possionId
     * @param muo
     * @return
     * @throws Exception
     */
    public List<TSuperviseTable> queryTaskAssignPerson(String possionId,MUOUserSession muo)throws Exception;
    
    /**
     * 部室内部--保存
     * 
     */
    public String updateBuShiAssignStatus(TaskAssgineeDto dto,MUOUserSession muo,TSuperviseTable sup)throws Exception;

    /**
     * 部室办理、反馈、插入上传的附件
     * @param supervise
     * @param files
     * @param filesFileName
     * @param muo
     */
	void insertSuperviseFile(TSuperviseTable supervise, File[] files,
			String[] filesFileName, MUOUserSession muo,TaskAssgineeDto taskAssgineeDto)throws Exception;

	/**
	 * 查询岗位为行领导的人员
	 * @param positionCode
	 * @param muo
	 * @return
	 */
	List<TSuperviseTable> queryLeader(String positionCode, MUOUserSession muo);
	
	/**
	 * 
	 * @param dto
	 * @param muo
	 */
	void insertTask(TaskAssgineeDto dto,MUOUserSession muo) throws Exception;
}