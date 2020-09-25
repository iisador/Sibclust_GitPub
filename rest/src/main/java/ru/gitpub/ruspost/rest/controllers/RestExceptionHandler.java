package ru.gitpub.ruspost.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Хэндлер исключений для контроллеров.
 */
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Message source для интерполяции сообщений.
     */
    private MessageSource messageSource;


    @Autowired
    void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
