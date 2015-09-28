package com.gotop.crm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gotop.util.Global;
import com.gotop.util.JSONConvert;
import com.gotop.util.XmlConvert;
import com.gotop.util.security.ForUtil;
import com.gotop.util.string.JsonUtil;
import com.gotop.vo.system.MUOUserSession;
import com.gotop.vo.system.Tonline;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.primeton.utils.Page;
import com.primeton.utils.pageCondExpand;

public class BaseAction extends ActionSupport {
	public static final String PARAMETER = "param_error";
	protected static Logger log = Logger.getLogger(BaseAction.class);
	//JSON类型  
    public static final int JSON_TYPE_TREE = 1; //树
    public static final int JSON_TYPE_GRID = 2; //网格
    
    //公用页
	public static final String SUCCESS = "publicSuccess";	//打开窗口成功
	public static final String FAILURE = "publicFailure";	//打开窗口失败
	public static final String LOCALSUCCESS = "success";	//跳转成功
	public static final String LOCALFAILURE = "failure";	//跳转失败
	public static final String INDEX = "index";	//业务模块主业
	public static final String UPDATE = "update";	//修改页面
	public static final String ADD = "add";	//修改页面
	public static final String DISPLAY = "display";	//查看页面
	
	public static final String OBJECT_UPDATE = "OBJECT_UPDATE"; 
	public static final String LOG_TYPE_UPDATE = "U"; //操作日志类型 1、修改 2、删除
	public static final String LOG_TYPE_DELETE = "D"; //操作日志类型 1、修改 2、删除
	public static final String RETURNJSON = "ruturnJsonStr";

	private List pageFunctionParamList ;
	private String pageFunctionParams ="";
    private Integer submitType;//0、post方式提交（action、jsp）；   1、普通ajax 2、loadDatacell；   3、sumbitDatacell ; 4、comboSelect； 
	
	public List getPageFunctionParamList() {
		return pageFunctionParamList;
	}
	
	public Page page;
	//返回json数据
	private String resultJson;
	
	public String getResultJson() {
		return resultJson;
	}

	public void setResultJson(String resultJson) {
		this.resultJson = resultJson;
	}
	/**
	 * 返回json数据
	 * @param resultMap
	 * @return
	 */
	public String ruturnJson(Map<String,Object> resultMap){
		if(resultMap==null){
			resultMap = new HashMap();
			resultMap.put("mess", "返回值为空");
			log.info("-返回值为空-");
			this.resultJson = JsonUtil.toJsonStr(resultMap);
		}else{
			this.resultJson = JsonUtil.toJsonStr(resultMap);
		}
		return "ruturnJsonStr";
	}
	/**
	 * 返回xml数据
	 * @param resultMap
	 * @return
	 */
	public static String resultXml(HashMap<String,Object> resultMap){
		String result = XmlConvert.getXmlListBean(resultMap);
		write(result);
		return null;
	}
	/**
	 * 返回后关闭窗口
	 * @param resultMap
	 * @return
	 */
    public String resultClose(String rValue,String mess){
		Integer submitType = this.getSubmitType();
		if(submitType!=null && submitType!=0){
			StringBuffer result = new StringBuffer();
			result.append("<root><exceptions><invalid>true</invalid><closePage>1</closePage>");
			if(StringUtils.isNotBlank(mess)){
				result.append("<closeMess>");
				result.append(mess);
				result.append("</closeMess>");
			}
			if(StringUtils.isNotBlank(rValue)){
				result.append("<returnValue>");
				result.append(rValue);
				result.append("</returnValue>");
			}
			result.append("</exceptions></root>");
			this.write(result.toString());
		}else{
			StringBuffer result = new StringBuffer();
			result.append("<script>var wpt=window.parent;if(wpt.submitCallBackByDialog){wpt.submitCallBackByDialog(");
			String valueStr = null;
			String messStr = null;
			if(StringUtils.isNotBlank(rValue)){
				valueStr = "'"+rValue+"'";
			}else{
				valueStr = "null";
			}
			if(StringUtils.isNotBlank(mess)){
				messStr = "'"+mess+"'";
			}else{
				messStr = "null";
			}
			result.append(valueStr);
			result.append(",");
			result.append(messStr);
			result.append(")}else{wpt.window['returnValue']=");
			result.append(valueStr);
			result.append(";");
			if(StringUtils.isNotBlank(mess)){
				result.append("alert('"+mess+"');");
			}
			result.append("wpt.closeD();}</script>");
			this.write(result.toString());
		}
		return null;
    }
    
	public Page getPage() {
		if(page==null){
			page = new Page();
			page.setBegin(0);
			page.setLength(10);
			page.setCount(-1);
			page.setCount(true);
		}
		return page;
	}
	public void setPage(Page page) {
		pageCondExpand pce = new pageCondExpand();
		pce.putPageCond(page);
		this.page = page;
	}
	public void splitParamList ()
	
	{
		if (this.pageFunctionParamList.size() > 0)
		{
			for (int i=0;i<this.pageFunctionParamList.size();i++)
				pageFunctionParams += pageFunctionParamList.get(i).toString() + ",";
			
			if (this.pageFunctionParams.length() > 0)
				pageFunctionParams = pageFunctionParams.substring(0,pageFunctionParams.length() -1);
		}
	}
	public void setPageFunctionParamList(List pageFunctionParamList) {
		this.pageFunctionParamList = pageFunctionParamList;
	}
	public String getPageFunctionParams() {
		return pageFunctionParams;
	}
	public void setPageFunctionParams(String pageFunctionParams) {
		this.pageFunctionParams = pageFunctionParams;
	}


