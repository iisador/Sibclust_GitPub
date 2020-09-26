package ru.gitpub.ruspost.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.rest.InputRequest;
import ru.gitpub.ruspost.services.ChainService;

/** Тестовый контроллер. **/
@RestController
@RequestMapping("rest/getChain")
public class InputController {

    private final ChainService chainService;

    public InputController(ChainService chainService) {
        this.chainService = chainService;
    }

    /**
     * Просто выводит приветствие.
     *
     * @return Hello!
     **/
    @PostMapping
    public List<Order> calcChain(@RequestBody InputRequest inputRequest) {
        return chainService.calcChain(inputRequest);
    }
}
