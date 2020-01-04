package com.zhoujin.quartzconfig;

import com.zhoujin.task.FirstTask;
import com.zhoujin.task.SecondTask;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * quartz 配置
 *
 * 此代码错误
 *
 */
@Configuration
public class QuartzConfig {



    //配置第二个任务
    @Bean
    public MethodInvokingJobDetailFactoryBean secondJobDetail(SecondTask secondTask){
        MethodInvokingJobDetailFactoryBean jobdetail = new MethodInvokingJobDetailFactoryBean();
        jobdetail.setConcurrent(false);

        jobdetail.setTargetObject(secondTask);

        jobdetail.setTargetMethod("task2");

        return jobdetail;
    }

    //配置第二个触发器
    @Bean
    public SimpleTriggerFactoryBean secondTrigger(JobDetail secondJobDetail){
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(secondJobDetail);
        trigger.setStartDelay(0);
        trigger.setRepeatInterval(5000);

        return trigger;
    }

    //创建第一个job
    @Bean
    public MethodInvokingJobDetailFactoryBean firstJobDetail(FirstTask firstTask){
        MethodInvokingJobDetailFactoryBean job = new MethodInvokingJobDetailFactoryBean();

        //是否并发执行
        job.setConcurrent(false);

        //需要执行任务的实体类
        job.setTargetObject(firstTask);

        //具体的任务方法
        job.setTargetMethod("task1");

        return job;

    }



    //创建trigger
    @Bean
    public CronTriggerFactoryBean firstTrigger(JobDetail firstJobDetail){

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();

        factoryBean.setJobDetail(firstJobDetail);

        factoryBean.setCronExpression("0/2 * * * * ?");

        return factoryBean;

    }

    //创建scheduler
    @Bean(name = "scheduler")
    public SchedulerFactoryBean scheduler1(Trigger firstTrigger,Trigger secondTrigger){
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();

        //延时启动,1s后启动
        scheduler.setStartupDelay(1);

        //注册触发器
        scheduler.setTriggers(firstTrigger,secondTrigger);


        return scheduler;
    }
//    @Bean(name = "scheduler2")
//    public SchedulerFactoryBean scheduler2(Trigger firstTrigger){
//        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//
//        //延时启动,1s后启动
//        scheduler.setStartupDelay(1);
//
//        //注册触发器
//        scheduler.setTriggers(firstTrigger);
//
//
//        return scheduler;
//    }
}
