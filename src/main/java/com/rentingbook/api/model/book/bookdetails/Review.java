package com.rentingbook.api.model.book.bookdetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
//@JsonDeserialize(using = ReviewDeserializer.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String account;
    private int rate;
    private String title;
    @Column(length = 2000)
    private String comment;
    @CreationTimestamp
    private LocalDateTime dateTime;
}
