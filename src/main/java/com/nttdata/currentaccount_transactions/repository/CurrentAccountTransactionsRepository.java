package com.nttdata.currentaccount_transactions.repository;

import com.nttdata.currentaccount_transactions.models.CurrentAccountTransactions;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CurrentAccountTransactionsRepository extends ReactiveMongoRepository<CurrentAccountTransactions,String> {
}
