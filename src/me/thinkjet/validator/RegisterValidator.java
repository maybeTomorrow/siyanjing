package me.thinkjet.validator;

import me.thinkjet.model.Users;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class RegisterValidator extends Validator {
	private static String msg = "errormsg";
	
	protected void validate(Controller controller) {
		validateRequiredString("users.username", msg, "请输入用户名！");
		validateRequiredString("users.password", msg, "请输入密码!");
		validateRequiredString("users.email", msg, "请输入邮箱地址!");
		validateEmail("users.email", msg, "请输入正确格式的邮箱地址！");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Users.class);
		controller.render("index.html");
	}
}
