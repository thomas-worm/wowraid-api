package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class RealmRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Realm> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Realm> query = builder.createQuery(Realm.class);
        Root<Realm> realmTable = query.from(Realm.class);
        List<Realm> foundRealms = this.entityManager.createQuery(query.select(realmTable)).getResultList();
        return foundRealms;
    }

    public Realm findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Realm> query = builder.createQuery(Realm.class);
        Root<Realm> realmTable = query.from(Realm.class);
        List<Realm> foundRealms = entityManager.createQuery(
            query.select(realmTable).where(builder.equal(builder.lower(realmTable.get("name")), name.toLowerCase()))
        ).setMaxResults(1).getResultList();
        return foundRealms.isEmpty() ? null : foundRealms.get(0);
    }

}