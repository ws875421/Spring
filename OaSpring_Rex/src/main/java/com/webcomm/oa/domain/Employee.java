package com.webcomm.oa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", unit=" + unit + ", empName=" + empName + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMP_ID")
	private String empId;

	@ManyToOne
	@JoinColumn(name = "UNIT_ID")
	private Unit unit;

	@Column(name = "EMP_NAME")
	private String empName;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hostEmployeeVO")
//	@OrderBy("CASE_NO asc")
//	private Set<CaseReqVO> hostCaseReqs = new HashSet<>();
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cohostEmployeeVO")
//	@OrderBy("CASE_NO asc")
//	private Set<CaseReqVO> cohostCaseReqs = new HashSet<>();

	public Employee() {
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

//	public Set<CaseReqVO> getHostCaseReqs() {
//		return hostCaseReqs;
//	}
//
//	public void setHostCaseReqs(Set<CaseReqVO> hostCaseReqs) {
//		this.hostCaseReqs = hostCaseReqs;
//	}
//
//	public Set<CaseReqVO> getCohostCaseReqs() {
//		return cohostCaseReqs;
//	}
//
//	public void setCohostCaseReqs(Set<CaseReqVO> cohostCaseReqs) {
//		this.cohostCaseReqs = cohostCaseReqs;
//	}

}
