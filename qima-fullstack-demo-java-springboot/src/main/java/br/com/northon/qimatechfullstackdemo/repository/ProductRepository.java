package br.com.northon.qimatechfullstackdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.northon.qimatechfullstackdemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
