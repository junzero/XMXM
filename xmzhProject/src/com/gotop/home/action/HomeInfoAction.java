/**
 * 福建国通科技有限公司 1997-2012 版权所有.
 */
package com.gotop.home.action;

import com.gotop.crm.util.BaseAction;
import com.gotop.crm.util.MUO;
import com.gotop.home.model.CurrUserInfo;
import com.gotop.home.model.HomeEvent;
import com.gotop.home.model.HomeInfo;
import com.gotop.home.service.IHomeInfoService;
import com.gotop.jbpm.model.TProcessTaskAssignee;
import com.gotop.jbpm.service.ITProcessTaskAssigneePersonService;
import com.gotop.jbpm.service.ITProcessTaskAssigneeService;
import com.gotop.mettingApply.model.TMettingApply;
import com.gotop.mettingApply.service.ITMettingApplyService;
import com.gotop.util.Struts2Utils;
import com.gotop.util.XmlConvert;
import com.gotop.util.string.JsonUtil;
import com.gotop.util.string.Obj2StrUtils;
import com.gotop.util.time.TimeUtil;
import com.primeton.utils.AjaxParam;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2014-7-25    黄开忠    新建
 * </pre>
 */
public class HomeInfoAction extends BaseAction {

    /**
     * 通过spring注入的Service对象.
     * 
     * @abatorgenerated
     */
    protected IHomeInfoService homeInfoService;
    /**
     * 通过spring注入的Service对象.
     * @abatorgenerated
     */
    protected ITProcessTaskAssigneeService tProcessTaskAssigneeService;
    private ITMettingApplyService tMettingApplyService;
    private List<HomeInfo> homeinfos;
    private CurrUserInfo curruserinfo;
    private List<HomeEvent> homeevents;
    private TProcessTaskAssignee taskAssignee;
    //待办任务列表
    private List<TProcessTaskAssignee> processTaskAssignees;
    
    //待办督办列表  20140904
    private List<TProcessTaskAssignee> supAssignees;
    
    //会议通知列表 20140904
    private List<TMettingApply> mettingList;
    
    /**
     * @return the processTaskAssignees
     */
    public List<TProcessTaskAssignee> getProcessTaskAssignees() {
        return processTaskAssignees;
    }

    /**
     * @param processTaskAssignees the processTaskAssignees to set
     */
    public void setProcessTaskAssignees(
            List<TProcessTaskAssignee> processTaskAssignees) {
        this.processTaskAssignees = processTaskAssignees;
    }

    public TProcessTaskAssignee getTaskAssignee() {
        return taskAssignee;
    }

    public void setTaskAssignee(TProcessTaskAssignee taskAssignee) {
        this.taskAssignee = taskAssignee;
    }
    
    /**
     * @return the homeevents
     */
    public List<HomeEvent> getHomeevents() {
        return homeevents;
    }

    /**
     * @param homeevents the homeevents to set
     */
    public void setHomeevents(List<HomeEvent> homeevents) {
        this.homeevents = homeevents;
    }

    /**
     * @return the homeinfos
     */
    public List<HomeInfo> getHomeinfos() {
        return homeinfos;
    }

    /**
     * @param homeinfos the homeinfos to set
     */
    public void setHomeinfos(List<HomeInfo> homeinfos) {
        this.homeinfos = homeinfos;
    }

 

    /**
	 * @return supAssignees
	 */
	public List<TProcessTaskAssignee> getSupAssignees() {
		return supAssignees;
	}

	/**
	 * @param supAssignees 要设置的 supAssignees
	 */
	public void setSupAssignees(List<TProcessTaskAssignee> supAssignees) {
		this.supAssignees = supAssignees;
	}

	/**
	 * @return mettingList
	 */
	public List<TMettingApply> getMettingList() {
		return mettingList;
	}

	/**
	 * @param mettingList 要设置的 mettingList
	 */
	public void setMettingList(List<TMettingApply> mettingList) {
		this.mettingList = mettingList;
	}

	/**
     * @return the curruserinfo
     */
    public CurrUserInfo getCurruserinfo() {
        return curruserinfo;
    }

    /**
     * @param curruserinfo the curruserinfo to set
     */
    public void setCurruserinfo(CurrUserInfo curruserinfo) {
        this.curruserinfo = curruserinfo;
    }

    /**
     * 通过spring注入Service的set类.
     * 
     * @abatorgenerated
     */
    public void setHomeInfoService(IHomeInfoService homeInfoService) {
        this.homeInfoService = homeInfoService;
    }

    /**
     * 通过spring注入Service的get类.
     * 
     * @abatorgenerated
     */
    public IHomeInfoService getHomeInfoService() {
        return this.homeInfoService;
    }

    /**
     * @return the tProcessTaskAssigneeService
     */
    public ITProcessTaskAssigneeService gettProcessTaskAssigneeService() {
        return tProcessTaskAssigneeService;
    }

    /**
     * @param tProcessTaskAssigneeService the tProcessTaskAssigneeService to set
     */
    public void settProcessTaskAssigneeService(
            ITProcessTaskAssigneeService tProcessTaskAssigneeService) {
        this.tProcessTaskAssigneeService = tProcessTaskAssigneeService;
    }

