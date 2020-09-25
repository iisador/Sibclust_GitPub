package ru.gitpub.ruspost.rest;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Базовый класс spring boot приложения.
 */
@SpringBootApplication
public class RusPostApplication extends SpringBootServletInitializer {

    /**
     * Метод main для запуска Spring Boot приложения.
     *
     * @param args параметры запуска приложения
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(RusPostApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
