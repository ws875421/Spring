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

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = CustomGenericException.class)
	public String handleCustomException(CustomGenericException ex) {

		List<Object> errorList = new ArrayList<>();
		errorList.add(ex.getErrCode() + ":" + ex.getErrMsg());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", "error");
		map.put("errorMsg", errorList);
		String jsonStr = gson.toJson(map);
		return jsonStr;

	}

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception ex) {

		List<Object> errorList = new ArrayList<>();
		errorList.add(ex.getMessage());
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		HashMap<String, Object> map = new HashMap<>();
		map.put("status", "error");
		map.put("errorMsg", errorList);
		String jsonStr = gson.toJson(map);
		return jsonStr;

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
