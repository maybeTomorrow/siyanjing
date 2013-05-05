package me.thinkjet.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import me.thinkjet.auth.AuthManager;
import me.thinkjet.interceptor.JobInterceptor;
import me.thinkjet.interceptor.JobRecordInterceptor;
import me.thinkjet.model.Comment;
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
		List<Job> list;
		int page = this.getParaToInt(1, 1);
		list=Job.dao.findByCache("job-index", "job",
				SqlKit.sql("job.findListForJobIndexByViews"));
		this.setAttr("page", page);
		this.setAttr("job_list", Comment.dao.paginateByCache("job-index",
				"job" + "_" + page, page, 20,"select J.*,R.views,R.comments","from job J left join job_record R on J.id=R.id order by R.views desc" ));
		setAttr("joblist",list);
		setAttr("count",list.size());
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
		job.set("author", 5/*AuthManager.getSession(this).getUser().getLong("id")*/);
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
	public void search() throws UnsupportedEncodingException {
		List<Job> list;
		StringBuffer sql = new StringBuffer(
				"select J.*,R.views,R.comments from job J left join job_record R on J.id=R.id where 1=1");
		if (this.getPara("name") != null && !this.getPara("name").equals(""))
			sql.append(" and J.name like '%" + this.getPara("name") + "%'");
		if (this.getPara("type") != null && !this.getPara("type").equals(""))
			sql.append(" and J.type=" + this.getPara("type"));
		if (this.getPara("salary") != null
				&& !this.getPara("salary").equals(""))
			sql.append(" and J.salary=" + this.getPara("salary"));
		if (this.getPara("prv") != null
				&& !this.getPara("prv").equals(""))
			sql.append(" and J.prv ='" +this.getPara("prv")+"'");
		if (this.getPara("city") != null
				&& !this.getPara("city").equals(""))
			sql.append(" and J.city ='" +this.getPara("city")+"'");
		if (this.getPara("company") != null
				&& !this.getPara("company").equals(""))
			sql.append(" and J.company like '%" +this.getPara("company")+"%'");
		sql.append(" order by J.id asc limit 0,10");
		list=Job.dao.find(sql.toString());
		setAttr("joblist", list);
		setAttr("count",list.size());
		render("index.html");
	}
}
