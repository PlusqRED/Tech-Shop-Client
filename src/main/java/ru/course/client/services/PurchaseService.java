package ru.course.client.services;

import ru.course.client.models.product.commons.Purchase;

import java.util.List;

public interface PurchaseService {
    void save(Long productId, Long appUserId);

    List<Purchase> findAll();
}
