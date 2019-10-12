package com.rentingbook.api.repository;

import com.rentingbook.api.model.order.ReturnBookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnBookOrderRepository extends JpaRepository<ReturnBookOrder, Integer> {
}
