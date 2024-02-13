package msa.orderserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.orderserver.domain.Order;
import msa.orderserver.dto.OrderDto;
import msa.orderserver.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return OrderDto.from(orderRepository.findByOrderId(orderId));
    }

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getUnitPrice() * orderDetails.getQty());
        Order order = orderDetails.toEntity(orderDetails);
        orderRepository.save(order);
        return OrderDto.from(order);
    }

    @Override
    public Iterable<Order> getOrdersByUserId(String userId) {

        return orderRepository.findByUserId(userId);
    }
}
