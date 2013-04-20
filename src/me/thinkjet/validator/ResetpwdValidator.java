package me.thinkjet.validator;

import com.jfinal.core.Controller;
import com.jfinal.ext.render.CaptchaRender;
import com.jfinal.kit.StringKit;
import com.jfinal.validate.Validator;

/**
 * BlogValidator.
 */
public class ResetpwdValidator extends Validator {
	private static String msg = "errormsg";

	protected void validate(Controller c) {
		setShortCircuit(true);
		validateRequired("email", msg, "请您输入邮箱!");
		validateEmail("email", msg, "请您输入正确邮箱格式!");
		validateRequired("vCode", msg, "请您输入验证码!");
		String random = c.getCookie("randomStr");
		String vCode = c.getPara("vCode");
		if (StringKit.notBlank(vCode)){
			vCode = vCode.toUpperCase();
			boolean success = CaptchaRender.validate(c, vCode, random);
			if(!success)
				this.addError(msg, "验证码输入不正确,请重新输入!");
		}
	}

	protected void handleError(Controller c) {
		c.keepPara();
		c.render("/WEB-INF/views/register/restpwd.html");
	}
}
