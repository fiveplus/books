package com.ace.dao;

import org.apache.ibatis.annotations.Param;

import com.ace.entity.BookFile;
import com.github.abel533.mapper.Mapper;

public interface BookFileMapper extends Mapper<BookFile>{
	public BookFile queryBookFileByBookId(@Param("bookId") int bookId);
}
