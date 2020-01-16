package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.springframework.stereotype.Repository;

@Repository()
public class EventRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Event> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> eventTable = query.from(Event.class);
        List<Event> foundEvents = this.entityManager.createQuery(query.select(eventTable)).getResultList();
        return foundEvents;
    }

    public Iterable<Event> findByCategory(Eventcategory eventcategory) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> query = builder.createQuery(Event.class);
        Root<Event> eventTable = query.from(Event.class);
        SetJoin<Event, Eventcategory> joinTable = eventTable.join(Event.Columns.categories);
        List<Event> foundEvents = this.entityManager.createQuery(
            query.select(eventTable).where(joinTable.in(eventcategory))
        ).getResultList();
        return foundEvents;
    }

}