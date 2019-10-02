package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.repository.BookOrderRepository;
import com.rentingbook.api.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookOrderServiceImpl implements BookOrderService {
    private BookOrderRepository bookOrderRepository;

    @Autowired
    public void setBookOrderRepository(BookOrderRepository bookOrderRepository) {
        this.bookOrderRepository = bookOrderRepository;
    }

    @Override
    public BookOrder save(BookOrder bookOrder) {
        return bookOrderRepository.saveAndFlush(bookOrder);
    }

    @Override
    public Optional<BookOrder> findById(int id) {
        return bookOrderRepository.findById(id);
    }

    @Override
    public Optional<BookOrder> delete(int id) {
        Optional<BookOrder> order = findById(id);
        if (order.isPresent()) {
            order.get().setCancel(true);
            return Optional.of(order.get());
        }
        return order;
    }
}
