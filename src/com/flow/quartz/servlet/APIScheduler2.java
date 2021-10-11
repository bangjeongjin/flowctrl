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

import com.flow.quartz.job.BillInfoJobTwo;

/**
 * Servlet implementation class APIScheduler2
 */
@WebServlet("/APIScheduler2")
public class APIScheduler2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//30초
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
    		
    		JobDetail job1 = newJob(BillInfoJobTwo.class)
    				.withIdentity("job3", "group3")
    				.build();
    		
    		CronTrigger trigger1 = newTrigger() 
    				.withIdentity("trigger4", "group4") 
    				.withSchedule(cronSchedule("*/30 * * * * ?")) //30초
    				.build();

    		scheduler.scheduleJob(job1, trigger1); 
    		scheduler.start(); 
    	} catch (SchedulerException e) { 
    		e.printStackTrace(); 
    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
