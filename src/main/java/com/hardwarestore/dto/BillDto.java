package com.hardwarestore.dto;

import java.time.LocalDate;
import java.util.List;

public class BillDto {
    private String id;
    private LocalDate date;
    private String clientName;
    private String employeeName;
    private List<String> productsId;
    private Double totalPrice;
}
