package com.webcomm.oa.service;

import java.io.IOException;
import java.util.List;

import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.to.CaseReqPDF;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


public interface CaseReqPdfService {

	JasperPrint setPdfData(List<CaseReq> list, CaseReqPDF caseReqPDF,String FileName)throws JRException, IOException;

}
