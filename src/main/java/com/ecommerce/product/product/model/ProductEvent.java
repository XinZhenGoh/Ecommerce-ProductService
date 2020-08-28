//package com.ecommerce.product.product.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Document(indexName = "product_event")
//public class ProductEvent {
//
//    @Id
//    @GeneratedValue
//    private Integer productEventId;
//    @Enumerated(EnumType.STRING)
//    private ProductEventType eventType;
//    private Product product;
//}
