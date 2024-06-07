package br.com.northon.qimatechfullstackdemo.controller.vo.request;

import java.io.Serializable;
import java.util.Objects;

public class ProductRequestVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Long categoryId;
	
	private Boolean available;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public ProductRequestVO(Long id, String name, String description, Double price, Long categoryId, Boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.available = available;
	}

	public ProductRequestVO() {
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, categoryId, description, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductRequestVO other = (ProductRequestVO) obj;
		return Objects.equals(available, other.available) && Objects.equals(categoryId, other.categoryId)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}
	
	
}
