package com.neux.spring.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionMemoryCache {

    private static final String expiredName = "_expired";

    @Autowired
    private HttpSession httpSession;

    public void addCache(String key , String value , Integer expiredSec) {

        httpSession.setAttribute(key,value);

        System.out.println("expiredSec = " + expiredSec);

        if(expiredSec != null) httpSession.setAttribute(key + expiredName,System.currentTimeMillis() + (expiredSec * 1000));
    }

    public String poll(String key) {
        String ret = get(key);

        if(StringUtils.isNotEmpty(ret)) removeCache(key);

        return ret;
    }

    public void removeCache(String key) {
        httpSession.removeAttribute(key);
        httpSession.removeAttribute(key + expiredName);
    }

    public String get(String key) {

        if(httpSession.getAttribute(key) == null) return null;
        if(httpSession.getAttribute(key + expiredName) == null) return String.valueOf(httpSession.getAttribute(key));
        else {
            Long expiredTime = Long.parseLong(String.valueOf(httpSession.getAttribute(key + expiredName)));

            System.out.println("expiredTime = " + expiredTime);
            System.out.println("currentTimeMillis = " + System.currentTimeMillis());

            if(System.currentTimeMillis() > expiredTime) return null;
            else return String.valueOf(httpSession.getAttribute(key));
        }

    }

    public static void main(String[] args) throws Exception{
        SessionMemoryCache sessionMemoryCache = new SessionMemoryCache();

        String key = "test";
        sessionMemoryCache.addCache(key,"123",10);

        System.out.println(sessionMemoryCache.get(key));

        for(int i=0;i<15;i++) {
            Thread.sleep(1000);
            System.out.println(i+"="+ sessionMemoryCache.get(key));
        }
    }
}
