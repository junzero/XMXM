package com.gotop.util.hibernate;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ResultSetDAO implements ResultSetExtractor
{
    
    int startRow,endRow;
    boolean selectAll=false;
    public ResultSetDAO(int pageNo,int pageSize)
    {
        if((pageNo+pageSize)==0)
        {
          selectAll=true;
        }
        else{
          setStartRow((pageNo-1)* pageSize);
          setEndRow((pageNo)* pageSize);
          selectAll=false;
        }
    }
    public Object extractData(ResultSet rs) throws SQLException, DataAccessException
    {
        List datas = new ArrayList();
        ResultSetMetaData metaData = rs.getMetaData();
        if(selectAll){
            if(rs!=null){
//              定位到起始位置
                while (rs.next())
                {
                    Map data = new HashMap();
                    for (int j = 1; j <= metaData.getColumnCount(); j++)
                    {
                        data.put(metaData.getColumnName(j).toLowerCase(),rs.getObject(metaData.getColumnName(j)));
                    }

                    datas.add(data);
                }

            }           
        }
        else{
            if(rs!=null){
//              定位到起始位置
                /*for (int i = 0; i < startRow&&rs.next(); i++){}
                for (int i = startRow; i < endRow&&rs.next(); i++)
                {
                    Map data = new HashMap();
                    for (int j = 1; j <= metaData.getColumnCount(); j++)
                    {

                        data.put(metaData.getColumnName(j).toLowerCase(),rs.getObject(metaData.getColumnName(j)));
                    }

                    datas.add(data);
                }*/
            	
            	//start修改
            	 if(rs!=null){
//                   定位到起始位置
            		 int i=0;
                     while (rs.next())
                     {
                         Map data = new HashMap();
                         for (int j = 1; j <= metaData.getColumnCount(); j++)
                         {
                             data.put(metaData.getColumnName(j).toLowerCase(),rs.getObject(metaData.getColumnName(j)));
                         }

                         datas.add(data);
                     }
                 }
            	 //end修改
            }            
        }
        return datas;
    }
    
    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
    }
    
    public void setEndRow(int endRow)
    {
        this.endRow = endRow;
    }
    
    public int getStartRow()
    {
        return this.startRow;
    }
    
    public int getEndRow()
    {
        return this.endRow;
    }
    
    private ResultSetDAO(){}

    /**
     * @return Returns the selectAll.
     */
    public boolean isSelectAll() {
        return selectAll;
    }
    /**
     * @param selectAll The selectAll to set.
     */
    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }
}
