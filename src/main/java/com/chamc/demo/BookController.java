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
		/*this.setPreQueryListener((model, page) -> {
			Long count = this.bookService.count();
			if(count < 10) {
				Book book = new Book();
				book.setName("spring实战4.x");
				book.setPrice(45.5F);
				book.setExpress("人民邮电出版社");
				book.setDesc("很好的一本书，值得一读！");
				this.bookService.save(book);
			}
			return GO_ON;
		});*/
	}
	
	@Override
	public BaseService<Book> baseService() {
		return bookService;
	}

}
