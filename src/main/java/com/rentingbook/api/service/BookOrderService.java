package com.rentingbook.api.service;

import com.rentingbook.api.model.order.BookOrder;

public interface BookOrderService {
    BookOrder save(BookOrder bookOrder);

    BookOrder findById(int id);

    BookOrder delete(int id);
}
