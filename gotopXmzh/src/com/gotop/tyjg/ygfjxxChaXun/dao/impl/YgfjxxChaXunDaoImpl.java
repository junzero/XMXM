package com.gotop.tyjg.ygfjxxChaXun.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.gotop.util.dataSource.SqlMapClientDao;
import com.gotop.tyjg.ygfjxxChaXun.dao.IYgfjxxChaXunDao;
import com.gotop.vo.tyjg.Abftygfjxxb;

public class YgfjxxChaXunDaoImpl extends SqlMapClientDao implements IYgfjxxChaXunDao{

	@SuppressWarnings("unchecked")
	@Override
	public List queryYgfjxxByCondit(HashMap<String,String> cdMap) throws Exception {
		List list = this.queryForList("ygfjxx_mngr.query_ygfjxx_info", cdMap);
//		List list = this.queryForList("ygfjxx_mngr.query_ygfjxx_list", cdMap);
		return list;
	}

	@Override
	public long countYgfjxx(HashMap<String, String> cdMap) throws Exception {
		long count =Long.parseLong(String.valueOf(this.queryForObject("ygfjxx_mngr.countYgfjxxByCondit", cdMap)));
		return count;
	}

	@Override
	public int delYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception {
		int reslt =this.delete("ygfjxx_mngr.deleteYgfjxxByid", ygfjxx);
		return reslt;
	}

	@Override
	public boolean insertYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception {
		Abftygfjxxb abfygxx =null;
		boolean reslt=false;
		abfygxx =(Abftygfjxxb)this.insert("ygfjxx_mngr.insert_ygfjxx_obj", ygfjxx);
		if(abfygxx!=null){
			reslt =true;
		}
		return reslt;
	}

	@Override
	public int updateYgfjxxEntity(Abftygfjxxb ygfjxx) throws Exception {
		int reslt =this.update("ygfjxx_mngr.update_ygfjxx_obj", ygfjxx);
		return reslt;
	}
	
	
	

}
