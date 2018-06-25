package com.ace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ace.dao.BookMapper;
import com.ace.entity.Book;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("bookService")
public class BookService extends BaseService<Book>{
	@Autowired
	private BookMapper bookMapper;
	
	public List<Book> getBookList(int num){
		return bookMapper.getBookList(num);
	}
	
	public PageInfo<Book> getBookListByTypeId(int typeId,int page,int pageSize){
		PageHelper.startPage(page,pageSize);
		List<Book> list = bookMapper.getBookListByTypeId(typeId);
		PageInfo<Book> pu = new PageInfo<Book>(list);
		return pu;
	}
	
	public PageInfo<Book> getBookListByParentId(int parentId,int page,int pageSize){
		PageHelper.startPage(page,pageSize);
		List<Book> list = bookMapper.getBookListByParentId(parentId);
		PageInfo<Book> pu = new PageInfo<Book>(list);
		return pu;
	}
	
	public List<Book> getBookListLikeName(String name){
		return bookMapper.getBookListLikeName(name);
	}
	
	public PageInfo<Book> getBookListOrderByReadCount(int page,int pageSize){
		PageHelper.startPage(page, pageSize);
		List<Book> list = bookMapper.getBookListOrderByReadCount();
		PageInfo<Book> pu = new PageInfo<Book>(list);
		return pu; 
	}
	
	public PageInfo<Book> getBookListByNoPrice(int page,int pageSize){
		PageHelper.startPage(page, pageSize);
		List<Book> list = bookMapper.getBookListByNoPrice();
		PageInfo<Book> pu = new PageInfo<Book>(list);
		return pu; 
	}
	
	public PageInfo<Book> getBookListByCreateTime(int page,int pageSize){
		PageHelper.startPage(page, pageSize);
		List<Book> list = bookMapper.getBookListByCreateTime();
		PageInfo<Book> pu = new PageInfo<Book>(list);
		return pu; 
	}
	
}
