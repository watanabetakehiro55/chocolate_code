package jp.co.internous.chocolate.model.domain;

import java.sql.Timestamp;

public class MstCategory {
	
	private int id;
	private String categoryName;
	private String categoryDescription;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
}
