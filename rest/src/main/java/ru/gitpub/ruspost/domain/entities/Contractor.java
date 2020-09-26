package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Поставщик. **/
@Getter
@Setter
@Entity
@Table(name = "CONTRACTOR")
public class Contractor implements Serializable {

    /** Id. **/
    @Id
    private UUID id;

    /** Имя поставщика. **/
    private String name;
}
