package com.webcomm.oa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcomm.oa.data.CaseLevelEnum;
import com.webcomm.oa.data.CaseTypeEnum;
import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.domain.Employee;
import com.webcomm.oa.domain.Unit;
import com.webcomm.oa.repository.CaseReqRepository;
import com.webcomm.oa.repository.EmployeeRepository;
import com.webcomm.oa.repository.UnitRepository;
import com.webcomm.oa.searchbean.CaseReqSearchBean;

// TODO: Auto-generated Javadoc
/**
 * The Class CaseReqServiceImpl.
 */
//@Component("caseReqService")

@Service
@Transactional
public class CaseReqServiceImpl implements CaseReqService {

	/** The unit repository. */
	@Autowired
	private UnitRepository unitRepository;

	/** The employee repository. */
	@Autowired
	private EmployeeRepository employeeRepository;

	/** The case req repository. */

	@Autowired
	private CaseReqRepository caseReqRepository;

	/**
	 * Instantiates a new case req service impl.
	 */
	public CaseReqServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#listAllCaseReq()
	 */
	@Override
	public List<CaseReq> listAllCaseReq() {

		return caseReqRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#listAllUnit()
	 */
	@Override
	public List<Unit> listAllUnit() {
		// TODO Auto-generated method stub
		return unitRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#listAllEmployee()
	 */
	@Override
	public List<Employee> listAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.webcomm.oa.service.CaseReqService#createOrUpdateCaseReq(com.webcomm.oa.
	 * domain.CaseReq)
	 */
	@Override
	public void createOrUpdateCaseReq(CaseReq caseReq) {
		// TODO Auto-generated method stub

		if (caseReq == null) {
			throw new RuntimeException("資料有誤");
		}

		if (StringUtils.isBlank(caseReq.getCaseNo())) {

			caseReqRepository.save(caseReq);

		} else {
			Optional<CaseReq> cvo = caseReqRepository.findById(caseReq.getCaseNo());

			if (cvo.isPresent()) {

				CaseReq cget = cvo.get();
				cget.setCaseType(caseReq.getCaseType());
				cget.setCaseLevel(caseReq.getCaseLevel());
				cget.setCohostEmployee(caseReq.getCohostEmployee());
				cget.setHostEmployee(caseReq.getHostEmployee());
				cget.setStartdate(caseReq.getStartdate());
				cget.setEnddate(caseReq.getEnddate());
				cget.setWorkitem(caseReq.getWorkitem());
				caseReqRepository.save(cget);
			} else {
				throw new RuntimeException("查無資料");
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#queryCaseReqList(com.webcomm.oa.
	 * searchbean.CaseReqSearchBean)
	 */
	@Override
	public List<CaseReq> queryCaseReqList(CaseReqSearchBean caseReqSearchBean) {

		return caseReqRepository.findAll(getSpecification(caseReqSearchBean), new Sort(Sort.Direction.ASC, "caseNo"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#getOneCaseReq(java.lang.String)
	 */
	@Override
	public CaseReq getOneCaseReq(String caseNo) {
		// TODO Auto-generated method stub
		return caseReqRepository.getOne(caseNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#deleteCaseReqs(java.util.List)
	 */
	@Override
	public void deleteCaseReqs(List<String> caseNos) {
//		caseReqRepository.delete(caseNos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#deleteCaseReqs(java.lang.String)
	 */
	@Override
	public void deleteCaseReqs(String caseNo) {
		caseReqRepository.deleteById(caseNo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webcomm.oa.service.CaseReqService#deleteCaseNos(java.lang.String[])
	 */
	@Override
	public int deleteCaseNos(String[] caseNos) {

		return caseReqRepository.deleteCaseNos(caseNos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.webcomm.oa.service.CaseReqService#queryCaseReqPageable(com.webcomm.oa.
	 * searchbean.CaseReqSearchBean, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<CaseReq> queryCaseReqPageable(CaseReqSearchBean caseReqSearchBean, Pageable pageable) {

		return caseReqRepository.findAll(getSpecification(caseReqSearchBean), pageable);
	}

	/**
	 * Gets the specification.
	 *
	 * @param caseReqSearchBean the case req search bean
	 * @return the specification
	 */
	static Specification<CaseReq> getSpecification(CaseReqSearchBean caseReqSearchBean) {
		Specification<CaseReq> specification = new Specification<CaseReq>() {
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();

				if (!"0".equals(caseReqSearchBean.getCaseType()))
					predicates.add(criteriaBuilder.equal(root.get("caseType"),
							CaseTypeEnum.getEnum(Integer.parseInt(caseReqSearchBean.getCaseType()))));
				if (!"0".equals(caseReqSearchBean.getCaseLevel()))
					predicates.add(criteriaBuilder.equal(root.get("caseLevel"),
							CaseLevelEnum.getEnum(Integer.parseInt(caseReqSearchBean.getCaseLevel()))));
				if (!"0".equals(caseReqSearchBean.getHostUnit()))
					predicates.add(
							criteriaBuilder.equal(root.get("hostEmployee").get("unit").get("unitId").as(String.class),
									caseReqSearchBean.getHostUnit()));
				if (!"0".equals(caseReqSearchBean.getCohostUnit()))
					predicates.add(
							criteriaBuilder.equal(root.get("cohostEmployee").get("unit").get("unitId").as(String.class),
									caseReqSearchBean.getCohostUnit()));

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return specification;
	}

}
