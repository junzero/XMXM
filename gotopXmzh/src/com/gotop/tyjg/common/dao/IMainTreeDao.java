package com.gotop.tyjg.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.tyjg.common.model.Role;
import com.gotop.vo.tyjg.OmPosition;

/**
 * *******************************
 * <p>Title:公共选人员、机构、角色、群组数据库交互 </p>
 * 
 * <p> Description: 公共选人员、机构、角色、群组数据库交互</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 11, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 11, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public interface IMainTreeDao {
	/**
	 * 查询行政树的机构节点
	 * @param hmp 查询条件
	 * @return 符合条件的根机构集合
	 * @throws Exception
	 */
	public List<Organization> selectAdminTreeNode(HashMap<String, String> hmp) throws Exception;
	/**
	 * 查询机构下的人员
	 * @param hmp 查询条件
	 * @return 符合条件的人员集合
	 * @throws Exception
	 */
	public List<Employee> selectAdminTreeEmpNode(HashMap<String, String> hmp) throws Exception;
	/**
	 * 查询当前用户可操作的群组
	 * @param groupids 可操作群组编号
	 * @return 可操作群组集合
	 * @throws Exception
	 */
	public List<Group> selectGroupTree(String[] groupids) throws Exception;
	/**
	 * 根据群组编号查询该组下的人员
	 * @param groupid 群组编号
	 * @return 该组下的人员集合
	 * @throws Exception
	 */
	public List<Employee> selectGroupEmp(String groupid) throws Exception;
	/**
	 * 根据群组编号查询该组下的机构
	 * @param groupid 群组编号
	 * @return 该组下的机构集合
	 * @throws Exception
	 */
	public List<Organization> selectGroupOrg(String groupid) throws Exception;
	/**
	 * 查询业务树信息
	 * @param topid 树编号
	 * @return 树信息
	 * @throws Exception
	 */
	public List selectBusessTreeInfo(String topid) throws Exception;
	/**
	 * 获取业务树的开始ID
	 * @param hmp 条件
	 * @return 符合条件的集合
	 * @throws Exception
	 */
	public List selectStartId(HashMap<String, String> hmp) throws Exception;
	/**
	 * 查询业务树机构
	 * @param hmp 查询条件
	 * @return 机构集合
	 * @throws Exception
	 */
	public List<Organization> selectBusessTreeOrg(HashMap hmp)throws Exception;
	/**
	 * 根据机构属性查询业务角色
	 * @param hmp 机构属性、分页参数
	 * @return 业务角色集合
	 * @throws Exception
	 */
	public List<Role> selectBusessRole(HashMap hmp)throws Exception;
	/**
	 * 统计业务角色记录数
	 * @param hmp 机构属性
	 * @return 记录数
	 * @throws Exception
	 */
	public int countBusessRole(HashMap hmp)throws Exception;
	/**
	 * 查询auto信息
	 * @param roleid 角色编码
	 * @return auto值
	 * @throws Exception
	 */
	public String selectRoleAuto(String roleid) throws Exception;
	
	public List<Employee> selectPersonList(HashMap hmp);
	
	public int countPersonList(HashMap hmp);
	
	public List<Employee> selectOrgList(HashMap<String, Object> hmp);
	
	public List<Employee> selectPersonPositionList(HashMap<String, Object> hmp);
	
	public List<Employee> selectPositionTreeEmpNode(HashMap paramMap);
	
	public List<OmPosition> selectPositionTree(HashMap paramMap);
	
	public List<Employee> selectPositionEmp(Map<String, Object> map);
	
	/**
	 * 2014-11-09 按人员查找群组
	 * @param empid
	 * @return
	 */
	public List<Group> selectGroupTree(Long empid);
}
