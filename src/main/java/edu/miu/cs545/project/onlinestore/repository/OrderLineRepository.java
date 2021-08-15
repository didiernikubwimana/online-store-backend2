package edu.miu.cs545.project.onlinestore.repository;

import edu.miu.cs545.project.onlinestore.domain.OrderLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
    @Query("SELECT ol.order FROM OrderLine ol WHERE ol.id = :orderId")
    public List<OrderLine> findAll();
    public List<OrderLine> getOrderLineById(@Param("orderId") Long orderId);


}
