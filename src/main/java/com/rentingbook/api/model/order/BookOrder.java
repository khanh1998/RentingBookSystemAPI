package com.rentingbook.api.model.order;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.model.order.orderdetails.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@JsonDeserialize(using = BookOrderDeserializer.class)
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String account;
    private OrderStatus status = OrderStatus.Received;
    @ManyToMany
    private List<RentingBook> books;
    private float shippingFee = 0;
    private boolean cancel = false;
    private String address;
    @CreationTimestamp
    private LocalDateTime dateTime;
    @Transient
    public float getTotalPrice() {
        Optional<Float> total = books.stream()
                .map(RentingBook::getPrice)
                .reduce(Float::sum);
        return total.map(aFloat -> aFloat + shippingFee).orElse(0F);
    }
}
