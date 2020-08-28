package com.ecommerce.product.product.service;

import com.ecommerce.product.product.model.Product;
import com.ecommerce.product.product.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    public void processProduct(ConsumerRecord<Integer, String> consumerRecord){
        try {
            Product product = objectMapper.readValue(consumerRecord.value(), Product.class);
            log.info("product  received : {} ", product);

            productRepository.save(product);

            log.info("succesfully saved  : {} ", product);

        } catch (JsonProcessingException e) {
            log.info("Encountered error parsing JSON : {} ", e.getMessage());
        }
    }
}
