package com.webcomm.oa.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.webcomm.oa.domain.CaseReq;

@Component
public class CaseReqValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return CaseReq.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {

		CaseReq caseReq = (CaseReq) obj;
		if (checkInputString(caseReq.getWorkitem())) {
			e.rejectValue("workitem", "CaseReq", "工作項目:不可為空");
		}
		if (caseReq.getWorkitem().length() >= 14) {
			e.rejectValue("workitem", "CaseReq", "工作項目:訊息過長");
		}
		if (caseReq.getStartdate() == null) {
			e.rejectValue("startdate", "CaseReq", "辦理啟日:日期有誤");
		}
		if (caseReq.getEnddate() == null) {
			e.rejectValue("enddate", "CaseReq", "辦理迄日:日期有誤");
		}
		if (caseReq.getEnddate().before(caseReq.getStartdate())) {
			e.rejectValue("enddate", "CaseReq", "辦理迄日:需大於辦理啟日");
		}
	}

	private boolean checkInputString(String input) {
		return (StringUtils.isBlank(input));
	}

}
