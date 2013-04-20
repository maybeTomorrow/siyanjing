package me.thinkjet.service.mail;

import java.util.Collection;

public class EmailSubscribeUtils extends EmailSenderUtils {

	public EmailSubscribeUtils(String htmlEmailTemplate, String subject) {
		super(htmlEmailTemplate, subject);
	}

	private void setReceiver() {
	}

	public void setListCollection(Collection<String> listCollection) {
		this.listCollection = listCollection;
	}

	private Collection<String> listCollection;

}
