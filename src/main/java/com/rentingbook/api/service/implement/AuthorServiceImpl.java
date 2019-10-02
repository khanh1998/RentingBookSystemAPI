package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.bookdetails.Author;
import com.rentingbook.api.repository.AuthorRepository;
import com.rentingbook.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository repository;

    @Autowired
    public void setRepository(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Author> createAuthors(List<Author> authors) {
        return repository.saveAll(authors);
    }

    @Override
    public List<Author> deleteAuthors(List<Integer> ids) {
        List<Author> authors = new ArrayList<>();
        ids.forEach(id -> {
            Optional<Author> author = repository.findById(id);
            if (author.isPresent()) {
                authors.add(author.get());
                repository.delete(author.get());
            }
        });
        return authors;
    }

    @Override
    public List<Author> updateAuthors(List<Author> authors) {
        List<Author> updatedAuthors = new ArrayList<>();
        authors.forEach(author -> {
            repository.saveAndFlush(author);
            updatedAuthors.add(author);
        });
        return updatedAuthors;
    }

    @Override
    public Optional<Author> findByID(int id) {
        return repository.findById(id);
    }
}
