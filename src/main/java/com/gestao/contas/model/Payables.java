package com.gestao.contas.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Payables extends JpaRepository<Payable, Integer> {

	@Query(value = "select sum(p.value) from payable p where p.status_type = 'Pago'", nativeQuery = true)
	Integer listPaidValue();
	
	@Query(value = "select sum(p.value) from payable p where p.status_type = 'Pagar'", nativeQuery = true)
	Integer listNotPayValue();	
	
	@Query(value = "select p from Payable p where p.company.id = :company_id")
	List<Payable> listByCompany(@Param("company_id") Integer company_id);
}
