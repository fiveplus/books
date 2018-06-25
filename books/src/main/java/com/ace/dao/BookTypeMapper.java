package com.ace.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.BookType;
import com.github.abel533.mapper.Mapper;

public interface BookTypeMapper extends Mapper<BookType>{
	public List<BookType> getParentList();
	public List<BookType> getChildList(@Param("id") Integer id);
	public List<BookType> getAllChildList();
}
