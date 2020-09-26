package ru.gitpub.ruspost.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.ContrServGeoRepository;
import ru.gitpub.ruspost.domain.repos.OrderRepository;
import ru.gitpub.ruspost.rest.InputRequest;
import ru.gitpub.ruspost.rest.resources.OrderResource;

import static java.util.stream.Collectors.toList;

@Service
public class ChainService {

    private final ContrServGeoRepository contrServGeoRepository;

    private final OrderRepository orderRepository;

    public ChainService(ContrServGeoRepository contrServGeoRepository, OrderRepository orderRepository) {
        this.contrServGeoRepository = contrServGeoRepository;
        this.orderRepository = orderRepository;
    }

    public List<OrderResource> calcChain(InputRequest inputRequest) {
        UUID fromGeozone = UUID.fromString(inputRequest.getFromGeozone());
        UUID toGeozone = UUID.fromString(inputRequest.getToGeozone());
        List<UUID> suppls = inputRequest.getSupplementaries().stream()
                .map(UUID::fromString)
                .collect(toList());
        Order first = contrServGeoRepository.getFirstContractor(fromGeozone, suppls)
                .map(Order::new)
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));
        Order ff = contrServGeoRepository.getFfCenter(Arrays.asList(fromGeozone, toGeozone))
                .map(Order::new)
                .orElseThrow(() -> new RuntimeException("ФФ не найден"));
        Order last = contrServGeoRepository.getLastContractor(toGeozone, suppls)
                .map(Order::new)
                .orElseThrow(() -> new RuntimeException("ФФ не найден"));
        first.setChild(ff);
        ff.setParent(first);

        ff.setChild(last);
        last.setParent(ff);

        first = orderRepository.save(first);

        return Arrays.asList(first, ff, last).stream()
                .map(o -> new OrderResource(o.getId(), o.getContrServGeo().getContractor().getName(), o.getContrServGeo().getPrice()))
                .collect(toList());
    }
}
