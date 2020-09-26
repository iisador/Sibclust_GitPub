package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/** Поставщик. **/
@Getter
@Setter
@Entity
@Table(name = "CONTRACTOR")
public class Contractor implements Serializable {

    /** Id. **/
    @Id
    private UUID id;

    /** Имя поставщика. **/
    private String name;

    @OneToMany
    @JoinColumn(name = "CONTRACTORID")
    private List<ContrServ> services = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTRAGENTID")
    private List<ContrServGeo> geos = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private Statistic statistic;

    private Double indexPrice;

    private Double indexTime;

    private Double rating;
}
