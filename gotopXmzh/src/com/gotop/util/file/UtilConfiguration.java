/*******************************************************************************
 * $Header: /opt/cvsroot/wiki/opensource/gocom/abframe2/src/org.gocom.abframe.tools/src/org/gocom/abframe/tools/UtilConfiguration.java,v 1.2 2009/01/07 07:04:14 xusl Exp $
 * $Revision: 1.2 $
 * $Date: 2009/01/07 07:04:14 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 *
 * Created on 2008-9-17
 *******************************************************************************/


package com.gotop.util.file;

/**
 *
 * Utility构件包配置常量定义
 *
 * @author wengzr (mailto:wengzr@primeton.com)
 */
/*
 * 修改历史
 * $Log: UtilConfiguration.java,v $
 * Revision 1.2  2009/01/07 07:04:14  xusl
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/07 06:52:12  liuxiang
 * *** empty log message ***
 *
 * Revision 1.1  2009/01/05 02:34:56  caisy
 * abframe二期初始版本
 *
 * Revision 1.2  2008/12/01 09:05:17  wengzr
 * Update:增加EXCEL_EXPORT_MAXNUM配置
 *
 * Revision 1.1  2008/11/28 04:03:02  wengzr
 * Added:增加系统信息工具类SystemInfo
 * Refactor:将Excel工具类从customize移入到utils
 *
 * Revision 1.1  2008/11/12 14:41:55  wengzr
 * Added:utility构件包名称修改为org.gocom.abframe.tools
 *
 * Revision 1.1  2008/10/07 09:25:48  wengzr
 * *** empty log message ***
 *
 * Revision 1.1  2008/09/17 09:38:31  wengzr
 * Update:重构常量定义
 *
 */
public interface UtilConfiguration {

	public static final String CONTRIBUTION_ABFRAME_UTILS="org.gocom.abframe.tools";

	public static final String MODULE_ABFRAME="abframe-config";

	public static final String GROUP_EXCEL="excel-config";

	/**
	 * EXCEL模板路径
	 */
	public static final String EXCEL_TEMPLATE_PATH="excel_template_path";
	
	/**
	 * 导出EXCEL最大行数
	 */
	public static final String EXCEL_EXPORT_MAXNUM="excel_export_maxnum";


}
