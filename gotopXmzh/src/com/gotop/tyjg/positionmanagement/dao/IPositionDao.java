package com.gotop.tyjg.positionmanagement.dao;

import java.util.HashMap;
import java.util.List;

import com.gotop.vo.tyjg.OmPosition;
import com.primeton.utils.Page;

/**
 * *******************************
 * <p>Title: </p>
 * 
 * <p> Description: </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 9, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 9, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public interface IPositionDao {
	/**
	 * 查询岗位信息列表
	 * @param omPosition 岗位过滤条件
	 * @return 满足条件的岗位列表
	 * @throws Exception
	 */
	public List<OmPosition> selectOmPositionList(OmPosition omPosition) throws Exception;
	/**
	 * 统计
	 * @param omPosition 查询条件
	 * @param String namingSql 命名SQL名称
	 * @return 记录数
	 * @throws Exception
	 */
	public int countRecord(String namingSql,OmPosition omPosition)throws Exception;
	/**
	 * 新增岗位信息
	 * @param omPosition 待新增的岗位信息
	 * @throws Exception
	 */
	public void insertPosition(OmPosition omPosition) throws Exception;
	/**
	 * 更新岗位信息
	 * @param omPosition 待更新的岗位信息
	 * @throws Exception
	 */
	public void updatePosition(OmPosition omPosition) throws Exception;
	/**
	 * 查询单个岗位信息
	 * @param omPosition 待查岗位信息条件
	 * @return 岗位信息
	 * @throws Exception
	 */
	public OmPosition selectSinglePosition(OmPosition omPosition) throws Exception;
	/**
	 * 批量删除岗位信息
	 * @param hmp 待删除的信息岗位编号用逗号分割
	 * @throws Exception
	 */
	public void deleteBeathPosition(HashMap<String, String> hmp) throws Exception;
	/**
	 * 批量删除人员岗位信息
	 * @param hmp 待删除的信息岗位编号用逗号分割
	 * @throws Exception
	 */
	public void deleteBeathEmpPosition(HashMap<String, String> hmp)throws Exception;
}
