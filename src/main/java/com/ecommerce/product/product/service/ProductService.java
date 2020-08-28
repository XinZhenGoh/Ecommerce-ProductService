package com.ecommerce.product.product.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ProductService {

    public void processProduct(ConsumerRecord<Integer, String> consumerRecord);
}
