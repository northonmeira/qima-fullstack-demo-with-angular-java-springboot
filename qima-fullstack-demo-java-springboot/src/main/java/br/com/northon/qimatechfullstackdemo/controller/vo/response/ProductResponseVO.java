package br.com.northon.qimatechfullstackdemo.controller.vo.response;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

public class ProductResponseVO extends RepresentationModel<ProductResponseVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String description;
	
	private Double price;
	
	private Boolean available;
	
	private Long categoryId;
	
	private String categoryName;
	

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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		return Objects.hash(available, categoryId, categoryName, description, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductResponseVO other = (ProductResponseVO) obj;
		return Objects.equals(available, other.available) && Objects.equals(categoryId, other.categoryId)
				&& Objects.equals(categoryName, other.categoryName) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(price, other.price);
	}

	public ProductResponseVO(Long id, String name, String description, Double price, Long categoryId, String categoryName,
			Boolean available) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.available = available;
	}

	public ProductResponseVO() {
		super();
	}


}
