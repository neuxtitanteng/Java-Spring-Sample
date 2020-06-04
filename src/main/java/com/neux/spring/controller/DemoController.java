package com.neux.spring.controller;

import com.neux.spring.service.PushService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private PushService pushService;

    @RequestMapping("/keepToken")
    public String keep(@RequestParam("token") String token) {

        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        try{
            pushService.setToken(token);
            success = true;
        }catch(Exception e) {
            logger.error("DemoController keep fail!!",e);
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }

    @RequestMapping("/push")
    public String push() {
        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        try{
            pushService.push();
            success = true;
        }catch(Exception e) {
            logger.error("DemoController push fail!!",e);
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }

}
