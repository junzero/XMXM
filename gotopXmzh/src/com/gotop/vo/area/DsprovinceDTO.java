package com.gotop.vo.area;

/*********************************
 * <p>Title: 地区DTO</p>
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
public class DsprovinceDTO {
	private String provinceid;//地市ID
	private String name;//名称
	private String countryid;//对应省直辖市ID
	public String getProvinceid() {
		return provinceid;
	}
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryid() {
		return countryid;
	}
	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}
}
