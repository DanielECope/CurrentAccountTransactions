package com.nttdata.currentaccount_transactions.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class CurrentAccountTransactions {
    @Id
    private String id;
    @NotNull
    private CurrentAccount currentAccount;
    //@NotBlank
    //private String transactionCode;
    @Valid
    private TypeTransaction typeTransaction;
    @NotNull
    private float transactionAmount;
    private LocalDateTime transactionDateTime;
}
