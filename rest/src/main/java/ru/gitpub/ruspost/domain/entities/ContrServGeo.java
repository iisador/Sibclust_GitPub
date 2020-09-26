package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Привязка услуги поставщика к геозоне. **/
@Getter
@Setter
@Entity
@Table(name = "CONTR_SERV_GEO")
public class ContrServGeo implements Serializable {

    /** Составной ключ. **/
    @EmbeddedId
    private ContrServGeoID id;

    /** Стоимость услуги. **/
    private Long price;

    /** Среднее время выполнения. **/
    private Long avgExecTime;

    /** Услуга доступна с. **/
    private String providedFrom;

    /** Услуга доступна по. **/
    private String providedTo;

    /** Составной ключ для сущности. **/
    @Getter
    @Setter
    @Embeddable
    public static class ContrServGeoID implements Serializable {

        /** ID услуги поставщика. **/
        private UUID contrServId;

        /** ID геозоны. **/
        private UUID geozoneId;
    }
}
