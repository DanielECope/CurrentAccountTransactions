package com.nttdata.currentaccount_transactions.repository;

import com.nttdata.currentaccount_transactions.models.CurrentAccountTransactions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CurrentAccountTransactionsRepository extends ReactiveMongoRepository<CurrentAccountTransactions,String> {
    Mono<CurrentAccountTransactions> findByAccountNumber(String accountNumber);
}
