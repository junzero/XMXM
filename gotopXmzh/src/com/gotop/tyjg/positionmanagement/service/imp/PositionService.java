package com.gotop.tyjg.positionmanagement.service.imp;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.positionmanagement.dao.IPositionDao;
import com.gotop.tyjg.positionmanagement.service.IPositionService;
import com.gotop.vo.tyjg.OmPosition;
import com.primeton.utils.Page;

import org.apache.log4j.Logger;

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
public class PositionService implements IPositionService {

	protected static Logger log = Logger.getLogger(PositionService.class);
	
	private IPositionDao positionDao;

	public IPositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(IPositionDao positionDao) {
		this.positionDao = positionDao;
	}
	
	/**
	 * 查询岗位信息列表
	 * @param omPosition 查询条件
	 * @param page 分页参数
	 * @return 满足条件的岗位信息
	 * @throws Exception
	 */
	public List<OmPosition> selectPositionList(OmPosition omPosition,Page page)throws Exception{
		omPosition.setOracleStart(String.valueOf(page.getBegin()));
		omPosition.setOracleEnd(String.valueOf(page.getBegin()+page.getLength()));
		int count = this.getPositionDao().countRecord("POSITION.countPosition", omPosition);
		page.setCount(count);
		return this.getPositionDao().selectOmPositionList(omPosition);
	}
	/**
	 * 新增岗位信息
	 * @param omPosition 待新增的岗位信息
	 * @throws Exception
	 */
	public void insertPosition(OmPosition omPosition) throws Exception{
		this.getPositionDao().insertPosition(omPosition);
	}
	/**
	 * 更新岗位信息
	 * @param omPosition 待更新的岗位信息
	 * @throws Exception
	 */
	public void updatePosition(OmPosition omPosition) throws Exception{
		this.getPositionDao().updatePosition(omPosition);
	}
	/**
	 * 查询单个岗位信息
	 * @param omPosition 待查岗位信息条件
	 * @return 岗位信息
	 * @throws Exception
	 */
	public OmPosition selectSinglePosition(OmPosition omPosition) throws Exception{
		return this.getPositionDao().selectSinglePosition(omPosition);
	}
	/**
	 * 批量删除岗位信息
	 * @param positionIds 待删除的岗位编号
	 * @throws Exception
	 */
	public void deleteBeathPosition(String positionIds) throws Exception{
		HashMap<String,String> hmp = new HashMap<String,String>(1);
		hmp.put("positionIds", positionIds);
		this.getPositionDao().deleteBeathPosition(hmp);
	}
}
