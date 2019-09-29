package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.order.OrderStatus;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonComponent
public class BookOrderDeserializer extends StdDeserializer<BookOrder> {
    private RentingBookService rentingBookService;

    public BookOrderDeserializer() {
        this(null);
    }

    public BookOrderDeserializer(Class<?> vc) {
        super(vc);
    }

    @Autowired
    public void setRentingBookService(RentingBookService rentingBookService) {
        this.rentingBookService = rentingBookService;
    }

    @Override
    public BookOrder deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        BookOrder bookOrder = new BookOrder();

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> books = jsonNode.get("books").iterator();
        List<RentingBook> rentingBooks = getRentingBooks(books);
        changeQuantityOfBookInRepository(rentingBooks);

        bookOrder.setCancel(false);
        bookOrder.setBooks(rentingBooks);
        bookOrder.setAccount(username);
        bookOrder.setAddress(jsonNode.get("address").textValue());
        bookOrder.setStatus(OrderStatus.Accepted);
        bookOrder.setShippingFee(0);
        return bookOrder;
    }

    private void changeQuantityOfBookInRepository(List<RentingBook> books) {
        books.forEach(book -> {
            book.setQuantity(book.getQuantity() - 1);
            book.setRented(book.getRented() + 1);
            rentingBookService.save(book);
        });
    }

    private List<RentingBook> getRentingBooks(Iterator<JsonNode> iterator) {
        List<RentingBook> books = new ArrayList<>();
        while (iterator.hasNext()) {
            String barcode = iterator.next().textValue();
            books.add(rentingBookService.findByBarcode(barcode));
        }
        return books;
    }
}
