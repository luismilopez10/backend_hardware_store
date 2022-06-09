package com.hardwarestore.repository;

import com.hardwarestore.collection.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}
