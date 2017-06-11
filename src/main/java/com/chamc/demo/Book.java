package com.chamc.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.chamc.framework.support.BaseEntity;

@Entity
public class Book extends BaseEntity {
	
	@Id @GeneratedValue
	private Long id;
	private String name;
	private String express;
	private Float price;
	
	@Column(name = "description")
	private String desc;
	
	@ManyToOne
	private Express exp;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Express getExp() {
		return exp;
	}

	public void setExp(Express exp) {
		this.exp = exp;
	}

}
