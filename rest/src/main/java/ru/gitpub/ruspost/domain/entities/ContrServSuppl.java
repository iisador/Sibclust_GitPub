package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Доп. услуга, которую предоставляет поставщик для конкретного сервиса. **/
@Getter
@Setter
@Entity
@Table(name = "CONTR_SERV_SUPPL")
public class ContrServSuppl implements Serializable {

    /** ID. **/
    @EmbeddedId
    private ContrServSupplId id;

    /** Цена на доп. услугу. **/
    private Long price;

    /** Составной ПК для таблицы. **/
    @Getter
    @Setter
    @Embeddable
    public static class ContrServSupplId implements Serializable {

        /** ID услуги поставщика. **/
        private UUID contrServId;

        /** ID доп. услуги. **/
        private UUID supplementaryId;

    }
}
