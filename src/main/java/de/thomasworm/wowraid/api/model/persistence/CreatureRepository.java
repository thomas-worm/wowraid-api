package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface CreatureRepository extends PagingAndSortingRepository<Creature, Long> {

    public Creature findByBlizzardIdentifier(int blizzardIdentifier);
    
}