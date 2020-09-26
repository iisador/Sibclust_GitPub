package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @JoinColumns({
            @JoinColumn(name = "GEOZONEID"),
            @JoinColumn(name = "CONTRSERVID")
    })
    private ContrServGeo contrServGeo;

    /** Вес посылки. **/
    private Long weight;

    /** Время выполнения услуги с. **/
    private LocalDateTime dateFrom;

    /** Время выполнения услуги по. **/
    private LocalDateTime dateTo;

    public Order() {
    }

    public Order(ContrServGeo contrServGeo) {
        this.contrServGeo = contrServGeo;
    }
}
