package com.common.threads;

import com.common.user.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUser {
    private ThreadLocal local=new ThreadLocal();

    public void setUser(UserInfo user){
        local.set(user);
    }

    public UserInfo getUser(){
        return (UserInfo) local.get();
    }

    public void clear(){
        local.remove();
    }
}