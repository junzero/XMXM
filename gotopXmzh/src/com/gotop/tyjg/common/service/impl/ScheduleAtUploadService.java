package com.gotop.tyjg.common.service.impl;

import com.gotop.tyjg.common.service.IAttachmentService;
import com.gotop.tyjg.common.service.IScheduleAtUploadService;
import org.apache.log4j.Logger;

public class ScheduleAtUploadService implements IScheduleAtUploadService{
	protected static Logger log = Logger.getLogger(ScheduleAtUploadService.class);
	protected IAttachmentService attachmentService;
	
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}


	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	/**
	 * 删除上月未确认的资源列表。 FILE_PATH is null基本判定垃圾数据
	 * /tmp下的临时文件可与项目组联系
	 */
	public void deleteAtUploadJob(){
		attachmentService.deleteNotEffective();
	}

}
