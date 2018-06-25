package com.ace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.dao.BookFileMapper;
import com.ace.entity.BookFile;

@Service("bookFileService")
public class BookFileService extends BaseService<BookFile>{
	@Autowired
	private BookFileMapper bookFileMapper;
	
	public BookFile queryBookFileByBookId(Integer bookId){
		return bookFileMapper.queryBookFileByBookId(bookId);
	}
	
}
