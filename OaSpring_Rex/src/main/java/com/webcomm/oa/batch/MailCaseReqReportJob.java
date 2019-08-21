package com.webcomm.oa.batch;

import javax.activation.DataSource;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webcomm.oa.service.MailService;

/**
 * Scheduler 計時寄信
 * 
 * @author user
 *
 */
@Component
public class MailCaseReqReportJob implements Job {

	@Autowired
	private MailService mailService;

	private static int i;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		DataSource aAttachment = (DataSource) dataMap.get("aAttachment");
		mailService.sendAttachmentMail("ws0936167890@gmail.com",
				"使用者：admin匯出了報表(caseReqExport" + String.format("%03d", ++i) + ")", "請參造附件。", aAttachment);
	}

}
