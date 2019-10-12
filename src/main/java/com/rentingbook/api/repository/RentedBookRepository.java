package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.RentedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedBookRepository extends JpaRepository<RentedBook, Integer> {
}
