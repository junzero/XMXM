package com.gotop.tyjg.datadictionary.dao;

import java.util.HashMap;
import java.util.List;

import com.gotop.tyjg.datadictionary.model.*;

import com.primeton.utils.Page;
/**
 * create date 2012-03-20
 * 
 * @author liushi
 * 对数据字典的操作包括数据字典实体和数据类型的操作
 *
 */
public interface IDictEntryDao {
	/**
	 * 单条数据的新增操作，新增数据字典类型
	 * @param dictType 要插入的数据载体（对象）
	 * @return 返回true（操作成功）， false（操作失败）
	 * @throws Exception
	 */
	public boolean insertDictType(DictType dictType) throws Exception;
	
	/**
	 * 单条数据的删除操作，删除数据字典类型
	 * @param dictType 要删除的数据载体（对象）
	 * @return 返回true（操作成功）， false（操作失败）
	 * @throws Exception
	 */
	public boolean deleteDictType(DictType dictType) throws Exception;
	
	/**
	 * 单条数据的修改操作，修改数据字典类型
	 * @param dictType 要修改的数据载体（对象）
	 * @return 返回true（操作成功）， false（操作失败）
	 * @throws Exception
	 */
	public boolean updateDictType(DictType dictType) throws Exception;
	
	/**
	 * 根据数据载体信息查询数据字典类型，返回查询的结果列表
	 * @param dictType 信息数据载体（对象）
	 * @return 返回查询结果列表，返回类型List<DictType>
	 * @throws Exception
	 */
	public List<DictType> queryDictTypeList(DictType dictType) throws Exception;
	
	/**
	 * 根据数据载体信息查询数据字典列表，返回查询的结果列表（分页查询）
	 * @param dictType 数据信息载体（对象）
	 * @param page 分页信息
	 * @return 返回查询结果列表，返回类型List<DictType>
	 * @throws Exception
	 */
	public List<DictType> queryDictTypeList(DictType dictType, Page page) throws Exception;
	
	/**
	 * 查询数据字典类型总记录数
	 * @param dictType
	 * @return
	 * @throws Exception
	 */
	public int queryDictTypeCount(DictType dictType) throws Exception;
	
	/**
	 * 查询数据字典实体总记录数
	 * @param dictEntry 实体参数
	 * @return    
	 * @throws Exception
	 */
	public int queryDictEntryCount(DictEntry dictEntry) throws Exception;
	
	public boolean insertDictEntry(DictEntry dictEntry) throws Exception;
	
	public boolean deleteDictEntry(DictEntry dictEntry) throws Exception;
	
	public boolean updateDictEntry(DictEntry dictEntry) throws Exception;
	
	public List<DictEntry> queryDictEntryList(DictEntry dictEntry) throws Exception;
	
	public List<DictEntry> queryDictEntryList(DictEntry dictEntry, Page page) throws Exception;
}
