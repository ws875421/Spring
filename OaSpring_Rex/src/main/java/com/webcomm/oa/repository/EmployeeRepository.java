package com.webcomm.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webcomm.oa.domain.Employee;

/**
 * 員工JPA
 * 
 * @author user
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
