package com.gotop.tyjg.stable.dao;

import com.gotop.tyjg.stable.model.OmGroupmemberExample;
import com.gotop.tyjg.stable.model.OmGroupmemberKey;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface IOmGroupmemberDAO {
    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    void insert(OmGroupmemberKey record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(OmGroupmemberExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(OmGroupmemberKey record, OmGroupmemberExample example, Page page);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(OmGroupmemberExample example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(OmGroupmemberKey key);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(OmGroupmemberExample example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(OmGroupmemberKey record, OmGroupmemberExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(OmGroupmemberKey record, OmGroupmemberExample example);

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