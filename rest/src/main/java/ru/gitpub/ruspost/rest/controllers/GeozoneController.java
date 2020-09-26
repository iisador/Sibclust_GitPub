package ru.gitpub.ruspost.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Geozone;
import ru.gitpub.ruspost.domain.repos.GeozoneRepository;

@RestController
@RequestMapping("/rest/geozones")
public class GeozoneController {

    private final GeozoneRepository geozoneRepository;

    public GeozoneController(GeozoneRepository geozoneRepository) {
        this.geozoneRepository = geozoneRepository;
    }

    @GetMapping
    public List<Geozone> list() {
        return geozoneRepository.findAll();
    }
}
