package me.thinkjet.controller;

import me.thinkjet.handler.MySpaceHandler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * CommonController
 */
@ControllerBind(controllerKey = MySpaceHandler.ACTION_PERFIX, viewPath = "space")
public class MySpaceController extends Controller {
	public void index() {

	}

}