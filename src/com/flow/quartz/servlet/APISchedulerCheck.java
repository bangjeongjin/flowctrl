package com.flow.quartz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Servlet implementation class APISchedulerCkeck
 */
@WebServlet("/APISchedulerCheck")
public class APISchedulerCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchedulerFactory schedulerFactory;
	private Scheduler scheduler;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APISchedulerCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			schedulerFactory = new StdSchedulerFactory();
			scheduler = schedulerFactory.getScheduler();
    		
    		if(scheduler.checkExists(new JobKey("job1","group1"))) {
    			response.getWriter().write("Running");
    		}else {
    			response.getWriter().write("Stopped");
			}
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
