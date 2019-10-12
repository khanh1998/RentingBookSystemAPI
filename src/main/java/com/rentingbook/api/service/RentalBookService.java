package com.rentingbook.api.service;

import com.rentingbook.api.model.book.RentalBook;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentalBookService {
    List<RentalBook> createBooks(List<RentalBook> rentalBooks);

    List<RentalBook> findByPage(Pageable page);

    List<RentalBook> findAll();

    RentalBook findByBarcode(String barcode);

    RentalBook save(RentalBook rentalBook);
}
