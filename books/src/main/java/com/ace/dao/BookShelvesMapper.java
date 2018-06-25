package com.ace.dao;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.BookShelves;
import com.github.abel533.mapper.Mapper;

public interface BookShelvesMapper extends Mapper<BookShelves>{
	public BookShelves getBookShelvesByUserIdAndBookId(@Param("userId") Integer userId,@Param("bookId") Integer bookId);
}
