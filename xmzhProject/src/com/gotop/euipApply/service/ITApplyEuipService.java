package com.gotop.euipApply.service;

import com.gotop.euipApply.dao.ITApplyEuipDAO;
import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.file.model.FileBean;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public interface ITApplyEuipService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settApplyEuipDAO(ITApplyEuipDAO tApplyEuipDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITApplyEuipDAO gettApplyEuipDAO() throws Exception;

   /**
    * 处理设备申请信息
    * @param euip
    * @param files
    * @param filesName
    * @param btnType
    * @param muo
    * @throws Exception
    */
    public void insertEuipInfo(TApplyEuip euip,File[] files,String[] filesName,String btnType,MUOUserSession muo,TaskAssgineeDto taskAssgineeDto,String isFirst)throws Exception;
    
    /**
     * 查询申请信息
     * @param epId
     * @return
     * @throws Exception
     */
    public TApplyEuip queryEuipInfo(String epId,String flowId)throws Exception;
}