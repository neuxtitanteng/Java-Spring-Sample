package com.neux.spring.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateService {

    public Date getToday() {
        return new Date();
    }

}
