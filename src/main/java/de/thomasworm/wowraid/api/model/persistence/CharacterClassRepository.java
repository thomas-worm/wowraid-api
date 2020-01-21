package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class CharacterClassRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<CharacterClass> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<CharacterClass> query = builder.createQuery(CharacterClass.class);
        Root<CharacterClass> classTable = query.from(CharacterClass.class);
        List<CharacterClass> foundClasses = this.entityManager.createQuery(query.select(classTable)).getResultList();
        return foundClasses;
    }

    public CharacterClass findByName(String className) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<CharacterClass> query = builder.createQuery(CharacterClass.class);
        Root<CharacterClass> characterClassTable = query.from(CharacterClass.class);
        List<CharacterClass> foundCharacterClasses = entityManager.createQuery(
            query.select(characterClassTable).where(builder.equal(characterClassTable.get(CharacterClass_.name), className))
        ).setMaxResults(1).getResultList();
        return foundCharacterClasses.isEmpty() ? null : foundCharacterClasses.get(0);
    }

}