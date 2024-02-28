package msa.orderserver.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msa.orderserver.domain.*;
import msa.orderserver.repository.OrderRepository;
import msa.orderserver.vo.order.RequestOrder;
import msa.orderserver.vo.order.ResponseOrder;
import msa.orderserver.vo.order.ResponseUpdateOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

        orderRepository.save(order);
    }

    @Override
    public ResponseUpdateOrder cancelOrder(String orderId) {
        Optional<Order> optionalOrder = orderRepository.findByOrderId(orderId);
        ResponseUpdateOrder responseUpdateOrder = new ResponseUpdateOrder(false);
        optionalOrder.ifPresentOrElse(order -> {
            if(order.getOrderStatus() == OrderStatus.PROCESSING){
                // 여기서 카프카로 해당 제품의 수량을 다시 올려야함.
                order.setOrderStatus(OrderStatus.CANCELLED);
                responseUpdateOrder.setCheck(true);

            }else{
                throw new RuntimeException("해당 주문은 취소 불가");
            }
        },()->{throw new NoSuchElementException("해당 주문 정보는 없습니다.");}
        );
        return responseUpdateOrder;
    }

    @Override
    public List<ResponseOrder> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(ResponseOrder::from).collect(Collectors.toList());
    }
}