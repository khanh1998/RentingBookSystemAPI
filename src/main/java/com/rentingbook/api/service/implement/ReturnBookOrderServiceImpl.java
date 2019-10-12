package com.rentingbook.api.service.implement;

import com.rentingbook.api.model.order.ReturnBookOrder;
import com.rentingbook.api.repository.ReturnBookOrderRepository;
import com.rentingbook.api.service.ReturnBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReturnBookOrderServiceImpl implements ReturnBookOrderService {
    private ReturnBookOrderRepository returnBookOrderRepository;

    @Autowired
    public void setReturnBookOrderRepository(ReturnBookOrderRepository returnBookOrderRepository) {
        this.returnBookOrderRepository = returnBookOrderRepository;
    }

    @Override
    public ReturnBookOrder save(ReturnBookOrder returnBookOrder) {
        return returnBookOrderRepository.save(returnBookOrder);
    }

    @Override
    public void delete(ReturnBookOrder returnBookOrder) {
        returnBookOrderRepository.delete(returnBookOrder);
    }

    @Override
    public Optional<ReturnBookOrder> findOne(int id) {
        return returnBookOrderRepository.findById(id);
    }
}
