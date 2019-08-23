package com.webcomm.oa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.webcomm.oa.config.OaSpringRexApplication;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OaSpringRexApplication.class)
public class EntityManagerTest {

	@Autowired
	private EntityManagerFactory emf;

	@Test
	@Rollback(value = false)
	public void entityManagerWithCriteriaTest() {
		List<CaseReq> list = null;

		//先從 EntityManagerFactory 取得 EntityManager
        EntityManager entityManager = emf.createEntityManager();
        //取得交易物件
        EntityTransaction etx =entityManager.getTransaction();
        //開始交易
        etx.begin();
        //從entityManager 取得 CriteriaBuilder
        CriteriaBuilder criteriaBuilder =entityManager.getCriteriaBuilder();
        //查詢 CaseReq 先建立一個CriteriaQuery
        CriteriaQuery<CaseReq> query = criteriaBuilder.createQuery(CaseReq.class);
       
        List<Predicate> predicateList = new ArrayList<Predicate>();
        
        //取得Root<CaseReq> 主要是要取得欄位做條件搜索
        Root<CaseReq> rootEntity = query.from(CaseReq.class);
        
        Subquery<Employee> subquery = query.subquery(Employee.class);
		Root<Employee> emp = subquery.from(Employee.class);
		Unit unitVO1 = new Unit();
		unitVO1.setUnitId("E0001");
		subquery.select(emp).where(criteriaBuilder.equal(emp.get("unit"), unitVO1));
		Predicate predicate3 = criteriaBuilder.in(rootEntity.get("hostEmployee")).value(subquery);
		predicateList.add(predicate3);
        
        
        //把查詢條件加入query
        query =query.select(rootEntity).where(predicateList.toArray(new Predicate[predicateList.size()]));
        //使用CaseReq Manager 查找
        list = entityManager.createQuery(query).getResultList();
        System.out.println(list);
        etx.commit();
		
		
	}
}
