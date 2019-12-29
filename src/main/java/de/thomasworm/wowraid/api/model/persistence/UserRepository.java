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
public class UserRepository {

    @PersistenceContext()
    private EntityManager entityManager;

    @Transactional()
    public void addOrUpdateByBlizzardIdentifier(User user) {
        User existingUser = findByBlizzardIdentifier(user.getBlizzardIdentifier());
        if (existingUser == null) {
            this.entityManager.persist(user);
        } else {
            existingUser.setBattleTag(user.getBattleTag());
            existingUser = this.entityManager.merge(existingUser);
        }
        this.entityManager.flush();
    }

    public User findByBlizzardIdentifier(int blizzardIdentifier) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> userTable = query.from(User.class);
        List<User> foundUsers = entityManager.createQuery(
            query.select(userTable).where(builder.equal(userTable.get("blizzardIdentifier"), blizzardIdentifier))
        ).setMaxResults(1).getResultList();
        return foundUsers.isEmpty() ? null : foundUsers.get(0);
    }

}