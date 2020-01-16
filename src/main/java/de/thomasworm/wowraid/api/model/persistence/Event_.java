package de.thomasworm.wowraid.api.model.persistence;

import java.time.LocalDateTime;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Event.class)
public class Event_ {
    public static volatile SingularAttribute<Event, Long> id;
    public static volatile SingularAttribute<Event, String> key;
    public static volatile SingularAttribute<Event, String> name;
    public static volatile SingularAttribute<Event, String> description;
    public static volatile SingularAttribute<Event, LocalDateTime> startDateTime;
    public static volatile SingularAttribute<Event, LocalDateTime> finishDateTime;
    public static volatile SetAttribute<Event, Eventcategory> categories;
}