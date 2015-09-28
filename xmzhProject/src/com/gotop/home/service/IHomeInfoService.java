package com.gotop.home.service;

import com.gotop.home.dao.IHomeInfoDAO;
import com.gotop.home.model.CurrUserInfo;
import com.gotop.home.model.HomeEvent;
import com.gotop.home.model.HomeInfo;
import com.primeton.utils.Page;
import java.util.HashMap;
import java.util.List;

public interface IHomeInfoService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void setHomeInfoDAO(IHomeInfoDAO homeInfoDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    IHomeInfoDAO getHomeInfoDAO() throws Exception;
    /*
     * 得到当前登录人员的未办信息
     */
    public List<HomeInfo> getEmpMessHomeInfo(CurrUserInfo curruserinfo) throws Exception;
    /*
     * 得到待办事件信息
     */
    public List<HomeEvent> getEmpEventInfo(HomeEvent homeevent) throws Exception;
}