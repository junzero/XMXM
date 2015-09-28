package com.gotop.vo.area;

/*********************************
 * <p>Title: 区县DTO</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: 2011</p>
 *
 * <p>Company: GOTOP</p>
 *
 * @author phc
 *
 * @date 2011-6-20
 *
 * @version 1.0
 *
 * HISTORY 2011-6-20
 *
 *********************************/
public class DscountyDTO {
	private String countyid;//区县ID
	private String name;//名称
	private String provinceid;//对应地市ID
	public String getCountyid() {
		return countyid;
	}
	public void setCountyid(String countyid) {
		this.countyid = countyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
}
