package com.rentingbook.api.model.user;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.order.BookOrder;
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
    @OneToMany
    private List<RentingBook> savedBooks;
}
