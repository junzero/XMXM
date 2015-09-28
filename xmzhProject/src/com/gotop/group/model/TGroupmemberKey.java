package com.gotop.group.model;

import java.io.Serializable;

public class TGroupmemberKey implements Serializable {
    /**
     * 群组编号 .
     * @abatorgenerated
     */
    private Long groupid;

    /**
     * 成员编号（empid） .
     * @abatorgenerated
     */
    private Long memberid;

    /**
     * 群组编号 .
     * @abatorgenerated
     */
    public Long getGroupid() {
        return groupid;
    }

    /**
     * 群组编号 .
     * @abatorgenerated
     */
    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    /**
     * 成员编号（empid） .
     * @abatorgenerated
     */
    public Long getMemberid() {
        return memberid;
    }

    /**
     * 成员编号（empid） .
     * @abatorgenerated
     */
    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }
}