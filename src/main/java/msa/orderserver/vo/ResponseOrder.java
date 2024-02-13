package msa.orderserver.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import msa.orderserver.domain.Order;
import msa.orderserver.dto.OrderDto;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;

    public static ResponseOrder from(OrderDto orderDto) {
        return ResponseOrder.builder()
                .productId(orderDto.getProductId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .build();
    }

    public static ResponseOrder fromEntity(Order order) {
        return ResponseOrder.builder()
                .productId(order.getProductId())
                .qty(order.getQty())
                .unitPrice(order.getUnitPrice())
                .totalPrice(order.getTotalPrice())
                .orderId(order.getOrderId())
                .build();
    }
}
