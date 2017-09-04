package com.asd.common.shiro.sessionid;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;


public class JavaUuidSessionIdGenerator implements SessionIdGenerator{
	/** 
     * Ignores the method argument and simply returns 
     * {@code UUID}.{@link UUID#randomUUID() randomUUID()}.{@code toString()}.
     * 
     * @param session the {@link Session} instance to which the ID will be applied. 
     * @return the String value of the JDK's next {@link UUID#randomUUID() randomUUID()}. 
     */  
    public Serializable generateId(Session session) {
        return UUID.randomUUID().toString();  
    }  
}
