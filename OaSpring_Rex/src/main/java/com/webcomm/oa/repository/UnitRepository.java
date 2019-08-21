package com.webcomm.oa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webcomm.oa.domain.Unit;

/**
 * 單位JPA
 * 
 * @author user
 *
 */
@Repository
public interface UnitRepository extends JpaRepository<Unit, String> {

}
