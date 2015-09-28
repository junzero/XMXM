package com.gotop.util;

public class Global {
	//页号
	public static final int DEFAULT_PAGE_NO=1;
	//每页记录数
	public static final int DEFAULT_PAGE_ROW_COUNT=20;
	//登入eos用户
	public static final String LOGON_USER_Object="login_user_obj";
	//登入用户
	public static final String LOGON_USER_KEY="login_user";
	//登入用户
	public static final String LOGON_USER_Token="login_user_token";
	//自动关闭页
	public static final String AUTOCLOSE_WINDOW_PAGE = "/jsp/util/dialogfailure.jsp";
	//当前活动session
	public static final String LOGON_SESSION_ID="login_session_id";
	//备份session map
	public static final String LOGON_SESSION_MAP="logon_session_map";
	
	//权限类型
	public static final short QXLX_USER = 0;			//用户权限
	public static final short QXLX_ROLE = 1;			//角色权限
    //用户管理
    public static final int DEFAULT_USER_SUPER = 1000;	//最高权限用户
    public static final String  DEFAULT_PASSW = "670b14728ad9902aecba32e22fa4f6bd";//默认密码000000
    public static final int DEFAULT_USER_UNUSED = 1;	//禁用用户的状态
    public static final int DEFAULT_USER_NORMAL = 0;	//启用用户的状态
    //角色
    public static final int DEFAULT_ROLE_SUPER = 1;		//最高权限角色
    public static final int DEFAULT_ROLE_USER = 0;   	//最低权限用户
    
    //当前操作的模块代码
    public static String MKDM="mkdm";;
    
    //JSON类型  
    public static final int JSON_TYPE_TREE = 1; //树
    public static final int JSON_TYPE_GRID = 2; //网格
    public static final int JSON_TYPE_OTHER = 99; //其他类型
    
    //机构属性
    public static final int PSBC_JGSX = 1;//银行
    public static final int POST_JGSX = 2;//邮政
}
