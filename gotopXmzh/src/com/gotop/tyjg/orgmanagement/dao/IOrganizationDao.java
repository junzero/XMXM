package com.gotop.tyjg.orgmanagement.dao;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.TdjgfjBean;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.primeton.utils.Page;
@SuppressWarnings("unchecked")
public interface IOrganizationDao {
	/**
	 * 新增机构
	 * @param org 机构基本信息
	 * @param jgfj 机构附加信息
	 * @return true 成功 false 失败
	 * @throws Exception
	 */
	public boolean insertOrg(Tomorganization org,Abftjgfjxx jgfj) throws Exception;
	/**
	 * 更新机构信息
	 * @param org 机构基本信息
	 * @param jgfj 机构附加信息
	 * @param roleSplit 机构角色数组
	 * @return true 成功 false 失败
	 * @throws Exception
	 */
	public boolean updateOrg(Tomorganization org,Abftjgfjxx jgfj,String[] roleSplit) throws Exception;
	/**
	 * 分页查询机构信息
	 * @param org 机构基本信息
	 * @param page 分页对象
	 * @return 满足条件的机构信息
	 * @throws Exception
	 */
	public List<Tomorganization> selectByOrg(Tomorganization org,Page page) throws Exception;
	/**
	 * 根据机构ID查询机构基本信息
	 * @param orgid 机构编号
	 * @return 机构基本信息
	 * @throws Exception
	 */
	public Tomorganization selectByPrimaryKey(long orgid) throws Exception;
	/**
	 * 根据机构ID查询机构附加信息
	 * @param orgid 机构编号
	 * @return 机构附加信息
	 * @throws Exception
	 */
	public Abftjgfjxx selectByPrimaryKeyFjxx(long orgid) throws Exception;
	/**
	 * 根据父机构ID查询子机构
	 * @param parentOrgId 父机构编号
	 * @return 子机构信息
	 * @throws Exception
	 */
	public List<Tomorganization> selectByChildOrg(long parentOrgId) throws Exception;
	/**
	 * 根据人员ID查询当前登录人可管理机构
	 * @param empid 人员编号
	 * @return 可管理的机构信息
	 * @throws Exception
	 */
	public List<Tomorganization> selectCurrUserManagerOrg(long empid) throws Exception;
	/**
	 * 根据机构ID获取下级机构数量
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	public int childOrgCount(long orgid) throws Exception;
	/**
	 * 调整机构显示顺序
	 * @param paramList 机构顺序集合
	 * @return true:顺序调整成功 false:顺序调整失败
	 * @throws Exception
	 */
	public boolean updateChildOrgOrder(List<HashMap> paramList) throws Exception;
	/**
	 * 验证该机构下是否有子机构
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	public long checkExistsOrg(long orgid)throws Exception;
	/**
	 * 验证该机构下是否有人员
	 * @param orgid 机构编号
	 * @return 数量
	 * @throws Exception
	 */
	public long checkExistsEmp(long orgid)throws Exception;
	/**
	 * 移动机构
	 * @param paramMap 移动起始机构节点信息与终点机构信息
	 * @return true:移动成功 false:移动失败
	 * @throws Exception
	 */
	public boolean updateMoveOrg(HashMap paramMap) throws Exception;
	/**
	 * 机构置顶
	 * @param orgid 机构编号
	 * @return true:置顶成功 false:置顶失败
	 * @throws Exception
	 */
	public boolean updateMoveOrgTop(String orgid) throws Exception;
	/**
	 * 获取序列
	 * @param seqName 序列名称
	 * @return 序列号
	 * @throws Exception
	 */
	public long getPrimaryKey(String seqName) throws Exception;
	/**
	 * 验证机构新增时的机构信息
	 * @param paramMap 机构信息
	 * @return 存在的数量
	 * @throws Exception
	 */
	public long checkOrgCode(HashMap paramMap)throws Exception;
	/**
	 * 查询机构可授权角色信息
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public List selectOrgRoles(HashMap hashMap)throws Exception;
	/**
	 * 机构可授权角色数量
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public long countOrgRole(HashMap hashMap) throws Exception;
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return 角色用逗号分割的集合
	 * @throws Exception
	 */
	public List<PartyRole> selectOrgAlreayGrantRoles(String orgid) throws Exception;
	
	
	/**查询机构附加信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public TdjgfjBean queryTdjgfjxx(String orgid) throws Exception;
}
