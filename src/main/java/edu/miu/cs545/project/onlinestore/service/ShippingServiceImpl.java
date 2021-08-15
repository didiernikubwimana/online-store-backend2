package edu.miu.cs545.project.onlinestore.service;

import edu.miu.cs545.project.onlinestore.domain.Shipping;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import edu.miu.cs545.project.onlinestore.repository.ShippingRepository;
@Service
public class ShippingServiceImpl implements ShippingService{
    @Autowired
    ShippingRepository shippingRepository;

    @Override
    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }
}