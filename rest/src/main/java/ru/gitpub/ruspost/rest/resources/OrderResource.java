package ru.gitpub.ruspost.rest.resources;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderResource {

    private UUID id;

    private String name;

    private Long sum;
}
