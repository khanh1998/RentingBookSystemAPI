package com.rentingbook.api.model.book;

import com.rentingbook.api.model.book.bookdetails.Cover;
import com.rentingbook.api.model.book.bookdetails.Publisher;
import com.rentingbook.api.model.book.bookdetails.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RentingBook implements Serializable {
    @Id
    private String barcode;
    @OneToOne
    private Book book;
    private float price;
    private int quantity;
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
