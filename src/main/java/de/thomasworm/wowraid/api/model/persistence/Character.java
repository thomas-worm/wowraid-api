package de.thomasworm.wowraid.api.model.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity()
public class Character {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

}