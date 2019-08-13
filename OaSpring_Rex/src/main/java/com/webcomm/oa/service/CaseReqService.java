package com.webcomm.oa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.domain.Employee;
import com.webcomm.oa.domain.Unit;
import com.webcomm.oa.searchbean.CaseReqSearchBean;

public interface CaseReqService {

	public List<Unit> listAllUnit();

	public List<Employee> listAllEmployee();
	
	public List<CaseReq> listAllCaseReq();

	public void createOrUpdateCaseReq(CaseReq caseReq);

	public List<CaseReq> queryCaseReqList(CaseReqSearchBean caseReqSearchBean);

	public CaseReq getOneCaseReq(String caseNo);

	public void deleteCaseReqs(List<String> caseNos);
	
	public void deleteCaseReqs(String caseNo);

	public Page<CaseReq> queryCaseReqPageable(CaseReqSearchBean caseReqSearchBean, Pageable pageable);
	
	public int deleteCaseNos(String[] caseNos);
}
