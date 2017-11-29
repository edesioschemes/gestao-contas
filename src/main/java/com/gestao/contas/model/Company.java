package com.gestao.contas.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Nome da empresa obrigatória.")
	private String name;
	private Integer IdParent;

	@OneToMany(mappedBy = "company")
	private List<Payable> payables;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdParent() {
		return IdParent;
	}

	public void setIdParent(Integer idParent) {
		IdParent = idParent;
	}

	public List<Payable> getPayables() {
		return payables;
	}

	public void setPayables(List<Payable> payables) {
		this.payables = payables;
	}
}
