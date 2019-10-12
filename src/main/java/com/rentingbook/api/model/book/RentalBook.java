package com.rentingbook.api.model.book;

import com.rentingbook.api.model.book.bookdetails.Cover;
import com.rentingbook.api.model.book.bookdetails.Publisher;
import com.rentingbook.api.model.book.bookdetails.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
//@JsonDeserialize(using = RentingBookDeserializer.class)
public class RentalBook {
    @Id
    private String barcode;
    @ManyToOne
    private Book book;
    private float rentalPrice;
    private float originalPrice;
    private int quantity;
    private int rented;
    private boolean rentable;
    @OneToOne
    private Publisher publisher;
    @OneToMany
    private List<Review> reviews;
    @ElementCollection
    private List<String> imageURLs;
    private float width;
    private float height;
    private float weight;
    private int pages;
    @Enumerated(EnumType.STRING)
    private Cover cover;
    //week
    private int rentalDuration;
    //week
    private int renewalDuration;
    //turn
    private int renewalTurnQuota;
}
