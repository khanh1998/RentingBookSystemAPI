package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.bookdetails.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
