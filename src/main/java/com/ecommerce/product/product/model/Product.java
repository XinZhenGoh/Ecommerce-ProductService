package com.ecommerce.product.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {

    @Id
    private Long itemID;
    private String itemName;
    private String description;
    private Double price;
    private String imageUrl;
    private int unitsInStock;
    private List<String> categories;
}
