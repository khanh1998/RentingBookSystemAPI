package com.rentingbook.api.model.account;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.book.RentedBook;
import com.rentingbook.api.model.cart.Cart;
import com.rentingbook.api.model.order.BookOrder;
import com.rentingbook.api.model.order.ReturnBookOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Email
    private String email;
    private String fullName;
    @JsonIgnore
    private String password;
    private boolean enable;
    @ManyToOne
    private AccountRole accountRole;
    @OneToMany
    private List<BookOrder> orders;
    @ManyToMany
    private List<RentalBook> savedBooks;
    @OneToOne
    private Cart cart;
    @ManyToMany
    private List<RentedBook> currentRentalBook;
    @OneToMany
    private List<ReturnBookOrder> returnBookOrders;
}
