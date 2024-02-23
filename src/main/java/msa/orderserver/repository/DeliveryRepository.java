package msa.orderserver.repository;

import msa.orderserver.domain.Delivery;
import msa.orderserver.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
    Optional<Delivery> findById(Long id);
}
