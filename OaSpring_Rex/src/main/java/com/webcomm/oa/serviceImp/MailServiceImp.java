package com.webcomm.oa.serviceImp;

import java.io.File;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.webcomm.oa.service.MailService;

// TODO: Auto-generated Javadoc
/**
 * The Class MailServiceImpl.
 */
//@Component("mailService")
@Service("mailService")
public class MailServiceImp implements MailService {

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/** The from. */
	@Value("${mail.fromMail.addr}")
	private String from;

	/* (non-Javadoc)
	 * @see com.webcomm.oa.service.MailService#sendSimpleMail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {

			mailSender.send(message);
			System.out.println("success");
		} catch (Exception e) {
			System.out.println("fail" + e);
		}
	}

	/* (non-Javadoc)
	 * @see com.webcomm.oa.service.MailService#sendHtmlMail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true); // true表示需要建立一個multipart message
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
			System.out.println("html郵件傳送成功");
		} catch (MessagingException e) {
			System.out.println("傳送html郵件時發生異常！" + e);
		}
	}

	/* (non-Javadoc)
	 * @see com.webcomm.oa.service.MailService#sendAttachmentMail(java.lang.String, java.lang.String, java.lang.String, javax.activation.DataSource)
	 */
	@Override
	public void sendAttachmentMail(String to, String subject, String content, DataSource aAttachment) {
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			helper.addAttachment("caseReqExport001.pdf", aAttachment);

			mailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
