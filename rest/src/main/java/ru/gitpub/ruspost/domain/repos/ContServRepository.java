package ru.gitpub.ruspost.domain.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gitpub.ruspost.domain.entities.ContrServ;

@Repository
public interface ContServRepository extends JpaRepository<ContrServ, UUID> {
}
