package ru.gitpub.ruspost.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Тестовый контроллер. **/
@RestController
@RequestMapping("rest/test")
public class TestController {

    /**
     * Просто выводит приветствие.
     *
     * @return Hello!
     **/
    @GetMapping
    public String hello() {
        return "Hello!";
    }
}
