package com.webcomm.oa.service;

import javax.activation.DataSource;

public interface MailService {
	// 傳送普通郵件
	void sendSimpleMail(String to, String subject, String content);

	// 傳送Html郵件
	void sendHtmlMail(String to, String subject, String content);

	// 傳送帶附件的郵件
	void sendAttachmentMail(String to, String subject, String content, DataSource aAttachment);
}
