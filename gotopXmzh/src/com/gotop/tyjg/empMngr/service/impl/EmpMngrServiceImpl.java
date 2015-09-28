package com.gotop.tyjg.empMngr.service.impl;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.gotop.tyjg.empMngr.dao.IEmpMngrDao;
import com.gotop.tyjg.empMngr.model.EmployeeTm;
import com.gotop.tyjg.empMngr.model.OmEmporg;
import com.gotop.tyjg.empMngr.model.validate;
import com.gotop.tyjg.empMngr.service.IEmpMngrService;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.vo.tyjg.Abftygfjxxb;
import com.gotop.vo.tyjg.AcOperator;
import com.gotop.vo.tyjg.AcRole;
import com.gotop.vo.tyjg.OmEmpOrg;
import com.gotop.vo.tyjg.OmPosition;
import com.gotop.vo.tyjg.Omemployee;
import com.gotop.vo.tyjg.Omorganization;
import com.primeton.utils.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class EmpMngrServiceImpl implements IEmpMngrService  {

	protected static Logger log = Logger.getLogger(EmpMngrServiceImpl.class);
	private IEmpMngrDao empMngrDao;

	public IEmpMngrDao getEmpMngrDao() {
		return empMngrDao;
	}

	public void setEmpMngrDao(IEmpMngrDao empMngrDao) {
		this.empMngrDao = empMngrDao;
	}

	@Override
	public List<Omorganization> initOrgInfo(Long empid) throws Exception {
		// 查询登录人员的可管理机构
		return empMngrDao.getUserMngrOrg(empid);
	}

	@Override
	public List<Omorganization> queryChildNode(Long parentOrgId)
			throws Exception {
		// 根据父节点查询子节点
		return this.getEmpMngrDao().queryChildNodes(parentOrgId);
	}

	@Override
	public List initOrgEmpTree(String parentorgid) throws Exception {
		// 查询有员工的机构
		List orgList =this.empMngrDao.selectOrgById(parentorgid);
		return orgList;
	}

	@Override
	public List queryOrgEmpNodes(Map param) throws Exception {
		// 查询机构下用户
		return this.empMngrDao.queryOrgEmpNodes(param);
	}

	@Override
	public int isEmpOrgExist(OmEmpOrg empOrg) {
		// 查询拖拉前机构是否存在
		boolean flag =false;
		HashMap tempMap = new HashMap();
		tempMap.put("empid", empOrg.getEmpid());
		tempMap.put("orgid", empOrg.getOrgid());
		int num= this.empMngrDao.countEmpOrg(tempMap);
		return num;
	}

	@Override
	public boolean isOrgToExist(OmEmpOrg empOrg) {
		// 判断机构是否存在
		boolean bz =false;
		HashMap tempMap = new HashMap();
		tempMap.put("orgid", empOrg.getOrgid());
		this.empMngrDao.queryOrgById(tempMap);
		return true;
	}

	@Override
	public boolean addNewEmpOrg(OmEmpOrg empOrgFrom,OmEmpOrg empOrgTo) {
		// 新增机构人员信息
		HashMap tempMap = new HashMap();
		Date currTime =new Date();
		tempMap.put("empid", empOrgFrom.getEmpid());
		tempMap.put("orgid", empOrgTo.getOrgid());
		tempMap.put("ismain", "n");
		tempMap.put("modflag", "A");
		tempMap.put("lastupdate", currTime);
		tempMap.put("datasource", "2");
		boolean tag =this.empMngrDao.addEmpOrgRec(tempMap);
		return tag;
	}


	@Override
	public boolean delEmpOrgObj(OmEmpOrg empOrg) {
		// TODO Auto-generated method stub
		boolean tag =false;
		HashMap tempMap = new HashMap();
		tempMap.put("empid", empOrg.getEmpid());
		tempMap.put("orgid", empOrg.getOrgid());
		this.empMngrDao.delEmpOrgObj(tempMap);
		return true;
	}

	@Override
	public boolean updateOrgidToEmployee(OmEmpOrg empOrg) {
		// 修改机构人员信息
		boolean flag =false;
		HashMap tempMap = new HashMap();
		tempMap.put("empid", empOrg.getEmpid());
		tempMap.put("orgid", empOrg.getOrgid());
		this.empMngrDao.updateOrgidToEmployee(tempMap);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryEmpUnOrgDataGrid(HashMap cdMap, Page page) throws Exception {
		// TODO Auto-generated method stub
		cdMap.put("oracleStart", page.getBegin());
		cdMap.put("oracleEnd", page.getBegin()+page.getLength());
		List empList =this.empMngrDao.selectEmpUnOrgList(cdMap);
		int count =this.empMngrDao.countEmpUdOrg(cdMap);
		page.setCount(count);
		return empList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateGrid(HashMap hmp) throws Exception {
		// 修改DataGrid中记录的排序
		this.empMngrDao.startBatch();
		List insertEntities = (List) hmp.get("insertEntities");
		@SuppressWarnings("unused")
		List updateEntities = (List) hmp.get("updateEntities");
		this.updateBatch(insertEntities);
//		this.insertBatch(updateEntities);
		this.empMngrDao.executeBatch();
	}


	/**
	 * 批量更新数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void updateBatch(List abs) throws Exception {
		for (int i = 0; i < abs.size(); i++) {
			Omemployee emp = (Omemployee) abs.get(i);
			HashMap hmp =new HashMap();
			String empid =emp.getEmpid();
			String displayOrder =emp.getDisplayOrder();
			hmp.put("empid", empid);
			hmp.put("displayorder", displayOrder);
		}
	}
	
	
	/**
	 * 更新单条记录，通过主键
	 */
	@SuppressWarnings("unchecked")
	public void updateEmpOrder(List<HashMap> hmpList) throws Exception {
		this.empMngrDao.updateEmpOrder(hmpList);
	}
	
	/**
	 * 查询不分页机构下人员信息列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List queryEmpUnOrgList(HashMap cdMap) throws Exception {
		// TODO Auto-generated method stub
		List empList =this.empMngrDao.selectEmpUnOrgList(cdMap);
		return empList;
	}
	
	
	@Override
	public void updateGridRecOrdr(List<HashMap> hmpList) throws Exception {
		// 修改员工记录的排序
		this.empMngrDao.updateEmpOrder(hmpList);
	}

	@Override
	public List<Omemployee> queryEmpByOrgId(Long orgid) throws Exception {
		// 查询机构下人员信息
		List<Omemployee> emplist =this.empMngrDao.queryEmpNodes(orgid);
		return emplist;
	}

	@Override
	public Tomorganization queryOrgInfoById(Long orgid) throws Exception {
		// 根据id查询机构信息
		HashMap hmp =new HashMap();
		hmp.put("orgid", orgid);
		Tomorganization orgVO =(Tomorganization)this.empMngrDao.queryOrgVoInfo(hmp);
		return orgVO;
	}

	@Override
	public Abftjgfjxx queryJgfjxxbInfoById(Long orgid) throws Exception {
		// 根据机构id查询机构附件信息表信息
		HashMap hmp =new HashMap();
		hmp.put("orgid", orgid);
		Abftjgfjxx tjgfjxxbVO =(Abftjgfjxx)this.empMngrDao.queryFjxxb(hmp);
		return tjgfjxxbVO;
	}
	
	
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return 角色对象
	 * @throws Exception
	 */
	public PartyRole selectOrgAlreayGrantRoles(String orgid) throws Exception{
		PartyRole partyRole = new PartyRole();
		List<PartyRole> roleList = this.empMngrDao.selectOrgAlreayGrantRoles(orgid);
		for(PartyRole role : roleList){
			partyRole.setSpecialTyname(role.getSpecialTyname());
			partyRole.setRoleId(role.getRoleId());
		}
		return partyRole;
	}

	
	/**
	 * 查询员工基本信息
	 */
	@Override
	public EmployeeTm queryEmpBaseInfo(String empid) throws Exception {
		List<EmployeeTm> employList =this.empMngrDao.queryEmpBaseInfo(empid);
		EmployeeTm empBaseInfo =null;
		if(employList!=null && employList.size()>0){
			empBaseInfo =employList.get(0);
			String cardNo = empBaseInfo.getCardNo();
			if(StringUtils.isNotBlank(cardNo) && !"null".equals(cardNo)){
				int size = cardNo.length();
				if(size == 15){
					//15位身份证号码升级为18位
					validate validate = new validate();
					empBaseInfo.setCardNo(validate.upperToEighteen(cardNo));
				}
			}
		}
		return empBaseInfo;
	}

	
	/**
	 * 查询员工附加信息表
	 */
	@Override
	public Abftygfjxxb queryYgfjxx(String empid) throws Exception {
		Abftygfjxxb abfygfjxx = null;
		List<Abftygfjxxb> afbFjList =this.empMngrDao.queryYgfjxx(empid);
		if(afbFjList!=null && afbFjList.size()>0){
			abfygfjxx =afbFjList.get(0);
		}
		return abfygfjxx;
	}

	@Override
	public AcOperator queryEmpAcOperInfo(String userid) throws Exception {
		AcOperator acOper =null;
		List<AcOperator> acOpList =this.empMngrDao.queryEmpAcOperInfo(userid);
		if(acOpList!=null && acOpList.size()>0){
			acOper =acOpList.get(0);
		}
		return acOper;
	}

	@Override
	public Tomorganization queryEmpOrgInfo(String orgid) throws Exception {
		Tomorganization organiz =null;
		List<Tomorganization> tomOrgList =this.empMngrDao.queryEmpOrgInfo(orgid);
		if(tomOrgList!=null && tomOrgList.size()>0){
			organiz =tomOrgList.get(0);
		}
		return organiz;
	}

	// 查询员工已分配的角色, "0"表示业务角色，"1"表示系统角色
	@Override
	public AcRole quertyEmpAlReadyGrantRole(String empid,String roleType) throws Exception {
		AcRole xtAcRole =null;
		List<AcRole> acRoleList =this.empMngrDao.quertyEmpAlReadyGrantRole(empid,roleType);
		if(acRoleList!=null && acRoleList.size()>0){
			xtAcRole =acRoleList.get(0);
		}
		return xtAcRole;
	}

	@Override
	public int countEmpByNo(HashMap hmp) throws Exception {
		int cns =this.empMngrDao.countEmpByno(hmp);
		return cns;
	}

	@Override
	public String mutilCardNoPromt(HashMap hmp) throws Exception {
		String promptMess =this.empMngrDao.queryEmpPromtMess(hmp);
		return promptMess;
	}

	@Override
	public List queryEmpOrgBelongDataCell(HashMap map, Page page)
			throws Exception {
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		List empList =this.empMngrDao.selectEmpOrg(map);
		int count =this.empMngrDao.countEmpOrgNum(map);
		page.setCount(count);
		return empList;
	}

	@Override
	public boolean copyEmpTransct(HashMap map) throws Exception {
		// TODO Auto-generated method stub
		return this.getEmpMngrDao().copyEmpTransct(map);
	}

	@Override
	public boolean moveEmpToOrgTranc(HashMap cdMap) throws Exception {
		// 拖动人员节点到其他机构
		return this.empMngrDao.moveEmpNodeTransc(cdMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addEmpInfo(HashMap paramMap) throws Exception {
		HashMap cdMap =new HashMap();
		HashMap hmp =new HashMap();
		String birthDateStr =null;
		String indateStr =null;
		String outdateStr =null;
		Date birthDate =null;
		Date indate =null;
		Date outdate =null;
		
		Date nowTime =new Date();
		paramMap.put("createTime", nowTime);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Object birObj =paramMap.get("birthDate");
		if(birObj!=null){
			birthDateStr =birObj.toString();
			birthDate =sdf.parse(birthDateStr);
			paramMap.put("birthDateDt", birthDate);
		}
		
		Object indObj =paramMap.get("indate");
		if(indObj!=null){
			indateStr =indObj.toString();
			indate =sdf.parse(indateStr);
			paramMap.put("indateDt", indate);
		}
		
		Object outObj =paramMap.get("outdate");
		if(outObj!=null){
			outdateStr =outObj.toString();
			outdate =sdf.parse(outdateStr);
			paramMap.put("outdateDt", outdate);
		}
		
		
//		long operOn =0;
		hmp.clear();
		hmp.put("seqName", "OM_EMPLOYEE_SEQ");
		Long empNo =getNextSeqVal(hmp);
		String empid =null;
		if(empNo!=null){
			empid =empNo.toString();
		}
		
		paramMap.put("empid", empid);
		paramMap.put("operatorid", empid);
		paramMap.put("ismain", "y");
		paramMap.put("modflag", "1");
		paramMap.put("lastUpdate", nowTime);
		paramMap.put("datasource", "2");
		return this.empMngrDao.addEmpInfoTransc(paramMap);
	}

	@Override
	public boolean resetOperPwd(HashMap cdMap) throws Exception {
		return this.empMngrDao.resetOperPwd(cdMap);
	}

	@Override
	public Long getNextSeqVal(HashMap<String, String> hmp) throws Exception {
		return this.empMngrDao.getNextSeqVal(hmp);
	}

	@Override
	public String getAcRoleExtendById(HashMap<String, String> cdMap)
			throws Exception {
		AcRole acRole =this.empMngrDao.getAcRoleAuto(cdMap);
		String auto =null;
		if(acRole!=null){
			auto =acRole.getAuto();
		}
		
		return auto;
	}

	
	/**
	 * 根据roleType查询角色列表
	 * @param roleType "0"表示业务角色，"1"表示系统角色
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getXtRoleListByRoleType(HashMap cdMap, Page page) throws Exception {
		List roleList =null;
		long count =0;
		String orgDegree  =cdMap.get("orgDegree").toString();
		String roleType  =cdMap.get("roleType").toString();
		cdMap.put("oracleStart", page.getBegin());
		cdMap.put("oracleEnd", page.getBegin()+page.getLength());
		if("0".equals(roleType)){
			if("4".equals(orgDegree)){
				roleList =this.empMngrDao.getAcRoleByRoleTypeOn4(cdMap);
				count =this.empMngrDao.countRoleByRoleTypeOn4(cdMap);
			}else{
				roleList =this.empMngrDao.getAcRoleByRoleTypeNot4(cdMap);
				count =this.empMngrDao.countRoleByRoleTypeNot4(cdMap);
			}
		}else{
			roleList =this.empMngrDao.getAcRoleByRoleType(cdMap);
			count =this.empMngrDao.countRoleByRoleType(cdMap);
		}
		
		page.setCount(Integer.parseInt(String.valueOf(count)));
		return roleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List getYwRoleListByRoleType(HashMap cdMap, Page page)
			throws Exception {
		List roleList =null;
		long count =0;
		String orgDegree  =cdMap.get("orgDegree").toString();
		String roleType  =cdMap.get("roleType").toString();
		cdMap.put("oracleStart", page.getBegin());
		cdMap.put("oracleEnd", page.getBegin()+page.getLength());
		if("0".equals(roleType)){
			if("4".equals(orgDegree)){
				roleList =this.empMngrDao.getAcRoleByRoleTypeOn4(cdMap);
				count =this.empMngrDao.countRoleByRoleTypeOn4(cdMap);
			}else{
				roleList =this.empMngrDao.getAcRoleByRoleTypeNot4(cdMap);
				count =this.empMngrDao.countRoleByRoleTypeNot4(cdMap);
			}
		}else{
			roleList =this.empMngrDao.getAcRoleByRoleType(cdMap);
			count =this.empMngrDao.countRoleByRoleType(cdMap);
		}
		
		page.setCount(Integer.parseInt(String.valueOf(count)));
		return roleList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateEmpInfo(HashMap paramMap) throws Exception {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		Date birthDateDt =null;
		Object birthDateObj =paramMap.get("birthDate");
		if(birthDateObj!=null && !"".equals(birthDateObj)){
			String birthDateStr =birthDateObj.toString();
			birthDateDt =sdf.parse(birthDateStr);
			paramMap.put("birthDateDt", birthDateDt);
		}
		
		Date indateDt =null;
		Object indateObj =paramMap.get("indate");
		if(indateObj!=null && !"".equals(indateObj)){
			String indateObjStr =indateObj.toString();
			indateDt =sdf.parse(indateObjStr);
			paramMap.put("indateDt", indateDt);
		}
		
		Date outdateDt =null;
		Object outdateObj =paramMap.get("outdate");
		if(outdateObj!=null && !"".equals(outdateObj)){
			String outdateStr =outdateObj.toString();
			outdateDt =sdf.parse(outdateStr);
			paramMap.put("outdateDt", outdateDt);
		}
		
		boolean flag =this.empMngrDao.updateEmpInfoTransc(paramMap);
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryMngorgOfLogon(HashMap<String,String> cdMap) throws Exception {
		return this.empMngrDao.queryMngorgByStartId(cdMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryTreeChildEmpNodes(HashMap cdMap) throws Exception {
		return this.getEmpMngrDao().queryChildEmpNodes(cdMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List queryTreeChildOrgNodes(HashMap cdMap) throws Exception {
		HashMap tempMap =(HashMap)cdMap.get("oParentOrg");
		return this.getEmpMngrDao().queryChildOrgNodes(tempMap);
	}

	@Override
	public List getOmpositionList(String posiName,Page page) throws Exception {
		HashMap map =new HashMap();
		map.put("oracleStart", page.getBegin());
		map.put("oracleEnd", page.getBegin()+page.getLength());
		map.put("posiName", posiName);
		List ompositList =this.empMngrDao.queryOmpositRecList(map);
		int count =this.empMngrDao.countPositionNum(map);
		page.setCount(count);
		return ompositList;
	}
	/**
	 * 保存人员归属机构
	 * @param insertList 新增归属机构信息
	 * @param updateList 修改归属机构信息
	 * @param deleteList 删除归属机构信息
	 * @throws Exception
	 */
	public void saveEmpOrg(List<OmEmporg> insertList,List<OmEmporg> updateList,List<OmEmporg> deleteList)throws Exception{
		this.getEmpMngrDao().startBatch();
		//新增人员归属机构
		if(insertList != null){
			for (OmEmporg omEmporg : insertList) {
				this.getEmpMngrDao().insertEmpOrg(omEmporg);
			}
		}
		//修改人员归属机构
		if(updateList != null){
			for (OmEmporg omEmporg : updateList) {
				this.getEmpMngrDao().updateEmpOrg(omEmporg);
			}
		}
		//删除人员归属机构
		if(deleteList != null){
			for (OmEmporg omEmporg : deleteList) {
				this.getEmpMngrDao().deleteEmpOrg(omEmporg);
			}
		}
		this.getEmpMngrDao().executeBatch();
	}
	/**
	 * 设置主机构
	 * @param hmp 人员机构信息
	 * @throws Exception
	 */
	public void updateMainOrg(HashMap<String, String> hmp) throws Exception{
		List<OmEmporg> list = this.getEmpMngrDao().selectEmpOrg(hmp);
		this.getEmpMngrDao().startBatch();
		for (OmEmporg omEmporg : list) {
			if(hmp.get("orgid").equals(omEmporg.getOrgid())){
				omEmporg.setIsmain("y");
			}else{
				omEmporg.setIsmain("n");
			}
			this.getEmpMngrDao().updateEmpOrg(omEmporg);
		}
		this.getEmpMngrDao().executeBatch();
	}
	/**
	 * 验证用户的empcode
	 * @param empcode 待验证的code
	 * @return 数量
	 * @throws Exception
	 */
	public int checkEmpCode(String empcode)throws Exception{
		return this.getEmpMngrDao().checkEmpCode(empcode);
	}
	/**
	 * 查询人员岗位
	 * @param empid
	 * @throws Exception
	 */
	public OmPosition selectEmpPosition(String empid) throws Exception{
		return this.getEmpMngrDao().selectEmpPosition(empid);
	}
	/**
	 * 修改用户密码
	 * @param hmp
	 * @throws Exception
	 */
	public void updateOperatorPassword(HashMap<String, String> hmp) throws Exception{
		String password = hmp.get("newPassword");
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
		String	Encryption = baseEncoder.encode(md5.digest(password.getBytes("utf-8")));
		hmp.put("newPassword", Encryption);
		this.getEmpMngrDao().updateOperatorPassword(hmp);
	}
}
