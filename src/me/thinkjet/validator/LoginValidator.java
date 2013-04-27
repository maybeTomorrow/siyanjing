package me.thinkjet.validator;

import me.thinkjet.model.Users;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class LoginValidator extends Validator {
	private static String msg = "error";

	protected void validate(Controller c) {
		validateRequiredString("users.username", msg, "请输入用户名！");
		validateRequiredString("users.password", msg, "请输入密码!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Users.class);
		controller.redirect("/login");
	}
}
