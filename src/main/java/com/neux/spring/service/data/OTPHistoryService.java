package com.neux.spring.service.data;

import com.neux.spring.jpa.model.OTPHistory;
import com.neux.spring.jpa.repository.OTPHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OTPHistoryService {

    @Autowired
    private OTPHistoryRepository otpHistoryRepository;

    @Async
    public void addHistory(String mail,String otpNumber,String mobile) {
        OTPHistory otpHistory = new OTPHistory();
        otpHistory.setEmail(mail);
        otpHistory.setTel(mobile);
        otpHistory.setOtpNumber(Integer.parseInt(otpNumber));
        otpHistory.setCreateTime(new Date());

        otpHistoryRepository.save(otpHistory);
    }
}
