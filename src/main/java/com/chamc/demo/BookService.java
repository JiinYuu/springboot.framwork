package com.chamc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.chamc.framework.support.BaseService;

@Service
public class BookService extends BaseService<Book> {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public JpaRepository<Book, Long> baseRepository() {
		return bookRepository;
	}

}
