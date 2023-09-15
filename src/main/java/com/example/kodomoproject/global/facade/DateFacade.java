package com.example.kodomoproject.global.facade;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateFacade {

    public Date getNowDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }
}