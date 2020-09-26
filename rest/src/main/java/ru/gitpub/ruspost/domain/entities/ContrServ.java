package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @ManyToOne
    @JoinColumn(name = "CONTRACTORID")
    private Contractor contractor;

    /** ID типа сервиса. **/
    @ManyToOne
    @JoinColumn(name = "SERVICETYPEID")
    private ServiceType serviceType;

    /** Имя услуги. **/
    private String name;

    /** Минимальный вес посылки. **/
    private Long weightMin;

    /** Максимальный вес посылки. **/
    private Long weightMax;

    @OneToMany
    @JoinColumn(name = "CONTRSERVID")
    private List<ContrServSuppl> supplementaries = new ArrayList<>();
}
