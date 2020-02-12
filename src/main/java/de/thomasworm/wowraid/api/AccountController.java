package de.thomasworm.wowraid.api;

import de.thomasworm.wowraid.api.model.dto.Account;
import de.thomasworm.wowraid.api.model.dto.EffortAndGearKeyPerformanceIndicator;
import de.thomasworm.wowraid.api.model.dto.LinkedCharacter;
import de.thomasworm.wowraid.api.model.dto.LinkedEvent;
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

    @GetMapping("/account/{key}")
    public Mono<Account> getAccount(@PathVariable("key") String accountKey) {
        return this.accountService
            .getAccountByKey(accountKey)
            .map(accountRecord -> {
                Account account = new Account();
                account.setName(accountRecord.getName());
                List<Transaction> transactionList = new ArrayList<>();
                accountRecord.getTransactions().forEach(transaction -> {
                    transactionList.add(this.convertTransactionToTransactionDto(transaction));
                });
                transactionList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
                account.getTransactions().addAll(transactionList);
                return account;
            });
    }

    @GetMapping("/account/{key}/transaction")
    public Mono<List<Transaction>> getTransactionsOfAccount(@PathVariable("key") String accountKey) {
        return this.accountService
            .getAccountByKey(accountKey)
            .flatMap(this.accountService::getTransactionsByAccount)
            .map(transactions -> {
                List<Transaction> transactionList = new ArrayList<>();
                transactions.forEach(transaction -> {
                    transactionList.add(this.convertTransactionToTransactionDto(transaction));
                });
                transactionList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
                return transactionList;
            });
    }

    private Transaction convertTransactionToTransactionDto(de.thomasworm.wowraid.api.model.persistence.Transaction transactionRecord) {
        Transaction transactionDto = new Transaction();
        transactionDto.setDateTime(transactionRecord.getDateTime());
        transactionDto.setTitle(transactionRecord.getTitle());
        transactionDto.setValue(transactionRecord.getValue());
        transactionDto.setCurrency(transactionRecord.getCurrency().getName());
        transactionRecord.getLinkedEvents().forEach(eventRecord -> {
            LinkedEvent event = new LinkedEvent();
            event.setKey(eventRecord.getKey());
            event.setName(eventRecord.getName());
            transactionDto.getEvents().add(event);
        });
        return transactionDto;
    }

    @GetMapping("/kpi/epgp")
    public Mono<Iterable<EffortAndGearKeyPerformanceIndicator>> getEffortAndGearKeyPerformanceIndicators() {
        return this.accountService
            .getEffortAndGearKeyPerformanceIndicators()
            .map(kpis -> {
                List<EffortAndGearKeyPerformanceIndicator> epgps = new ArrayList<>();
                kpis.forEach(kpi -> {
                    EffortAndGearKeyPerformanceIndicator epgp = new EffortAndGearKeyPerformanceIndicator();
                    epgp.setBattleTag(kpi.getUser().getBattleTag());
                    epgp.setEffortPoints(kpi.getEffortPoints());
                    epgp.setEffortPointsAccount(kpi.getEffortPointsAccount().getKey());
                    epgp.setGearPoints(kpi.getGearPoints());
                    epgp.setGearPointsAccount(kpi.getGearPointsAccount().getKey());
                    epgp.setPriority(kpi.getPriority());
                    if (kpi.getUser().getCharacters() != null) {
                        kpi.getUser().getCharacters().forEach(character -> {
                            LinkedCharacter characterLink = new LinkedCharacter();
                            characterLink.setRealm(character.getRealm().getName());
                            characterLink.setName(character.getName());
                            epgp.getCharacters().add(characterLink);
                        });
                    }
                    epgps.add(epgp);
                });
                epgps.sort((a, b) -> Double.compare(b.getPriority(), a.getPriority()));
                return epgps;
            });
    }

}