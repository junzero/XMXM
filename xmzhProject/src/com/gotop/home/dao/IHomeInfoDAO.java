package com.gotop.home.dao;

import com.gotop.home.model.CurrUserInfo;
import com.gotop.home.model.HomeInfo;
import com.primeton.utils.Page;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface IHomeInfoDAO {
    /*
     * 得到当前登录人员的未办信息
     */
    public List<HomeInfo> getEmpMessHomeInfo(CurrUserInfo curruserinfo) throws Exception;
}