package com.gotop.mettingApply.dao;

import com.gotop.mettingApply.model.TMettingApply;
import com.primeton.utils.Page;
import java.util.List;

public interface ITMettingApplyDAO {
    /**
     * 插入一条新数据.
     * @abatorgenerated
     */
    void insert(TMettingApply record);

   /**
    *  修改信息
    * @param meet
    * @return
    * @throws Exception
    */
   public  void updateMetingInfo(TMettingApply meet)throws Exception;
   
   /**
    * 查询列表信息
    * @param meet
    * @param page
    * @return
    * @throws Exception
    */
   public List<TMettingApply> queryMettingAppList(TMettingApply meet,Page page)throws Exception;
   
   /**
    * 查询信息
    * @param meet
    * @return
    * @throws Exception
    */
   public TMettingApply queryMettingApply(TMettingApply meet)throws Exception;
   
   /**
    * 查询信息
    * @param meet
    * @return
    * @throws Exception
    */
   public TMettingApply queryMettingApply1(TMettingApply meet)throws Exception;

List<TMettingApply> queryHomeMettingList(TMettingApply meet, Page page);
}