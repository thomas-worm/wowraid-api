package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    public Iterable<Transaction> findByAccount(Account account);

}