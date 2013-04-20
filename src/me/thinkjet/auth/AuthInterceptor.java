package me.thinkjet.auth;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

/**
 * 
 * @ClassName AuthInterceptor
 * @author johnny_zyc
 * @Modified 2013-4-13 下午12:34:51
 * 
 */
public class AuthInterceptor implements Interceptor {

	final public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		String authRoles = getAuthRoles(ai, controller);
		if (authRoles != null) {
			if (authRoles.equals(AuthRoles.ANONYMOUS)) {
				ai.invoke();
				return;
			} else {
				if (AuthManager.checkAuth(controller, authRoles)) {
					ai.invoke();
					AuthManager.putSessionInView(controller, false);
					return;
				} else {
					AuthManager.redirectToLoginPage(controller);
					return;
				}
			}
		} else {
			if (AuthManager.checkAuth(controller)) {
				ai.invoke();
				AuthManager.putSessionInView(controller, false);
				return;
			} else {
				AuthManager.redirectToLoginPage(controller);
				return;
			}

		}
	}

	private String getAuthRoles(ActionInvocation ai, Controller controller) {
		AuthRoles authRoles = ai.getMethod().getAnnotation(AuthRoles.class);
		if (authRoles != null)
			return authRoles.value();
		authRoles = controller.getClass().getAnnotation(AuthRoles.class);
		if (authRoles != null)
			return authRoles.value();
		return null;
	}

}
