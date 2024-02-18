package msa.orderserver.service;


import msa.orderserver.domain.Order;
import msa.orderserver.vo.order.RequestOrder;
import msa.orderserver.vo.order.ResponseOrder;

import java.util.List;

public interface OrderService {
    void createOrder(RequestOrder orderDetails, String userId);
    void getOrderByOrderId(String orderId);
    List<ResponseOrder> getOrdersByUserId(String userId);
}
