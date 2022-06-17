package com.hardwarestore.dto;

import com.hardwarestore.collection.BillProducts;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BillDto {
    private String id;
    private LocalDate date;
    private String clientName;
    private String employeeName;
    private List<BillProducts> products;
    private Double totalPrice;
}
