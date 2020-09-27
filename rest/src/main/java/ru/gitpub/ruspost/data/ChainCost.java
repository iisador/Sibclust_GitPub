package ru.gitpub.ruspost.data;

import lombok.Data;

@Data
public class ChainCost {
    private final Long costByDuration;
    private final Long costByPrice;
}
