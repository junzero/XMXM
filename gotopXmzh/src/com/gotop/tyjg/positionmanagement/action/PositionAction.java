package com.gotop.tyjg.positionmanagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gotop.crm.util.BaseAction;
import com.gotop.tyjg.positionmanagement.service.IPositionService;
import com.gotop.util.XmlConvert;
import com.gotop.vo.tyjg.OmPosition;

import org.apache.log4j.Logger;

/**
 * *******************************
 * <p>Title: 岗位管理分发请求</p>
 * 
 * <p> Description: 岗位管理分发请求</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: GOTOP</p>
 * 
 * @author xuxh
 * 
 * @date Apr 9, 2012
 * 
 * @version 1.0
 * 
 * HISTORY Apr 9, 2012 xuxh 创建文件
 * 
 * *******************************
 */
public class PositionAction extends BaseAction {

	protected static Logger log = Logger.getLogger(PositionAction.class);
	
	private static final long serialVersionUID = -820296999104074743L;
	
	private OmPosition omPosition;
	
	private IPositionService positionService;
	
	private List<OmPosition> omPositionList = new ArrayList<OmPosition>();

	public OmPosition getOmPosition() {
		return omPosition;
	}

	public void setOmPosition(OmPosition omPosition) {
		this.omPosition = omPosition;
	}

	public List<OmPosition> getOmPositionList() {
		return omPositionList;
	}

	public void setOmPositionList(List<OmPosition> omPositionList) {
		this.omPositionList = omPositionList;
	}
	
	public IPositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(IPositionService positionService) {
		this.positionService = positionService;
	}
	/**
	 * 查询岗位列表
	 * @return
	 * @throws Exception
	 */
	public String queryPositionList() throws Exception{
		this.setOmPositionList(this.getPositionService().selectPositionList(this.getOmPosition(), this.getPage()));
		this.setPage(page);
		return "queryPositionList";
	}
	/**
	 * 新增岗位信息
	 * @return
	 * @throws Exception
	 */
	public String insertPosition() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			this.getPositionService().insertPosition(omPosition);
			buffer.append("alert('新增成功！');window.parent.close();");
		}catch (Exception e) {
			buffer.append("alert('新增失败');");
			log.error("[岗位新增失败]", e);
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	}
	/**
	 * 修改岗位信息
	 * @return
	 * @throws Exception
	 */
	public String updatePosition() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<script>");
			this.getPositionService().updatePosition(omPosition);
			buffer.append("alert('岗位更新成功！');window.parent.close();");
		}catch (Exception e) {
			buffer.append("alert('岗位更新失败');");
			log.error("[岗位新增失败]", e);
			throw e;
		}finally{
			buffer.append("</script>");
			this.write(buffer.toString());
		}
		return null;
	} 
	/**
	 * 查询单个岗位信息
	 * @return
	 * @throws Exception
	 */
	public String querySinglePosition() throws Exception{
		this.setOmPosition(this.getPositionService().selectSinglePosition(omPosition));
		return "querySinglePosition";
	}
	/**
	 * 批量删除岗位信息
	 * @return
	 * @throws Exception
	 */
	public String deleteBeathPosition() throws Exception{
		StringBuffer buffer = new StringBuffer(100);
		try{
			buffer.append("<root><data><rtun>");
			HashMap<String, String> hmp = XmlConvert.getParamsAjax();
			String positionIds = hmp.get("positionids");
			this.getPositionService().deleteBeathPosition(positionIds);
			buffer.append("success");
		}catch (Exception e) {
			buffer.append("fairl");
			log.error("[批量删除岗位信息失败]", e);
			throw e;
		}finally{
			buffer.append("</rtun></data></root>");
			this.write(buffer.toString());
		}
		return null;
	}
}
