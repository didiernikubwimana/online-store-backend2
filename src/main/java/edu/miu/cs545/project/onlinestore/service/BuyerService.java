package edu.miu.cs545.project.onlinestore.service;

import edu.miu.cs545.project.onlinestore.domain.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer findBuyerById(Long id);
    List<Buyer> findAll();
}
