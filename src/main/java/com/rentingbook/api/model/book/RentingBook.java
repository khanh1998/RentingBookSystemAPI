package com.rentingbook.api.model.book;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rentingbook.api.model.book.bookdetails.Cover;
import com.rentingbook.api.model.book.bookdetails.Publisher;
import com.rentingbook.api.model.book.bookdetails.Review;
import com.rentingbook.api.utils.deserializer.RentingBookDeserializer;
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
@JsonDeserialize(using = RentingBookDeserializer.class)
public class RentingBook {
    @Id
    private String barcode;
    @ManyToOne
    private Book book;
    private float price;
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
    private Cover cover;
}
