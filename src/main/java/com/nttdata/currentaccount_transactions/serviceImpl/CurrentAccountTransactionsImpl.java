package com.nttdata.currentaccount_transactions.serviceImpl;

import com.nttdata.currentaccount_transactions.models.CurrentAccount;
import com.nttdata.currentaccount_transactions.models.CurrentAccountTransactions;
import com.nttdata.currentaccount_transactions.models.TypeTransaction;
import com.nttdata.currentaccount_transactions.repository.CurrentAccountTransactionsRepository;
import com.nttdata.currentaccount_transactions.service.CurrentAccountTransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
public class CurrentAccountTransactionsImpl implements CurrentAccountTransactionsService {
    private final WebClient webClientCat= WebClient.create("http://localhost:8080/api/CurrentAccount");
    @Autowired
    CurrentAccountTransactionsRepository repository;

    @Override
    public Flux<CurrentAccountTransactions> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CurrentAccountTransactions> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<CurrentAccountTransactions> create(CurrentAccountTransactions cat) {
        Mono<CurrentAccount> caMono = findByCurrentAccountMono(cat.getCurrentAccount().getAccountNumber());
        CurrentAccount ca=new CurrentAccount();
        try {
            ca.setAccountNumber(caMono.block().getAccountNumber());
            ca.setCommission(caMono.block().getCommission());
            ca.setAmountAvailable(caMono.block().getAmountAvailable());
            ca.setCustomer(caMono.block().getCustomer());
            ca.setHolders(caMono.block().getHolders());
            ca.setSigners(caMono.block().getSigners());
            //ca.setRegistration_date(LocalDateTime.now());
            cat.setTransactionDateTime(LocalDateTime.now());
            if (cat.getTypeTransaction()== TypeTransaction.IN) {
                ca.setAmountAvailable(ca.getAmountAvailable()+cat.getTransactionAmount()-ca.getCommission());
            }else{
                ca.setAmountAvailable(ca.getAmountAvailable()-cat.getTransactionAmount()-ca.getCommission());
            }

            cat.setCurrentAccount(ca);
        }catch (Exception e){
            throw new RuntimeException("Nro de cuenta incorrecto");
        }
        try{
            repository.save(cat);
            this.updateCurrentAccount(ca);
        }catch (Exception e){
            throw new RuntimeException("Error");
        }
        return Mono.just(cat);
    }

    @Override
    public Mono<CurrentAccountTransactions> findBySavingAccountNumberAccount(String NumberAccount) {
        return null;
    }

    public Mono<CurrentAccount> findByCurrentAccountMono(String accountNumber){
        log.info("SavingAccountTransactionImpl: implements findBySavingAccountMono() method : {}",accountNumber);
        return webClientCat.get().uri("/findByAccountNumber/{accountNumber}",accountNumber)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrentAccount.class);
    }
    public Mono<CurrentAccount> updateCurrentAccount(CurrentAccount sv){
        log.info("SavingAccountTransactionImpl: implements findBySavingAccountUpdate() method : {}",sv.toString());
        return webClientCat.put().uri("/update",sv)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CurrentAccount.class);
    }
}
