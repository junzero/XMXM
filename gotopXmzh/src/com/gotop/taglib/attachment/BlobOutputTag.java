/*******************************************************************************
 * $Header: /opt/cvsroot/wiki/opensource/gocom/abframe2/src/org.gocom.abframe.auth/src/org/gocom/abframe/auth/taglib/BlobOutputTag.java,v 1.8 2009/02/23 10:04:42 zhujiang Exp $
 * $Revision: 1.8 $
 * $Date: 2009/02/23 10:04:42 $
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2006 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2008-11-26
 *******************************************************************************/

package com.gotop.taglib.attachment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;

import com.eos.common.connection.ConnectionHelper;
import com.eos.das.entity.DASManager;
import com.eos.das.entity.IDASSession;
import com.eos.runtime.core.ApplicationContext;
import com.eos.system.exception.EOSRuntimeException;
import com.eos.system.utility.StringUtil;
import com.eos.web.taglib.basic.BaseIteratorTagSupport;
import com.eos.web.taglib.util.RequestUtil;
import com.eos.web.taglib.util.ResponseUtil;
import com.eos.web.taglib.util.XpathUtil;
import com.gotop.util.security.ForUtil;
import commonj.sdo.DataObject;

/**
 * 
 * ���ֶ����ݵ����������������ʵ��org.gocom.abframe.dataset.utility.AtFileupload<BR>
 * ���attachmentΪfalse,���ļ�ֱ���������ǰҳ��,ͨ��ΪͼƬ�ļ����<BR>
 * ���attachmentΪtrue,�����ļ����ӷ�ʽ��ʾ,����ļ������ظ��ļ�.
 * 
 * @author wengzr (mailto:wengzr@primeton.com)
 */
/*
 * �޸���ʷ $Log: BlobOutputTag.java,v $
 * �޸���ʷ Revision 1.8  2009/02/23 10:04:42  zhujiang
 * �޸���ʷ *** empty log message ***
 * �޸���ʷ Revision 1.7 2009/02/16 09:41:25 gengdw
 * bug�޸�
 * 
 * Revision 1.1 2008/11/29 12:31:09 wengzr
 * Refactor:�ع�BlobFieldTag->BlobOutputTag
 * 
 * Revision 1.2 2008/11/28 04:15:22 wengzr Added:��BlogFieldTag���ֶ������ǩ
 * Refactor:�ع�AuthConfiguration
 * 
 * Revision 1.1 2008/11/26 13:55:53 wengzr Added:���Ӵ��ֶ������ǩ
 * 
 */
public class BlobOutputTag extends BaseIteratorTagSupport {

	// ���ֶ�������
	private String blobProperty;

	// �Ƿ��Ը�����ʽ����
	private boolean attachment;

	// ָ���ļ���
	private String fileName;

	private String fileNewName;

	// ָ���ļ�����
	private String fileType;

	// ָ���ļ���ʾ·��
	private String filePath;

	private StringBuffer html;

	// �ļ���ȡ���ͣ�1Ϊ�ļ�ϵͳ��2Ϊ���ݿ�ϵͳ
	private String fileSave;

