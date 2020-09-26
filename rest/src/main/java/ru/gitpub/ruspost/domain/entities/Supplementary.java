package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Доп. услуга. **/
@Getter
@Setter
@Entity
@Table(name = "SUPPLEMENTARY")
public class Supplementary implements Serializable {

    /** ID. **/
    @Id
    private UUID id;

    /** Имя доп. услуги. **/
    private String name;
}
