package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Геозона. **/
@Getter
@Setter
@Entity
@Table(name = "GEOZONE")
public class Geozone implements Serializable {

    /** ID. **/
    @Id
    private UUID id;

    /** Имя геозоны. **/
    private String name;
}
