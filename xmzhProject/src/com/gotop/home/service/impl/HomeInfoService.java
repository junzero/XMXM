package com.gotop.home.service.impl;

import com.gotop.home.dao.IHomeInfoDAO;
import com.gotop.home.model.CurrUserInfo;
import com.gotop.home.model.HomeEvent;
import com.gotop.home.model.HomeInfo;
import com.gotop.home.service.IHomeInfoService;
import com.primeton.utils.Page;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;

public class HomeInfoService implements IHomeInfoService {
    /**
     * 
     * @abatorgenerated
     */
    protected Logger log = Logger.getLogger(HomeInfoService.class);

    /**
     * 通过spring注入的DAO对象.
     * @abatorgenerated
     */
    protected IHomeInfoDAO homeInfoDAO;

    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    public void setHomeInfoDAO(IHomeInfoDAO homeInfoDAO) throws Exception {
        this.homeInfoDAO = homeInfoDAO;
    }

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    public IHomeInfoDAO getHomeInfoDAO() throws Exception {
        return this.homeInfoDAO;
    }

    /*
     * 得到当前登录人员的未办信息
     */
    public List<HomeInfo> getEmpMessHomeInfo(CurrUserInfo curruserinfo) throws Exception{
        return this.homeInfoDAO.getEmpMessHomeInfo(curruserinfo);        
    }
    /*
     * 得到待办事件信息
     */
    public List<HomeEvent> getEmpEventInfo(HomeEvent homeevent) throws Exception{
        return null;
    }
}