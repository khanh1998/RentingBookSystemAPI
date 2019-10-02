package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.model.book.bookdetails.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentingBookRepository extends JpaRepository<RentingBook, Integer> {
    RentingBook findByBarcode(String barcode);

    RentingBook findAllByBook_Isbn(String isbn);

    List<RentingBook> findAllByPublisher(Publisher publisher);

    List<RentingBook> findAllByBook_Authors(List<Author> authors);

    List<RentingBook> findAllByBook_Genres(List<Genre> genres);

    List<RentingBook> findAllByBook_Language(List<Language> languages);

    List<RentingBook> findAllByBook_Title(String title);

    List<RentingBook> findAllByPriceBetween(float begin, float end);
}
