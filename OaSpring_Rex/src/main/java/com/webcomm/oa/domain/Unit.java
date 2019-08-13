package com.webcomm.oa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNIT")
public class Unit implements Serializable {

	@Id
	@Column(name = "UNIT_ID")
	private String unitId;

	@Column(name = "UNIT_NAME")
	private String unitName;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "unitVO")
//	@OrderBy("EMP_ID asc")
//	private Set<EmployeeVO> employees = new HashSet<>();

	public Unit() {
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName + "]";
	}

//	public Set<EmployeeVO> getEmployees() {
//		return this.employees;
//	}
//
//	public void setEmployees(Set<EmployeeVO> employees) {
//		this.employees = employees;
//	}

}
