package com.neux.spring.service;

import com.neux.spring.interceptor.HeaderRequestInterceptor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Service
public class PushService {

    private Logger logger = LoggerFactory.getLogger(PushService.class);

    private String FIREBASE_SERVER_KEY = "AAAAVAMNDSc:APA91bHK0AwYpyTDF-QrHpqcXsa5ZMG0MR8vfTCreliZJulmPfKa8ezB8AWK0aPWrFsdNh94WVaPmtXo_1l-lBaK_SI4UmjkRevoYGST8ehG-os__IcliL_rJsdythsdZXTdJPI9KDy8";
    private String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    private String token = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void push() {

        JSONObject notificationJSON = new JSONObject();
        notificationJSON.put("title","[安達人壽網路投保通知]");
        notificationJSON.put("body", "您有未完成的投保，建議您可以抽空完成投保唷！");

        JSONObject data = new JSONObject("{\n" +
                "  \"body\": \"您有未完成的投保，建議您可以抽空完成投保唷！\",\n" +
                "  \"title\": \"[安達人壽網路投保通知]\",\n" +
                "  \"key_1\": \"Value for key_1\",\n" +
                "  \"key_2\": \"Value for key_2\"\n" +
                "}");


        JSONObject firebaseBody = new JSONObject();
        firebaseBody.put("priority", "high");
        firebaseBody.put("notification", notificationJSON);
        firebaseBody.put("data", data);

        try{
            firebaseBody.put("to",token);

            logger.info("body = " + firebaseBody.toString());

            HttpEntity<String> request = new HttpEntity<>(firebaseBody.toString());

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

            ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
            interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
            interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));

            restTemplate.setInterceptors(interceptors);

            logger.info("send to firebase");

            String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, request, String.class);

            logger.debug("firebaseResponse = " + firebaseResponse );
        }catch(Exception e) {
            logger.error("Push firebase fail!! firebaseBody = ["+firebaseBody.toString()+"]",e);
        }


    }

}
