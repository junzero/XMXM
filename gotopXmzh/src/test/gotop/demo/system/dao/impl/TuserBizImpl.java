package test.gotop.demo.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java_cup.internal_error;

import org.springframework.jdbc.core.JdbcTemplate;
import com.gotop.util.dataSource.SqlMapClientDao;

import test.gotop.demo.system.dao.ITuserBiz;

import com.primeton.utils.Condb;
import com.primeton.utils.Page;
import com.primeton.utils.ResultSet;
import org.apache.log4j.Logger;

public class TuserBizImpl extends SqlMapClientDao implements ITuserBiz{
	
	protected static Logger log = Logger.getLogger(TuserBizImpl.class);
	private JdbcTemplate jdbcTemplate = null;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public TuserBizImpl() {
		super();
	}
	/**
	 * datacell
	 */
	public List selectUserByMap(HashMap example,Page page) {
		if(page!=null){
			example.put("oracleStart", page.getBegin());
			example.put("oracleEnd", page.getBegin()+page.getLength());
			int count = countUserByMap(example);
			page.setCount(count);
		}
		List list = this.queryForList("TUSER_TEST.selectUserByMap", example);
		return list;
	}
	
	public int countUserByMap(HashMap example) {
		Integer count = (Integer) this.queryForObject("TUSER_TEST.countUserByMap", example);
		return count.intValue();
	}
	/*
	 * Datacell演示
	 * @see test.gotop.demo.system.dao.ITuserBiz#selectFunByMap(java.util.HashMap, com.primeton.utils.Page)
	 */
	public List selectFunByMap(HashMap example,Page page) {
		if(page!=null){
			example.put("oracleStart", page.getBegin());
			example.put("oracleEnd", page.getBegin()+page.getLength());
			int count = countFunByMap(example);
			page.setCount(count);
		}
		List list = this.queryForList("TUSER_TEST.selectFunByMap", example);
		return list;
	}
	/*
	 * count
	 * (non-Javadoc)
	 * @see test.gotop.demo.system.dao.ITuserBiz#countFunByMap(java.util.HashMap)
	 */
	public int countFunByMap(HashMap example) {
		Integer count = (Integer) this.queryForObject("TUSER_TEST.countFunByMap", example);
		return count.intValue();
	}
	
	/**
	 * user-comboselect
	 * @param example
	 * @return
	 */
	public List selectUserByMapBox(HashMap example) {
		List list = this.queryForList("TUSER_TEST.selectUserByMapBox", example);
		return list;
	}
	/**
	 * fun-comboselect
	 * @param example
	 * @param page
	 * @return
	 */
	public List selectFunByMapBox(HashMap example) {
		List list = this.queryForList("TUSER_TEST.selectFunByMapBox", example);
		return list;
	}
	/**
	 * role-singleton
	 * @param example
	 * @param page
	 * @return
	 */
	public List selectRoleByMapBox(HashMap example) {
		List list = this.queryForList("TUSER_TEST.selectRoleByMapBox", example);
		return list;
	}
	/**
	 * role-comboselect
	 * @param example
	 * @param page
	 * @return
	 */
	public HashMap selectRoleById(int rid) {
		HashMap role = (HashMap)this.queryForObject("TUSER_TEST.selectRoleById", rid);
		return role;
	}
	/**
	 * JDBC只读访问方式
	 */
	@Override
	public List queryJdbcOnly(HashMap example, Page page) {
		String sql = "select gnmc, gnlx, gndm, mkdm, gntp, gnsj, gnsx from tfunction where gnsx<:gnsx";
		Condb con = new Condb();
		ArrayList<HashMap<String, String>> resault=null;
		try {
			example.put("gnsx", 7);
			resault = con.prepareStatement(sql, page, example);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return resault;
	}
	/**
	 * JDBC滚动模式
	 */
	@Override
	public List queryJdbcScroll(HashMap example, Page page) {
		String sql = "select USID, PWD, ZCSJ, GMZQ, XZQX, YHNC, YHMC, DLCS, YHZT, BZ, LID, ORGID, EMAIL, MOBILE, FAX, ROLETYPE, USEDATE from tuser";
		Condb con = new Condb();
		ArrayList<HashMap<String, String>> resault=null;
		try {
			resault = con.prepareStatement(sql, page, example);
			for (int i = 0; i < resault.size(); i++) {
				HashMap<String, String> hmp = resault.get(i);
				String ORGID = hmp.get("ORGID");
				String sqlorg = "select orgid,orgname from torganization where orgid = '"+ORGID+"'";
				ResultSet rs = con.executeQuery(sqlorg);
				while(rs.next()){
					String org_id = rs.getString("orgid");
					
					if(org_id!=null && org_id.equals(ORGID)){
						String orgname = rs.getString("orgname");
						hmp.put("ORGNAME", orgname);
						break;
					}
				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		return resault;
	}
	/**
	 * 将SQL语句传入ibatis执行
	 */
	@Override
	public List querySqlMap(HashMap example, Page page) {
		String str = "select USID, PWD, ZCSJ, GMZQ, XZQX, YHNC, YHMC, DLCS, YHZT, BZ, LID, ORGID, EMAIL, MOBILE, FAX, ROLETYPE, USEDATE from tuser";
		if(page!=null){
			example.put("oracleStart", page.getBegin());
			example.put("oracleEnd", page.getBegin()+page.getLength());
			int count = (Integer)this.queryForObject("COMMON.dynamic_sql_count", str);
			page.setCount(count);
		}
		example.put("sql", str);
		List request = (List)this.queryForList("COMMON.dynamic_sql_page", example);
		return request;
	}
	@Override
	public List findSqlHasNext(HashMap example, Page page) {
		Condb con = new Condb();
		String sqlorg = "select orgid,orgname from torganization";
		List<HashMap> reault = new ArrayList<HashMap>();
		ResultSet rs = con.executeQuery(sqlorg);
		for(int i =0 ; rs.hasNext(i) ;i++){
			rs.next();
			HashMap hMap= new HashMap();
			String org_id = rs.getString("orgid");
			String orgname = rs.getString("orgname");
			hMap.put("ORGID", org_id);
			hMap.put("ORGNAME", orgname);
			reault.add(hMap);
		}
		rs.close();
		System.out.println("---reault-"+reault);
		return reault;
	}
	
	public void update_test(int fint){
		log.info("---远程调用失败");
	}
}