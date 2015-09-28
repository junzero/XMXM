package com.gotop.tyjg.ygfjxxChaXun.service;

import java.util.HashMap;
import java.util.List;

import com.gotop.vo.tyjg.Abftygfjxxb;
import com.primeton.utils.Page;

public interface IYgfjxxChaXunService {
	
	/**
	 * 根据条件查询员工附加信息
	 * @param cdMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryYgfjxxByCondit(HashMap cdMap,Page page) throws Exception;
	
	
	/**
	 * datacell提交保存
	 * @param hmp
	 * @return
	 * @throws Exception
	 */
	public boolean dataCellSubmitSave(HashMap hmp) throws Exception;
	
}
