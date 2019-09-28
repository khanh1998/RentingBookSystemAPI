package com.rentingbook.api.repository;

import com.rentingbook.api.model.order.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
}
