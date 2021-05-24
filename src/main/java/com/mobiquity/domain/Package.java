package com.mobiquity.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Immutable Package class
 * */

@AllArgsConstructor
@EqualsAndHashCode(exclude = "ID")
public class Package {
    @Getter
    private final double weightLimit;
    @Getter
    private final String ID = UUID.randomUUID().toString();
    private final List<Product> products;

    public List<Product> getProducts() {
        if (Objects.isNull(products))
            return null;
        if (products.isEmpty())
            return new ArrayList<>();

        return products.stream()
                .map(p -> new Product(p.getIndex(), p.getWeight(), p.getCost()))
                .collect(Collectors.toList());
    }
}
