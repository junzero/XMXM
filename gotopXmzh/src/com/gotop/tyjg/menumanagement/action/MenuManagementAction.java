package com.gotop.tyjg.menumanagement.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.menumanagement.model.AcApplication;
import com.gotop.tyjg.menumanagement.model.AcFunction;
import com.gotop.tyjg.menumanagement.model.AcFunctionGroup;
import com.gotop.tyjg.menumanagement.service.IMenuService;
import com.gotop.util.XmlConvert;
import com.gotop.util.file.FileUtil;
import com.gotop.util.security.ForUtil;
/**
 * *******************************
 * <p>Title: 应用菜单分发任务</p>
 * 
 * <p> Description:   应用菜单分发任务</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date 2012-3-30
 * 
 * @version 1.0
 * 
 * HISTORY 2012-3-30 xuxh 创建文件
 * 
 * *******************************
 */
public class MenuManagementAction extends BaseAction {
	private static final long serialVersionUID = 7883554154104063359L;
	protected static Logger log = Logger.getLogger(MenuManagementAction.class);
	private List<AcApplication> appList = new ArrayList<AcApplication>();
	private List<AcFunctionGroup> acFunGroupList = new ArrayList<AcFunctionGroup>();
	private List<AcFunction> acFunctionList = new ArrayList<AcFunction>();
	private IMenuService menuService;
	private AcApplication acApplication;
	private AcFunctionGroup acFunctionGroup;
	private AcFunction acFunction;
	private String execType;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getExecType() {
		return execType;
	}
	public void setExecType(String execType) {
		this.execType = execType;
	}
	public AcFunction getAcFunction() {
		return acFunction;
	}
	public void setAcFunction(AcFunction acFunction) {
		this.acFunction = acFunction;
	}
	public List<AcFunction> getAcFunctionList() {
		return acFunctionList;
	}
	public void setAcFunctionList(List<AcFunction> acFunctionList) {
		this.acFunctionList = acFunctionList;
	}
	public AcFunctionGroup getAcFunctionGroup() {
		return acFunctionGroup;
	}
	public void setAcFunctionGroup(AcFunctionGroup acFunctionGroup) {
		this.acFunctionGroup = acFunctionGroup;
	}
	public List<AcFunctionGroup> getAcFunGroupList() {
		return acFunGroupList;
	}
	public void setAcFunGroupList(List<AcFunctionGroup> acFunGroupList) {
		this.acFunGroupList = acFunGroupList;
	}
	public AcApplication getAcApplication() {
		return acApplication;
	}
	public void setAcApplication(AcApplication acApplication) {
		this.acApplication = acApplication;
	}
	public IMenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	public List<AcApplication> getAppList() {
		return appList;
	}
	public void setAppList(List<AcApplication> appList) {
		this.appList = appList;
	}
	/**
	 * 获取应用菜单系统的根
	 * @return 应用系统
	 * @throws Exception
	 */
	public String queryAppRoot() throws Exception{
		this.setAppList(this.getMenuService().queryAppRoot());
		String xml = XmlConvert.getXmlListBean(this.getAppList());
		this.write(xml);
		return null;
	}
	/**
	 * 获取菜单组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryMenuGroup() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		HashMap temp = (HashMap) hmp.get("acApplication");
		this.getAcApplication().setAppId(String.valueOf(temp.get("appId")));
		this.setAcFunGroupList(this.getMenuService().queryMenuGroup(this.getAcApplication()));
		String xml = XmlConvert.getXmlListBean(this.getAcFunGroupList());
		this.write(xml);
		return null;
	}
	/**
	 * 获取菜单子组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String queryMenuChildGroup() throws Exception{
		HashMap hmp = XmlConvert.getParamsAjax();
		HashMap temp = (HashMap) hmp.get("AcFunctionGroup");
		this.getAcFunctionGroup().setFuncGroupId(String.valueOf(temp.get("funcGroupId")));
		this.getAcFunctionGroup().setGroupLevel(String.valueOf(temp.get("groupLevel")));
		this.setAcFunGroupList(this.getMenuService().queryMenuChildGroup(this.getAcFunctionGroup()));
		this.setAcFunctionList(this.getMenuService().queryMenu(this.getAcFunctionGroup()));
		String xml = XmlConvert.getXmlListBean(this.getAcFunGroupList(),this.getAcFunctionList());
		this.write(xml);
		return null;
	}
	/**
	 * 查询应用系统列表
	 * @return
	 * @throws Exception
	 */
	public String querySystemApp() throws Exception{
		appList = this.getMenuService().queryAppList(this.getAcApplication(),this.getPage());
		this.setPage(page);
		return "queryApp";
	}
	/**
	 * 新增应用系统
	 * @return
	 * @throws Exception
	 */
	public String addApp()throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			this.uploadFileToServer("application");
			boolean falg = this.getMenuService().insertApp(this.getAcApplication());
			if(falg){
				buffer.append("alert('应用系统新增成功！');window.parent.close();");
			}else{
				buffer.append("alert('应用系统新增失败！');");
			}
		}catch (Exception e) {
			buffer.append("alert('应用系统新增失败！');");
			log.error(e.getMessage());
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 验证应用代码是否存在
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String checkAppCode() throws Exception{
		HashMap<String, Object> hmp = XmlConvert.getParamsAjax();
		String appCode = String.valueOf(hmp.get("appcode"));
		Object object = hmp.get("appid");
		String appId = null;
		if(object != null){
			appId = String.valueOf(object);
		}
		long count = this.getMenuService().checkAppCode(appCode,appId);
		StringBuffer buffer = new StringBuffer(100);
		buffer.append("<root><data><isValid>");
		buffer.append(count);
		buffer.append("</isValid></data></root>");
		this.write(buffer.toString());
		return null;
	}
	/**
	 * 删除应用系统
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteApp() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
		buffer.append("<root><data><isValid>");
		HashMap hmp = XmlConvert.getParamsAjax();
		String appids = String.valueOf(hmp.get("appids"));
		boolean falg = this.getMenuService().deleteApp(appids);
		if(falg){
			buffer.append(1);//成功
		}else{
			buffer.append(0);//失败
		}
		}catch (Exception e) {
			buffer.append(0);//失败
			log.error("[删除应用系统失败]"+e.getMessage());
			throw e;
		}finally{
			buffer.append("</isValid></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 更新应用系统信息
	 * @return
	 * @throws Exception
	 */
	public String updateApp() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			this.uploadFileToServer("application");
			boolean falg = this.getMenuService().updateApp(acApplication);
			buffer.append("<script>");
			if(falg){
				buffer.append("alert('应用系统更新成功！');window.parent.close();");
			}else{
				buffer.append("alert('应用系统更新失败！');");
			}
		}catch (Exception e) {
			buffer.append("alert('应用系统更新失败！');");
			log.error(e.getMessage());
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 查询单个要修改的应用信息
	 * @return
	 * @throws Exception
	 */
	public String querySingleApp() throws Exception{
		String from = this.getAcApplication().getFrom();
		this.setAcApplication(this.getMenuService().querySingleApp(acApplication));
		this.getAcApplication().setFrom(from);
		return "singleApp";
	}
	/**
	 * 新增菜单功能组
	 * @return
	 * @throws Exception
	 */
	public String addFuncGroup() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
		buffer.append("<script>");
		boolean falg = this.getMenuService().insertFuncGroup(this.getAcFunctionGroup());
		if(falg){
			buffer.append("alert('菜单组新增成功！');window.parent.close();");
		}else{
			buffer.append("alert('菜单组新增失败！');");
		}
		}catch (Exception e) {
			buffer.append("alert('菜单组新增失败！');");
			log.error("[新增菜单组失败]"+e.getMessage());
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 查询某个应用下功能组列表
	 * @return
	 * @throws Exception
	 */
	public String queryAppChildGroupList() throws Exception{
		String appid = this.getAcFunctionGroup().getAppId();
		this.setAcFunGroupList(this.getMenuService().selectByAppChildGroupList(appid, this.getPage()));
		this.setPage(this.getPage());
		return "queryAppChildGroupList";
	}
	/**
	 * 更新功能组显示顺序
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateGroupDisplayOrder() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><funcResult>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String groupids = hmp.get("groupids");
			String orderValues = hmp.get("orderValue");
			boolean falg = this.getMenuService().updateAppGroupDisplayOrder(groupids, orderValues);
			if(falg){
				buffer.append("success");
			}else{
				buffer.append("fairl");
			}
		}
		catch (Exception e) {
			buffer.append("fairl");
			log.error("[更新显示顺序失败]"+e.getMessage());
			throw e;
		}finally{
			buffer.append("</funcResult></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 更新工作组名称
	 * @return
	 * @throws Exception
	 */
	public String updateGroupName() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		buffer.append("<script>");
		try{
			boolean falg = this.getMenuService().updateGroupName(this.getAcFunctionGroup());
			if(falg){
				buffer.append("alert('更新成功');window.parent.close();");
			}else{
				buffer.append("alert('更新失败');");
			}
		}catch(Exception e){
			buffer.append("alert('更新失败');");
			log.error(e.getMessage());
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 批量删除工作组
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteBeathGroup() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><rtunVal>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String groupids = hmp.get("groupids");
			String[] groupidArray = groupids.split(",");
			boolean falg = this.getMenuService().deleteBeathGroup(groupidArray);
			if(falg){
				buffer.append("success");
			}else{
				buffer.append("fairl");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			buffer.append("fairl");
			throw e;
		}finally{
			buffer.append("</rtunVal></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 查询工作组信息
	 * @return
	 * @throws Exception
	 */
	public String selectByGroupidInfo() throws Exception{
		this.setAcFunctionGroup(this.getMenuService().selectByGroupidInfo(this.getAcFunctionGroup()));
		return "groupInfo";
	}
	/**
	 * 更新工作组信息
	 * @return
	 * @throws Exception
	 */
	public String updateGroupInfo() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			boolean falg = this.getMenuService().updateGrupInfo(this.getAcFunctionGroup());
			if(falg){
				buffer.append("alert('保存成功');window.parent.parent.frames['IFRAMEAppFuncTree'].DTree.getSelectNode().reloadChild();");
			}else{
				buffer.append("alert('保存失败');");
			}
		}catch (Exception e) {
			buffer.append("alert('保存失败');");
			log.error(e.getMessage());
			throw e;
		}finally{
			buffer.append("window.location.href='/menumanagement/menuManagementAction_selectByGroupidInfo.action?acFunctionGroup.funcGroupId=");
			buffer.append(this.getAcFunctionGroup().getFuncGroupId()).append("';");
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 查询某功能组下子功能组列表
	 * @return
	 * @throws Exception
	 */
	public String queryGroupChildGroupList() throws Exception{
		this.setAcFunGroupList(this.getMenuService().selectGroupChildGroupList(this.getAcFunctionGroup(), this.getPage()));
		this.setPage(page);
		return "queryGroupChildGroupList";
	}
	/**
	 * 查询某个工作组下的功能菜单列表
	 * @return
	 * @throws Exception
	 */
	public String queryGroupFunMenu()throws Exception{
		this.setAcFunctionList(this.getMenuService().selectGroupFunMenu(this.getAcFunction(), this.getPage()));
		this.setPage(page);
		return "queryGroupFunMenu";
	}
	/**
	 * 查询单个菜单信息
	 * @return
	 * @throws Exception
	 */
	public String querySingleFuncMenuInfo() throws Exception{
		this.setAcFunction(this.getMenuService().selectSingleFuncMenuInfo(this.getAcFunction()));
		return "querySingleFuncMenuInfo";
	}
	/**
	 * 新增菜单
	 * @return
	 * @throws Exception
	 */
	public String addMenu()throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			this.uploadFileToServer("function");//上传文件到upload文件夹
			int count = this.getMenuService().insertMenu(this.getAcFunction());
			if(count > 0){
				buffer.append("alert('菜单新增成功!');");
				buffer.append("window.parent.close();");
			}else{
				buffer.append("alert('菜单新增失败!');");
			}
		}catch (Exception e) {
			buffer.append("alert('菜单新增失败!');");
			log.error("[新增菜单失败！]"+e.getMessage());
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 删除菜单
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteMenu() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><rtunVal>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String funccode = hmp.get("funccodes");
			this.getAcFunction().setFuncCode(funccode);
			int count = this.getMenuService().deleteMenu(this.getAcFunction());
			if(count > 0){
				buffer.append("success");
			}else{
				buffer.append("fairl");
			}
		}catch (Exception e) {
			log.error(e.getMessage());
			buffer.append("fairl");
			throw e;
		}finally{
			buffer.append("</rtunVal></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 更新菜单的显示顺序
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateMenuDispleyOrder()throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><funcResult>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String funccodes = hmp.get("funccodes");
			String orderValues = hmp.get("orderValue");
			int count = this.getMenuService().updateMenuDisplayOrder(funccodes.split(","), orderValues.split(","));
		if(count > 0){
			buffer.append("success");
		}else{
			buffer.append("fairl");
		}
		}catch (Exception e) {
			buffer.append("fairl");
			log.error("[更新菜单顺序失败]：" +e.getMessage());
			throw e;
		}finally{
			buffer.append("</funcResult></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 更新菜单信息
	 * @return
	 * @throws Exception
	 */
	public String updateMenu()throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			this.uploadFileToServer("function");
			int count = this.getMenuService().updateMenu(this.getAcFunction());
			if(count > 0){
				buffer.append("alert('菜单保存成功!');");
				if(execType == null || "".equals(execType)){
					buffer.append("window.parent.close();");
					buffer.append("$name('appFuncGroup_viewlist').submit();");
				}
					buffer.append("window.parent.parent.frames['IFRAMEAppFuncTree'].DTree.getRootNode().reloadChild();");
				
			}else{
				buffer.append("alert('菜单保存失败!');");
			}
		}catch (Exception e) {
			buffer.append("alert('菜单保存失败!');");
			log.error("[菜单保存失败！]"+e.getMessage());
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 验证新增或修改的菜单编码是否存在
	 * @return
	 * @throws Exception
	 */
	public String checkFuncCode()throws Exception{
		StringBuffer buffer = new StringBuffer(30);
		try{
			buffer.append("<root><data><isValid>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			acFunction.setFuncCode(hmp.get("funcCode"));
			acFunction.setOldFuncCode(hmp.get("oldFuncCode"));
			int count = this.getMenuService().checkFuncCode(acFunction);
			buffer.append(count);
		}catch (Exception e) {
			buffer.append("-1");
			log.error("[验证失败]",e);
			throw e;
		}finally{
			buffer.append("</isValid></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 移动树节点
	 * @return
	 * @throws Exception
	 */
	public String moveTreeNode() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			HashMap<String, Object> hmp = XmlConvert.getParamsAjax();
			HashMap<String,String> fromMap = null;
			if("menuToGroup".equals(execType)){
				fromMap = (HashMap<String,String>)hmp.get("AcFunction");
			}else{
				fromMap = (HashMap<String,String>)hmp.get("AcFunctionGroup");
			}
			HashMap<String,String> toMap = (HashMap<String,String>)hmp.get("to");
			this.getMenuService().updateMoveTreeNode(fromMap,toMap,execType);
		}catch (Exception e) {
			buffer.append("alert('移动失败');");
			log.error("[移动失败]",e);
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 上传文件到服务器
	 * @param types 类型：用来判断给什么类型赋值
	 * @throws Exception
	 */
	private void uploadFileToServer(String types) throws Exception{
		if(this.upload != null){
			String fileName = this.uploadFileName;
			String path = ServletActionContext.getServletContext().getRealPath("\\common\\images\\funicons");
			String url = path + "/"+fileName;
			if("function".equals(types)){
				this.getAcFunction().setFunIcon(fileName);
			}else if("application".equals(types)){
				this.getAcApplication().setAppIcon(fileName);
			}
			FileUtil fileUtil = new FileUtil();
			fileUtil.fileUpload(ForUtil.createFileInputStream((File)this.upload), url);
		}
	}

}
