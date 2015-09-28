package com.gotop.tyjg.orgmanagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.TdjgfjBean;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.tyjg.orgmanagement.service.IOrganizationService;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.StringUtil;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.XmlHelper;
import com.primeton.utils.pageCondExpand;
@SuppressWarnings("unchecked")
public class OrganizationAction extends BaseAction {

	private static final long serialVersionUID = -9066936350861232993L;
	private List<Tomorganization> orgList = new ArrayList<Tomorganization>();
	private List<Tomorganization> managerOrgList = new ArrayList<Tomorganization>();//可管理机构
	private IOrganizationService orgService;
	private Tomorganization tomorganization;
	private Abftjgfjxx abftjgfjxx;
	private PartyRole partyRole;
	private TdjgfjBean tdjgfjb;

	public List<Tomorganization> getManagerOrgList() {
		return managerOrgList;
	}

	public void setManagerOrgList(List<Tomorganization> managerOrgList) {
		this.managerOrgList = managerOrgList;
	}

	public PartyRole getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(PartyRole partyRole) {
		this.partyRole = partyRole;
	}

	public Tomorganization getTomorganization() {
		return tomorganization;
	}

	public void setTomorganization(Tomorganization tomorganization) {
		this.tomorganization = tomorganization;
	}

	public IOrganizationService getOrgService() {
		return orgService;
	}

	public void setOrgService(IOrganizationService orgService) {
		this.orgService = orgService;
	}

