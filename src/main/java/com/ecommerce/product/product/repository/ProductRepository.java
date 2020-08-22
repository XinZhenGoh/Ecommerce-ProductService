package com.ecommerce.product.product.repository;


import com.ecommerce.product.product.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findAllByCategories(List<String> categories);

    List<Product> findAllByCategoriesContaining(String category);

}
