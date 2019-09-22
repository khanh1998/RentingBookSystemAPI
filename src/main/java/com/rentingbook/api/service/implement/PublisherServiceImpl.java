package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.bookdetails.Publisher;
import com.rentingbook.api.repository.PublisherRepository;
import com.rentingbook.api.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {
    private PublisherRepository repository;

    @Autowired
    public void setRepository(PublisherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Publisher> createPublishers(List<Publisher> publishers) {
        return repository.saveAll(publishers);
    }

    @Override
    public List<Publisher> deletePublishers(List<Integer> publisherIDs) {
        List<Publisher> publishers = new ArrayList<>();
        publisherIDs.forEach(publisherID -> {
            Optional<Publisher> publisher = repository.findById(publisherID);
            if (publisher.isPresent()) {
                publishers.add(publisher.get());
                repository.delete(publisher.get());
            }
        });
        return publishers;
    }

    @Override
    public List<Publisher> updatePublishers(List<Publisher> publishers) {
        List<Publisher> updatedPublishers = new ArrayList<>();
        publishers.forEach(publisher -> {
            repository.save(publisher);
            updatedPublishers.add(publisher);
        });
        return updatedPublishers;
    }

    @Override
    public Publisher findByID(int id) {
        return repository.findById(id).get();
    }
}