    /**
	 * @return tMettingApplyService
	 */
	public ITMettingApplyService gettMettingApplyService() {
		return tMettingApplyService;
	}

	/**
	 * @param tMettingApplyService 要设置的 tMettingApplyService
	 */
	public void settMettingApplyService(ITMettingApplyService tMettingApplyService) {
		this.tMettingApplyService = tMettingApplyService;
	}

	/**
     * 
     * 
     * 得到当前登录人员的待办信息，取前10条
     * 
     * @return
     * @throws Exception
     * 
     *             <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-26    黄开忠    新建
     * </pre>
     */
    public String getHomeInfo() throws Exception {
        String info="";
        try{     
        if(curruserinfo==null){
            curruserinfo =new CurrUserInfo();
        }
        this.curruserinfo.setCurrempid(this.getCurrentOnlineUser().getEmpid());
        this.curruserinfo.setCurrorgcode(this.getCurrentOnlineUser().getOrgcode());
        this.curruserinfo.setCurrRoles(Arrays.toString(this.getCurrentOnlineUser().getRoleid()));
        this.curruserinfo.setCurrorgid(this.getCurrentOnlineUser().getOrgid());
        //2014.9.1 岗位发布
        this.curruserinfo.setPositionId(Obj2StrUtils.join(this.getCurrentOnlineUser().getPositionId(), String.class, ","));
        this.setHomeinfos(this.getHomeInfoService().getEmpMessHomeInfo(curruserinfo));
        
        }catch(Exception e){
            log.info("得到当前登录人员的待办信息错误："+e.getMessage());
        }
        String resultJson=JsonUtil.getJsonString4JavaArryPOJO(this.getHomeinfos());
        log.info("resultJson="+resultJson);
        this.setResultJson(resultJson);
        return "ruturnJsonStr";
    }
    /**
     * 
     *
     *得到首页待办事件信息,得到前4条
     *
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-9-15    黄开忠    新建
     * </pre>
     */
public String getSupAssigneesList() throws Exception{
    //获取用户empId
    String empId = String.valueOf(this.getCurrentOnlineUser().getEmpid());
    //获取角色id数组
    String[] roleIdArray = this.getCurrentOnlineUser().getRoleid();
    //获取机构代码
    String orgCode = this.getCurrentOnlineUser().getOrgcode();
    //将角色id数组转换成用","分割的字符串
    String roleIds = Obj2StrUtils.join(roleIdArray, String.class, ",");
    //角色id、人员id、机构id
    String relationids = "'" + empId + "'" + "," + "'"+ orgCode+"'" ;
     if(roleIds!=null&&!"".equals(roleIds))
                relationids+="," + roleIds ;
    this.getPage().setLength(4);
    try{
    List<TProcessTaskAssignee> tProcessTaskAssignees = this.tProcessTaskAssigneeService.queryMyToDoTasksList(relationids,taskAssignee,this.getPage());
    this.setProcessTaskAssignees(tProcessTaskAssignees);
    }catch(Exception e){
        log.info("首页得到待办事件错误。");
    }
    String resultJson=JsonUtil.getJsonString4JavaArryPOJO(this.getProcessTaskAssignees());
    this.setResultJson(resultJson);
    return "ruturnJsonStr";
}
   /**
    * 
    *
    * 首页得到督办提醒，取4条
    *
    * @return
    * @throws Exception
    *
    * <pre>
    * 修改日期        修改人    修改原因
    * 2014-9-15    黄开忠    新建
    * </pre>
    */
 public String getSuperviseList() throws Exception{
     HashMap<String, Object> map= new HashMap<String, Object>();
     map.put("empid", this.getCurrentOnlineUser().getEmpid());
     map.put("orgcode", this.getCurrentOnlineUser().getOrgcode());
     this.getPage().setLength(4);
     try{
         supAssignees = this.tProcessTaskAssigneeService.querySuperviseList(map,this.getCurrentOnlineUser().getPosiCode(), taskAssignee,this.getPage());
     }catch(Exception e){
         log.info("首页得到督办提醒信息错误");
     }
     String resultJson=JsonUtil.getJsonString4JavaArryPOJO(this.getSupAssignees());
     this.setResultJson(resultJson);
     return "ruturnJsonStr"; 
 }
 /**
  * 
  *
  * 首页提醒会议信息,取4条
  *
  * @return
  * @throws Exception
  *
  * <pre>
  * 修改日期        修改人    修改原因
  * 2014-9-15    黄开忠    新建
  * </pre>
  */
 public String getMettingLists() throws Exception{
     TMettingApply meet = new TMettingApply();
     meet.setCreateDate1(TimeUtil.today()+TimeUtil.now());
     this.getPage().setLength(4);
     try{
         mettingList = tMettingApplyService.queryHomeMettingList(meet, this.getCurrentOnlineUser(), this.getPage());
     }catch(Exception e){
         log.info("首页得到会议提醒错误");
     }
     String resultJson=JsonUtil.getJsonString4JavaArryPOJO(this.getMettingList());
     this.setResultJson(resultJson);
     return "ruturnJsonStr"; 
 }
}
