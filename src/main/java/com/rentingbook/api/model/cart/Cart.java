package com.rentingbook.api.model.cart;

import com.rentingbook.api.model.book.RentingBook;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Cart {
    @ManyToMany
    List<RentingBook> books;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Cart() {
        this.books = new ArrayList<>();
    }
}
