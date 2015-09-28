package test.gotop.crm.demo.dao.celltest;

import java.util.HashMap;
import java.util.List;
import test.gotop.crm.demo.model.celltest.AcOperator;
import test.gotop.crm.demo.model.celltest.AcOperatorExample;

public interface IAcOperatorDAO {
    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    void insert(AcOperator record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(AcOperator record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(AcOperator record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(AcOperatorExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(AcOperator record, AcOperatorExample example);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    AcOperator selectByPrimaryKey(Integer operatorid);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(AcOperatorExample example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Integer operatorid);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(AcOperatorExample example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(AcOperator record, AcOperatorExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(AcOperator record, AcOperatorExample example);

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