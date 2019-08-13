package com.webcomm.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webcomm.oa.domain.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
