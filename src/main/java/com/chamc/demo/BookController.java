package com.chamc.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chamc.framework.support.BaseController;
import com.chamc.framework.support.BaseService;

@Controller
@RequestMapping("/book")
public class BookController extends BaseController<Book> {

	@Autowired
	private BookService bookService;
	
	public BookController() {
	}
	
	@Override
	public BaseService<Book> baseService() {
		return bookService;
	}

}
