package com.webcomm.oa.serviceImp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webcomm.oa.data.CaseLevelEnum;
import com.webcomm.oa.data.CaseTypeEnum;
import com.webcomm.oa.domain.CaseReq;
import com.webcomm.oa.domain.Employee;
import com.webcomm.oa.domain.Unit;
import com.webcomm.oa.exception.CustomGenericException;
import com.webcomm.oa.repository.CaseReqRepository;
import com.webcomm.oa.repository.EmployeeRepository;
import com.webcomm.oa.repository.UnitRepository;
import com.webcomm.oa.searchbean.CaseReqSearchBean;
import com.webcomm.oa.service.CaseReqService;

/**
 * 承辦案件Service實做
 * 
 * @author user
 *
 */
@Service
@Transactional
public class CaseReqServiceImp implements CaseReqService {

	private static final Logger LOG = LoggerFactory.getLogger(CaseReqServiceImp.class);

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
	public CaseReqServiceImp() {
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
	public void createOrUpdateCaseReq(CaseReq caseReq) throws Exception {
		// TODO Auto-generated method stub

		if (caseReq == null) {
			throw new CustomGenericException("createOrUpdateCaseReq", "資料有誤");
		}
		if (StringUtils.isBlank(caseReq.getCaseNo())) {
			caseReq.setCreattime(new Timestamp(System.currentTimeMillis()));
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
				throw new CustomGenericException("createOrUpdateCaseReq", "查無資料");
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
	public void deleteCaseReqs(String[] caseNos) throws Exception {

		try {
			List<CaseReq> list = new ArrayList<>();
			for (String s : caseNos) {
				list.add(caseReqRepository.getOne(s));
			}
			
			caseReqRepository.deleteAll(list);
		} catch (Exception e) {
			throw new CustomGenericException("1", "資料不存在");
		}

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
	public int deleteCaseNos(String[] caseNos) throws Exception {

		int i = caseReqRepository.deleteCaseNos(caseNos);
		if (i == 0) {
			throw new CustomGenericException("1", "資料不存在");
		}

		return i;
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
				if (null != caseReqSearchBean.getStart()) {
					LOG.info("## start:{}", caseReqSearchBean.getStart());
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creattime").as(Date.class),
							caseReqSearchBean.getStart()));
				}
				if (null != caseReqSearchBean.getEnd()) {
					LOG.info("## end:{}", caseReqSearchBean.getEnd());
					predicates.add(
							criteriaBuilder.lessThan(root.get("creattime").as(Date.class), caseReqSearchBean.getEnd()));
				}

				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		return specification;
	}

}
