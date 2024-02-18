package msa.orderserver.vo.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.domain.DeliveryStatus;
import msa.orderserver.domain.Order;

@Data
@Builder
public class ResponseOrder {
    private final String productId;
    private final Integer qty;
    private final Integer unitPrice;
    private final Integer totalPrice;
    private final String orderId;
    private final DeliveryStatus deliveryStatus;

    public static ResponseOrder from(Order order) {
        return ResponseOrder.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .orderId(order.getOrderId())
                .deliveryStatus(order.getDelivery().getDeliveryStatus())
                .build();
    }

}
