package com.ace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.dao.BookTypeMapper;
import com.ace.entity.BookType;

@Service("bookTypeService")
public class BookTypeService extends BaseService<BookType>{
	@Autowired
	private BookTypeMapper bookTypeMapper;
	
	public List<BookType> getParentList(){
		return bookTypeMapper.getParentList();
	}
	
	public List<BookType> getChildList(Integer id){
		return bookTypeMapper.getChildList(id);
	}
	
	public List<BookType> getAllChildList(){
		return bookTypeMapper.getAllChildList();
	}
	
}
