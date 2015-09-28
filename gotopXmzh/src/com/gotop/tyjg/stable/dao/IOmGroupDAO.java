package com.gotop.tyjg.stable.dao;

import com.gotop.tyjg.stable.model.OmGroup;
import com.gotop.tyjg.stable.model.OmGroupExample;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface IOmGroupDAO {
    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    Long insert(OmGroup record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(OmGroup record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(OmGroup record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(OmGroupExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(OmGroup record, OmGroupExample example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    OmGroup selectByPrimaryKey(Long groupid);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(OmGroupExample example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(Long groupid);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(OmGroupExample example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(OmGroup record, OmGroupExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(OmGroup record, OmGroupExample example);

    /**
     * 分页查询信息
     * @abatorgenerated
     */
    List selectByDynamic(HashMap map, Page page) throws Exception;

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