	/**
	 * �Ƿ񸽼�
	 * 
	 * @return
	 */
	public boolean isAttachment() {
		return attachment;
	}

	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}

	/**
	 * ���ֶ�����
	 * 
	 * @return
	 */
	public String getBlobProperty() {
		return blobProperty;
	}

	public void setBlobProperty(String blobField) {
		this.blobProperty = blobField;
	}

	public int doStartTag() throws JspException {
		// initAttributes();
		DataObject dataObject = null;
		// ��ȡ��ǰ���ݶ���������ڵ���ID����ӵ�ǰpageContext��ȡ���ݶ���,�����Property��ȡ���ݶ���
		// ��ȡ���ݶ���󣬴�blobProperty��ȡ����Ĵ��ֶ�����
		if (getIterateId() != null)
			dataObject = (DataObject) pageContext.getAttribute(this
					.getIterateId());
		else
			dataObject = (DataObject) XpathUtil.getObjectByXpath(getRootObj(),
					getProperty());

		// ��������AtFileuploadʵ��,�ļ����ܴ���server����·��
		if ("AtFileupload".equalsIgnoreCase(dataObject.getType().getName())) {
			fileName = dataObject.getString("fileName");
			fileNewName = dataObject.getString("fileNewName");
			fileSave = dataObject.getString("fileSave");
			if ("1".equals(fileSave))// ������ļ�ϵͳ��ȡ�����ȡfilepath�ֶ�
				filePath = dataObject.getString("filePath");
			fileType = dataObject.getString("fileType");
		}

		if (fileName == null){
			fileName = getDefaultFileName();
			fileNewName = fileName;
		}
		if (filePath == null)
			filePath = getDefaultFilePath() + File.separator
					+ fileName;

		File file = ForUtil.createFile(filePath);
		if (!file.exists()) {
			expandLobProperty(dataObject, blobProperty);
			filePath = dataObject.getString(blobProperty);
			if (filePath == null) {
				return EVAL_PAGE;
			}
		}

		if (attachment) {
			html = new StringBuffer();
			html.append("<a href=\"").append(getActionUrl()).append(
					"&attachment=true").append("\">").append(fileName).append(
					"</a>");
		} else {
			html.append("<img id=\"").append(fileName).append("\" ").append(
					"src=\"").append(getActionUrl())
					.append("&attachment=false").append("\"/>");
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * @return ��ȡĬ�ϵ��ļ���
	 */
	private String getDefaultFileName() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetimeString = format.format(new Date());
		return datetimeString;
	}

	/**
	 * ��ȡĬ���ļ�·��
	 * 
	 * @return
	 */
	private String getDefaultFilePath() {
		String uploadPath = ApplicationContext.getInstance()
				.getApplicationWorkPath()
				+ File.separator + "upload";
		return uploadPath;
	}

	/**
	 * ��ȡ���ֶ������������·��
	 * 
	 * @return
	 */
	private String getActionUrl() {
		StringBuffer action = new StringBuffer();
		try {
			action.append(RequestUtil.getContextPath(pageContext)).append(
					"/auth/blob.jsp?").append("fileType=").append(
					java.net.URLEncoder.encode(fileType, "UTF-8")).append("&")
					.append("filePath=").append(
							java.net.URLEncoder.encode(filePath, "UTF-8"))
					.append("&").append("fileName=").append(
							java.net.URLEncoder.encode(fileNewName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return action.toString();
	}

	public int doEndTag() throws JspException {
		ResponseUtil.write(pageContext, html.toString());
		release();
		return EVAL_PAGE;
	}

	@Override
	public void initAttributes() throws JspException {
		// TODO Auto-generated method stub
		super.initAttributes();
		html = new StringBuffer();

		String fileTypeValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"fileType");
		if (StringUtil.isNotNullAndBlank(fileTypeValue))
			fileType = fileTypeValue;

		String filePathValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"filePath");
		if (StringUtil.isNotNullAndBlank(filePathValue))
			filePath = filePathValue;

		String fileNameValue = XpathUtil.getStringByXpathSupport(getRootObj(),
				"fileName");
		if (StringUtil.isNotNullAndBlank(fileNameValue))
			fileName = fileNameValue;

	}

	/**
	 * ���ֽ���д�뵽�ļ�
	 * 
	 * @param fileName
	 *            �ļ���
	 * @param bytes
	 *            �ֽ�����
	 */
	private void streamToFile(String fileName, byte[] bytes) {
		File file = ForUtil.createFile(fileName);
		FileOutputStream fos = null;
		try {
			file.createNewFile();
			fos = ForUtil.createFileOutputStream(file);
			fos.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Connection getConnection() {
		Connection conn = ConnectionHelper
				.getCurrentContributionConnection("default");
		return conn;
	}

	private void expandLobProperty(DataObject object, String blobField) {
		Connection conn = getConnection();
		IDASSession session = DASManager.createDasSession(conn);
		try {
			session.expandLobProperty(object, blobField);
		} finally {
			closeConnection(session, conn);
		}
	}

	/**
	 * �ر�DAS session�����ݿ����ӡ�
	 * 
	 * @param session
	 *            ���ݷ��ʻỰ����
	 * @param conn
	 *            ���ݿ����ӡ�
	 */
	protected static void closeConnection(IDASSession session, Connection conn) {
		if (session != null) {
			try {
				session.close();
			} catch (Exception e) {
				throw new EOSRuntimeException(
						"occur exception when close session", e);
			}
		}
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			throw new EOSRuntimeException(
					"occur exception when close connection", e);
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * �ͷ���Դ
	 */
	public void release() {
		// �ͷ���Դ
		super.release();
		this.attachment = false;
		this.blobProperty = null;
		this.fileName = null;
		this.filePath = null;
		this.fileType = null;
	}

}
