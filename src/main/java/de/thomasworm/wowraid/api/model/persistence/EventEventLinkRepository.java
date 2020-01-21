package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class EventEventLinkRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<EventEventLink> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<EventEventLink> query = builder.createQuery(EventEventLink.class);
        Root<EventEventLink> eventEventLinkTable = query.from(EventEventLink.class);
        List<EventEventLink> foundEvents = this.entityManager.createQuery(query.select(eventEventLinkTable)).getResultList();
        return foundEvents;
    }

}