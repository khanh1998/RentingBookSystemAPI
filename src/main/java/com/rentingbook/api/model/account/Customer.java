package com.rentingbook.api.model.account;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.order.BookOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Account account;
    @OneToMany
    private List<BookOrder> orders;
    @OneToMany
    private List<RentingBook> savedBooks;
}
