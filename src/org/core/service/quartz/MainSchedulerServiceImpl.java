package org.core.service.quartz;

import javax.annotation.PostConstruct;
import org.core.util.SqlUtil;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)

@Service("mainSchedulerService")
public class MainSchedulerServiceImpl {
	
	/***
	 * 主任务调度器
	 * @throws SchedulerException
	 */
	@PostConstruct
	public void schedulerJob() throws SchedulerException {
		/**
		 * 打开  是否  启动任务调度开关  
		 */
		if ("1".equals(SqlUtil.getSys().getProperty("isQuartz"))) {
			// 1：创建   导入车辆  任务
			JobDetail vehicleJob = JobBuilder.newJob(VehicleJob.class).withIdentity("VehicleJob", "VehicleJobGroup").build();
			// 2： 创建触发器 每5分钟执行一次
			Trigger vehicleTrigger = TriggerBuilder.newTrigger()
					 .withIdentity("VehicleTrigger", "VehicleTriggerGroup")
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1).repeatForever())
					.build();
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 将任务及其触发器放入调度器
			scheduler.scheduleJob(vehicleJob, vehicleTrigger);
			// 调度器开始调度任务
			scheduler.start();
		}
	}
}
