package com.webcomm.oa.serviceImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.service.CaseReqPdfService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 產生PDF 實做
 * 
 * @author user
 *
 */
@Service
public class CaseReqPdfServiceImp implements CaseReqPdfService {

	@Override
	public JasperPrint setPdfData(List<CaseReq> list, String FileName) throws JRException, IOException {

		JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(list);

		/* Map to hold Jasper report Parameters */
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ItemDataSource", itemsJRBean);

		/* Using compiled version(.jasper) of Jasper report to generate PDF */
		// ClassLoader classLoader = getClass().getClassLoader();
		// File reportPath = new
		// File(classLoader.getResource("jasper/report1.jasper").getFile());
		Resource rs = new ClassPathResource(FileName);

		JasperPrint jasperPrint = JasperFillManager.fillReport(rs.getFile().toString(), parameters,
				new JREmptyDataSource());

		return jasperPrint;
	}

}
