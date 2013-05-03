package me.thinkjet.controller;

import me.thinkjet.model.News;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

@ControllerBind(controllerKey = "/news", viewPath = "news")
public class NewsController extends Controller {
	@Before(CacheInterceptor.class)
	@CacheName("news")
	public void index() {
		this.setAttr(
				"news",
				News.dao.paginateByCache("news",
						"index_" + this.getParaToInt(1, 1),
						this.getParaToInt(1, 1), 10, "select *", "from news"));
	}
}
