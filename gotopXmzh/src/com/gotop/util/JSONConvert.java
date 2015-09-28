package com.gotop.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

import com.garyman.db.util.ResultPack;
import com.gotop.util.Global;

/**
 * 
 * @author garyman
 * 
 */
public class JSONConvert {

	public String toJSONString(Object JSONOBJ, int ObjType) throws Exception {
		String jsonString = "";
		switch (ObjType) {
		case Global.JSON_TYPE_TREE:
			jsonString = Tree(JSONOBJ);
			break;
		case Global.JSON_TYPE_GRID:
			jsonString = Grid(JSONOBJ);
			break;
		case Global.JSON_TYPE_OTHER:
			jsonString = Other(JSONOBJ);
			break;
		default:
			break;
		}
		return jsonString;

	}

	private String Other(Object JSON) throws Exception {
		String result = "";
		JSONArray jsonArray = null;
		try {
			jsonArray = JSONArray.fromObject(JSON,this.getJsonConfig());
			result = jsonArray.toString();
		} catch (Exception e) {
			throw new Exception("严重:JSON-Other 数据转换失败:" + e.getMessage());
		} finally {
			jsonArray.clear();
			jsonArray = null;
		}
		return result;
	}

	private String Grid(Object JSON) throws Exception {
		String grid = "";
		JSONObject json = null;
		try {
			//jsonArray = JSONArray.fromObject(JSON,this.getJsonConfig());
			ResultPack datalist = (ResultPack) JSON;
			if (datalist.getRows().size() < 1)
				return "{\"total\":0,\"rows\":[]}";
			//jsonArray.add("total:"+datalist.getCount());
			json = JSONObject.fromObject(JSON, this.getJsonConfig())==null?new JSONObject():JSONObject.fromObject(JSON, this.getJsonConfig());
			json.put("total", datalist.getCount());
			grid = json.toString();
		} catch (Exception e) {
			throw new Exception("严重:JSON-DataGrid 数据转换失败:" + e.getMessage());
		} finally {
			if(null!=json)
			json.clear();
			json = null;
		}
		return grid;
	}

	private String Tree(Object JSON) throws Exception {
		String tree = "";
		try {
			tree = this.getJSON(JSON);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return tree;
	}

	private String getJSON(Object JSON) throws Exception {
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (name.equals("aa")) { // 要过滤的areas ，Map对象中的
					return true;
				} else {
					return false;
				}
			}
		});

		JSONArray jsonArray = null;
		String jsonstr = "";
		try {
			jsonArray = JSONArray.fromObject(JSON, config);
		} catch (Exception e) {
			throw new Exception("无影响:JSON-Tree 数据转换失败:" + e.getMessage());
		} finally {
			jsonstr = jsonArray.toString();
			jsonArray.clear();
			jsonArray = null;
		}
		return jsonstr;
	}
	
	
	/**
	 * JSON格式类型注册
	 * @return
	 */
	public JsonConfig getJsonConfig() {

		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new JsonValueProcessor() {
					private final String format = "yyyy-MM-dd";
					public Object processObjectValue(String key, Object value,
							JsonConfig arg2) {
						if (value == null)
							return "";
						if (value instanceof Date) {
							String str = new SimpleDateFormat(format)
									.format((Date) value);
							return str;
						}
						return value.toString();
					}
					
					public Object processArrayValue(Object value,
							JsonConfig arg1) {
						return null;
					}
				});
		
		cfg.registerJsonValueProcessor(java.sql.Timestamp.class,
				new JsonValueProcessor() {
					private final String format = "yyyy-MM-dd";
					public Object processObjectValue(String key, Object value,
							JsonConfig arg2) {
						if (value == null)
							return "";
						if (value instanceof Timestamp) {
							String str = new SimpleDateFormat(format).format(value);
							return str;
						}
						return value.toString();
					}
					
					public Object processArrayValue(Object value,
							JsonConfig arg1) {
						return null;
					}
				});
		
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new JsonValueProcessor() {
					private final String format = "yyyy-MM-dd";
					public Object processObjectValue(String key, Object value,
							JsonConfig arg2) {
						if (value == null)
							return "";
						if (value instanceof Date) {
							String str = new SimpleDateFormat(format)
									.format((Date) value);
							return str;
						}
						return value.toString();
					}
					
					public Object processArrayValue(Object value,
							JsonConfig arg1) {
						return null;
					}
				});
		return cfg;
	}
	
}
