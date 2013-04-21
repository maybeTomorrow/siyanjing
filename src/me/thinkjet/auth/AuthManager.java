package me.thinkjet.auth;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import me.thinkjet.controller.LoginController;

import com.jfinal.core.Controller;
import com.jfinal.log.Logger;

/**
 * 
 * @ClassName AuthManager
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:06
 * 
 */
public class AuthManager {
	private static final Logger log = Logger.getLogger(LoginController.class);

	private static Configuration configuration = null;

	public final static String SESSION_NAME = "authuser";

	public static void init() {
		if (configuration == null) {
			Configuration.initConfiguration();
		}
	}

	public static boolean checkAuth(Controller controller) {
		AuthUser au = ((AuthUser) controller.getSession().getAttribute(
				AuthManager.SESSION_NAME));
		String url = controller.getRequest().getServletPath();
		for (GlobeRoles g : Configuration.getIntercept_map()) {
			if (Pattern.compile(g.getPattern()).matcher(url).find()) {
				if (au != null) {
					String[] roles = au.getRoles();
					for (int j = 0; j < roles.length; j++) {
						if (g.authorize(roles[j])) {
							log.info(au.getUser().getStr("username") + "["
									+ roles[j] + "] " + "get authorized: "
									+ url);
							return true;
						}
					}
				}
				return false;
			}
		}
		return true;
	}

	public static boolean checkAuth(Controller controller, String roles) {
		AuthUser au = ((AuthUser) controller.getSession().getAttribute(
				AuthManager.SESSION_NAME));
		String[] roles_access = roles.split(",");
		if (au != null) {
			String[] roles_session = au.getRoles();
			for (int i = 0; i < roles_session.length; i++) {
				for (int j = 0; j < roles_access.length; j++) {
					if (roles_session[i].equals(roles_access[j])) {
						log.info(au.getUser().getStr("username") + "["
								+ roles_session[i] + "] " + "get authorized: "
								+ controller.getRequest().getServletPath());
						return true;
					}
				}
			}
		}

		return false;
	}

	public static void redirectToLoginPage(Controller controller) {
		try {
			controller.redirect(Configuration.getLogin_url()
					+ "?redirect="
					+ URLEncoder.encode(controller.getRequest().getRequestURL()
							.toString(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static void putSessionInView(Controller controller,
			boolean createSession) {
		HttpSession hs = controller.getSession(createSession);
		if (hs != null) {
			controller.setAttr("auth_user",
					(AuthUser) hs.getAttribute(SESSION_NAME));

		}
	}

	public static void addSession(Controller controller, AuthUser authUser) {
		controller.getSession().setAttribute(SESSION_NAME, authUser);
	}

	public static void RemoveSession(Controller controller) {
		if (controller.getSession() != null)
			controller.getSession().removeAttribute(SESSION_NAME);
	}

	public static AuthUser getSession(Controller controller) {
		return controller.getSession().getAttribute(SESSION_NAME) == null ? null
				: (AuthUser) controller.getSession().getAttribute(SESSION_NAME);
	}
}