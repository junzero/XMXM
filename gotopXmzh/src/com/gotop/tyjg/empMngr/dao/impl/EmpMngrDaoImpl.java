package com.gotop.tyjg.empMngr.dao.impl;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.gotop.util.dataSource.SqlMapClientDao;

import com.gotop.tyjg.empMngr.dao.IEmpMngrDao;
import com.gotop.tyjg.empMngr.model.EmployeeTm;
import com.gotop.tyjg.empMngr.model.OmEmporg;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.util.exception.GotopException;
import com.gotop.util.security.ForUtil;
import com.gotop.vo.tyjg.Abftygfjxxb;
import com.gotop.vo.tyjg.AcOperator;
import com.gotop.vo.tyjg.AcRole;
import com.gotop.vo.tyjg.OmPosition;
import com.gotop.vo.tyjg.Omemployee;
import com.gotop.vo.tyjg.Omorganization;

public class EmpMngrDaoImpl extends SqlMapClientDao implements IEmpMngrDao {

	protected static Logger log = Logger.getLogger(EmpMngrDaoImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<Omorganization> getUserMngrOrg(Long empid) throws Exception {
		// 查询登录用户可管理机构
		List list = this.queryForList("OMEMPLOYEE.select_OrgMangs", empid);
        return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Omorganization> queryChildNodes(long parentOrgId)
			throws Exception {
		// 根据父节点查询子节点
		HashMap hmp = new HashMap();
		hmp.put("parentOrgId", parentOrgId);
		List<Omorganization> orgList =  this.queryForList("OMEMPLOYEE.queryOrgNodes", hmp);
		return orgList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Omemployee> queryEmpNodes(long parentOrgId) throws Exception{
		// 查询机构下人员
		HashMap hmp = new HashMap();
		hmp.put("orgid", parentOrgId);
		List<Omemployee> empList =  this.queryForList("OMEMPLOYEE.selectEmpNodes", hmp);
		return empList;
	}

	@Override
	public List selectOrgById(String orgidStr) throws SQLException {
		//查询有员工的机构
		List empList =null;
		
		if(orgidStr!=null){
			int orgid =Integer.parseInt(orgidStr);
			empList =this.queryForList("OMEMPLOYEE.selectOrg",orgid);
		}
        return empList;
	}

	@Override
	public List queryOrgEmpNodes(Map param) throws SQLException {
		// 查询机构员工
		return this.queryForList("OMEMPLOYEE.queryEmpid", param);
	}

	
	@Override
	public int countEmpOrg(HashMap tempMap) {
		// 根据empid和orgid查询OmEmpOrg个数
		List empOrgList =this.queryForList("OMEMPLOYEE.select_EmpOrgList", tempMap);
		int cn =empOrgList.size();
		return cn;
	}

	@Override
	public int queryOrgById(HashMap tempMap) {
		// 根据id查询机构
		List orgList =this.queryForList("OMEMPLOYEE.query_orgList", tempMap);
		int ctNum =orgList.size();
		return ctNum;
	}

	@Override
	public boolean addEmpOrgRec(HashMap tempMap) {
		this.insert("OMEMPLOYEE.insert_om_emporg", tempMap);
		return true;
	}

	@Override
	public int delEmpOrgObj(HashMap tempMap) {
		// TODO Auto-generated method stub
		int mdNum =this.delete("OMEMPLOYEE.del_om_emporg", tempMap);
		return mdNum;
	}

	@Override
	public int updateOrgidToEmployee(HashMap tempMap) {
		// TODO Auto-generated method stub
		int cnum= this.update("OMEMPLOYEE.update_om_employee", tempMap);
		return cnum;
	}

	@Override
	public int countEmpUdOrg(HashMap cdMap) {
		// 查询记录数
		Integer count = (Integer) this.queryForObject(
				"OMEMPLOYEE.empUnOrg_countNums", cdMap);
		return count.intValue();
	}
	public int countEmpOrgNum(HashMap cdMap) throws Exception {
		// 查询记录数
		Integer count = (Integer) this.queryForObject(
				"OMEMPLOYEE.countEmpOrg", cdMap);
		return count.intValue();
	}

	@Override
	public List selectEmpUnOrgList(HashMap cdMap) {
		// 查询人员信息列表
		List list = this.queryForList(
				"OMEMPLOYEE.query_empUnOrgList", cdMap);
		return list;
	}
	/**
	 * 统计岗位数量
	 * @return
	 * @throws Exception
	 */
	public int countPositionNum(HashMap hmp) throws Exception{
		String num = this.queryForObject("OMEMPLOYEE.countPositionNum",hmp).toString();
		return Integer.valueOf(num);
	}
	
	/**
	 * 修改排序
	 */
	@SuppressWarnings("unchecked")
	public void updateEmpOrder(List<HashMap> hmpList) throws Exception{
		
		this.startBatch();
		for(int i=0;i<hmpList.size();i++){
			HashMap map =new HashMap();
			map =hmpList.get(i);
			int orgid =Integer.parseInt(map.get("orgid").toString());
			int empid =Integer.parseInt(map.get("empid").toString());
			String displayorder =map.get("displayorder").toString();
			map.put("orgid", orgid);
			map.put("empid", empid);
			map.put("displayorder", displayorder);
			int rows = this.update(
					"OMEMPLOYEE.update_employee_order", map);
		}
		this.executeBatch();
	}
	
	@Override
	public Tomorganization queryOrgVoInfo(HashMap hmp) throws Exception {
		// 根据机构id查询机构基本信息
		
		List<Tomorganization> orgList =this.queryForList("OMEMPLOYEE.singleOrgInfo", hmp);
		Tomorganization orgVo =(Tomorganization)orgList.get(0);
		return orgVo;
	}

	@Override
	public Abftjgfjxx queryFjxxb(HashMap hmp) throws Exception {
		// 根据机构id查询机构附件信息表信息
		Abftjgfjxx tjgfjxxb =(Abftjgfjxx)this.queryForObject("OMEMPLOYEE.singleOrgfjxxInfo", hmp);
		return tjgfjxxb;
	}

	
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return  角色用逗号分割的集合
	 * @throws Exception
	 */
	public List<PartyRole> selectOrgAlreayGrantRoles(String orgid) throws Exception{
		HashMap hmp =new HashMap();
		hmp.put("orgid", orgid);
		return this.queryForList("OMEMPLOYEE.queryAlreadyGrantRoles", hmp);
	}
	
	
	/**
	 * 查询机构下员工基本信息
	 */
	@Override
	public List<EmployeeTm> queryEmpBaseInfo(String empid) throws Exception {
		// TODO Auto-generated method stub
		HashMap hmp =new HashMap();
		hmp.put("empid", empid);
		return this.queryForList("OMEMPLOYEE.queryEmpBaseInfo", hmp);
	}

	
	/**
	 * 查询员工附加信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Abftygfjxxb> queryYgfjxx(String empid) throws Exception {
		
		HashMap hmp =new HashMap();
		hmp.put("empid", empid);
		List<Abftygfjxxb> abfygList =this.queryForList("OMEMPLOYEE.queryygfjxx", hmp);
		return abfygList;
	}

	
	//	查询应该角色信息
	@Override
	public List<AcOperator> queryEmpAcOperInfo(String userid) throws Exception {
		HashMap hmp =new HashMap();
		hmp.put("userid", userid);
		return this.queryForList("OMEMPLOYEE.queryEnpAcOperInfo", hmp);
	}

	
	//	查询员工机构信息
	@Override
	public List<Tomorganization> queryEmpOrgInfo(String orgid) throws Exception {
		HashMap hmp =new HashMap();
		hmp.put("orgid", orgid);
		return this.queryForList("OMEMPLOYEE.queryEmpOrgInfo", hmp);
	}

	/**
	 * 查询员工已分配角色
	 * @param empid
	 * @param roleType, "0"表示业务角色，"1"表示系统角色
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List quertyEmpAlReadyGrantRole(String empid,String roleType) throws Exception {
		HashMap hmp =new HashMap();
		hmp.put("empid", empid);
		hmp.put("roleType", roleType);
		return this.queryForList("OMEMPLOYEE.queryEmpAlreadyGrantRoles", hmp);
	}

	@Override
	public int countEmpByno(HashMap hmp) throws Exception {
		Integer cns =0;
		cns=(Integer)this.queryForObject("OMEMPLOYEE.countEmpNumsByno", hmp);
		return cns.intValue();
	}

	@Override
	public String queryEmpPromtMess(HashMap hmp) throws Exception {
		Object object = this.queryForObject("OMEMPLOYEE.selectPromtMess", hmp);
		if(object != null){
			return String.valueOf(object);
		}
		return null;
	}

	@Override
	public List selectEmpOrgBelnDataCell(HashMap cdMap) {
		// 查询员工归属机构datacell列表
		
		List list = this.queryForList(
				"OMEMPLOYEE.query_empOrgBelongDataCell", cdMap);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean copyEmpTransct(HashMap cdMap) throws Exception {
		// 复制人员节点
		
		Date currTime =new Date();
		String empid =cdMap.get("empid").toString();
		String fromOrgid =cdMap.get("fromOrgid").toString();
		String toOrgid =cdMap.get("toOrgid").toString();
		HashMap upMap =new HashMap();
		upMap.put("empid", empid);
		upMap.put("orgid", fromOrgid);
		HashMap adMap =new HashMap();
		adMap.put("empid", empid);
		adMap.put("orgid", toOrgid);
		adMap.put("ismain", "n");
		adMap.put("modflag", "A");	//	新增
		adMap.put("lastUpdate", currTime);
		adMap.put("datasource", "2");
		
		this.startBatch();
		// 写入from机构id到人员表
		this.update("OMEMPLOYEE.update_om_employee", upMap);
		
		// 新增一条人员机构信息
		this.insert("OMEMPLOYEE.insert_om_emporg", adMap);
		
		this.executeBatch();
		
		return true;
	}

	@Override
	public boolean moveEmpNodeTransc(HashMap cdMap) throws Exception {
		// 拖动人员节点到其他机构
		
		Date currTime =new Date();
		String empid =cdMap.get("empid").toString();
		String fromOrgid =cdMap.get("fromOrgid").toString();
		String toOrgid =cdMap.get("toOrgid").toString();
		String isDouble = null;
		if(cdMap.containsKey("isDouble")){
			cdMap.get("isDouble").toString();
		}
		HashMap upMap =new HashMap();
		upMap.put("empid", empid);
		upMap.put("orgid", fromOrgid);
		
		HashMap adMap =new HashMap();
		adMap.put("empid", empid);
		adMap.put("orgid", toOrgid);
		adMap.put("ismain", "y");	//	主机构
		adMap.put("modflag", "A");	//	新增
		adMap.put("lastUpdate", currTime);
		adMap.put("datasource", "2");
		
		this.startBatch();
		// 写入from机构id到人员表
		this.update("OMEMPLOYEE.update_om_employee", upMap);
		
		if(!"true".equals(isDouble)){
//			删除原有员工归属机构信息记录
			this.delete("OMEMPLOYEE.del_om_emporg", upMap);
		}
		
		// 新增一条人员机构信息
		this.insert("OMEMPLOYEE.insert_om_emporg", adMap);
		this.executeBatch();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addEmpInfoTransc(HashMap cdMap) throws Exception {
		String empid =cdMap.get("empid").toString();
		
		//	密码加密
		String password =cdMap.get("password").toString();
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
		String	Encryption = baseEncoder.encode(md5.digest(password.getBytes("utf-8")));
		cdMap.put("password", Encryption);
		
		this.startBatch();
		//	新增一条人员信息记录
		this.insert("OMEMPLOYEE.insert_om_employee", cdMap);
		//	新增一条附加信息
		this.insert("OMEMPLOYEE.insert_abf_ygfjxxb", cdMap);
		
		// 新增一条人员机构信息
		this.insert("OMEMPLOYEE.insert_ac_operator", cdMap);
		
		this.insert("OMEMPLOYEE.insert_om_empOrg", cdMap);

		//	插入系统角色
		Object xtObj =cdMap.get("xt_mngrole");
		if(xtObj!=null && !"".equals(xtObj)){
			String xt_mngrole = String.valueOf(xtObj);
			String[] xtArr =xt_mngrole.split(",");
			for(int i=0;i<xtArr.length;i++){
				String mngCode =xtArr[i];
				HashMap hmp =new HashMap();
				hmp.put("roleid", mngCode);
				hmp.put("empid", empid);
//				agency=0 表示业务角色， agency=1 表示 系统角色
				hmp.put("agency", "1");
				this.insert("OMEMPLOYEE.insert_ac_operrole", hmp);
			}
		}
		
		// 插入业务角色
		Object ywObj = cdMap.get("yw_mngrole");
		if(ywObj!=null && !"".equals(ywObj)){
			String yw_mngrole = String.valueOf(ywObj);
			String[] ywArr =yw_mngrole.split(",");
			for(int i=0;i<ywArr.length;i++){
				String mngCode =ywArr[i];
				HashMap hmp =new HashMap();
				hmp.put("roleid", mngCode);
				hmp.put("empid", empid);
//				agency=0 表示业务角色， agency=1 表示 系统角色
				hmp.put("agency", "0");
				this.insert("OMEMPLOYEE.insert_ac_operrole", hmp);
			}
			
		}
		
		
		String empCode =cdMap.get("empCode").toString();
		String empName =cdMap.get("empName").toString();
		
		//	插入可管理机构
		Object orgListObj =cdMap.get("orgidlist");
		if(orgListObj!=null && !"".equals(orgListObj)){
			String orgidlist = String.valueOf(orgListObj);
			List<HashMap> orgInfoList=this.queryForList("OMEMPLOYEE.queryOrgStrList", orgidlist);
			for(int i=0;i<orgInfoList.size();i++){
				HashMap hmp =orgInfoList.get(i);
				hmp.put("empid", empid);
				hmp.put("EMPCODE", empCode);
				hmp.put("EMPNAME", empName);
				this.insert("OMEMPLOYEE.insert_abft_kgljg", hmp);
			}
			
		}
		Object positionObj = cdMap.get("position");
		if(positionObj!=null && !"".equals(positionObj)){
			String positins = String.valueOf(positionObj);
			String[] posiArray = positins.split(",");
			for(String position : posiArray){
				HashMap< String, String> maps = new HashMap<String, String>(2);
				maps.put("empid", empid);
				maps.put("positionid", position);
				this.insert("OMEMPLOYEE.insertPosition",maps);
			}
		}
		this.executeBatch();
		return true;
	}

	@Override
	public boolean resetOperPwd(HashMap<String,String> cdMap) throws Exception {
		this.update("OMEMPLOYEE.update_oper_pwd", cdMap);
		return true;
	}

	@Override
	public long getNextSeqVal(HashMap<String,String> hmp) throws Exception {
		Object obj = this.queryForObject("OMEMPLOYEE.getNextSeqValue", hmp);
		return Long.parseLong(String.valueOf(obj));
		//return ForUtil.getPrimaryKey(hmp.get("seqName"));
	}

	@Override
	public AcRole getAcRoleAuto(HashMap<String, String> cdMap) throws Exception {
		AcRole acRole =(AcRole)this.queryForObject("OMEMPLOYEE.queryAcRoleAuto", cdMap);
		return acRole;
	}

	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * orgDegree = 4
	 * roleType =0
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getAcRoleByRoleTypeOn4(HashMap<String, String> cdMap)
			throws Exception {
		List<AcRole> list = this.queryForList(
					"OMEMPLOYEE.queryAllRolesToGrantOn4", cdMap);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countRoleByRoleTypeOn4(HashMap cdMap) throws Exception{
		return  Long.parseLong(String.valueOf(this.queryForObject("OMEMPLOYEE.getRoleByRoleTypeOn4_countNums", cdMap)));
	}

	
	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * orgDegree != 4
	 * roleType =0
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getAcRoleByRoleTypeNot4(HashMap<String, String> cdMap)
			throws Exception {
		List<AcRole> list = this.queryForList(
					"OMEMPLOYEE.queryAllRolesToGrantNot4", cdMap);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countRoleByRoleTypeNot4(HashMap cdMap) throws Exception{
		return  Long.parseLong(String.valueOf(this.queryForObject("OMEMPLOYEE.getRoleByRoleTypeNot4_countNums", cdMap)));
	}
	
	
	/**
	 * 根据roleType查询业务角色或系统角色
	 * roleType "0"表示业务角色，"1"表示系统角色
	 * roleType =1
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getAcRoleByRoleType(HashMap<String, String> cdMap)
			throws Exception {
		List<AcRole> list = this.queryForList(
					"OMEMPLOYEE.queryAllRolesToGrant", cdMap);
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countRoleByRoleType(HashMap cdMap) throws Exception{
		long count =Long.parseLong(String.valueOf(this.queryForObject("OMEMPLOYEE.getRoleByRoleType_countNums", cdMap)));
		return  count;
	}
	/**
	 * 查询人员的人员机构
	 * @param hmp
	 * @return 人员机构集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OmEmporg> selectEmpOrg(HashMap hmp)throws Exception{
		return this.queryForList("OMEMPLOYEE.selectEmpOrg",hmp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateEmpInfoTransc(HashMap paramMap) throws Exception {
		String empid =paramMap.get("empid").toString();
		this.startBatch();
		//更新
		
		this.update("OMEMPLOYEE.update_save_omemployee", paramMap);
		
		this.update("OMEMPLOYEE.update_save_ygfjxxb", paramMap);
		
		this.update("OMEMPLOYEE.update_save_acoperator", paramMap);
		
		Object xtObj =paramMap.get("xt_mngrole");
		Object ywObj = paramMap.get("yw_mngrole");
		Object orgListObj =paramMap.get("orgidlist");
		Object positionObj = paramMap.get("position");
		if((xtObj!=null && !"".equals(xtObj) &&!"null".equals(xtObj))){
			//删除系统角色
			paramMap.put("roletypes", 1);
			this.delete("OMEMPLOYEE.delOperroleByOpid", paramMap);
		}
		if(ywObj!=null && !"".equals(ywObj) &&!"null".equals(ywObj)){
			//删除业务角色
			paramMap.put("roletypes", 0);
			this.delete("OMEMPLOYEE.delOperroleByOpid", paramMap);
		}
		if(orgListObj!=null && !"".equals(orgListObj) && !"null".equals(orgListObj)){
			//	删除可管理机构
			this.delete("OMEMPLOYEE.delrykgljggByEmpid", paramMap);
		}
		
		if(positionObj!=null && !"".equals(positionObj) && !"null".equals(positionObj)){
			//删除人员岗位
			this.delete("OMEMPLOYEE.deletePosition", empid);
		}
		//	插入系统角色
		if((xtObj!=null && !"".equals(xtObj) &&!"null".equals(xtObj))){
			String xt_mngrole = String.valueOf(xtObj);
			String[] xtArr =xt_mngrole.split(",");
			for(int i=0;i<xtArr.length;i++){
				String mngCode =xtArr[i];
				if(StringUtils.isBlank(mngCode)){
					continue;
				}
				HashMap hmp =new HashMap();
				hmp.put("roleid", mngCode);
				hmp.put("empid", empid);
//				agency=0 表示业务角色， agency=1 表示 系统角色
				hmp.put("agency", "1");
				this.insert("OMEMPLOYEE.insert_ac_operrole", hmp);
			}
		}
		
		// 插入业务角色
		if(ywObj!=null && !"".equals(ywObj) &&!"null".equals(ywObj)){
			String yw_mngrole = String.valueOf(ywObj);
			String[] ywArr =yw_mngrole.split(",");
			for(int i=0;i<ywArr.length;i++){
				String mngCode =ywArr[i];
				if(StringUtils.isBlank(mngCode)){
					continue;
				}
				HashMap hmp =new HashMap();
				hmp.put("roleid", mngCode);
				hmp.put("empid", empid);
//				agency=0 表示业务角色， agency=1 表示 系统角色
				hmp.put("agency", "0");
				this.insert("OMEMPLOYEE.insert_ac_operrole", hmp);
			}
			
		}
		
		
		String empCode =paramMap.get("empCode").toString();
		String empName =paramMap.get("empName").toString();
		
		//	插入可管理机构
		if(orgListObj!=null && !"".equals(orgListObj) && !"null".equals(orgListObj)){
			List paramOrgList = new ArrayList();
			StringBuffer sbr = new StringBuffer();
			if(orgListObj instanceof ArrayList){
				paramOrgList = (List)orgListObj;
			}else{
				paramOrgList.add(orgListObj);
			}
			for (int i = 0; i < paramOrgList.size(); i++) {
				sbr.append("'"+paramOrgList.get(i)+"'");
				if(i<paramOrgList.size()-1){
					sbr.append(",");
				}
			}
			log.info("----orgListObj-"+orgListObj);
			List<HashMap> orgInfoList=this.queryForList("OMEMPLOYEE.queryOrgStrList", orgListObj);
			for(int i=0;i<orgInfoList.size();i++){
				HashMap hmp =orgInfoList.get(i);
				hmp.put("empid", empid);
				hmp.put("EMPCODE", empCode);
				hmp.put("EMPNAME", empName);
				this.insert("OMEMPLOYEE.insert_abft_kgljg", hmp);
			}
			
		}
	
		if(positionObj!=null && !"".equals(positionObj) && !"null".equals(positionObj)){
			String positins = String.valueOf(positionObj);
			String[] posiArray = positins.split(",");
			for(String position : posiArray){
				HashMap< String, String> maps = new HashMap<String, String>(2);
				maps.put("empid", empid);
				maps.put("positionid", position);
				this.insert("OMEMPLOYEE.insertPosition",maps);
			}
		}
		this.executeBatch();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryMngorgByStartId(HashMap<String,String> cdMap) throws Exception {
		if(cdMap.containsKey("startOrgid")){
			return this.queryForList("OMEMPLOYEE.queryOrgByStartId",cdMap);
		}else if(cdMap.containsKey("startOrgcode")){
			return this.queryForList("OMEMPLOYEE.queryOrgByStartCode",cdMap);
		}else if(cdMap.containsKey("manager")){
			return this.queryForList("OMEMPLOYEE.queryEmpManagerOrg",cdMap);
		}else{
			return this.queryForList("OMEMPLOYEE.queryOrgByJgsx",cdMap);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryChildEmpNodes(HashMap cdMap) throws Exception {
		if(cdMap.get("orgid")==null && cdMap.get("orgId")==null){
			HashMap oParentOrg = (HashMap)cdMap.get("oParentOrg");
			if(oParentOrg!=null){
				Object orgId = oParentOrg.get("orgId");
				if(orgId!=null){
					cdMap.put("orgId", orgId);
				}else{
					throw new GotopException("orgid 不存在");
				}
			}else{
				throw new GotopException("orgid 不存在");
			}
		}
		return this.queryForList("OMEMPLOYEE.select_orgemp_org",cdMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryChildOrgNodes(HashMap tempMap) throws Exception {
		List orgNodeList =this.queryForList("OMEMPLOYEE.queryTreeChildOrgNodes",tempMap);
		return orgNodeList;
	}

	@Override
	public List<OmPosition> queryOmpositRecList(HashMap hmp) throws Exception {
		return this.queryForList("OMEMPLOYEE.queryOmpositObjList",hmp);
	}
	/**
	 * 新增人员归属机构
	 * @param emporg 人员归属机构信息
	 * @throws Exception
	 */
	public void insertEmpOrg(OmEmporg emporg) throws Exception{
		this.insert("OMEMPLOYEE.insertEmpOrg", emporg);
	}
	/**
	 * 删除人员归属机构
	 * @param emporg 待删除的信息
	 * @throws Exception
	 */
	public void deleteEmpOrg(OmEmporg emporg)throws Exception{
		this.delete("OMEMPLOYEE.deleteEmpOrg", emporg);
	}
	/**
	 * 更新人员归属机构
	 * @param emporg 待更新的信息
	 * @throws Exception
	 */
	public void updateEmpOrg(OmEmporg emporg)throws Exception{
		this.update("OMEMPLOYEE.updateEmpOrg",emporg);
	}
	/**
	 * 验证用户的empcode
	 * @param empcode 待验证的code
	 * @return 数量
	 * @throws Exception
	 */
	public int checkEmpCode(String empcode)throws Exception{
		Object object = this.queryForObject("OMEMPLOYEE.checkEmpCode",empcode);
		return Integer.parseInt(String.valueOf(object));
	}
	/**
	 * 查询人员岗位
	 * @param empid
	 * @throws Exception
	 */
	public OmPosition selectEmpPosition(String empid) throws Exception{
		Object object = this.queryForObject("OMEMPLOYEE.selectEmpPosition", empid);
		if(object != null){
			return (OmPosition)object;
		}
		return null;
	}
	/**
	 * 修改用户密码
	 * @param hmp
	 * @throws Exception
	 */
	public void updateOperatorPassword(HashMap<String, String> hmp) throws Exception{
		this.update("OMEMPLOYEE.updateOperatorPassword", hmp);
	}

	@Override
	public String queryEmpOrgid(String empid) throws Exception {
		HashMap<String, Object> hmp=new HashMap<String, Object>();
		hmp.put("empid", empid);
		// TODO Auto-generated method stub
	List<OmEmporg> list=this.queryForList("OMEMPLOYEE.select_EmpOrgid",hmp);
	if(list.size()>0){
		return list.get(0).getOrgid();
	}
		return null;
	}
}
