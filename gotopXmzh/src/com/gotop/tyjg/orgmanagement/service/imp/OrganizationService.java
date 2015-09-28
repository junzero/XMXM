package com.gotop.tyjg.orgmanagement.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gotop.tyjg.orgmanagement.dao.IOrganizationDao;
import com.gotop.tyjg.orgmanagement.model.Abftjgfjxx;
import com.gotop.tyjg.orgmanagement.model.PartyRole;
import com.gotop.tyjg.orgmanagement.model.TdjgfjBean;
import com.gotop.tyjg.orgmanagement.model.Tomorganization;
import com.gotop.tyjg.orgmanagement.service.IOrganizationService;
import com.primeton.utils.Page;
@SuppressWarnings("unchecked")
public class OrganizationService implements IOrganizationService {
	
	private static final long serialVersionUID = 7669557850004823270L;
	private IOrganizationDao organizationDao;
	
	public IOrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	public void setOrganizationDao(IOrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	@Override
	public boolean insertOrg(Tomorganization org, Abftjgfjxx jgfj)
			throws Exception {
		long newOrgid = this.getOrganizationDao().getPrimaryKey("OM_ORGANIZATION_SEQ");
		org.setOrgId(String.valueOf(newOrgid));
		jgfj.setOrgId(String.valueOf(newOrgid));
		return this.getOrganizationDao().insertOrg(org, jgfj);
	}

	@Override
	public List<Tomorganization> selectByChildOrg(long parentOrgId)
			throws Exception {
		return organizationDao.selectByChildOrg(parentOrgId);
	}

	@Override
	public List<Tomorganization> selectByOrg(Tomorganization org, Page page)
			throws Exception {
		int count = this.getOrganizationDao().childOrgCount(Long.parseLong(org.getOrgId()));
		page.setCount(count);
		return this.getOrganizationDao().selectByOrg(org, page);
	}
	/**
	 * 根据机构ID查询机构基本信息
	 * @param orgid 机构编号
	 * @return 机构基本信息
	 * @throws Exception
	 */
	@Override
	public Tomorganization selectByPrimaryKey(long orgid) throws Exception {
		return this.getOrganizationDao().selectByPrimaryKey(orgid);
	}

	@Override
	public Abftjgfjxx selectByPrimaryKeyFjxx(long orgid) throws Exception {
		return this.getOrganizationDao().selectByPrimaryKeyFjxx(orgid);
	}

	@Override
	public List<Tomorganization> selectCurrUserManagerOrg(long empid)
			throws Exception {
		List<Tomorganization> list = this.getOrganizationDao().selectCurrUserManagerOrg(empid);
		return list;
	}
	/**
	 * 更新机构信息
	 * @param org 机构基本信息
	 * @param jgfj 机构附加信息
	 * @param roleId 机构拥有的角色id
	 * @return true 成功 false 失败
	 * @throws Exception
	 */
	@Override
	public boolean updateOrg(Tomorganization org, Abftjgfjxx jgfj,String roleId)
			throws Exception {
		String[] roleSplit = null;
		if(!StringUtils.isBlank(roleId)){
			roleSplit = roleId.split(",");
		}
		return this.getOrganizationDao().updateOrg(org, jgfj,roleSplit);
	}
	
	public List<Tomorganization> selectOrderByChildOrg(Tomorganization tomorganization) throws Exception{
		return this.getOrganizationDao().selectByOrg(tomorganization, null);
	}
	/**
	 * 调整机构显示顺序
	 * @param params 机构顺序数组
	 * @return true:顺序调整成功 false:顺序调整失败
	 * @throws Exception
	 */
	public boolean updateChildOrgOrder(String submitParams) throws Exception{
		String[] paramArray = submitParams.split("~#");
		List<HashMap> paramList = new ArrayList<HashMap>();
		int size = paramArray.length;
		for(int i = 0; i < size; i++){
			HashMap hmp = new HashMap();
			String[] tempArray = paramArray[i].split(",");
			hmp.clear();
			hmp.put("orgid", tempArray[0]);
			hmp.put("display", tempArray[1]);
			paramList.add(hmp);
			
		}
		return this.getOrganizationDao().updateChildOrgOrder(paramList);
	}
	/**
	 * 检查该机构下是否存在子机构或人员
	 * @param orgid 机构编号
	 * @return 返回验证信息
	 * @throws Exception
	 */
	@Override
	public String checkExixtsChildOrgOrEmp(long orgid) throws Exception {
		StringBuffer errorMessage = new StringBuffer();
		long orgNum = this.getOrganizationDao().checkExistsOrg(orgid);
		if(orgNum > 0 ){
			errorMessage.append("该机构下存在未注销的子机构,先注销子机构！\r\n");
		}
		long empNum = this.getOrganizationDao().checkExistsEmp(orgid);
		if(empNum > 0){
			errorMessage.append("该机构下存在人员，先把人员移动到其它机构！");
		}
		return errorMessage.toString();
	}
	
	/**
	 * 移动机构
	 * @param paramMap 移动起始机构节点信息与终点机构信息
	 * @return true:移动成功 false:移动失败
	 * @throws Exception
	 */
	public boolean moveOrg(HashMap paramMap) throws Exception{
		return this.getOrganizationDao().updateMoveOrg(paramMap);
	}
	/**
	 * 机构置顶
	 * @param orgid 机构编号
	 * @return true:置顶成功 false:置顶失败
	 * @throws Exception
	 */
	public boolean moveOrgTop(String orgid) throws Exception{
		return this.getOrganizationDao().updateMoveOrgTop(orgid);
	}
	/**
	 * 验证机构新增时的机构信息
	 * @param paramMap 机构信息
	 * @return 错误信息
	 * @throws Exception
	 */
	public String checkOrgForm(HashMap paramMap)throws Exception{
		StringBuffer buffer = new StringBuffer();
		long orgCodeCount = this.getOrganizationDao().checkOrgCode(paramMap);//orgcode存在数量
		buffer.append("<root><data>");
		buffer.append("<orgCode>");
		buffer.append(orgCodeCount);
		buffer.append("</orgCode>");
		buffer.append("</data></root>");
		return buffer.toString();
	}
	/**
	 * 查询机构可授权角色信息
	 * @param hashMap
	 * @return
	 * @throws Exception
	 */
	public List selectOrgRoles(HashMap hashMap,Page page)throws Exception{
		hashMap.put("oracleStart", page.getBegin());
		hashMap.put("oracleEnd", page.getBegin()+page.getLength());
		long count = this.getOrganizationDao().countOrgRole(hashMap);
		page.setCount(Integer.parseInt(String.valueOf(count)));
		return this.getOrganizationDao().selectOrgRoles(hashMap);
	}
	/**
	 * 查询已授权给机构的角色
	 * @param orgid 机构编号
	 * @return 角色对象
	 * @throws Exception
	 */
	public PartyRole selectOrgAlreayGrantRoles(String orgid) throws Exception{
		PartyRole partyRole = new PartyRole();
		List<PartyRole> roleList = this.getOrganizationDao().selectOrgAlreayGrantRoles(orgid);
		for(PartyRole role : roleList){
			partyRole.setSpecialTyname(role.getSpecialTyname());
			partyRole.setRoleId(role.getRoleId());
		}
		return partyRole;
	}

	@Override
	public TdjgfjBean queryTdjgfjxx(String orgid) throws Exception
	{
		// TODO Auto-generated method stub
		return this.getOrganizationDao().queryTdjgfjxx(orgid);
	}
}
