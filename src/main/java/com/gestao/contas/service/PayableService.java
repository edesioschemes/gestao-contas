package com.gestao.contas.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestao.contas.model.Payable;
import com.gestao.contas.model.Payables;
import com.gestao.contas.model.StatusType;

@Service
public class PayableService {

	@Autowired
	private Payables payables;
	
    public List<Payable> findAll() {
        return payables.findAll();
    }
     
    public Payable findOne(Integer id) {
        return payables.findOne(id);
    }
     
    public Payable save(Payable payable) {
        return payables.saveAndFlush(payable);
    }
     
    public void delete(Integer id) {
    	payables.delete(id);
    }		
    
    public Payable pay(Payable payable) {
    	
    	Date today = new Date();
    	
    	Payable paid = payables.findOne(payable.getId());
    	
    	if (paid.getStatusType().equals(StatusType.Pagar) && paid.getPayday().getTime() >= today.getTime()) {
    		paid.setStatusType(StatusType.Pago);
    	}
    	
    	return paid;
    }
    
    public Integer getPaidValue() {
    	return payables.listPaidValue();
    }
	
    public Integer getNotPaydValue() {
    	return payables.listNotPayValue();
    }  
    
    public List<Payable> listByCompany(Integer companyId){
    	return payables.listByCompany(companyId);
    }
}
