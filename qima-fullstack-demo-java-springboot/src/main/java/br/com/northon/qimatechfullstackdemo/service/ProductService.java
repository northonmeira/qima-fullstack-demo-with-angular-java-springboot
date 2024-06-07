package br.com.northon.qimatechfullstackdemo.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.northon.qimatechfullstackdemo.controller.ProductController;
import br.com.northon.qimatechfullstackdemo.controller.vo.request.ProductRequestVO;
import br.com.northon.qimatechfullstackdemo.controller.vo.response.ProductResponseVO;
import br.com.northon.qimatechfullstackdemo.converter.ProductConverter;
import br.com.northon.qimatechfullstackdemo.exceptions.RequiredCategoryIdIsNullException;
import br.com.northon.qimatechfullstackdemo.exceptions.RequiredObjectIsNullException;
import br.com.northon.qimatechfullstackdemo.exceptions.ResourceNotFoundException;
import br.com.northon.qimatechfullstackdemo.mapper.DozerMapper;
import br.com.northon.qimatechfullstackdemo.model.Product;
import br.com.northon.qimatechfullstackdemo.repository.CategoryRepository;
import br.com.northon.qimatechfullstackdemo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductConverter productConverter;
	
	private Logger logger = Logger.getLogger(ProductService.class.getName());

	public ProductResponseVO findById(Long id) {
		
		logger.info("finding one product");
		
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
		
		var response = productConverter.entityToResponse(entity);
		
		response.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		
		return response;
		
	}

	public List<ProductResponseVO> findAll() {
		
		logger.info("finding all products");
		
		var products = productRepository.findAll();
		
		List<ProductResponseVO> response = new ArrayList<>();
		
		products.stream().forEach(p -> {
			var productResponse = productConverter.entityToResponse(p);
			productResponse.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel());
			response.add(productResponse);
		});
		
		return response;
		
	}

	public ProductResponseVO createProduct(ProductRequestVO productRequest) {

		if(productRequest == null) throw new RequiredObjectIsNullException();
		
		logger.info("creating product");
		
		if(productRequest.getCategoryId() == null) 
			throw new RequiredCategoryIdIsNullException();
		
		var category = categoryRepository.findById(productRequest.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
		
		var entity = DozerMapper.parseObject(productRequest, Product.class);	
		
		entity.setCategory(category);
		
		var response = productConverter.entityToResponse(productRepository.save(entity));
		
		response.add(linkTo(methodOn(ProductController.class).createProduct(productRequest)).withSelfRel());
		
		return response;
		
	}

	public ProductResponseVO updateProduct(ProductRequestVO productRequest) {

		if(productRequest == null) throw new RequiredObjectIsNullException();
		
		logger.info("updating product");
		
		var entity = productRepository.findById(productRequest.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No product found for this ID!"));
		
		var category = categoryRepository.findById(productRequest.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("No category found for this ID!"));
		
		entity.setName(productRequest.getName());
		entity.setDescription(productRequest.getDescription());
		entity.setPrice(productRequest.getPrice());
		entity.setAvailable(productRequest.getAvailable());
		entity.setCategory(category);
		
		var response = productConverter.entityToResponse(productRepository.save(entity));
		
		response.add(linkTo(methodOn(ProductController.class).updateProduct(productRequest)).withSelfRel());
				
		return response;
		
	}

	public void deleteProduct(Long id) {
		
		logger.info("deleting product");
		
		var entity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No product found for this ID!"));
		
		productRepository.delete(entity);
		
	}

	
}
