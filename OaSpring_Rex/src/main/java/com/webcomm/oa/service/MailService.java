package com.webcomm.oa.service;

import javax.activation.DataSource;

import net.sf.jasperreports.engine.JasperPrint;


/**
 * 寄信 MailService
 * @author user
 *
 */
public interface MailService {

	/**
	 * 傳送普通郵件
	 * 
	 * @param to      收件者
	 * @param subject 主旨
	 * @param content 內文
	 */
	void sendSimpleMail(String to, String subject, String content);

	/**
	 * 傳送Html郵件
	 * 
	 * @param to      收件者
	 * @param subject 主旨
	 * @param content 內文
	 */
	void sendHtmlMail(String to, String subject, String content);

	/**
	 * 傳送帶附件的郵件
	 * 
	 * @param to          收件者
	 * @param subject     主旨
	 * @param content     內文
	 * @param aAttachment 夾帶檔案
	 */
	void sendAttachmentMail(String to, String subject, String content, DataSource aAttachment);

	/**
	 * 計時寄信
	 * 
	 * @param jasperPrint
	 * @throws Exception
	 */
	public void sendmail(JasperPrint jasperPrint) throws Exception;
}
