package com.webcomm.oa.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcomm.oa.controller.CaseReqController;
import com.webcomm.oa.result.ResultBeen;

/**
 * 錯誤統一處理
 * 
 * @author user
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseBody
	@ExceptionHandler(value = CustomGenericException.class)
	public ResultBeen<Object> handleCustomException(CustomGenericException ex) {

		List<Object> errorList = new ArrayList<>();
		errorList.add(ex.getErrCode() + ":" + ex.getErrMsg());
		ResultBeen<Object> resultBeen = new ResultBeen<Object>();
		resultBeen.setMsg("error");
		resultBeen.setCode(ResultBeen.ERROR);
		resultBeen.setData(errorList);
		return resultBeen;

	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResultBeen<Object> handleException(Exception ex) {
		ex.printStackTrace();
		LOG.info(ex.toString() + " : " + ex.getMessage());
		List<Object> errorList = new ArrayList<>();
		errorList.add("忙線中");
		ResultBeen<Object> resultBeen = new ResultBeen<Object>();
		resultBeen.setMsg("error");
		resultBeen.setCode(ResultBeen.ERROR);
		resultBeen.setData(errorList);
		return resultBeen;

	}

//	@ExceptionHandler(value = Exception.class)
//	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", e);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName("error");
//		return mav;
//	}

}
