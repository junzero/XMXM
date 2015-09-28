package test.develop.ibatis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.Resource;

import test.develop.FileDesc;

import com.ibatis.common.xml.NodeletException;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapConfigParser;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapParser;
import com.ibatis.sqlmap.engine.builder.xml.XmlParserState;
import com.ibatis.sqlmap.engine.config.SqlMapConfiguration;
import com.ibatis.sqlmap.engine.impl.SqlMapClientImpl;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;

public class DySqlMapClient extends SqlMapClientImpl {

	private SqlMapParser mapParser = null;
	private SqlMapConfigParser configParser = null;
	private Resource[] configLocations = null;
	private List<FileDesc> descList = null;
	private DySqlMapExecutorDelegate myDelegate = null;

	private Map<String, FileDesc> statementMap = null;

	private DySqlMapClient(SqlMapExecutorDelegate delegate) {
		super(delegate);
	}

	public DySqlMapClient(SqlMapClient client, SqlMapParser mapParser, SqlMapConfigParser configParser, Resource[] configLocations) {
		super(new DySqlMapExecutorDelegate(((SqlMapClientImpl) client).getDelegate()));
		this.myDelegate = (DySqlMapExecutorDelegate) this.delegate;
		this.myDelegate.setClient(this);
		this.mapParser = mapParser;
		this.configParser = configParser;
		this.configLocations = configLocations;
		initFileDescList();
		initStatementMap();
		initState();
	}

	public FileDesc getFileDescUseId(String id) {
		FileDesc fd = statementMap.get(id);
		return fd;
	}
	
	public FileDesc getFileDescByNamespace(String id) {
		if(id==null){
			return null;
		}
		String namespace = id.substring(0,id.indexOf(".")+1);
		Set<String> smap = statementMap.keySet();
		for (Iterator iterator = smap.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			if(name.startsWith(namespace)){
				return statementMap.get(id);
			}
		}
		return null;
	}

	public void refreshFileDesc(String id, long tm) {
		FileDesc fd = getFileDescUseId(id);
		fd.setTm(tm);
	}

	public void reParseSqlMap(InputStream is) {
		try {
			this.mapParser.parse(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NodeletException e) {
			e.printStackTrace();
		}
	}

	public void initState() {
		try {
			Field f = this.configParser.getClass().getDeclaredField("state");
			f.setAccessible(true);
			XmlParserState state = (XmlParserState) f.get(this.configParser);
			Field configFiled = state.getClass().getDeclaredField("config");
			configFiled.setAccessible(true);
			SqlMapConfiguration impl = (SqlMapConfiguration) configFiled.get(state);
			Field clientField = impl.getClass().getDeclaredField("client");
			clientField.setAccessible(true);
			clientField.set(impl, this);
			Field delegateField = impl.getClass().getDeclaredField("delegate");
			delegateField.setAccessible(true);
			delegateField.set(impl, this.myDelegate);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void initFileDescList() {
		descList = new ArrayList<FileDesc>();
		for (int i = 0; i < configLocations.length; i++) {
			try {
				String path = configLocations[i].getFile().getAbsolutePath();
				descList.addAll(SqlMapConfigUtils.readSqlMapFileMapping(path));
			} catch (IOException e) {
			}
		}
	}

	private void initStatementMap() {
		this.statementMap = new HashMap<String, FileDesc>();
		for (Iterator<FileDesc> it = descList.listIterator(); it.hasNext();) {
			FileDesc desc = it.next();
			List<String> list = SqlMapConfigUtils.readSqlMap(desc);
			for (Iterator<String> i = list.listIterator(); i.hasNext();) {
				String id = i.next();
				statementMap.put(id, desc);
			}
		}
	}
}