package ru.gitpub.ruspost.rest.resources;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.gitpub.ruspost.domain.entities.Order;

@Getter
@Setter
@AllArgsConstructor
public class ChainResource {

    List<OrderResource> chains;

    public Long getTotalSum() {
        return chains.stream()
                .mapToLong(OrderResource::getSum)
                .sum();
    }

    public Long getTotalTime() {
        return chains.stream()
                .mapToLong(OrderResource::getTime)
                .sum();
    }
}
