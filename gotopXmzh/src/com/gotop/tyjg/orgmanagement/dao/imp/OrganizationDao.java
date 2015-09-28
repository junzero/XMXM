package com.gotop.tyjg.orgmanagement.dao.imp;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.orgmanagement.dao.IOrganizationDao;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.TdjgfjBean;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.util.dataSource.SqlMapClientDao;
import com.gotop.util.security.ForUtil;
import com.primeton.utils.Page;
@SuppressWarnings("unchecked")
public class OrganizationDao extends SqlMapClientDao
							implements IOrganizationDao {
	/**
	 * 新增机构
	 * @param org 机构基本信息
	 * @param jgfj 机构附加信息
	 * @return true 成功 false 失败
	 * @throws Exception
	 */
	@Override
	public boolean insertOrg(Tomorganization org, Abftjgfjxx jgfj)
			throws Exception {
		this.startBatch();
		this.insert("TOMORGANIZATION.insertOrgBase", org);
		if(Integer.parseInt(org.getParentOrgId()) >=1){
			this.update("TOMORGANIZATION.updateParentSubCount", org);
		}
		this.insert("TOMORGANIZATION.insertOrgJgfjxx", jgfj);
		this.executeBatch();
		return true;
	}
	
	/**
	 * 根据父机构ID查询子机构
	 * @param parentOrgId 父机构编号
	 * @return 子机构信息
	 * @throws Exception
	 */
	@Override
	public List<Tomorganization> selectByChildOrg(long parentOrgId)
			throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("parentOrgId", parentOrgId);
		List<Tomorganization> orgList =  this.queryForList("TOMORGANIZATION.selectByChildOrg", hmp);
		return orgList;
	}
	
	/**
	 * 分页查询机构信息
	 * @param org 机构基本信息
	 * @param page 分页对象
	 * @return 满足条件的机构信息
	 * @throws Exception
	 */
	@Override
	public List<Tomorganization> selectByOrg(Tomorganization org, Page page)
			throws Exception {
		HashMap hmp = new HashMap(3);
		hmp.put("orgid", org.getOrgId());
		if(page != null){
			hmp.put("oracleStart", page.getBegin());
			hmp.put("oracleEnd", page.getBegin()+page.getLength());
		}
		if(page==null){
			return queryForList("TOMORGANIZATION.selectByOrgChild", hmp);
		}else{
			return queryForList("TOMORGANIZATION.selectByOrgChild", hmp,page);
		}
	}
	
	/**
	 * 根据机构ID查询机构基本信息
	 * @param orgid 机构编号
	 * @return 机构基本信息
	 * @throws Exception
	 */
	@Override
	public Tomorganization selectByPrimaryKey(long orgid) throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		Tomorganization org = (Tomorganization)this.queryForList("TOMORGANIZATION.singleOrgInfo", hmp).get(0);
		return org;
	}
	
	/**
	 * 根据机构ID查询机构附加信息
	 * @param orgid 机构编号
	 * @return 机构附加信息
	 * @throws Exception
	 */
	@Override
	public Abftjgfjxx selectByPrimaryKeyFjxx(long orgid) throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		
		List result = this.queryForList("TOMORGANIZATION.singleOrgfjxxInfo", hmp);
		if(result.size()>0){
			return (Abftjgfjxx)result.get(0);
		}else{
			return null;
		}
	}

	/**
	 * 根据人员ID查询当前登录人可管理机构
	 * @param empid 人员编号
	 * @return 可管理的机构信息
	 * @throws Exception
	 */
	@Override
	public List<Tomorganization> selectCurrUserManagerOrg(long empid)
			throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("empid", empid);
		return this.queryForList("TOMORGANIZATION.OrgMangs", hmp);
	}

	/**
	 * 更新机构信息
	 * @param org 机构基本信息
	 * @param jgfj 机构附加信息
	 * @param roleSplit 机构角色数组
	 * @return true 成功 false 失败
	 * @throws Exception
	 */
	@Override
	public boolean updateOrg(Tomorganization org, Abftjgfjxx jgfj,String[] roleSplit)
			throws Exception {
		this.startBatch();
		this.update("TOMORGANIZATION.updateBaseInfo", org);
		this.update("TOMORGANIZATION.updateJgfjxx", jgfj);
		String orgid = org.getOrgId();
		HashMap hmp = new HashMap(3);
		hmp.put("ORGID", orgid);
		this.delete("TOMORGANIZATION.delOrgRoles", hmp);
		if(roleSplit != null && roleSplit.length > 0){
			for(String temp : roleSplit){
				hmp.clear();
				hmp.put("ORGID", orgid);
				hmp.put("ROLEID", temp);
				this.insert("TOMORGANIZATION.addOrgRole", hmp);
			}
		}
		this.executeBatch();
		return true;
	}
	/**
	 * 根据机构ID获取下级机构数量
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	@Override
	public int childOrgCount(long orgid) throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		String count = String.valueOf(this.queryForObject("TOMORGANIZATION.childOrgCount",hmp));
		return Integer.parseInt(count);
	}
	/**
	 * 调整机构显示顺序
	 * @param paramList 机构顺序集合
	 * @return true:顺序调整成功 false:顺序调整失败
	 * @throws Exception
	 */
	public boolean updateChildOrgOrder(List<HashMap> paramList) throws Exception{
		boolean falg = false;
		this.startBatch();
		for(HashMap paramMap : paramList){
			this.insert("TOMORGANIZATION.updateChildOrgOrder", paramMap);
		}
		this.executeBatch();
		falg = true;
		return falg;
	}
	/**
	 * 验证该机构下是否有子机构
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	@Override
	public long checkExistsOrg(long orgid) throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		long orgNum = Long.parseLong(String.valueOf(this.queryForObject("TOMORGANIZATION.checkExistsChildOrgNum", hmp)));
		return orgNum;
	}
	/**
	 * 验证该机构下是否有人员
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	@Override
	public long checkExistsEmp(long orgid) throws Exception {
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		long empNum = Long.parseLong(String.valueOf(this.queryForObject("TOMORGANIZATION.checkExistsChildOrgEmpNum", hmp)));
		return empNum;
	}
	/**
	 * 移动机构
	 * @param paramMap 移动起始机构节点信息与终点机构信息
	 * @return true:移动成功 false:移动失败
	 * @throws Exception
	 */
	public boolean updateMoveOrg(HashMap paramMap) throws Exception{
		HashMap hmp = new HashMap(10);
		int count = 0;
		HashMap fromOrg = (HashMap)paramMap.get("Tomorganization");
		HashMap toOrg = (HashMap)paramMap.get("to");
		hmp.put("fromOrgid", fromOrg.get("orgId"));
		hmp.put("toOrgid", toOrg.get("orgId"));
		//查询需要更新的子机构
		List childOrgList = this.queryForList("TOMORGANIZATION.query_child_orgid", hmp);
		int size = childOrgList.size();
		this.startBatch();
		//更新当前移动的机构
		count = this.update("TOMORGANIZATION.updateCurrentOrg", hmp);
		for(int i = 0; i < size; i++){
			HashMap childOrgMap = (HashMap)childOrgList.get(i);
			hmp.clear();
			hmp.put("fromOrgid", childOrgMap.get("ORGID"));
			hmp.put("parentOrgid", childOrgMap.get("PARENTORGID"));
			//更新子机构的orgseq与subcount(子机构数量)
			count += this.update("TOMORGANIZATION.updateChildOrg", hmp);
		}
		this.executeBatch();
		if(count > 0){
			return true;
		}
		return false;
	}
	/**
	 * 机构置顶
	 * @param orgid 机构编号
	 * @return true:置顶成功 false:置顶失败
	 * @throws Exception
	 */
	public boolean updateMoveOrgTop(String orgid) throws Exception{
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		int count = this.update("TOMORGANIZATION.updateOrgTop", hmp);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取序列
	 * @param seqName 序列名称
	 * @return 序列号
	 * @throws Exception
	 */
	public long getPrimaryKey(String seqName) throws Exception{
		HashMap hmp = new HashMap(1);
		hmp.put("seqName", seqName);
		Object obj = this.queryForObject("TOMORGANIZATION.getPrimary", hmp);
		return Long.parseLong(String.valueOf(obj));
	 //return ForUtil.getPrimaryKey(seqName);
	}
	/**
	 * 验证机构新增时的机构信息
	 * @param paramMap 机构信息
	 * @return 存在数量
	 * @throws Exception
	 */
	public long checkOrgCode(HashMap paramMap)throws Exception{
		return Long.parseLong(String.valueOf(this.queryForObject("TOMORGANIZATION.getCountOrgCode", paramMap)));
	}
	/**
	 * 查询机构可授权角色信息
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public List selectOrgRoles(HashMap hashMap)throws Exception{
		List orgRoleList = this.queryForList("TOMORGANIZATION.queryOrgRole", hashMap);
		return orgRoleList;
	}
	/**
	 * 机构可授权角色数量
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public long countOrgRole(HashMap hashMap) throws Exception{
		return  Long.parseLong(String.valueOf(this.queryForObject("TOMORGANIZATION.countOrgRole", hashMap)));
	}
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return  角色用逗号分割的集合
	 * @throws Exception
	 */
	public List<PartyRole> selectOrgAlreayGrantRoles(String orgid) throws Exception{
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
		return this.queryForList("TOMORGANIZATION.queryAlreadyGrantRoles", hmp);
	}

	@Override
	public TdjgfjBean queryTdjgfjxx(String orgid) throws Exception
	{
		// TODO Auto-generated method stub
		HashMap hmp = new HashMap(1);
		hmp.put("orgid", orgid);
//		List<TdjgfjBean> lst=this.queryForList("TOMORGANIZATION.queryTdjgfjxx", hmp);
//		return lst.size()>0?lst.get(0):null;
		return null;
	}
}
