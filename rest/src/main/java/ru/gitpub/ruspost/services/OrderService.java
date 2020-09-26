package ru.gitpub.ruspost.services;

import lombok.val;
import lombok.var;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.gitpub.ruspost.data.ChainCost;
import ru.gitpub.ruspost.domain.entities.Order;
import ru.gitpub.ruspost.domain.repos.OrderRepository;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @NonNull
    public ChainCost calculateChainCostFromOrder(@NonNull final UUID orderUuid) {
        return calculateChainCostFromOrder(
                orderRepository
                        .findById(orderUuid)
                        .orElseThrow(() -> new RuntimeException("No order was found by id=" + orderUuid))
        );
    }

    @NonNull
    public ChainCost calculateChainCostFromOrder(@NonNull final Order order) {
        var observedOrder = order;
        var totalTime = 0L;
        var totalPrice = 0L;
        while (observedOrder != null) {
            totalTime += observedOrder.getContrServGeo().getAvgExecTime();
            totalPrice += observedOrder.getContrServGeo().getPrice();
            observedOrder = observedOrder.getChild();
        }
        return new ChainCost(totalTime, totalPrice);
    }
}
