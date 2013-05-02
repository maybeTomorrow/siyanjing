package me.thinkjet.controller.admin;

import me.thinkjet.model.Slider;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "admin/slider", viewPath = "admin/slider")
public class SliderController extends Controller {
	public void index() {
		this.setAttr("slider",
				Slider.dao.find("select * from slider order by id asc"));
	}
	
	public void show(){
		this.setAttr("slider", Slider.dao.findById(this.getPara()));
	}

	public void edit(){
	}
}
