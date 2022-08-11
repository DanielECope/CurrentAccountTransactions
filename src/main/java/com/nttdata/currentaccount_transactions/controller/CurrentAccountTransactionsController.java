package com.nttdata.currentaccount_transactions.controller;

import com.nttdata.currentaccount_transactions.models.CurrentAccountTransactions;
import com.nttdata.currentaccount_transactions.service.CurrentAccountTransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class CurrentAccountTransactionsController {
    @Autowired
    CurrentAccountTransactionsService service;

    @GetMapping("/list")
    public Flux<CurrentAccountTransactions> findAll(){
        return service.findAll();
    }
    @GetMapping("/list/{number}")
    public Mono<CurrentAccountTransactions> findByAccountNumber(String number){
        return service.findByAccountNumber(number);
    }

    @PostMapping("/create")
    public Mono<CurrentAccountTransactions> create(CurrentAccountTransactions cat)
    {
        return service.create(cat);
    }
}
