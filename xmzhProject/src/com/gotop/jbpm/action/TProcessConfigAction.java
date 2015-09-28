package com.gotop.jbpm.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.file.model.TFileResourceTable;
import com.gotop.file.service.ITFileResourceTableService;
import com.gotop.jbpm.model.TProcessConfig;
import com.gotop.jbpm.model.TProcessConfigPerson;
import com.gotop.jbpm.service.ITProcessConfigPersonService;
import com.gotop.jbpm.service.ITProcessConfigService;
import com.gotop.jbpm.service.JbpmService;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.Obj2StrUtils;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jbpm.api.ProcessDefinition;

public class TProcessConfigAction extends BaseAction {
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITProcessConfigService tProcessConfigService;

    protected ITFileResourceTableService fileResourceTableService;
    
    protected ITProcessConfigPersonService configPersonService;
    
    protected JbpmService jbpmService;
    
    private List<TProcessConfig> processConfigs;
    private List<TProcessConfig> configs;
    private String empids;
    private String empnames;
    private String fileId;
    private String businessType;
    
    private TProcessConfig processConfig;
    
	public TProcessConfig getProcessConfig() {
		return processConfig;
	}

	public void setProcessConfig(TProcessConfig processConfig) {
		this.processConfig = processConfig;
	}

	public ITProcessConfigPersonService getConfigPersonService() {
		return configPersonService;
	}

	public void setConfigPersonService(
			ITProcessConfigPersonService configPersonService) {
		this.configPersonService = configPersonService;
	}

	public JbpmService getJbpmService() {
		return jbpmService;
	}

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public ITFileResourceTableService getFileResourceTableService() {
		return fileResourceTableService;
	}

	public void setFileResourceTableService(
			ITFileResourceTableService fileResourceTableService) {
		this.fileResourceTableService = fileResourceTableService;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getEmpids() {
		return empids;
	}

	public void setEmpids(String empids) {
		this.empids = empids;
	}

	public String getEmpnames() {
		return empnames;
	}

	public void setEmpnames(String empnames) {
		this.empnames = empnames;
	}

	public List<TProcessConfig> getProcessConfigs() {
		return processConfigs;
	}

	public void setProcessConfigs(List<TProcessConfig> processConfigs) {
		this.processConfigs = processConfigs;
	}

	/**
     * 通过spring注入Service的set类.
     * @abatorgenerated
     */
    public void settProcessConfigService(ITProcessConfigService tProcessConfigService) {
        this.tProcessConfigService = tProcessConfigService;
    }

    /**
     * 通过spring注入Service的get类.
     * @abatorgenerated
     */
    public ITProcessConfigService gettProcessConfigService() {
        return this.tProcessConfigService;
    }

    /**
     * 查询datacell列表.
     * @abatorgenerated
     */
    public void queryDataGrid() throws Exception {
        AjaxParam apm = XmlConvert.queryDatacell();
        Page page = apm.getPage();
        HashMap hm = apm.getParams();
        List data = tProcessConfigService.queryDataGrid(hm , page);
        String xmlStr = XmlConvert.getXmlListBean(page,data);
        MUO.write(xmlStr);
    }
    
    /**
     * 获取流程定义ID
     * @return
     */
    public String queryDefinitionId(){
    	try {
    		configs=new ArrayList<TProcessConfig>();
    		configs=tProcessConfigService.queryProcessDefinitionId(businessType);
		} catch (Exception e) {
			log.error("查询流程定义出错", e);
		}
		return "definitions";
    }

    /**
     * 批量更新列表.
     * @abatorgenerated
     */
    public void updateDataGrid() throws Exception {
        HashMap hmp = XmlConvert.updateDatacell();
        tProcessConfigService.updateDataGrid(hmp);
    }

    /**
     * comboselect演示.
     * @abatorgenerated
     */
    public void comboSelect() throws Exception {
        HashMap combopara = this.getCombopara();
        if(combopara!=null){
            	List combo = tProcessConfigService.queryAllDataList(combopara);
            	String dataresult = XmlConvert.getXmlListBean(combo);
            	MUO.write(dataresult);
        }
    }

    /**
     * 我的流程列表展示
     * @return
     * @throws Exception 
     */
    public String queryMyProcessList() throws Exception{
    	//获取用户empId
    	String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    	//获取角色id数组
    	String[] roleIdArray = this.getCurrentOnlineUser().getRoleid();
    	//获取机构代码
    	String orgId = String.valueOf(this.getCurrentOnlineUser().getOrgid());
    	
    	//将角色id数组转换成用","分割的字符串
    	String roleIds = Obj2StrUtils.join(roleIdArray, String.class, ",");
    	
    	//20140901
    	//String positionId = this.getCurrentOnlineUser().getPositionId();
    	//获取岗位id数组
    	String[] positionIdArray = this.getCurrentOnlineUser().getPositionId();
    	//将岗位id数组转换成用","分割的字符串
    	String positionIds = Obj2StrUtils.join(positionIdArray, String.class, ",");
    	//角色id、人员id、机构id
    	String relationids = "'" + empId + "'" + "," + "'"+ orgId+"'" ;
    	if(roleIds!=null&&!"".equals(roleIds))
    		relationids+="," + roleIds ;
    	if(positionIds!=null&&!"".equals(positionIds))
    		relationids+="," + positionIds;
    	processConfigs = tProcessConfigService.queryMyProcessList(relationids,orgId,processConfig,this.getPage());
    	return "my_process_list";
    }
    
    /**
     * 配置流程发起人
     * @return
     * @throws Exception
     */
    public String saveProcessConfig() throws Exception{
    	//根据文件id获取文件
    	TFileResourceTable tFileResourceTable = fileResourceTableService.getFileResource(fileId);
    	
    	//根据路径加载配置文件
    	//部署流程
    	ProcessDefinition processDefinition = jbpmService.deployProcess(tFileResourceTable.getFilePath());
    	
    	TProcessConfig tProcessConfig = new TProcessConfig();
    	tProcessConfig.setDefinitionId(processDefinition.getId());
    	tProcessConfig.setRoleOrgPerson("");
    	//流程状态-可用
    	tProcessConfig.setState("01");
    	tProcessConfig.setUploadOrg(this.getCurrentOnlineUser().getOrgid());
    	tProcessConfig.setUserId(this.getCurrentOnlineUser().getEmpid());
    	//上传时间
    	
    	Long tProcessConfigId = this.tProcessConfigService.insert(tProcessConfig);
    	
    	//保存流程配置与流程发布人员配置
    	if(empids != null){
    		String[] empIdArray = empids.split(",");
    		for (String empId : empIdArray) {
				TProcessConfigPerson tProcessConfigPerson = new TProcessConfigPerson();
				tProcessConfigPerson.setProcessConfigId(tProcessConfigId);
				tProcessConfigPerson.setRelationId(String.valueOf(empId));
				this.configPersonService.insert(tProcessConfigPerson);
			}
    	}
    	return "my_process_list";
    }

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public List<TProcessConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<TProcessConfig> configs) {
		this.configs = configs;
	}

	
    
    
    
}