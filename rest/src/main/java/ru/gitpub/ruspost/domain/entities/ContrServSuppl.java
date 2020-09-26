package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "SUPPLEMENTARYID")
    private Supplementary supplementary;

    @ManyToOne
    @JoinColumn(name = "CONTRSERVID")
    private ContrServ contrServ;

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
