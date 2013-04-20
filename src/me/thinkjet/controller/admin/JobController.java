package me.thinkjet.controller.admin;

import me.thinkjet.model.Job;
import me.thinkjet.model.JobRecord;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
@ControllerBind(controllerKey = "/admin/job", viewPath = "admin/")
public class JobController extends Controller{
	@Before(CacheInterceptor.class)
	@CacheName("job-index")
	public void index() {
		setAttr("job",
				Job.dao.findByCache("job-index", "job",
						SqlKit.sql("job.findListForJobIndexByViews")));
	}
	
public void delete(){
	JobRecord.dao.deleteById(this.getPara("id"));
	Job.dao.deleteById(this.getPara("id"));
	render("index.html");
}
}
