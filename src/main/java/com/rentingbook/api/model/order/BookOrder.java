package com.rentingbook.api.model.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.utils.deserializer.BookOrderDeserializer;
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
@JsonDeserialize(using = BookOrderDeserializer.class)
public class BookOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String account;
    private OrderStatus status;
    @OneToMany
    private List<RentingBook> books;
    private float shippingFee;
    private boolean cancel;
    private String address;
    @CreationTimestamp
    private LocalDateTime dateTime;
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
