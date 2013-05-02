package me.thinkjet.service.upyun;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

public class UploadService {

	private static final String FILE_BUCKET_NAME = "syj-files";
	private static final String IMG_BUCKET_NAME = "syj-img";
	private static final String USER_NAME = "siyanjing";
	private static final String USER_PWD = "syj12345";
	private static final String FILE_DOMAIN = "http://cdn.siyanjing.net";
	private static final String IMG_DOMAIN = "http://cdn.siyanjing.net";

	public static String uplaodFile(File file) {
		UpYun upyun = new UpYun(FILE_BUCKET_NAME, USER_NAME, USER_PWD);
		Calendar calender = Calendar.getInstance();
		boolean result = false;
		String filePath = "/" + calender.get(Calendar.YEAR) + "/"
				+ calender.get(Calendar.MONTH) + "/" + new Date().getTime()
				+ RandomStringUtils.randomNumeric(10)
				+ file.getName().substring(file.getName().lastIndexOf("."));
		try {
			result = upyun.writeFile(filePath, file, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result) {
			return FILE_DOMAIN + filePath;
		} else {
			return null;
		}
	}

	public static String uploadImg(File img) {
		Calendar calender = Calendar.getInstance();
		String filePath = "/" + calender.get(Calendar.YEAR) + "/"
				+ calender.get(Calendar.MONTH) + "/" + new Date().getTime()
				+ RandomStringUtils.randomNumeric(10)
				+ img.getName().substring(img.getName().lastIndexOf("."));
		boolean result = false;
		UpYun upyun = new UpYun(IMG_BUCKET_NAME, USER_NAME, USER_PWD);
		try {
			result = upyun.writeFile(filePath, img, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result) {
			return IMG_DOMAIN + filePath;
		} else {
			return null;
		}
	}
}
