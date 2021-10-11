package com.flow.quartz.servlet;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob; 
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.flow.quartz.job.BillInfoJob;


/**
 * Servlet implementation class APIScheduler
 */
@WebServlet("/APIScheduler")
public class APIScheduler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//20초
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
    		
    		JobDetail job = newJob(BillInfoJob.class)
    				.withIdentity("job1", "group1")
    				.build();
    		
    		CronTrigger trigger = newTrigger() 
    				.withIdentity("trigger2", "group2") 
    				.withSchedule(cronSchedule("*/20 * * * * ?")) //20초
    				.build();

    		scheduler.scheduleJob(job, trigger); 
    		scheduler.start();
    	} catch (SchedulerException e) { 
    		e.printStackTrace(); 
    	}
	}
}