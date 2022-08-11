package com.nttdata.currentaccount_transactions.service;

import com.nttdata.currentaccount_transactions.models.CurrentAccountTransactions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrentAccountTransactionsService {
    public Flux<CurrentAccountTransactions> findAll();
    public Mono<CurrentAccountTransactions> findById(String id);
    public Mono<CurrentAccountTransactions> create(CurrentAccountTransactions t);
    public Mono<CurrentAccountTransactions> findBySavingAccountNumberAccount(String NumberAccount);
}
