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

import com.gestao.contas.model.Payable;
import com.gestao.contas.model.StatusType;
import com.gestao.contas.service.CompanyService;
import com.gestao.contas.service.PayableService;

@RestController
@RequestMapping("/payable")
public class PayableController {

	@Autowired
	private PayableService payableService;
	@Autowired
	private CompanyService companyService;

	@GetMapping("/hist")
	public ModelAndView HistPayables() {

		ModelAndView modelAndView = new ModelAndView("HistPayable");

		modelAndView.addObject("payables", payableService.findAll());
		modelAndView.addObject("sumPaid", payableService.getPaidValue());
		modelAndView.addObject("sumNotPay", payableService.getNotPaydValue());

		return modelAndView;
	}

	@GetMapping("{companyId}")
	public ModelAndView payablesByCompany(@PathVariable("companyId") Integer companyId) {

		ModelAndView modelAndView = new ModelAndView("Payables");

		if (companyId == 0) {
			modelAndView.addObject("payables", payableService.findAll());
		} else {
			modelAndView.addObject("payables", payableService.listByCompany(companyId));
		}
		modelAndView.addObject("sumPaid", payableService.getPaidValue());
		modelAndView.addObject("sumNotPay", payableService.getNotPaydValue());
		modelAndView.addObject("companies", companyService.findAll());

		return modelAndView;
	}

	@GetMapping("/add")
	public ModelAndView add(Payable payable) {

		ModelAndView mv = new ModelAndView("/PayablesCRUD");
		mv.addObject("payable", payable);
		mv.addObject("companies", companyService.findAll());

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id) {

		return add(payableService.findOne(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id) {

		payableService.delete(id);

		return payablesByCompany(0);
	}

	@GetMapping("/pay/{id}")
	public ModelAndView pay(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("PaidView");

		modelAndView.addObject("payable", payableService.findOne(id));

		return modelAndView;
	}

	@PostMapping("/pay")
	public ModelAndView pay(@Valid Payable payable, BindingResult result) {

		if (result.hasErrors()) {
			return add(payable);
		}

		if (payable.getPayday() != null
				&& (payable.getStatusType() == null || payable.getStatusType().equals(StatusType.Pagar))) {
			payable.setStatusType(StatusType.Pago);
			payableService.save(payable);
		} else {

		}

		return payablesByCompany(0);
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Payable payable, BindingResult result) {

		if (result.hasErrors()) {
			return add(payable);
		}
		if (payable.getStatusType() == null) {
			payable.setStatusType(StatusType.Pagar);
		}
		payableService.save(payable);

		return payablesByCompany(0);
	}

}
