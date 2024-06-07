package br.com.northon.qimatechfullstackdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.northon.qimatechfullstackdemo.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
