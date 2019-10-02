package com.rentingbook.api.service;

import com.rentingbook.api.model.order.BookOrder;

import java.util.Optional;

public interface BookOrderService {
    BookOrder save(BookOrder bookOrder);

    Optional<BookOrder> findById(int id);

    Optional<BookOrder> delete(int id);
}
