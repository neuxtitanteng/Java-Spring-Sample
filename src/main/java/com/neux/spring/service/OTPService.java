package com.neux.spring.service;

import com.neux.spring.jpa.model.OTPHistory;
import com.neux.spring.jpa.repository.OTPHistoryRepository;
import com.neux.spring.service.bean.MailInfo;
import com.neux.spring.service.data.OTPHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

@Service
public class OTPService {

    private Logger logger = LoggerFactory.getLogger(OTPService.class);

    private final static String otpKey = "OTP_KEY";

    @Value("${otp_expired_sec}")
    private int otpExpiredSec;

    @Value("${every8d.sms.mobile}")
    private String smsMobile;

    @Autowired
    private SMTPMailService smtpMailService;

    @Autowired
    private SessionMemoryCache sessionMemoryCache;

    @Autowired
    private OTPHistoryService otpHistoryService;

    @Autowired
    private SMSHttpService smsHttpService;

    public boolean sendOTPByMail() {

        boolean isSuccess = false;

        try {
            MailInfo mailInfo = new MailInfo();

            String otpNumber = generationOTP(6);

            sessionMemoryCache.addCache(otpKey,otpNumber,otpExpiredSec);

            mailInfo.setSubject("[OTP驗證通知信]");
            mailInfo.setBody("您的驗證碼為："+otpNumber+"，請於"+otpExpiredSec+"秒內輸入完畢");
            mailInfo.addRecipient("titanteng@neux.com.tw");
            mailInfo.addRecipient("bryantwu@neux.com.tw");

            smtpMailService.sendEmail(mailInfo);

            otpHistoryService.addHistory(mailInfo.toRecipient(),otpNumber,"");

            isSuccess = true;
        }catch(Exception e) {
            logger.error("sendOTP fail!!",e);
        }

        return isSuccess;
    }

    public boolean sendOTPBySMS() {

        boolean isSuccess = false;

        try {


            String otpNumber = generationOTP(6);

            sessionMemoryCache.addCache(otpKey,otpNumber,otpExpiredSec);

            smsHttpService.sendSMS("[OTP驗證通知信]","您的驗證碼為："+otpNumber+"，請於"+otpExpiredSec+"秒內輸入完畢",smsMobile);

            otpHistoryService.addHistory("",otpNumber,smsMobile);

            isSuccess = true;
        }catch(Exception e) {
            logger.error("sendOTP fail!!",e);
        }

        return isSuccess;
    }

    public boolean validationOTP(String otpNumber) {
        String originalOtpNumber = sessionMemoryCache.get(otpKey);
        if(StringUtils.isEmpty(originalOtpNumber)) return false;
        else {

            System.out.println("otpNumber = " + otpNumber + ",originalOtpNumber = " + originalOtpNumber);

            boolean isMatch = originalOtpNumber.equalsIgnoreCase(otpNumber);
            sessionMemoryCache.removeCache(otpKey);
            return isMatch;
        }
    }


    private String generationOTP(int len)
    {

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();


        char[] otp = new char[len];

        for (int i = 0; i < len; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }

        return new String(otp);

    }
}
