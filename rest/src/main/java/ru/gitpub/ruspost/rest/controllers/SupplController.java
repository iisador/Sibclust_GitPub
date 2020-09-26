package ru.gitpub.ruspost.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Supplementary;
import ru.gitpub.ruspost.domain.repos.SupplementaryRepository;

@RestController
@RequestMapping("/rest/supplementary")
public class SupplController {

    private final SupplementaryRepository supplementaryRepository;

    public SupplController(SupplementaryRepository supplementaryRepository) {
        this.supplementaryRepository = supplementaryRepository;
    }

    @GetMapping
    public List<Supplementary> list() {
        return supplementaryRepository.findAll();
    }
}
