package me.thinkjet.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import me.thinkjet.auth.AuthManager;
import me.thinkjet.auth.AuthUser;
import me.thinkjet.model.UserRole;
import me.thinkjet.model.Users;
import me.thinkjet.service.UserService;
import me.thinkjet.service.mail.EmailService;
import me.thinkjet.utils.RandomStringGenerator;
import me.thinkjet.validator.LoginValidator;
import me.thinkjet.validator.ResetpwdValidator;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.Controller;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.StringKit;

@ControllerBind(controllerKey = "/login", viewPath = "/profile")
@ClearInterceptor(ClearLayer.ALL)
public class LoginController extends Controller {
	private static final String USERNAME = "users.username";
	private static final String PASSWD = "users.password";
	private UserService us = new UserService();
	private EmailService es = new EmailService();

	public void index() {
		String target = "/";
		if (this.getRequest().getHeader("referer") != null)
			try {
				target = URLEncoder.encode(
						this.getRequest().getHeader("referer"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		else if (this.getPara("redirect") != null
				&& !this.getPara("redirect").equals("")) {
			try {
				target = URLEncoder.encode(this.getPara("redirect"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
			}
		}
		this.setAttr("redirect", target);
		this.render("login.html");
	}

	// 登录
	@Before(LoginValidator.class)
	public void submit() {
		String uname = this.getPara(USERNAME);
		Users u = us.getUser(uname);
		if (u == null) {
			this.setAttr("error", "用户不存在");
			this.render("login.html");
			return;
		}
		if (u.getStr("password").equals(
				us.getEncodedPassword(this.getPara(PASSWD),
						u.getStr("username")))) {
			List<UserRole> urs = UserRole.dao.find(
					"select * from user_role where user_id = ?",
					u.getLong("id"));
			if (urs == null || urs.size() == 0) {
				this.setAttr("error", "权限错误");
				this.render("login.html");
				return;
			} else {
				String[] roles = new String[urs.size()];
				for (int i = 0; i < urs.size(); i++) {
					roles[i] = urs.get(i).getStr("role");
				}
				AuthManager.addSession(this, new AuthUser(u, roles));
				us.UpdateUserStatus(u);
				if (this.getPara("redirect") != null
						&& !this.getPara("redirect").equals("")) {
					try {
						this.redirect(URLDecoder.decode(getPara("redirect"),
								"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						this.redirect("/");
						return;
					}
				} else {
					this.redirect("/");
					return;
				}
			}
		} else {
			this.setAttr("error", "密码错误");
			this.render("login.html");
			return;
		}
	}

	public void out() {
		AuthManager.RemoveSession(this);
		this.redirect("/");
	}

	// 验证码图片
	@ClearInterceptor(ClearLayer.ALL)
	public void img() {
		String random = new RandomStringGenerator().getNewString();
		this.setCookie("randomStr", random, 15);
		CaptchaRender img = new CaptchaRender(random);
		render(img);
	}

	public void pwd() {
		this.render("resetpwd.html");
	}

	// 密码重置
	@Before(ResetpwdValidator.class)
	public void reset() {
		String email = getPara("email");
		String random = getCookie("randomStr");
		if (StringKit.isBlank(random))
			this.setAttr("errormsg", "超时，请刷新!");
		us.findUserByEmail(email).set("verified", 0).set("verify", random)
				.save();
		this.es.resetSender(email + "-" + random, email);
		this.render("check.html");
	}

	// 密码重置通过
	public void pass() {
		Users users = this.us.findUserByEmail(getPara(0));
		if (users.get("vertify").equals(getPara(1)))
			users.set("vertify", null).set("verified", 1).update();
		else
			this.render("error.html");
		this.render("resetpwd.html");
	}
}
