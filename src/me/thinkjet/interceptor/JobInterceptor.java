package me.thinkjet.interceptor;

import me.thinkjet.model.Job;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.ext.plugin.sqlinxml.SqlKit;

public class JobInterceptor implements Interceptor{
	public void intercept(ActionInvocation ai) {
	ai.getController().setAttr("hotjobtype",Job.dao.findByCache("hotjob","hotjob", SqlKit.sql("job.findHotJobTypeForJobIndexByCount")));
	ai.invoke();
	}
}
