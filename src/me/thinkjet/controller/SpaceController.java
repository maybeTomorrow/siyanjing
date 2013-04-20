package me.thinkjet.controller;

import me.thinkjet.handler.UserSpaceHandler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * CommonController
 */
@ControllerBind(controllerKey = UserSpaceHandler.ACTION_PERFIX, viewPath = "space")
public class SpaceController extends Controller {
	public void index() {

	}

}