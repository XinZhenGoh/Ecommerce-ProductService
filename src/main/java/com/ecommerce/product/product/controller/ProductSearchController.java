package com.ecommerce.product.product.controller;

import com.ecommerce.product.product.model.Filter;
import com.ecommerce.product.product.model.Product;
import com.ecommerce.product.product.repository.ProductRepository;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/category/{name}")
    public List<Product> getByCategory(@PathVariable String name){
          return repository.findAllByCategoriesContaining(name);
    }

    //Save product
    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody List<Product> products){

        try{
            repository.saveAll(products);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving product");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Products saved");
    }

    //full text search
    @GetMapping("/search/{text}")
    public Iterable<Product> findByFullTextSearch(@PathVariable String text) {

        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(text)
                .field("itemName")
                .field("description")
                .field("categories")
                .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX);
        return repository.search(queryBuilder);
    }

    //autocomplete suggestion
    @GetMapping("/autocomplete/{word}")
    public Iterable<Product> findByAutoComplete(@PathVariable String word) {
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("itemName", word);
        return repository.search(matchPhrasePrefixQueryBuilder);
    }

    //search with filters
    @PostMapping("/getProducts/{productName}")
    public Iterable<Product> saveHotels(@RequestBody List<Filter> filters, @PathVariable String productName) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.matchQuery("name", productName));

        for(Filter f : filters) {
            boolQueryBuilder.must(QueryBuilders.matchQuery(f.getFilterName(), f.getFilterValue()));
        }

        return repository.search(boolQueryBuilder);
    }


    @GetMapping("/{id}")
    public Optional<Product> getProducts(@PathVariable String id){
        return repository.findById(id);
    }


}
