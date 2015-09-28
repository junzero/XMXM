package com.gotop.monthReport.service;

import com.gotop.file.model.FileBean;
import com.gotop.jbpm.dto.TaskAssgineeDto;
import com.gotop.monthReport.dao.ITWorkMonthReportsDAO;
import com.gotop.monthReport.model.TWorkMonthReports;
import com.gotop.vo.system.MUOUserSession;
import com.primeton.utils.Page;

import java.io.File;
import java.util.List;

public interface ITWorkMonthReportsService {
    /**
     * 通过spring注入DAO的set类.
     * @abatorgenerated
     */
    void settWorkMonthReportsDAO(ITWorkMonthReportsDAO tWorkMonthReportsDAO) throws Exception;

    /**
     * 通过spring注入DAO的get类.
     * @abatorgenerated
     */
    ITWorkMonthReportsDAO gettWorkMonthReportsDAO() throws Exception;

    /**
     * 保存个人周报
     * @param completionFilesFileName 
     * @param completionFiles 
     * @param gatherFilesFileName 
     * @param gatherFiles 
     * @param businessType 
     * @param isFirst 
     * @return 
     */
    TaskAssgineeDto insertMonthReportsInfo(TWorkMonthReports monthReports,File[] monthReportFiles,
            File[] completionFiles, File[] gatherFiles, String[] monthReportFilesFileName,String[] completionFilesFileName, 
            String[] gatherFilesFileName, MUOUserSession muo,String btnType,TaskAssgineeDto taskAssgineeDto, String definitionId, String businessType, String isFirst) throws Exception;
    /**
     * 
     *
     * TODO 创建fileBean.
     *
     * @param monthReports
     * @param fileType
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-29    杨艺祥    新建
     * </pre>
     */
    
    FileBean makeFileBean(TWorkMonthReports monthReports,String fileType) throws Exception;
    
    /**
     * 
     *
     * TODO 生成任务实体。[jbpm]
     *
     * @param monthReports
     * @param id
     * @param muo
     * @param dto
     * @return
     * @throws Exception
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-29    杨艺祥    新建
     * </pre>
     */
    TaskAssgineeDto makeTaskAssgineeDto(TWorkMonthReports monthReports,String id,MUOUserSession muo,TaskAssgineeDto dto) throws Exception;

    
    /**
     * 
     *
     * TODO 动态查询，分页查询数据并返回list.
     *
     * @param muo
     * @param monthReports
     * @param page
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-30   杨艺祥    新建
     * </pre>
     */
    List<TWorkMonthReports> queryTWorkMonthReportsList(MUOUserSession muo,
            TWorkMonthReports monthReports, Page page);

    /**
     *
     * TODO 根据ID获取月报详情.
     *
     * @param reportId
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    张怡    新建
     * </pre>
     */
    TWorkMonthReports queryMonthReportInfo(Long reportId);

    /**
     * 
     *
     * TODO 根据流程ID获取月报实例.
     *
     * @param executionId
     * @return
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2014-7-31    yyx    新建
     * </pre>
     */
    TWorkMonthReports getMonthReportByFlowId(String flowId);
    
    
}