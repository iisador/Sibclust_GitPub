package ru.gitpub.ruspost.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.ContrServGeoRepository;
import ru.gitpub.ruspost.domain.repos.OrderRepository;

import java.util.UUID;

@RestController
@RequestMapping("/rest/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ContrServGeoRepository contrServGeoRepository;

    public OrderController(OrderRepository orderRepository, ContrServGeoRepository contrServGeoRepository) {
        this.orderRepository = orderRepository;
        this.contrServGeoRepository = contrServGeoRepository;
    }

    @PatchMapping(value = "contractor-service-geo")
    @ApiOperation(
            value = "Replace contactor service in order",
            nickname = "changeOrderContrServGeo",
            response = Order.class
    )
    public Order changeOrderContrServGeo(
            @ApiParam(name = "Order ID", value = "Order id to change") String orderId,
            @ApiParam(name = "ContrServGeo ID", value = "Contractor service with certain geozone id to apply to order") String contServGeoId) {
        val order = orderRepository.findById(UUID.fromString(orderId))
                .orElseThrow(() -> new RuntimeException("No order with given id found"));

        val contrServGeo = contrServGeoRepository.findById(UUID.fromString(contServGeoId))
                .orElseThrow(() -> new RuntimeException("No contract service for location with given id found"));

        order.setContrServGeo(contrServGeo);

        return order;
    }
}
