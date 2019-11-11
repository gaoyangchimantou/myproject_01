package com.bfsu.myproject_01.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0 * * * * 0-6")
    private void scheduled(){

        System.out.println("定时任务。。。。");

    }
}
