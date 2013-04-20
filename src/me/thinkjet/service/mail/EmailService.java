package me.thinkjet.service.mail;

public class EmailService {

	public void registerSender(String url, String sentTo) {
		new EmailRegisterUtils(url, sentTo).SendEmail();
	}

	public void resetSender(String url, String sentTo) {
		new EmailResetUtils(url, sentTo).SendEmail();
	}

	public void subSender() {

	}

}
