package me.thinkjet.controller.admin;

import me.thinkjet.model.Activity;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/admin/activity", viewPath = "admin/activity")
public class ActivityController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("activity")
	public void index() {
		int page = this.getParaToInt("page", 1);
		setAttr("activitylist", Activity.dao.paginateByCache("activity",
				"activity_" + page, page, 4, "select *",
				"from activity order by id desc"));
	}

	public void add() {
		render("add.html");
	}

	public void create() {
		Activity a = getModel(Activity.class);
		a.save();
		this.redirect("index");
	}

	public void update() {
		Activity a = getModel(Activity.class);
		a.update();
		this.setAttr("a",a);
		this.render("show.html");
	}

	public void show() {
		setAttr("a", Activity.dao.findById(getPara("id")));
	}

	public void edit() {
		setAttr("a", Activity.dao.findById(getPara("id")));
	}

	public void delete() {

	}

}
