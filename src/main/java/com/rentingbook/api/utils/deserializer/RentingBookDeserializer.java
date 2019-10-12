package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.RentalBook;
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
public class RentingBookDeserializer extends StdDeserializer<RentalBook> {
    private BookService bookService;
    private PublisherService publisherService;

    public RentingBookDeserializer() {
        this(null);
    }

    public RentingBookDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RentalBook deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        RentalBook rentalBook = new RentalBook();

        rentalBook.setBarcode(jsonNode.get("barcode").textValue());
        rentalBook.setBook(bookService.findByISBN(jsonNode.get("book").textValue()));
        rentalBook.setPublisher(publisherService.findByID(jsonNode.get("publisher").asInt()));
        rentalBook.setCover(Cover.valueOf(jsonNode.get("cover").textValue()));
        rentalBook.setRentable(jsonNode.get("rentable").asBoolean());
        rentalBook.setHeight((float) jsonNode.get("height").asDouble());
        rentalBook.setWeight((float) jsonNode.get("weight").asDouble());
        rentalBook.setWidth((float) jsonNode.get("width").asDouble());
        rentalBook.setOriginalPrice((float) jsonNode.get("price").asDouble());
        rentalBook.setPages(jsonNode.get("pages").asInt());
        rentalBook.setQuantity(jsonNode.get("quantity").asInt());
        rentalBook.setRented(jsonNode.get("rented").asInt());
        rentalBook.setImageURLs(getListImage(jsonNode.get("imageURLs").iterator()));

        return rentalBook;
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
