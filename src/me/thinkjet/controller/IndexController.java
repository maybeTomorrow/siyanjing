package me.thinkjet.controller;

import me.thinkjet.model.Activity;
import me.thinkjet.model.Blog;
import me.thinkjet.model.Job;
import me.thinkjet.model.News;
import me.thinkjet.model.Slider;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.plugin.sqlinxml.SqlKit;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/", viewPath = "")
public class IndexController extends Controller {
	@Before(CacheInterceptor.class)
	@CacheName("index")
	public void index() {
		this.setAttr("activity",
				Activity.dao.find(SqlKit.sql("activity.findListForIndex")));
		this.setAttr("job", Job.dao.find(SqlKit.sql("job.findListForIndex")));
		this.setAttr("news", News.dao.find(SqlKit.sql("news.findListForIndex")));
		this.setAttr("Blog", Blog.dao.find(SqlKit.sql("blog.findListForIndex")));
		this.setAttr("slider", Slider.dao.findByCache("index", "slider-index",
				"select * from slider limit 0,5"));
	}

}
