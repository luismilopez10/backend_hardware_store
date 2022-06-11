package com.hardwarestore.usecase.receipt;

import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.mapper.ReceiptMapper;
import com.hardwarestore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetAllReceiptsUseCase {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptMapper mapper;

    public Flux<ReceiptDto> getAllReceipts() {
        return receiptRepository.findAll().map(mapper::fromReceiptToReceiptDto);
    }
}
