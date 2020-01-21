package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface RealmRepository extends PagingAndSortingRepository<Realm, Long> {

    public Realm findByNameIgnoreCase(String name);

}