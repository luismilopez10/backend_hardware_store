package com.hardwarestore.mapper;

import com.hardwarestore.collection.Bill;
import com.hardwarestore.dto.BillDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.config.EnableWebFlux;

@Component
@EnableWebFlux
public class BillMapper {

    private final ModelMapper mapper;

    public BillMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public BillDto fromBillToBillDto(Bill bill){
        return mapper.map(bill, BillDto.class);
    }

    public Bill fromBillDtoToBill(BillDto billDto){
        return mapper.map(billDto, Bill.class);
    }
}
