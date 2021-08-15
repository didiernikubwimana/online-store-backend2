package edu.miu.cs545.project.onlinestore.service;

import edu.miu.cs545.project.onlinestore.domain.Seller;
import edu.miu.cs545.project.onlinestore.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Boolean approveSeller(long id) {
        Seller seller = sellerRepository.getSellerById(id);
        if(seller != null){
            seller.getUser().setEnabled(true);
            seller.setApproved(true);
            sellerRepository.save(seller);
            return true;
        }
        return false;
    }
}
