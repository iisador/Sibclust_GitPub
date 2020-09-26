package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
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

    @OneToMany
    @JoinColumn(name = "CONTRSERVID")
    private List<ContrServGeo> geos = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "ID")
    private Statistic statistic;

    public ContrServ getService(UUID serviceType) {
        return services.stream()
                .filter(s -> s.getServiceType().getId().equals(serviceType))
                .findFirst()
                .orElse(null);
    }

    public ContrServGeo getGeozone(UUID geozoneId) {
        return geos.stream().filter(g -> g.getGeozone().getId().equals(geozoneId))
                .findFirst()
                .orElse(null);
    }
}
