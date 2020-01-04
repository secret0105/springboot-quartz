package com.zhoujin.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FirstTask {


    public void task1(){
        System.out.println("第一个任务"+new Date());
    }
}
