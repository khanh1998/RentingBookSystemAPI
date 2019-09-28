package com.rentingbook.api.model.book.bookdetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rentingbook.api.utils.deserializer.ReviewDeserializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonDeserialize(using = ReviewDeserializer.class)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String account;
    private int rate;
    private String comment;
    @CreationTimestamp
    private LocalDateTime dateTime;
}