	public List<Tomorganization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<Tomorganization> orgList) {
		this.orgList = orgList;
	}
	
	public Abftjgfjxx getAbftjgfjxx() {
		return abftjgfjxx;
	}

	public void setAbftjgfjxx(Abftjgfjxx abftjgfjxx) {
		this.abftjgfjxx = abftjgfjxx;
	}
	
	public TdjgfjBean getTdjgfjb()
	{
		return tdjgfjb;
	}

	public void setTdjgfjb(TdjgfjBean tdjgfjb)
	{
		this.tdjgfjb = tdjgfjb;
	}

	public String queryChildOrg() throws Exception{
		//获取ajax请求参数
		List<HashMap> orgMap= XmlConvert.getMapAjax("oParentOrg");
		if(orgMap.size() > 0){//大于0说明是获取子机构
			String tempOrgId = orgMap.get(0).get("orgId").toString();
			long orgId = Long.parseLong(tempOrgId);
			this.setOrgList(this.getOrgService().selectByChildOrg(orgId));
		}else{
			this.setOrgList(this.getOrgService().selectByChildOrg(0L));//传入0说明获取根机构
		}
		String orgresult = XmlHelper.getXmlListBean(this.getOrgList());
		this.write(orgresult);
		return null;
	}
	
	public String queryOrgAllChildOrg() throws Exception{
		orgList = this.getOrgService().selectByOrg(this.getTomorganization(), this.getPage());
		this.setPage(page);
		return "childOrgPageList";
	}
	
	public String queryOrgOrder() throws Exception{
		orgList = this.getOrgService().selectOrderByChildOrg(this.getTomorganization());
		return "childOrgList";
	}
	/**
	 * 更新子机构显示顺序
	 * @return
	 * @throws Exception
	 */
	public String updateChildOrgOrder() throws Exception{
		HashMap paramMap =  XmlConvert.getParamsAjax();
		String paramStr = String.valueOf(paramMap.get("orginfo"));
		boolean falg = this.getOrgService().updateChildOrgOrder(paramStr);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root><data>");
		buffer.append("<falg>");
		buffer.append(falg);
		buffer.append("</falg>");
		buffer.append("</data></root>");
		this.write(buffer.toString());
		return null;
	}
	/**
	 * 查询某机构的基本信息与附加信息
	 * @return
	 * @throws Exception
	 */
	public String querySingleOrgInfo() throws Exception{
		this.setTomorganization(this.getOrgService().selectByPrimaryKey(Long.parseLong(this.getTomorganization().getOrgId())));
		this.setAbftjgfjxx(this.getOrgService().selectByPrimaryKeyFjxx(Long.parseLong(this.getTomorganization().getOrgId())));
		this.setPartyRole(this.getOrgService().selectOrgAlreayGrantRoles(this.getTomorganization().getOrgId()));
		this.setTdjgfjb(this.getOrgService().queryTdjgfjxx(this.getTomorganization().getOrgId()));
		return "orgBaseAndFjxxInfo";
	}
	/**
	 * 修改机构基本信息与机构附加信息
	 * @return
	 * @throws Exception
	 */
	public String updateSingleOrgInfo() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			MUOUserSession userSession  = this.getCurrentOnlineUser();
			this.getTomorganization().setWhry(userSession.getEmpcode());
			boolean falg = this.getOrgService().updateOrg(this.getTomorganization(), this.getAbftjgfjxx(),this.getPartyRole().getRoleId());
			if(falg){
				buffer.append("alert('保存成功');");
			}else{
				buffer.append("alert('保存失败');");
			}
			}catch (Exception e) {
				buffer.append("alert('保存失败');");
				log.error("【机构保存失败】：", e);
				throw e;
		}finally{
			buffer.append("window.location.href='/orgmanagement/organizationAction_querySingleOrgInfo.action?tomorganization.orgId="+tomorganization.getOrgId()+"';");
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 验证待注销的机构下是否有人员与未注销的子机构存在
	 * @return
	 * @throws Exception
	 */
	public String checkExistsChildOrEmp() throws Exception{
		HashMap hmp =  XmlConvert.getParamsAjax();
		long orgid = Long.parseLong(String.valueOf(hmp.get("orgid")));
		String error = this.getOrgService().checkExixtsChildOrgOrEmp(orgid);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root><data>");
		buffer.append("<checkMessage>");
		buffer.append(error);
		buffer.append("</checkMessage>");
		buffer.append("</data></root>");
		this.write(buffer.toString());
		return null;
	}
	/**
	 * 移动机构
	 * @return
	 * @throws Exception
	 */
	public String moveOrg() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		this.getOrgService().moveOrg(hmp);
		return null;
	}
	/**
	 * 机构置顶
	 * @return
	 * @throws Exception
	 */
	public String moveOrgTop() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		String orgid = String.valueOf(hmp.get("orgid"));
		boolean falg = this.getOrgService().moveOrgTop(orgid);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root><data>");
		buffer.append("<falg>");
		buffer.append(falg);
		buffer.append("</falg>");
		buffer.append("</data></root>");
		this.write(buffer.toString());
		return null;
	}

	/**
	 * 新增机构
	 * @return
	 * @throws Exception
	 */
	public String addOrgInfo() throws Exception{
		StringBuffer buffer = new StringBuffer();
		try{
		buffer.append("<script>");
		boolean falg = this.getOrgService().insertOrg(this.getTomorganization(), this.getAbftjgfjxx());
		if(falg){
			buffer.append("alert('机构新增成功');window.parent.close();");
		}else{
			buffer.append("alert('机构新增失败');");
		}
		}catch (Exception e) {
			buffer.append("alert('机构新增失败');");
			log.error("【新增机构失败】：", e);
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 获取机构名称首拼音
	 * @return
	 * @throws Exception
	 */
	public String tohypyInitial() throws Exception{
		StringBuffer buffer = new StringBuffer();
		buffer.append("<root><data><py>");
		buffer.append(StringUtil.tohypyInitial(String.valueOf(XmlConvert.getParamsAjax().get("orgname"))));
		buffer.append("</py></data></root>");
		this.write(buffer.toString());
		return null;
	}
	/**
	 * 验证机构新增时的机构信息
	 * @return 错误信息
	 * @throws Exception
	 */
	public String checkOrgCode() throws Exception{
		HashMap paramMap = XmlConvert.getParamsAjax();
		String xmlMsg = this.getOrgService().checkOrgForm(paramMap);
		this.write(xmlMsg);
		return null;
	}
	/**
	 * 跳转可授权角色界面
	 * @return
	 * @throws Exception
	 */
	public String changeGrantRole() throws Exception{
		return "changeRole";
	}
	/**
	 * 获取机构可授权角色
	 * @return
	 * @throws Exception
	 */
	public String initChangeGrantRole() throws Exception{
		AjaxParam apm = XmlConvert.queryDatacell();
		Page page = apm.getPage();
		HashMap hmp = XmlConvert.getParamsAjax();
		List roleList = this.getOrgService().selectOrgRoles(hmp,page);
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		String xml = XmlConvert.getXmlListBean(page,roleList);
		this.write(xml);
		return null;
	}
	
	public String queryManagerOrg() throws Exception{
		long empid = this.getCurrentOnlineUser().getEmpid();
		this.setManagerOrgList(this.getOrgService().selectCurrUserManagerOrg(empid));
		this.getRequest().getSession().setAttribute("managerOrg", this.getManagerOrgList());
		this.write("<script>window.location.href='/jsp/tyjg/orgmanagement/org_main.jsp'</script>");
		return null;
	}
}
