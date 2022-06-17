package com.hardwarestore.dto;

import com.hardwarestore.collection.ProductSold;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillDto {
    private String id;
    private LocalDate date;
    private String clientName;
    private String employeeName;
    private List<ProductSold> products;
    private Double totalPrice;
}
