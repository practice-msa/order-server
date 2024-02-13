package msa.orderserver.service;


import msa.orderserver.domain.Order;
import msa.orderserver.dto.OrderDto;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<Order> getOrdersByUserId(String userId);
}
