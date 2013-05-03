package me.thinkjet.service.upyun;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import com.jfinal.kit.PathKit;

public class UploadService {

	private static final String FILE_BUCKET_NAME = "syj-files";
	private static final String IMG_BUCKET_NAME = "syj-img";
	private static final String USER_NAME = "siyanjing";
	private static final String USER_PWD = "syj12345";
	private static final String FILE_DOMAIN = "http://www.siyanjing.com";
	private static final String IMG_DOMAIN = "http://www.siyanjing.com";

	/**
	 * 上床文件到又拍云
	 * 
	 * @param file
	 * @return
	 */
	public static String uplaodFileToUpyun(File file) {
		UpYun upyun = new UpYun(FILE_BUCKET_NAME, USER_NAME, USER_PWD);
		Calendar calender = Calendar.getInstance();
		boolean result = false;
		String filePath = "/" + calender.get(Calendar.YEAR) + "/"
				+ (calender.get(Calendar.MONTH) + 1) + "/"
				+ new Date().getTime() + RandomStringUtils.randomNumeric(10)
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

	/**
	 * 上传图片到又拍云
	 * 
	 * @param img
	 * @return
	 */
	public static String uploadImgToUpyun(File img) {
		Calendar calender = Calendar.getInstance();
		String filePath = "/" + calender.get(Calendar.YEAR) + "/"
				+ (calender.get(Calendar.MONTH) + 1) + "/"
				+ new Date().getTime() + RandomStringUtils.randomNumeric(10)
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

	/**
	 * 上传文件到本地服务器
	 * 
	 * @param file
	 * @return
	 */
	public static String uplaodFileToLocal(File file) {
		Calendar calender = Calendar.getInstance();
		String filePath = "/upload/files/" + calender.get(Calendar.YEAR) + "/"
				+ (calender.get(Calendar.MONTH) + 1);
		String file_name = new Date().getTime()
				+ RandomStringUtils.randomNumeric(10)
				+ file.getName().substring(file.getName().lastIndexOf("."));
		if (!new File(PathKit.getWebRootPath() + filePath).exists()) {
			new File(PathKit.getWebRootPath() + filePath).mkdirs();
		}

		file.renameTo(new File(PathKit.getWebRootPath() + filePath + "/"
				+ file_name));
		return FILE_DOMAIN + filePath + "/" + file_name;
	}

	/**
	 * 上传图片到本地服务器
	 * 
	 * @param img
	 * @return
	 */
	public static String uplaodImgToLocal(File img) {
		Calendar calender = Calendar.getInstance();
		String filePath = "/upload/imgs/" + calender.get(Calendar.YEAR) + "/"
				+ (calender.get(Calendar.MONTH) + 1);
		String file_name = new Date().getTime()
				+ RandomStringUtils.randomNumeric(10)
				+ img.getName().substring(img.getName().lastIndexOf("."));
		if (!new File(PathKit.getWebRootPath() + filePath).exists()) {
			new File(PathKit.getWebRootPath() + filePath).mkdirs();
		}

		img.renameTo(new File(PathKit.getWebRootPath() + filePath + "/"
				+ file_name));
		return IMG_DOMAIN + filePath + "/" + file_name;
	}
}
