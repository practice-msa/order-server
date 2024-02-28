package msa.orderserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.orderserver.domain.*;
import msa.orderserver.repository.OrderRepository;
import msa.orderserver.vo.order.RequestOrder;
import msa.orderserver.vo.order.ResponseOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void getOrderByOrderId(String orderId) {
        // return OrderDto.from(orderRepository.findByOrderId(orderId));
    }

    @Override
    public void createOrder(RequestOrder requestOrder, String userId) {

        // 여기에 추가 배송 정보도 추가되었음
        Delivery delivery = Delivery.builder()
                .deliveryStatus(DeliveryStatus.valueOf("READY"))
                .deliveryUser(new DeliveryUser(requestOrder.getName(),requestOrder.getAddress(),requestOrder.getPhoneNumber()))
                            .build();
        Order order = requestOrder.toEntity(userId,delivery);
        order.setOrderStatus(OrderStatus.PROCESSING);
        orderRepository.save(order);
    }

    @Override
    public List<ResponseOrder> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(ResponseOrder::from).collect(Collectors.toList());
    }
}
