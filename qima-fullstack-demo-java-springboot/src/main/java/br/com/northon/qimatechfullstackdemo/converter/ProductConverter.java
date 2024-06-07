package br.com.northon.qimatechfullstackdemo.converter;

import org.springframework.stereotype.Component;

import br.com.northon.qimatechfullstackdemo.controller.vo.response.ProductResponseVO;
import br.com.northon.qimatechfullstackdemo.model.Product;

@Component
public class ProductConverter {
	
	public ProductResponseVO entityToResponse(Product entity) {
		ProductResponseVO response = new ProductResponseVO();
		
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setDescription(entity.getDescription());
		response.setPrice(entity.getPrice());
		response.setAvailable(entity.getAvailable());
		response.setCategoryId(entity.getCategory().getId());
		response.setCategoryName(entity.getCategory().getName());
		
		return response;
	}
	
//	public Product requestToEntity(ProductRequest request) {
//		Product entity = new Product();
//		
//		entity.setId(request.getId());
//		entity.setName(request.getName());
//		entity.setDescription(request.getDescription());
//		entity.setPrice(request.getPrice());
//		entity.setAvailable(request.getAvailable());
//		
//		return entity;
//	}

}
