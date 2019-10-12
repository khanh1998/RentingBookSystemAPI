package com.rentingbook.api.repository;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.model.book.bookdetails.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalBookRepository extends JpaRepository<RentalBook, Integer> {
    RentalBook findByBarcode(String barcode);

    RentalBook findAllByBook_Isbn(String isbn);

    List<RentalBook> findAllByPublisher(Publisher publisher);

    List<RentalBook> findAllByBook_Authors(List<Author> authors);

    List<RentalBook> findAllByBook_Genres(List<Genre> genres);

    List<RentalBook> findAllByBook_Language(List<Language> languages);

    List<RentalBook> findAllByBook_Title(String title);

    List<RentalBook> findAllByRentalPriceBetween(float begin, float end);
}
