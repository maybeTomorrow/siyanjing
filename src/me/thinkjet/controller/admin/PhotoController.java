package me.thinkjet.controller.admin;

import me.thinkjet.model.Activity;
import me.thinkjet.model.ActivityPhoto;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/admin/photo", viewPath = "admin/")
public class PhotoController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("photo")
	public void index() {
		setAttr("plist", Activity.dao.paginateByCache("photo",
				"photo-index" + this.getParaToInt("page", 1),
				this.getParaToInt("page", 1), 12, "select *", "from activity_photo order by desc"));
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

	public void edit() {
		setAttr("a", Activity.dao.findById(getPara("id")));
	}
	
	public void delete(){
		ActivityPhoto.dao.deleteById(this.getAttr("id"));
		this.render("index/html");
	}

}
