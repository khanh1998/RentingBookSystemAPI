package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.RentingBook;
import com.rentingbook.api.repository.RentingBookRepository;
import com.rentingbook.api.service.RentingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentingBookServiceImpl implements RentingBookService {
    private RentingBookRepository repository;

    @Autowired
    public void setRepository(RentingBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RentingBook> createBooks(List<RentingBook> rentingBooks) {
        return repository.saveAll(rentingBooks);
    }

    @Override
    public List<RentingBook> findByPage(Pageable page) {
        Page<RentingBook> books = repository.findAll(page);
        return books.get().collect(Collectors.toList());
    }

    @Override
    public List<RentingBook> findAll() {
        return repository.findAll();
    }

    @Override
    public RentingBook findByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public RentingBook save(RentingBook rentingBook) {
        return repository.save(rentingBook);
    }
}
