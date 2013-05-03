package me.thinkjet.controller;

import java.util.HashMap;
import java.util.Map;

import me.thinkjet.auth.AuthManager;
import me.thinkjet.auth.AuthUser;
import me.thinkjet.model.Comment;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/comment", viewPath = "/")
public class CommentController extends Controller {
	public void index() {
		String target = this.getPara(0);
		int page = this.getParaToInt(1, 1);
		this.setAttr("target", target);
		this.setAttr("page", page);
		this.setAttr("comment_list", Comment.dao.paginateByCache("comment",
				target + "_" + page, page, 20, "select c.*,u.avatar as avatar",
				"from comment c left join users u "
						+ "on c.comment_uname=u.username "
						+ "where c.target=? " + "order by c.id asc", target));
		this.render("comment.html");
	}

	public void update() {
		String target = this.getPara(0);
		int page = this.getParaToInt(1, 1);
		this.setAttr("target", target);
		this.setAttr("page", page);
		Page<Comment> comments = Comment.dao.paginate(page, 20,
				"select c.*,u.avatar as avatar",
				"from comment c left join users u "
						+ "on c.comment_uname=u.username "
						+ "where c.target=? " + "order by c.id asc", target);
		CacheKit.put("comment", target + "_" + page, comments);
		this.setAttr("comment_list", comments);
		this.render("comment.html");
	}

	public void add() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (this.getPara("target") == null || this.getPara("comment") == null
				|| AuthManager.getSession(this) == null) {
			this.renderHtml("评论失败");
			return;
		}
		attrs.put("target", this.getPara("target"));
		attrs.put("comment", this.getPara("comment"));
		AuthUser user = AuthManager.getSession(this);
		attrs.put("comment_uname", user.getUser().getStr("username"));
		attrs.put("comment_unickname", user.getUser().getStr("name") == null
				|| user.getUser().getStr("name").equals("") ? user.getUser()
				.getStr("username") : user.getUser().getStr("name"));
		if (this.getPara("motion") != null) {
			String motion = this.getPara("motion");
			String[] motions = motion.substring(2, motion.length() - 1).split(
					",");
			String uname = motions[0].split(":")[1];
			String unickname = motions[1].split(":")[1];
			attrs.put("at_uname", uname);
			attrs.put("at_unickname", unickname);
		}
		new Comment().setAttrs(attrs).save();
		this.renderHtml("评论成功！");
	}
}
