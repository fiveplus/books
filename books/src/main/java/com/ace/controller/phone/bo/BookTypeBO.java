package com.ace.controller.phone.bo;

import java.io.Serializable;
import java.util.List;

import com.ace.entity.BookType;

public class BookTypeBO implements Serializable{
	private String name;
	private List<BookType> childs;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BookType> getChilds() {
		return childs;
	}
	public void setChilds(List<BookType> childs) {
		this.childs = childs;
	}
	
}
