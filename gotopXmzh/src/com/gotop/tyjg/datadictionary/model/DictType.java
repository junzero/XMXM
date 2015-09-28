package com.gotop.tyjg.datadictionary.model;

public class DictType {
    private String dicttypeid;
    private String dicttypename;
    private long rank;
    private String parentid;
    private String seqno;
    private Object operate;
	public String getDicttypeid() {
		return dicttypeid;
	}
	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
	}
	public String getDicttypename() {
		return dicttypename;
	}
	public void setDicttypename(String dicttypename) {
		this.dicttypename = dicttypename;
	}
	public long getRank() {
		return rank;
	}
	public void setRank(long rank) {
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
	public Object getOperate() {
		return operate;
	}
	public void setOperate(Object operate) {
		this.operate = operate;
	}
}
