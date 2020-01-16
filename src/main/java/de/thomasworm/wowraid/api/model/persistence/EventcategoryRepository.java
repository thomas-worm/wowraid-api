package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class EventcategoryRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Eventcategory> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Eventcategory> query = builder.createQuery(Eventcategory.class);
        Root<Eventcategory> eventcategoryTable = query.from(Eventcategory.class);
        List<Eventcategory> foundEventcategories = this.entityManager.createQuery(query.select(eventcategoryTable)).getResultList();
        return foundEventcategories;
    }

    public Eventcategory findByName(String name) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Eventcategory> query = builder.createQuery(Eventcategory.class);
        Root<Eventcategory> eventcategoryTable = query.from(Eventcategory.class);
        List<Eventcategory> foundEventgategories = this.entityManager.createQuery(
            query.select(eventcategoryTable).where(builder.equal(eventcategoryTable.get(Eventcategory.Columns.name), name))
        ).setMaxResults(1).getResultList();
        return foundEventgategories.isEmpty() ? null : foundEventgategories.get(0);
    }

}