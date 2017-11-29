package com.gestao.contas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gestao.contas.model.Company;
import com.gestao.contas.service.CompanyService;

@RestController 
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public ModelAndView findAll() {

		ModelAndView modelAndView = new ModelAndView("Company");

		modelAndView.addObject("companies", companyService.findAll());

		return modelAndView;
	}

	@GetMapping("/add")
	public ModelAndView add(Company company) {

		ModelAndView mv = new ModelAndView("/CompanyCRUD");
		mv.addObject("company", company);

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id) {

		return add(companyService.findOne(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {

		companyService.delete(id);

		return findAll();
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Company company, BindingResult result) {

		if (result.hasErrors()) {
			return add(company);
		}

		companyService.save(company);

		return findAll();
	}

}
