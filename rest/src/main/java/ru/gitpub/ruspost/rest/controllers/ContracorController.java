package ru.gitpub.ruspost.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Contractor;
import ru.gitpub.ruspost.domain.repos.ContractorRepository;

@RestController
@RequestMapping("/rest/contractor")
public class ContracorController {

    private final ContractorRepository contractorRepository;

    public ContracorController(ContractorRepository contractorRepository) {
        this.contractorRepository = contractorRepository;
    }

    @GetMapping
    public List<Contractor> list() {
        return contractorRepository.findAll();
    }
}
