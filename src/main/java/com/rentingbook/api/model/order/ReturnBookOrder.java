package com.rentingbook.api.model.order;

import com.rentingbook.api.model.book.RentedBook;
import com.rentingbook.api.model.order.orderdetails.ReturnBookOrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ReturnBookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany
    private List<RentedBook> rentedBooks;
    private String address;
    private float shippingFee;
    private boolean cancel;
    private LocalDate appointmentDate;
    private LocalDate actualReceivedDate;
    private ReturnBookOrderStatus status = ReturnBookOrderStatus.Pending;
    @CreationTimestamp
    private LocalDateTime createdDateTime;
}
