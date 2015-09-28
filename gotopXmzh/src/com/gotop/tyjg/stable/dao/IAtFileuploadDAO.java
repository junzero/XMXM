package com.gotop.tyjg.stable.dao;

import com.gotop.tyjg.stable.model.AtFileupload;
import com.gotop.tyjg.stable.model.AtFileuploadExample;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface IAtFileuploadDAO {
    /**
     * 插入一条新数据
     * @abatorgenerated
     */
    void insert(AtFileupload record);

    /**
     * 通过主键更新一条全部字段内容
     * @abatorgenerated
     */
    int updateByPrimaryKey(AtFileupload record);

    /**
     * 通过主键更新部分字段，部分字段说明：当字段为null时不更新，当字段值为''空值是更新为空值
     * @abatorgenerated
     */
    int updateByPrimaryKeySelective(AtFileupload record);

    /**
     * 通过查询实例，查询记录
     * @abatorgenerated
     */
    List selectByExample(AtFileuploadExample example);

    /**
     * 通过Map方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByMapAndPage(HashMap example);

    /**
     * 通过Bean方式的动态条件，查询分页数据
     * @abatorgenerated
     */
    List selectByExampleAndPage(AtFileupload record, AtFileuploadExample example, Page page);

    /**
     * 通过主键查询一条记录
     * @abatorgenerated
     */
    AtFileupload selectByPrimaryKey(String fileId);

    /**
     * 通过查询实例，删除数据
     * @abatorgenerated
     */
    int deleteByExample(AtFileuploadExample example);

    /**
     * 通过主键删除一条记录
     * @abatorgenerated
     */
    int deleteByPrimaryKey(String fileId);

    /**
     * 通过查询实例，统计总数
     * @abatorgenerated
     */
    int countByExample(AtFileuploadExample example);

    /**
     * 通过查询实例，更新非null字段
     * @abatorgenerated
     */
    int updateByExampleSelective(AtFileupload record, AtFileuploadExample example);

    /**
     * 通过查询实例，更新全部字段
     * @abatorgenerated
     */
    int updateByExample(AtFileupload record, AtFileuploadExample example);

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