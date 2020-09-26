package ru.gitpub.ruspost.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/** Привязка услуги поставщика к геозоне. **/
@Getter
@Setter
@Entity
@Table(name = "CONTR_SERV_GEO")
public class ContrServGeo implements Serializable {

    /** Составной ключ. **/
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "CONTRAGENTID")
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "GEOZONEID")
    private Geozone geozone;

    /** Стоимость услуги. **/
    private Long price;

    /** Среднее время выполнения. **/
    private Long avgExecTime;

    /** Услуга доступна с. **/
    private String providedFrom;

    /** Услуга доступна по. **/
    private String providedTo;
}
