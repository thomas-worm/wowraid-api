package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface CharacterRoleRepository extends PagingAndSortingRepository<CharacterRole, Long> {

    public CharacterRole findByName(String name);

}