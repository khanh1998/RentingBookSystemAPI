package com.rentingbook.api.model.order;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.model.order.orderdetails.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
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
    private List<RentalBook> books;
    private float shippingFee = 0;
    private boolean cancel = false;
    private String address;
    @CreationTimestamp
    private LocalDateTime dateTime;
    private LocalDate deliveredDate;

    @Transient
    public float getTotalRentalPrice() {
        Optional<Float> total = books.stream()
                .map(RentalBook::getRentalPrice)
                .reduce(Float::sum);
        return total.map(aFloat -> aFloat + shippingFee).orElse(0F);
    }

    @Transient
    public float getDeposited() {
        Optional<Float> total = books.stream()
                .map(RentalBook::getOriginalPrice)
                .reduce(Float::sum);
        if (total.isPresent())
            return total.get();
        else
            return 0;
    }
}
