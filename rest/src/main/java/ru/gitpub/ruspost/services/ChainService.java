package ru.gitpub.ruspost.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.rest.InputRequest;

import static java.util.Collections.emptyList;

@Service
public class ChainService {

    public List<Order> calcChain(InputRequest inputRequest) {
        UUID fromGeozone = UUID.fromString(inputRequest.getFromGeozone());
        UUID toGeozone = UUID.fromString(inputRequest.getToGeozone());


        return emptyList();
    }
}
