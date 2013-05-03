package me.thinkjet.controller;

import java.io.File;

import me.thinkjet.service.upyun.UploadService;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * kindeditor 专用 文件&图片上传实现
 */
@ControllerBind(controllerKey = "/upload", viewPath = "")
public class UploadController extends Controller {
	public void file() {
		File file = this.getFile().getFile();
		String url = UploadService.uplaodFileToLocal(file);
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
