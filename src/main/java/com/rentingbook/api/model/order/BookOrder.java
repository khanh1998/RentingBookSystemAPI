package com.rentingbook.api.model.order;

import com.rentingbook.api.audit.Auditable;
import com.rentingbook.api.model.account.Account;
import com.rentingbook.api.model.book.RentingBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookOrder extends Auditable<Account> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
    @ManyToOne
    private Account account;
    private String status;
    @OneToMany
    private List<RentingBook> books;
    private float shippingFee;

    @Transient
    public float getTotalPrice() {
        Optional<Float> total = books.stream()
                .map(book -> book.getPrice())
                .reduce((price1, price2) -> price1 + price2);
        if (total.isPresent())
            return total.get() + shippingFee;
        else
            return 0;
    }
}
