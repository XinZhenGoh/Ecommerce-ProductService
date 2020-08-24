package com.ecommerce.product.product.repository;


import com.ecommerce.product.product.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findAllByCategories(List<String> categories);

    List<Product> findAllByCategoriesContaining(String category);

}
