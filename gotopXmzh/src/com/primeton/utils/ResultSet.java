package com.primeton.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class ResultSet {
	protected Logger log = Logger.getLogger(ResultSet.class);
	java.sql.ResultSet rs;
	HashMap<String, Integer> column;
	int rowCount = 0;
	int[] coltype;
	String sql;
	public ResultSet(java.sql.ResultSet rs){
		this.rs = rs;
		MetaData();
		rowCount = getCount();
	}
	public void MetaData(){
        try {
			int ccount = rs.getMetaData().getColumnCount();
			coltype = new int[ccount + 1];
			column = new HashMap<String, Integer>();
			for (int i = 1; i <= ccount; i++) {
				coltype[i] = rs.getMetaData().getColumnType(i);
				String cname = rs.getMetaData().getColumnName(i);
				column.put(cname.toUpperCase(), coltype[i]);
			}
		} catch (Exception e) {
			errorSql();
			e.printStackTrace();
		}
	}
	public int getCount(){
		try {
			rs.last(); //移到最后一行    
			int rowCount = rs.getRow(); //得到当前行号，也就是记录数    
			rs.beforeFirst(); //如果还要用结果集，就把指针再移到初始化的位置  
			return rowCount;
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
	}
	public String getString(int i) throws SQLException{
		String result="";
        if (coltype[i] == Types.VARCHAR) {
        	result = rs.getString(i);
        } else if (coltype[i] == Types.INTEGER) {
        	result = rs.getString(i);
        } else if (coltype[i] == Types.DATE) {
        	Date value = rs.getDate(i);
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	result = sdf.format(value);
        } else if (coltype[i] == Types.DOUBLE) {
        	result = Double.toString(rs.getDouble(i));
        } else {
        	Object value = rs.getObject(i);
        	if(value!=null){
        		result = value.toString();
        	}
        }
		return result;
	}
	public String getString(String columnLabel){
		String result="";
		try {
			int ctype = column.get(columnLabel.toUpperCase());
			if (ctype == Types.VARCHAR) {
				result = rs.getString(columnLabel);
			} else if (ctype == Types.INTEGER) {
				result = rs.getString(columnLabel);
			} else if (ctype == Types.DATE) {
				Date value = rs.getDate(columnLabel);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				result = sdf.format(value);
			} else if (ctype == Types.DOUBLE) {
				result = Double.toString(rs.getDouble(columnLabel));
			} else {
				Object value = rs.getObject(columnLabel);
				if(value!=null){
					result = value.toString();
				}
			}
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return result;
	}
	public String getDateStr(int columnIndex) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = rs.getDate(columnIndex);
		return sdf.format(date);
	}
	public String getDateStr(String columnLabel) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = rs.getDate(columnLabel);
		return sdf.format(date);
	}
	public String getDateTime(int columnIndex) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp stamp = rs.getTimestamp(columnIndex);
		return sdf.format(stamp);
	}
	public String getDateTime(String columnLabel) throws SQLException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp stamp = rs.getTimestamp(columnLabel);
		return sdf.format(stamp);
	}
	
	public boolean next(){
		if(rs==null){
			return false;
		}
		try {
			return rs.next();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isBeforeFirst(){
		try {
			return rs.isBeforeFirst();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isAfterLast(){
		try {
			return rs.isAfterLast();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
	}
	

    public void beforeFirst(){
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
    }

    public void afterLast(){
		try {
			rs.afterLast();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
    }

    public boolean first(){
		try {
			return rs.first();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
    }

    public boolean last(){
		try {
			return rs.last();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
    }

    public int getRow(){
		try {
			return rs.getRow();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
    }

    public boolean absolute( int row ){
		try {
			return rs.absolute(row);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
    }

    public boolean relative( int rows ){
		try {
			return rs.relative(rows);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
    }

    public boolean previous(){
		try {
			return rs.previous();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
    }
    public int getFetchSize(){
		try {
			return rs.getFetchSize();
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
    }
    public void close(){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	public int getInt(int columnIndex) {
		try {
			return rs.getInt(columnIndex);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
	}

	public int getInt(String columnLabel) {
		try {
			return rs.getInt(columnLabel);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
	}
	public long getLong(int columnIndex) {
		try {
			return rs.getLong(columnIndex);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
	}

	public long getLong(String columnLabel) {
		try {
			return rs.getLong(columnLabel);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return 0;
	}
	public java.sql.Date getDate(int columnIndex) {
		try {
			return rs.getDate(columnIndex);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return null;
	}

	public java.sql.Date getDate(String columnLabel) {
		try {
			return rs.getDate(columnLabel);
		} catch (SQLException e) {
			errorSql();
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean hasNext(){
		if(rs==null){
			return false;
		}
		try {
			if (rowCount > rs.getRow()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
	}
	public boolean hasNext(int i){
		if(rs==null){
			return false;
		}
		try {
			if (rowCount > i) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			errorSql();
			e.printStackTrace();
		}
		return false;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public void errorSql(){
		this.close();
		this.rs = null;
		log.info("-异常结果集-"+sql);
	}
}
