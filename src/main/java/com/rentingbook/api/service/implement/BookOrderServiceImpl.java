package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.repository.BookOrderRepository;
import com.rentingbook.api.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    private BookOrderRepository bookOrderRepository;

    @Autowired
    public void setBookOrderRepository(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder save(BookOrder bookOrder) {
        return bookOrderRepository.save(bookOrder);
    }

    @Override
    public BookOrder findById(int id) {
        return bookOrderRepository.findById(id).get();
    }

    @Override
    public BookOrder delete(int id) {
        BookOrder order = bookOrderRepository.findById(id).get();
        order.setCancel(true);
        return order;
    }
}
