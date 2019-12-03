package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class FactionRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Faction> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Faction> query = builder.createQuery(Faction.class);
        Root<Faction> factionTable = query.from(Faction.class);
        List<Faction> foundFactions = this.entityManager.createQuery(query.select(factionTable)).getResultList();
        return foundFactions;
    }

    public Faction findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Faction> query = builder.createQuery(Faction.class);
        Root<Faction> factionTable = query.from(Faction.class);
        List<Faction> foundFactions = entityManager.createQuery(
            query.select(factionTable).where(builder.equal(factionTable.get("name"), name))
        ).setMaxResults(1).getResultList();
        return foundFactions.isEmpty() ? null : foundFactions.get(0);
    }

}