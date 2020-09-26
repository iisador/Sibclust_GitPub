package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
    private UUID id;

    /** ID родителя. **/
    private UUID parentId;

    /** ID потомка. **/
    private UUID childId;

    /** Привязка к конкретной услуге конкретного поставщика. **/
    private UUID contrServId;

    /** Привязка к геозоне. **/
    private UUID geozoneId;

    /** Вес посылки. **/
    private Long weight;

    /** Время выполнения услуги с. **/
    private LocalDateTime dateFrom;

    /** Время выполнения услуги по. **/
    private LocalDateTime dateTo;
}
