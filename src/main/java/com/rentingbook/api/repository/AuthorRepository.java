package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.bookdetails.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
