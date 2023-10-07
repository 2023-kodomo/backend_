package com.example.kodomoproject.global.facade;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateFacade {

    public Date getNowDate() {
        return Date.from(LocalDateTime.now()
                .atZone(TimeZone.getDefault().toZoneId())
                .toInstant());
    }
}
