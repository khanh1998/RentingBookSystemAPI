package com.rentingbook.api.service;

import com.rentingbook.api.model.book.bookdetails.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> createPublishers(List<Publisher> publishers);

    List<Publisher> deletePublishers(List<Integer> publisherIDs);

    List<Publisher> updatePublishers(List<Publisher> publishers);

    Publisher findByID(int id);
}
