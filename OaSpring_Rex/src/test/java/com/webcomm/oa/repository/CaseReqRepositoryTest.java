package com.webcomm.oa.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.webcomm.oa.config.OaSpringRexApplication;
import com.webcomm.oa.domain.CaseReq;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OaSpringRexApplication.class)
@Transactional
public class CaseReqRepositoryTest {

	@Autowired
	CaseReqRepository caseReqRepository;
	@Autowired
	UnitRepository unitRepository;

//	@Test
//	@Rollback(value = false)
//	public void saveUserTest() {
//		Employee employee = new Employee(); // 部門POJO
//		employee.setEmpId("E0001");
//		CaseReq caseReq = new CaseReq();
//		caseReq.setCaseNo("201900049");
//		caseReq.setCaseType(CaseTypeEnum.Default_ALL);
//		caseReq.setCaseLevel(CaseLevelEnum.Default_ALL);
//		caseReq.setHostEmployee(employee);
//		caseReq.setCohostEmployee(employee);
//		caseReq.setStartdate(java.sql.Date.valueOf("2005-01-01"));
//		caseReq.setEnddate(java.sql.Date.valueOf("2005-03-03"));
//		caseReq.setWorkitem("工作項目7");
//		caseReqRepository.save(caseReq);
//	}

//	@Test
//	@Rollback(value = false)
//	public void findUserTest() {
//		CaseReq caseReq = caseReqRepository.getOne("201900047");
//		Assert.assertEquals(caseReq.getWorkitem(),"工作項目6");
//	
//	}

//    @Test
//    @Rollback(value = false)
//    public void modifyUserTest(){
//    	CaseReq caseReq =caseReqRepository.getOne("201900047");
//    	caseReq.setWorkitem("更改測試");
//    }

//	@Test
//	@Rollback(value = false)
//	public void deleteUserTest() {
//		String[] casenos = { "201900019", "201900020" };
////		System.out.println(casenos);
//		caseReqRepository.deleteCaseNos(casenos);
//	}

//	@Test
//	@Rollback(value = false)
//	public void getAll() {
//		System.out.println(caseReqRepository.findAll());
//	}
//	@Test
//	@Rollback(value = false)
//	public void getAllunit() {
//		System.out.println(caseReqRepository.findAll().size());
//	}

//	@Test
//	@Rollback(value = false)
//	public void getAll2() {
//		int page = 0, size = 10;
//		Sort sort = new Sort(Direction.DESC, "caseNo");
//		Pageable pageable = new PageRequest(page, size, sort);
//		System.out.println("##"+caseReqRepository.findAll(pageable));
//	}

//	@Test
//	@Rollback(value = false)
//	public void orderTest() {
//		Sort sort = new Sort(Sort.Direction.DESC, "caseNo");
//		List<CaseReq> details = caseReqRepository.findAll(sort);
//		details.stream().map(CaseReq::getCaseNo).forEach(System.out::println);
//
//		
//	}

//	@Test
//	@Rollback(value = false)
//	public void pageTest() {
//		Page<CaseReq> details = caseReqRepository.findAll(PageRequest.of(0, 10));
//		details.get().map(CaseReq::getCaseNo).forEach(System.out::println);
//	}

//	@Test
//	@Rollback(value = false)
//	public void pageOrder() {
//		Sort sort = new Sort(Sort.Direction.ASC, "caseNo");
//		Page<CaseReq> details = caseReqRepository.findAll(PageRequest.of(0, 10,sort));
//		details.get().map(CaseReq::getCaseNo).forEach(System.out::println);
//	}

}
