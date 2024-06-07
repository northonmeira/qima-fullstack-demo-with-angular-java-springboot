package br.com.northon.qimatechfullstackdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.northon.qimatechfullstackdemo.controller.vo.request.ProductRequestVO;
import br.com.northon.qimatechfullstackdemo.controller.vo.response.ProductResponseVO;
import br.com.northon.qimatechfullstackdemo.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping(value= "/{id}")
	public ProductResponseVO findById(@PathVariable(value="id") Long id) {
		return productService.findById(id);
	}
	
	@GetMapping
	public List<ProductResponseVO> findAll() {
		return productService.findAll();
	}
	
	@PostMapping
	public ProductResponseVO createProduct(@RequestBody ProductRequestVO productRequest) {
		return productService.createProduct(productRequest);
	}
	
	@PutMapping
	public ProductResponseVO updateProduct(@RequestBody ProductRequestVO productRequest) {
		return productService.updateProduct(productRequest);
	}
	
	@DeleteMapping(value= "/{id}")
	public void deleteProduct(@PathVariable(value="id") Long id) {
		productService.deleteProduct(id);
	}
}
