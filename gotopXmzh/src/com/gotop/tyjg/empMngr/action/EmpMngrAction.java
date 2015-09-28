package com.gotop.tyjg.empMngr.action;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.gotop.crm.util.BaseAction;

import com.gotop.tyjg.common.model.ChangeTree;
import com.gotop.tyjg.empMngr.model.EmployeeTm;
import com.gotop.tyjg.empMngr.model.OmEmporg;
import com.gotop.tyjg.empMngr.service.IEmpMngrService;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.StringUtil;
import com.gotop.vo.system.MUOUserSession;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.service.IOrganizationService;
import com.gotop.vo.tyjg.Abftygfjxxb;
import com.gotop.vo.tyjg.AcOperator;
import com.gotop.vo.tyjg.AcRole;
import com.gotop.vo.tyjg.OmEmpOrg;
import com.gotop.vo.tyjg.OmPosition;
import com.gotop.vo.tyjg.Omemployee;
import com.gotop.vo.tyjg.Omorganization;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.XmlHelper;
import com.primeton.utils.pageCondExpand;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
@SuppressWarnings("unchecked")
public class EmpMngrAction extends BaseAction{

	private static final long serialVersionUID = 7369335063010192183L;

	protected static Logger log = Logger.getLogger(EmpMngrAction.class);
	
	private Abftjgfjxx abftjgfjxx;
	
	private Abftygfjxxb abfygfjxx;
	
	private AcOperator acOper;
	
	private ChangeTree changeTree;
	
	private List<Omemployee> empList = new ArrayList<Omemployee>();
	
	private IEmpMngrService empMngrService;
	
	private OmPosition oaPosi;
	
	private List<OmPosition> oaPosiList = new ArrayList<OmPosition>();
	
	private Omemployee omEmp;
	
	private EmployeeTm omEmpTm;
	
	private Omorganization omorganization;
	
	private List<Omorganization> orgList = new ArrayList<Omorganization>();
	
	private HashMap param = new HashMap();
	
	private PartyRole partyRole;
	
	private OmPosition omPosition;
	
	
	private Tomorganization tomorganiz;

	private AcRole xtAcRole;

	private AcRole ywAcRole;
	
	private IOrganizationService orgService;
	