	public static Tonline getOnlineUser() {
		HttpServletRequest request=ServletActionContext.getRequest();
		Tonline obj = (Tonline)request.getSession().getAttribute(Global.LOGON_USER_KEY);
		return obj;
	}
	
	public static MUOUserSession getCurrentOnlineUser() {
		Map session = ActionContext.getContext().getSession();
		MUOUserSession obj = (MUOUserSession)session.get(Global.LOGON_USER_KEY);
		return obj;
	}
	
	
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public static HttpSession getSession(){
		return ServletActionContext.getRequest().getSession();
	}
	
	/**
	 * 获取viewList提交过来的page信息
	 * @param request
	 * @return
	 */
	protected static Page getPageObj(HttpServletRequest request) {
		Page page = new Page();
		String sBegin = request.getParameter("page/begin");
		String sLength = request.getParameter("page/length");
		String sCount = request.getParameter("page/count");
		String sIsCount = request.getParameter("page/isCount");
		int nBegin = Global.DEFAULT_PAGE_NO;
		int nLength = 10;
		int nCount = -1;
		boolean bPageCount = true;
		if (StringUtils.isNotBlank(sBegin)) {
			nBegin = Integer.parseInt(sBegin);
		}
		if (StringUtils.isNotBlank(sLength)) {
			nLength = Integer.parseInt(sLength);
		}
		if (StringUtils.isNotBlank(sCount)) {
			nCount = Integer.parseInt(sCount);
		}
		if (StringUtils.isNotBlank(sIsCount)) {
			bPageCount = Boolean.valueOf(sIsCount);
		}
		page.setBegin(nBegin);
		page.setLength(nLength);
		page.setSize(nLength);
		page.setCount(nCount);
		page.setCount(bPageCount);
		return page;
	}
	/**
	 * 获取页面数
	 * @return
	 */
	protected static int getRowsCount() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String sPageCount = request.getParameter("rows");
		int nPageCount = Global.DEFAULT_PAGE_ROW_COUNT;
		if (null != sPageCount) {
			nPageCount = Integer.parseInt(sPageCount);
		}
		return nPageCount;
	}

	// 将表名转换为VO
	protected static String Table2Entity(String tableName) {
		String str = tableName.toUpperCase();
		StringBuffer sb = new StringBuffer();
		sb.append(str.substring(0, 1));
		str = tableName.toLowerCase();
		sb.append(str.substring(1));
		str = sb.toString();
		return str;
	}
	/**
	 * 将查询结果转换为JSONString
	 * @param jsonObj
	 * @param ObjType
	 * @return
	 */
	protected static String toJSONString(Object jsonObj,int ObjType){
		JSONConvert json = new JSONConvert();
		String result = "";
		try {
			result = json.toJSONString(jsonObj, ObjType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			json = null;
		}
		return result;
	}
	
	/**
	 * 跳到主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public static String toQuery() {
		return INDEX;
	}
	
	/**
	 * 将当前查询出的对象放入SESSION，作为数据操作日志比对的依据
	 * @param entity
	 * @param request
	 */
	protected static void saveEqualsObject(Serializable entity,HttpServletRequest request){
		request.getSession().setAttribute(OBJECT_UPDATE,entity);
	}
	
	/**
	 * 将VO转换成HashMap
	 * @param entity
	 * @return
	 */
	protected static Map ConvertV2M(Serializable entity){
		Map map = null;
		if(null!=entity){
			map = new HashMap();
			String methodName = "";
			java.lang.reflect.Field[] fields = entity.getClass().getDeclaredFields();
			for(int i=0;i<fields.length;i++){
				methodName = "get" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1);
				Method method;
				try {
					method = entity.getClass().getDeclaredMethod(methodName);
					Object value = method.invoke(entity);
					map.put(fields[i].getName(), value);
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	//这是专门从requeset中获得JSON数据的方法。   
	public static String jsonRequeset(HttpServletRequest request) {   
       StringBuffer jb = new StringBuffer();   
       String line = null;   
       try {   
           BufferedReader reader = request.getReader();
           while ((line = reader.readLine()) != null){
               jb.append(line);   
           }
       } catch (Exception e) {   
  
        }   
        return jb.toString();   
    }
	/**
	 * 获取动态方式comboselect传递来的数据
	 * @return 由于第一次访问会默认载入一次，而第一次访问没有没有输入内容，返回值为空
	 */
	public static HashMap getCombopara(){
		//获取comboSelect通过initParamFunc传来的参数
		HashMap hmp = XmlConvert.getParamsAjax();
		if(hmp!=null && hmp.get("filterModel")!=null){
			HashMap combopara = (HashMap)hmp.get("combopara");
			return combopara;
		}
		return null;
	}
	public static void write(String result){
		ForUtil.write(result);
	}
	public Integer getSubmitType() {
		return submitType;
	}
	public void setSubmitType(Integer submitType) {
		this.submitType = submitType;
	}
}
