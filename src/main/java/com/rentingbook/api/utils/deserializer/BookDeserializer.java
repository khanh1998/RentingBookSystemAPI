package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.Book;
import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.model.book.bookdetails.Genre;
import com.rentingbook.api.model.book.bookdetails.Language;
import com.rentingbook.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonComponent
public class BookDeserializer extends JsonDeserializer<Book> {
    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        System.out.println("jsonnode:" + jsonNode);
        Book newBook = new Book();
        //ISBN
        newBook.setIsbn(jsonNode.get("isbn").textValue());
        //Title
        newBook.setTitle(jsonNode.get("title").textValue());
        //Description
        newBook.setDescription(jsonNode.get("description").textValue());
        //Authors
        List<Author> authors = getAuthorList(jsonNode.get("authors").iterator());
        newBook.setAuthors(authors);
        //Translators
        List<Author> translators = getAuthorList(jsonNode.get("translators").iterator());
        newBook.setTranslators(translators);
        //Language
        System.out.println("Language:" + jsonNode.get("language").textValue());
        newBook.setLanguage(Language.valueOf(jsonNode.get("language").textValue()));
        //Genres
        List<Genre> genres = getGenreList(jsonNode.get("genres").iterator());
        newBook.setGenres(genres);
        //Published date
        newBook.setPublishedDate(LocalDate.parse(jsonNode.get("publishedDate").textValue()));
        return newBook;
    }

    private List<Genre> getGenreList(Iterator<JsonNode> jsonNodeIterator) {
        List<Genre> genres = new ArrayList<>();
        while (jsonNodeIterator.hasNext()) {
            genres.add(Genre.valueOf(jsonNodeIterator.next().textValue()));
        }
        return genres;
    }

    private List<Author> getAuthorList(Iterator<JsonNode> authorIDs) {
        List<Author> authors = new ArrayList<>();
        while (authorIDs.hasNext()) {
            int authorID = authorIDs.next().intValue();
            authors.add(authorService.findByID(authorID));
        }
        return authors;
    }
}
