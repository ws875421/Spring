package com.webcomm.oa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.webcomm.oa.data.CaseLevelEnum;
import com.webcomm.oa.data.CaseTypeEnum;

/**
 * 承辦案件entuty
 * @author user
 *
 */
@Entity
@Table(name = "CASE_REQ")
public class CaseReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CASE_NO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_caseNo")
	@GenericGenerator(name = "sequence_caseNo", strategy = "com.webcomm.oa.gen.MyGenerator")
	private String caseNo;

	@Column(name = "CASE_TYPE")
	@Enumerated(EnumType.ORDINAL)
	private CaseTypeEnum caseType;

	@Column(name = "CASE_LEVEL")
	@Enumerated(EnumType.ORDINAL)
	private CaseLevelEnum caseLevel;

	@ManyToOne
	@JoinColumn(name = "HOST_EMP_ID")
	private Employee hostEmployee;

	@ManyToOne
	@JoinColumn(name = "COHOST_EMP_ID")
	private Employee cohostEmployee;

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date startdate;

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date enddate;

//	@NotEmpty(message = "不能為空")
	@Column(name = "WORKITEM")
//	@Size(max = 14, message = "訊息過長")
	private String workitem;

	@Column(name = "CREATTIME")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date creattime;

	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public CaseReq() {
		super();
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public CaseTypeEnum getCaseType() {
		return caseType;
	}

	public CaseLevelEnum getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(CaseLevelEnum caseLevel) {
		this.caseLevel = caseLevel;
	}

	public void setCaseType(CaseTypeEnum caseType) {
		this.caseType = caseType;
	}

	public Employee getHostEmployee() {
		return hostEmployee;
	}

	public void setHostEmployee(Employee hostEmployee) {
		this.hostEmployee = hostEmployee;
	}

	public Employee getCohostEmployee() {
		return cohostEmployee;
	}

	public void setCohostEmployee(Employee cohostEmployee) {
		this.cohostEmployee = cohostEmployee;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getWorkitem() {
		return workitem;
	}

	public void setWorkitem(String workitem) {
		this.workitem = workitem;
	}

	@Override
	public String toString() {
		return "CaseReq [caseNo=" + caseNo + ", caseType=" + caseType + ", caseLevel=" + caseLevel + ", hostEmployee="
				+ hostEmployee + ", cohostEmployee=" + cohostEmployee + ", startdate=" + startdate + ", enddate="
				+ enddate + ", workitem=" + workitem + ", creattime=" + creattime + "]";
	}

}
