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

import br.com.northon.qimatechfullstackdemo.controller.vo.request.CategoryRequestVO;
import br.com.northon.qimatechfullstackdemo.controller.vo.response.CategoryResponseVO;
import br.com.northon.qimatechfullstackdemo.service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(value= "/{id}")
	public CategoryResponseVO findById(@PathVariable(value="id") Long id) {
		return categoryService.findById(id);
	}
	
	@GetMapping
	public List<CategoryResponseVO> findAll() {
		return categoryService.findAll();
	}
	
	@PostMapping
	public CategoryResponseVO createCategory(@RequestBody CategoryRequestVO categoryVO) {
		return categoryService.createCategory(categoryVO);
	}
	
	@PutMapping
	public CategoryResponseVO updateCategory(@RequestBody CategoryRequestVO categoryVO) {
		return categoryService.updateCategory(categoryVO);
	}
	
	@DeleteMapping(value= "/{id}")
	public void deleteCategory(@PathVariable(value="id") Long id) {
		categoryService.deleteCategory(id);
	}
	
}
