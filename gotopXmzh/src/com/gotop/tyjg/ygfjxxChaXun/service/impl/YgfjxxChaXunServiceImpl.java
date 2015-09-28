package com.gotop.tyjg.ygfjxxChaXun.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.ygfjxxChaXun.dao.IYgfjxxChaXunDao;
import com.gotop.tyjg.ygfjxxChaXun.service.IYgfjxxChaXunService;
import com.gotop.vo.tyjg.Abftygfjxxb;
import com.primeton.utils.Page;

public class YgfjxxChaXunServiceImpl implements IYgfjxxChaXunService{
	
	private IYgfjxxChaXunDao ygfjxxDao;
	public IYgfjxxChaXunDao getYgfjxxDao() {
		return ygfjxxDao;
	}

	public void setYgfjxxDao(IYgfjxxChaXunDao ygfjxxDao) {
		this.ygfjxxDao = ygfjxxDao;
	}


	@Override
	public List<Abftygfjxxb> queryYgfjxxByCondit(HashMap cdMap,Page page)
			throws Exception {
		cdMap.put("oracleStart", page.getBegin());
		cdMap.put("oracleEnd", page.getBegin()+page.getLength());
		List ygfjxxList =this.getYgfjxxDao().queryYgfjxxByCondit(cdMap);
		long count =this.ygfjxxDao.countYgfjxx(cdMap);
		page.setCount(Integer.parseInt(String.valueOf(count)));
		return ygfjxxList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean dataCellSubmitSave(HashMap hmp) throws Exception {
		List<Abftygfjxxb> delEntity =(List<Abftygfjxxb>)hmp.get("deleteEntities");
		List<Abftygfjxxb> insertEntity =(List<Abftygfjxxb>)hmp.get("insertEntities");
		List<Abftygfjxxb> updateEntity =(List<Abftygfjxxb>)hmp.get("updateEntities");
		Abftygfjxxb ygfjxx =null;
		
		if(delEntity!=null && delEntity.size()>0){
			for(int i=0;i<delEntity.size();i++){
				ygfjxx =(Abftygfjxxb)delEntity.get(i);
				this.ygfjxxDao.delYgfjxxEntity(ygfjxx);
			}
		}
		
		if(insertEntity!=null && insertEntity.size()>0){
			for(int i=0;i<insertEntity.size();i++){
				ygfjxx =(Abftygfjxxb)insertEntity.get(i);
				this.ygfjxxDao.insertYgfjxxEntity(ygfjxx);
			}
		}
		
		if(updateEntity!=null && updateEntity.size()>0){
			for(int i=0;i<updateEntity.size();i++){
				ygfjxx =(Abftygfjxxb)updateEntity.get(i);
				Date nowDate =new Date();
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
				String nowTime =sdf.format(nowDate);
				System.out.println("nowTime ==== "+nowTime);
				ygfjxx.setLastModyTime(nowTime);
				this.ygfjxxDao.updateYgfjxxEntity(ygfjxx);
			}
		}
		
		return true;
	}

}
