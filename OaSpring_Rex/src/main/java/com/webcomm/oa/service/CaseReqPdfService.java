package com.webcomm.oa.service;

import java.io.IOException;
import java.util.List;

import com.webcomm.oa.domain.CaseReq;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * 承辦案件PDF產生
 * 
 * @author user
 *
 */
public interface CaseReqPdfService {

	/**
	 * 設定產生PDF參數
	 * 
	 * @param list     承辦案件List
	 * @param FileName jasper檔名
	 * @return JasperPrint
	 * @throws JRException
	 * @throws IOException
	 */
	JasperPrint setPdfData(List<CaseReq> list, String FileName) throws JRException, IOException;

}
