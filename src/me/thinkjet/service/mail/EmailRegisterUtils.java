package me.thinkjet.service.mail;

public class EmailRegisterUtils extends EmailSenderUtils {

	public EmailRegisterUtils(String url, String sentTo) {
		super(setTemplate(url), sentTo, SUBJECT);
	}

	public static String setTemplate(String url) {
		return TEMPLATE_1 + URI + url + TEMPLATE_2 + URI + url + TEMPLATE_3;
	}

	private static String TEMPLATE_1 = "<http><a href=\"";
	private static String TEMPLATE_2 = "\">";
	private static String TEMPLATE_3 = "</a></http>";
	private static String URI = "localhost/register/pass/";
	private static String SUBJECT = "注册";
	
	
	public static void main(String[] args){
		new EmailRegisterUtils("hello","ylnb91@gmail.com").SendEmail();
	}

}
