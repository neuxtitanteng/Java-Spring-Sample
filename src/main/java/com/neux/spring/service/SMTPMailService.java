package com.neux.spring.service;

import com.neux.spring.service.bean.MailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SMTPMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(MailInfo mailInfo) throws Exception {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mailInfo.toRecipientArray());
        msg.setSubject(mailInfo.getSubject());
        msg.setText(mailInfo.getBody());

        javaMailSender.send(msg);
    }
}
