package com.gestao.contas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.contas.model.Companies;
import com.gestao.contas.model.Company;

@Service
public class CompanyService {

	@Autowired
	private Companies companies;

	public List<Company> findAll() {
		return companies.findAll();
	}

	public Company findOne(Integer id) {
		return companies.findOne(id);
	}

	public Company save(Company company) {
		return companies.saveAndFlush(company);
	}

	public void delete(Integer id) {
		companies.delete(id);
	}

}
