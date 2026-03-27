package org.example.config;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            LiquibaseRunner.runLiquibase();

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail jobDetail= JobBuilder.newJob(QuartzSheduler.class)
                    .withIdentity("intrestJob").build();

            Trigger trigger= TriggerBuilder.newTrigger()
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 0 1 * ?")
                    ).build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}