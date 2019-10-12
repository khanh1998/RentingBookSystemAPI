package com.rentingbook.api.model.account;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.order.BookOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Account account;
    @OneToMany
    private List<BookOrder> orders;
    @OneToMany
    private List<RentalBook> savedBooks;
}
