package com.zhoujin.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SecondTask {

    public void task2(){
        System.out.println("第二个任务"+new Date());
    }
}
