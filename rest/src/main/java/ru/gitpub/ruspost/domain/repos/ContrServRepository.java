package ru.gitpub.ruspost.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gitpub.ruspost.domain.entities.ContrServ;
import ru.gitpub.ruspost.domain.entities.ContrServGeo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContrServRepository extends JpaRepository<ContrServ, UUID> {
}
