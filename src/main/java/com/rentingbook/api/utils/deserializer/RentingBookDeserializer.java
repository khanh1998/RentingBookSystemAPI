package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.book.bookdetails.Cover;
import com.rentingbook.api.service.BookService;
import com.rentingbook.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonComponent
public class RentingBookDeserializer extends StdDeserializer<RentingBook> {
    private BookService bookService;
    private PublisherService publisherService;

    public RentingBookDeserializer() {
        this(null);
    }

    public RentingBookDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RentingBook deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        RentingBook rentingBook = new RentingBook();

        rentingBook.setBarcode(jsonNode.get("barcode").textValue());
        rentingBook.setBook(bookService.findByISBN(jsonNode.get("book").textValue()));
        rentingBook.setPublisher(publisherService.findByID(jsonNode.get("publisher").asInt()));
        rentingBook.setCover(Cover.valueOf(jsonNode.get("cover").textValue()));
        rentingBook.setRentable(jsonNode.get("rentable").asBoolean());
        rentingBook.setHeight((float) jsonNode.get("height").asDouble());
        rentingBook.setWeight((float) jsonNode.get("weight").asDouble());
        rentingBook.setWidth((float) jsonNode.get("width").asDouble());
        rentingBook.setPrice((float) jsonNode.get("price").asDouble());
        rentingBook.setPages(jsonNode.get("pages").asInt());
        rentingBook.setQuantity(jsonNode.get("quantity").asInt());
        rentingBook.setRented(jsonNode.get("rented").asInt());
        rentingBook.setImageURLs(getListImage(jsonNode.get("imageURLs").iterator()));

        return rentingBook;
    }

    private List<String> getListImage(Iterator<JsonNode> iterator) {
        List<String> imgs = new ArrayList<>();

        while (iterator.hasNext()) {
            imgs.add(iterator.next().textValue());
        }
        return imgs;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setPublisherService(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
}
