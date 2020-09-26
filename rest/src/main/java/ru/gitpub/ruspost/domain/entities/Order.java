package ru.gitpub.ruspost.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

/**
 * Звено в цепочке заказов.
 * {@code parentId == null}  начальное звено.
 * {@code childId == null}  конечное звено.
 **/
@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    /** ID. **/
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    /** ID родителя. **/
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "PARENTID")
    private Order parent;

    /** ID потомка. **/
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "CHILDID")
    private Order child;

    /** Привязка к конкретной услуге конкретного поставщика. **/
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "CONTRSERVGEOID")
    private ContrServGeo contrServGeo;
    
    @OneToOne
    @JoinColumn(name = "CONTRSERVID")
    private ContrServ service;

    /** Вес посылки. **/
    private Long weight;

    /** Время выполнения услуги с. **/
    private LocalDateTime dateFrom;

    /** Время выполнения услуги по. **/
    private LocalDateTime dateTo;

    @Transient
    private Double rating;

    @Transient
    private Long time;

    public Order() {
    }

    public Order(ContrServGeo contrServGeo, ContrServ service, Double rating, Long time) {
        this.contrServGeo = contrServGeo;
        this.service = service;
        this.rating = rating;
        this.time = time;
    }
}
