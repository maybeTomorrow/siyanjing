package me.thinkjet.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName CookieGenerator
 * @author johnny_zyc
 * @Modified 2013-3-2 下午9:53:09
 * 
 */
public class CookieUtils {

	public static final String DEFAULT_COOKIE_PATH = "/";

	/**
	 * The default is three months (7889231 in seconds, according to Google)
	 */
	public final static int REMEBER_ME_MAX_AGE = 7889231;

	private String cookieName;

	private String cookieDomain;

	private String cookiePath = DEFAULT_COOKIE_PATH;

	private boolean cookieSecure = false;

	private boolean cookieHttpOnly = false;

	public CookieUtils(final String cookieName) {
		this.cookieName = cookieName;
	}

	public void addCookie(HttpServletResponse response, String cookieValue,
			final boolean rememberMe) {
		Cookie cookie = createCookie(cookieValue);
		if (rememberMe) {
			cookie.setMaxAge(CookieUtils.REMEBER_ME_MAX_AGE);
		}

		if (cookieSecure) {
			cookie.setSecure(true);
		}
		if (cookieHttpOnly) {
			cookie.setHttpOnly(true);
		}
		response.addCookie(cookie);
	}

	public String getCookieValue(final HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public void removeCookie(HttpServletResponse response) {
		Cookie cookie = createCookie("");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	private Cookie createCookie(String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		if (cookieDomain != null) {
			cookie.setDomain(cookieDomain);
		}
		cookie.setPath(cookiePath);
		return cookie;
	}

	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}

	public void setCookiePath(String cookiePath) {
		this.cookiePath = cookiePath;
	}

	public void setCookieSecure(boolean cookieSecure) {
		this.cookieSecure = cookieSecure;
	}

	public void setCookieHttpOnly(boolean cookieHttpOnly) {
		this.cookieHttpOnly = cookieHttpOnly;
	}

}
