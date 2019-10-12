package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.order.orderdetails.OrderStatus;
import com.rentingbook.api.service.RentalBookService;
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
    private RentalBookService rentalBookService;

    public BookOrderDeserializer() {
        this(null);
    }

    public BookOrderDeserializer(Class<?> vc) {
        super(vc);
    }

    @Autowired
    public void setRentalBookService(RentalBookService rentalBookService) {
        this.rentalBookService = rentalBookService;
    }

    @Override
    public BookOrder deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        BookOrder bookOrder = new BookOrder();

        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        Iterator<JsonNode> books = jsonNode.get("books").iterator();
        List<RentalBook> rentalBooks = getRentingBooks(books);
        changeQuantityOfBookInRepository(rentalBooks);

        bookOrder.setCancel(false);
        bookOrder.setBooks(rentalBooks);
        bookOrder.setAccount(username);
        bookOrder.setAddress(jsonNode.get("address").textValue());
        bookOrder.setStatus(OrderStatus.Accepted);
        bookOrder.setShippingFee(0);
        return bookOrder;
    }

    private void changeQuantityOfBookInRepository(List<RentalBook> books) {
        books.forEach(book -> {
            book.setQuantity(book.getQuantity() - 1);
            book.setRented(book.getRented() + 1);
            rentalBookService.save(book);
        });
    }

    private List<RentalBook> getRentingBooks(Iterator<JsonNode> iterator) {
        List<RentalBook> books = new ArrayList<>();
        while (iterator.hasNext()) {
            String barcode = iterator.next().textValue();
            books.add(rentalBookService.findByBarcode(barcode));
        }
        return books;
    }
}
