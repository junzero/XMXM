package com.gotop.commonProcess.service;

import com.gotop.commonProcess.dao.ITCommonProcessDAO;
import com.gotop.commonProcess.model.TCommonProcess;
import com.gotop.dto.BusinessDto;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface ITCommonProcessService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settCommonProcessDAO(ITCommonProcessDAO tCommonProcessDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITCommonProcessDAO gettCommonProcessDAO() throws Exception;

    /**
     * 动态查询实例，分页查询数据并返回list
     * @abatorgenerated
     */
    List queryDataGrid(HashMap map, Page page) throws Exception;

    /**
     * 更新单条记录，通过主键
     * @abatorgenerated
     */
    void update(TCommonProcess obj) throws Exception;

    /**
     * 插入单条记录
     * @abatorgenerated
     */
    void insert(TCommonProcess obj) throws Exception;

    /**
     * 删除单条记录
     * @abatorgenerated
     */
    void delete(TCommonProcess obj) throws Exception;

    /**
     * 批量更新数据
     * @abatorgenerated
     */
    void updateBatch(List abs) throws Exception;

    /**
     * 批量插入数据
     * @abatorgenerated
     */
    void insertBatch(List abs) throws Exception;

    /**
     * 批量删除数据
     * @abatorgenerated
     */
    void deleteBatch(List abs) throws Exception;

    /**
     * datacell方式批量更新数据
     * @abatorgenerated
     */
    void updateDataGrid(HashMap hmp) throws Exception;

    /**
     * 查询所有数据并返回List
     * @abatorgenerated
     */
    List queryAllDataList(HashMap map) throws Exception;

    /**
     * 分页方式查询列表数据
     * @abatorgenerated
     */
    List queryPageDataList(HashMap map, Page page) throws Exception;

    /**
     * 通过主键或流程实例获得通用流程信息
     * @param recId
     * @param flowId
     * @return
     * @throws Exception
     */
	TCommonProcess queryCommonProcess(String recId, String flowId) throws Exception;

	/**
	 * 插入或更新公共流程表
	 * @param commonProcess
	 * @param businessDto
	 * @param muo
	 * @param taskAssgineeDto
	 * @throws Exception
	 */
	void insertCommonProcess(TCommonProcess commonProcess,
			BusinessDto businessDto, MUOUserSession muo,
			TaskAssgineeDto taskAssgineeDto) throws Exception;

	/**
	 * 审核公共流程
	 * @param commonProcess
	 * @param businessDto
	 * @param packTaskAssgineeDto
	 * @param muo
	 * @throws Exception
	 */
	void insertApprove(TCommonProcess commonProcess, BusinessDto businessDto,
			TaskAssgineeDto packTaskAssgineeDto, MUOUserSession muo) throws Exception;

	/**
	 * 获取部门下所有非部门领导人
	 * @param positionCode
	 * @param muo
	 * @return
	 * @throws Exception
	 */
	List<TCommonProcess> queryTaskAssignPerson(String positionCode,
			MUOUserSession muo) throws Exception;

	/**
	 * 部室内部会签
	 * @param taskAssgineeDto
	 * @param muo
	 * @param commonProcess
	 * @return
	 * @throws Exception
	 */
	String insertTaskAssignePerson(TaskAssgineeDto taskAssgineeDto,
			MUOUserSession muo, TCommonProcess commonProcess) throws Exception;

	/**
	 * 部室人员反馈意见
	 * @param taskAssgineeDto
	 * @param muo
	 * @param commonProcess
	 * @return
	 * @throws Exception
	 */
	String updateBuShiAssignStatus(TaskAssgineeDto taskAssgineeDto,
			MUOUserSession muo, TCommonProcess commonProcess) throws Exception;
}