package edu.miu.cs545.project.onlinestore.service;

import edu.miu.cs545.project.onlinestore.repository.BuyerRepository;
import edu.miu.cs545.project.onlinestore.domain.Buyer;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService{
    @Autowired
    BuyerRepository buyerRepository;

    @Override
    public Buyer findBuyerById(Long id) {
        return buyerRepository.findBuyerById(id);
    }

    @Override
    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }
}
