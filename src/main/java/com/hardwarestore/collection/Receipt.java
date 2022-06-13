package com.hardwarestore.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Receipt {
    @Id
    private String id;
    private LocalDate date;
    private String productId;
    private Integer productsAmount;
    private String providerId;
}
