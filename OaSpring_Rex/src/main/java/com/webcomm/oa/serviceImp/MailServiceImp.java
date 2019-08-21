package com.webcomm.oa.serviceImp;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.webcomm.oa.batch.MailCaseReqReportJob;
import com.webcomm.oa.service.MailService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * MailService實作
 * 
 * @author user
 *
 */
@Service("mailService")
public class MailServiceImp implements MailService {

	/** The mail sender. */
	@Autowired
	private JavaMailSender mailSender;

	/** The from. */
	@Value("${mail.fromMail.addr}")
	private String from;

	@Autowired
	private Scheduler scheduler;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.MailService#sendSimpleMail(java.lang.String,
	 * java.lang.String, java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.MailService#sendHtmlMail(java.lang.String,
	 * java.lang.String, java.lang.String)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.MailService#sendAttachmentMail(java.lang.String,
	 * java.lang.String, java.lang.String, javax.activation.DataSource)
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

	public void sendmail(JasperPrint jasperPrint) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		DataSource aAttachment = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("aAttachment", aAttachment);
		JobDetail job = JobBuilder.newJob(MailCaseReqReportJob.class).setJobData(jobDataMap).build();
		Trigger trigger = TriggerBuilder.newTrigger().startAt(DateBuilder.futureDate(10, IntervalUnit.SECOND)).build();
		scheduler.scheduleJob(job, trigger);
	}

}
