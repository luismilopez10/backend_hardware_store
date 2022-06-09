package com.hardwarestore.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Bill {
    @Id
    private String id;
    private LocalDate date;
    private String clientName;
    private String employeeName;
    private Integer productsAmount;
    private Double totalPrice;
}
