package de.thomasworm.wowraid.api;

import de.thomasworm.wowraid.api.model.dto.EffortAndGearKeyPerformanceIndicator;
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
                    transactionList.add(transactionDto);
                });
                transactionList.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));
                return transactionList;
            });
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
                    epgps.add(epgp);
                });
                epgps.sort((a, b) -> Double.compare(b.getPriority(), a.getPriority()));
                return epgps;
            });
    }

}