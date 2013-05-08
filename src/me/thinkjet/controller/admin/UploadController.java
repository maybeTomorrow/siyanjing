package me.thinkjet.controller.admin;

import java.io.File;

import me.thinkjet.service.upyun.UploadService;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

@ControllerBind(controllerKey = "/admin/upload", viewPath = "admin/")
public class UploadController extends Controller {

	public void index() {
		render("upload.html");
	}

	public void img() {
		File file = this.getFile().getFile();
		String url = UploadService.uplaodImgToLocal(file);
		if (file.exists())
			file.delete();
		if (url != null) {
			this.setAttr("error", 0);
			this.setAttr("url", url);
		} else {
			this.setAttr("error", 1);
		}
		this.renderJson();
	}

}
