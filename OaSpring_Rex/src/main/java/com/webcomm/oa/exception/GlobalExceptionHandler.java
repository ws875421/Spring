package com.webcomm.oa.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcomm.oa.result.ResultBeen;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = CustomGenericException.class)
	public ResultBeen<Object> handleCustomException(CustomGenericException ex) {

		System.out.println("@");

		List<Object> errorList = new ArrayList<>();
		errorList.add(ex.getErrCode() + ":" + ex.getErrMsg());
		ResultBeen<Object> resultBeen = new ResultBeen<Object>();
		resultBeen.setMsg("error");
		resultBeen.setCode(ResultBeen.ERROR);
		resultBeen.setDate(errorList);
		return resultBeen;

	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResultBeen<Object> handleException(Exception ex) {

		List<Object> errorList = new ArrayList<>();
		errorList.add("忙線中");
		ResultBeen<Object> resultBeen = new ResultBeen<Object>();
		resultBeen.setMsg("error");
		resultBeen.setCode(ResultBeen.ERROR);
		resultBeen.setDate(errorList);
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
