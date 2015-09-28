package com.gotop.group.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.group.model.TGroup;
import com.gotop.group.service.ITGroupService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class TGroupAction extends BaseAction {
	
	/**
	 * 群组对象
	 */
	private TGroup group;
	
	/**
	 * 群组列表
	 */
	private List<TGroup> groups;
	
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITGroupService tGroupService;

    /**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settGroupService(ITGroupService tGroupService) {
        this.tGroupService = tGroupService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITGroupService gettGroupService() {
        return this.tGroupService;
    }

    public TGroup getGroup() {
		return group;
	}

	public void setGroup(TGroup group) {
		this.group = group;
	}

	public List<TGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<TGroup> groups) {
		this.groups = groups;
	}

	/**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tGroupService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tGroupService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tGroupService.queryAllDataList(combopara);
            	String dataresult = XmlConvert.getXmlListBean(combo);
            	MUO.write(dataresult);
        }
    }

    /**
     * viewDataList说明.
     * @abatorgenerated
     */
    public String queryViewList() throws Exception {
        HttpServletRequest request=ServletActionContext.getRequest();
        Page page = this.getPage();
        HashMap hm = new HashMap();
        List orgs = tGroupService.queryPageDataList(hm,page);
        request.setAttribute("orgs", orgs);
        request.setAttribute("page", page);
        return "viewlist";
    }
    
    /**
     * 路径：
     * 					我的群组
     * 功能描述：
     * 					跳转到我的群组列表页面，查询当前登录者所拥有的群组列表信息
     * 备注说明：
     * 
     * @return
     */
    public String myGroupList(){
    	MUOUserSession muoUserSession = getCurrentOnlineUser();
    	long empId = muoUserSession.getEmpid();
    	if(group == null){
    		group = new TGroup();
    	}
    	group.setGroupCreator(empId);
    	groups = tGroupService.myGroupList(group,this.getPage());
    	this.setGroups(groups);
    	return "myGroupList";
    }
    
    /**
     * 跳转到增加群组界面
     * @return
     */
    public String toGroup(){
    	if(group != null){
    		group = tGroupService.getGroupByRecId(group);
    	}
    	this.setGroup(group);
    	return "group";
    }
    
    /**
     * 新增或修改群组信息
     * @throws Exception 
     */
    public void addGroup() throws Exception{
    	String info ="success";
    	MUOUserSession muoUserSession = getCurrentOnlineUser();
    	String[] roleIds = muoUserSession.getRoleid();
    	boolean flag = false;
    	for (String roleId : roleIds) {
			if(roleId.equals("SYSADMIN")){
				flag = true;
				break;
			}
		}
    	if(flag == true){
    		//group.setIsWholebank("1");
    		group.setCreateRole("SYSADMIN");
    	}else{
    		group.setIsWholebank("0");
    	}
    	group.setGroupCreator(muoUserSession.getEmpid());
    	try {
    		this.tGroupService.saveGroup(group);
    	} catch (Exception e) {
			info="fails";
			log.error("[保存群组信息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
    }
    
    public void deleteGroup() throws Exception{
    	String info ="success";
    	try {
    		this.tGroupService.deleteGroup(group);
    	} catch (Exception e) {
			info="fails";
			log.error("[删除群组信息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
    }
    
    public void isSysadmin() throws Exception{
    	String info ="success";
    	MUOUserSession muoUserSession = getCurrentOnlineUser();
    	String[] roleIds = muoUserSession.getRoleid();
    	boolean flag = false;
    	for (String roleId : roleIds) {
			if(roleId.equals("SYSADMIN")){
				flag = true;
				break;
			}
		}
    	try {
    		if(flag == true){
    			
    		}else{
    			info ="false";
    		}
    	} catch (Exception e) {
			info="fails";
			log.error("[保存群组信息失败！]", e);
			throw e;
		}finally{	
		}
		Struts2Utils.renderText(info);
    }
    
    
}