	public IOrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrganizationService orgService) {
		this.orgService = orgService;
	}
	
	public Abftjgfjxx getAbftjgfjxx() {
		return abftjgfjxx;
	}

	public Abftygfjxxb getAbfygfjxx() {
		return abfygfjxx;
	}

	public AcOperator getAcOper() {
		return acOper;
	}
	
	public ChangeTree getChangeTree() {
		return changeTree;
	}

	public List<Omemployee> getEmpList() {
		return empList;
	}
	
	public IEmpMngrService getEmpMngrService() {
		return empMngrService;
	}

	public OmPosition getOaPosi() {
		return oaPosi;
	}

	public List<OmPosition> getOaPosiList() {
		return oaPosiList;
	}

	public Omemployee getOmEmp() {
		return omEmp;
	}
	
	public EmployeeTm getOmEmpTm() {
		return omEmpTm;
	}

	public Omorganization getOmorganization() {
		return omorganization;
	}
	
	public void setAbftjgfjxx(Abftjgfjxx abftjgfjxx) {
		this.abftjgfjxx = abftjgfjxx;
	}
	
	public void setAbfygfjxx(Abftygfjxxb abfygfjxx) {
		this.abfygfjxx = abfygfjxx;
	}
	
	public void setAcOper(AcOperator acOper) {
		this.acOper = acOper;
	}

	
	public void setChangeTree(ChangeTree changeTree) {
		this.changeTree = changeTree;
	}

	
	public void setEmpList(List<Omemployee> empList) {
		this.empList = empList;
	}

	
	
	public void setEmpMngrService(IEmpMngrService empMngrService) {
		this.empMngrService = empMngrService;
	}
	
	public void setOmEmpTm(EmployeeTm omEmpTm) {
		this.omEmpTm = omEmpTm;
	}
	
	
	public void setOmorganization(Omorganization omorganization) {
		this.omorganization = omorganization;
	}
	
	public void setOrgList(List orgList) {
		this.orgList = orgList;
	}
	
	
	public void setParam(HashMap param){
		this.param =param;
	}
	
	
	public void setPartyRole(PartyRole partyRole) {
		this.partyRole = partyRole;
	}
	
	

	public void setTomorganiz(Tomorganization tomorganiz) {
		this.tomorganiz = tomorganiz;
	}
	
	
	public void setXtAcRole(AcRole xtAcRole) {
		this.xtAcRole = xtAcRole;
	}
	public List<Omorganization> getOrgList() {
		return orgList;
	}
	public HashMap getParam(){
		return param;
	}

	public PartyRole getPartyRole() {
		return partyRole;
	}
	public OmPosition getOmPosition() {
		return omPosition;
	}

	public void setOmPosition(OmPosition omPosition) {
		this.omPosition = omPosition;
	}
	public Tomorganization getTomorganiz() {
		return tomorganiz;
	}
	
	public AcRole getXtAcRole() {
		return xtAcRole;
	}
	public AcRole getYwAcRole() {
		return ywAcRole;
	}
	public void setOaPosi(OmPosition oaPosi) {
		this.oaPosi = oaPosi;
	}
	
	public void setOaPosiList(List<OmPosition> oaPosiList) {
		this.oaPosiList = oaPosiList;
	}
	
	
	public void setOmEmp(Omemployee omEmp) {
		this.omEmp = omEmp;
	}
	/**
	 * 获得单个汉字的Ascii.
	 * 
	 * @param cn
	 *            char 汉字字符
	 * @return int 错误返回 0,否则返回ascii
	 */
	public static int getCnAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
			return 0;
		}
		if (bytes.length == 1) { // 英文字符
			return bytes[0];
		}
		if (bytes.length == 2) { // 中文字符
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];
			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			return ascii;
		}
		return 0; // 错误
	}
	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii
	 *            int 字符对应的ASCII
	 * @return String 拼音,首先判断ASCII是否>0&<160,如果是返回对应的字符, <BR>
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	@SuppressWarnings("unchecked")
	public static String getSpellByAscii(int ascii) {
		LinkedHashMap spellMap = null;
		if (ascii > 0 && ascii < 160) { // 单字符
			return String.valueOf((char) ascii);
		}
		if (ascii < -20319 || ascii > -10247) { // 不知道的字符
			return null;
		}
		Set keySet = spellMap.keySet();
		Iterator it = keySet.iterator();
		String spell0 = null;
		String spell = null;
		int asciiRang0 = -20319;
		int asciiRang;
		while (it.hasNext()) {
			spell = (String) it.next();
			Object valObj = spellMap.get(spell);
			if (valObj instanceof Integer) {
				asciiRang = ((Integer) valObj).intValue();
				if (ascii >= asciiRang0 && ascii < asciiRang) { // 区间找到
					return (spell0 == null) ? spell : spell0;
				} else {
					spell0 = spell;
					asciiRang0 = asciiRang;
				}
			}
		}
		return null;
	}
	/*
	 * 专有名词过滤
	 */
	private static String properNoun(String o){
		if(o==null){
			return "";
		}
		o=o.replace("行", "h");
		o=o.replace("作", "z");
		return o;
	}

	
	
	public String cardNoIsExist() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><empid_count>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			//String cardno =hmp.get("cardNo");
			int strCn = this.empMngrService.countEmpByNo(hmp);
			String promtMess =this.empMngrService.mutilCardNoPromt(hmp);
			buffer.append(strCn);
			buffer.append("</empid_count><promtMess>").append(promtMess).append("</promtMess>");
		}catch (Exception e) {
			buffer.append("1");
			buffer.append("</empid_count><promtMess>验证身份证失败</promtMess>");
			log.error("【验证身份证失败】", e);
		}finally{
			buffer.append("</data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	
	/**
	 * 查询员工归属机构datacell列表
	 * @return
	 * @throws Exception
	 */
	public String empOrgBelongDataCell() throws Exception{
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		HashMap hmp = apm.getParams();
		String empid = String.valueOf(hmp.get("oEmp.empid"));
		hmp.put("empid", empid);
		List dataList = this.empMngrService.queryEmpOrgBelongDataCell(hmp, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		String xmlStr = XmlConvert.getXmlListBean(page,dataList);
		this.write(xmlStr);
		return null;
	}

	

	/**
	 * 根据角色id查询AcRole对象，返回auto
	 * @return
	 * @throws Exception
	 */
	public String getOrgDegreeByRoleid() throws Exception{
		HashMap<String,String> cdMap =new HashMap();
		String auto =this.getEmpMngrService().getAcRoleExtendById(cdMap);
		this.write(auto);
		return null;
	}

	/**
	 * 返回字符串的首拼,是汉字转化为全拼,其它字符不进行转换
	 * 
	 * @param cnStr String 字符串
	 * @return String 转换成全拼后的字符串
	 * @return
	 */
	public String getShouSpell() throws Exception {
		HashMap<String, String> hmp = XmlConvert.getParamsAjax();
		String cnStr = hmp.get("cnStr");
		if(StringUtils.isBlank(cnStr)){
			throw new  Exception("汉字转化为全拼参数：cnStr为空!");
		}
		StringBuffer retuBuf = new StringBuffer(100);
		retuBuf.append("<root><data><spStr>");
		retuBuf.append(StringUtil.tohypy(cnStr));
		retuBuf.append("</spStr></data></root>");
		this.write(retuBuf.toString());
		return null;
	}


	/**
	 * 设置系统角色时获取系统角色datacell列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getXtAcRoleListByRoleType() throws Exception{
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		HashMap hmp = XmlConvert.getParamsAjax();
		List dataList = this.empMngrService.getXtRoleListByRoleType(hmp,page);
		this.setPage(page);
		String xmlStr = XmlConvert.getXmlListBean(page,dataList);
		this.write(xmlStr);
		return null;
	}
	

	/**
	 * 设置系统角色时获取系统角色datacell列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getYwAcRoleListByRoleType() throws Exception{
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		HashMap hmp = XmlConvert.getParamsAjax();
		List<PartyRole> roleList = this.empMngrService.getYwRoleListByRoleType(hmp,page);
		this.setPage(page);
		String xml = XmlConvert.getXmlListBean(page,roleList);
		this.write(xml);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String initEntityParam() throws Exception{
		MUOUserSession musous = (MUOUserSession)this.getCurrentOnlineUser();
		Long orgpentityid =null;
		String jgsx =null;
		jgsx =musous.getOrgJgsx();
		if("2".equals(jgsx)){
			orgpentityid =musous.getOrgpostentityid();
		}else{
			orgpentityid =musous.getOrgentityid();
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<root><data><orgpentityid>").append(orgpentityid).append("</orgpentityid></data></root>");
		this.write(String.valueOf(buf.toString()));
		return null;
		
	}

	/**
	 * 新增时保存用户信息
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public String insertSaveEmpInfo() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><iRtn>");
			HashMap paramMap = XmlConvert.getParamsAjax();
			String rtn ="1";
			boolean flag =this.empMngrService.addEmpInfo(paramMap);
		if(flag){
			rtn ="0";
		}
		buffer.append(rtn);
		}catch (Exception e) {
			log.error("【新增或保存人员失败】", e);
			buffer.append("1");
		}finally{
			buffer.append("</iRtn></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 移动机构用户
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String moveEmpNode() throws Exception {
		OmEmpOrg oEmporgFrom =new OmEmpOrg();
		OmEmpOrg oEmporgTo =new OmEmpOrg();
		HashMap hmp =new HashMap();
		HashMap fromMap =new HashMap();
		HashMap toMap =new HashMap();
		HashMap paramMap = XmlConvert.getParamsAjax();
		HashMap fromOrg = (HashMap)paramMap.get("from");
		HashMap toOrg = (HashMap)paramMap.get("to");
		HashMap oempMap = (HashMap)paramMap.get("Omemployee");
		String isMoveOrCopy = paramMap.get("isMoveOrCopy").toString();
		String empid =oempMap.get("empid").toString();
		String fromOrgId =fromOrg.get("orgId").toString();
		String toOrgId =toOrg.get("orgId").toString();
		hmp.put("empid", empid);
		hmp.put("fromOrgid", fromOrgId);
		hmp.put("toOrgid", toOrgId);
		hmp.put("isMoveOrCopy", toOrg.get("isMoveOrCopy"));
		fromMap.put("orgid", fromOrgId);
		fromMap.put("empid", empid);
		oEmporgFrom =setOmEmpOrgObjFrom(fromMap);
		toMap.put("orgid", toOrgId);
		toMap.put("empid", empid);
		oEmporgTo =setOmEmpOrgObjTo(toMap);
		int fromNum =this.empMngrService.isEmpOrgExist(oEmporgFrom);
		if(fromNum<1){	// 原来的机构不存在
			return null;
		}
		boolean isToExt =this.empMngrService.isOrgToExist(oEmporgTo);
		if(!isToExt){		//	to的机构不存在
			return null;
		}
		int toNum =this.empMngrService.isEmpOrgExist(oEmporgTo);
		if(toNum<1){	// 新的人员机构记录不存在，可以新增
			if("copy".equals(isMoveOrCopy)){	// 拷贝人员，新增一条机构人员信息，ismain ="n"
				// 启动事务
				this.empMngrService.copyEmpTransct(hmp);
			}else{		
				// 拖拉人员,修改原来机构人员信息的机构id
				this.empMngrService.moveEmpToOrgTranc(hmp);
			}
		}else if(!"copy".equals(isMoveOrCopy)){		
			// 拖拉人员,修改原来机构人员信息的机构id
			hmp.put("isDouble", "true");	//	为true表示to的机构中已经有此用户，不需要新增，只需要删除
			this.empMngrService.moveEmpToOrgTranc(hmp);
		}
		return null;
	}

	/**
	 * 可管理机构树打开模态窗口
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String pageRespict() throws Exception{
		String orgType =this.getRequest().getParameter("orgType");
		param.clear();
		param.put("orgType", orgType);
		this.setParam(param);
		return "selectDialogOrgType";
	}
	
	@SuppressWarnings("unchecked")
	public String positionSelectWin2() throws Exception{
		Page page = this.getPage();
		oaPosiList =this.getEmpMngrService().getOmpositionList(omPosition.getPosiName(),page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);	//	
		this.setOaPosiList(oaPosiList);
		return "om_posit_list";
	}

	/**
	 * 查询应该基本信息和附加信息、机构信息、拥有的系统角色、业务角色
	 * @return
	 * @throws Exception
	 */
	public String queryEmpBaseAndFjxx() throws Exception{
		String empid =this.getRequest().getParameter("empid");
		String userid =this.getRequest().getParameter("userid");
		String orgid =this.getRequest().getParameter("orgId");
		String execType =this.getRequest().getParameter("execType");
		//	员工基本信息
		this.setOmEmpTm(this.empMngrService.queryEmpBaseInfo(empid));
		//	附加信息
		this.setAbfygfjxx(this.empMngrService.queryYgfjxx(empid));
		//	
		this.setAcOper(this.empMngrService.queryEmpAcOperInfo(userid));
		//	查询员工信息
		this.setTomorganiz(this.empMngrService.queryEmpOrgInfo(orgid));
		//	查询系统角色，"1"表示系统角色
		this.setXtAcRole(this.empMngrService.quertyEmpAlReadyGrantRole(empid,"1"));
		//	查询业务角色，"0"表示业务角色
		this.setYwAcRole(this.empMngrService.quertyEmpAlReadyGrantRole(empid,"0"));
		this.setOmPosition(this.getEmpMngrService().selectEmpPosition(empid));
		this.getRequest().setAttribute("execType", execType);
		return "empBaseAndFjxx";
	}
	/**
	 * 查询机构下人员信息datacell列表.
	 */
	@SuppressWarnings("unchecked")
	public String queryEmpOrgDataGrid() throws Exception {
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		HashMap hmp = apm.getParams();
		List dataList = this.empMngrService.queryEmpUnOrgDataGrid(hmp, page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);	//	
		String xmlStr = XmlConvert.getXmlListBean(page,dataList);
		//	bean 全路径
		this.write(xmlStr);
		return null;
	}

	/**
	 * 人员管理里面设置可管理机构树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryMngorgOfLogon() throws Exception{
		HashMap oaOrgMap= XmlConvert.getParamsAjax();		//  页面中没显示写参数时取法
		orgList =this.getEmpMngrService().queryMngorgOfLogon(oaOrgMap);
		String orgresult = XmlHelper.getXmlListBean(this.getOrgList());
		this.write(orgresult);
		return null;
	}
	

	/**
	 * 查询用户可管理机构
	 * @return
	 * @throws Exception
	 */
	public String queryMngrOrgInfo() throws Exception{
		MUOUserSession musous = (MUOUserSession)this.getCurrentOnlineUser();
		Long empid =musous.getEmpid();
		this.setOrgList(this.empMngrService.initOrgInfo(empid));
		return "mngrlist";
	}
	
	
	/**
	 * 查询机构下的子机构和人员信息
	 * @return
	 * @throws Exception
	 */
	public String queryOrgAndEmp() throws Exception{
		//获取ajax请求参数
		List<HashMap> orgMap= XmlConvert.getMapAjax("oParentOrg");
		if(orgMap.size() > 0){//大于0说明是获取子机构
			String tempOrgId = orgMap.get(0).get("orgId").toString();
			Long orgid = Long.parseLong(tempOrgId);
			this.setOrgList(this.empMngrService.queryChildNode(orgid));
			this.setEmpList(this.empMngrService.queryEmpByOrgId(orgid));
		}else{
			this.setOrgList(this.empMngrService.queryChildNode(0L));
			this.setEmpList(this.empMngrService.queryEmpByOrgId(0L));
		}
		
		String orgOrEmp =XmlConvert.getXmlListBean(this.getOrgList(),this.getEmpList());
		this.write(orgOrEmp);
		return null;
	}
	
	/**
	 * 查询机构树根节点，初始化人员树
	 * @return
	 * @throws Exception
	 */
	public String queryOrgNodes() throws Exception{
		
		Long orgid = this.getCurrentOnlineUser().getOrgentityid();
		String orgseq = this.getCurrentOnlineUser().getOrgseq();
	/*	if(orgid==1){
			orgid = 0L;
		}else{
			if(StringUtils.isNotBlank(orgseq)){
				String[] orgArray = orgseq.split(".");
				if(orgArray.length>1 && StringUtils.isNumeric(orgArray[1])){
					orgid = Long.valueOf(orgArray[1]);
				}
			}
		}*/
		if(orgid==1){
			this.setOrgList(this.getEmpMngrService().queryChildNode(0L));
		}else{
			Object ton = this.getOrgService().selectByPrimaryKey(orgid);
			if(ton==null){
				return null;
			}
			List orgList = new ArrayList();
			orgList.add(ton);
			this.setOrgList(orgList);
		}
		
		String orgresult = XmlHelper.getXmlListBean(this.getOrgList());
		this.write(orgresult);
		return null;
	}
	
	
	/**
	 * 点击机构获取基本信息和机构附加信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryOrgObjInfo() throws Exception{
		String orgidStr =this.getRequest().getParameter("orgid");
		if(orgidStr != null){
			Long orgid =Long.parseLong(orgidStr);
			this.setTomorganiz(this.empMngrService.queryOrgInfoById(orgid));
			this.setAbftjgfjxx(this.empMngrService.queryJgfjxxbInfoById(orgid));
			this.setPartyRole(this.empMngrService.selectOrgAlreayGrantRoles(orgidStr));
		}
		return "orginfo";
	}
	
	
	/**
	 * 查询组织归属选择树下的子机构
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String querySelectTreeNodes() throws Exception{
		HashMap oaOrgMap= XmlConvert.getParamsAjax();
		oaOrgMap  = (HashMap)oaOrgMap.get("oParentOrg");
		String orgidStr = oaOrgMap.get("orgId").toString();
		long orgid = Long.parseLong(orgidStr);
		String orgOrEmpResult = XmlHelper.getXmlListBean(this.getEmpMngrService().queryChildNode(orgid));
		this.write(orgOrEmpResult);
		return null;
	}
	
	/**
	 * 查询机构节点下的的子机构、岗位、员工
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryTreeChildNodes() throws Exception{
		String orgTypeStr =null;
		HashMap oaOrgMap= XmlConvert.getParamsAjax();
		Object orgTypeObj =oaOrgMap.get("orgType");
		String orgOrEmpResult =null;
		if(orgTypeObj!=null){
			orgTypeStr =orgTypeObj.toString();
		}
		int orgType =0;
		if(orgTypeStr!=null){
			orgType =Integer.parseInt(orgTypeStr);
			if(orgType==4 || orgType==6 || orgType==7 || orgType==9){
				this.setOrgList(this.getEmpMngrService().queryTreeChildOrgNodes(oaOrgMap));
				this.setEmpList(this.getEmpMngrService().queryTreeChildEmpNodes(oaOrgMap));
				orgOrEmpResult = XmlHelper.getXmlListBean(this.getOrgList(),this.getEmpList());
				this.write(orgOrEmpResult);
			}
		}
		
		return null;
	}
	
	/**
	 * 查询机构下人员信息，跳转到排序页面
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String repOrderEmp() throws Exception{
		String iOrgid =this.getRequest().getParameter("iOrgid");
		HashMap hm = new HashMap();
		hm.put("orgid", iOrgid);
		List dslist =this.empMngrService.queryEmpUnOrgList(hm);
		this.getRequest().setAttribute("oaEmp", dslist);
		this.getRequest().setAttribute("iOrgid", iOrgid);
		return "orderViewlist";
	}
	
	

	/**
	 * 密码重置
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String resetPwd() throws Exception{
		StringBuffer buffer = new StringBuffer(30);
		try{
			buffer.append("<root><data><result>");
			HashMap<String,String> cdMap =new HashMap<String,String>();
			HashMap hmp = XmlConvert.getParamsAjax();
			String operatorid = hmp.get("operatorid").toString();
			String password = hmp.get("password").toString();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			String	Encryption = baseEncoder.encode(md5.digest(password.getBytes("utf-8")));
			cdMap.put("operatorid", operatorid);
			cdMap.put("password", Encryption);
			String rtns ="0";
			boolean flag =this.empMngrService.resetOperPwd(cdMap);
			if(flag){
				rtns ="1";
			}
			buffer.append(rtns);
		}catch (Exception e) {
			buffer.append(0);
			log.error("【密码重置失败】", e);
		}finally{
			buffer.append("</result></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	

	
	public Omemployee setEmpObj(List<HashMap> oempMap){
		Omemployee obEmp =new Omemployee();
		String empidStr =oempMap.get(0).get("empid").toString();
		obEmp.setEmpid(empidStr);
		String orgidStr =oempMap.get(0).get("orgid").toString();
		obEmp.setOrgid(orgidStr);
		String empname =oempMap.get(0).get("empname").toString();
		obEmp.setEmpName(empname);
		String empstates =oempMap.get(0).get("empname").toString();
		obEmp.setEmpStatus(empstates);
		String userid =oempMap.get(0).get("userid").toString();
		obEmp.setUserid(userid);
		String operStr =oempMap.get(0).get("userid").toString();
		obEmp.setOperatorid(operStr);
		return obEmp;
	}
	
	
	/**
	 * 设置管理机构时跳转到可管理机构页面
	 * @return
	 * @throws Exception
	 */
	public String setMngOrgInit() throws Exception{
		this.getRequest().setAttribute("lookupType", changeTree.getLookupType());
		this.getRequest().setAttribute("startOrgId", changeTree.getStartOrgid());
		this.getRequest().setAttribute("jgsx", changeTree.getJgsx());
		this.getRequest().setAttribute("checkTitle", changeTree.getCheckTitle());
		return "reDirect_mmgOrg";
	}
	
	public OmEmpOrg setOmEmpOrgObjFrom(HashMap fmEmp){
		OmEmpOrg empOrgFrom =new OmEmpOrg();
		String empidStr =fmEmp.get("empid").toString();
		empOrgFrom.setEmpid(empidStr);
		String orgidStr =fmEmp.get("orgid").toString();
		empOrgFrom.setOrgid(orgidStr);
		return empOrgFrom;
	}
	
	
	public OmEmpOrg setOmEmpOrgObjTo(HashMap toOrgMap){
		OmEmpOrg copyEmporg =new OmEmpOrg();
		String empid =toOrgMap.get("empid").toString();
		copyEmporg.setEmpid(empid);
		String orgidStr =toOrgMap.get("orgid").toString();
		copyEmporg.setOrgid(orgidStr);
		return copyEmporg;
	}
	
	

	
	
	/**
	 * 设置系统角色初始化
	 * @return
	 * @throws Exception
	 */
	public String setXtAcRoleInit() throws Exception{
		MUOUserSession muous = this.getCurrentOnlineUser();
		this.partyRole = new PartyRole();
		this.partyRole.setJgsx(muous.getOrgJgsx());
		this.partyRole.setOrgDegree(muous.getOrgdegree());
		this.partyRole.setRoleType(this.getRequest().getParameter("partyRole.roleType"));
		return "setXtAcRoleRespo";
	}
	
	
	public void setYwAcRole(AcRole ywAcRole) {
		this.ywAcRole = ywAcRole;
	}
	
	
	
	/**
	 * 设置业务角色初始化
	 * @return
	 * @throws Exception
	 */
	public String setYwAcRoleInit() throws Exception{
		MUOUserSession muous = this.getCurrentOnlineUser();
		this.partyRole = new PartyRole();
		this.partyRole.setJgsx(muous.getOrgJgsx());
		this.partyRole.setOrgDegree(muous.getOrgdegree());
		this.partyRole.setRoleType(this.getRequest().getParameter("partyRole.roleType"));
		return "setYwAcRoleRespo";
	}
	
	
	
	/**
	 * 修改dataCell调用的方法
	 * @throws Exception
	 */
	public void upDataGrid() throws Exception {
		HashMap hmp = XmlConvert.updateDatacell();
//		this.empMngrService.updateGridRecOrdr(hmp);
	}
	
	/**
	 * 修改机构下员工排序
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String upDataGridOrder() throws Exception {
		List<HashMap> empOrderList= XmlConvert.getMapAjax("DOGroup");
		int rtn =0;
		try{
			this.empMngrService.updateGridRecOrdr(empOrderList);
			rtn =1;
		}catch(Exception e){
			rtn =0;
		}
		this.write(String.valueOf(rtn));
		return null;
	}

	
	
	/**
	 * 修改保存用户
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateSaveEmpInfo() throws Exception{
		HashMap paramMap = XmlConvert.getParamsAjax();
		String rtn ="1";
		boolean flag =this.empMngrService.updateEmpInfo(paramMap);
		if(flag){
			rtn ="0";
		}
		StringBuffer retuBuf = new StringBuffer(100);
		retuBuf.append("<root><data><iRtn>");
		retuBuf.append(StringUtil.tohypy(rtn));
		retuBuf.append("</iRtn></data></root>");
		this.write(retuBuf.toString());
		return null;
	}
	/**
	 * 保存人员归属机构
	 * @return
	 * @throws Exception
	 */
	public String saveEmpOrg() throws Exception{
		try{
			HashMap hmp = XmlConvert.updateDatacell();
			List<OmEmporg> insertEntryList = (List<OmEmporg>)hmp.get("insertEntities");
			List<OmEmporg> updateEntryList = (List<OmEmporg>)hmp.get("updateEntities");
			List<OmEmporg> deleteEntryList = (List<OmEmporg>)hmp.get("deleteEntities");
			this.getEmpMngrService().saveEmpOrg(insertEntryList, updateEntryList, deleteEntryList);
		}catch (Exception e) {
			log.error("【保存人员归属机构失败】", e);
		}
		return null;
	}
	/**
	 * 设置主机构
	 * @return
	 * @throws Exception
	 */
	public String setMainOrg() throws Exception{
		StringBuffer buffer = new StringBuffer(50);
		try{
			buffer.append("<root><data><flag>");
			HashMap<String, String> paramMap = XmlConvert.getParamsAjax();
			this.getEmpMngrService().updateMainOrg(paramMap);
			buffer.append("success");
		}catch (Exception e) {
			buffer.append("fair");
			log.error("【设为主机构失败】", e);
		}finally{
			buffer.append("</flag></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 验证用户的empcode
	 * @return
	 * @throws Exception
	 */
	public String checkEmpCode() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><flag>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String empcode = hmp.get("empcode");
			int num = this.getEmpMngrService().checkEmpCode(empcode);
			buffer.append(num);
		}catch (Exception e) {
			buffer.append(-1);
			log.error("【验证empcode失败】", e);
		}finally{
			buffer.append("</flag></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 个人信息设置修改
	 * @return
	 * @throws Exception
	 */
	public String querySingleEmpInfo() throws Exception{
		MUOUserSession userSession = this.getCurrentOnlineUser();
		String empid = String.valueOf(userSession.getEmpid());
		String userid = userSession.getEmpcode();
		String orgid = String.valueOf(userSession.getOrgid());
		//	员工基本信息
		this.setOmEmpTm(this.empMngrService.queryEmpBaseInfo(empid));
		//	附加信息
		this.setAbfygfjxx(this.empMngrService.queryYgfjxx(empid));
		//	
		this.setAcOper(this.empMngrService.queryEmpAcOperInfo(userid));
		//	查询员工信息
		this.setTomorganiz(this.empMngrService.queryEmpOrgInfo(orgid));
		//	查询系统角色，"1"表示系统角色
		this.setXtAcRole(this.empMngrService.quertyEmpAlReadyGrantRole(empid,"1"));
		//	查询业务角色，"0"表示业务角色
		this.setYwAcRole(this.empMngrService.quertyEmpAlReadyGrantRole(empid,"0"));
		this.setOmPosition(this.getEmpMngrService().selectEmpPosition(empid));
		return "setSingleEmpInfo";
	}
	
	public String modifyPassword() throws Exception{
		StringBuffer buffer = new StringBuffer(40);
		try{
			buffer.append("<root><data><execFlag>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			this.getEmpMngrService().updateOperatorPassword(hmp);
			buffer.append("success");
		}catch (Exception e) {
			buffer.append("fairl");
			log.error("【修改密码失败】", e);
		}finally{
			buffer.append("</execFlag></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}

}
