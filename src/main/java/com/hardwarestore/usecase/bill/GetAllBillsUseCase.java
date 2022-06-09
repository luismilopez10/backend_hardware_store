package com.hardwarestore.usecase.bill;

import com.hardwarestore.dto.BillDto;
import com.hardwarestore.mapper.BillMapper;
import com.hardwarestore.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllBillsUseCase {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper mapper;

    public Flux<BillDto> getAllBills() {
        return billRepository.findAll().map(mapper::fromBillToBillDto);
    }
}
