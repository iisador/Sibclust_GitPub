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

    private static final UUID FIRST_MILE = UUID.fromString("9c43245d-7989-4e05-bdb1-cac99bc44155");

    private static final UUID FF = UUID.fromString("a4379f8c-ca3b-402d-b192-d78fac8f815f");

    private static final UUID LAST_MILE = UUID.fromString("d4ca479d-ec3d-4d15-9fda-4a826cb2ef57");

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

        return Arrays.asList(toResource(first, FIRST_MILE),
                toResource(ff, FF),
                toResource(last, LAST_MILE));
    }

    private OrderResource toResource(Order o, UUID type) {
        OrderResource r = new OrderResource();
        r.setId(o.getId());
        //        r.setGeozoneId();
        r.setName(o.getContrServGeo().getContractor().getName());
        //        r.setServiceType(type);
        r.setSum(o.getContrServGeo().getPrice());
        return r;
    }
}
