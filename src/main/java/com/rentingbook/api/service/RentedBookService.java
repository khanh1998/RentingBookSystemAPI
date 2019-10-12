package com.rentingbook.api.service;

import com.rentingbook.api.model.book.RentedBook;

import java.util.List;
import java.util.Optional;

public interface RentedBookService {
    List<RentedBook> save(List<RentedBook> rentedBooks);

    Optional<RentedBook> findOne(int id);

    void deleteOne(RentedBook rentedBook);
}
