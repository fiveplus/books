package com.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.Book;
import com.github.abel533.mapper.Mapper;

public interface BookMapper extends Mapper<Book>{
	public List<Book> getBookList(@Param("num") int num);
	public List<Book> getBookListByTypeId(@Param("typeId") int typeId);
	public List<Book> getBookListByParentId(@Param("parentId") int parentId);
	public List<Book> getBookListLikeName(@Param("name") String name);
	public List<Book> getBookListOrderByReadCount();
	public List<Book> getBookListByNoPrice();
	public List<Book> getBookListByCreateTime();
}
