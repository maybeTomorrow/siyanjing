package me.thinkjet.controller;

import me.thinkjet.model.Comment;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/comment", viewPath = "/")
public class CommentController extends Controller {
	@Before(CacheInterceptor.class)
	@CacheName("comment")
	public void index() {
		String target = this.getPara(0);
		int page = this.getParaToInt(1, 1);
		this.setAttr("target", target);
		this.setAttr("page", page);
		this.setAttr("comment_list", Comment.dao.paginateByCache("comment",
				target + "_" + page, page, 6, "select c.*,u.avatar",
				"as avatar from comment c left join users u "
						+ "on  c.comment_uname=u.username "
						+"where c.target=? "
						+ "order by c.id asc", target));
		this.setAttr(
				"count",
				Comment.dao.findByCache("comment", "count_" + target,
						"select count(id) from comment where target=?", target)
						.get(0).getAttrValues()[0]);
		System.out.println(Comment.dao.paginateByCache("comment",
				target + "_" + page, page, 6, "select c.*,u.avatar",
				"as avatar from comment c left join users u "
						+ "on c.target=? and c.comment_uname=u.username "
						+ "order by c.id asc", target));
		this.render("comment.html");
	}
	public void add(){
		Comment comment = getModel(Comment.class);
		comment.save();
	}
	
}
