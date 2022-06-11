package com.hardwarestore.usecase.receipt;

import com.hardwarestore.dto.ReceiptDto;
import com.hardwarestore.mapper.ReceiptMapper;
import com.hardwarestore.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class PostReceiptUseCase {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptMapper mapper;

    public Mono<ReceiptDto> postReceipt(ReceiptDto receiptDto) {
        receiptDto.setDate(LocalDate.now());
        return receiptRepository.save(mapper.fromReceiptDtoToReceipt(receiptDto)).map(mapper::fromReceiptToReceiptDto);
    }
}
