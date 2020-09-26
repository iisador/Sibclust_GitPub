package ru.gitpub.ruspost.rest.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Contractor;
import ru.gitpub.ruspost.domain.repos.ContractorRepository;

@RestController
@RequestMapping("/rest/contractor")
public class ContractorController {

    private final ContractorRepository contractorRepository;

    public ContractorController(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @GetMapping
    public Contractor list(String id) {
        return contractorRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("No contractor"));
    }
}
