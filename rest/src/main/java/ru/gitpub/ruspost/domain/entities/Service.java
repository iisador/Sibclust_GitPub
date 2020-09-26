package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Услуга. **/
@Getter
@Setter
@Entity
@Table(name = "SERVICE")
public class Service implements Serializable {

    /** ID. **/
    @Id
    private UUID id;

    /** Тип услуги. **/
    private UUID serviceTypeId;

    /** Имя услуги. **/
    private String name;

    /** Описание. **/
    private String descr;
}
