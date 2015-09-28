package test.gotop.demo.system.dao;

import java.sql.SQLException;
import java.util.List;
import test.gotop.demo.system.model.Tuser;
import test.gotop.demo.system.model.TuserExample;

public interface TuserDAO {
    /**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	void insert(Tuser record);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	List selectByExample(TuserExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	int deleteByExample(TuserExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	int countByExample(TuserExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	int updateByExampleSelective(Tuser record, TuserExample example);

	/**
	 * This method was generated by Abator for iBATIS. This method corresponds to the database table TUSER
	 * @abatorgenerated  Sun Oct 30 23:13:37 CST 2011
	 */
	int updateByExample(Tuser record, TuserExample example);

	int testFun();
}