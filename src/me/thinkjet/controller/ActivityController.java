package me.thinkjet.controller;

import me.thinkjet.interceptor.ActivityInterceptor;
import me.thinkjet.model.Activity;

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
		setAttr("activitylist", Activity.dao.paginateByCache("activity",
				"activity-index" + this.getParaToInt("page", 1),
				this.getParaToInt("page", 1), 12, "select *", "from activity"));
	}

	public void add() {
		render("add.html");
	}

	public void create() {
		Activity a = getModel(Activity.class);
		a.set("author", /* this.getSession().getAttribute(SESSION_ID) */1);
		a.save();
		render("index.html");
	}

	public void update() {
		Activity a = getModel(Activity.class);
		a.update();
		render("show.html");
	}

	public void show() {
		setAttr("a", Activity.dao.findById(getPara("id")));
	}

	public void edit() {
		setAttr("a", Activity.dao.findById(getPara("id")));
	}

}
