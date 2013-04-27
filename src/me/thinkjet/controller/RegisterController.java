package me.thinkjet.controller;

import me.thinkjet.model.Users;
import me.thinkjet.service.UserService;
import me.thinkjet.service.mail.EmailService;
import me.thinkjet.utils.RandomStringGenerator;
import me.thinkjet.validator.RegisterValidator;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * RegisterController
 */
@ControllerBind(controllerKey = "/register", viewPath = "profile")
public class RegisterController extends Controller {

	private UserService userService = new UserService();
	private EmailService eService = new EmailService();
	private static String MSG = "errormsg";

	public void index() {
		this.render("register.html");
	}

	@Before(RegisterValidator.class)
	public void verify() {
		Users users = getModel(Users.class);
		if (this.userService.findUserByEmail(users.getStr("email")) != null){
			this.setAttr(MSG, "邮箱已注册");
		}
		if (this.userService.findUserByUsername(users.getStr("username")) != null)
			this.setAttr(MSG, "用户名已注册");
		users.put("verified", 0)
				.put("verify", new RandomStringGenerator().getNewString())
				.put("name", "");
		eService.registerSender(
				users.getStr("username") + "-" + users.getStr("verify"),
				users.getStr("email"));
		this.userService.createUser(users);
		this.setAttr("username", users.getStr("username"));
		this.render("check.html");
	}

	public void reverify() {
		Users users = this.userService.findUserByUsername(getPara(0));
		eService.registerSender(
				users.getStr("username") + "-" + users.getStr("verify"),
				users.getStr("email"));
		this.render("check.html");

	}

	public void pass() {
		Users users = this.userService.findUserByUsername(getPara(0));
		System.out.println(getPara(1) + "   ");
		if (users.get("verify").equals(getPara(1)))
			users.set("verify", null).set("verified", 1).update();
		else
			this.render("error.html");
		this.render("/WEB-INF/views/profile/login.html");
	}

	public void check() {
		if (this.getPara("users.username") != null
				|| this.getPara("users.email") != null) {
			String name = this.getPara("users.username") == null ? this
					.getPara("users.email") : this.getPara("users.username");
			if (this.userService.checkExist(name)) {
				this.renderHtml("false");
			} else {
				this.renderHtml("true");
			}
		} else {
			this.renderHtml("false");
		}

	}

}
