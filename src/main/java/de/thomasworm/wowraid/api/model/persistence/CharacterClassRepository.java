package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface CharacterClassRepository extends PagingAndSortingRepository<CharacterClass, Long> {

    public CharacterClass findByName(String name);

}