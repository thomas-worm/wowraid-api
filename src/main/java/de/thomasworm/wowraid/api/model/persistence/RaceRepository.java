package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface RaceRepository extends PagingAndSortingRepository<Race, Long> {

    public Race findByName(String name);

}