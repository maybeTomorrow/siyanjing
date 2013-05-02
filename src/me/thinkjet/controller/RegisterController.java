package me.thinkjet.controller;

import me.thinkjet.model.Users;
import me.thinkjet.service.SysconfigKey;
import me.thinkjet.service.SysconfigService;
import me.thinkjet.service.UserService;
import me.thinkjet.service.mail.EmailService;
import me.thinkjet.validator.RegisterValidator;

import org.apache.commons.lang3.RandomStringUtils;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * RegisterController
 */
@ControllerBind(controllerKey = "/register", viewPath = "profile")
public class RegisterController extends Controller {

	private UserService userService = new UserService();
	private EmailService emailService = new EmailService();
	private static String MSG = "errormsg";

	public void index() {
		if (Boolean.parseBoolean(SysconfigService
				.getValue(SysconfigKey.ALLOW_REGISTE))) {
			this.render("register.html");
		} else {
			this.setAttr("invite_registe_msg",
					SysconfigService.getValue(SysconfigKey.INVITE_REGISTE_MSG));
			this.render("invite.html");
		}
	}

	@Before(RegisterValidator.class)
	public void verify() {
		Users users = getModel(Users.class);
		if (this.userService.findUserByEmail(users.getStr("email")) != null) {
			this.setAttr(MSG, "邮箱已注册");
			return;
		}
		if (this.userService.findUserByUsername(users.getStr("username")) != null) {
			this.setAttr(MSG, "用户名已注册");
			return;
		}
		users.put("verified", 0)
				.put("verify", RandomStringUtils.random(50, true, true))
				.put("name", users.getStr("username"));
		this.userService.createUser(users);
		emailService.registerSender(
				users.getStr("username") + "-" + users.getStr("verify"),
				users.getStr("email"));
		this.setAttr("username", users.getStr("username"));
		this.render("check.html");
	}

	public void reverify() {
		Users users = this.userService.findUserByUsername(getPara(0));
		emailService.registerSender(
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
