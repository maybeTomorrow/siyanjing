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
		System.out.println("index");
		this.setAttr("activity",
				Activity.dao.find(SqlKit.sql("activity.findListForIndex")));
		System.out.println("index");
		this.setAttr("job", Job.dao.find(SqlKit.sql("job.findListForIndex")));
		System.out.println("index");
		this.setAttr("news", News.dao.find(SqlKit.sql("news.findListForIndex")));
		System.out.println("index");
		this.setAttr("Blog", Blog.dao.find(SqlKit.sql("blog.findListForIndex")));
		System.out.println("index");
		this.setAttr("slider", Slider.dao.findByCache("index", "slider-index",
				"select * from slider limit 0,5"));
		System.out.println("index");
	}

}
