package test.gotop.demo.system.dao;

import java.util.HashMap;
import java.util.List;

import com.primeton.utils.Page;

public interface ITuserBiz {
	
	public List selectUserByMap(HashMap example,Page page);
	
	public List selectFunByMap(HashMap example,Page page);
	
	public int countUserByMap(HashMap example);
	
	public int countFunByMap(HashMap example);
	
	public List selectUserByMapBox(HashMap example);
	
	public List selectFunByMapBox(HashMap example);
	
	public List selectRoleByMapBox(HashMap example);
	
	
	public HashMap selectRoleById(int rid);
	
	public List queryJdbcOnly(HashMap example,Page page);
	
	public List queryJdbcScroll(HashMap example,Page page);
	
	public List querySqlMap(HashMap example,Page page);
	
	public List findSqlHasNext(HashMap example,Page page);
	
	public void update_test(int fint);
}