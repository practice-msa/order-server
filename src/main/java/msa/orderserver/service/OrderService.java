package msa.orderserver.service;


import msa.orderserver.domain.OrderEntity;
import msa.orderserver.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
