package com.rentingbook.api.model.book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RentedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private RentalBook rentalBook;
    private LocalDate receivedDate;
    private int renewalTurn;

    public RentedBook(RentalBook rentalBook, LocalDate receivedDate, int renewalTurn) {
        this.rentalBook = rentalBook;
        this.receivedDate = receivedDate;
        this.renewalTurn = renewalTurn;
    }
}
