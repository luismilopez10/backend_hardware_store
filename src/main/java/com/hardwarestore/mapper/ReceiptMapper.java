package com.hardwarestore.mapper;

import com.hardwarestore.collection.Receipt;
import com.hardwarestore.dto.ReceiptDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class ReceiptMapper {

    private final ModelMapper mapper;

    public ReceiptMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ReceiptDto fromReceiptToReceiptDto(Receipt Receipt){
        return mapper.map(Receipt, ReceiptDto.class);
    }

    public Receipt fromReceiptDtoToReceipt(ReceiptDto ReceiptDto){
        return mapper.map(ReceiptDto, Receipt.class);
    }
}