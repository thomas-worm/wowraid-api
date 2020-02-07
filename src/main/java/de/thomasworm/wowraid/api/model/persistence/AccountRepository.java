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
            "COALESCE(SUM(transaction.value), 0) " +
        "FROM Account account " +
        "JOIN account.transactions transaction " +
        "WHERE account = :account " +
        "GROUP BY account"
    )
    public double findSumOfValueByAccount(@Param("account") Account account);

}