package ru.gitpub.ruspost.domain.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gitpub.ruspost.domain.entities.Contractor;

/** Рпозиторий для поставщиков. **/
@Repository
public interface ContractorRepository extends JpaRepository<Contractor, UUID> {
}
