package de.thomasworm.wowraid.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thomasworm.wowraid.api.model.persistence.Account;
import de.thomasworm.wowraid.api.model.persistence.AccountRepository;
import de.thomasworm.wowraid.api.model.persistence.EffortAndGearAccountMapping;
import de.thomasworm.wowraid.api.model.persistence.EffortAndGearKeyPerformanceIndicator;
import de.thomasworm.wowraid.api.model.persistence.Transaction;
import de.thomasworm.wowraid.api.model.persistence.TransactionRepository;
import de.thomasworm.wowraid.api.model.persistence.User;
import de.thomasworm.wowraid.api.model.persistence.UserRepository;
import reactor.core.publisher.Mono;

@Service()
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired()
    public AccountService(
        AccountRepository accountRepository,
        TransactionRepository transactionRepository,
        UserRepository userRepository
    ) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Mono<Account> getAccountByKey(String key) {
        return Mono.justOrEmpty(this.accountRepository.findByKey(key));
    }

    public Mono<Iterable<Transaction>> getTransactionsByAccount(Account account) {
        return Mono.justOrEmpty(this.transactionRepository.findByAccount(account));
    }

    public Mono<Iterable<EffortAndGearKeyPerformanceIndicator>> getEffortAndGearKeyPerformanceIndicators() {
        Iterable<User> users = this.userRepository.findAll();
        List<EffortAndGearKeyPerformanceIndicator> epgps = new ArrayList<>();
        users.forEach(user -> {
            EffortAndGearAccountMapping mapping = user.getEffortAndGearAccounts();
            if (mapping != null) {
                Account effortAccount = mapping.getEffortAccount();
                Account gearAccount = mapping.getGearAccount();
                if (effortAccount != null && gearAccount != null) {
                    Double effortPointsResult = this.accountRepository.findSumOfValueByAccount(effortAccount);
                    double effortPoints = (effortPointsResult == null) ? 0 : effortPointsResult;
                    Double gearPointsResult = this.accountRepository.findSumOfValueByAccount(gearAccount);
                    double gearPoints = (gearPointsResult == null) ? 0 : gearPointsResult;
                    EffortAndGearKeyPerformanceIndicator epgp = new EffortAndGearKeyPerformanceIndicator(
                        user,
                        effortPoints,
                        effortAccount,
                        gearPoints,
                        gearAccount,
                        effortPoints/(gearPoints == 0 ? (effortPoints == 0 ? 1.0 : effortPoints) : gearPoints)
                    );
                    epgps.add(epgp);
                }
            }
        });
        return Mono.justOrEmpty(epgps);
    }

}