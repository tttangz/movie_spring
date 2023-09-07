package com.auth.threads;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ThreadLocalToken {
    private ThreadLocal local=new ThreadLocal();

    public void setToken(HashMap<String, String> tokenMap){
        local.set(tokenMap);
    }

    public HashMap<String, String> getTokenMap(){
        return (HashMap<String, String>) local.get();
    }

    public void clear(){
        local.remove();
    }
}

