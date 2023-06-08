package antProject;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.internal.util.ConfigHelper;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
public class MainClass { 
	
	public static final Logger logger = Logger.getLogger(MainClass.class);
	 
	public static void main(String[] args) throws IOException, ClassNotFoundException, SchedulerException {  
	     
		BasicConfigurator.configure();
		 
		JobDetail job = JobBuilder.newJob(LogicPart.class).build();
		logger.info("Creating the job" +job);
		//Trigger ti = TriggerBuilder.newTrigger().withIdentity("SimpleTrigger").startNow().build();
		
		Trigger t2 = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0 * * ? * *")).build();
		logger.info("Trigger Information"+t2);
		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		logger.info("Scheduling the job---->" +s);
		s.start();
		s.scheduleJob(job,t2);
	 
	}

}

