package com.rentingbook.api.model.book;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.utils.deserializer.BookDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor

//@JsonDeserialize(using = BookDeserializer.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    @NotEmpty
    private String isbn;
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @ManyToMany
    private List<Author> authors;
    @ManyToMany
    private List<Author> translators;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;
    private LocalDate publishedDate;
}
