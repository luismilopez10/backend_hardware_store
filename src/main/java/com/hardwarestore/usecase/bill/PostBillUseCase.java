package com.hardwarestore.usecase.bill;

import com.hardwarestore.dto.BillDto;
import com.hardwarestore.mapper.BillMapper;
import com.hardwarestore.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PostBillUseCase {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper mapper;

    public Mono<BillDto> postBill(BillDto billDto){
//        billDto.setDate(LocalDate.now());
        return billRepository.save(mapper.fromBillDtoToBill(billDto)).map(mapper::fromBillToBillDto);
    }
}
