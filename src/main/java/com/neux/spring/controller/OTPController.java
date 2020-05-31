package com.neux.spring.controller;

import com.neux.spring.service.OTPService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OTP")
public class OTPController {

    private Logger logger = LoggerFactory.getLogger(OTPController.class);

    @Autowired
    private OTPService otpService;

    @RequestMapping("/send/email")
    public String sendOTP() {

        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        try{
            success = otpService.sendOTPByMail();
        }catch(Exception e) {
            logger.error("OTPController sendOTP fail!!",e);
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }

    @RequestMapping("/send/mobile")
    public String sendOTPByMobile() {

        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        try{
            success = otpService.sendOTPBySMS();
        }catch(Exception e) {
            logger.error("OTPController sendOTPByMobile fail!!",e);
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }

    @RequestMapping("/validation")
    public String validationOTP(@RequestParam("otpNumber") String otpNumber) {
        JSONObject jsonObject = new JSONObject();

        boolean success = false;
        try{
            success = otpService.validationOTP(otpNumber);
        }catch(Exception e) {
            logger.error("OTPController validationOTP fail!!",e);
        }

        jsonObject.put("success",success);

        return jsonObject.toString();
    }
}
