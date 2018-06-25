package com.ace.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_book")
public class Book implements Serializable{
	@Id
	private Integer id;
	@Column
	private String name;
	@Column
	private String author;
	@Column
	private String copyRight;
	@Column
	private Double price;
	@Column
	private String publicHouse;
	@Column
	private Long publicTime;
	@Column
	private String info;
	@Column
	private Long createTime;
	@Column
	private Long readCount;
	@Column
	private String recommend;
	@Column
	private String authorInfo;
	@Column
	private Integer typeId;
	@Column
	private String picture;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublicHouse() {
		return publicHouse;
	}
	public void setPublicHouse(String publicHouse) {
		this.publicHouse = publicHouse;
	}
	public Long getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Long publicTime) {
		this.publicTime = publicTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getReadCount() {
		return readCount;
	}
	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getAuthorInfo() {
		return authorInfo;
	}
	public void setAuthorInfo(String authorInfo) {
		this.authorInfo = authorInfo;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	
}
