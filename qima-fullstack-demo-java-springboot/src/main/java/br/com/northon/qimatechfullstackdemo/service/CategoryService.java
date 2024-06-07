package br.com.northon.qimatechfullstackdemo.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.northon.qimatechfullstackdemo.controller.CategoryController;
import br.com.northon.qimatechfullstackdemo.controller.vo.request.CategoryRequestVO;
import br.com.northon.qimatechfullstackdemo.controller.vo.response.CategoryResponseVO;
import br.com.northon.qimatechfullstackdemo.exceptions.RequiredObjectIsNullException;
import br.com.northon.qimatechfullstackdemo.exceptions.ResourceNotFoundException;
import br.com.northon.qimatechfullstackdemo.mapper.DozerMapper;
import br.com.northon.qimatechfullstackdemo.model.Category;
import br.com.northon.qimatechfullstackdemo.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Logger logger = Logger.getLogger(CategoryService.class.getName());

	public CategoryResponseVO findById(Long id) {
		
		logger.info("finding one category");
		
		var entity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("category not found!"));
		
		var response = DozerMapper.parseObject(entity, CategoryResponseVO.class);
		
		response.add(linkTo(methodOn(CategoryController.class).findById(id)).withSelfRel());
		
		return response;
	}

	public List<CategoryResponseVO> findAll() {
		
		logger.info("finding all categorys");
		
		var categorys = categoryRepository.findAll();
		
		List<CategoryResponseVO> response = new ArrayList<>();
		
		categorys.stream().forEach(c -> {
			var category = DozerMapper.parseObject(c, CategoryResponseVO.class);
			category.add(linkTo(methodOn(CategoryController.class).findById(c.getId())).withSelfRel());
			response.add(category);
		});
		
		return response;
		
	}

	public CategoryResponseVO createCategory(CategoryRequestVO categoryVO) {

		if(categoryVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("creating category");
		
		var entity = DozerMapper.parseObject(categoryVO, Category.class);
		
		var response = DozerMapper.parseObject(categoryRepository.save(entity), CategoryResponseVO.class);
		
		response.add(linkTo(methodOn(CategoryController.class).createCategory(categoryVO)).withSelfRel());
		
		return response;
		
	}

	public CategoryResponseVO updateCategory(CategoryRequestVO categoryVO) {

		if(categoryVO == null) throw new RequiredObjectIsNullException();
		
		logger.info("updating category");
		
		var entity = categoryRepository.findById(categoryVO.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No category found for this ID!"));
		
		entity.setName(categoryVO.getName());
		entity.setDescription(categoryVO.getDescription());
		
		var response = DozerMapper.parseObject(categoryRepository.save(entity), CategoryResponseVO.class);
		
		response.add(linkTo(methodOn(CategoryController.class).updateCategory(categoryVO)).withSelfRel());
				
		return response;
		
	}

	public void deleteCategory(Long id) {
		
		logger.info("deleting category");
		
		var entity = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No category found for this ID!"));
		
		categoryRepository.delete(entity);
		
	}

}
