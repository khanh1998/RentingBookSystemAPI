package com.rentingbook.api.utils.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rentingbook.api.model.book.bookdetails.Review;
import com.rentingbook.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.time.LocalDateTime;

@JsonComponent
public class ReviewDeserializer extends StdDeserializer<Review> {
    private AccountService accountService;

    public ReviewDeserializer(Class<?> vc) {
        super(vc);
    }

    public ReviewDeserializer() {
        this(null);
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Review deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Review review = new Review();
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        //System.out.println("username:"+username);
        //Account account = accountService.getAccount(username);
        review.setAccount(username);
        review.setComment(jsonNode.get("comment").textValue());
        review.setDateTime(LocalDateTime.now());
        review.setRate(jsonNode.get("rate").asInt());
        return review;
    }
}
