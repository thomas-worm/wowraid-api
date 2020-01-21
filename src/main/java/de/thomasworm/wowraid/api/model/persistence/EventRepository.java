package de.thomasworm.wowraid.api.model.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository()
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("SELECT event FROM Event event WHERE :category IN categories")
    public Iterable<Event> findByCategory(@Param("category") Eventcategory category);

}