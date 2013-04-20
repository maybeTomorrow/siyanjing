package me.thinkjet.controller;

import me.thinkjet.interceptor.ActivityInterceptor;
import me.thinkjet.interceptor.JobRecordInterceptor;
import me.thinkjet.model.Job;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/activity", viewPath = "activity")
@Before(ActivityInterceptor.class)
public class ActivityController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("activity")
	public void index() {
		setAttr("activity_list", Job.dao.paginateByCache("activity",
				"activity-index", this.getParaToInt("page", 1), 12, "select *",
				"from activity"));
	}

	// 发布招聘
	public void add() {
		render("add.html");
	}

	// 提交发布招聘
	public void create() {
		Job job = getModel(Job.class);
		job.set("author", /* this.getSession().getAttribute(SESSION_ID) */1);
		job.save();
		render("index.html");
	}

	// 提交修改
	public void update() {
		Job job = getModel(Job.class);
		job.update();
	}

	// 显示单条记录
	@Before({ JobRecordInterceptor.class, CacheInterceptor.class })
	public void show() {
		setAttr("job", Job.dao.findById(getPara("id")));
	}

	// 修改
	public void edit() {
		setAttr("job", Job.dao.findById(getPara("id")));
	}

	// 搜索岗位
	public void search() {
		StringBuffer sql = new StringBuffer(
				"select J.*,R.views,R.comments from job J left join jobrecord R on J.id=R.id where 1=1");
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
