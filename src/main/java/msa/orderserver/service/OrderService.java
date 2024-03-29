package msa.orderserver.service;


import msa.orderserver.domain.Order;
import msa.orderserver.vo.order.RequestOrder;
import msa.orderserver.vo.order.RequestUpdateOrder;
import msa.orderserver.vo.order.ResponseOrder;
import msa.orderserver.vo.order.ResponseUpdateOrder;

import java.util.List;

public interface OrderService {
    void createOrder(RequestOrder orderDetails, String userId);
    void getOrderByOrderId(String orderId);
    List<ResponseOrder> getOrdersByUserId(String userId);

    // 주문 취소 -> 현재 배송 상태에 따라서 주문 취소 가능한지 불가능한지 판별해야함
    ResponseUpdateOrder cancelOrder(String orderId);

    // 해당 주문에서 배송지등 변경
    Boolean updateOrder(String orderId, RequestUpdateOrder requestUpdateOrder);
}
