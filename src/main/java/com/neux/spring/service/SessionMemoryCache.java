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

    private String otpNumber = null;
    private Long expiredTime = null;

    public void addCache(String key , String value , Integer expiredSec) {

        System.out.println("expiredSec = " + expiredSec);

        this.otpNumber = value;
        this.expiredTime = System.currentTimeMillis() + (expiredSec * 1000);

    }

    public void removeCache(String key) {
        this.otpNumber = null;
        this.expiredTime = null;
    }

    public String get(String key) {

        System.out.println("expiredTime = " + expiredTime);
        System.out.println("currentTimeMillis = " + System.currentTimeMillis());

        if(otpNumber == null || expiredTime == null) return null;
        if(System.currentTimeMillis() > expiredTime) return null;
        else return otpNumber;

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
