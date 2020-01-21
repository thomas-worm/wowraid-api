package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

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

    public Iterable<Character> findByUser(User user) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Character> query = builder.createQuery(Character.class);
        Root<Character> characterTable = query.from(Character.class);
        List<Character> foundCharacters = entityManager.createQuery(
            query.select(characterTable).where(builder.equal(characterTable.get(Character_.user), user))
        ).getResultList();
        return foundCharacters.isEmpty() ? null : foundCharacters;
    }

    public Character findByRealmAndName(Realm realm, String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Character> query = builder.createQuery(Character.class);
        Root<Character> characterTable = query.from(Character.class);
        List<Character> foundCharacters = entityManager.createQuery(
            query.select(characterTable).where(builder.and(
                builder.equal(characterTable.get(Character_.realm), realm),
                builder.equal(builder.lower(characterTable.get(Character_.name)), name.toLowerCase())
            ))
        ).setMaxResults(1).getResultList();
        return foundCharacters.isEmpty() ? null : foundCharacters.get(0);
    }

    @Transactional()
    public void add(Character character) {
        this.entityManager.persist(character);
        this.entityManager.flush();
    }

    @Transactional()
    public Character save(Character character) {
        Character savedCharacter = this.entityManager.merge(character);
        this.entityManager.flush();
        return savedCharacter;
    }
    
}