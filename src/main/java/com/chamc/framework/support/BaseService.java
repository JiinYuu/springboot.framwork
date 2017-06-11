package com.chamc.framework.support;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T> {

	public abstract JpaRepository<T, Long> baseRepository();
	
	@Transactional
	public T save(T entity) {
		return this.baseRepository().save(entity);
	}
	
	@Transactional
	public void delete(T entity) {
		this.baseRepository().delete(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		this.baseRepository().delete(id);
	}
	
	@Transactional
	public void deleteAll() {
		this.baseRepository().deleteAll();
	}
	
	@Transactional
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
