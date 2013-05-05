package me.thinkjet.interceptor;

import me.thinkjet.model.Job;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.ext.plugin.sqlinxml.SqlKit;

public class JobInterceptor implements Interceptor{
	public void intercept(ActionInvocation ai) {
	ai.getController().setAttr("hotjobtype",Job.dao.findByCache("hotjob","hotjob", SqlKit.sql("job.findHotJobTypeForJobIndexByCount")));
	ai.getController().setAttr("hotcity",Job.dao.findByCache("hotjob","hotcity", SqlKit.sql("job.findHotCityForJobIndexByCount")));
	String target="job_15";
	System.out.println();
	ai.invoke();
	}
}
