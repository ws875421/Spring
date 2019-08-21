package com.webcomm.oa.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.DateBuilder.IntervalUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.datetime.DateFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcomm.oa.batch.MailCaseReqReportJob;
import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.exception.CustomGenericException;
import com.webcomm.oa.result.ResultBeen;
import com.webcomm.oa.service.CaseReqPdfService;
import com.webcomm.oa.service.CaseReqService;
import com.webcomm.oa.service.MailService;
import com.webcomm.oa.validator.CaseReqValidator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.webcomm.oa.searchbean.CaseReqSearchBean;

/**
 * 承辦案件 Controller
 * 
 * @author user
 *
 */
@Controller
@RequestMapping("/")
public class CaseReqController {

	@Autowired
	private CaseReqService caseReqService;

	@Autowired
	private MailService mailService;

	@Autowired
	private CaseReqPdfService caseReqPdfService;

	@Autowired
	private CaseReqValidator reqValidator;

	private static final Logger LOG = LoggerFactory.getLogger(CaseReqController.class);

//	@InitBinder(value = "command")
//	public void setValidator(WebDataBinder binder) {
//		binder.setValidator(new CaseReqValidator());
//	}

	/**
	 * 首頁
	 *
	 * @param model    the model
	 * @param pageable the pageable
	 * @return the string
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}

	/**
	 * 搜尋畫面
	 *
	 * @param model the model
	 * @return the search view
	 */
	@RequestMapping("/caseReq/index")
	public String getSearchView(Model model) {
		model.addAttribute("unitList", caseReqService.listAllUnit());
		return "caseReq/caseReqSearch";
	}

	/**
	 * 新增畫面
	 *
	 * @param model   the model
	 * @param caseReq the case req
	 * @return the creates the view
	 */
	@RequestMapping("/caseReq/caseReqCreate")
	public String getCreateView(Model model, CaseReq caseReq) {

		model.addAttribute("caseReq", caseReq);
		model.addAttribute("employeeList", caseReqService.listAllEmployee());
		return "caseReq/caseReqCreate";
	}

	/**
	 * 修改畫面
	 *
	 * @param model   the model
	 * @param caseReq the case req
	 * @return the update view
	 */
	@RequestMapping("/caseReq/caseReqUpdate")
	public String getUpdateView(Model model, CaseReq caseReq) {
		model.addAttribute("caseReq", caseReqService.getOneCaseReq(caseReq.getCaseNo()));
		model.addAttribute("employeeList", caseReqService.listAllEmployee());
		return "caseReq/caseReqUpdate";
	}

	/**
	 * 查詢承辦人應辦清冊
	 *
	 * @param pageable          the pageable
	 * @param model             the model
	 * @param caseReqSearchBean the case req search bean
	 * @return the search result view
	 */
	@RequestMapping("/caseReq/caseReqSearchResult")
	public String getSearchResultView(
			@PageableDefault(size = 5, sort = { "caseNo" }, direction = Sort.Direction.ASC) Pageable pageable,
			Model model, CaseReqSearchBean caseReqSearchBean) {
		LOG.info(caseReqSearchBean.toString());

		Calendar calendar = new GregorianCalendar();
		if (caseReqSearchBean.getEnd() != null) {
			calendar.setTime(caseReqSearchBean.getEnd());
			calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			caseReqSearchBean.setEnd(calendar.getTime());
		}

		model.addAttribute("pageable", pageable);
		Page<CaseReq> queryCaseReqList = caseReqService.queryCaseReqPageable(caseReqSearchBean, pageable);
		int size = queryCaseReqList.getSize();
		LOG.info("count:{}", size);
		for (CaseReq cc : queryCaseReqList) {
			LOG.info(String.valueOf(cc.getCaseNo()));
		}

		model.addAttribute("queryCaseReqList", caseReqService.queryCaseReqPageable(caseReqSearchBean, pageable));
		return "caseReq/caseReqSearchResult";
	}

	/**
	 * 新增or修改承辦人應辦清冊
	 *
	 * @param model   the model
	 * @param caseReq the case req
	 * @return the ResultBeen
	 * @throws Exception
	 */
	@RequestMapping("/caseReq/createOrUpdateCaseReq")
	@ResponseBody
	public ResultBeen<Object> createOrUpdateCaseReq(Model model, CaseReq caseReq, BindingResult result)
			throws Exception {
		List<Object> errorList = new ArrayList<>();
		reqValidator.validate(caseReq, result);

		if (result.hasErrors()) { // 現在表示執行的驗證出現錯誤
			Iterator<ObjectError> iterator = result.getAllErrors().iterator(); // 獲取全部錯誤信息
			while (iterator.hasNext()) {
				ObjectError error = iterator.next(); // 取出每一個錯誤 "【錯誤信息】code = " + error.getCode() + "，message = " +
														// error.getDefaultMessage()
				if (error.getCode().equals("CaseReq")) {
					errorList.add(error.getDefaultMessage());
				}
			}

			ResultBeen<Object> resultBeen = new ResultBeen<Object>();
			resultBeen.setMsg("error");
			resultBeen.setCode(ResultBeen.ERROR);
			resultBeen.setDate(errorList);

			return resultBeen;

		} else {
			caseReqService.createOrUpdateCaseReq(caseReq);
			return new ResultBeen<Object>();

		}

	}

	/**
	 * 刪除承辦人應辦清冊
	 *
	 * @param caseNos the case nos
	 * @return the string
	 */
	@RequestMapping("/caseReq/deleteCaseReq")
	@ResponseBody
	public ResultBeen<Object> deleteCaseReqs(@RequestParam(value = "caseNos[]") String[] caseNos) {
		int i = caseReqService.deleteCaseNos(caseNos);
		ResultBeen<Object> resultBeen = new ResultBeen();
		if (i == 0) {
			resultBeen.setMsg("ERROR");
			resultBeen.setCode(1);
			resultBeen.setDate("資料不存在");
			return resultBeen;
		}

		else {
			resultBeen.setDate("成功! 刪除 " + (i) + " 筆 ");
			return resultBeen;
		}

	}

	/**
	 * 產生 PDF & 寄信
	 *
	 * @param response          the response
	 * @param caseReqSearchBean the case req search bean
	 * @param caseReqPDF        the case req PDF
	 */
	@RequestMapping("/caseReq/converPDF")
	public void converPDF(HttpServletResponse response, CaseReqSearchBean caseReqSearchBean) {

		try {
			OutputStream out;
			JasperPrint jasperPrint = caseReqPdfService.setPdfData(caseReqService.queryCaseReqList(caseReqSearchBean),
					"jasper/report2.jasper");
			out = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setCharacterEncoding("UTF-8");

			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			out.flush();

			mailService.sendmail(jasperPrint);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

//@RequestMapping("/caseReq/caseReqCreate")
//public String getCreateView() {
//	System.out.println("getCreateView()");
////	return "redirect:/caseReq/index";
//	return "caseReq/caseReqCreate";
//}
//@RequestMapping("/caseReq2/index")
//public ModelAndView index3(Model model) {
//	System.out.println("index3");
//	getCreateView();
//	RedirectView rv = new RedirectView("/oa/caseReq/index");
//	ModelAndView mv = new ModelAndView(rv);
//	return mv;
//
//}
