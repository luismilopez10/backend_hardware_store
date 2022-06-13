package com.hardwarestore.collection;

import lombok.Data;

// Used for tests
@Data
public class ProductSold {
    private String id;
    private String name;
    private Double price;
    private Integer amount;
}
