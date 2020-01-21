package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface EventcategoryRepository extends PagingAndSortingRepository<Eventcategory, Long> {

    public Eventcategory findByName(String name);

}