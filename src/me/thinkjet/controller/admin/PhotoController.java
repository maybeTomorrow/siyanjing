package me.thinkjet.controller.admin;

import me.thinkjet.model.Activity;
import me.thinkjet.model.ActivityPhoto;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/admin/activity/photo", viewPath = "admin/activity/photo")
public class PhotoController extends Controller {

	@Before(CacheInterceptor.class)
	@CacheName("photo")
	public void index() {
		int page = this.getParaToInt("page", 1);
		setAttr("plist", Activity.dao.paginateByCache("photo", "photo-" + page,
				page, 20, "select p.*,a.title as title",
				"from activity_photo p left join activity a on"
						+ " p.activity_id = a.id order by p.id desc"));
	}

	public void add() {
		render("add.html");
	}

	public void create() {
		Activity a = getModel(Activity.class);
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

	public void delete() {
		ActivityPhoto.dao.deleteById(this.getAttr("id"));
		this.render("index/html");
	}

}
