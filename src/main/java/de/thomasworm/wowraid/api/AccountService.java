package de.thomasworm.wowraid.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Account;
import de.thomasworm.wowraid.api.model.persistence.AccountRepository;
import de.thomasworm.wowraid.api.model.persistence.EffortAndGearKeyPerformanceIndicator;
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
        return Mono.justOrEmpty(this.accountRepository.findByKey(key));
    }

    public Mono<Iterable<Transaction>> getTransactionsByAccount(Account account) {
        return Mono.justOrEmpty(this.transactionRepository.findByAccount(account));
    }

    public Mono<Iterable<EffortAndGearKeyPerformanceIndicator>> getEffortAndGearKeyPerformanceIndicators() {
        return Mono.justOrEmpty(this.accountRepository.findEffortAndGearKeyPerformanceIndicators());
    }

}