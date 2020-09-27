package ru.gitpub.ruspost.rest.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.OrderRepository;
import ru.gitpub.ruspost.rest.resources.ChainResource;
import ru.gitpub.ruspost.rest.resources.OrderResource;

@RestController
@RequestMapping("/rest/chain")
public class ChainController {

    private OrderRepository orderRepository;

    public ChainController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<OrderResource> get(String chainId) {
        return toResource(orderRepository.findById(UUID.fromString(chainId)).orElse(null));
    }

    private List<OrderResource> toResource(Order order) {
        OrderResource first = toOrderResource(order);
        OrderResource ff = toOrderResource(order.getChild());
        OrderResource last = toOrderResource(order.getChild().getChild());
        return Arrays.asList(first, ff, last);
    }

    private OrderResource toOrderResource(Order o) {
        OrderResource r = new OrderResource();
        r.setId(o.getId());
        r.setGeozoneId(o.getContrServGeo().getGeozone().getName());
        r.setName(o.getContrServGeo().getContractor().getName());
        r.setServiceType(o.getService().getServiceType().getName());
        r.setSum(o.getContrServGeo().getPrice());
        r.setRating(o.getRating());
        r.setTime(o.getTime());
        r.setWeight(o.getWeight());
        return r;
    }
}
