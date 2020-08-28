package com.ecommerce.product.product.consumer;

import com.ecommerce.product.product.service.ProductService;
import com.ecommerce.product.product.service.ProductServiceImpl;
import com.ecommerce.product.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventsConsumer {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductService productService;

    @KafkaListener(topics = {"product-events"})
    public void onMessage(ConsumerRecord<Integer,String> consumerRecord){
        log.info("Consumer Record: {} ",consumerRecord);
        productService.processProduct(consumerRecord);
    }
}
