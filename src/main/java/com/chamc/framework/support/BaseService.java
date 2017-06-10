package com.chamc.framework.support;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T> {

	public abstract JpaRepository<T, Long> baseRepository();
	
	public T save(T entity) {
		return this.baseRepository().save(entity);
	}
	
	public void delete(T entity) {
		this.baseRepository().delete(entity);
	}
	
	public void deleteAll() {
		this.baseRepository().deleteAll();
	}
	
	public T findOne(Long id) {
		return this.baseRepository().findOne(id);
	}
	
	public List<T> findAll() {
		return this.baseRepository().findAll();
	}
	
	public Page<T> findPage(Pageable page) {
		return this.baseRepository().findAll(page);
	}
	
	public Page<T> findPage(int page, int size) {
		return this.baseRepository().findAll(new PageRequest(page, size));
	}
	
	public Long count() {
		return this.baseRepository().count();
	}
}
