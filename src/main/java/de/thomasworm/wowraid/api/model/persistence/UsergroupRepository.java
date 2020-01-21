package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class UsergroupRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Usergroup> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usergroup> query = builder.createQuery(Usergroup.class);
        Root<Usergroup> usergroupTable = query.from(Usergroup.class);
        List<Usergroup> foundUsergroups = this.entityManager.createQuery(query.select(usergroupTable)).getResultList();
        return foundUsergroups;
    }

}