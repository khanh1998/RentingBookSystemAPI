package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.book.RentedBook;
import com.rentingbook.api.repository.RentedBookRepository;
import com.rentingbook.api.service.RentedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentedBookServiceImpl implements RentedBookService {
    private RentedBookRepository rentedBookRepository;

    @Autowired
    public void setRentedBookRepository(RentedBookRepository rentedBookRepository) {
        this.rentedBookRepository = rentedBookRepository;
    }

    @Override
    public List<RentedBook> save(List<RentedBook> rentedBooks) {
        return rentedBookRepository.saveAll(rentedBooks);
    }

    @Override
    public Optional<RentedBook> findOne(int id) {
        return rentedBookRepository.findById(id);
    }

    @Override
    public void deleteOne(RentedBook rentedBook) {
        rentedBookRepository.delete(rentedBook);
    }
}
