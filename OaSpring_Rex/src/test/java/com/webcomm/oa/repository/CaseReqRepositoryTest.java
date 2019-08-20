package com.webcomm.oa.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.webcomm.oa.config.OaSpringRexApplication;
import com.webcomm.oa.data.CaseLevelEnum;
import com.webcomm.oa.data.CaseTypeEnum;
import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.searchbean.CaseReqSearchBean;

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

	@Test
	@Rollback(value = false)
	public void getAll2() {
		CaseReqSearchBean caseReqSearchBean = new CaseReqSearchBean();
		caseReqSearchBean.setCaseLevel("0");
		caseReqSearchBean.setCaseType("0");
		caseReqSearchBean.setHostUnit("0"); // 2019-04-01
		caseReqSearchBean.setCohostUnit("0");// 2019-05-31
		caseReqSearchBean.setStart(new GregorianCalendar(2019, 3, 28).getTime());
		caseReqSearchBean.setEnd(new GregorianCalendar(2019, 4, 31).getTime());
		System.out.println("#caseReqSearchBean : " + caseReqSearchBean);

		for (CaseReq c : caseReqRepository.findAll(getSpecification(caseReqSearchBean))) {
			System.out.println(c);
		}

	}

	static Specification<CaseReq> getSpecification(CaseReqSearchBean caseReqSearchBean) {
		Specification<CaseReq> specification = new Specification<CaseReq>() {
			@SuppressWarnings("unchecked")
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if (!"0".equals(caseReqSearchBean.getCaseType())) {
					predicates.add(criteriaBuilder.equal(root.get("caseType"),
							CaseTypeEnum.getEnum(Integer.parseInt(caseReqSearchBean.getCaseType()))));
				}
				if (!"0".equals(caseReqSearchBean.getCaseLevel())) {
					predicates.add(criteriaBuilder.equal(root.get("caseLevel"),
							CaseLevelEnum.getEnum(Integer.parseInt(caseReqSearchBean.getCaseLevel()))));
				}
				if (!"0".equals(caseReqSearchBean.getHostUnit())) {
					predicates.add(criteriaBuilder.equal(root.get("hostEmployee").get("unit").get("unitId"),
							caseReqSearchBean.getHostUnit()));
				}
				if (!"0".equals(caseReqSearchBean.getCohostUnit())) {
					predicates.add(criteriaBuilder.equal(root.get("cohostEmployee").get("unit").get("unitId"),
							caseReqSearchBean.getCohostUnit()));
				}
				if (!(null == caseReqSearchBean.getStart())) {
					System.out.println();
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creattime").as(Date.class),
							caseReqSearchBean.getStart()));
				}
				if (!(null == caseReqSearchBean.getEnd())) {
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("creattime").as(Date.class),
							caseReqSearchBean.getEnd()));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return specification;
	}

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

//	public static void main(String[] args) {
//		Calendar calendar = new GregorianCalendar(2019, 3, 28);
//		Date date = calendar.getTime();
//		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
//		System.out.println("2007 Christmas is:" + format.format(date));
//		System.out.println("2007 Christmas is:" + date);
//	}
}
