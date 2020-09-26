package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
    @JoinColumn(columnDefinition = "ID")
    private List<ContrServ> services = new ArrayList<>();
}
