package com.flow.quartz.servlet;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.flow.quartz.job.BillInfoJobTwo;

/**
 * Servlet implementation class APISchedulerStop
 */
@WebServlet("/APISchedulerStop2")
public class APISchedulerStop2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APISchedulerStop2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//30√ 
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
    		
    		scheduler.deleteJob(new JobKey("job3","group3"));
    		System.out.println("stop 2");
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
