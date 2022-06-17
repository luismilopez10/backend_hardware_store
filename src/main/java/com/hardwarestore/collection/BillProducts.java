package com.hardwarestore.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillProducts {
    private String id;
    private String name;
    private Double price;
    private Integer amount;
}
