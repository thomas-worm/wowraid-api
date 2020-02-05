package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    public Account findByKey(String key);

    @Query(
        "SELECT " +
            "" +
                "user.blizzardIdentifier AS battleTag, " +
                "SUM(effortTransactions.value) AS effortPoints, " +
                "SUM(gearTransactions.value) AS gearPoints, " +
                "SUM(effortTransactions.value)/SUM(gearTransactions.value) AS priority " +
            "" +
        "FROM User user " +
        "JOIN user.effortAndGearAccounts epgpmapping " +
        "JOIN epgpmapping.effortAccount effortAccount " +
        "JOIN epgpmapping.gearAccount gearAccount " +
        "LEFT JOIN effortAccount.transactions effortTransactions " +
        "LEFT JOIN gearAccount.transactions gearTransactions " +
        "GROUP BY user"
    )
    public Iterable<EffortAndGearKeyPerformanceIndicator> findEffortAndGearKeyPerformanceIndicators();

}