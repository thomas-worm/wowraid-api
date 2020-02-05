package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Account;
import de.thomasworm.wowraid.api.model.persistence.AccountRepository;
import de.thomasworm.wowraid.api.model.persistence.Transaction;
import de.thomasworm.wowraid.api.model.persistence.TransactionRepository;
import reactor.core.publisher.Mono;

@Service()
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    @Autowired()
    public AccountService(
        AccountRepository accountRepository,
        TransactionRepository transactionRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Mono<Account> getAccountByKey(String key) {
        System.out.println("Looking for account with key " + key);
        Account x = this.accountRepository.findByKey(key);
        if (x == null) System.out.println("Not found");
        return Mono.justOrEmpty(x);
    }

    public Mono<Iterable<Transaction>> getTransactionsByAccount(Account account) {
        System.out.println("Looking for transaction for account " + account.getName());
        Iterable<Transaction> x = this.transactionRepository.findByAccount(account);
        x.forEach(i -> {
            System.out.println(i.getTitle());
        });
        return Mono.justOrEmpty(x);
    }

}