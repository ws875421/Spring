package com.webcomm.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.webcomm.oa.domain.CaseReq;

@Repository
public interface CaseReqRepository extends JpaRepository<CaseReq, String>, JpaSpecificationExecutor<CaseReq>,CrudRepository<CaseReq, String> {
	
	@Modifying
	@Query("delete CaseReq where CASE_NO in ?1")
	public int deleteCaseNos(String[] caseNos);

}
