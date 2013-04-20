package me.thinkjet.service.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailSenderUtils {

	public EmailSenderUtils(String htmlEmailTemplate, String sentTo,
			String subject) {
		this.htmlEmailTemplate = htmlEmailTemplate;
		this.sentTo = sentTo;
		this.subject = subject;
	}

	public EmailSenderUtils(String htmlEmailTemplate, String subject) {
		this.htmlEmailTemplate = htmlEmailTemplate;
		this.subject = subject;
	}

	public void SendEmail() {
		try {
			this.setInfo();
			this.setReceiver();
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

	private void setInfo() {
		email.setHostName(HOSTNAME);
		email.setAuthentication(USERNAME, PASSCODE);
		email.setSubject(this.subject);
		try {
			email.setFrom(FROM, NAME);
			email.setHtmlMsg(this.htmlEmailTemplate);
			email.setTextMsg(TEXTMSG);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	private void setReceiver() {
		try {
			email.addTo(this.sentTo);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void setHtmlEmailTemplate(String htmlEmailTemplate) {
		this.htmlEmailTemplate = htmlEmailTemplate;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	private HtmlEmail email = new HtmlEmail();
	private String htmlEmailTemplate;
	private String sentTo;
	private String subject;
	private static String TEXTMSG = "你的邮箱不支持Html邮件";
	private static String HOSTNAME = "smtp.yeah.net";
	private static String USERNAME = "ylnb91";
	private static String PASSCODE = "0507iYangLu";
	private static String FROM = "ylnb91@yeah.net";
	private static String NAME = "Me";

}
