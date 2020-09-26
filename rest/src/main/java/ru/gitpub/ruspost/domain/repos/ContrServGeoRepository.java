package ru.gitpub.ruspost.domain.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gitpub.ruspost.domain.entities.ContrServGeo;

@Repository
public interface ContrServGeoRepository extends JpaRepository<ContrServGeo, UUID> {
}
