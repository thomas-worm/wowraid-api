package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository()
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    public Account findByKey(String key);

    @Query(
        "SELECT " +
            "new de.thomasworm.wowraid.api.model.persistence.EffortAndGearKeyPerformanceIndicator(" +
                "user, " +
                "SUM(effortTransactions.value), " +
                "SUM(gearTransactions.value), " +
                "SUM(effortTransactions.value)/SUM(gearTransactions.value)" +
            ")" +
        "FROM User user " +
        "JOIN user.effortAndGearAccounts epgpmapping " +
        "JOIN epgpmapping.effortAccount effortAccount " +
        "JOIN epgpmapping.gearAccount gearAccount " +
        "LEFT JOIN effortAccount.transactions effortTransactions " +
        "LEFT JOIN gearAccount.transactions gearTransactions " +
        "GROUP BY user"
    )
    public Iterable<EffortAndGearKeyPerformanceIndicator> findEffortAndGearKeyPerformanceIndicators();

    public class SumResult {
        private double sum = 0.0;
        public SumResult(double sum) {
            this.sum = sum;
        }
        public double getSum() {
            return this.sum;
        }
    }
    @Query(
        "SELECT " +
            //"new de.thomasworm.wowraid.api.model.persistence.AccountRepository.SumResult(SUM(transaction.value)) " +
            "SUM(transaction.value) " +
        "FROM Account account " +
        "JOIN account.transactions transaction " +
        "WHERE account = :account " +
        "GROUP BY account"
    )
    public double findSumOfValueByAccount(@Param("account") Account account);

}