package com.gotop.tyjg.empMngr.dao;

import java.sql.SQLException;
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

 


public interface IEmpMngrDao {

	// 查询登录用户的可管理机构
	public List<Omorganization> getUserMngrOrg(Long empid) throws Exception;
	
	// 查询机构子节点
	public List<Omorganization> queryChildNodes(long parentOrgId) throws Exception;
	
	//查询有员工的机构
	public List selectOrgById(String orgid) throws SQLException;
	
	// 查询机构员工
	public List queryOrgEmpNodes(Map param) throws SQLException;
	
	// 查询个数
	public int countEmpOrg(HashMap tempMap);
	
	// 查询机构是否存在
	public int queryOrgById(HashMap tempMap);
	
	// 修改employee对象
	public int updateOrgidToEmployee(HashMap tempMap);
	
	// 删除OmEmpOrg对象
	public int delEmpOrgObj(HashMap tempMap);
	
	// 新增OmEmpOrg对象
	public boolean addEmpOrgRec(HashMap tempMap);
	
	// 查询机构下人员信息列表
	public List selectEmpUnOrgList(HashMap cdMap);
	
	// 查询记录数
	public int countEmpUdOrg(HashMap cdMap);
	
	// 批量提交开始
	public void startBatch() throws Exception;
	
	// 修改排序
	public void updateEmpOrder(List<HashMap> hmpList) throws Exception;
	
	// 批量更新
	public void executeBatch() throws Exception;
	
	// 查询机构下人员
	public List<Omemployee> queryEmpNodes(long parentOrgId) throws Exception;
	
	// 根据机构id查询机构基本信息
	public Tomorganization queryOrgVoInfo(HashMap hmp) throws Exception;
	
	// 根据机构id查询机构附件信息表信息
	public Abftjgfjxx queryFjxxb(HashMap hmp) throws Exception;
	
	
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return 角色用逗号分割的集合
	 * @throws Exception
	 */
	public List<PartyRole> selectOrgAlreayGrantRoles(String orgid) throws Exception;
	
	

	/**
	 * 查询员工附件信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public List<Abftygfjxxb> queryYgfjxx(String empid) throws Exception;
	
	
	/**
	 * 查询员工基本信息
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeTm> queryEmpBaseInfo(String empid) throws Exception;
	
	
	/**
	 * 查询员工的机构信息
	 * @param orgid
	 * @return
	 * @throws Exception
	 */
	public List<Tomorganization> queryEmpOrgInfo(String orgid) throws Exception;
	
	/**
	 * 查询员工角色信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<AcOperator> queryEmpAcOperInfo(String userid) throws Exception;
	
	/**
	 * 查询员工已分配的系统角色
	 * @param empid
	 * @param roleType, "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	public List quertyEmpAlReadyGrantRole(String empid,String roleType) throws Exception;
	
	
	/**
	 * 根据身份证计算员工个数
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public int countEmpByno(HashMap hmp) throws Exception;
	
	
	/**
	 * 查询身份证相同的信息提示
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public String queryEmpPromtMess(HashMap hmp) throws Exception;
	
	
	/**
	 * 查询员工归属机构datacell列表
	 * @param cdMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List selectEmpOrgBelnDataCell(HashMap cdMap);
	
	/**
	 * 拷贝人员节点到其他机构
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean copyEmpTransct(HashMap cdMap) throws Exception;
	
	
	/**
	 * 拖动人员节点到其他机构
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean moveEmpNodeTransc(HashMap cdMap) throws Exception;
	
	
	/**
	 * 添加新用户的事务
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean addEmpInfoTransc(HashMap cdMap) throws Exception;
	
	
	/**
	 * 设置密码
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public boolean resetOperPwd(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 查询序列值
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public long getNextSeqVal(HashMap<String,String> hmp) throws Exception;
	
	
	/**
	 * 根据roleid查询AcRole对象并返回auto字段
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public AcRole getAcRoleAuto(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getAcRoleByRoleTypeOn4(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据roleType获取记录数
	 * @param cdMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long countRoleByRoleTypeOn4(HashMap cdMap) throws Exception;
	
	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getAcRoleByRoleTypeNot4(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据roleType获取记录数
	 * @param cdMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long countRoleByRoleTypeNot4(HashMap cdMap) throws Exception;
	
	
	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getAcRoleByRoleType(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据roleType获取记录数
	 * @param cdMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public long countRoleByRoleType(HashMap cdMap) throws Exception;
	/**
	 * 查询人员的人员机构
	 * @param hmp
	 * @return 人员机构集合
	 * @throws Exception
	 */
	public List<OmEmporg> selectEmpOrg(HashMap hmp)throws Exception;
	
	
	/**
	 * 根据条件查询记录数
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	public int countEmpOrgNum(HashMap cdMap) throws Exception ;
	
	
	/**
	 * 修改时保存用户信息
	 * @param omEmp
	 * @param abfygfjxx
	 * @param acOper
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmpInfoTransc(HashMap paramMap) throws Exception;
	
	
	
	/**
	 * 根据条件查询机构
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryMngorgByStartId(HashMap<String,String> cdMap) throws Exception;
	
	
	/**
	 * 根据条件查询子机构节点
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryChildOrgNodes(HashMap cdMap) throws Exception;
	
	
	/**
	 * 根据条件查询机构下人员节点
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryChildEmpNodes(HashMap cdMap) throws Exception;
	
	
	/**
	 * 查询OmPosition列表
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public List<OmPosition> queryOmpositRecList(HashMap hmp)throws Exception;
	/**
	 * 统计岗位数量
	 * @return
	 * @throws Exception
	 */
	public int countPositionNum(HashMap hmp) throws Exception;
	/**
	 * 新增人员归属机构
	 * @param emporg 人员归属机构信息
	 * @throws Exception
	 */
	public void insertEmpOrg(OmEmporg emporg) throws Exception;
	/**
	 * 删除人员归属机构
	 * @param emporg 待删除的信息
	 * @throws Exception
	 */
	public void deleteEmpOrg(OmEmporg emporg)throws Exception;
	/**
	 * 更新人员归属机构
	 * @param emporg 待更新的信息
	 * @throws Exception
	 */
	public void updateEmpOrg(OmEmporg emporg)throws Exception;
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
	
	/**
	 * 获取人员主要机构编号orgid
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public String queryEmpOrgid(String empid)throws Exception;
}
