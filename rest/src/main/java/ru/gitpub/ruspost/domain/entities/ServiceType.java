package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Тип услуги. **/
@Getter
@Setter
@Entity
@Table(name = "SERVICE_TYPE")
public class ServiceType implements Serializable {

    /** ID. **/
    @Id
    private UUID id;

    /** Текстовое представление. **/
    private String name;
}
