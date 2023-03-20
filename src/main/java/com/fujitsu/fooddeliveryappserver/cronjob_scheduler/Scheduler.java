package com.fujitsu.fooddeliveryappserver.cronjob_scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 * @Scheduled is used to trigger the scheduler for
 * a specific time period.
 * The following code executes the task 15 minutes every
 * hour, after a full hour.
 * */

@Component
public class Scheduler {
    @Scheduled(cron = "15 * * * *")
    public void cronJobSch() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = simpleDateFormat.format(now);
        System.out.println("Java cron job expression: " + strDate);
    }
}
