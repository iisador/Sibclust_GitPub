package ru.gitpub.ruspost.rest.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.ContrServGeoRepository;
import ru.gitpub.ruspost.domain.repos.ContrServRepository;
import ru.gitpub.ruspost.domain.repos.ContractorRepository;
import ru.gitpub.ruspost.domain.repos.OrderRepository;

import java.util.UUID;

@RestController
@RequestMapping("/rest/order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ContrServGeoRepository contrServGeoRepository;
    private final ContrServRepository contrServRepository;

    public OrderController(
            OrderRepository orderRepository,
            ContrServGeoRepository contrServGeoRepository,
            ContrServRepository contrServRepository) {
        this.orderRepository = orderRepository;
        this.contrServGeoRepository = contrServGeoRepository;
        this.contrServRepository = contrServRepository;
    }

    @PatchMapping(value = "contractor-service-geo")
    @ApiOperation(
            value = "Replace contactor service bound to geozone in an order",
            nickname = "changeOrderContrServGeo",
            response = Order.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Order.class),
            @ApiResponse(code = 400, message = "Parameters should be a valid UUID identifier"),
            @ApiResponse(code = 404, message = "Entities could not be found"),
    })
    public ResponseEntity<Order> changeOrderContrServGeo(String orderId, String contServGeoId) {

        if (orderId == null || contServGeoId == null) {
            return ResponseEntity.badRequest().build();
        }

        UUID orderUuid;
        UUID contrServGeoUuid;

        try {
            orderUuid = UUID.fromString(orderId);
            contrServGeoUuid = UUID.fromString(contServGeoId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        val order = orderRepository.findById(orderUuid)
                .orElse(null);

        val contrServGeo = contrServGeoRepository.findById(contrServGeoUuid)
                .orElse(null);

        if (order == null || contrServGeo == null) {
            return ResponseEntity.notFound().build();
        }

        order.setContrServGeo(contrServGeo);
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }

    @PatchMapping(value = "contractor-service")
    @ApiOperation(
            value = "Replace contactor service in an order",
            nickname = "changeOrderContrServ",
            response = Order.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Order.class),
            @ApiResponse(code = 400, message = "Parameters should be a valid UUID identifier"),
            @ApiResponse(code = 404, message = "Entities could not be found"),
    })
    public ResponseEntity<Order> changeOrderContrServ(String orderId, String contServId) {

        if (orderId == null || contServId == null) {
            return ResponseEntity.badRequest().build();
        }

        UUID orderUuid;
        UUID contrServUuid;

        try {
            orderUuid = UUID.fromString(orderId);
            contrServUuid = UUID.fromString(contServId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        val order = orderRepository.findById(orderUuid)
                .orElse(null);

        val contrServ = contrServRepository.findById(contrServUuid)
                .orElse(null);

        if (order == null || contrServ == null) {
            return ResponseEntity.notFound().build();
        }

        order.setService(contrServ);
        orderRepository.save(order);

        return ResponseEntity.ok(order);
    }
}
