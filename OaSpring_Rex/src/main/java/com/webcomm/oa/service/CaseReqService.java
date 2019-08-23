package com.webcomm.oa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.domain.Employee;
import com.webcomm.oa.domain.Unit;
import com.webcomm.oa.searchbean.CaseReqSearchBean;

/**
 * 承辦案件Service
 * 
 * @author user
 *
 */
public interface CaseReqService {

	/**
	 * 單位清單查詢
	 * 
	 * @return
	 */
	public List<Unit> listAllUnit();

	/**
	 * 員工清單查詢
	 * 
	 * @return
	 */
	public List<Employee> listAllEmployee();

	/**
	 * 承辦案件清單查詢
	 * 
	 * @return
	 */
	public List<CaseReq> listAllCaseReq();

	/**
	 * 承辦案件 新增or刪除
	 * 
	 * @param caseReq 承辦案件
	 * @throws Exception
	 */
	public void createOrUpdateCaseReq(CaseReq caseReq) throws Exception;

	/**
	 * 畫面搜尋條件
	 * 
	 * @param caseReqSearchBean 搜尋條件
	 * @return 承辦案件清單
	 */
	public List<CaseReq> queryCaseReqList(CaseReqSearchBean caseReqSearchBean);

	/**
	 * 依工作編號查詢承辦案件
	 * 
	 * @param caseNo 工作編號
	 * @return 承辦案件
	 */
	public CaseReq getOneCaseReq(String caseNo);

	/**
	 * 刪除承辦案件(多筆)
	 * 
	 * @param caseNos 工作編號
	 */
	public void deleteCaseReqs(String[] caseNos)throws Exception;

	/**
	 * 刪除承辦案件
	 * 
	 * @param caseNo 工作編號
	 */
	public void deleteCaseReqs(String caseNo);

	/**
	 * 複合查詢，分頁
	 * 
	 * @param caseReqSearchBean 搜尋條件
	 * @param pageable          分頁
	 * @return 承辦案件集合
	 */
	public Page<CaseReq> queryCaseReqPageable(CaseReqSearchBean caseReqSearchBean, Pageable pageable);

	/**
	 * 刪除多筆承辦案件
	 * 
	 * @param caseNos
	 * @return 成功筆數
	 */
	public int deleteCaseNos(String[] caseNos)throws Exception ;
}
