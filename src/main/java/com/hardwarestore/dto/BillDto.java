package com.hardwarestore.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class BillDto {
    private String id;
    private LocalDate date;
    private String clientName;
    private String employeeName;
    private Map<String,Integer> products;
    private Double totalPrice;
}
