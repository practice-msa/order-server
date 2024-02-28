package msa.orderserver.repository;

import msa.orderserver.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Long> {
    Optional<Order> findByOrderId(String orderId);
    List<Order> findByUserId(String userId);
}
