package edu.miu.cs545.project.onlinestore.repository;

import edu.miu.cs545.project.onlinestore.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public List<Order> findAllByBuyerId(long buyerId);
    public  List<Order> findAll();
    public Optional<Order> findById(Long Id);
    public Order findOrderById(Long id);




}