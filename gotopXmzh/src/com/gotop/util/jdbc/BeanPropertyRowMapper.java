package com.gotop.util.jdbc;

import org.springframework.jdbc.core.RowMapper;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.util.Assert;

public class BeanPropertyRowMapper implements RowMapper {

	protected final Log logger = LogFactory.getLog(getClass());

	private Class mappedClass;

	private boolean checkFullyPopulated = false;

	private Map mappedFields;

	private Set mappedProperties;


	public BeanPropertyRowMapper() {
	}

	public BeanPropertyRowMapper(Class mappedClass) {
		initialize(mappedClass);
	}

	public BeanPropertyRowMapper(Class mappedClass, boolean checkFullyPopulated) {
		initialize(mappedClass);
		this.checkFullyPopulated = checkFullyPopulated;
	}


	public void setMappedClass(Class mappedClass) {
		if (this.mappedClass == null) {
			initialize(mappedClass);
		}
		else {
			if (!this.mappedClass.equals(mappedClass)) {
				throw new InvalidDataAccessApiUsageException("The mapped class can not be reassigned to map to " +
						mappedClass + " since it is already providing mapping for " + this.mappedClass);
			}
		}
	}

	protected void initialize(Class mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap();
		this.mappedProperties = new HashSet();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);//取得class中所有属性数组
		for (int i = 0; i < pds.length; i++) {
			PropertyDescriptor pd = pds[i];
			if (pd.getWriteMethod() != null) {
				this.mappedFields.put(pd.getName().toLowerCase(), pd);
				String underscoredName = underscoreName(pd.getName());//对字符进行过滤
				if (!pd.getName().toLowerCase().equals(underscoredName)) {
					this.mappedFields.put(underscoredName, pd);//如果有大写字符存在在MAP中放原来值
				}
				this.mappedProperties.add(pd.getName());
			}
		}
	}

	private String underscoreName(String name) {
		StringBuffer result = new StringBuffer();
		if (name != null && name.length() > 0) {
			result.append(name.substring(0, 1).toLowerCase());
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())) {
					result.append("_");
					result.append(s.toLowerCase());
				}
				else {
					result.append(s);
				}
			}
		}
		return result.toString();
	}

	public final Class getMappedClass() {
		return this.mappedClass;
	}

	public void setCheckFullyPopulated(boolean checkFullyPopulated) {
		this.checkFullyPopulated = checkFullyPopulated;
	}

	public boolean isCheckFullyPopulated() {
		return this.checkFullyPopulated;
	}


	public Object mapRow(ResultSet rs, int rowNumber) throws SQLException {
		Assert.state(this.mappedClass != null, "Mapped class was not specified");
		Object mappedObject = BeanUtils.instantiateClass(this.mappedClass);
		BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(mappedObject);
		initBeanWrapper(bw);

		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		Set populatedProperties = (isCheckFullyPopulated() ? new HashSet() : null);

		for (int index = 1; index <= columnCount; index++) {
			String column = JdbcUtils.lookupColumnName(rsmd, index).toLowerCase();
			PropertyDescriptor pd = (PropertyDescriptor) this.mappedFields.get(column);
			if (pd != null) {
				try {
					Object value = getColumnValue(rs, index, pd);
					if (logger.isDebugEnabled() && rowNumber == 0) {
						logger.debug("Mapping column '" + column + "' to property '" +
								pd.getName() + "' of type " + pd.getPropertyType());
					}
					bw.setPropertyValue(pd.getName(), value);
					if (populatedProperties != null) {
						populatedProperties.add(pd.getName());
					}
				}
				catch (NotWritablePropertyException ex) {
					throw new DataRetrievalFailureException(
							"Unable to map column " + column + " to property " + pd.getName(), ex);
				}
			}
		}

		if (populatedProperties != null && !populatedProperties.equals(this.mappedProperties)) {
			throw new InvalidDataAccessApiUsageException("Given ResultSet does not contain all fields " +
					"necessary to populate object of class [" + this.mappedClass + "]: " + this.mappedProperties);
		}

		return mappedObject;
	}

	protected void initBeanWrapper(BeanWrapper bw) {
	}

	protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
	}

}
