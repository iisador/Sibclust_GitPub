package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Сервисы предоставляемые поставщиком. **/
@Getter
@Setter
@Entity
@Table(name = "CONTR_SERV")
public class ContrServ implements Serializable {

    /** ID. **/
    @Id
    private UUID id;

    /** ID поставщика. **/
    private UUID contractorId;

    /** ID типа сервиса. **/
    private UUID serviceTypeId;

    /** Имя услуги. **/
    private String name;

    /** Минимальный вес посылки. **/
    private Long weightMin;

    /** Максимальный вес посылки. **/
    private Long weightMax;
}
