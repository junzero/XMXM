package com.primeton.utils;

/*create time 2009-7-21*/
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.eos.infra.connection.ConnectionFactory;
import com.gotop.util.dataSource.ConUtils;

public class Condb {
	protected Logger log = Logger.getLogger(Condb.class);
	Connection conn = null;

	ResultSet rs = null;

	Statement stmt = null;

	java.sql.PreparedStatement ps = null;

	java.sql.PreparedStatement upps = null;

	java.sql.PreparedStatement inps = null;

	/**
	 * 创建一个数据库连接
	 */
	public Condb() {
		try {
			conn = ConUtils.getConn();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 连接池方式数据库连接
	 */
	public Condb(String sourceName)
	{
		try{
			if(StringUtils.isEmpty(sourceName)){
				sourceName="yzjmwd";
			}
			javax.naming.Context context = new javax.naming.InitialContext();         
			DataSource ds = (javax.sql.DataSource) context.lookup( "java:comp/env/"+sourceName);
			
			conn = ConnectionFactory.createConnection(ds);
			
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	/**
	 * 执行数据库批量操作 Sql=执行的Sql语句 Parameter=参数列表 max=最大数 typedllSql=Sql语句类型
	 * incount，upcount=？总数 返回布尔型
	 */
	public boolean ExcutePreparedstatementSql(String upSql, String inSql,
			List<String> upParameter, List<String> inParameter, int max,
			int incount, int upcount) {
		int upk = 1, ink = 1;
		int count1 = 0, count2 = 0;
		long start = System.currentTimeMillis();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			if (null == upParameter && null == inParameter) {
				stmt.close();
				conn.close();
				return false;
			}
			int k1 = 0;
			int k2 = 0;
			if (inParameter != null) {
				k1 = inParameter.size();
			}
			if (upParameter != null) {
				k2 = upParameter.size();
			}

			if ("" != inSql && null != inParameter && inParameter.size() > 0) {
				ps = conn.prepareStatement(inSql);
				conn.setAutoCommit(false);
				// ------------------------一维数据INSERT处理-----------------------------
				for (int i = 0; i < k1; i++) {
					ps.setString(ink, inParameter.get(i));
					ink++;
					if ((i + 1) % incount == 0) {
						ps.addBatch();
						ink = 1;
						count1++;
					}
					if ((i != 0 && count1 != 0) && (count1) % max == 0) {// 可以设置不同的大小;如50，100，500，1000等等
						ps.executeBatch();
						conn.commit();
						count1 = 0;
					}

				}
				ps.executeBatch();
				conn.commit();
				ps.clearBatch();
			}
			if ("" != upSql && null != upParameter && upParameter.size() > 0) {
				ps = conn.prepareStatement(upSql);
				// ------------------------二维数据UPDATE处理-----------------------------------
				for (int j = 0; j < k2; j++) {
					ps.setString(upk, upParameter.get(j));
					upk++;
					if ((j + 1) % upcount == 0) {
						ps.addBatch();
						upk = 1;
						count2++;
					}
					if ((j != 0 && count2 != 0) && (count2) % max == 0) {// 可以设置不同的大小;如50，100，500，1000等等
						ps.executeBatch();
						conn.commit();
						count2 = 0;
					}
				}
				ps.executeBatch();
				conn.commit();
				System.out.println("提交成功");
				ps.clearBatch();
			}
			long end = System.currentTimeMillis();
		} catch (Exception e) {
			try {
				upSql = "";
				inSql = "";
				conn.rollback();
				ps.clearBatch();
				System.out.print(e.getMessage().toString() + "insert 语句第" + count1 + "条" + "--update 语句第" + count2 + "条");
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return true;
	}

	// ,String DllSql
	public boolean ExecuteSql(List<String> SQLTotal, int max) {
		boolean flag;
		try {
			conn.setAutoCommit(false);
			int a = SQLTotal.size();
			for (int i = 0; i < a; i++) {
				stmt.executeUpdate(SQLTotal.get(i));

				if ((i + 1) % max == 0) {// 可以设置不同的大小;如50，100，500，1000等等
					conn.commit();
				}
			}
			conn.commit();
			flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		return flag;
	}
	public ArrayList<HashMap<String, String>> prepareStatement(String sql, HashMap params) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<HashMap<String, String>> returns = new ArrayList<HashMap<String, String>>();
        List<String> paraList = getParam(sql);
        try {
            pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            if (paraList !=null && params != null) {// if the params is null it means no ?
            	Set set = params.keySet();
            	int i=0;
            	for (Iterator iterator = paraList.iterator(); iterator.hasNext();) {
					String name = (String) iterator.next();
					Object param = params.get(name);
                    if (param.getClass() == Integer.class) {
                        pstmt.setInt(i + 1, (Integer) param);
                    } else if (param.getClass() == String.class) {
                        pstmt.setString(i + 1, (String) param);
                    } else if (param.getClass() == Date.class) {
                        pstmt.setDate(i + 1, (Date) param);
                    } else if (param.getClass() == RowId.class) {
                        pstmt.setRowId(i + 1, (RowId)param);
                    } else {
                        pstmt.setObject(i+1, param);
                    }
                    i++;
                }
            }
            rs = pstmt.executeQuery();

            int ccount = rs.getMetaData().getColumnCount();
            String[] column = new String[ccount+1];
            int[] coltype = new int[ccount+1];
            for(int i=1;i<=ccount;i++){
            	coltype[i] = rs.getMetaData().getColumnType(i);
            	column[i] = rs.getMetaData().getColumnName(i);
            }
            while (rs.next()) {
                HashMap<String, String> temp = new HashMap<String, String>();
                for (int i = 1; i <= ccount; i++) {
                    if (coltype[i] == Types.VARCHAR) {
                        temp.put(column[i], rs.getString(i));
                    } else if (coltype[i] == Types.INTEGER) {
                        temp.put(column[i], rs.getString(i));
                    } else if (coltype[i] == Types.DATE) {
                    	Timestamp value = rs.getTimestamp(i);
                    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    	if(value!=null){
                    		temp.put(column[i], sdf.format(value));
                    	}else{
                    		temp.put(column[i], null);
                    	}
                    } else if (coltype[i] == Types.DOUBLE) {
                        temp.put(column[i], Double.toString(rs.getDouble(i)));
                    } else {
                    	Object value = rs.getObject(i);
                    	if(value!=null){
                    		temp.put(column[i], value.toString());
                    	}
                    }
                }
                returns.add(temp);
            }
        } catch (Exception ex) {
        	log.info("-异常SQL-"+sql);
        	log.info("-参数-"+params);
        	log.info("-分离-"+paraList);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returns;
    }
	public int count(String sql, HashMap params) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> paraList = getParam(sql);
        try {
        	sql = "select count(*) from ("
	        		+ sql 
	        		+ ")";
            pstmt = conn.prepareStatement(sql);

            if (paraList !=null && params != null) {// if the params is null it means no ?
            	Set set = params.keySet();
            	int i=0;
            	for (Iterator iterator = paraList.iterator(); iterator.hasNext();) {
					String name = (String) iterator.next();
					Object param = params.get(name);
/*					if(param==null){
						throw new Exception(param+"参数为空！");
					}
*/                    
					if (param.getClass() == Integer.class) {
                        pstmt.setInt(i + 1, (Integer) param);
                    } else if (param.getClass() == String.class) {
                        pstmt.setString(i + 1, (String) param);
                    } else if (param.getClass() == Date.class) {
                        pstmt.setDate(i + 1, (Date) param);
                    } else if (param.getClass() == RowId.class) {
                        pstmt.setRowId(i + 1, (RowId)param);
                    } else {
                        pstmt.setObject(i+1, param);
                    }
                    i++;
                }
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	int num = rs.getInt(1);
            	return num;
            }
        } catch (Exception ex) {
        	log.info("-异常SQL-"+sql);
        	log.info("-参数-"+params);
        	log.info("-分离-"+paraList);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
	public ArrayList<HashMap<String, String>> prepareStatement(String sql, Page page, HashMap params) throws Exception {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select * from (");
		sbf.append("       select t.*,rownum num from(");
		sbf.append(sql);
		sbf.append("       ) t");
		sbf.append(")where num>"+page.begin);
		sbf.append(" and num<="+(page.begin+page.length));
		ArrayList<HashMap<String, String>> result = this.prepareStatement(sbf.toString(), params);
		int num = this.count(sql, params);
		page.setCount(num);
		return result;
	}
	public List getParam(String sqlStr){
		List list = new ArrayList();
		char[] tca = sqlStr.toCharArray();
		for (int i = 0; i < tca.length; i++) {
			char t = tca[i];
			if(t == ':'){
				StringBuffer sbf = new StringBuffer();
				int j = i+1;
				for (; j < tca.length; j++) {
					if ((tca[j] >= '0' && tca[j] <= '9') 
							|| (tca[j] >= 'a' && tca[j] <= 'z') 
							|| (tca[j] >= 'A' && tca[j] <= 'Z')
							|| tca[j] >= '_') {
						sbf.append(tca[j]);
					}else{
						break;
					}
				}
				i = j;
				list.add(sbf.toString());
			}
		}
		return list;
	}
	
	public ArrayList<HashMap<String, String>> find(String sql, Object[] params) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<HashMap<String, String>> returns = new ArrayList<HashMap<String, String>>();

        try {
            pstmt = conn.prepareStatement(sql);

            if (params != null) {// if the params is null it means no ?
                for (int i = 0; i < params.length; i++) {
                    if (params[i].getClass() == Integer.class) {
                        pstmt.setInt(i + 1, (Integer) params[i]);
                    } else if (params[i].getClass() == String.class) {
                        pstmt.setString(i + 1, (String) params[i]);
                    } else if (params[i].getClass() == Date.class) {
                        pstmt.setDate(i + 1, (Date) params[i]);
                    } else if (params[i].getClass() == RowId.class) {
                        pstmt.setRowId(i + 1, (RowId)params[i]);
                    } else {
                        // for some other types
                    }
                }

                if (questionCount(sql) != params.length) {
                    throw new Exception("SQL需要的参数个数与实际传递的不一致！");
                }
            }
            rs = pstmt.executeQuery();

            int ccount = rs.getMetaData().getColumnCount();
            String[] column = new String[ccount+1];
            int[] coltype = new int[ccount+1];
            for(int i=1;i<=ccount;i++){
            	coltype[i] = rs.getMetaData().getColumnType(i);
            	column[i] = rs.getMetaData().getColumnName(i);
            }
            while (rs.next()) {
                HashMap<String, String> temp = new HashMap<String, String>();
                for (int i = 1; i <= ccount; i++) {
                    if (coltype[i] == Types.VARCHAR) {
                        temp.put(column[i], rs.getString(i));
                    } else if (coltype[i] == Types.INTEGER) {
                        temp.put(column[i], rs.getString(i));
                    } else if (coltype[i] == Types.DATE) {
                    	Date value = rs.getDate(i);
                    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        temp.put(column[i], sdf.format(value));
                    } else if (coltype[i] == Types.DOUBLE) {
                        temp.put(column[i], Double.toString(rs.getDouble(i)));
                    } else {
                    	Object value = rs.getObject(i);
                    	if(value!=null){
                    		temp.put(column[i], value.toString());
                    	}
                    }
                }
                returns.add(temp);
            }
        } catch (Exception ex) {
        	log.info("-异常SQL-"+sql);
        	log.info("-参数-"+params);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returns;
    }
	public int count(String sql, Object[] params) throws Exception {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	sql = "select count(*) from ("
	        		+ sql 
	        		+ ")";
            pstmt = conn.prepareStatement(sql);

            if (params != null) {// if the params is null it means no ?
                for (int i = 0; i < params.length; i++) {
                    if (params[i].getClass() == Integer.class) {
                        pstmt.setInt(i + 1, (Integer) params[i]);
                    } else if (params[i].getClass() == String.class) {
                        pstmt.setString(i + 1, (String) params[i]);
                    } else if (params[i].getClass() == Date.class) {
                        pstmt.setDate(i + 1, (Date) params[i]);
                    } else {
                        // for some other types
                    }
                }

                if (questionCount(sql) != params.length) {
                    throw new Exception("SQL需要的参数个数与实际传递的不一致！");
                }
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	int num = rs.getInt(1);
            	return num;
            }
        } catch (Exception ex) {
        	log.info("-异常SQL-"+sql);
        	log.info("-参数-"+params);
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
	public ArrayList<HashMap<String, String>> find(String sql, Page page, Object[] params) throws Exception {
		StringBuffer sbf = new StringBuffer();
		sbf.append("select * from (");
		sbf.append("       select t.*,rownum num from(");
		sbf.append(sql);
		sbf.append("       ) t");
		sbf.append(")where num>"+page.begin);
		sbf.append(" and num<="+(page.begin+page.length));
		ArrayList<HashMap<String, String>> result = this.find(sbf.toString(), params);
		int num = this.count(sql, params);
		page.setCount(num);
		return result;
	}
	private int questionCount(String sql) {
        int questions = 0;
        for (int i = 0; i < sql.length(); i++) {
            if (sql.charAt(i) == '?')
                questions++;
        }
        return questions;
    }
	/**
	 * 一般用于查询操作
	 * 
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public com.primeton.utils.ResultSet executeQuery(String sql){
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
        	log.info("-异常SQL-"+sql);
			e.printStackTrace();
			rs=null;
		}
		com.primeton.utils.ResultSet resultSet = new com.primeton.utils.ResultSet(rs);
		resultSet.setSql(sql);
		return resultSet;
	}

	/**
	 * 执行更新操作
	 * 
	 * @param sql
	 * @return
	 */
	public int executeUpdate(String sql) throws Exception{
		int result = 0;
		result = stmt.executeUpdate(sql);
		return result;
	}

	public Statement getStmt() {
		return stmt;
	}

	public Connection getConn() {
		return conn;
	}

	/**
	 * 关闭所有连接
	 */

	public void close() {
		try {
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
