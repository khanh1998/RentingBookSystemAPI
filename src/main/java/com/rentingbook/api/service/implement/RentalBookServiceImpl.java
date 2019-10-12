package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.RentalBook;
import com.rentingbook.api.repository.RentalBookRepository;
import com.rentingbook.api.service.RentalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentalBookServiceImpl implements RentalBookService {
    private RentalBookRepository repository;

    @Autowired
    public void setRepository(RentalBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RentalBook> createBooks(List<RentalBook> rentalBooks) {
        return repository.saveAll(rentalBooks);
    }

    @Override
    public List<RentalBook> findByPage(Pageable page) {
        Page<RentalBook> books = repository.findAll(page);
        return books.get().collect(Collectors.toList());
    }

    @Override
    public List<RentalBook> findAll() {
        return repository.findAll();
    }

    @Override
    public RentalBook findByBarcode(String barcode) {
        return repository.findByBarcode(barcode);
    }

    @Override
    public RentalBook save(RentalBook rentalBook) {
        return repository.save(rentalBook);
    }
}
