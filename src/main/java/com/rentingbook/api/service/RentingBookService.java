package com.rentingbook.api.service;

import com.rentingbook.api.model.book.RentingBook;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentingBookService {
    List<RentingBook> createBooks(List<RentingBook> rentingBooks);

    List<RentingBook> findByPage(Pageable page);

    RentingBook findByBarcode(String barcode);

    RentingBook save(RentingBook rentingBook);
}
