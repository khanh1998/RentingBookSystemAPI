package com.rentingbook.api.service;

import com.rentingbook.api.model.order.ReturnBookOrder;

import java.util.Optional;

public interface ReturnBookOrderService {
    ReturnBookOrder save(ReturnBookOrder returnBookOrder);

    void delete(ReturnBookOrder returnBookOrder);

    Optional<ReturnBookOrder> findOne(int id);
}
