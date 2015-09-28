/*******************************************************************************
 * Copyright (c) 2012 Gotop.net.cn Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on 2012-6-19
 *******************************************************************************/


package com.gotop.vo.system;

import java.io.Serializable;

import com.eos.data.datacontext.IOpenable;

public interface IUserObject extends IOpenable, Serializable, Cloneable
{

    public abstract String getUniqueId();

    public abstract String getSessionId();

    public abstract String getUserId();

    public abstract String getUserMail();

    public abstract String getUserName();

    public abstract String getUserOrgId();

    public abstract String getUserOrgName();

    public abstract String getUserRealName();

    public abstract String getUserRemoteIP();
    
    public abstract void setUniqueId(String uniqueId);
    
    public abstract void setSessionId(String sessionId);
    
    public abstract void setUserId(String userId);
    
    public abstract void setUserMail(String userMail);
    
    public abstract void setUserName(String userName);
    
    public abstract void setUserOrgId(String userOrgId);
    
    public abstract void setUserOrgName(String userOrgName);
    
    public abstract void setUserRealName(String userRealName);
    
    public abstract void setUserRemoteIP(String userRemoteIP);

    public abstract Object clone()
        throws CloneNotSupportedException;

    public static final String KEY_IN_CONTEXT = "userObject";
}
