package com.gotop.tyjg.empMngr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gotop.tyjg.empMngr.model.EmployeeTm;
import com.gotop.tyjg.empMngr.model.OmEmporg;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.vo.tyjg.Abftjgfjxxb;
import com.gotop.vo.tyjg.Abftygfjxxb;
import com.gotop.vo.tyjg.AcOperator;
import com.gotop.vo.tyjg.AcRole;
import com.gotop.vo.tyjg.OmEmpOrg;
import com.gotop.vo.tyjg.OmPosition;
import com.gotop.vo.tyjg.Omemployee;
import com.gotop.vo.tyjg.Omorganization;
import com.primeton.utils.Page;

public interface IEmpMngrService {

	public List<Omorganization> initOrgInfo(Long empid) throws Exception;
	
	public List<Omorganization> queryChildNode(Long parentOrgId) throws Exception;
	
	public List initOrgEmpTree(String parentorgid) throws Exception;
	
	public List queryOrgEmpNodes(Map param) throws Exception;
	
	// 判断empOrg对象是否存在
	public int isEmpOrgExist(OmEmpOrg empOrg);
	
	// 判断机构是否存在
	public boolean isOrgToExist(OmEmpOrg empOrg);
	
	// 新增机构人员信息
	public boolean addNewEmpOrg(OmEmpOrg empOrgFrom,OmEmpOrg empOrgTo);
	
	// 修改OmEmpOrg对象
	public boolean delEmpOrgObj(OmEmpOrg empOrg);
	
	// 把from机构id写入人员信息表
	public boolean updateOrgidToEmployee(OmEmpOrg empOrg);
	
	// 查询人员信息列表
	public List queryEmpUnOrgDataGrid(HashMap map, Page page) throws Exception;
	
	// 保存datacell修改数据
	public void updateGrid(HashMap hmp) throws Exception;
	
	/**
	 * 批量更新数据
	 */
	public void updateBatch(List abs) throws Exception;
	
	/**
	 * 查询不分页机构下用户列表
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public List queryEmpUnOrgList(HashMap cdMap) throws Exception;
	
	
	/**
	 * 保存排序
	 */
	public void updateGridRecOrdr(List<HashMap> hmp) throws Exception;
	
	/**
	 * 查机构下人员信息
	 * @return
	 */
	public List queryEmpByOrgId(Long orgid) throws Exception;
	
	/**
	 * 根据id查询机构编号
	 * @return
	 * @throws Exception
	 */
	public Tomorganization queryOrgInfoById(Long orgid) throws Exception;
	
	
	/**
	 * 根据机构id查询机构附件信息表信息
	 * @return
	 * @throws Exception
	 */
	public Abftjgfjxx queryJgfjxxbInfoById(Long orgid) throws Exception;
	
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return 角色对象
	 * @throws Exception
	 */
	public PartyRole selectOrgAlreayGrantRoles(String orgid) throws Exception;
	
	
	/**
	 * 查询员工基本信息
	 * @return
	 * @throws Exception
	 */
	public EmployeeTm queryEmpBaseInfo(String empid) throws Exception;
	
	
	/**
	 * 查询员工附加信息
	 * @return
	 * @throws Exception
	 */
	public Abftygfjxxb queryYgfjxx(String empid) throws Exception;
	
	
	/**
	 * 查询用户机构信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public Tomorganization queryEmpOrgInfo(String orgid) throws Exception;
	
	/**
	 * 查询员工角色信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public AcOperator queryEmpAcOperInfo(String userid) throws Exception;
	
	
	/**
	 * 查询员工已分配的系统角色
	 * @param empid
	 * @param roleType, "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	public AcRole quertyEmpAlReadyGrantRole(String empid,String roleType) throws Exception;
	
	
	/**
	 * 根据身份证计算员工个数
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public int countEmpByNo(HashMap hmp) throws Exception;
	
	
	/**
	 * 查询身份证相同的信息提示
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public String mutilCardNoPromt(HashMap hmp) throws Exception;
	
	
	// 查询人员归属机构信息datacell列表
	public List queryEmpOrgBelongDataCell(HashMap map, Page page) throws Exception;
	
	/**
	 * 复制人员节点到机构
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean copyEmpTransct(HashMap map) throws Exception;
	
	/**
	 * 拖动人员节点到其他机构
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean moveEmpToOrgTranc(HashMap cdMap) throws Exception;
	
	
	/**
	 * 添加新用户
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean addEmpInfo(HashMap cdMap) throws Exception;
	
	
	/**
	 * 修改用户信息
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmpInfo(HashMap cdMap) throws Exception;
	
	
	
	/**
	 * 设置密码
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean resetOperPwd(HashMap cdMap) throws Exception;
	
	
	/**
	 * 根据序列名称获取序列值
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public Long getNextSeqVal(HashMap<String,String> hmp) throws Exception;
	
	
	/**
	 * 根据角色id查询AcRole对象，返回auto
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public String getAcRoleExtendById(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 设置系统角色时获取系统角色datacell列表
	 * @param roleType "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getXtRoleListByRoleType(HashMap map, Page page) throws Exception;
	
	
	/**
	 * 设置系统角色时获取系统角色datacell列表
	 * @param roleType "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getYwRoleListByRoleType(HashMap map, Page page) throws Exception;
	
	
	/**
	 * 人员管理里面设置可管理机构树
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public List queryMngorgOfLogon(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据条件查询子机构
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public List queryTreeChildOrgNodes(HashMap cdMap) throws Exception;
	
	
	/**
	 * 根据条件查询机构下人员节点
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public List queryTreeChildEmpNodes(HashMap cdMap) throws Exception;
	
	
	/**
	 * 查询可授权岗位列表
	 * @param roleType "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getOmpositionList(String posiName,Page page) throws Exception;
	
	/**
	 * 保存人员归属机构
	 * @param insertList 新增归属机构信息
	 * @param updateList 修改归属机构信息
	 * @param deleteList 删除归属机构信息
	 * @throws Exception
	 */
	public void saveEmpOrg(List<OmEmporg> insertList,List<OmEmporg> updateList,List<OmEmporg> deleteList)throws Exception;
	
	/**
	 * 设置主机构
	 * @param hmp 人员机构信息
	 * @throws Exception
	 */
	public void updateMainOrg(HashMap<String, String> hmp) throws Exception;
	/**
	 * 验证用户的empcode
	 * @param empcode 待验证的code
	 * @return 数量
	 * @throws Exception
	 */
	public int checkEmpCode(String empcode)throws Exception;
	/**
	 * 查询人员岗位
	 * @param empid
	 * @throws Exception
	 */
	public OmPosition selectEmpPosition(String empid) throws Exception;
	/**
	 * 修改用户密码
	 * @param hmp
	 * @throws Exception
	 */
	public void updateOperatorPassword(HashMap<String, String> hmp) throws Exception;
}
