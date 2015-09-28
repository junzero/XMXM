package com.gotop.tyjg.common.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gotop.tyjg.common.model.ChangeTree;
import com.gotop.tyjg.common.model.Employee;
import com.gotop.tyjg.common.model.Group;
import com.gotop.tyjg.common.model.Organization;
import com.gotop.vo.tyjg.OmPosition;
import com.primeton.utils.Page;

/**
 * *******************************
 * <p>Title: </p>
 * 
 * <p> Description: </p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 10, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 10, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public interface IMainTreeService {
	/**
	 * 初始化树主页
	 * @param changeTree
	 * @throws Exception
	 */
	public void initMainTree(ChangeTree changeTree) throws Exception;
	/**
	 * 初始化行政树
	 * @param changeTree 行政树参数
	 * @throws Exception
	 */
	public void initAdminTree(ChangeTree changeTree) throws Exception;
	/**
	 * 查询行政树的机构节点
	 * @param hmp 查询条件
	 * @return 符合条件的机构集合
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
	 * 初始化群组树
	 * @param changeTree 群组条件
	 * @throws Exception
	 */
	public void initGroupTree(ChangeTree changeTree) throws Exception;
	
	/**
	 * 查询当前用户可操作的群组
	 * @param groupArray 拥有的可操作群组
	 * @return 可操作群组集合
	 * @throws Exception
	 */
	public List<Group> selectGroupTree(String[] groupArray) throws Exception;
	/**
	 * 查询群组下的机构、人员、角色
	 * @param groupid 群组编号
	 * @param orgType 选择类型
	 * @return 包含机构、人员、角色的XML
	 * @throws Exception
	 */
	public String selectGroupOrgEmpRole(String orgType, String groupid) throws Exception;
	/**
	 * 初始化业务树
	 * @param changeTree 业务树条件
	 * @throws Exception
	 */
	public void initBusessTree(ChangeTree changeTree) throws Exception;
	/**
	 * 获取业务树的机构与人员
	 * @param hmp 查询条件
	 * @return 包含机构、人员的XML
	 * @throws Exception
	 */
	public String selectBusessTreeOrgEmp(HashMap hmp) throws Exception;
	/**
	 * 查询业务角色
	 * @param hmp 参数
	 * @param page 分页参数
	 * @return xml
	 * @throws Exception
	 */
	public String selectBusessRole(HashMap hmp,Page page)throws Exception;
	/**
	 * 查询auto信息
	 * @param roleid 角色编码
	 * @return auto值
	 * @throws Exception
	 */
	public String selectRoleAuto(String roleid) throws Exception;
	
	/**
	 * 查询对应人员
	 * @param hmp
	 * @return
	 */
	public List<Employee> selectPersonList(HashMap hmp);
	
	/**
	 * 查询对应部门和人员
	 * @param hmp
	 * @return
	 */
	public List<Employee> selectOrgList(HashMap<String, Object> hmp);
	
	/**
	 * 查找行领导人员
	 * @param hmp
	 * @return
	 */
	public List<Employee> selectPersonPositionList(HashMap<String, Object> hmp);
	
	/**
	 * 初始化岗位人员树
	 * @param changeTree 业务树条件
	 * @throws Exception
	 */
	public void initPositionEmp(ChangeTree changeTree);
	
	/**
	 * 查找对应岗位的人员
	 * @param paramMap
	 * @return
	 */
	public List<Employee> selectPositionTreeEmpNode(HashMap paramMap);
	
	/**
	 * 查询岗位树
	 * @return
	 */
	public List<OmPosition> selectPositionTree(HashMap paramMap);
	
	/**
	 * 查询岗位下的人员
	 * @param posiCode
	 * @return
	 */
	public List<Employee> selectPositionEmp(String posiCode);
	
	/**
	 * 查询登录人员可操作群组树
	 * @param empid
	 * @return
	 */
	public List<Group> selectGroupTree(Long empid);
	
	/**
	 * 查找某群组下所有人员
	 * @param groupid
	 * @return
	 */
	public String selectGroupOrgEmpRole(String groupid) throws Exception;
}
