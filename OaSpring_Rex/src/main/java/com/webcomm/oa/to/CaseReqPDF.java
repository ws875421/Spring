package com.webcomm.oa.to;

import java.util.Date;
import com.webcomm.oa.data.CaseLevelEnum;
import com.webcomm.oa.data.CaseTypeEnum;

public class CaseReqPDF {

	private String num;

	private String caseNo;

	private String caseType;

	private String caseLevel;

	private String hEmpName;

	private String hEmpId;

	private String cEmpName;

	private String cEmpId;

	private String startdate;

	private String enddate;
	
	private String workitem;

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String gethEmpName() {
		return hEmpName;
	}

	public void sethEmpName(String hEmpName) {
		this.hEmpName = hEmpName;
	}

	public String gethEmpId() {
		return hEmpId;
	}

	public void sethEmpId(String hEmpId) {
		this.hEmpId = hEmpId;
	}

	public String getcEmpName() {
		return cEmpName;
	}

	public void setcEmpName(String cEmpName) {
		this.cEmpName = cEmpName;
	}

	public String getcEmpId() {
		return cEmpId;
	}

	public void setcEmpId(String cEmpId) {
		this.cEmpId = cEmpId;
	}

	public String getWorkitem() {
		return workitem;
	}

	public void setWorkitem(String workitem) {
		this.workitem = workitem;
	}

	@Override
	public String toString() {
		return "CaseReqPDF [num=" + num + ", caseNo=" + caseNo + ", caseType=" + caseType + ", caseLevel=" + caseLevel
				+ ", hEmpName=" + hEmpName + ", hEmpId=" + hEmpId + ", cEmpName=" + cEmpName + ", cEmpId=" + cEmpId
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", workitem=" + workitem + "]";
	}

}
