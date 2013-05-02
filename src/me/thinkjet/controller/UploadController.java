package me.thinkjet.controller;

import me.thinkjet.service.upyun.UploadService;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.upload.UploadFile;

/**
 * CommonController
 */
@ControllerBind(controllerKey = "/upload", viewPath = "")
public class UploadController extends Controller {
	public void file() {
		UploadFile file = this.getFile();
		String url = UploadService.uplaodFile(file.getFile());
		this.renderText(url);
	}

	public void img() {
		UploadFile file = this.getFile();
		String url = UploadService.uplaodFile(file.getFile());
		this.renderText(url);
	}

}
