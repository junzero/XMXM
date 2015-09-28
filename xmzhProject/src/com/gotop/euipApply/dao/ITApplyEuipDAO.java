package com.gotop.euipApply.dao;

import com.gotop.euipApply.model.TApplyEuip;
import com.gotop.euipApply.model.TApplyEuipExample;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ITApplyEuipDAO {
  
	/**
	 * 插入申报申请信息
	 * @param euip
	 * @throws Exception
	 */
	public void insertEuipInfo(TApplyEuip euip)throws Exception;
	
	/**'
	 * 查询设备申请信息
	 * @param euip
	 * @return
	 * @throws Exception
	 */
	public TApplyEuip queryApplyEuip(TApplyEuip euip)throws Exception;
	
	/**
	 * 修改设备申请
	 * @param euip
	 * @throws Exception
	 */
	public void updateApplyEuip(TApplyEuip euip)throws Exception;
}