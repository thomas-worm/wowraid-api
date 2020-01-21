package de.thomasworm.wowraid.api.model.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository()
public class ItemRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    public Iterable<Item> findAll() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> itemTable = query.from(Item.class);
        List<Item> foundItems = this.entityManager.createQuery(query.select(itemTable)).getResultList();
        return foundItems;
    }

}