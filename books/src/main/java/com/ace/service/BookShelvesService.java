package com.ace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.dao.BookShelvesMapper;
import com.ace.entity.BookShelves;

@Service("bookShelvesService")
public class BookShelvesService extends BaseService<BookShelves>{
	@Autowired
	private BookShelvesMapper bookShelvesMapper;
	
	public BookShelves getBookShelvesByUserIdAndBookId(Integer userId,Integer bookId){
		return bookShelvesMapper.getBookShelvesByUserIdAndBookId(userId,bookId);
	}
	
	
}
