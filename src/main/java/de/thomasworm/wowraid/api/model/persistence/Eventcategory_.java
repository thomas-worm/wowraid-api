package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Eventcategory.class)
public class Eventcategory_ {
    public static volatile SingularAttribute<Eventcategory, Long> id;
    public static volatile SingularAttribute<Eventcategory, String> name;
    public static volatile SetAttribute<Eventcategory, Event> events;
}
