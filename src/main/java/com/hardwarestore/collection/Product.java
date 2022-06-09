package com.hardwarestore.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Provider provider;
    private Integer minimumAmount;
    private Integer maximumAmount;
}
