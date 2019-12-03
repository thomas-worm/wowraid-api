package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class CharacterRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Character> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Character> query = builder.createQuery(Character.class);
        Root<Character> characterTable = query.from(Character.class);
        List<Character> foundCharacters = this.entityManager.createQuery(query.select(characterTable)).getResultList();
        return foundCharacters;
    }
    
}