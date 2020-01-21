package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class RaceRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Race> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Race> query = builder.createQuery(Race.class);
        Root<Race> raceTable = query.from(Race.class);
        List<Race> foundRaces = this.entityManager.createQuery(query.select(raceTable)).getResultList();
        return foundRaces;
    }

    public Race findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Race> query = builder.createQuery(Race.class);
        Root<Race> raceTable = query.from(Race.class);
        List<Race> foundRaces = entityManager.createQuery(
            query.select(raceTable).where(builder.equal(raceTable.get(Race_.name), name))
        ).setMaxResults(1).getResultList();
        return foundRaces.isEmpty() ? null : foundRaces.get(0);
    }

}