package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.RentingBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentingBookRepository extends JpaRepository<RentingBook, Integer> {
    RentingBook findByBarcode(String barcode);
}
