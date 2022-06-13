package com.hardwarestore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceiptDto {
    private String id;
    private LocalDate date;
    private String productId;
    private Integer productsAmount;
    private String providerId;
}
