package test.gotop.crm.demo.dao.datacell;

import java.util.HashMap;
import java.util.List;
import test.gotop.crm.demo.model.datacell.AbfTJgfjxxb;
import test.gotop.crm.demo.model.datacell.AbfTJgfjxxbExample;

public interface IAbfTJgfjxxbDAO {

	/**
	 * 插入一条新数据
	 * @abatorgenerated
	 */
	void insert(AbfTJgfjxxb record);

	/**
	 * 通过主键更新一条全部字段内容
	 * @abatorgenerated
	 */
	int updateByPrimaryKey(AbfTJgfjxxb record);

	/**
	 * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
	 * @abatorgenerated
	 */
	int updateByPrimaryKeySelective(AbfTJgfjxxb record);

	/**
	 * 通过查询实例，查询记录
	 * @abatorgenerated
	 */
	List selectByExample(AbfTJgfjxxbExample example);

	/**
	 * 通过Map方式的动态条件，查询分页数据
	 * @abatorgenerated
	 */
	List selectByMapAndPage(HashMap example);

	/**
	 * 通过Bean方式的动态条件，查询分页数据
	 * @abatorgenerated
	 */
	List selectByExampleAndPage(AbfTJgfjxxb record, AbfTJgfjxxbExample example);

	/**
	 * 通过主键查询一条记录
	 * @abatorgenerated
	 */
	AbfTJgfjxxb selectByPrimaryKey(Integer orgid);

	/**
	 * 通过查询实例，删除数据
	 * @abatorgenerated
	 */
	int deleteByExample(AbfTJgfjxxbExample example);

	/**
	 * 通过主键删除一条记录
	 * @abatorgenerated
	 */
	int deleteByPrimaryKey(Integer orgid);

	/**
	 * 通过查询实例，统计总数
	 * @abatorgenerated
	 */
	int countByExample(AbfTJgfjxxbExample example);

	/**
	 * 通过查询实例，更新非null字段
	 * @abatorgenerated
	 */
	int updateByExampleSelective(AbfTJgfjxxb record, AbfTJgfjxxbExample example);

	/**
	 * 通过查询实例，更新全部字段
	 * @abatorgenerated
	 */
	int updateByExample(AbfTJgfjxxb record, AbfTJgfjxxbExample example);

	/**
	 * 分页查询信息
	 * @abatorgenerated
	 */
	List selectByDynamic(HashMap map) throws Exception;

	/**
	 * 批量提交开始
	 * @abatorgenerated
	 */
	void startBatch() throws Exception;

	/**
	 * 批量提交
	 * @abatorgenerated
	 */
	void executeBatch() throws Exception;
}