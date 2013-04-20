package me.thinkjet.service.mail;

public class EmailResetUtils extends EmailSenderUtils {

	public EmailResetUtils(String url, String sentTo) {
		super(setTemplate(url), sentTo, SUBJECT);
	}

	public static String setTemplate(String url) {
		return TEMPLATE_1 + URI + url + TEMPLATE_2 + URI + url + TEMPLATE_3;
	}

	private static String TEMPLATE_1 = "<http><a href=\"";
	private static String TEMPLATE_2 = "\">";
	private static String TEMPLATE_3 = "</a></http>";
	private static String URI = "localhost/resetpwd/pass/";
	private static String SUBJECT = "注册";

}
