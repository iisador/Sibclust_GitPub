package ru.gitpub.ruspost.domain.entities;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STAT")
public class Statistic implements Serializable {

    @Id
    private UUID id;

    private Long assignedOverall;

    private Long completed;

    private Long cancelled;

    private Long clientCancelled;

    private Long failed;
}
