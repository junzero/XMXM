package com.gotop.tyjg.datadictionary.model;


public class DictEntry {
    private String dicttypeid;
    private String dictid;
    private String dictname;
    private Long status;
    private Long sortno;
    private Long rank;
    private String parentid;
    private String seqno;
    private String filter1;
    private String filter2;
    
	public String getDicttypeid() {
		return dicttypeid;
	}
	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
	}
	public String getDictid() {
		return dictid;
	}
	public void setDictid(String dictid) {
		this.dictid = dictid;
	}
	public String getDictname() {
		return dictname;
	}
	public void setDictname(String dictname) {
		this.dictname = dictname;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public Long getSortno() {
		return sortno;
	}
	public void setSortno(Long sortno) {
		this.sortno = sortno;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	public String getFilter1() {
		return filter1;
	}
	public void setFilter1(String filter1) {
		this.filter1 = filter1;
	}
	public String getFilter2() {
		return filter2;
	}
	public void setFilter2(String filter2) {
		this.filter2 = filter2;
	}

}
