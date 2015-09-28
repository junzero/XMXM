package com.eos.server.dict;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eos.server.dict.impl.DictEntryImpl;
import com.eos.server.dict.impl.DictTypeImpl;
import com.primeton.utils.Condb;

public class EOSBusinDictFactory_gotop implements DictFactory{

	protected static Logger log = Logger
			.getLogger(EOSBusinDictFactory_gotop.class);

	@Override
	public Map<String, DictType> loadAllDictTypes() throws Exception {
		Map retlist = new HashMap();
/*		PreparedStatement ppsm=null;
		ResultSet rs=null;
		Connection conn = DBUtils.getCon("crm");
		String sql = "select fldm,bz from tXTDM group by fldm,bz";
		ppsm=conn.prepareStatement(sql);
		rs=ppsm.executeQuery();
		while(rs.next()){
			String dictTypeId = rs.getString("fldm");
			String DictTypeName = rs.getString("bz");
			DictType dictType = new DictTypeImpl(dictTypeId);
			dictType.setDictTypeName(DictTypeName);
			dictType.setLevel(1);
			
			String sqlt = "select * from tXTDM where fldm = '"+dictTypeId+"'";
			PreparedStatement ppsmt=conn.prepareStatement(sqlt);
			ResultSet rst=ppsmt.executeQuery();
			while(rst.next()){
				String dictId = rst.getString("zfbm");
				String dictName = rst.getString("sm");
				DictEntryImpl entry = new DictEntryImpl(dictId, dictName);
				dictType.addDictEntry(entry);
			}
			
			ppsmt.close();
			rst.close();
			retlist.put(dictTypeId, dictType);
		}
		rs.close();
		DBUtils.releaseSource(rs, ppsm, conn);
		Connection conn2 = DBUtils.getCon("crm");
		DBUtils.releaseSource(rs, ppsm, conn2);*/
		retlist.putAll(loadAbfDictTypes());
		return retlist;
	}

	public Map<String, DictType> loadAbfDictTypes() throws Exception{
		Map retlist = new HashMap();
		PreparedStatement ppsm=null;
		Condb condb = new Condb();
		Condb condb2 = new Condb();
		String sql = "select dicttypeid, dicttypename, rank, parentid, seqno from eos_dict_type";
		com.primeton.utils.ResultSet rs = condb.executeQuery(sql);
		while(rs.next()){
			String dictTypeId = rs.getString("dicttypeid");
			String DictTypeName = rs.getString("dicttypename");
			DictType dictType = new DictTypeImpl(dictTypeId);
			dictType.setDictTypeName(DictTypeName);
			dictType.setLevel(1);
			
			String sqlt = "select eos_dict_entry.dictid,eos_dict_entry.dictname from eos_dict_entry where eos_dict_entry.dicttypeid = '"+dictTypeId+"'";
			com.primeton.utils.ResultSet rst = condb2.executeQuery(sqlt);
			while(rst.next()){
				String dictId = rst.getString("dictid");
				String dictName = rst.getString("dictname");
				DictEntryImpl entry = new DictEntryImpl(dictId, dictName);
				dictType.addDictEntry(entry);
			}
			rst.close();
			retlist.put(dictTypeId, dictType);
		}
		condb2.close();
		rs.close();
		condb.close();
		
		DictManager.clearDict_Cache();
		return retlist;
	}
	
	public static void main(String[] arg){
		EOSBusinDictFactory_gotop eosbdf = new EOSBusinDictFactory_gotop();
		try {
			eosbdf.loadAllDictTypes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
