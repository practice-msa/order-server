package msa.orderserver.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import msa.orderserver.domain.OrderEntity;

import java.io.Serializable;

@Data
@Builder
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;

    public static OrderDto from(OrderEntity orderEntity) {
        return OrderDto.builder()
                .productId(orderEntity.getProductId())
                .qty(orderEntity.getQty())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .orderId(orderEntity.getOrderId())
                .userId(orderEntity.getUserId())
                .build();
    }


    public OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .orderId(orderDto.getOrderId())
                .qty(orderDto.getQty())
                .unitPrice(orderDto.getUnitPrice())
                .totalPrice(orderDto.getTotalPrice())
                .productId(orderDto.getProductId())
                .userId(orderDto.getUserId())
                .build();
    }
}
