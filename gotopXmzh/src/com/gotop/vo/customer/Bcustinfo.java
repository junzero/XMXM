package com.gotop.vo.customer;
/****************************************************
 * 描    述： 客户信息实体Bcustinfo entity
 * 前置条件:
 * 后置条件:无
 * 实施资源:
 * 调用者  :
 * 被调用者:
 * 重载说明:
 * Copyright: Copyright (c) 2011
 * Company:  Gotop
 * @author   liuyh  
 * @version   1.0    2011/04/18
 * @see
	HISTORY
		*  2011/04/18 VO 创建文件
*  
**************************************************/
import java.util.Date;

public class Bcustinfo implements java.io.Serializable {

	// Fields

	private Integer id;    
	private String custname;
	private String belongsid;
	private String custnamech;
	private String custno;
	private String custlx;
	private String parentid;
	private String realcustname;
	private String credit;
	private String valuation;
	private String ishot;
	private String hottype;
	private String custsource;
	private String custindustry;
	private String custproperty;
	private String loyalty;
	private String country;
	private String province;
	private String city;
	private String area;
	private String oaddress;
	private String otel;
	private String faxno;
	private String opostcode;
	private String oemail;
	private String website;
	private String ispost;
	private String qyfr;
	private Double zczj;
	private String yyzk;
	private String yyjd;
	private String zjqk;
	private String khjj;
	private String ygrs;
	private Double nyye;
	private String gsxz;
	private String datastate;
	private Integer creater;
	private Date creatdate;
	private String orgid;
	private String qygm;

	// Constructors

	/** default constructor */
	public Bcustinfo() {
	}

	/** minimal constructor */
	public Bcustinfo(Integer id, String custname, String custlx) {
		this.id = id;
		this.custname = custname;
		this.custlx = custlx;
	}

	/** full constructor */
	public Bcustinfo(Integer id, String custname, String belongsid,
			String gxfw, String custnamech, String custlx, String parentid,
			String realcustname, String credit,
			String valuation, String ishot, String hottype, String custsource,
			String custindustry, String custproperty, 
			String loyalty, String country, String province, String city,
			String area, String oaddress, String otel, String faxno,
			String opostcode, String ispost, String oemail,String qyfr, Double zczj,
			String yyzk, String ygrs,String yyjd,String khjj, Double nyye, String gsxz,String zjqk,
			String datastate, Integer creater, Date creatdate,String orgid,String custno,String website) {
		this.id = id;
		this.custname = custname;
		this.belongsid = belongsid;
		this.custno = custno;
		this.custnamech = custnamech;
		this.custlx = custlx;
		this.parentid = parentid;
		this.realcustname = realcustname;
		this.credit = credit;
		this.valuation = valuation;
		this.ishot = ishot;
		this.hottype = hottype;
		this.custsource = custsource;
		this.custindustry = custindustry;
		this.custproperty = custproperty;
		this.loyalty = loyalty;
		this.country = country;
		this.province = province;
		this.city = city;
		this.area = area;
		this.oaddress = oaddress;
		this.otel = otel;
		this.oemail = oemail;
		this.website = website;
		this.faxno = faxno;
		this.opostcode = opostcode;
		this.ispost = ispost;
		this.qyfr = qyfr;
		this.zczj = zczj;
		this.yyzk = yyzk;
		this.khjj = khjj;
		this.yyjd = yyjd;
		this.ygrs = ygrs;
		this.zjqk = zjqk;
		this.nyye = nyye;
		this.gsxz = gsxz;
		this.datastate = datastate;
		this.creater = creater;
		this.creatdate = creatdate;
		this.orgid= orgid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getBelongsid() {
		return this.belongsid;
	}

	public void setBelongsid(String belongsid) {
		this.belongsid = belongsid;
	}



	public String getCustnamech() {
		return this.custnamech;
	}

	public void setCustnamech(String custnamech) {
		this.custnamech = custnamech;
	}

	public String getCustlx() {
		return this.custlx;
	}

	public void setCustlx(String custlx) {
		this.custlx = custlx;
	}

	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getRealcustname() {
		return this.realcustname;
	}

	public void setRealcustname(String realcustname) {
		this.realcustname = realcustname;
	}

	
	public String getCredit() {
		return this.credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getValuation() {
		return this.valuation;
	}

	public void setValuation(String valuation) {
		this.valuation = valuation;
	}

	public String getIshot() {
		return this.ishot;
	}

	public void setIshot(String ishot) {
		this.ishot = ishot;
	}

	public String getHottype() {
		return this.hottype;
	}

	public void setHottype(String hottype) {
		this.hottype = hottype;
	}

	public String getCustsource() {
		return this.custsource;
	}

	public void setCustsource(String custsource) {
		this.custsource = custsource;
	}

	public String getCustindustry() {
		return this.custindustry;
	}

	public void setCustindustry(String custindustry) {
		this.custindustry = custindustry;
	}

	public String getCustproperty() {
		return this.custproperty;
	}

	public void setCustproperty(String custproperty) {
		this.custproperty = custproperty;
	}
	public String getLoyalty() {
		return this.loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOaddress() {
		return this.oaddress;
	}

	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}

	public String getOtel() {
		return this.otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getOpostcode() {
		return this.opostcode;
	}

	public void setOpostcode(String opostcode) {
		this.opostcode = opostcode;
	}

	public String getIspost() {
		return this.ispost;
	}

	public void setIspost(String ispost) {
		this.ispost = ispost;
	}

	public String getQyfr() {
		return this.qyfr;
	}

	public void setQyfr(String qyfr) {
		this.qyfr = qyfr;
	}

	public Double getZczj() {
		return this.zczj;
	}

	public void setZczj(Double zczj) {
		this.zczj = zczj;
	}

	public String getYyzk() {
		return this.yyzk;
	}

	public void setYyzk(String yyzk) {
		this.yyzk = yyzk;
	}

	public String getYgrs() {
		return this.ygrs;
	}

	public void setYgrs(String ygrs) {
		this.ygrs = ygrs;
	}

	public Double getNyye() {
		return this.nyye;
	}

	public void setNyye(Double nyye) {
		this.nyye = nyye;
	}

	public String getGsxz() {
		return this.gsxz;
	}

	public void setGsxz(String gsxz) {
		this.gsxz = gsxz;
	}

	public String getDatastate() {
		return this.datastate;
	}

	public void setDatastate(String datastate) {
		this.datastate = datastate;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Date getCreatdate() {
		return this.creatdate;
	}

	public void setCreatdate(Date creatdate) {
		this.creatdate = creatdate;
	}

	

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getQygm() {
		return qygm;
	}

	public void setQygm(String qygm) {
		this.qygm = qygm;
	}

	public String getYyjd() {
		return yyjd;
	}

	public void setYyjd(String yyjd) {
		this.yyjd = yyjd;
	}

	public String getKhjj() {
		return khjj;
	}

	public void setKhjj(String khjj) {
		this.khjj = khjj;
	}

	public String getOemail() {
		return oemail;
	}

	public void setOemail(String oemail) {
		this.oemail = oemail;
	}
    public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getZjqk() {
		return zjqk;
	}

	public void setZjqk(String zjqk) {
		this.zjqk = zjqk;
	}


}