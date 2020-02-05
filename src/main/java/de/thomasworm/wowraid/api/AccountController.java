package de.thomasworm.wowraid.api;

import de.thomasworm.wowraid.api.model.dto.Transaction;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AccountController {

    private AccountService accountService;

    @Autowired()
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account/{key}/transaction")
    public Mono<List<Transaction>> getTransactionsOfAccount(@PathVariable("key") String accountKey) {
        return this.accountService
            .getAccountByKey(accountKey)
            .flatMap(this.accountService::getTransactionsByAccount)
            .map(transactions -> {
                List<Transaction> transactionList = new ArrayList<>();
                transactions.forEach(transaction -> {
                    Transaction transactionDto = new Transaction();
                    transactionDto.setDateTime(transaction.getDateTime());
                    transactionDto.setTitle(transaction.getTitle());
                    transactionDto.setValue(transaction.getValue());
                    transactionDto.setCurrency(transaction.getCurrency().getName());
                });
                return transactionList;
            });
    }

}