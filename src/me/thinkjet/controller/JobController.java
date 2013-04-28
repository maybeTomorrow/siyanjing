package me.thinkjet.controller;

import me.thinkjet.auth.AuthManager;
import me.thinkjet.interceptor.JobInterceptor;
import me.thinkjet.interceptor.JobRecordInterceptor;
import me.thinkjet.model.Job;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/job", viewPath = "job")
@Before(JobInterceptor.class)
public class JobController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("job-index")
	public void index() {
		setAttr("joblist",
				Job.dao.findByCache("job-index", "job",
						SqlKit.sql("job.findListForJobIndexByViews")));
	}

	// 返回用户发布的所有招聘
	public void findByUser() {
		setAttr("joblist",
				Job.dao.find(SqlKit.sql("job.findByUser"), AuthManager
						.getSession(this).getUser().getLong("id")));
		render("job-user.html");
	}

	// 发布招聘
	public void add() {
		render("add.html");
	}

	// 提交发布招聘
	public void create() {
		Job job = getModel(Job.class);
		job.set("author", AuthManager.getSession(this).getUser().getLong("id"));
		job.save();
		render("index.html");
	}

	// 提交修改
	public void update() {
		Job job = getModel(Job.class);
		job.update();
	}

	// 显示单条记录
	@Before(JobRecordInterceptor.class)
	public void show() {
		setAttr("job",
				Job.dao.findFirst(SqlKit.sql("job.findOneById"), getPara("id")));
	}

	// 修改
	public void edit() {
		setAttr("job", Job.dao.findById(getPara("id")));
	}

	// 搜索岗位
	public void search() {
		StringBuffer sql = new StringBuffer(
				"select J.*,R.views,R.comments from job J left join job_record R on J.id=R.id where 1=1");
		if (this.getPara("name") != null && !this.getPara("name").equals(""))
			sql.append(" and J.name like '%" + this.getPara("name") + "%'");
		if (this.getPara("type") != null && !this.getPara("type").equals(""))
			sql.append(" and J.type=" + this.getPara("type"));
		if (this.getPara("salary") != null
				&& !this.getPara("salary").equals(""))
			sql.append(" and J.salary=" + this.getPara("salary"));
		sql.append(" order by J.id asc limit 0,10");
		setAttr("joblist", Job.dao.find(sql.toString()));
		render("index.html");
	}
}
