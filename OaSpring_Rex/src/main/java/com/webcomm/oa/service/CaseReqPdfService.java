package com.webcomm.oa.service;

import java.io.IOException;
import java.util.List;

import com.webcomm.oa.domain.CaseReq;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;


public interface CaseReqPdfService {

	JasperPrint setPdfData(List<CaseReq> list,String FileName)throws JRException, IOException;

}
