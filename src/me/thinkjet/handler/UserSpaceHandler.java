package me.thinkjet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jfinal.handler.Handler;

public class UserSpaceHandler extends Handler {

	public final static String ACTION_PERFIX = "/space";

	private static String domain;

	public UserSpaceHandler(String domain) {
		UserSpaceHandler.domain = domain;
	}

	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, boolean[] isHandled) {
		if (request.getServerName().equals(domain)) {
			target = ACTION_PERFIX + target;
		}
		this.nextHandler.handle(target, request, response, isHandled);
	}

}
