package com.ace.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tbl_bookshelves")
public class BookShelves implements Serializable{
	@Id
	private Integer id;
	@Column
	private Integer bookId;
	@Column
	private Integer bookfileId;
	@Column
	private Long createTime;
	@Column
	private Integer userId;
	@Column
	private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getBookfileId() {
		return bookfileId;
	}
	public void setBookfileId(Integer bookfileId) {
		this.bookfileId = bookfileId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
