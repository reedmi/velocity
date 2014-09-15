package com.reedmi.framework.web.scheduler;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * http://www.2cto.com/kf/201311/257405.html
 */
@Component
public class TimeSchedule {

    @Scheduled(cron = "*/5 * * * * *")
    public void showTime() {
        System.out.println("time : " + new Date());
    }

    @Scheduled(fixedDelay = 5000)
    public void showTime2() {
        System.out.println("time2 : " + new Date());
    }
}
